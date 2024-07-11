package dev.valente.course_platform.infra;

import dev.valente.course_platform.content.exceptions.ContentAlreadyExists;
import dev.valente.course_platform.content.exceptions.ContentNotCreated;
import dev.valente.course_platform.content.exceptions.ContentNotFound;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.DevNotCreated;
import dev.valente.course_platform.devs.exceptions.DevNotFound;
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

    @ExceptionHandler(DevNotCreated.class)
    private ResponseEntity<RestErrorMessage> devNotCreatedHandler(DevNotCreated exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY,exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(threatResponse);
    }


    @ExceptionHandler(DevNotFound.class)
    private ResponseEntity<RestErrorMessage> devNotFoundHandler(DevNotFound exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }


    @ExceptionHandler(ContentNotCreated.class)
    private ResponseEntity<RestErrorMessage> contentNotCreated(ContentNotCreated exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY,exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(threatResponse);
    }


    @ExceptionHandler(ContentNotFound.class)
    private ResponseEntity<RestErrorMessage> contentNotFound(ContentNotFound exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }


    @ExceptionHandler(ContentAlreadyExists.class)
    private ResponseEntity<RestErrorMessage> contentAlreadyExists(ContentAlreadyExists exception){
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

}
