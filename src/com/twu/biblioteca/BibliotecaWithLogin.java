package com.twu.biblioteca;


public class BibliotecaWithLogin extends Biblioteca {

    boolean loggedIn = false;

    public void login(Credentials credentials) {
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
