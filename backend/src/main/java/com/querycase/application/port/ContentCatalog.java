package com.querycase.application.port;

import com.querycase.domain.topic.TopicSummary;

import java.util.List;

public interface ContentCatalog {
    public List<TopicSummary> getAllTopics();
}
