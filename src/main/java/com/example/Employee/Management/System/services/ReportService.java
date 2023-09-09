package com.example.Employee.Management.System.services;

import com.example.Employee.Management.System.models.Employee;
import com.example.Employee.Management.System.models.Report;
import com.example.Employee.Management.System.repositories.IReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    IReportRepo reportRepo;

    @Autowired
    EmployeeService employeeService;
    public ResponseEntity<String> addReport(Report report, int id) {
        Employee employee=employeeService.getEmployeeById(id);
        if(employee!=null && employee.getJob_role().substring(0,2).equalsIgnoreCase("Hr")){
            reportRepo.save(report);
            return new ResponseEntity<>("Employee report successfully added!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid User!",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Report>> getAllReport(){
        List<Report> reports=reportRepo.findAll();
        if(reports.isEmpty())return new ResponseEntity<>(reports,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reports,HttpStatus.OK);
    }

    public ResponseEntity<Report> getReportByEmpId(int empId){
        Employee employee=employeeService.getEmployeeById(empId);
        if(employee==null)return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        Report report=reportRepo.findByEmployee(employee);
        if(report==null)return new ResponseEntity<>(report,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(report,HttpStatus.OK);
    }
}
