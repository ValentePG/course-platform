package dev.valente.course_platform.devs.exceptions;

public class UserNotFound extends RuntimeException{

    public UserNotFound() {
        super("Usuário não encontrado!");
    }

    public UserNotFound(String message) {
        super(message);
    }
}
