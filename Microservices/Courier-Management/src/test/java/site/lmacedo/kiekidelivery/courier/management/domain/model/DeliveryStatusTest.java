package site.lmacedo.kiekidelivery.courier.management.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryStatusTest {

    @Test
    void draft_canChangeToWaitingForCourier() {
        DeliveryStatus current = DeliveryStatus.DRAFT;
        DeliveryStatus newStatus = DeliveryStatus.WAITING_FOR_COURIER;

        assertTrue(current.canChangeTo(newStatus));
    }

    @Test
    void delivered_canNotChangeToWaitingForCourier() {
        DeliveryStatus current = DeliveryStatus.DELIVERED;
        DeliveryStatus newStatus = DeliveryStatus.WAITING_FOR_COURIER;

        assertFalse(current.canChangeTo(newStatus));
    }

}