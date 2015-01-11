package com.twu.biblioteca;

import java.io.PrintStream;

public class BibliotecaApp {

    private PrintStream out;

    public BibliotecaApp(PrintStream out) {
        this.out = out;
    }

    public void run() {
        out.println("Welcome to Biblioteca!");
        out.println("Real World Haskell");
        out.println("Java Persistence with Hibernate");
    }

    public static void main(String[] args) {
        new BibliotecaApp(System.out).run();
    }

}
