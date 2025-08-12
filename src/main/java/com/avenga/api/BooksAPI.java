package com.avenga.api;

import com.avenga.constants.ApiEndpoints;
import com.avenga.models.Book;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BooksAPI {

    private static Response response;

    public static String bookById(long id) {
        return ApiEndpoints.BOOKS + "/" + id;
    }

    public Response createBook(RequestSpecification request, Book book) {
        response = request.body(book).post(ApiEndpoints.BOOKS);
        return response;
    }

    public Response getAllBooks(RequestSpecification request) {
        response = request.get(ApiEndpoints.BOOKS);
        return response;
    }

    public Response getBookById(RequestSpecification request, long id) {
        response = request.get(bookById(id));
        return response;
    }

    public Response updateBookById(RequestSpecification request, Book book, long id) {
        response = request.body(book).put(bookById(id));
        return response;
    }

    public Response deleteBookById(RequestSpecification request, long id) {
        response = request.delete(bookById(id));
        return response;
    }
}
