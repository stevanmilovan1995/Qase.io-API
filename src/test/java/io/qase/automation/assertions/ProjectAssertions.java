package io.qase.automation.assertions;
import io.qase.automation.pojo.projects.responses.GetProjectResponse;
import io.qase.automation.pojo.projects.responses.GetProjectsResponse;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectAssertions {
    public static void assertProjectDetailsMatch(GetProjectResponse response, String expectedTitle, String expectedCode) {
        assertEquals(expectedTitle, response.getResult().getTitle(), String.format("Project title should be '%s'", expectedTitle));
        assertEquals(expectedCode, response.getResult().getCode(), String.format("Project code should be '%s'", expectedCode));
    }

    public static void assertProjectListContains(GetProjectsResponse response, String projectCode) {
        assertTrue(response.getStatus(), "Project list request should be successful");
        assertNotNull(response.getResult(), "Project list should not be null");
        assertTrue(response.getResult().getCount() > 0, "Project list should not be empty");
        boolean projectFound = response.getResult().getEntities().stream().anyMatch(p -> projectCode.equals(p.getCode()));
        assertTrue(projectFound, String.format("Project with code '%s' should exist in the list", projectCode));
    }
}