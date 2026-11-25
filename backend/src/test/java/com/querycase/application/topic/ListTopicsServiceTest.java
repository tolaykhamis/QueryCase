package com.querycase.application.topic;

import com.querycase.application.port.ContentCatalog;
import com.querycase.domain.topic.TopicSummary;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTopicsServiceTest {



    @Test
    public void givenTopicsServiceTest_whenExecute_thenReturnGetAllTopics(){

        ContentCatalog fakeCatalog = new FakeContentCatalog();
        ListTopicsService listTopicsService = new ListTopicsService(fakeCatalog);

        List<TopicSummary> actual = listTopicsService.execute();
        List<TopicSummary> expected = List.of(
                new TopicSummary(
                        "select-basics",
                        "SQL SELECT Basics",
                        "Learn SELECT statements."
                )
        );

        assertEquals(actual, expected);
    }



    private static class FakeContentCatalog implements ContentCatalog {

        @Override
        public List<TopicSummary> getAllTopics() {
            return List.of(
                    new TopicSummary(
                            "select-basics",
                            "SQL SELECT Basics",
                            "Learn SELECT statements."
                    )
            );
        }
    }
}



