package io.qase.automation.models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectRequest {
    private String title;
    private String code;
    private String description;
    private String access;
    private String group;

    public static ProjectRequest createDefaultProject(String title, String code) {
        return ProjectRequest.builder()
                .title(title)
                .code(code)
                .description("Test project created with API")
                .access("all")
                .build();
    }
}
