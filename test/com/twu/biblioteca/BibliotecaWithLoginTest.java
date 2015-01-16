package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BibliotecaWithLoginTest {

    private BibliotecaWithLogin biblioteca;
    private Credentials credentials;

    @Before
    public void setUp() throws LibraryNumber.InvalidFormat {
        biblioteca = new BibliotecaWithLogin();
        credentials = new Credentials(LibraryNumber.parse("555-1234"), "1234");
    }

    @Test
    public void testLogin_Success() throws LoginFailed {
        biblioteca.login(credentials);
    }

    @Test(expected = LoginFailed.class)
    public void testLogin_Failure_NullCredentials() throws LoginFailed {
        biblioteca.login(null);
    }

    @Test(expected = LoginFailed.class)
    public void testLogin_Failure_InvalidCredentials() throws LoginFailed {
        biblioteca.login(new Credentials(null, null));
    }

    @Test(expected = LoginFailed.class)
    public void testLogin_Failure_WrongPassword() throws LoginFailed, LibraryNumber.InvalidFormat {
        biblioteca.login(new Credentials(LibraryNumber.parse("555-1234"), "1324"));
    }


    @Test(expected = LoginRequired.class)
    public void checkoutBookWithLogin_LoginRequired() throws NoSuchBook {
        biblioteca.checkOutBookByName("Does it matter?");
    }

    @Test
    public void checkoutBookWithLogin() throws NoSuchBook, LoginFailed {
        biblioteca.login(credentials);
        biblioteca.checkOutBookByName("Real World Haskell");
        assertEquals("Number of available books", 1, biblioteca.availableBooks().size());
    }

    @Test(expected = LoginRequired.class)
    public void returnBookWithLogin_LoginRequired() throws NoSuchBook {
        biblioteca.returnBookByName("Does it matter?");
    }

    @Test
    public void returnBookWithLogin() throws NoSuchBook, LoginFailed {
        biblioteca.login(credentials);
        biblioteca.checkOutBookByName("Real World Haskell");
        assertEquals("Number of available books", 1, biblioteca.availableBooks().size());
        biblioteca.returnBookByName("Real World Haskell");
        assertEquals("Number of available books", 2, biblioteca.availableBooks().size());
    }

}
