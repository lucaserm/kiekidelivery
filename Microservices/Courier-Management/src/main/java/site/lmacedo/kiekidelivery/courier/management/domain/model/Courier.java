package site.lmacedo.kiekidelivery.courier.management.domain.model;


import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
public class Courier {

    @EqualsAndHashCode.Include
    private UUID id;

    @Setter(AccessLevel.PUBLIC)
    private String name;

    @Setter(AccessLevel.PUBLIC)
    private String phone;

    private Integer fulfilledDeliveriesQuantity;

    private Integer pendingDeliveriesQuantity;

    private OffsetDateTime lastFulfilledDeliveryAt;

    private List<AssignedDelivery> pendingDeliveries = new ArrayList<>();


    public static Courier brandNew(String name, String phone) {
        Courier courier = new Courier();
        courier.setId(UUID.randomUUID());
        courier.setName(name);
        courier.setPhone(phone);
        courier.setPendingDeliveriesQuantity(0);
        courier.setFulfilledDeliveriesQuantity(0);
        return courier;
    }

    public void assign(UUID deliveryId) {
        this.pendingDeliveries.add(AssignedDelivery.pending(deliveryId));
        this.setPendingDeliveriesQuantity(this.getPendingDeliveriesQuantity() + 1);
    }

    public void fulfill(UUID deliveryId) {
        this.pendingDeliveries.removeIf(delivery -> delivery.getId().equals(deliveryId));
        this.setPendingDeliveriesQuantity(this.getPendingDeliveriesQuantity() - 1);
        this.setFulfilledDeliveriesQuantity(this.getFulfilledDeliveriesQuantity() + 1);
        this.setLastFulfilledDeliveryAt(OffsetDateTime.now());
    }

    public List<AssignedDelivery> getPendingDeliveries() {
        return Collections.unmodifiableList(pendingDeliveries);
    }
}
