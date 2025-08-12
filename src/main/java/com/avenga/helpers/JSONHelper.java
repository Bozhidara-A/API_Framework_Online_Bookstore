package com.avenga.helpers;

import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JSONHelper {

    /**
     * Checks if the response contains a value at the given JSON path that matches or contains the expected value.
     *
     * @param response      The RestAssured response object.
     * @param jsonPath      The JSON path to check (e.g., "errors.'id'" or "errors.'$.title'").
     * @param expectedValue The expected value to match or contain.
     */
    public static void getJSONPathValue(Response response, String jsonPath, String expectedValue) {

        List<String> values = response.jsonPath().getList(jsonPath);
        Assert.assertNotNull("No values found at path: " + jsonPath, values);

        boolean isContained = values.stream().anyMatch(msg -> msg.contains(expectedValue));
        Assert.assertTrue(
                "Expected value not found at path. Path: " + jsonPath +
                        " | Expected to contain: " + expectedValue +
                        " | Actual values: " + values,
                isContained
        );
    }
}