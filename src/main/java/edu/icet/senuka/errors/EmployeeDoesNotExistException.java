package edu.icet.senuka.errors;

public class EmployeeDoesNotExistException extends Exception{
    public EmployeeDoesNotExistException(Long id) {
        super("Employee with the ID " + id + " does not exist!");
    }

    public EmployeeDoesNotExistException(String email) {
        super("Employee with the email " + email + " does not exist!");
    }
}
