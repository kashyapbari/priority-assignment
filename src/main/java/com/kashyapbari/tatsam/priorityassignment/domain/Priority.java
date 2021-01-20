package com.kashyapbari.tatsam.priorityassignment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name= "UK_PR_AREA_LEVEL",columnNames = {"area_id","priority_level"} ),
                @UniqueConstraint(name= "UK_PR_AREA_USER",columnNames = {"area_id", "user_id"}),
                @UniqueConstraint(name = "UK_PR_USER_LEVEL", columnNames = {"user_id", "priority_level"})}
)
public class Priority {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;
    private Integer priority_level;
    private Integer satisfaction;

}
