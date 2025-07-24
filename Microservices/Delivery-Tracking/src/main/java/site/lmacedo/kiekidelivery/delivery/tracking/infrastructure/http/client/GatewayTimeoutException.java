package site.lmacedo.kiekidelivery.delivery.tracking.infrastructure.http.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class GatewayTimeoutException extends RuntimeException {
    public GatewayTimeoutException() {
        super("Gateway timeout occurred while trying to reach the service.");
    }

    public GatewayTimeoutException(Throwable cause) {
        super("Gateway timeout occurred while trying to reach the service.", cause);
    }
}
