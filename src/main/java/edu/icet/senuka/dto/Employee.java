package edu.icet.senuka.dto;

import edu.icet.senuka.util.DepartmentType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;

    @NotEmpty
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Must contain only letters and spaces")
    @Size(max = 100, message = "Name cannot be greater than 100 characters")
    private String name;

    @Email
    @NotEmpty
    private String email;

    @NotNull
    private DepartmentType department;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;

}
