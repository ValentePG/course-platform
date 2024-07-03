package dev.valente.course_platform.devs.exceptions;

public class UserNameAlreadyExists extends RuntimeException{

    public UserNameAlreadyExists() {
        super("Nome de usuário já existe!");
    }

    public UserNameAlreadyExists(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Nome de usuário já existe!";
    }

}
