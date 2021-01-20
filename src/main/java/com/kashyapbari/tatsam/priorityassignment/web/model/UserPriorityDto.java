package com.kashyapbari.tatsam.priorityassignment.web.model;

import com.kashyapbari.tatsam.priorityassignment.domain.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPriorityDto {

    @NotNull
    private UUID userId;
    private String userName;
    private List<PriorityDto> priorityList;
}
