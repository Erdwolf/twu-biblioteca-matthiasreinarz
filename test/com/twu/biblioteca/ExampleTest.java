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
    public void testCheckoutBook() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("2\n");
        s.useDelimiter(">");
        s.next("Name of book");
        s.reset();
        s.skip("> ");
        provideInput("Real World Haskell\n");
        s.skip("> ");
        provideInput("1\n");
        assertEquals("Java Persistence with Hibernate | Bauer, and King | 2007", s.nextLine());
    }

}
