package com.kashyapbari.tatsam.priorityassignment.web.model;

import com.kashyapbari.tatsam.priorityassignment.domain.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPriorityDto {

    @NotBlank
    private UUID userId;
    private String userName;
    @UniqueElements()
    private List<PriorityDto> priorityList;
}
