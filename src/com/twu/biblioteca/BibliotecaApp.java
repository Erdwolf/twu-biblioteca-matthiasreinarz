package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class BibliotecaApp {

    private static final List<Book> ALL_BOOKS = new ArrayList() {{
        add(new Book("Real World Haskell", "O'Sullivan, Goerzen, and Stuart", 2009));
        add(new Book("Java Persistence with Hibernate", "Bauer, and King", 2007));
    }};

    private static final List<String> ALL_MOVIES = new ArrayList() {{
        add("Groundhog Day | 1993 | Harold Ramis | 10");
        add("Hotel | 2004 | Jessica Hausner | 1");
        add("The Hobbit: The Battle of the Five Armies | 2014 | Peter Jackson | unrated");
    }};

    private Scanner in;
    private PrintStream out;
    private HashSet<Book> checkedOutBooks = new HashSet<Book>();

    public BibliotecaApp(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void run() {
        out.println("Welcome to Biblioteca!");
        out.println("0) Quit");
        out.println("1) List books");
        out.println("2) Checkout book");
        out.println("3) Return book");
        out.println("4) List movies");
        out.println("5) Checkout movie");
        out.println("6) User information");
        boolean done = false;
        while(! done) {
            out.print("> ");
            int menuOption = in.nextInt();
            if (menuOption == 0) {
                done = true;
            } else if (menuOption == 1) {
                listBooks();
            } else if (menuOption == 2) {
                checkOutBook();
            } else if (menuOption == 3) {
                returnBook();
            } else if (menuOption == 4) {
                listMovies();
            } else if (menuOption == 5) {
                checkOutMovie();
            } else if (menuOption == 6) {
                userInformation();
            } else {
                out.println("Select a valid option!");
            }
        }
    }

    private void userInformation() {
        if(checkCredentials()) {
            out.println("Matthias Reinarz | mreinarz@thoughtworks.com | +44");
        }
    }

    private void checkOutMovie() {
        String name = getName("movie");
        // Nothing to be done with it so far
    }

    private void listMovies() {
        for (String movie : ALL_MOVIES) {
            out.println(movie);
        }
    }

    private void returnBook() {
        String name = getName("book");
        Book book = findBookByName(name);
        if (book == null || ! checkedOutBooks.contains(book)) {
            out.println("That is not a valid book to return.");
            return;
        }
        if(checkCredentials()) {
            checkedOutBooks.remove(book);
            out.println("Thank you for returning the book.");
        }
    }

    private void checkOutBook() {
        String name = getName("book");
        Book book = findBookByName(name);
        if (book == null || checkedOutBooks.contains(book)) {
            out.println("That book is not available.");
            return;
        }
        if (checkCredentials()) {
            checkedOutBooks.add(book);
            out.println("Thank you! Enjoy the book");
        }
    }

    private boolean checkCredentials() {
        out.print("Library number> ");
        in.useDelimiter("\\n");
        String libraryNumber = in.next();
        in.reset();
        if(! "555-1234".equals(libraryNumber)) {
            out.println("Login failure: Unknown library number.");
            return false;
        }
        in.useDelimiter("\\n");
        out.print("Password> ");
        String password = in.next();
        in.reset();
        if(! "1234".equals(password)) {
            out.println("Login failure: Wrong password.");
            return false;
        }
        return true;
    }

    private void listBooks() {
        for (Book book : ALL_BOOKS) {
            if(! checkedOutBooks.contains(book)) {
                out.println(book);
            }
        }
    }

    private String getName(String thing) {
        out.print("Name of "+thing+"> ");
        in.useDelimiter("\\n");
        String name = in.next();
        in.reset();
        return name;
    }

    private Book findBookByName(String name) {
        for (Book book : ALL_BOOKS) {
            if(name.equals(book.name())) {
                return book;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new BibliotecaApp(System.in,System.out).run();
    }

}
