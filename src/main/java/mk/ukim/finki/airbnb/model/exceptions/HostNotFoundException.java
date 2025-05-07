package mk.ukim.finki.airbnb.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HostNotFoundException extends RuntimeException {
    public HostNotFoundException(Long id) {
        super("Host with ID " + id + " not found");
    }
}
