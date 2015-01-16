package com.twu.biblioteca;


public class Rating {

    private static final Rating[] RATINGS = new Rating[10];
    static {
        for (int i=1; i<=10; i++) {
            RATINGS[i-1] = new Rating(Integer.toString(i));
        }
    }

    public static Rating unrated = new Rating("unrated");
    public static Rating worst = rating(1);
    public static Rating best  = rating(10);

    public static Rating rating(int numberFrom1to10) {
        return RATINGS[numberFrom1to10 - 1];
    }

    private Rating(String displayString) {
        this.displayString = displayString;
    }
    private final String displayString;

    @Override
    public String toString() {
        return displayString;
    }
}
