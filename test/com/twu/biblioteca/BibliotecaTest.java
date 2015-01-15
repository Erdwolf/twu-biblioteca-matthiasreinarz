package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BibliotecaTest {

    private Biblioteca biblioteca;

    @Before
    public void setUp() {
        biblioteca = new Biblioteca();
    }

    @Test
    public void testAvailableBooks() {
        List<Book> books = biblioteca.availableBooks();
        assertNotNull("Available books should not be null!", books);
        assertEquals("Number of available books", 2, books.size());
    }

}
