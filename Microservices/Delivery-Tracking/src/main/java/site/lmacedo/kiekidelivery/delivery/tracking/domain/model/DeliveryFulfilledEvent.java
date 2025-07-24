package site.lmacedo.kiekidelivery.delivery.tracking.domain.model;

import java.time.OffsetDateTime;

public class DeliveryFulfilledEvent {
    private OffsetDateTime occurredAt;
    private String deliveryId;
}
