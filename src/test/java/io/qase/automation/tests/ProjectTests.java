package io.qase.automation.tests;

import io.qase.automation.pojo.projects.data.ProjectTestData;
import io.qase.automation.pojo.projects.requests.CreateProjectRequest;
import io.qase.automation.pojo.projects.responses.CreateProjectResponse;
import io.qase.automation.pojo.projects.responses.GetProjectResponse;
import io.qase.automation.pojo.projects.responses.GetProjectsResponse;
import io.qase.automation.assertions.ProjectAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTests extends BaseTest {
    @BeforeEach
    void setUp() {
        cleanupProject();
    }

    @Test
    public void createProjectTest() {
        CreateProjectRequest request = CreateProjectRequest.createProject(ProjectTestData.DEFAULT_TITLE, ProjectTestData.DEFAULT_CODE);
        CreateProjectResponse response = projectService.createProject(request);
        GetProjectResponse projectResponse = projectService.getProject(ProjectTestData.DEFAULT_CODE);
        ProjectAssertions.assertProjectDetailsMatch(projectResponse, ProjectTestData.DEFAULT_TITLE, ProjectTestData.DEFAULT_CODE);
    }

    @Test
    public void createDuplicateProjectTest() {
        CreateProjectRequest request1 = CreateProjectRequest.createProject(ProjectTestData.DEFAULT_TITLE, ProjectTestData.DEFAULT_CODE);
        CreateProjectResponse response1 = projectService.createProject(request1);
        assertTrue(response1.isStatus(), "Initial project creation should be successful");
        CreateProjectRequest request2 = CreateProjectRequest.createProject(ProjectTestData.DEFAULT_TITLE, ProjectTestData.DEFAULT_CODE);
        try {
            projectService.createProject(request2);
            fail("Expected exception was not thrown for duplicate project");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Project with the same code already exists"), "Should throw conflict error when creating a duplicate project");
        }
    }

    @Test
    public void getAllProjectsTest() {
        CreateProjectRequest request = CreateProjectRequest.createProject(ProjectTestData.DEFAULT_TITLE, ProjectTestData.DEFAULT_CODE);
        CreateProjectResponse createResponse = projectService.createProject(request);
        assertTrue(createResponse.isStatus(), "Project creation should be successful before retrieving all projects");
        GetProjectsResponse getProjectsResponse = projectService.getAllProjects();
        ProjectAssertions.assertProjectListContains(getProjectsResponse, ProjectTestData.DEFAULT_CODE);
    }

    @Test
    public void getNonExistentProjectTest() {
        try {
            projectService.getProject(ProjectTestData.NONEXISTENT_CODE);
            fail("Expected exception was not thrown for non-existent project");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Project not found"), "Should throw an error when getting a non-existent project");
        }
    }

    @Test
    public void deleteNonExistentProjectTest() {
        try {
            projectService.deleteProject(ProjectTestData.NONEXISTENT_CODE);
            fail("Expected exception was not thrown for non-existent project deletion");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Project not found"), "Should throw an error when deleting a non-existent project");
        }
    }

    @Test
    public void createProjectWithEmptyCodeTest() {
        CreateProjectRequest invalidRequest = CreateProjectRequest.createProject("Invalid Project", "");
        try {
            projectService.createProject(invalidRequest);
            fail("Expected exception was not thrown for empty project code");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Project code is required."), String.format("Expected 'Project code is required.' in error message, but got: '%s'", e.getMessage()));
        }
    }

    private void cleanupProject() {
        try {
            log.info("Cleaning up project: {}", ProjectTestData.DEFAULT_CODE);
            projectService.deleteProject(ProjectTestData.DEFAULT_CODE);
        } catch (Exception e) {
            log.debug("Project cleanup failed (project probably doesn't exist): {}", e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        cleanupProject();
    }
}