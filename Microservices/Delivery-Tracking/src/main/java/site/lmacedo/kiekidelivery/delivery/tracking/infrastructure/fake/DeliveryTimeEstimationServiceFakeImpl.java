package site.lmacedo.kiekidelivery.delivery.tracking.infrastructure.fake;

import org.springframework.stereotype.Service;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.model.ContactPoint;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.service.DeliveryEstimate;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.service.DeliveryTimeEstimationService;

import java.time.Duration;

@Service
public class DeliveryTimeEstimationServiceFakeImpl implements DeliveryTimeEstimationService {

    @Override
    public DeliveryEstimate estimate(ContactPoint sender, ContactPoint recipient) {
        return new DeliveryEstimate(Duration.ofHours(3), 3.1);
    }
}
