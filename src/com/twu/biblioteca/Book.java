package com.twu.biblioteca;

public class Book {

    private final String name;
    private final String authors;
    private final int yearPublished;

    public Book(String name, String authors, int yearPublished) {
        this.name = name;
        this.authors = authors;
        this.yearPublished = yearPublished;
    }

    public String name() {
        return name;
    }

    public String authors() {
        return authors;
    }

    public int yearPublished() {
        return yearPublished;
    }

    @Override
    public String toString() {
        return name + " | " + authors + " | " + yearPublished;
    }

}
