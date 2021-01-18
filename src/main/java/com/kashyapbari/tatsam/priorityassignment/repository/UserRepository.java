package com.kashyapbari.tatsam.priorityassignment.repository;

import com.kashyapbari.tatsam.priorityassignment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
