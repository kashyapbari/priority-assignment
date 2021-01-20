package com.kashyapbari.tatsam.priorityassignment.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriorityDto {
    private UUID id;
    @NotNull
    private UUID areaId;
    private Integer priority_level;
    @Max(5)
    @Min(1)
    private Integer satisfaction;
}
