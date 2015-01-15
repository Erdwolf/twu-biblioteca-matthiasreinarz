package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    private Biblioteca biblioteca = new Biblioteca();

    private void run() {
        printWelcomeMessage();

        while(true) {
            try {
                handleUserInput();
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to Biblioteca!");
        listBooks();
    }

    private void listBooks() {
        for (Book book : biblioteca.availableBooks()) {
            System.out.println(book);
        }
    }

    private void handleUserInput() {
        System.out.print("> ");
        int menuOption = new Scanner(System.in).nextInt();
    }

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }

}
