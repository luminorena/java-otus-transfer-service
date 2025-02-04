package ru.otus.java.pro.spring.app.exceptions_handling;

import java.time.LocalDateTime;
import java.util.List;

public class BusinessLogicErrorDto {
    private String code;
    private String message;
    private List<BusinessLogicFieldErrorDto> errors;
    private LocalDateTime dateTime;

    public BusinessLogicErrorDto(String code, String message, List<BusinessLogicFieldErrorDto> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.dateTime = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BusinessLogicFieldErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<BusinessLogicFieldErrorDto> errors) {
        this.errors = errors;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
