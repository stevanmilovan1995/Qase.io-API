package io.qase.automation.models.response;

import lombok.Data;

import java.util.List;

@Data
public class ProjectListResponse {
    private boolean status;
    private Result result;

    @Data
    public static class Result {
        private int total;
        private int filtered;
        private int count;
        private List<ProjectResponse.Result> entities;

        public boolean hasProjects() {
            return entities != null && !entities.isEmpty();
        }

        public int getActiveProjectsCount() {
            return (int) entities.stream()
                    .filter(project -> "active".equals(project.getAccess())).count();
        }
    }


}
