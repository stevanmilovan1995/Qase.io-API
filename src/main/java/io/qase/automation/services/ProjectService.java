package io.qase.automation.services;
import io.qase.automation.api.client.RestClient;
import io.qase.automation.pojo.projects.requests.CreateProjectRequest;
import io.qase.automation.pojo.projects.responses.GetProjectsResponse;
import io.qase.automation.pojo.projects.responses.CreateProjectResponse;
import io.qase.automation.pojo.projects.responses.GetProjectResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static io.qase.automation.pojo.projects.data.ProjectTestData.BASE_PATH;


public class ProjectService {
    protected static final Logger log = LogManager.getLogger(ProjectService.class);
    private final RestClient restClient;
    public ProjectService(RestClient restClient){
        this.restClient = restClient;
    }

    public CreateProjectResponse createProject(CreateProjectRequest request) {
        log.info("Creating new project with code: {}", request.getCode());
        Response response = restClient.post(BASE_PATH, request);
        return response.as(CreateProjectResponse.class);
    }

    public GetProjectResponse getProject(String code) {
        log.info("Fetching project details for code: {}", code);
        String path = String.format("%s/%s", BASE_PATH, code);
        Response response = restClient.get(path);
        return response.as(GetProjectResponse.class);
    }

    public void deleteProject(String code) {
        log.info("Deleting project with code: {}", code);
        String path = String.format("%s/%s", BASE_PATH, code);
        Response response = restClient.delete(path);
        response.as(CreateProjectResponse.class);
    }

    public GetProjectsResponse getAllProjects() {
        log.info("Fetching all projects");
        Response response = restClient.get(BASE_PATH);
        return response.as(GetProjectsResponse.class);
    }

}
