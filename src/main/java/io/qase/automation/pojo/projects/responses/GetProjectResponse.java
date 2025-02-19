package io.qase.automation.pojo.projects.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProjectResponse {
    @JsonProperty("status")
    public Boolean status;
    @JsonProperty("result")
    public Result result;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {

        @JsonProperty("title")
        public String title;
        @JsonProperty("code")
        public String code;
        @JsonProperty("counts")
        public Counts counts;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Counts {
        @JsonProperty("cases")
        public Integer cases;
        @JsonProperty("suites")
        public Integer suites;
        @JsonProperty("milestones")
        public Integer milestones;
        @JsonProperty("runs")
        public Runs runs;
        @JsonProperty("defects")
        public Defects defects;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Runs {

        @JsonProperty("total")
        public Integer total;
        @JsonProperty("active")
        public Integer active;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Defects {

        @JsonProperty("total")
        public Integer total;
        @JsonProperty("open")
        public Integer open;

    }
}
