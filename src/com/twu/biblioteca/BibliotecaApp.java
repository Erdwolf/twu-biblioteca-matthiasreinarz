package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;

public class BibliotecaApp {

    private PrintStream out;

    public BibliotecaApp(PrintStream out) {
        this.out = out;
    }

    public void run() {
        out.println("Welcome to Bibliotheca!");
    }

    public static void main(String[] args) {
        new BibliotecaApp(System.out).run();
    }

}
