package edu.icet.senuka.service.impl;

import edu.icet.senuka.dto.Employee;
import edu.icet.senuka.entity.EmployeeEntity;
import edu.icet.senuka.errors.EmailNotUniqueException;
import edu.icet.senuka.errors.EmployeeDoesNotExistException;
import edu.icet.senuka.errors.IdNullException;
import edu.icet.senuka.repository.EmployeeRepository;
import edu.icet.senuka.service.EmployeeService;
import edu.icet.senuka.util.DepartmentType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final ModelMapper mapper;

    @Override
    public Employee add(Employee employee) throws EmailNotUniqueException {

        try {
            if (getByEmail(employee.getEmail()).isPresent()) {
                throw new EmailNotUniqueException();
            }
        } catch (EmployeeDoesNotExistException ignored) {}

        EmployeeEntity employeeEntity = repository.save(mapper.map(employee, EmployeeEntity.class));

        return mapper.map(employeeEntity, Employee.class);
    }

    @Override
    public Employee update(Employee employee) throws IdNullException, EmployeeDoesNotExistException {
        if (employee.getId() == null) {
            throw new IdNullException();
        }

        if (repository.findById(employee.getId()).isEmpty()) {
            throw new EmployeeDoesNotExistException(employee.getId());
        }

        return mapper.map(repository.save(mapper.map(employee, EmployeeEntity.class)), Employee.class);
    }

    @Override
    public Boolean deleteByID(Long id) throws EmployeeDoesNotExistException {
        if (repository.findById(id).isEmpty()) {
            throw new EmployeeDoesNotExistException(id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Employee> getAllByName(String name) {
        List<EmployeeEntity> all = repository.findAllByNameContainingIgnoreCase(name);

        return all.stream()
                .map(employeeEntity -> mapper.map(employeeEntity, Employee.class))
                .toList();
    }

    @Override
    public List<Employee> getAllByDepartment(DepartmentType departmentType) {
        List<EmployeeEntity> all = repository.findAllByDepartment(departmentType);

        return all.stream()
                .map(employeeEntity -> mapper.map(employeeEntity, Employee.class))
                .toList();
    }

    @Override
    public Optional<Employee> getByEmail(String email) throws EmployeeDoesNotExistException {
        Optional<EmployeeEntity> employee = repository.findByEmailIgnoreCase(email);

        if (employee.isEmpty()) {
            throw new EmployeeDoesNotExistException(email);
        }

        return Optional.of(mapper.map(employee.get(), Employee.class));
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll()
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity, Employee.class))
                .toList();
    }
}
