package com.querycase.api;

import com.querycase.application.service.ListTopicsService;
import com.querycase.domain.model.TopicSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ListTopicsService listTopicsService;

    @Test
    void givenListTopicService_whenGetCall_thenReturnTopics() throws Exception {
        List<TopicSummary> expectedTopics = List.of(
                new TopicSummary("select", "SELECT", "Learn how to retrieve data"),
                new TopicSummary("where", "WHERE", "Learn how to filter records"),
                new TopicSummary("join", "JOIN", "Learn how to combine tables"));
        when(listTopicsService.execute()).thenReturn(expectedTopics);
        mockMvc.perform(get("/api/v1/topics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].slug").value("select"))
                .andExpect(jsonPath("$[1].slug").value("where"))
                .andExpect(jsonPath("$[2].slug").value("join"));
        verify(listTopicsService).execute();
    }
}
