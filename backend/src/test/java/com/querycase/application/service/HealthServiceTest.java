package com.querycase.application.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthServiceTest {

    @Test
    void givenHealthService_whenGetStatus_thenReturnRunningMessage() {
        HealthService healthService = new HealthService();

        String actual = healthService.getStatus();

        String expected = "QueryCase backend is running!";

        assertEquals(expected, actual);
    }
}
