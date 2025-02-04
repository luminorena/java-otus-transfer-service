package ru.otus.java.pro.spring.app.exceptions_handling;


public class BusinessLogicFieldError {
    private String field;
    private String message;

    public BusinessLogicFieldError() {
    }

    public BusinessLogicFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
