package com.querycase.application.service;

import com.querycase.application.port.out.TopicCatalogPort;
import com.querycase.domain.model.TopicSummary;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTopicsServiceTest {

    @Test
    void givenListTopicService_whenExecute_thenReturnsTopics() {
        TopicCatalogPort topicCatalogPort = mock(TopicCatalogPort.class);
        TopicSummary topic = new TopicSummary(
                "select",
                "SELECT",
                "Learn how to retrieve data"
        );
        List<TopicSummary> expectedTopics = List.of(topic);
        when(topicCatalogPort.findAll()).thenReturn(expectedTopics);

        ListTopicsService listTopicsService = new ListTopicsService(topicCatalogPort);
        List<TopicSummary> actualTopics = listTopicsService.execute();

        assertEquals(expectedTopics, actualTopics);
    }
}
