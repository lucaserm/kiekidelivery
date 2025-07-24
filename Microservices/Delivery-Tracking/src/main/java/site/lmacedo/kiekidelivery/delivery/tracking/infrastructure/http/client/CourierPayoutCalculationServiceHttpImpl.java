package site.lmacedo.kiekidelivery.delivery.tracking.infrastructure.http.client;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import site.lmacedo.kiekidelivery.delivery.tracking.domain.service.CourierPayoutCalculationService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {

    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {
        try {
            CourierPayoutCalculationInput input = new CourierPayoutCalculationInput(distanceInKm);
            CourierPayoutResultModel result = courierAPIClient.payoutCalculation(input);
            return result.payoutFee();
        } catch (ResourceAccessException e) {
            throw new GatewayTimeoutException(e);
        } catch (HttpServerErrorException | IllegalArgumentException | CallNotPermittedException e) {
            throw new BadGatewayException(e);
        }
    }
}
