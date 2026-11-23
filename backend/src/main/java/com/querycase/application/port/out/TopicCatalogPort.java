package com.querycase.application.port.out;

import com.querycase.domain.model.TopicSummary;

import java.util.List;

public interface TopicCatalogPort {
    List<TopicSummary> findAll();
}
