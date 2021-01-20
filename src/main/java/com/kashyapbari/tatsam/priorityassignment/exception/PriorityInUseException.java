package com.kashyapbari.tatsam.priorityassignment.exception;

import lombok.Getter;

public class PriorityInUseException extends CustomDataIntegrityException {

    public PriorityInUseException(String message) {
        super(message);
        errorCode = getErrorCode()+".priority.in.use";
    }
}
