package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

public class BibliotecaWithLoginTest {

    private static final Credentials CREDENTIALS = new Credentials(LibraryNumber.parse("555-1234"), "1234");
    private static final String password = "1234";

    private BibliotecaWithLogin biblioteca;

    @Before
    public void setUp() {
        biblioteca = new BibliotecaWithLogin();
    }

    @Test
    public void testLogin() {
        biblioteca.login(CREDENTIALS);
    }
}
