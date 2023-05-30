package tech.enfint.studyplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FlashCardException extends Exception {

    public FlashCardException(String message) {
        super(message);
    }


}//public class FlashCardException extends Exception
