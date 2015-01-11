package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    private Scanner s;

    @Before
    public void captureOutput() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new BibliotecaApp(new PrintStream(baos)).run();
        s = new Scanner(baos.toString());
    }

    @Test
    public void testWelcomeMessage() {
        assertEquals("Welcome to Biblioteca!", s.nextLine());
    }

    @Test
    public void testListOfBooks() {
        s.nextLine();
        assertEquals("Real World Haskell", s.nextLine());
        assertEquals("Java Persistence with Hibernate", s.nextLine());
    }

}
