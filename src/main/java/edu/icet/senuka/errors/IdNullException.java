package edu.icet.senuka.errors;

public class IdNullException extends Exception{
    public IdNullException() {
        super("ID cannot be null when updating an employee!");
    }
}
