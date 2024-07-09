package dev.valente.course_platform.infra;

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
    private ResponseEntity<String> userNotCreatedHandler(UserNotCreated exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O usuário não foi criado por falta de dados!");
    }

    @ExceptionHandler(UserNotFound.class)
    private ResponseEntity<String> userNotFoundHandler(UserNotFound exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado!");
    }

}
