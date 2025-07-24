package site.lmacedo.kiekidelivery.delivery.tracking.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.model.Delivery;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
}
