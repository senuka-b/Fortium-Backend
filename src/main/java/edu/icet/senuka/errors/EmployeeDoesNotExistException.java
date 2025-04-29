package edu.icet.senuka.errors;

public class EmployeeDoesNotExistException extends Exception{
    public EmployeeDoesNotExistException(Long id) {
        super("Employee with the ID " + id + " does not exist!");
    }
}
