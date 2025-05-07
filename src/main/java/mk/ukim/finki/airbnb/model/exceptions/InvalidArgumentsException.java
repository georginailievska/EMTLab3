package mk.ukim.finki.airbnb.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentsException extends RuntimeException {

    public InvalidArgumentsException() {
        super("Invalid arguments provided.");
    }
}
