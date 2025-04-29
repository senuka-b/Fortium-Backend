package edu.icet.senuka.service;

import edu.icet.senuka.dto.Employee;
import edu.icet.senuka.util.DepartmentType;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee add(Employee employee);
    Employee update(Employee employee);
    Boolean deleteByID(Long id);

    Optional<List<Employee>> getAllByName(String name);
    Optional<List<Employee>> getAllByDepartment(DepartmentType departmentType);
    Optional<Employee> getByEmail(String email);

    List<Employee> getAll();
}
