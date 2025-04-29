package edu.icet.senuka.service;

import edu.icet.senuka.dto.Employee;
import edu.icet.senuka.errors.EmailNotUniqueException;
import edu.icet.senuka.errors.EmployeeDoesNotExistException;
import edu.icet.senuka.errors.IdNullException;
import edu.icet.senuka.util.DepartmentType;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee add(Employee employee) throws EmailNotUniqueException;
    Employee update(Employee employee) throws IdNullException, EmployeeDoesNotExistException;
    Boolean deleteByID(Long id) throws EmployeeDoesNotExistException;

    List<Employee> getAllByName(String name);
    List<Employee> getAllByDepartment(DepartmentType departmentType);
    Optional<Employee> getByEmail(String email);

    List<Employee> getAll();
}
