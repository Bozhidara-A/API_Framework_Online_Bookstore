package com.avenga.api;

import com.avenga.constants.ApiEndpoints;
import com.avenga.models.Author;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorsAPI {
    private static Response response;

    public static String authorById(long id) {
        return ApiEndpoints.AUTHORS + "/" + id;
    }

    public Response createAuthor(RequestSpecification request, Author author) {
        response = request.body(author).post(ApiEndpoints.AUTHORS);
        return response;
    }

    public Response getAllAuthors(RequestSpecification request) {
        response = request.get(ApiEndpoints.AUTHORS);
        return response;
    }

    public Response getAuthorById(RequestSpecification request, long id) {
        response = request.get(authorById(id));
        return response;
    }

    public Response getAuthorByBookId(RequestSpecification request, long bookId) {
        response = request.get(ApiEndpoints.AUTHORS + "/authors/books/" + bookId);
        return response;
    }

    public Response updateAuthorById(RequestSpecification request, Author author, long id) {
        response = request.body(author).put(authorById(id));
        return response;
    }

    public Response deleteAuthorById(RequestSpecification request, long id) {
        response = request.delete(authorById(id));
        return response;
    }
}
