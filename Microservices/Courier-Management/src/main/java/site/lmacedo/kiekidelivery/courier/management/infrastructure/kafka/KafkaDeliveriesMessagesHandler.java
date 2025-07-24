package site.lmacedo.kiekidelivery.courier.management.infrastructure.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import site.lmacedo.kiekidelivery.courier.management.domain.service.CourierDeliveryService;
import site.lmacedo.kiekidelivery.courier.management.infrastructure.event.DeliveryFulfilledIntegrationEvent;
import site.lmacedo.kiekidelivery.courier.management.infrastructure.event.DeliveryPlacedIntegrationEvent;

@Component
@KafkaListener(topics = {
        "deliveries.v1.events"
}, groupId = "courier-management")
@Slf4j
@RequiredArgsConstructor
public class KafkaDeliveriesMessagesHandler {

    private final CourierDeliveryService courierDeliveryService;

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Object event) {
        log.info("Default handler: {}", event);
    }

    @KafkaHandler
    public void handle(@Payload DeliveryPlacedIntegrationEvent event) {
        log.info("Delivery placed event received: {}", event);
        courierDeliveryService.assign(event.getDeliveryId());
    }

    @KafkaHandler
    public void handle(@Payload DeliveryFulfilledIntegrationEvent event) {
        log.info("Delivery placed event received: {}", event);
        courierDeliveryService.fulfill(event.getDeliveryId());
    }
}
