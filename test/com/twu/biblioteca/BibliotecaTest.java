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

        assertNotNull("Book should not be null!", books.get(0));
        assertEquals("Real World Haskell", books.get(0).name());
        assertEquals("O'Sullivan, Goerzen, and Stuart", books.get(0).authors());
        assertEquals(2009, books.get(0).yearPublished());

        assertNotNull("Book should not be null!", books.get(1));
        assertEquals("Java Persistence with Hibernate", books.get(1).name());
        assertEquals("Bauer, and King", books.get(1).authors());
        assertEquals(2007, books.get(1).yearPublished());
    }

    @Test
    public void testCheckOutBook() throws NoSuchBook {
        biblioteca.checkOutBookByName("Real World Haskell");

        List<Book> books = biblioteca.availableBooks();
        assertNotNull("Available books should not be null!", books);
        assertEquals("Number of available books", 1, books.size());
        assertNotNull("Book should not be null!", books.get(0));

        assertEquals("Java Persistence with Hibernate", books.get(0).name());
        assertEquals("Bauer, and King", books.get(0).authors());
        assertEquals(2007, books.get(0).yearPublished());
    }


    @Test(expected = NoSuchBook.class)
    public void testCheckOutBook_BogusBook() throws NoSuchBook {
        biblioteca.checkOutBookByName("Design Patterns");
    }

    @Test(expected = NoSuchBook.class)
    public void testCheckOutBook_AlreadyCheckedOut() throws NoSuchBook {
        biblioteca.checkOutBookByName("Real World Haskell");
        biblioteca.checkOutBookByName("Real World Haskell");
    }

}
