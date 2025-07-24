package site.lmacedo.kiekidelivery.delivery.tracking.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lmacedo.kiekidelivery.delivery.tracking.api.model.ContactPointInput;
import site.lmacedo.kiekidelivery.delivery.tracking.api.model.DeliveryInput;
import site.lmacedo.kiekidelivery.delivery.tracking.api.model.ItemInput;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.exception.DomainException;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.model.ContactPoint;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.model.Delivery;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.repository.DeliveryRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryPreparationService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public Delivery draft(DeliveryInput input) {
        Delivery delivery = Delivery.draft();
        handlePreparation(input, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }

    @Transactional
    public Delivery edit(UUID deliveryId, DeliveryInput input) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(DomainException::new);
        delivery.removeItems();
        handlePreparation(input, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }


    private void handlePreparation(DeliveryInput input, Delivery delivery) {
        ContactPointInput senderInput = input.sender();
        ContactPointInput recipientInput = input.recipient();

        ContactPoint sender = handleContactPoint(senderInput);
        ContactPoint recipient = handleContactPoint(recipientInput);

        Duration expectedDeliveryTime = Duration.ofHours(3);
        BigDecimal deliveryFee = new BigDecimal("10");

        BigDecimal payout = new BigDecimal("10");

        Delivery.PreparationDetails preparationDetails = Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .expectedDeliveryTime(expectedDeliveryTime)
                .courierPayout(payout)
                .distanceFee(deliveryFee)
                .build();

        delivery.editPreparationDetails(preparationDetails);

        for (ItemInput item : input.items()) {
            delivery.addItem(item.name(), item.quantity());
        }
    }

    private ContactPoint handleContactPoint(ContactPointInput input) {
        return ContactPoint.builder()
                .zipCode(input.zipCode())
                .street(input.street())
                .number(input.number())
                .complement(input.complement())
                .name(input.name())
                .phone(input.phone())
                .build();
    }
}
