package com.querycase.api;


import com.querycase.application.HealthService;
import com.querycase.application.topic.ListTopicsService;
import com.querycase.domain.topic.TopicSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TopicController.class)
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ListTopicsService listTopicsService;




    @Test
    void givenTopics_whenGetTopicsRequested_thenReturnTopics() throws Exception {
        List<TopicSummary> topics = List.of(
                new TopicSummary(
                        "select-basics",
                        "SQL SELECT Basics",
                        "Learn SELECT statements."
                )
        );
        when(listTopicsService.execute()).thenReturn(topics);
        mockMvc.perform(get("/api/v1/topics"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        org.springframework.http.MediaType.APPLICATION_JSON
                ))
                .andExpect(jsonPath("$[0].slug").value("select-basics"))
                .andExpect(jsonPath("$[0].title").value("SQL SELECT Basics"))
                .andExpect(jsonPath("$[0].description")
                        .value("Learn SELECT statements."));
        verify(listTopicsService).execute();

    }


}
