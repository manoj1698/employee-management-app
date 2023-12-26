package com.global.groupware.employeewise.controllers;

import com.global.groupware.employeewise.entities.Employee;
import com.global.groupware.employeewise.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //Add Employee to a Database:
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    //Get All Employees:
    @GetMapping
    public ResponseEntity<List<Employee>> getALL(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "employeeName") String sortBy
    ) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, pageSize, sortBy));
    }

    //Delete Employee by ID:
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmpById(@PathVariable String employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.noContent().build();
    }

    //Update Employee by ID:
    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable String employeeId,
            @Valid @RequestBody Employee updatedEmployee
    ) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, updatedEmployee));
    }

    //Get nth Level Manager of an Employee
    @GetMapping("/manager/{employeeId}")
    public ResponseEntity<Employee> getNthLevelManager(
            @PathVariable String employeeId,
            @RequestParam int level
    ) {
        return ResponseEntity.ok(employeeService.getNthLevelManager(employeeId, level));
    }
}
