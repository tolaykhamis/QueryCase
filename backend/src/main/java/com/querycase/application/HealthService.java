package com.querycase.application;

import org.springframework.stereotype.Service;

@Service
public class HealthService {
    public String getStatus() {
        return "QueryCase backend is running!";
    }
}
