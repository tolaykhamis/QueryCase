package com.querycase.infrastructure.adapter;

import com.querycase.application.port.out.TopicCatalogPort;
import com.querycase.domain.model.TopicSummary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class JsonTopicCatalogAdapter implements TopicCatalogPort {
    private final JsonMapper jsonMapper;

    public JsonTopicCatalogAdapter(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public List<TopicSummary> findAll() {
        ClassPathResource classPathResource = new ClassPathResource("topics-v1.json");
        try (InputStream inputStream = classPathResource.getInputStream()) {
            TypeReference<List<TopicSummary>> topicListType = new TypeReference<List<TopicSummary>>() {
            };
            return jsonMapper.readValue(inputStream, topicListType);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to read topics-v1.json", e
            );
        }
    }
}
