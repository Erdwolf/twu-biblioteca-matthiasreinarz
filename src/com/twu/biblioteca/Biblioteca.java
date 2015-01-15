package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Biblioteca {

    private final List<Book> availableBooks = new ArrayList<Book>() {{
        add(null);
        add(null);
    }};

    public List<Book> availableBooks() {
        return availableBooks;
    }

}
