package com.example.Employee.Management.System.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employee_id;

    private String first_name;

    private String last_name;

    @Email
    private String email;

    @Length(min = 10,max = 14,message = "Enter a valid Phone no")
    private String phone_no;

    private String job_role;

    private int salary;

}
