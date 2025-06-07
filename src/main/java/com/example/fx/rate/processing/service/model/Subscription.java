package com.example.fx.rate.processing.service.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Subscription {

  private String id;
  private String userId;
  private String currencyPair;
  private BigDecimal threshold;
  private String direction;
  private List<String> notificationChannels;
  private String status;
  private String createdAt;
  private String updatedAt;
  private String lastTriggeredAt;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCurrencyPair() {
    return currencyPair;
  }

  public void setCurrencyPair(String currencyPair) {
    this.currencyPair = currencyPair;
  }

  public BigDecimal getThreshold() {
    return threshold;
  }

  public void setThreshold(BigDecimal threshold) {
    this.threshold = threshold;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public List<String> getNotificationChannels() {
    return notificationChannels;
  }

  public void setNotificationChannels(List<String> notificationChannels) {
    this.notificationChannels = notificationChannels;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getLastTriggeredAt() {
    return lastTriggeredAt;
  }

  public void setLastTriggeredAt(String lastTriggeredAt) {
    this.lastTriggeredAt = lastTriggeredAt;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Subscription that)) return false;
    return Objects.equals(getCreatedAt(), that.getCreatedAt())
            && Objects.equals(getUpdatedAt(), that.getUpdatedAt())
            && Objects.equals(getLastTriggeredAt(), that.getLastTriggeredAt())
            && Objects.equals(getId(), that.getId())
            && Objects.equals(getUserId(), that.getUserId())
            && Objects.equals(getCurrencyPair(), that.getCurrencyPair())
            && Objects.equals(getThreshold(), that.getThreshold())
            && Objects.equals(getDirection(), that.getDirection())
            && Objects.equals(getNotificationChannels(), that.getNotificationChannels())
            && Objects.equals(getStatus(), that.getStatus());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            getId(),
            getUserId(),
            getCurrencyPair(),
            getThreshold(),
            getDirection(),
            getNotificationChannels(),
            getStatus(),
            getCreatedAt(),
            getUpdatedAt(),
            getLastTriggeredAt());
  }

  @Override
  public String toString() {
    return "Subscription{" +
            "id='" + id + '\'' +
            ", userId='" + userId + '\'' +
            ", currencyPair='" + currencyPair + '\'' +
            ", threshold=" + threshold +
            ", direction='" + direction + '\'' +
            ", notificationsChannels=" + notificationChannels +
            ", status='" + status + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", lastTriggeredAt=" + lastTriggeredAt +
            '}';
  }
}
