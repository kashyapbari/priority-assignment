package com.kashyapbari.tatsam.priorityassignment.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriorityDto {
    private UUID id;
    private UUID areaId;
    private Integer priority_level;
    private Integer satisfaction;
}
