package com.kashyapbari.tatsam.priorityassignment.repository;

import com.kashyapbari.tatsam.priorityassignment.domain.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriorityRepository extends JpaRepository<Priority, UUID> {
}
