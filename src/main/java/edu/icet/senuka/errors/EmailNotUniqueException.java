package edu.icet.senuka.errors;

public class EmailNotUniqueException extends Exception {
    public EmailNotUniqueException() {
        super("Email is not unique! Please try again");
    }
}
