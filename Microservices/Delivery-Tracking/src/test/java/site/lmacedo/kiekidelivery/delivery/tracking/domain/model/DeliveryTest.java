package site.lmacedo.kiekidelivery.delivery.tracking.domain.model;

import org.junit.jupiter.api.Test;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.exception.DomainException;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    public void shouldChangeToPlaced() {
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createdValidPreparationDetails());

        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());
    }

    @Test
    public void shouldNotPlace() {
        Delivery delivery = Delivery.draft();

        assertThrows(DomainException.class, delivery::place);

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlacedAt());
    }

    private Delivery.PreparationDetails createdValidPreparationDetails() {
        ContactPoint sender = ContactPoint.builder()
                .zipCode("000000-000")
                .street("Rua São Paulo")
                .number("123")
                .complement("Apto 101")
                .name("João da Silva")
                .phone("(11) 99999-9999")
                .build();

        ContactPoint recipient = ContactPoint.builder()
                .zipCode("111111-111")
                .street("Avenida Rio de Janeiro")
                .number("456")
                .complement("Casa 2")
                .name("Maria Oliveira")
                .phone("(21) 88888-8888")
                .build();

        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofHours(5))
                .build();
    }

}