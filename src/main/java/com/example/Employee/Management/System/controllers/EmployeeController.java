package com.example.Employee.Management.System.controllers;

import com.example.Employee.Management.System.models.Employee;
import com.example.Employee.Management.System.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/profile/{id}")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody Employee employee, @PathVariable int id){
        return employeeService.addEmployee(employee,id);
    }

    @PutMapping("/re_profile")
    public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee,@RequestParam int id){
        return employeeService.updateEmployee(employee,id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        Employee employee=employeeService.getEmployeeById(id);
        if(employee==null)return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
}

