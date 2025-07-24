package site.lmacedo.kiekidelivery.delivery.tracking.domain.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.model.ContactPoint;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.model.Delivery;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void shouldPersist() {
        Delivery delivery = Delivery.draft();
        delivery.addItem("Computador", 2);
        delivery.addItem("Notebook", 2);
        delivery.editPreparationDetails(createdValidPreparationDetails());

        deliveryRepository.saveAndFlush(delivery);

        Delivery persistedDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow();

        assertEquals(2, persistedDelivery.getItems().size());
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