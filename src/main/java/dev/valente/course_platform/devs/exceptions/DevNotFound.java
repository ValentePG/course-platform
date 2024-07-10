package dev.valente.course_platform.devs.exceptions;

public class DevNotFound extends RuntimeException{

    public DevNotFound() {
        super("Usuário não encontrado!");
    }

    public DevNotFound(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Usuário não encontrado!";
    }
}
