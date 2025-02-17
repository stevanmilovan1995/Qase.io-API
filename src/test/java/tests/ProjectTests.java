package tests;

import io.qase.automation.models.request.ProjectRequest;
import io.qase.automation.models.response.ProjectListResponse;
import io.qase.automation.models.response.ProjectResponse;
import io.qase.automation.services.ProjectService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjectTests {
    private final ProjectService projectService = new ProjectService();
    private final String PROJECT_CODE = "NTP";
    private final String PROJECT_TITLE = "New Test Project";

    @BeforeAll
    public void setup() {
        try {
            projectService.deleteProject(PROJECT_CODE);
        } catch (Exception ignored) {
        }
    }

    @Test
    @Order(1)
    public void shouldCreateProject() {
        ProjectRequest request = ProjectRequest.createDefaultProject(PROJECT_TITLE, PROJECT_CODE);
        ProjectResponse createResponse = projectService.createProject(request);
        assertTrue(createResponse.isStatus());
        assertEquals(PROJECT_CODE, createResponse.getResult().getCode());
        ProjectResponse getProjectResponse = projectService.getProject(PROJECT_CODE);
        assertEquals(PROJECT_TITLE, getProjectResponse.getResult().getTitle());
    }

    @Test
    @Order(2)
    public void shouldGetProject() {
        ProjectResponse response = projectService.getProject(PROJECT_CODE);
        assertEquals(PROJECT_TITLE, response.getResult().getTitle());
        assertEquals(PROJECT_CODE, response.getResult().getCode());
    }

    @Test
    @Order(3)
    public void shouldDeleteProject() {
        ProjectResponse response = projectService.deleteProject(PROJECT_CODE);
        assertTrue(response.isStatus());
        ProjectListResponse getAllProjectsResponse = projectService.getAllProjects();

        assertTrue(getAllProjectsResponse.getResult().getEntities().stream()
                .noneMatch(project -> project.getCode().equals(PROJECT_CODE)));
    }

    @AfterAll
    public void cleanup() {
        try {
            projectService.deleteProject(PROJECT_CODE);
        } catch (Exception ignored) {
        }
    }
}