package com.example.fx.rate.processing.service.model;

import java.math.BigDecimal;
import java.util.Objects;

public class FXRateUpdate {

  private String currencyPair;
  private BigDecimal bid;
  private BigDecimal ask;
  private BigDecimal mid;
  private long timestamp;

  public FXRateUpdate() {}

  public FXRateUpdate(String currencyPair, BigDecimal bid, BigDecimal ask, BigDecimal mid, long timestamp) {
    this.currencyPair = currencyPair;
    this.bid = bid;
    this.ask = ask;
    this.mid = mid;
    this.timestamp = timestamp;
  }

  public String getCurrencyPair() {
    return currencyPair;
  }

  public void setCurrencyPair(String currencyPair) {
    this.currencyPair = currencyPair;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public void setBid(BigDecimal bid) {
    this.bid = bid;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public void setAsk(BigDecimal ask) {
    this.ask = ask;
  }

  public BigDecimal getMid() {
    return mid;
  }

  public void setMid(BigDecimal mid) {
    this.mid = mid;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof FXRateUpdate fxRateUpdate)) return false;
    return Objects.equals(getCurrencyPair(), fxRateUpdate.getCurrencyPair())
            && Objects.equals(getBid(), fxRateUpdate.getBid())
            && Objects.equals(getAsk(), fxRateUpdate.getAsk())
            && Objects.equals(getMid(), fxRateUpdate.getMid())
            && Objects.equals(getTimestamp(), fxRateUpdate.getTimestamp());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCurrencyPair(), getBid(), getAsk(), getMid(), getTimestamp());
  }

  @Override
  public String toString() {
    return "FXRateUpdate{" +
            "currencyPair='" + currencyPair + '\'' +
            ", bid=" + bid +
            ", ask=" + ask +
            ", mid=" + mid +
            ", timestamp=" + timestamp +
            '}';
  }
}
