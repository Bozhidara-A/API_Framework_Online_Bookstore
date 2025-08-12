package com.avenga.models;

public class Author {

    public Long id;
    public Long idBook;
    public String firstName;
    public String lastName;

    public Author() {
    }

    public Author(Long id, Long idBook, String firstName, String lastName) {
        super();
        this.id = id;
        this.idBook = idBook;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
