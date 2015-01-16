package com.twu.biblioteca;


public class LibraryNumber {

    public static class InvalidFormat extends Exception {}

    private final String prefix;
    private final String suffix;

    private LibraryNumber(String prefix, String suffix) throws InvalidFormat {
        if(prefix.length() != 3 || suffix.length() != 4) {
            throw new InvalidFormat();
        }
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public static LibraryNumber parse(String string) throws InvalidFormat {
        String[] parts = string.split("-");
        if(parts.length != 2) {
            throw new InvalidFormat();
        }
        return new LibraryNumber(parts[0], parts[1]);
    }

    public String prefix() {
        return prefix;
    }

    public String suffix() {
        return suffix;
    }

    @Override
    public String toString() {
        return prefix() + "-" + suffix();
    }

}
