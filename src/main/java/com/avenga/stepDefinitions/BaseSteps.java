package com.avenga.stepDefinitions;

import com.avenga.api.BaseAPI;
import com.avenga.cucumber.ScenarioContext;
import com.avenga.cucumber.TestContext;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

    private BaseAPI baseAPI;
    protected final RequestSpecification request;
    protected final TestContext testContext;
    protected final ScenarioContext scenarioContext;

    public BaseSteps(TestContext testContext) {
        this.testContext = testContext;
        BaseAPI baseApi = testContext.getBaseApi();
        this.request = testContext.getRequest();
        this.scenarioContext = testContext.getScenarioContext();
    }

    public BaseAPI getbaseAPI() {
        return baseAPI;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}
