package site.lmacedo.kiekidelivery.delivery.tracking.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lmacedo.kiekidelivery.delivery.tracking.api.model.CourierIdInput;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.exception.DomainException;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.repository.DeliveryRepository;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryCheckpointService {
    private final DeliveryRepository deliveryRepository;

    public void place(UUID deliveryId) {
        deliveryRepository.findById(deliveryId)
                .ifPresentOrElse(
                        delivery -> {
                            delivery.place();
                            deliveryRepository.saveAndFlush(delivery);
                        },
                        () -> {
                            throw new DomainException("Delivery not found");
                        }
                );
    }

    public void pickUp(UUID deliveryId, CourierIdInput input) {
        deliveryRepository.findById(deliveryId)
                .ifPresentOrElse(
                        delivery -> {
                            delivery.pickUp(input.courierId());
                            deliveryRepository.saveAndFlush(delivery);
                        },
                        () -> {
                            throw new DomainException("Delivery not found");
                        }
                );
    }

    public void complete(UUID deliveryId) {
        deliveryRepository.findById(deliveryId)
                .ifPresentOrElse(
                        delivery -> {
                            delivery.markAsDelivered();
                            deliveryRepository.saveAndFlush(delivery);
                        },
                        () -> {
                            throw new DomainException("Delivery not found");
                        }
                );
    }
}
