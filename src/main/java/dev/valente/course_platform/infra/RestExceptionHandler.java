package dev.valente.course_platform.infra;

import dev.valente.course_platform.content.exceptions.ContentNotCreated;
import dev.valente.course_platform.content.exceptions.ContentNotFound;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.UserNotCreated;
import dev.valente.course_platform.devs.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // Explorar mais!!!!
    @ExceptionHandler(UserNameAlreadyExists.class)
    private ResponseEntity<RestErrorMessage> userNameAlreadyExistsHandler(UserNameAlreadyExists exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(UserNotCreated.class)
    private ResponseEntity<RestErrorMessage> userNotCreatedHandler(UserNotCreated exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(UserNotFound.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFound exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(ContentNotCreated.class)
    private ResponseEntity<RestErrorMessage> contentNotCreated(ContentNotCreated exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(ContentNotFound.class)
    private ResponseEntity<RestErrorMessage> contentNotFound(ContentNotFound exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

}
