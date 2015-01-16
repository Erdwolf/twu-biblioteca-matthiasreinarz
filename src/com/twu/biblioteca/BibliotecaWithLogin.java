package com.twu.biblioteca;


import java.util.ArrayList;
import java.util.List;

public class BibliotecaWithLogin extends Biblioteca {

    private static final List<Credentials> VALID_CREDENTIALS = new ArrayList<Credentials>() {{
        try {
            add(new Credentials(LibraryNumber.parse("555-1234"),"1234"));
        } catch (LibraryNumber.InvalidFormat invalidFormat) {
            throw new RuntimeException(invalidFormat);
        }
    }};

    private boolean loggedIn = false;

    public void login(Credentials credentials) throws LoginFailed {
        if(credentials == null || ! VALID_CREDENTIALS.contains(credentials)) {
            throw new LoginFailed();
        }
        loggedIn = true;
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
        if(! loggedIn) {
            throw new LoginRequired();
        }
    }
}
