package io.qase.automation.pojo.projects.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProjectsResponse {

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("result")
    private Result result;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {

        @JsonProperty("total")
        private Integer total;

        @JsonProperty("filtered")
        private Integer filtered;

        @JsonProperty("count")
        private Integer count;

        @JsonProperty("entities")
        private List<Entity> entities;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Entity {

        @JsonProperty("title")
        private String title;

        @JsonProperty("code")
        private String code;

        @JsonProperty("counts")
        private Counts counts;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Counts {

        @JsonProperty("cases")
        private Integer cases;

        @JsonProperty("suites")
        private Integer suites;

        @JsonProperty("milestones")
        private Integer milestones;

        @JsonProperty("runs")
        private Runs runs;

        @JsonProperty("defects")
        private Defects defects;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Runs {

        @JsonProperty("total")
        private Integer total;

        @JsonProperty("active")
        private Integer active;

        @JsonProperty("defects")
        private Defects defects;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Defects {

        @JsonProperty("total")
        private Integer total;

        @JsonProperty("open")
        private Integer open;
    }
}

