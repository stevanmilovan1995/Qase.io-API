package io.qase.automation.services;

import io.qase.automation.api.client.RestClient;
import io.qase.automation.models.request.TestCaseRequest;
import io.qase.automation.models.response.TestCaseListResponse;
import io.qase.automation.models.response.TestCaseResponse;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseService {
    private static final Logger log = LoggerFactory.getLogger(TestCaseService.class);
    private static final String BASE_PATH = "/case";
    private final RestClient restClient;

    public TestCaseService(RestClient restClient) {
        this.restClient = restClient;
    }

    public TestCaseResponse createTestCase(String projectCode, TestCaseRequest request) {
        log.info("Creating new test case in project: {}", projectCode);
        String path = String.format("%s/%s", BASE_PATH, projectCode);
        Response response = restClient.post(path, request);
        return response.as(TestCaseResponse.class);
    }

    public TestCaseResponse getTestCase(String projectCode, int testCaseId) {
        log.info("Fetching test case {} from project: {}", testCaseId, projectCode);
        String path = String.format("/%s/%s/%d", BASE_PATH, projectCode, testCaseId);
        Response response = restClient.get(path);
        return response.as(TestCaseResponse.class);
    }

    public TestCaseResponse updateTestCase(String projectCode, int testCaseId, TestCaseRequest request) {
        log.info("Updating test case {} in project: {}", testCaseId, projectCode);
        String path = String.format("/%s/%s/%d", BASE_PATH, projectCode, testCaseId);
        Response response = restClient.patch(path, request);
        return response.as(TestCaseResponse.class);
    }

    public TestCaseResponse deleteTestCase(String projectCode, int testCaseId) {
        log.info("Deleting test case {} from project: {}", testCaseId, projectCode);
        String path = String.format("/%s/%s/%d", BASE_PATH, projectCode, testCaseId);
        Response response = restClient.delete(path);
        return response.as(TestCaseResponse.class);
    }

    public TestCaseListResponse getAllTestCases(String projectCode) {
        log.info("Fetching all test cases from project: {}", projectCode);
        String path = String.format("%s/%s", BASE_PATH , projectCode);
        Response response = restClient.get(path);
        return response.as(TestCaseListResponse.class);
    }
}