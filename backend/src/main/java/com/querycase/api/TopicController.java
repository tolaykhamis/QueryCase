package com.querycase.api;
import com.querycase.application.topic.ListTopicsService;
import com.querycase.domain.topic.TopicSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TopicController {

    private final ListTopicsService listTopicsService;

    public TopicController(ListTopicsService listTopicsService) {
        this.listTopicsService = listTopicsService;
    }

    @GetMapping("/topics")
    public List<TopicResponse> topics(){
          return listTopicsService.execute()
                  .stream()
                  .map(this::toResponse)
                  .toList();
    }
    private TopicResponse toResponse(TopicSummary topic) {
        return new TopicResponse(
                topic.slug(),
                topic.title(),
                topic.description()
        );
    }
}
