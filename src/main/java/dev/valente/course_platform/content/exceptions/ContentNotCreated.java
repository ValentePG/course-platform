package dev.valente.course_platform.content.exceptions;

public class ContentNotCreated extends RuntimeException{

    @Override
    public String getMessage() {
        return "Conteúdo não criado por falta de dados!";
    }

}
