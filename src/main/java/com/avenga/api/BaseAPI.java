package com.avenga.api;

import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class BaseAPI {

    private final RequestSpecification request;

    public BaseAPI(String baseUri) {
        RestAssured.baseURI = baseUri;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    public RequestSpecification getRequest() {
        return request;
    }

}
