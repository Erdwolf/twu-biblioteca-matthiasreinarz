package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Biblioteca {

    private final List<Book> availableBooks = new ArrayList<Book>() {{
        add(new Book("Real World Haskell", "O'Sullivan, Goerzen, and Stuart", 2009));
        add(new Book("Java Persistence with Hibernate", "Bauer, and King", 2007));
    }};
    private final List<Book> checkedOutBooks = new ArrayList<Book>();

    private List<Movie> availableMovies = new ArrayList<Movie>() {{
        add(new Movie("Groundhog Day", 1993, "Harold Ramis", Rating.best));
        add(new Movie("Hotel", 2004, "Jessica Hausner", Rating.worst));
        add(new Movie("The Hobbit: The Battle of the Five Armies", 2014, "Peter Jackson", Rating.unrated));
    }};;

    public List<Book> availableBooks() {
        return availableBooks;
    }

    private List<Book> checkedOutBooks() {
        return checkedOutBooks;
    }

    public void checkOutBookByName(String name) throws NoSuchBook {
        Book book = findBookByName(name, availableBooks());
        checkOutBook(book);
    }

    public void returnBookByName(String name) throws NoSuchBook {
        Book book = findBookByName(name, checkedOutBooks());
        returnBook(book);
    }

    private void checkOutBook(Book book) {
        availableBooks.remove(book);
        checkedOutBooks.add(book);
    }

    private void returnBook(Book book) {
        checkedOutBooks.remove(book);
        availableBooks.add(book);
    }

    private static Book findBookByName(String name, List<Book> books) throws NoSuchBook {
        for(Book book : books) {
            if(name.equals((book.name()))) {
                return book;
            }
        }
        throw new NoSuchBook();
    }

    public List<Movie> availableMovies() {
        return availableMovies;
    }
}
