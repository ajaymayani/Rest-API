package com.example.restapi.services;

import com.example.restapi.payloads.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    // add employee
    EmployeeDto addEmployee(EmployeeDto employeeDto);

    //update employee
    EmployeeDto updateEmployee(Integer employeeId,EmployeeDto employeeDto);

    //delete employee
    void deleteEmployee(Integer employeeId);
    //fetch employee by id
    EmployeeDto fetchEmployeeById(Integer employeeId);

    //fetch all employee
    List<EmployeeDto> fetchAllEmployee();
}
