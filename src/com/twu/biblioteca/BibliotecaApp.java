package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private void run() {
        Biblioteca biblioteca = new Biblioteca();

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
    }

    private void handleUserInput() {
        System.out.print("> ");
        int menuOption = new Scanner(System.in).nextInt();
    }

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }

}
