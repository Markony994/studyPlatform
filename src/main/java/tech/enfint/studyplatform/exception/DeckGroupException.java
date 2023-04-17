package tech.enfint.studyplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DeckGroupException extends Exception {

    public DeckGroupException(String message) {
        super(message);
    }
}
