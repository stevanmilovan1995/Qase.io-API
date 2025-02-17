package io.qase.automation.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class ProjectResponse {
    private boolean status;
    private Result result;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private String title;
        private String code;
        private String description;
        private String access;
        private Counts counts;
    }

    @Data
    public static class Counts {
        private int cases;
        private int suites;
        private int milestones;
        private Runs runs;
        private Defects defects;
    }

    @Data
    public static class Runs {
        private int total;
        private int active;
    }

    @Data
    public static class Defects {
        private int total;
        private int open;
    }
}
