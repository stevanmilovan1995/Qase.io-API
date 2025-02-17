package tests;

import io.qase.automation.models.request.ProjectRequest;
import io.qase.automation.models.request.TestCaseRequest;
import io.qase.automation.models.response.TestCaseListResponse;
import io.qase.automation.models.response.TestCaseResponse;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Test Case API Tests")
public class TestCaseTests extends BaseTest {
    private static final String PROJECT_CODE = "DEMO";
    private static final String TEST_CASE_TITLE = "API Test Case";
    private static final String UPDATED_TITLE = "Updated API Test Case";
    private int testCaseId;

    @BeforeEach
    void setUp() {
        createTestProject();
    }

    @Test
    @Order(1)
    @DisplayName("Should create new test case")
    void shouldCreateTestCase() {
        TestCaseRequest request = TestCaseRequest.createBasicTestCase(TEST_CASE_TITLE);
        TestCaseResponse response = testCaseService.createTestCase(PROJECT_CODE, request);
        assertTrue(response.isStatus(), "Test case creation should be successful");
        TestCaseResponse getTestCaseResponse = testCaseService.getTestCase(PROJECT_CODE, response.getResult().getId());
        assertEquals(TEST_CASE_TITLE, getTestCaseResponse.getResult().getTitle(), "Created test case should have correct title");
    }

    @Test
    @Order(2)
    @DisplayName("Should get existing test case")
    void shouldGetTestCase() {
        TestCaseRequest request = TestCaseRequest.createBasicTestCase(TEST_CASE_TITLE);
        TestCaseResponse createResponse = testCaseService.createTestCase(PROJECT_CODE, request);
        testCaseId = createResponse.getResult().getId();
        TestCaseResponse response = testCaseService.getTestCase(PROJECT_CODE, testCaseId);
        assertTrue(response.isStatus(), "Should successfully get test case");
        assertEquals(TEST_CASE_TITLE, response.getResult().getTitle(), "Retrieved test case should have correct title");
        assertEquals(testCaseId, response.getResult().getId(), "Retrieved test case should have correct ID");
    }

    @Test
    @Order(3)
    @DisplayName("Should update existing test case")
    void shouldUpdateTestCase() {
        TestCaseRequest createRequest = TestCaseRequest.createBasicTestCase(TEST_CASE_TITLE);
        TestCaseResponse createResponse = testCaseService.createTestCase(PROJECT_CODE, createRequest);
        testCaseId = createResponse.getResult().getId();
        TestCaseRequest updateRequest = TestCaseRequest.builder().title(UPDATED_TITLE).description("Updated description").priority(1).severity(1).build();
        TestCaseResponse updateResponse = testCaseService.updateTestCase(PROJECT_CODE, testCaseId, updateRequest);
        assertTrue(updateResponse.isStatus(), "Test case update should be successful");
        TestCaseResponse getTestCaseResponse = testCaseService.getTestCase(PROJECT_CODE, testCaseId);
        assertEquals(UPDATED_TITLE, getTestCaseResponse.getResult().getTitle(), "Updated test case should have new title");
        assertEquals(1, getTestCaseResponse.getResult().getPriority(), "Updated test case should have new priority");
    }

    @Test
    @Order(4)
    @DisplayName("Should delete test case")
    void shouldDeleteTestCase() {
        TestCaseRequest request = TestCaseRequest.createBasicTestCase(TEST_CASE_TITLE);
        TestCaseResponse createResponse = testCaseService.createTestCase(PROJECT_CODE, request);
        testCaseId = createResponse.getResult().getId();
        TestCaseResponse deleteResponse = testCaseService.deleteTestCase(PROJECT_CODE, testCaseId);
        assertTrue(deleteResponse.isStatus(), "Test case deletion should be successful");
        TestCaseListResponse allTestCases = testCaseService.getAllTestCases(PROJECT_CODE);
        assertTrue(allTestCases.getResult().getEntities().stream().noneMatch(testCase -> testCase.getId() == testCaseId), "Deleted test case should not be present in the list");
    }

    private void createTestProject() {
        try {
            projectService.createProject(ProjectRequest.createDefaultProject("Demo Project", PROJECT_CODE));
        } catch (Exception e) {
            log.debug("Project already exists: {}", e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        if (testCaseId > 0) {
            try {
                log.info("Cleaning up test case: {}", testCaseId);
                testCaseService.deleteTestCase(PROJECT_CODE, testCaseId);
            } catch (Exception e) {
                log.debug("Test case cleanup failed: {}", e.getMessage());
            }
        }
    }

    @AfterAll
    static void cleanUp() {
        try {
            projectService.deleteProject(PROJECT_CODE);
        } catch (Exception e) {
            log.debug("Project cleanup failed: {}", e.getMessage());
        }
    }
}