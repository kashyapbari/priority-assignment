package com.kashyapbari.tatsam.priorityassignment.web.controller.advice;

import com.kashyapbari.tatsam.priorityassignment.exception.CustomDataIntegrityException;
import com.kashyapbari.tatsam.priorityassignment.exception.InvalidIdentifierException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@ControllerAdvice
public class PriorityControllerAdvice {
    public static final String API_GENERIC_BREAKDOWN_ERROR_CODE = "priorityassignment.generic.breakdown";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong!";
    public static final String API_MISSING_REQUEST_PARAM = "priorityassignment.missing.request.param";
    public static final String API_ARGUMENT_TYPE_MISMATCH = "priorityassignment.argument.type.mismatch";
    public static final String ARGUMENT_TYPE_MISMATCH_MESSAGE = "Argument type mismatch";
    public static final String API_CONSTRAINT_VIOLATION_ERRORCODE = "priorityassignment.Constratin.violation";
    public static final String API_CONSTRAINT_VIOLATION_MESSAGE = "Data not valid as per constraint";
    public static final String API_BINDING_ERRORCODE= "priorityassignment.Binding.violation";


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<PriorityAssignmentError> handleMissingParamException(MissingServletRequestParameterException exception) {
        log.warn("[Missing request query param] error : {}", exception.getMessage());
        return new ResponseEntity<>(PriorityAssignmentError.builder()
                .errorCode(API_MISSING_REQUEST_PARAM)
                .errorMessage(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<PriorityAssignmentError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.warn("[Request argument type mismatch] error : {}", exception.getMessage());
        return new ResponseEntity<>(PriorityAssignmentError.builder()
                .errorCode(API_ARGUMENT_TYPE_MISMATCH)
                .errorMessage(ARGUMENT_TYPE_MISMATCH_MESSAGE)
                .build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<PriorityAssignmentError> validationErrorHandler(javax.validation.ConstraintViolationException ex){
        List<String> errors = new ArrayList<>(ex.getConstraintViolations().size());
        ex.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + constraintViolation.getMessageTemplate()+" : " + constraintViolation.getMessage());
        });
        return new ResponseEntity<>(PriorityAssignmentError.builder()
                .errorMessage(API_CONSTRAINT_VIOLATION_ERRORCODE)
                .errorMessage(ex.getLocalizedMessage())
                .errors(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<PriorityAssignmentError> handleBindException(BindException ex){
        List<String> errors = new ArrayList<>(ex.getAllErrors().size());
        ex.getAllErrors().forEach(objectError -> {
            errors.add(objectError.getDefaultMessage()+ " : " +objectError.getObjectName());
        });
        return new ResponseEntity(PriorityAssignmentError.builder()
                .errorMessage(API_BINDING_ERRORCODE)
                .errorMessage(ex.getLocalizedMessage())
                .errors(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomDataIntegrityException.class )
    public ResponseEntity<PriorityAssignmentError> handleCustomDataIntegrityException(
            CustomDataIntegrityException ex) {

        return new ResponseEntity<>(PriorityAssignmentError.builder()
                .errorCode(ex.getErrorCode())
                .errorMessage(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class )
    public ResponseEntity<PriorityAssignmentError> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getConstraintName());
        errors.add(ex.getMessage());

        return new ResponseEntity<>(PriorityAssignmentError.builder()
                .errorCode(API_CONSTRAINT_VIOLATION_ERRORCODE)
                .errorMessage(API_CONSTRAINT_VIOLATION_MESSAGE)
                .errors(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class )
    public ResponseEntity<PriorityAssignmentError> handleInvalidDataAccessApiUsageException(
            InvalidDataAccessApiUsageException ex) {

        return new ResponseEntity<>(PriorityAssignmentError.builder()
                .errorCode(API_CONSTRAINT_VIOLATION_ERRORCODE)
                .errorMessage(ex.getLocalizedMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidIdentifierException.class)
    public ResponseEntity<PriorityAssignmentError> handleInvalidIdentifierException(
            InvalidIdentifierException ex){
        return new ResponseEntity<>(PriorityAssignmentError.builder()
                .errorCode(ex.getErrorCode())
                .errorMessage(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PriorityAssignmentError> defaultExceptionHandler(Exception exception) {
        log.error("[InternalServerError] ", exception);
        return new ResponseEntity<>(PriorityAssignmentError.builder()
                .errorCode(API_GENERIC_BREAKDOWN_ERROR_CODE)
                .errorMessage(INTERNAL_SERVER_ERROR_MESSAGE)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
