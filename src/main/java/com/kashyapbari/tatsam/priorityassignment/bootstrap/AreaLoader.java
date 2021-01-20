package com.kashyapbari.tatsam.priorityassignment.bootstrap;

import com.kashyapbari.tatsam.priorityassignment.domain.Area;
import com.kashyapbari.tatsam.priorityassignment.repository.AreaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
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
            log.info("Initial Loading areas");
            areaRepository.save(Area.builder()
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
