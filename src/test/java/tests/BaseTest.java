package tests;

import io.qase.automation.api.client.RestClient;
import io.qase.automation.services.ProjectService;
import io.qase.automation.services.TestCaseService;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {
    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected RestClient restClient;
    protected static ProjectService projectService;
    protected TestCaseService testCaseService;

    @BeforeEach
    void baseSetUp() {
        restClient = new RestClient();
        projectService = new ProjectService(restClient);
        testCaseService = new TestCaseService(restClient);
    }
}