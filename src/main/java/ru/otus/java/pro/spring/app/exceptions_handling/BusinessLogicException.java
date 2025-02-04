package ru.otus.java.pro.spring.app.exceptions_handling;


import java.util.List;

public class BusinessLogicException extends RuntimeException {
    private String code;
    private List<BusinessLogicFieldError> errors;

    public BusinessLogicException(String sourceAccount, String targetAccount) {
    }

    public BusinessLogicException(String message, String code, List<BusinessLogicFieldError> errors) {
        super(message);
        this.code = code;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public List<BusinessLogicFieldError> getErrors() {
        return errors;
    }

}
