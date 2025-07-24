package site.lmacedo.kiekidelivery.courier.management.domain.model;

import java.time.OffsetDateTime;

public class DeliveryPickedUpEvent {
    private OffsetDateTime occurredAt;
    private String deliveryId;
}
