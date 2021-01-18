package com.kashyapbari.tatsam.priorityassignment.bootstrap;

import com.kashyapbari.tatsam.priorityassignment.domain.Priority;
import com.kashyapbari.tatsam.priorityassignment.repository.PriorityRepository;
import org.springframework.boot.CommandLineRunner;

public class PriorityLoader implements CommandLineRunner {
    private final PriorityRepository priorityRepository;

    public PriorityLoader(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPriorities();
    }

    private void loadPriorities() {
        if (priorityRepository.count() == 0){
        }
    }
}
