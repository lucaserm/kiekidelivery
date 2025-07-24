package site.lmacedo.kiekidelivery.courier.management.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.lmacedo.kiekidelivery.courier.management.domain.model.Courier;

import java.util.Optional;
import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {
    Optional<Courier> findTop1ByOrderByLastFulfilledDeliveryAtAsc();
    Optional<Courier> findByPendingDeliveries_id(UUID deliveryId);
}
