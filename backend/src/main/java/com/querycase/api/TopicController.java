package com.querycase.api;

import com.querycase.application.service.ListTopicsService;
import com.querycase.domain.model.TopicSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController {
    private final ListTopicsService listTopicsService;

    public TopicController(ListTopicsService listTopicsService) {
        this.listTopicsService = listTopicsService;
    }


    @GetMapping("/api/v1/topics")
    public List<TopicSummary> getTopics() {
        return listTopicsService.execute();
    }

}
