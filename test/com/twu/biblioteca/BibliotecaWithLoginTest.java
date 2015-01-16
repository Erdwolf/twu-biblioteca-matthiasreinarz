package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void testCheckoutBookWithLogin_LoginRequired() throws NoSuchBook {
        biblioteca.checkOutBookByName("Does it matter?");
    }

    @Test
    public void testCheckoutBookWithLogin() throws NoSuchBook, LoginFailed {
        biblioteca.login(credentials);
        biblioteca.checkOutBookByName("Real World Haskell");
        assertEquals("Number of available books", 1, biblioteca.availableBooks().size());
    }

    @Test(expected = LoginRequired.class)
    public void testReturnBookWithLogin_LoginRequired() throws NoSuchBook {
        biblioteca.returnBookByName("Does it matter?");
    }

    @Test
    public void testReturnBookWithLogin() throws NoSuchBook, LoginFailed {
        biblioteca.login(credentials);
        biblioteca.checkOutBookByName("Real World Haskell");
        assertEquals("Number of available books", 1, biblioteca.availableBooks().size());
        biblioteca.returnBookByName("Real World Haskell");
        assertEquals("Number of available books", 2, biblioteca.availableBooks().size());
    }

    @Test
    public void testUserInformation() throws LoginFailed {
        biblioteca.login(credentials);
        UserInfo userInfo = biblioteca.userInformation();
        assertNotNull("User information should not be null!", userInfo);
        assertEquals("Matthias Reinarz", userInfo.name());
        assertEquals("mreinarz@thoughtworks.com", userInfo.emailAddress());
        assertEquals("+44", userInfo.phoneNumber());
    }

    @Test
    public void testUserInformation_DifferentUser() throws LoginFailed, LibraryNumber.InvalidFormat {
        biblioteca.login(new Credentials(LibraryNumber.parse("666-ABCD"), "fish"));
        UserInfo userInfo = biblioteca.userInformation();
        assertNotNull("User information should not be null!", userInfo);
        assertEquals("Max Mustermann", userInfo.name());
        assertEquals("max.mustermann@example.com", userInfo.emailAddress());
        assertEquals("+445551234", userInfo.phoneNumber());
    }

}
