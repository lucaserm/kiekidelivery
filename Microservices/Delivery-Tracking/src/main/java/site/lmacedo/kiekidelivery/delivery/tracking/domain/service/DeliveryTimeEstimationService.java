package site.lmacedo.kiekidelivery.delivery.tracking.domain.service;


import site.lmacedo.kiekidelivery.delivery.tracking.domain.model.ContactPoint;

public interface DeliveryTimeEstimationService {
    DeliveryEstimate estimate(ContactPoint sender, ContactPoint recipient);
}
