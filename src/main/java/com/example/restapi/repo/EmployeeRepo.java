package com.example.restapi.repo;

import com.example.restapi.entities.Employee;
import com.example.restapi.payloads.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

    Employee findByEmail(String email);

}
