package com.kashyapbari.tatsam.priorityassignment.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class PageResponse<T> {
    private int page;
    private int count;
    private int totalPages;
    private long totalCount;
    private List<T> contents;
}
