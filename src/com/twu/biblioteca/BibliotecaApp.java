package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    private BibliotecaWithLogin biblioteca = new BibliotecaWithLogin();

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
            catch (LoginRequired loginRequired) {
                login();
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
        System.out.println("3) Return book");
        System.out.println("4) List movies");
        System.out.println("5) Checkout movie");
        System.out.println("6) Return movie");
        System.out.println("7) User Information");
    }

    private void login() {
        System.out.println("Login required:");
        try {
            System.out.print("Library number> ");
            LibraryNumber libraryNumber = LibraryNumber.parse(new Scanner(System.in).nextLine());
            System.out.print("Password> ");
            String password = new Scanner(System.in).nextLine();
            biblioteca.login(new Credentials(libraryNumber, password));
        }
        catch (LibraryNumber.InvalidFormat invalidFormat) {
            System.out.println("That is not a valid library number! Library numbers have the format xxx-xxxx.");
            return;
        }
        catch (LoginFailed loginFailed) {
            System.out.println("Login failed!");
            return;
        }
        System.out.println("Login successful.2");
    }

    private void listBooks() {
        for (Book book : biblioteca.availableBooks()) {
            System.out.println(book);
        }
    }

    private void checkoutBook() {
        System.out.print("Name of the book> ");
        String bookName = new Scanner(System.in).nextLine();
        try {
            biblioteca.checkOutBookByName(bookName);
            System.out.println("Thank you! Enjoy the book");
        } catch (NoSuchBook noSuchBook) {
            System.out.println("That book is not available.");
        }
    }

    private void returnBook() {
        System.out.print("Name of the book> ");
        String bookName = new Scanner(System.in).nextLine();
        try {
            biblioteca.returnBookByName(bookName);
            System.out.println("Thank you for returning the book.");
        } catch (NoSuchBook noSuchBook) {
            System.out.println("That is not a valid book to return.");
        }
    }

    private void listMovies() {
        for (Movie movie : biblioteca.availableMovies()) {
            System.out.println(movie);
        }
    }

    private void checkoutMovie() {
        System.out.print("Name of the movie> ");
        String bookName = new Scanner(System.in).nextLine();
        try {
            biblioteca.checkOutMovieByName(bookName);
            System.out.println("Thank you! Enjoy the movie");
        } catch (NoSuchMovie noSuchMovie) {
            System.out.println("That movie is not available.");
        }
    }

    private void returnMovie() {
        System.out.print("Name of the movie> ");
        String movieName = new Scanner(System.in).nextLine();
        try {
            biblioteca.returnMovieByName(movieName);
            System.out.println("Thank you for returning the movie.");
        } catch (NoSuchMovie noSuchMovie) {
            System.out.println("That is not a valid movie to return.");
        }
    }

    private void userInformation() {
        System.out.println(biblioteca.userInformation());
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
            case 3:
                returnBook();
                break;
            case 4:
                listMovies();
                break;
            case 5:
                checkoutMovie();
                break;
            case 6:
                returnMovie();
                break;
            case 7:
                userInformation();
                break;
            default:
                throw new InvalidMenuOption();
        }
    }

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }

}
