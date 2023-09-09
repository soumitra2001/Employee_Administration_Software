package com.example.Employee.Management.System.repositories;

import com.example.Employee.Management.System.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo extends JpaRepository<Employee,Integer> {

}
