package com.querycase.application.topic;

import com.querycase.application.port.ContentCatalog;
import com.querycase.domain.topic.TopicSummary;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ListTopicsService {

    private final ContentCatalog contentCatalog;

    public ListTopicsService(ContentCatalog contentCatalog) {
        this.contentCatalog = contentCatalog;
    }

    public List<TopicSummary> execute() {
        return contentCatalog.getAllTopics();
    }
}
