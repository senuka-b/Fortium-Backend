package edu.icet.senuka.repository;

import edu.icet.senuka.entity.EmployeeEntity;
import edu.icet.senuka.util.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAllByDepartment(DepartmentType departmentType);
    List<EmployeeEntity> findAllByNameContainingIgnoreCase(String name);
    Optional<EmployeeEntity> findByEmailIgnoreCase(String email);
}
