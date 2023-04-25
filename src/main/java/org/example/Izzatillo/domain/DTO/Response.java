package org.example.Izzatillo.domain.DTO;

public class Response<T> {
    private final String message;
    private T data;
    private int status;

    public Response(String message, T data, int status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public Response(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return message;
    }
}
