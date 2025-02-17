package io.qase.automation.api.client;

import io.qase.automation.api.specifications.RequestSpecs;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class RestClient {
    private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    public Response get(String endpoint) {
        log.info("Executing GET request to: {}", endpoint);
        return given()
                .log().all()
                .spec(RequestSpecs.getDefaultSpec())
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response post(String endpoint, Object body) {
        log.info("Executing POST request to: {}", endpoint);
        return given()
                .log().all()
                .spec(RequestSpecs.getDefaultSpec())
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response put(String endpoint, Object body) {
        log.info("Executing PUT request to: {}", endpoint);
        return given()
                .log().all()
                .spec(RequestSpecs.getDefaultSpec())
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response delete(String endpoint) {
        log.info("Executing DELETE request to: {}", endpoint);
        return given()
                .log().all()
                .spec(RequestSpecs.getDefaultSpec())
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
