package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static final List<Book> ALL_BOOKS = new ArrayList() {{
        add(new Book("Real World Haskell", "O'Sullivan, Goerzen, and Stuart", 2009));
        add(new Book("Java Persistence with Hibernate", "Bauer, and King", 2007));
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
        boolean done = false;
        while(! done) {
            out.print("> ");
            int menuOption = in.nextInt();
            if (menuOption == 0) {
                done = true;
            } else if (menuOption == 1) {
                for (Book book : ALL_BOOKS) {
                    if(! checkedOutBooks.contains(book)) {
                        out.println(book);
                    }
                }
            } else if (menuOption == 2) {
                out.print("Name of book> ");
                in.useDelimiter("\\n");
                String name = in.next();
                in.reset();
                Book book = findBookByName(name);
                checkedOutBooks.add(book);
                out.println("Thank you! Enjoy the book");
            } else {
                out.println("Select a valid option!");
            }
        }
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
