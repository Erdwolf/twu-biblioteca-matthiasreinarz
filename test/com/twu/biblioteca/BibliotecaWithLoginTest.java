package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

public class BibliotecaWithLoginTest {

    private BibliotecaWithLogin biblioteca;
    private Credentials credentials;

    @Before
    public void setUp() throws LibraryNumber.InvalidFormat {
        biblioteca = new BibliotecaWithLogin();
        credentials = new Credentials(LibraryNumber.parse("555-1234"), "1234");
    }

    @Test
    public void testLogin() {
        biblioteca.login(credentials);
    }
}
