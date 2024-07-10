package dev.valente.course_platform.content.exceptions;

public class ContentNotFound extends RuntimeException{
    @Override
    public String getMessage() {
        return "Conteúdo não encontrado!";
    }
}
