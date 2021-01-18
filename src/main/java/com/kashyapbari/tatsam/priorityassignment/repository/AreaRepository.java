package com.kashyapbari.tatsam.priorityassignment.repository;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AreaRepository extends JpaRepository<Area, UUID> {
}
