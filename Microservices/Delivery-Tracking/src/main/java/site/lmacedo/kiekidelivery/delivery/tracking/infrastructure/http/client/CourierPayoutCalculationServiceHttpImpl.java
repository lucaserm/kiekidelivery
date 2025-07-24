package site.lmacedo.kiekidelivery.delivery.tracking.infrastructure.http.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.service.CourierPayoutCalculationService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {

    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {
        CourierPayoutCalculationInput input = new CourierPayoutCalculationInput(distanceInKm);
        CourierPayoutResultModel result = courierAPIClient.payoutCalculation(input);
        return result.payoutFee();
    }
}
