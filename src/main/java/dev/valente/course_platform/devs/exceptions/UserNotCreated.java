package dev.valente.course_platform.devs.exceptions;

public class UserNotCreated extends RuntimeException {

    public UserNotCreated() {
        super("Usuário não criado por falta de dados!");
    }

    public UserNotCreated(String message) {
        super(message);
    }
}
