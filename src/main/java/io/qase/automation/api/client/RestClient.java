package io.qase.automation.api.client;

import io.qase.automation.api.specifications.RequestSpecs;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class RestClient {
    private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    public enum HttpMethod {
        GET, POST, PUT, DELETE
    }

    private Response executeRequest(HttpMethod method, String endpoint, Object body) {
        log.info("Executing {} request to: {}", method, endpoint);
        RequestSpecification request = given().filter(new RequestLoggingFilter(LogDetail.METHOD))
                .filter(new RequestLoggingFilter(LogDetail.URI))
                .filter(new RequestLoggingFilter(LogDetail.HEADERS))
                .filter(new ResponseLoggingFilter(LogDetail.STATUS))
                .filter(new ResponseLoggingFilter(LogDetail.HEADERS))
                .spec(RequestSpecs.getDefaultSpec());
        ;
        if (body != null) {
            request.body(body);
        }
        Response response = request.when().request(method.toString(), endpoint)
                .then().log().all().extract().response();
        log.debug("Response status code: {}", response.getStatusCode());
        return response;

    }

    public Response get(String endpoint) {
        return executeRequest(HttpMethod.GET, endpoint, null);
    }

    public Response post(String endpoint, Object body) {
        return executeRequest(HttpMethod.POST, endpoint, body);
    }

    public Response put(String endpoint, Object body) {
        return executeRequest(HttpMethod.PUT, endpoint, body);
    }

    public Response delete(String endpoint) {
        return executeRequest(HttpMethod.DELETE, endpoint, null);
    }

}
