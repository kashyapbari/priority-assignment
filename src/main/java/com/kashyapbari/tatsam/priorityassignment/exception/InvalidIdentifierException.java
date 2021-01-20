package com.kashyapbari.tatsam.priorityassignment.exception;

import lombok.Getter;

public class InvalidIdentifierException extends RuntimeException{
    @Getter
    protected String errorCode;

    public InvalidIdentifierException(String message) {
        super(message);
        this.errorCode = "priorityassigment.api.invalid.identifier";
    }
}
