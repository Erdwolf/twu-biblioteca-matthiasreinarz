package com.twu.biblioteca;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void testWelcomeMessage() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new BibliotecaApp(new PrintStream(baos)).run();
        assertEquals("Welcome to Bibliotheca!\n", baos.toString());
    }
}
