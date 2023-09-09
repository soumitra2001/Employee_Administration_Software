package com.example.Employee.Management.System.repositories;

import com.example.Employee.Management.System.models.Employee;
import com.example.Employee.Management.System.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReportRepo extends JpaRepository<Report,Integer> {

    Report findByEmployee(Employee employee);
}
