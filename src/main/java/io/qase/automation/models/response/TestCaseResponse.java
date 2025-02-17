package io.qase.automation.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class TestCaseResponse {
    private boolean status;
    private Result result;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private int id;
        private String title;
        private String description;
        private int priority;
        private int severity;
        private String preconditions;
        private String postconditions;
        private String status;
        private String type;
        private String layer;
        private boolean isFlaky;
        private String behavior;
        private String automation;
        private String created;
        private String updated;
    }
}