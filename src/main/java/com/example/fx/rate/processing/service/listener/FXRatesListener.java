package com.example.fx.rate.processing.service.listener;

import com.example.fx.rate.processing.service.dto.FXRateUpdate;
import com.example.fx.rate.processing.service.model.Subscription;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.example.fx.rate.processing.service.service.DataLoadingAndCachingService.CACHE;

@Service
public class FXRatesListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(FXRatesListener.class);

  @KafkaListener(topics = {"${spring.kafka.topic.fx-rates}"},
  containerFactory = "fxRatesKafkaListenerContainerFactory")
  public void consume(ConsumerRecord<String, FXRateUpdate> message) {
    LOGGER.info("[FXRatesListener] Received message with key: {}, value: {}", message.key(), message.value());

    processMessage(message);
  }

  private void processMessage(ConsumerRecord<String, FXRateUpdate> message) {
    String currencyPair = message.key();
    FXRateUpdate value = message.value();

    for (Subscription subscription : CACHE.get(currencyPair)) {
      if (subscription.getDirection().equals("BELOW")
              && value.bid().compareTo(subscription.getThreshold()) < 0) {
        LOGGER.info("[FXRatesListener] Sending BELOW notification to user {}, " +
                        "subscription {}, " +
                        "currency-pair {}, " +
                        "bid {}, threshold {}",
                subscription.getUserId(),
                subscription.getId(),
                subscription.getCurrencyPair(),
                value.bid(),
                subscription.getThreshold()
        );
      } else if (subscription.getDirection().equals("ABOVE")
              && value.ask().compareTo(subscription.getThreshold()) > 0) {
        LOGGER.info("[FXRatesListener] Sending ABOVE notification to user {}, " +
                        "subscription {}, " +
                        "currency-pair {}, " +
                        "ask {}, threshold {}",
                subscription.getUserId(),
                subscription.getId(),
                subscription.getCurrencyPair(),
                value.ask(),
                subscription.getThreshold()
        );
      }
    }
  }
}
