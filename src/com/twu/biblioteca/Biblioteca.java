package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Biblioteca {

    private final List<Book> availableBooks = new ArrayList<Book>() {{
        add(new Book("Real World Haskell", "O'Sullivan, Goerzen, and Stuart", 2009));
        add(new Book("Java Persistence with Hibernate", "Bauer, and King", 2007));
    }};

    public List<Book> availableBooks() {
        return availableBooks;
    }

}
