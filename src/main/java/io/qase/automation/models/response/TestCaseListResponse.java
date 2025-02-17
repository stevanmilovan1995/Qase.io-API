package io.qase.automation.models.response;

import lombok.Data;
import java.util.List;

@Data
public class TestCaseListResponse {
    private boolean status;
    private Result result;

    @Data
    public static class Result {
        private int total;
        private int filtered;
        private int count;
        private List<TestCaseResponse.Result> entities;
    }
}