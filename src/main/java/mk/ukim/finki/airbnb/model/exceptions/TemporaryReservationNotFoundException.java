package mk.ukim.finki.airbnb.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TemporaryReservationNotFoundException extends RuntimeException {
    public TemporaryReservationNotFoundException(Long id) {
        super("Temporary reservation with ID " + id + " not found");
    }
}
