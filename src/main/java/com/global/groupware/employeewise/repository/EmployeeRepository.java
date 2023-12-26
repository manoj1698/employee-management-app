package com.global.groupware.employeewise.repository;

import com.global.groupware.employeewise.entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee,String> {
}
