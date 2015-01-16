package com.twu.biblioteca;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LibraryNumberTest {

    @Test
    public void testParser_Valid_1() throws LibraryNumber.InvalidFormat {
        LibraryNumber libraryNumber = LibraryNumber.parse("555-1234");
        assertNotNull("parse(String) Should return non-null LibraryNumber!", libraryNumber);
        assertEquals("555", libraryNumber.prefix());
        assertEquals("1234", libraryNumber.suffix());
    }

    @Test
    public void testParser_Valid_2() throws LibraryNumber.InvalidFormat {
        LibraryNumber libraryNumber = LibraryNumber.parse("ABC-22AF");
        assertNotNull("parse(String) Should return non-null LibraryNumber!", libraryNumber);
        assertEquals("ABC", libraryNumber.prefix());
        assertEquals("22AF", libraryNumber.suffix());
    }

    @Test(expected = LibraryNumber.InvalidFormat.class)
    public void testParser_Invalid_MissingDash() throws LibraryNumber.InvalidFormat {
        LibraryNumber.parse("ABC22AF");
    }

    @Test(expected = LibraryNumber.InvalidFormat.class)
    public void testParser_Invalid__TooManyDashes() throws LibraryNumber.InvalidFormat {
        LibraryNumber.parse("AB-C22-AF");
    }

    @Test(expected = LibraryNumber.InvalidFormat.class)
    public void testParser_Invalid__PrefixTooShort() throws LibraryNumber.InvalidFormat {
        LibraryNumber.parse("AB-C22AF");
    }
    @Test(expected = LibraryNumber.InvalidFormat.class)
    public void testParser_Invalid__PrefixTooLong() throws LibraryNumber.InvalidFormat {
        LibraryNumber.parse("ABC2-2AF");
    }

    @Test(expected = LibraryNumber.InvalidFormat.class)
    public void testParser_Invalid__SuffixTooShort() throws LibraryNumber.InvalidFormat {
        LibraryNumber.parse("ABC-22A");
    }
    @Test(expected = LibraryNumber.InvalidFormat.class)
    public void testParser_Invalid__SuffixTooLong() throws LibraryNumber.InvalidFormat {
        LibraryNumber.parse("ABC-22AFA");
    }

}
