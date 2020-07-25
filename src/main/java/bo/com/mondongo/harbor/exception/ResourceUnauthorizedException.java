package bo.com.mondongo.harbor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class ResourceUnauthorizedException extends RuntimeException {
    public ResourceUnauthorizedException(String message) {
        super(message);
    }
}