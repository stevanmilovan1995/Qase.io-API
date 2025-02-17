package io.qase.automation.services;

import io.qase.automation.api.client.RestClient;
import io.qase.automation.models.request.ProjectRequest;
import io.qase.automation.models.response.ProjectListResponse;
import io.qase.automation.models.response.ProjectResponse;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectService {
    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
    private static final String BASE_PATH = "/project";
    private final RestClient restClient;

    public ProjectService(RestClient restClient) {
        this.restClient = restClient;
    }

    public ProjectResponse createProject(ProjectRequest request) {
        log.info("Creating new project with code: {}", request.getCode());
        Response response = restClient.post(BASE_PATH, request);
        return response.as(ProjectResponse.class);
    }

    public ProjectResponse getProject(String code) {
        log.info("Fetching project details for code: {}", code);
        String path = String.format("%s/%s", BASE_PATH, code);
        Response response = restClient.get(path);
        return response.as(ProjectResponse.class);
    }

    public ProjectResponse updateProject(String code, ProjectRequest request) {
        log.info("Updating project with code: {}", code);
        String path = String.format("%s/%s", BASE_PATH, code);
        Response response = restClient.put(path, request);
        return response.as(ProjectResponse.class);
    }

    public ProjectResponse deleteProject(String code) {
        log.info("Deleting project with code: {}", code);
        String path = String.format("%s/%s", BASE_PATH, code);
        Response response = restClient.delete(path);
        return response.as(ProjectResponse.class);
    }

    public ProjectListResponse getAllProjects() {
        log.info("Fetching all projects");
        Response response = restClient.get(BASE_PATH);
        return response.as(ProjectListResponse.class);
    }

    public boolean projectExists(String code) {
        try {
            getProject(code);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}