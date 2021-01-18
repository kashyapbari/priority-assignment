package com.kashyapbari.tatsam.priorityassignment.bootstrap;

import com.kashyapbari.tatsam.priorityassignment.domain.User;
import com.kashyapbari.tatsam.priorityassignment.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class UserLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserObjects();
    }

    private void loadUserObjects() {
        if( userRepository.count() == 0){
            userRepository.save(User.builder()
                    .id(UUID.fromString("874a644b-03bd-46a8-a9a3-bf51eddc337b"))
                    .userName("kashyap")
                    .build());
            userRepository.save(User.builder()
                    .userName("tanushree")
                    .build());
            userRepository.save(User.builder()
                    .userName("nikhil")
                    .build());
        }
    }
}
