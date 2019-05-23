package com.weduoo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;


    @GetMapping(value = "/", produces = "application/json")
    public Employees getEmployees(){
        return employeeDAO.getAllEmloyees();
    }

}
