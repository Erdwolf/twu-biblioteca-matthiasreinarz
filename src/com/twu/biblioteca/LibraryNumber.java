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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibraryNumber that = (LibraryNumber) o;

        if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;
        if (suffix != null ? !suffix.equals(that.suffix) : that.suffix != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prefix != null ? prefix.hashCode() : 0;
        result = 31 * result + (suffix != null ? suffix.hashCode() : 0);
        return result;
    }
}
