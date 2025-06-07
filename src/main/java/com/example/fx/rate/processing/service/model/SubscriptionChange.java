package com.example.fx.rate.processing.service.model;

import java.util.Objects;

public class SubscriptionChange {

  private String eventId;
  private long timestamp;
  private String eventType;
  private Subscription payload;

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public Subscription getPayload() {
    return payload;
  }

  public void setPayload(Subscription payload) {
    this.payload = payload;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SubscriptionChange that)) return false;
    return Objects.equals(getEventId(), that.getEventId())
            && Objects.equals(getTimestamp(), that.getTimestamp())
            && Objects.equals(getEventType(), that.getEventType())
            && Objects.equals(getPayload(), that.getPayload());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEventId(), getTimestamp(), getEventType(), getPayload());
  }

  @Override
  public String toString() {
    return "SubscriptionChange{" +
            "eventId='" + eventId + '\'' +
            ", timestamp='" + timestamp + '\'' +
            ", eventType='" + eventType + '\'' +
            ", payload=" + payload +
            '}';
  }
}
