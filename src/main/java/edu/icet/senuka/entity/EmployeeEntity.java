package edu.icet.senuka.entity;

import edu.icet.senuka.util.DepartmentType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "employee")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private DepartmentType department;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
