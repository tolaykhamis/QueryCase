package com.querycase.infrastructure.adapter;
import com.querycase.application.port.ContentCatalog;
import com.querycase.domain.topic.TopicSummary;


import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JsonContentCatalog implements ContentCatalog {

    private final ObjectMapper objectMapper;
    private final List<TopicSummary> topics;

    public JsonContentCatalog(
            ObjectMapper objectMapper,
            @Value("classpath:content/topics.json") Resource resource
    ) {
        this.objectMapper = objectMapper;
        this.topics = loadTopics(resource);
    }

    private List<TopicSummary> loadTopics(Resource resource) {



        try (InputStream inputStream = resource.getInputStream()) {

            List<TopicSummary> loadedTopics =
                    objectMapper.readValue(
                            inputStream,
                            new TypeReference<List<TopicSummary>>() {}
                    );

            validateTopics(loadedTopics);

            return List.copyOf(loadedTopics);

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to load topics from topics.json", e
            );
        }
    }

    private void validateTopics(List<TopicSummary> topics) {

        if (topics == null || topics.isEmpty()) {
            throw new IllegalStateException(
                    "Topics catalog cannot be empty"
            );
        }

        Set<String> slugs = new HashSet<>();

        for (TopicSummary topic : topics) {

            if (topic == null
                    || topic.slug() == null
                    || topic.slug().isBlank()
                    || topic.title() == null
                    || topic.title().isBlank()
                    || topic.description() == null
                    || topic.description().isBlank()) {

                throw new IllegalStateException(
                        "Topic contains missing required fields"
                );
            }

            if (!slugs.add(topic.slug())) {
                throw new IllegalStateException(
                        "Duplicate topic slug: " + topic.slug()
                );
            }
        }
    }

    @Override
    public List<TopicSummary> getAllTopics() {
        return topics;
    }
}