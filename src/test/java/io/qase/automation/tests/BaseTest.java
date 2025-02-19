package io.qase.automation.tests;
import io.qase.automation.api.client.RestClient;
import io.qase.automation.services.ProjectService;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.apache.logging.log4j.Logger;

public abstract class BaseTest {
    protected static final Logger log = LogManager.getLogger(BaseTest.class);
    protected RestClient restClient;
    protected static ProjectService projectService;

    @BeforeEach
    void baseSetUp() {
        restClient = new RestClient();
        projectService = new ProjectService(restClient);
    }
}
