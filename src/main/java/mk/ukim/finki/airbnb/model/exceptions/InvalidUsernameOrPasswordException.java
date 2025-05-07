package mk.ukim.finki.airbnb.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidUsernameOrPasswordException extends RuntimeException {

    public InvalidUsernameOrPasswordException() {
        super("Invalid username or password.");
    }
}
