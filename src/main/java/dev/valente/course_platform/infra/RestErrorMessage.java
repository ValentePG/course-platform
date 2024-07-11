package dev.valente.course_platform.infra;

import org.springframework.http.HttpStatus;

// Incluir mais detalhes

public class RestErrorMessage {
    private HttpStatus status;
    private String message;
    private final int statusCode;


    public RestErrorMessage(HttpStatus status, String message){
        this.message = message;
        this.status = status;
        this.statusCode = status.value();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
