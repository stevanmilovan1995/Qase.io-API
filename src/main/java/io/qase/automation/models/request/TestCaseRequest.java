package io.qase.automation.models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCaseRequest {
    private String title;
    private String description;
    private int priority;
    private int severity;

    public static TestCaseRequest createBasicTestCase(String title) {
        return builder()
                .title(title)
                .description("Basic test case created via API")
                .priority(2)
                .severity(2)
                .build();
    }

}