package site.lmacedo.kiekidelivery.courier.management.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.lmacedo.kiekidelivery.courier.management.domain.model.Courier;

import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {
}
