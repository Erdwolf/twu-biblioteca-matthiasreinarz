package com.twu.biblioteca;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaWithLogin extends Biblioteca {

    private static final List<Credentials> VALID_CREDENTIALS;
    private static final Map<LibraryNumber,UserInfo> USER_INFO;

    static {
        try {
            VALID_CREDENTIALS = new ArrayList<Credentials>() {{
                add(new Credentials(LibraryNumber.parse("555-1234"), "1234"));
                add(new Credentials(LibraryNumber.parse("666-ABCD"), "fish"));
            }};
            USER_INFO = new HashMap<LibraryNumber, UserInfo>() {{
                put(LibraryNumber.parse("555-1234"), new UserInfo("Matthias Reinarz", "mreinarz@thoughtworks.com", "+44"));
                put(LibraryNumber.parse("666-ABCD"), new UserInfo("Max Mustermann", "max.mustermann@example.com", "+445551234"));
            }};
        } catch (LibraryNumber.InvalidFormat invalidFormat) {
            throw new RuntimeException(invalidFormat);
        }
    }

    private LibraryNumber loggedInLibraryNumber = null;

    public void login(Credentials credentials) throws LoginFailed {
        if(credentials == null || ! VALID_CREDENTIALS.contains(credentials)) {
            throw new LoginFailed();
        }
        loggedInLibraryNumber = credentials.libraryNumber();
    }

    @Override
    public void checkOutBookByName(String name) throws NoSuchBook {
        requireLogin();
        super.checkOutBookByName(name);
    }

    @Override
    public void returnBookByName(String name) throws NoSuchBook {
        requireLogin();
        super.returnBookByName(name);
    }

    private void requireLogin() {
        if(null == loggedInLibraryNumber) {
            throw new LoginRequired();
        }
    }

    public UserInfo userInformation() {
        requireLogin();
        return USER_INFO.get(loggedInLibraryNumber);
    }
}
