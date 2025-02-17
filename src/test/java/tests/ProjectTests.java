package tests;

import io.qase.automation.api.client.RestClient;
import io.qase.automation.models.request.ProjectRequest;
import io.qase.automation.models.response.ProjectListResponse;
import io.qase.automation.models.response.ProjectResponse;
import io.qase.automation.services.ProjectService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Project API Tests")
public class ProjectTests {
    private static final Logger log = LoggerFactory.getLogger(ProjectTests.class);
    private static final String PROJECT_CODE = "NTP";
    private static final String PROJECT_TITLE = "New Test Project";
    private ProjectService projectService;
    private ProjectRequest defaultProject;

    @BeforeEach
    void setUp() {
        RestClient restClient = new RestClient();
        projectService = new ProjectService(restClient);
        defaultProject = ProjectRequest.createDefaultProject(PROJECT_TITLE, PROJECT_CODE);
        cleanupProject();
    }

    @Test
    @Order(1)
    @DisplayName("Should create new project")
    void shouldCreateProject() {
        ProjectResponse createResponse = projectService.createProject(defaultProject);
        assertTrue(createResponse.isStatus(), "Project creation should be successful");
        assertEquals(PROJECT_CODE, createResponse.getResult().getCode(), "Project code should match");
        ProjectResponse getProjectResponse = projectService.getProject(PROJECT_CODE);
        assertEquals(PROJECT_TITLE, getProjectResponse.getResult().getTitle(), "Project title should match");
    }

    @Test
    @Order(2)
    @DisplayName("Should get existing project")
    void shouldGetProject() {
        projectService.createProject(defaultProject);
        ProjectResponse response = projectService.getProject(PROJECT_CODE);
        assertEquals(PROJECT_TITLE, response.getResult().getTitle(), "Project title should match");
        assertEquals(PROJECT_CODE, response.getResult().getCode(), "Project code should match");
    }

    @Test
    @Order(3)
    @DisplayName("Should delete existing project")
    void shouldDeleteProject() {
        projectService.createProject(defaultProject);
        ProjectResponse deleteResponse = projectService.deleteProject(PROJECT_CODE);
        assertTrue(deleteResponse.isStatus(), "Project deletion should be successful");
        ProjectListResponse allProjects = projectService.getAllProjects();
        assertTrue(allProjects.getResult().getEntities().stream().noneMatch(project -> project.getCode().equals(PROJECT_CODE)), "Project should not exist in project list");
    }

    @Test
    @DisplayName("Should get all projects")
    void shouldGetAllProjects() {
        projectService.createProject(defaultProject);
        ProjectListResponse response = projectService.getAllProjects();
        assertNotNull(response.getResult().getEntities(), "Project list should not be null");
        assertFalse(response.getResult().getEntities().isEmpty(), "Project list should not be empty");
    }

    private void cleanupProject() {
        try {
            log.info("Cleaning up project: {}", PROJECT_CODE);
            projectService.deleteProject(PROJECT_CODE);
        } catch (Exception e) {
            log.debug("Project cleanup failed (project probably doesn't exist): {}", e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        cleanupProject();
    }
}