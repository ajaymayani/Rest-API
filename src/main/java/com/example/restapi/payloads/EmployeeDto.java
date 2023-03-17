package com.example.restapi.payloads;

import com.example.restapi.entities.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class EmployeeDto {
    private Integer employeeId;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "Enter valid email")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "Password is required")
    @Size(min = 8,message = "Password should be 8 character")
    @Size(max = 12,message = "Password maximum 12 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message ="Enter strong password" )
    private String password;
    @NotNull(message = "Date of joining required")
    private Date doj;
    private List<Role> roles = new ArrayList<>();
}
