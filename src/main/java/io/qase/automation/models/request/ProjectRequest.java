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
        return builder()
                .title(title)
                .code(code)
                .description("Test project created with API")
                .access("all")
                .build();
    }

    public static ProjectRequest createPrivateProject(String title, String code) {
        return builder()
                .title(title)
                .code(code)
                .description("Private test project")
                .access("private")
                .build();
    }

    public static ProjectRequest createGroupProject(String title, String code, String group) {
        return builder()
                .title(title)
                .code(code)
                .description("Group test project")
                .access("group")
                .group(group)
                .build();
    }
}
