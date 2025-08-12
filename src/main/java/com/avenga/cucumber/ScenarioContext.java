package com.avenga.cucumber;

import com.avenga.enums.Context;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private final Map<String, Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext = new HashMap<String, Object>();
    }

    public void set(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(scenarioContext.get(key));
    }

    public boolean contains(String key) {
        return scenarioContext.containsKey(key);
    }

    public void setResponse(Response response) {
        scenarioContext.put(Context.RESPONSE.toString(), response);
    }

    public Response getResponse() {
        return (Response) scenarioContext.get(Context.RESPONSE.toString());
    }
}
