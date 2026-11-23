package com.querycase.application.service;

import com.querycase.application.port.out.TopicCatalogPort;
import com.querycase.domain.model.TopicSummary;

import java.util.List;


public class ListTopicsService {
    private final TopicCatalogPort topicCatalogPort;

    public ListTopicsService(TopicCatalogPort topicCatalogPort) {
        this.topicCatalogPort = topicCatalogPort;
    }

    public List<TopicSummary> execute() {
        return topicCatalogPort.findAll();
    }
}
