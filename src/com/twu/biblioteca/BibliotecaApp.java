package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class BibliotecaApp {

    private Scanner in;
    private PrintStream out;

    public BibliotecaApp(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void run() {
        out.println("Welcome to Biblioteca!");
        out.println("1) List books");
        out.print("> ");
        assert 1 == in.nextInt();
        out.println("Real World Haskell | O'Sullivan, Goerzen, and Stuart | 2009");
        out.println("Java Persistence with Hibernate | Bauer, and King | 2007 ");
    }

    public static void main(String[] args) {
        new BibliotecaApp(System.in,System.out).run();
    }

}
