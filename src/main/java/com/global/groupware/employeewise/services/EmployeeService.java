package com.global.groupware.employeewise.services;

import com.global.groupware.employeewise.entities.Employee;

import java.util.List;

public interface EmployeeService {
    String addEmployee(Employee employee);

    List<Employee> getAllEmployees(int page, int pageSize, String sortBy);

    void deleteEmployeeById(String employeeId);

    Employee updateEmployeeById(String employeeId, Employee updatedEmployee);

    Employee getNthLevelManager(String employeeId, int level);
}
