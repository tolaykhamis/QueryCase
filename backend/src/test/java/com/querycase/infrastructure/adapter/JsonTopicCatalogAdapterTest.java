package com.querycase.infrastructure.adapter;

import com.querycase.domain.model.TopicSummary;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTopicCatalogAdapterTest {

    @Test
    void givenJsonCatalogAdapter_whenReadJsonFile_thenDeserialize() {
        JsonMapper jsonMapper = JsonMapper.builder().build();
        JsonTopicCatalogAdapter jsonTopicCatalogAdapter = new JsonTopicCatalogAdapter(jsonMapper);

        List<TopicSummary> actualTopics = jsonTopicCatalogAdapter.findAll();
        assertEquals(3, actualTopics.size());
        assertEquals("select", actualTopics.get(0).slug());
        assertEquals("where", actualTopics.get(1).slug());
        assertEquals("join", actualTopics.get(2).slug());
    }
}
