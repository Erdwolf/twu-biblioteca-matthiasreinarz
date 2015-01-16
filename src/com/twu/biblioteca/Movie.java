package com.twu.biblioteca;


public class Movie {

    private final String name;
    private final int year;
    private final String director;
    private final Rating rating;

    public Movie(String name, int year, String director, Rating rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String name() {
        return name;
    }

    public int year() {
        return year;
    }

    public String director() {
        return director;
    }

    public Rating rating() {
        return rating;
    }

    @Override
    public String toString() {
        return name + " | " + year + " | " + director + " | " + rating;
    }
}
