package ru.otus.java.pro.spring.app.dtos;

import java.util.Objects;

public final class ExecuteTransfersDtoRs {
    private String id;
    private String sourceAccount;
    private String targetAccount;
    private String message;

    public ExecuteTransfersDtoRs(String id, String sourceAccount,
                                 String targetAccount, String message) {
        this.id = id;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.message = message;
    }


    public String id() {
        return id;
    }

    public String sourceAccount() {
        return sourceAccount;
    }

    public String targetAccount() {
        return targetAccount;
    }

    public String message() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ExecuteTransfersDtoRs) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.sourceAccount, that.sourceAccount) &&
                Objects.equals(this.targetAccount, that.targetAccount) &&
                Objects.equals(this.message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceAccount, targetAccount, message);
    }

    @Override
    public String toString() {
        return "ExecuteTransfersDtoRs[" +
                "id=" + id + ", " +
                "sourceAccount=" + sourceAccount + ", " +
                "targetAccount=" + targetAccount + ", " +
                "message=" + message + ']';
    }


}

