package com.example.fx.rate.processing.service.dto;

import com.example.fx.rate.processing.service.model.Subscription;

public record SubscriptionChange(
        String eventId,
        long timestamp,
        String eventType,
        Subscription payload
) {}
