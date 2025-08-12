package com.avenga.models;

public class Book {

    public Long id;
    public String title;
    public String description;
    public Long pageCount;
    public String excerpt;
    public String publishDate;

    public Book() {
    }

    public Book(Long id, String title, String description,
                Long pageCount, String excerpt, String publishDate) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.excerpt = excerpt;
        this.publishDate = publishDate;
    }
}