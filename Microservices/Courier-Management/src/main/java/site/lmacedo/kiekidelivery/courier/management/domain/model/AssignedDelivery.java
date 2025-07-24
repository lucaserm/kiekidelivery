package site.lmacedo.kiekidelivery.courier.management.domain.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AssignedDelivery {

    @EqualsAndHashCode.Include
    private UUID id;

    private OffsetDateTime assignedAt;

    static AssignedDelivery pending(UUID id) {
        AssignedDelivery delivery = new AssignedDelivery();
        delivery.setId(id);
        delivery.setAssignedAt(OffsetDateTime.now());
        return delivery;
    }
}
