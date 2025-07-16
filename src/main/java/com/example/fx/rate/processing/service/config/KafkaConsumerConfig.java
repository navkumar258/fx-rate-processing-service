package com.example.fx.rate.processing.service.config;

import com.example.fx.rate.processing.service.dto.FXRateUpdate;
import com.example.fx.rate.processing.service.dto.SubscriptionChange;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Value(value = "${spring.kafka.consumer-group-id}")
  private String consumerGroupId;

  @Bean
  public ConsumerFactory<String, FXRateUpdate> fxRatesConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.fx.rate.processing.service.dto.FXRateUpdate");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, FXRateUpdate>
  fxRatesKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, FXRateUpdate> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(fxRatesConsumerFactory());
    return factory;
  }

  @Bean
  public ConsumerFactory<String, SubscriptionChange> subscriptionChangeConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.fx.rate.processing.service.dto.SubscriptionChange");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, SubscriptionChange>
  subscriptionChangeKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, SubscriptionChange> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(subscriptionChangeConsumerFactory());
    return factory;
  }
}
