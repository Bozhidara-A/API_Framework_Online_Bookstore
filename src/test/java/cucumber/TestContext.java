package cucumber;

import com.avenga.api.BaseAPI;
import com.avenga.config.ConfigProvider;
import io.restassured.specification.RequestSpecification;

public class TestContext {

    private final BaseAPI baseApi;
    private final RequestSpecification request;
    private final ScenarioContext scenarioContext;

    public TestContext() {
        baseApi = new BaseAPI(ConfigProvider.getInstance().getBaseUrl());
        request = baseApi.getRequest();
        scenarioContext = new ScenarioContext();
    }

    public BaseAPI getBaseApi() {
        return baseApi;
    }

    public RequestSpecification getRequest() {
        return request;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
