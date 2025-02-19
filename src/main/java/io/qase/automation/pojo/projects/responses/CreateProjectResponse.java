package io.qase.automation.pojo.projects.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectResponse {
    @JsonProperty("status")
    private boolean status;
    @JsonProperty("result")
    private Result result;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result{
        @JsonProperty("code")
        private String code;
    }
}
