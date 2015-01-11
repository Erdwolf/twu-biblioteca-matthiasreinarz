package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    private Scanner s;
    private Writer w;

    @Before
    public void captureOutput() throws IOException {
        final PipedOutputStream pipeIn  = new PipedOutputStream();
        final PipedOutputStream pipeOut = new PipedOutputStream();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new BibliotecaApp(new PipedInputStream(pipeIn), new PrintStream(pipeOut)).run();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

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
        assertEquals("1) List books", s.nextLine());
        assertEquals(">", s.next(">"));
    }

    @Test
    public void testListOfBooks() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("1\n");
        assertEquals("Real World Haskell | O'Sullivan, Goerzen, and Stuart | 2009", s.nextLine());
        assertEquals("Java Persistence with Hibernate | Bauer, and King | 2007 ", s.nextLine());
    }


    @Test
    public void testInvalidMenuOption() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("2\n");
        assertEquals("Select a valid option!", s.nextLine());
    }

    @Test
    public void testInvalidMenuOption_ReEnterChoice() throws IOException {
        while (! s.hasNext(">")) { s.nextLine(); }
        s.skip("> ");
        provideInput("2\n");
        s.nextLine();
        testListOfBooks();
    }

}
