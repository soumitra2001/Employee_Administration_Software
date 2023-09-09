package com.example.Employee.Management.System.services;

import com.example.Employee.Management.System.models.Employee;
import com.example.Employee.Management.System.repositories.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    IEmployeeRepo employeeRepo;

    public ResponseEntity<String> addEmployee(Employee employee,int id) {
        ResponseEntity<String> response;
        if(id!=0) {
            Employee employee1 = getEmployeeById(id);
            if (employee1 == null) response = new ResponseEntity<>("Invalid user", HttpStatus.BAD_REQUEST);
            if (employee1.getJob_role().substring(0,2).equalsIgnoreCase("HR")) {
                Employee emp = employeeRepo.save(employee);
                if (emp == null)
                    response = new ResponseEntity<>("Employee profile was not created", HttpStatus.BAD_REQUEST);
                else response = new ResponseEntity<>("Employee profile successfully created", HttpStatus.CREATED);
            } else response = new ResponseEntity<>("Not a valid user to add Employee Profile", HttpStatus.BAD_REQUEST);
        }else {
            employeeRepo.save(employee);
            response=new ResponseEntity<>("Employee profile successfully created",HttpStatus.CREATED);
        }

        return response;
    }

    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id).get();
    }


    public ResponseEntity<String> updateEmployee(Employee employee, int id) {
        ResponseEntity<String> response;
        Employee emp1=getEmployeeById(id);
        int empId=employee.getEmployee_id();
        Employee emp2=getEmployeeById(empId);
        if(emp1==null || emp2==null){
            response=new ResponseEntity<>("Invalid User",HttpStatus.BAD_REQUEST);
            return response;
        }
        emp2.setFirst_name(employee.getFirst_name());
        emp2.setLast_name(employee.getLast_name());
        emp2.setEmail(employee.getEmail());
        emp2.setPhone_no(employee.getPhone_no());
        if(emp1.getJob_role().substring(0,2).equalsIgnoreCase("HR")){
            emp2.setSalary(employee.getSalary());
            emp2.setJob_role(employee.getJob_role());
        }
        employeeRepo.save(emp2);
        response=new ResponseEntity<>("Employee Profile Successfully Updated",HttpStatus.OK);
        return response;
    }

    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees=employeeRepo.findAll();
        if(employees.isEmpty())return new ResponseEntity<>(employees,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

}
