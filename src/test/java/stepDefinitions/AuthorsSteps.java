package stepDefinitions;

import com.avenga.api.AuthorsAPI;
import cucumber.TestContext;
import com.avenga.models.Author;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class AuthorsSteps extends BaseSteps {

    private static Response response;
    private final AuthorsAPI authorsAPI = new AuthorsAPI();

    public AuthorsSteps(TestContext testContext) {
        super(testContext);
    }

    protected static void verifyAuthorFields(Author author) {
        assertNotNull("Author ID is missing.", author.id);
        assertNotNull("Book ID is missing.", author.idBook);
        assertNotNull("First name is missing.", author.firstName);
        assertNotNull("Last name is missing.", author.lastName);
    }

    protected void verifyAuthorFieldsValues(Author author, Long expectedId, Long expectedBookId, String expectedFirstName, String expectedLastName) {
        assertEquals("Author ID value mismatch.", author.id, expectedId);
        assertEquals("Book ID value mismatch.", author.idBook, expectedBookId);
        assertEquals("First name value mismatch.", author.firstName, expectedFirstName);
        assertEquals("Last name mismatch.", author.lastName, expectedLastName);
    }

    protected Response createAuthorWithDefaultData(Long id) {
        Author authorRequest = new Author(id, 10L, "John", "Doe");
        response = authorsAPI.createAuthor(request, authorRequest);
        getScenarioContext().setResponse(response);
        return response;
    }

    @When("I retrieve all authors")
    public void retrieveAllAuthors() {
        response = authorsAPI.getAllAuthors(request);
        getScenarioContext().setResponse(response);
    }

    @When("I retrieve the author with ID: {long}")
    public void getAuthorById(Long id) {
        response = authorsAPI.getAuthorById(request, id);
        getScenarioContext().setResponse(response);
    }

    @When("I retrieve the authors by book ID: {long}")
    public void getAuthorByBookId(Long id) {
        response = authorsAPI.getAuthorByBookId(request, id);
        getScenarioContext().setResponse(response);
    }

    @When("I create an author with ID: {long}, Book ID: {long}, First Name: {string}, Last Name: {string}")
    public void createAuthor(Long id, Long bookId, String firstName, String lastName) {
        Author authorRequest = new Author(id, bookId, firstName, lastName);
        response = authorsAPI.createAuthor(request, authorRequest);
        getScenarioContext().setResponse(response);
    }

    @When("I update the author with ID: {long}, with New ID: {long}, Book ID: {long}, First Name: {string}, Last Name: {string}")
    public void updateAuthor(Long id, Long newId, Long bookId, String firstName, String lastName) {
        Author authorRequest = new Author(newId, bookId, firstName, lastName);
        response = authorsAPI.updateAuthorById(request, authorRequest, id);
        getScenarioContext().setResponse(response);
    }

    @Then("the response should contain the author details")
    public void theResponseShouldContainBookDetails() {
        Author author = response.as(Author.class);
        verifyAuthorFields(author);
    }

    @Then("the response should contain an author with ID: {long}, Book ID: {long}, First Name: {string}, Last Name: {string}")
    public void verifyAuthor(Long id, Long bookId, String firstName, String lastName) {
        Author author = response.as(Author.class);
        verifyAuthorFieldsValues(author, id, bookId, firstName, lastName);
    }

    @Given("^an author with ID (\\d+) (does not exist|exists)")
    public void verifyAuthorExistence(Long id, String exists) {
        response = authorsAPI.getAuthorById(request, id);
        if (exists.equals("exists")) {
            if (response.getStatusCode() != 200) {
                response = createAuthorWithDefaultData(id);
                CommonApiSteps.verifyResponseStatusCode(response, 200);
            }
        } else if (exists.equals("does not exist")) {
            if (response.getStatusCode() == 200) {
                response = authorsAPI.deleteAuthorById(request, id);
                CommonApiSteps.verifyResponseStatusCode(response, 200);
            }
        }
    }
}
