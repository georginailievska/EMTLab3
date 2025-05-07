package mk.ukim.finki.airbnb.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccommodationAlreadyRentedException extends RuntimeException {
  public AccommodationAlreadyRentedException(Long accommodationId) {
    super("Accommodation with ID " + accommodationId + " is already rented");
  }
}
