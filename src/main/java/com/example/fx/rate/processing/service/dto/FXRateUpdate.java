package com.example.fx.rate.processing.service.dto;

import java.math.BigDecimal;

public record FXRateUpdate(
        String currencyPair,
        BigDecimal bid,
        BigDecimal ask,
        BigDecimal mid,
        long timestamp
) {}
