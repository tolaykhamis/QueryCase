package com.querycase.infrastructure.adapter;

import com.querycase.domain.topic.TopicSummary;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonContentCatalogTest {

    @Test
    void givenValidJson_whenGetAllTopics_thenReturnTopics() {

        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();

        Resource testResource =
                new ClassPathResource("content/topics.json");

        JsonContentCatalog catalog =
                new JsonContentCatalog(objectMapper, testResource);

        // Act
        List<TopicSummary> actual = catalog.getAllTopics();

        // Assert
        List<TopicSummary> expected = List.of(
                new TopicSummary(
                        "select-basics",
                        "SQL SELECT Basics",
                        "Learn SELECT statements."
                )
        );

        assertEquals(expected, actual);
    }
}
