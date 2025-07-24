package site.lmacedo.kiekidelivery.delivery.tracking.domain.model;

import java.time.OffsetDateTime;

public class DeliveryPickedUpEvent {
    private OffsetDateTime occurredAt;
    private String deliveryId;
}
