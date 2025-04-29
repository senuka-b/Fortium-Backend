package edu.icet.senuka.controller;

import edu.icet.senuka.dto.Employee;
import edu.icet.senuka.errors.EmployeeDoesNotExistException;
import edu.icet.senuka.errors.IdNullException;
import edu.icet.senuka.service.EmployeeService;
import edu.icet.senuka.util.DepartmentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(required = false) DepartmentType department,
            @RequestParam(required = false) String name
            ) {

        List<Employee> all;

        if (department != null) {
            all = employeeService.getAllByDepartment(department);
        } else if (name != null) {
            all = employeeService.getAllByName(name);
        } else {
            all = employeeService.getAll();
        }


        if (all.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(all);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
        return employeeService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }


    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {

        try {
            Employee update = employeeService.update(employee);

            return ResponseEntity.ok(update);
        } catch (IdNullException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (EmployeeDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            Boolean delete = employeeService.deleteByID(id);

            return ResponseEntity.ok(delete);

        } catch (EmployeeDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
