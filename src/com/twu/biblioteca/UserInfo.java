package com.twu.biblioteca;


public class UserInfo {

    private final String name;
    private final String emailAddress;
    private final String phoneNumber;

    public UserInfo(String name, String emailAddress, String phoneNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public String name() {
        return name;
    }

    public String emailAddress() {
        return emailAddress;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return name + " | " + emailAddress + " | " + phoneNumber;
    }

}
