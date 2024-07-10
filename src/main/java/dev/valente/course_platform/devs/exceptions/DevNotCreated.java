package dev.valente.course_platform.devs.exceptions;

public class DevNotCreated extends RuntimeException {

    public DevNotCreated() {
        super("Usuário não criado por falta de dados!");
    }

    public DevNotCreated(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Usuário não criado por falta de dados!";
    }
}
