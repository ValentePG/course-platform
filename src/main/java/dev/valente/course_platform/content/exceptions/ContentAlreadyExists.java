package dev.valente.course_platform.content.exceptions;

public class ContentAlreadyExists extends RuntimeException{

    @Override
    public String getMessage() {
        return "Conteúdo já existe!";
    }

}
