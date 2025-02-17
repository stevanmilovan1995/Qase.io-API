package io.qase.automation.api.specifications;

import io.qase.automation.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {
    private static RequestSpecification defaultSpec;

    public static RequestSpecification getDefaultSpec() {
        if (defaultSpec == null) {
            defaultSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigManager.getInstance().get("baseUrl"))
                    .addHeader("Token", ConfigManager.getInstance().get("apiToken"))
                    .addHeader("Content-Type", "application/json")
                    .build();
        }
        return defaultSpec;
    }
}
