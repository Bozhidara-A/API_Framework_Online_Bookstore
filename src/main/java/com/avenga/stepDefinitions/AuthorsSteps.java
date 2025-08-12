package com.avenga.stepDefinitions;

import com.avenga.api.AuthorsAPI;
import com.avenga.cucumber.TestContext;
import com.avenga.models.Author;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class AuthorsSteps extends BaseSteps {

    private static Response response;
    private final AuthorsAPI authorsAPI = new AuthorsAPI();

    public static void verifyAuthorFields(Author author) {
        assertNotNull("Author ID is missing.", author.id);
        assertNotNull("Book ID is missing.", author.idBook);
        assertNotNull("First name is missing.", author.firstName);
        assertNotNull("Last name is missing.", author.lastName);
    }

    public void verifyAuthorFieldsValues(Author author, Long expectedId, Long expectedBookId, String expectedFirstName, String expectedLastName) {
        assertEquals("Author ID value mismatch.", author.id, expectedId);
        assertEquals("Book ID value mismatch.", author.idBook, expectedBookId);
        assertEquals("First name value mismatch.", author.firstName, expectedFirstName);
        assertEquals("Last name mismatch.", author.lastName, expectedLastName);
    }

    public AuthorsSteps(TestContext testContext) {
        super(testContext);
    }

    @When("I retrieve all authors")
    public void retrieveAllAuthors() {
        response = authorsAPI.getAllAuthors(request);
        getScenarioContext().setResponse(response);
    }

    @When("I retrieve the author with ID: {int}")
    public void getAuthorById(int id) {
        response = authorsAPI.getAuthorById(request, id);
        getScenarioContext().setResponse(response);
    }

    @When("I retrieve the authors by book ID: {int}")
    public void getAuthorByBookId(int id) {
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

}
