package com.example.fx.rate.processing.service.listener;

import com.example.fx.rate.processing.service.dto.SubscriptionChange;
import com.example.fx.rate.processing.service.model.Subscription;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.fx.rate.processing.service.service.DataLoadingAndCachingService.CACHE;

@Service
public class SubscriptionChangesListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionChangesListener.class);

  @KafkaListener(topics = "#{'${spring.kafka.topic.subscription-changes}'.split(',')}",
  containerFactory = "subscriptionChangeKafkaListenerContainerFactory")
  public void consume(ConsumerRecord<String, SubscriptionChange> message) {
    LOGGER.info("[SubscriptionChangesListener] Received message with key: {}, value: {}", message.key(), message.value());

    processMessage(message);
  }

  private void processMessage(ConsumerRecord<String, SubscriptionChange> message) {
    SubscriptionChange subscriptionChange = message.value();
    Subscription payload = subscriptionChange.payload();
    String eventType = subscriptionChange.eventType();

    if ("SubscriptionCreated".equals(eventType) || "SubscriptionUpdated".equals(eventType)) {
      CACHE.computeIfAbsent(payload.getCurrencyPair(), k -> Collections.synchronizedList(new ArrayList<>()))
              .add(payload);
    } else if ("SubscriptionDeleted".equals(subscriptionChange.eventType())) {
      List<Subscription> subscriptions = CACHE.get(payload.getCurrencyPair());

      if (subscriptions != null) {
        subscriptions.removeIf(s -> s.getId().equals(message.key()));

        if (subscriptions.isEmpty()) {
          CACHE.remove(payload.getCurrencyPair());
        }
      }
    }
  }
}
