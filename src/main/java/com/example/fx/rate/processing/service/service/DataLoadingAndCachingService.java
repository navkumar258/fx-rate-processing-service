package com.example.fx.rate.processing.service.service;

import com.example.fx.rate.processing.service.model.Subscription;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataLoadingAndCachingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataLoadingAndCachingService.class);
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static final Map<String, List<Subscription>> CACHE = new ConcurrentHashMap<>();

  @PostConstruct
  public void populateSubscriptionsCache() {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("subscriptions_and_users.json")) {
      LOGGER.info("START loading subscriptions data...");
      List<Subscription> subscriptions = OBJECT_MAPPER.readValue(inputStream, new TypeReference<List<Subscription>>() {
      });
      CACHE.putAll(subscriptions.stream()
              .collect(Collectors.groupingBy(Subscription::getCurrencyPair,
                      Collectors.mapping(Function.identity(), Collectors.toList()))));
      LOGGER.info("END loaded {} currencyPairs successfully!", CACHE.size());
    } catch (IOException e) {
      LOGGER.error("Failed to load subscriptions data: {}", e.getMessage());
    }
  }

  @PreDestroy
  public void clearSubscriptionsCache() {
    CACHE.clear();
  }
}
