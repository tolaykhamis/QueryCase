package com.querycase.infrastructure.config;

import com.querycase.application.port.out.TopicCatalogPort;
import com.querycase.application.service.HealthService;
import com.querycase.application.service.ListTopicsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public HealthService healthService() {
        return new HealthService();
    }

    @Bean
    public ListTopicsService listTopicsService(TopicCatalogPort topicCatalogPort) {
        return new ListTopicsService(topicCatalogPort);
    }
}
