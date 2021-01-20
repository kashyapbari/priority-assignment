package com.kashyapbari.tatsam.priorityassignment.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kashyapbari.tatsam.priorityassignment.service.UserPriorityService;
import com.kashyapbari.tatsam.priorityassignment.web.model.PageResponse;
import com.kashyapbari.tatsam.priorityassignment.web.model.PriorityDto;
import com.kashyapbari.tatsam.priorityassignment.web.model.UserPriorityDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserPriorityController.class)
class UserPriorityControllerTest {

    @MockBean
    UserPriorityService userPriorityService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    UserPriorityDto userPriorityDto;

    @BeforeEach
    void setUp() {
        List<PriorityDto> priorityDtoList = new ArrayList<>();
        priorityDtoList.add(PriorityDto.builder()
                .id(UUID.randomUUID())
                .areaId(UUID.randomUUID())
                .priority_level(1)
                .satisfaction(1)
                .build());
        userPriorityDto = UserPriorityDto.builder()
                .userId(UUID.randomUUID())
                .userName("kashyap")
                .priorityList(priorityDtoList)
                .build();
    }

    @Test
    void getAllUserPriorities() throws Exception {
        int page = 0;
        int count = 3;

        given(userPriorityService.getAllUserPriorities(PageRequest.of(page, count)))
                .willReturn(new PageResponse<>(page,
                        count,
                        page,
                        count,
                        Arrays.asList(userPriorityDto)));

        mockMvc.perform(get("/api/v1/userpriority/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("contents.[0].userId", is(userPriorityDto.getUserId().toString())));
    }

    @Test
    void savePriority() throws Exception{
        given(userPriorityService.saveUserPriority(any())).willReturn(userPriorityDto);

        mockMvc.perform(post("/api/v1/userpriority/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userPriorityDto)))
                .andExpect(status().isCreated());
    }
}