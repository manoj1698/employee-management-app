package com.global.groupware.employeewise.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("emp")
public class Employee {
    @Id
    private String employeeId;
    private String employeeName;
    @Size(min = 10, max = 10, message = "phone_no should be exact 10 characters.")
    private String phoneNumber;
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    private String reportsTo;
    @URL
    private String profileImage;

}
