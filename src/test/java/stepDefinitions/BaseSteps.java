package stepDefinitions;

import com.avenga.cucumber.ScenarioContext;
import com.avenga.cucumber.TestContext;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

    protected final RequestSpecification request;
    protected final TestContext testContext;
    protected final ScenarioContext scenarioContext;

    public BaseSteps(TestContext testContext) {
        this.testContext = testContext;
        this.request = testContext.getRequest();
        this.scenarioContext = testContext.getScenarioContext();
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}