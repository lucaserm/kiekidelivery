package site.lmacedo.kiekidelivery.delivery.tracking.infrastructure.http.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends RuntimeException {
    public BadGatewayException() {
        super("Bad gateway error occurred while trying to reach the service.");
    }
    public BadGatewayException(Throwable cause) {
        super("Bad gateway error occurred while trying to reach the service.", cause);
    }
}
