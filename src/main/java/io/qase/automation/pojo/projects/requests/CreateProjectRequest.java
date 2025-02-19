package io.qase.automation.pojo.projects.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("access_type")
    private String accessType;

    @JsonProperty("access")
    private Object access;

    @JsonProperty("group")
    private Object group;

    public static CreateProjectRequest createProject(String title, String projectCode) {
        return CreateProjectRequest.builder()
                .title(title)
                .code(projectCode)
                .description("Test automation project description")
                .access("all")
                .build();
    }
}