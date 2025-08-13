package stepDefinitions;

import com.avenga.api.BooksAPI;
import com.avenga.cucumber.TestContext;
import com.avenga.models.Book;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.junit.Assert.*;

public class BooksSteps extends BaseSteps {

    private static Response response;
    private final BooksAPI booksAPI = new BooksAPI();

    public BooksSteps(TestContext testContext) {
        super(testContext);
    }

    protected static void verifyBookFields(Book book) {
        assertNotNull("Book ID is missing.", book.id);
        assertNotNull("Title is missing.", book.title);
        assertNotNull("Description is missing.", book.description);
        assertNotNull("Page count is missing.", book.pageCount);
        assertTrue("Page count should be positive.", book.pageCount >= 0);
        assertNotNull("Excerpt is missing.", book.excerpt);
        assertNotNull("Publish date is missing.", book.publishDate);
    }

    protected void verifyBookFieldsValues(Book book, Long expectedId, String expectedTitle, String expectedDescription, Long expectedPageCount, String expectedExcerpt, String expectedPublishDate) {
        assertEquals("Book ID value mismatch.", book.id, expectedId);
        assertEquals("Title value mismatch.", book.title, expectedTitle);
        assertEquals("Description value mismatch.", book.description, expectedDescription);
        assertEquals("Page value mismatch.", book.pageCount, expectedPageCount);
        assertEquals("Excerpt value mismatch.", book.excerpt, expectedExcerpt);
        assertEquals("Publish value mismatch.", book.publishDate, expectedPublishDate);
    }

    protected Response createBookWithDefaultData(Long id) {
        Book bookRequest = new Book(id, "Book Title", "Book description", 250L, "Book excerpt", "2025-04-03T00:00:00");
        response = booksAPI.createBook(request, bookRequest);
        getScenarioContext().setResponse(response);
        return response;
    }

    @When("I retrieve all books")
    public void retrieveAllBooks() {
        response = booksAPI.getAllBooks(request);
        getScenarioContext().setResponse(response);
    }

    @When("I retrieve the book with ID: {long}")
    public void getBookById(Long id) {
        response = booksAPI.getBookById(request, id);
        getScenarioContext().setResponse(response);
    }

    @When("^I create a book with ID: (\\d+), Title: (.*), Description: (.*), Page Count: (\\d+), Excerpt: (.*), Publish Date: (.*)$")
    public void createBookWithData(Long id, String title, String description, Long pageCount, String excerpt, String publishDate) {
        Book bookRequest = new Book(id, title, description, pageCount, excerpt, publishDate);
        response = booksAPI.createBook(request, bookRequest);
        getScenarioContext().setResponse(response);
    }

    @When("^I update the book with ID: (\\d+) with New ID: (\\d+), Title: (.*), Description: (.*), Page Count: (\\d+), Excerpt: (.*), Publish Date: (.*)$")
    public void updateBookWithData(Long id, Long newId, String title, String description, Long pageCount, String excerpt, String publishDate) {
        Book bookRequest = new Book(newId, title, description, pageCount, excerpt, publishDate);
        response = booksAPI.updateBookById(request, bookRequest, id);
        getScenarioContext().setResponse(response);
    }

    @Then("the response should contain the book details")
    public void theResponseShouldContainBookDetails() {
        Book book = response.as(Book.class);
        verifyBookFields(book);
    }

    @Then("the response should contain a book with ID: (\\d+), Title: (.*), Description: (.*), Page Count: (\\d+), Excerpt: (.*), Publish Date: (.*)$")
    public void verifyBook(Long id, String title, String description, Long pageCount, String excerpt, String publishDate) {
        Book book = response.as(Book.class);
        verifyBookFieldsValues(book, id, title, description, pageCount, excerpt, publishDate);
    }

    @Given("^a book with ID (\\d+) (does not exist|exists)")
    public void verifyBookExistence(Long id, String exists) {
        response = booksAPI.getBookById(request, id);
        if (exists.equals("exists")) {
            if (response.getStatusCode() != 200) {
                response = createBookWithDefaultData(id);
                CommonApiSteps.verifyResponseStatusCode(response, 200);
            }
        } else if (exists.equals("does not exist")) {
            if (response.getStatusCode() == 200) {
                response = booksAPI.deleteBookById(request, id);
                CommonApiSteps.verifyResponseStatusCode(response, 200);
            }
        }
    }
}