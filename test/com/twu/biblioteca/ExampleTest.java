package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ExampleTest {

    private Scanner s;
    private Writer w;
    private Thread t;

    @Before
    public void captureOutput() throws IOException {
        final PipedOutputStream pipeIn  = new PipedOutputStream();
        final PipedOutputStream pipeOut = new PipedOutputStream();

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new BibliotecaApp(new PipedInputStream(pipeIn), new PrintStream(pipeOut)).run();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();

        w = new OutputStreamWriter(pipeIn);
        s = new Scanner(new PipedInputStream(pipeOut));
    }

    private void provideInput(String string) throws IOException {
        w.write(string);
        w.flush();
    }

    @Test
    public void testWelcomeMessage() {
        assertEquals("Welcome to Biblioteca!", s.nextLine());
    }

    @Test
    public void testMainMenu() {
        s.nextLine();
        assertEquals("0) Quit", s.nextLine());
        assertEquals("1) List books", s.nextLine());
        assertEquals("2) Checkout book", s.nextLine());
        assertEquals("3) Return book", s.nextLine());
        assertEquals("4) List movies", s.nextLine());
        assertEquals("5) Checkout movie", s.nextLine());
        assertEquals(">", s.next(">"));
    }

    @Test
    public void testListOfBooks() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("1\n");
        assertEquals("Real World Haskell | O'Sullivan, Goerzen, and Stuart | 2009", s.nextLine());
        assertEquals("Java Persistence with Hibernate | Bauer, and King | 2007", s.nextLine());
    }


    @Test
    public void testInvalidMenuOption() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("123\n");
        assertEquals("Select a valid option!", s.nextLine());
    }

    @Test
    public void testInvalidMenuOption_ReEnterChoice() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("123\n");
        s.nextLine();
        testListOfBooks();
    }

    @Test
    public void testQuit() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("0\n");
        assertFalse(s.hasNext());
        assertFalse(t.isAlive());
    }

    @Test
    public void testCheckoutBookAndSuccessfulCheckout() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("2\n");
        s.useDelimiter(">");
        s.next("Name of book");
        s.reset();
        s.skip("> ");
        provideInput("Real World Haskell\n");
        assertEquals("Thank you! Enjoy the book", s.nextLine());
        s.skip("> ");
        provideInput("1\n");
        assertEquals("Java Persistence with Hibernate | Bauer, and King | 2007", s.nextLine());
    }

    @Test
    public void testUnsuccessfulCheckout_BogusBook() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("2\n");
        s.useDelimiter(">");
        s.next("Name of book");
        s.reset();
        s.skip("> ");
        provideInput("Design Patterns\n");
        assertEquals("That book is not available.", s.nextLine());
    }

    @Test
    public void testUnsuccessfulCheckout_CheckedOutBook() throws IOException {
        testCheckoutBookAndSuccessfulCheckout();
        s.skip("> ");
        provideInput("2\n");
        s.useDelimiter(">");
        s.next("Name of book");
        s.reset();
        s.skip("> ");
        provideInput("Real World Haskell\n");
        assertEquals("That book is not available.", s.nextLine());
    }

    @Test
    public void testReturnBookAndSuccessfulReturn() throws IOException {
        testCheckoutBookAndSuccessfulCheckout();
        s.skip("> ");
        provideInput("3\n");
        s.useDelimiter(">");
        s.next("Name of book");
        s.reset();
        s.skip("> ");
        provideInput("Real World Haskell\n");
        assertEquals("Thank you for returning the book.", s.nextLine());
        s.skip("> ");
        provideInput("1\n");
        assertEquals("Real World Haskell | O'Sullivan, Goerzen, and Stuart | 2009", s.nextLine());
        assertEquals("Java Persistence with Hibernate | Bauer, and King | 2007", s.nextLine());
    }

    @Test
    public void testUnsuccessfulReturn_BogusBook() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("3\n");
        s.useDelimiter(">");
        s.next("Name of book");
        s.reset();
        s.skip("> ");
        provideInput("Design Patterns\n");
        assertEquals("That is not a valid book to return.", s.nextLine());
    }

    @Test
    public void testUnsuccessfulReturn_AvailableBook() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("3\n");
        s.useDelimiter(">");
        s.next("Name of book");
        s.reset();
        s.skip("> ");
        provideInput("Real World Haskell\n");
        assertEquals("That is not a valid book to return.", s.nextLine());
    }


    @Test
    public void testListOfMovies() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("4\n");
        assertEquals("Groundhog Day | 1993 | Harold Ramis | 10", s.nextLine());
        assertEquals("Hotel | 2004 | Jessica Hausner | 1", s.nextLine());
        assertEquals("The Hobbit: The Battle of the Five Armies | 2014 | Peter Jackson | unrated", s.nextLine());
    }

    @Test
    public void testCheckoutMovie() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("5\n");
        s.useDelimiter(">");
        s.next("Name of movie");
        s.reset();
        s.skip("> ");
        provideInput("Hotel\n");
    }
}
