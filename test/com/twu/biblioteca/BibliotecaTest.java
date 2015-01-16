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

    @Test
    public void testReturnBook() throws NoSuchBook {
        biblioteca.checkOutBookByName("Real World Haskell");
        assertEquals("Number of available books", 1, biblioteca.availableBooks().size());
        biblioteca.returnBookByName("Real World Haskell");
        assertEquals("Number of available books", 2, biblioteca.availableBooks().size());
    }

    @Test(expected = NoSuchBook.class)
    public void testReturnBook_BogusBook() throws NoSuchBook {
        biblioteca.returnBookByName("Design Patterns");
    }

    @Test(expected = NoSuchBook.class)
    public void testReturnBook_NotCheckedOut() throws NoSuchBook {
        biblioteca.returnBookByName("Real World Haskell");
    }


    @Test
    public void testAvailableMovies() {
        List<Movie> movies = biblioteca.availableMovies();

        assertNotNull("Available movies should not be null!", movies);

        assertEquals("Number of available movies", 3, movies.size());

        assertNotNull("Movie should not be null!", movies.get(0));
        assertNotNull("Movie should not be null!", movies.get(1));
        assertNotNull("Movie should not be null!", movies.get(2));
        assertEquals("Groundhog Day", movies.get(0).name());
        assertEquals("Hotel", movies.get(1).name());
        assertEquals("The Hobbit: The Battle of the Five Armies", movies.get(2).name());
        assertEquals(1993, movies.get(0).year());
        assertEquals(2004, movies.get(1).year());
        assertEquals(2014, movies.get(2).year());
        assertEquals("Harold Ramis", movies.get(0).director());
        assertEquals("Jessica Hausner", movies.get(1).director());
        assertEquals("Peter Jackson", movies.get(2).director());
        assertEquals(Rating.best, movies.get(0).rating());
        assertEquals(Rating.worst, movies.get(1).rating());
        assertEquals(Rating.unrated, movies.get(2).rating());
    }


    @Test
    public void testCheckOutMovie() throws NoSuchMovie {
        biblioteca.checkOutMovieByName("Groundhog Day");

        List<Movie> movies = biblioteca.availableMovies();
        assertEquals("Number of available movies", 2, movies.size());

        assertNotNull("Movie should not be null!", movies.get(0));
        assertNotNull("Movie should not be null!", movies.get(1));
        assertEquals("Hotel", movies.get(0).name());
        assertEquals("The Hobbit: The Battle of the Five Armies", movies.get(1).name());
        assertEquals(2004, movies.get(0).year());
        assertEquals(2014, movies.get(1).year());
        assertEquals("Jessica Hausner", movies.get(0).director());
        assertEquals("Peter Jackson", movies.get(1).director());
        assertEquals(Rating.worst, movies.get(0).rating());
        assertEquals(Rating.unrated, movies.get(1).rating());
    }

    @Test(expected = NoSuchMovie.class)
    public void testCheckOutMovie_BogusMovie() throws NoSuchMovie {
        biblioteca.checkOutMovieByName("Bill & Ted's Excellent Adventure");
    }

    @Test(expected = NoSuchMovie.class)
    public void testCheckOutMovie_AlreadyCheckedOut() throws NoSuchMovie {
        biblioteca.checkOutMovieByName("Groundhog Day");
        biblioteca.checkOutMovieByName("Groundhog Day");
    }

}