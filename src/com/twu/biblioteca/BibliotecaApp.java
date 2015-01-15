package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    private Biblioteca biblioteca = new Biblioteca();

    private void run() {
        printWelcomeMessage();

        while(true) {
            try {
                handleUserInput();
            }
            catch (UserQuit userQuit) {
                return;
            }
            catch (InvalidMenuOption invalidMenuOption) {
                System.out.println("Select a valid option!");
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to Biblioteca!");
        displayMenu();
    }

    private void displayMenu() {
        System.out.println("0) Quit");
        System.out.println("1) List books");
        System.out.println("2) Checkout book");
    }

    private void listBooks() {
        for (Book book : biblioteca.availableBooks()) {
            System.out.println(book);
        }
    }

    private void checkoutBook() {
        System.out.print("> ");
        String bookName = new Scanner(System.in).nextLine();
        try {
            biblioteca.checkOutBookByName(bookName);
            System.out.println("Thank you! Enjoy the book");
        } catch (NoSuchBook noSuchBook) {
            System.out.println("That book is not available.");
        }
    }

    private void handleUserInput() throws UserQuit, InvalidMenuOption {
        System.out.print("> ");

        Scanner userInput = new Scanner(System.in);
        if(! userInput.hasNextInt()) {
            throw new InvalidMenuOption();
        }
        int menuOption = userInput.nextInt();

        switch(menuOption){
            case 0:
                throw new UserQuit();
            case 1:
                listBooks();
                break;
            case 2:
                checkoutBook();
                break;
            default:
                throw new InvalidMenuOption();
        }
    }

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }

}
