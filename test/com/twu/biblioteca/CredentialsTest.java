package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class CredentialsTest {

    @Test
    public void testEquals_Same() throws LibraryNumber.InvalidFormat {
        assertCredentialsEquality("555-ABCD", "abcd");
        assertCredentialsEquality("123-1234", "qwerty");
        assertCredentialsEquality("xxx-xxxx", "password");
    }

    private void assertCredentialsEquality(String libraryNumberAsString, String password) throws LibraryNumber.InvalidFormat {
        Credentials credentials1 = new Credentials(LibraryNumber.parse(libraryNumberAsString), password);
        Credentials credentials2 = new Credentials(LibraryNumber.parse(libraryNumberAsString), password);
        assertTrue("Credentials containing the same data should be equal!", credentials1.equals(credentials2));
    }


    @Test
    public void testEquals_DifferentPassword() throws LibraryNumber.InvalidFormat {
        assertCredentialsInequality_Password("555-ABCD", "abcd", "acbd");
        assertCredentialsInequality_Password("123-1234", "qwerty", "password");
        assertCredentialsInequality_Password("xxx-xxxx", "password", "qwerty");
    }

    private void assertCredentialsInequality_Password(String libraryNumberAsString, String password1, String password2) throws LibraryNumber.InvalidFormat {
        Credentials credentials1 = new Credentials(LibraryNumber.parse(libraryNumberAsString), password1);
        Credentials credentials2 = new Credentials(LibraryNumber.parse(libraryNumberAsString), password2);
        assertFalse("Credentials containing different passwords should not be equal!", credentials1.equals(credentials2));
    }


    @Test
    public void testEquals_DifferentLibraryNumber() throws LibraryNumber.InvalidFormat {
        assertCredentialsInequality_LibraryNumber("555-ABCD", "555-ACDB", "abcd");
        assertCredentialsInequality_LibraryNumber("123-1234", "132-1234", "password");
        assertCredentialsInequality_LibraryNumber("xxx-xxxx", "XXX-XXXX", "qwerty");
    }

    private void assertCredentialsInequality_LibraryNumber(String libraryNumberAsString1, String libraryNumberAsString2, String password) throws LibraryNumber.InvalidFormat {
        Credentials credentials1 = new Credentials(LibraryNumber.parse(libraryNumberAsString1), password);
        Credentials credentials2 = new Credentials(LibraryNumber.parse(libraryNumberAsString2), password);
        assertFalse("Credentials containing different library numbers should not be equal!", credentials1.equals(credentials2));
    }

}
