package com.kashyapbari.tatsam.priorityassignment.web.controller;

import com.kashyapbari.tatsam.priorityassignment.service.AreaService;
import com.kashyapbari.tatsam.priorityassignment.web.model.AreaDto;
import com.kashyapbari.tatsam.priorityassignment.web.model.PageResponse;
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
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AreaController.class)
class AreaControllerTest {

    @MockBean
    AreaService areaService;

    @Autowired
    MockMvc mockMvc;

    AreaDto areaDto;

    @BeforeEach
    void setUp() {
        areaDto = AreaDto.builder()
                .id(UUID.randomUUID())
                .name("Area1")
                .build();
    }

    @Test
    void getAreas() throws Exception {
        int page = 0;
        int count = 3;
        List<AreaDto> areaDtoList = new ArrayList<>();
        areaDtoList.add(areaDto);

        given(areaService.getAllArea(PageRequest.of(page, count)))
                .willReturn(
                        new PageResponse<>(page,
                                count,
                                page,
                                count,
                                areaDtoList)
                );
        mockMvc.perform(get("/api/v1/area/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("contents.[0].id", is(areaDto.getId().toString())));
    }
}