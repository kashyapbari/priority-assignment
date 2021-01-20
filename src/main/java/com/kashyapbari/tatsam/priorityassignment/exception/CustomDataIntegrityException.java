package com.kashyapbari.tatsam.priorityassignment.exception;

import lombok.Getter;

public class CustomDataIntegrityException extends RuntimeException{
    @Getter
    protected String errorCode;

    public CustomDataIntegrityException(String message) {
        super(message);
        this.errorCode = "priorityassigment.api.dataintegrity";
    }


}
