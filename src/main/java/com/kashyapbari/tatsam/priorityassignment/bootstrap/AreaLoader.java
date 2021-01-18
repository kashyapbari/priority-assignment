package com.kashyapbari.tatsam.priorityassignment.bootstrap;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import com.kashyapbari.tatsam.priorityassignment.repository.AreaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AreaLoader implements CommandLineRunner {

    private final AreaRepository areaRepository;

    public AreaLoader(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadAreas();
    }

    private void loadAreas() {
        if (areaRepository.count() == 0){
            areaRepository.save(Area.builder()
                    .id(UUID.fromString("b869cad7-8cd2-4f88-bb8e-9c7b1feb6c47"))
                    .name("Connection")
                    .build());
            areaRepository.save(Area.builder()
                    .name("Relationships")
                    .build());
            areaRepository.save(Area.builder()
                    .name("Career")
                    .build());
            areaRepository.save(Area.builder()
                    .name("Wealth")
                    .build());
        }
    }

}
