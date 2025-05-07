package mk.ukim.finki.airbnb.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateTemporaryReservationException extends RuntimeException {
    public DuplicateTemporaryReservationException(Long accommodationId) {
        super("Accommodation with ID " + accommodationId + " is already in your temporary reservation list.");
    }
}
