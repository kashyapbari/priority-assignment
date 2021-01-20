package com.kashyapbari.tatsam.priorityassignment.web.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriorityAssignmentError {
    private String errorCode;
    private String errorMessage;
    private List<String> errors;
}
