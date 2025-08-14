package com.avenga.api;

import com.avenga.constants.ApiEndpoints;
import com.avenga.models.Author;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorsAPI {
    private static Response response;

    /**
     * Constructs the URL path for accessing a specific author by their ID.
     *
     * @param id The unique identifier of the author.
     * @return A string representing the full endpoint path for the author.
     */
    private static String authorById(long id) {
        return String.format("%s/%d", ApiEndpoints.AUTHORS, id);
    }

    /**
     * Sends a POST request to create a new author using the provided request specification.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @param author  The Author object to be sent in the request body.
     * @return The Response received after attempting to create the author.
     */
    public Response createAuthor(RequestSpecification request, Author author) {
        response = request.body(author).post(ApiEndpoints.AUTHORS);
        return response;
    }

    /**
     * Sends a GET request to retrieve all authors.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @return The Response containing the list of all authors.
     */
    public Response getAllAuthors(RequestSpecification request) {
        response = request.get(ApiEndpoints.AUTHORS);
        return response;
    }

    /**
     * Sends a GET request to retrieve a specific author by their ID.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @param id      The unique identifier of the author.
     * @return The Response containing the author's details.
     */
    public Response getAuthorById(RequestSpecification request, long id) {
        response = request.get(authorById(id));
        return response;
    }

    /**
     * Sends a GET request to retrieve an author based on a book's ID.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @param bookId  The unique identifier of the book.
     * @return The Response containing the author's details associated with the book.
     */
    public Response getAuthorByBookId(RequestSpecification request, long bookId) {
        response = request.get(ApiEndpoints.AUTHORS + "/authors/books/" + bookId);
        return response;
    }

    /**
     * Sends a PUT request to update an existing author by their ID.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @param author  The updated Author object to be sent in the request body.
     * @param id      The unique identifier of the author to update.
     * @return The Response after attempting to update the author.
     */
    public Response updateAuthorById(RequestSpecification request, Author author, long id) {
        response = request.body(author).put(authorById(id));
        return response;
    }

    /**
     * Sends a DELETE request to remove an author by their ID.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @param id      The unique identifier of the author to delete.
     * @return The Response after attempting to delete the author.
     */
    public Response deleteAuthorById(RequestSpecification request, long id) {
        response = request.delete(authorById(id));
        return response;
    }
}
