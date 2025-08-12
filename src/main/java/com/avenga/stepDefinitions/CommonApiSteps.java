package com.avenga.stepDefinitions;

import com.avenga.api.AuthorsAPI;
import com.avenga.api.BooksAPI;
import com.avenga.cucumber.TestContext;

import com.avenga.helpers.JSONHelper;
import com.avenga.models.Author;
import com.avenga.models.Book;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CommonApiSteps extends BaseSteps {

    private static Response response;
    private final BooksAPI booksAPI = new BooksAPI();
    private final AuthorsAPI authorsAPI = new AuthorsAPI();

    public CommonApiSteps(TestContext testContext) {
        super(testContext);
    }

    @Then("the response status code should be {int}")
    public void verifyResponseStatusCode(int statusCode) {
        response = getScenarioContext().getResponse();
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals("Status code value mismatch. | Expected: " + statusCode + " | Actual: " + actualStatusCode, statusCode, actualStatusCode);
    }

    @Then("the response should contain an error message with title: {string}")
    public void verifyErrorTitle(String errorTitle) {
        response = getScenarioContext().getResponse();
        String actualTitle = response.jsonPath().getString("title");
        Assert.assertTrue("Error title value mismatch. | Expected: " + errorTitle + " | Actual: " + actualTitle, actualTitle.contains(errorTitle));
    }

    @Then("^the response should contain a list of (books|authors)")
    public void verifyResponseContainsObjectList(String object) {
        switch (object.toLowerCase()) {
            case "books":
                List<Book> books = Arrays.asList(response.getBody().as(Book[].class));
                assertNotNull(books);
                assertFalse(books.isEmpty());
                for (Book book : books) {
                    BooksSteps.verifyBookFields(book);
                }
                break;
            case "authors":
                List<Author> authors = Arrays.asList(response.getBody().as(Author[].class));
                assertNotNull(authors);
                assertFalse(authors.isEmpty());
                for (Author author : authors) {
                    AuthorsSteps.verifyAuthorFields(author);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported object: " + object);
        }
    }

    @When("^I delete the (book|author) with ID: (\\d+)$")
    public void deleteById(String object, Long id) {
        switch (object.toLowerCase()) {
            case "book":
                response = booksAPI.deleteBookById(request, id);
                getScenarioContext().setResponse(response);
                break;
            case "author":
                response = authorsAPI.deleteAuthorById(request, id);
                getScenarioContext().setResponse(response);
                break;
            default:
                throw new IllegalArgumentException("Unsupported object: " + object);
        }
    }

    @Then("the response body should be an empty array")
    public void verifyEmptyBody() {
        response = getScenarioContext().getResponse();
        List<?> bodyAsList = response.getBody().jsonPath().getList("$");
        Assert.assertNotNull("Response body is null", bodyAsList);
        Assert.assertTrue("Expected empty array, but got: " + bodyAsList, bodyAsList.isEmpty());
    }

    @Then("the response should contain value: {string} at path: {string}")
    public void verifyErrorMessage(String value, String errorPath) {
        response = getScenarioContext().getResponse();
        JSONHelper.getJSONPathValue(response, errorPath, value);
    }
}