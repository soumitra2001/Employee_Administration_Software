package com.example.Employee.Management.System.controllers;

import com.example.Employee.Management.System.models.Report;
import com.example.Employee.Management.System.services.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("/report/{id}")
    public ResponseEntity<String> addReport(@Valid @RequestBody Report report,@PathVariable int id){
        return reportService.addReport(report,id);
    }

    @GetMapping("/report/all")
    public ResponseEntity<List<Report>> getAllReport(){
        return reportService.getAllReport();
    }

    @GetMapping("/report/one/{empId}")
    public ResponseEntity<Report> getReportByEmpId(@PathVariable int empId){
        return reportService.getReportByEmpId(empId);
    }
}
