package com.global.groupware.employeewise.services.implementation;

import com.global.groupware.employeewise.entities.Employee;
import com.global.groupware.employeewise.repository.EmployeeRepository;
import com.global.groupware.employeewise.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees(int page, int pageSize, String sortBy) {
        // Logic to fetch employees with pagination and sorting
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Page<Employee> employeePage = employeeRepository.findAll(pageRequest);
        return employeePage.getContent();
    }

    @Override
    public void deleteEmployeeById(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Employee updateEmployeeById(String employeeId, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setReportsTo(updatedEmployee.getReportsTo());
        existingEmployee.setProfileImage(updatedEmployee.getProfileImage());
        return employeeRepository.save(existingEmployee);
    }

    //Get nth Level Manager of an Employee
    @Override
    public Employee getNthLevelManager(String employeeId, int level) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found."));
        return findNthLevelManager(employee, level);
    }

    //find the manager
    private Employee findNthLevelManager(Employee employee, int level) {
        if (level == 0 || employee.getReportsTo() == null) {
            return employee;
        }
        return findNthLevelManager(employeeRepository.findById(employee.getReportsTo()).
                        orElseThrow(() -> new RuntimeException("Manager Not Found.")), level - 1);
    }
}
