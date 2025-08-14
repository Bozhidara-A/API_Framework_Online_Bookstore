package com.avenga.api;

import com.avenga.constants.ApiEndpoints;
import com.avenga.models.Book;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BooksAPI {

    private static Response response;

    /**
     * Constructs the URL path for accessing a specific book by its ID.
     *
     * @param id The unique identifier of the book.
     * @return A string representing the full endpoint path for the book.
     */
    private static String bookById(long id) {
        return String.format("%s/%d", ApiEndpoints.BOOKS, id);
    }

    /**
     * Sends a POST request to create a new book using the provided request specification.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @param book    The Book object to be sent in the request body.
     * @return The Response received after attempting to create the book.
     */
    public Response createBook(RequestSpecification request, Book book) {
        response = request.body(book).post(ApiEndpoints.BOOKS);
        return response;
    }

    /**
     * Sends a GET request to retrieve all books.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @return The Response containing the list of all books.
     */
    public Response getAllBooks(RequestSpecification request) {
        response = request.get(ApiEndpoints.BOOKS);
        return response;
    }

    /**
     * Sends a GET request to retrieve a specific book by its ID.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @param id      The unique identifier of the book.
     * @return The Response containing the book's details.
     */
    public Response getBookById(RequestSpecification request, long id) {
        response = request.get(bookById(id));
        return response;
    }

    /**
     * Sends a PUT request to update an existing book by its ID.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @param book    The updated Book object to be sent in the request body.
     * @param id      The unique identifier of the book to update.
     * @return The Response after attempting to update the book.
     */
    public Response updateBookById(RequestSpecification request, Book book, long id) {
        response = request.body(book).put(bookById(id));
        return response;
    }

    /**
     * Sends a DELETE request to remove a book by its ID.
     *
     * @param request The configured RequestSpecification for the HTTP request.
     * @param id      The unique identifier of the book to delete.
     * @return The Response after attempting to delete the book.
     */
    public Response deleteBookById(RequestSpecification request, long id) {
        response = request.delete(bookById(id));
        return response;
    }
}