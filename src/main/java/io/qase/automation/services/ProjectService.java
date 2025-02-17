package io.qase.automation.services;

import io.qase.automation.api.client.RestClient;
import io.qase.automation.models.request.ProjectRequest;
import io.qase.automation.models.response.ProjectListResponse;
import io.qase.automation.models.response.ProjectResponse;
import io.restassured.response.Response;

public class ProjectService {
    private final RestClient restClient;

    public ProjectService() {
        this.restClient = new RestClient();
    }

    public ProjectResponse createProject(ProjectRequest request) {
        Response response = restClient.post("/project", request);
        return response.as(ProjectResponse.class);
    }

    public ProjectResponse getProject(String code) {
        Response response = restClient.get("/project/"  + code);
        return response.as(ProjectResponse.class);
    }

    public ProjectResponse updateProject(String code, ProjectRequest request) {
        Response response = restClient.put("/project/" + code, request);
        return response.as(ProjectResponse.class);
    }

    public ProjectResponse deleteProject(String code) {
        Response response = restClient.delete("/project/" +code);
        return response.as(ProjectResponse.class);
    }

    public ProjectListResponse getAllProjects() {
        Response response = restClient.get("/project");
        return response.as(ProjectListResponse.class);
    }
}
