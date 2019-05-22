package com.weduoo.rest.controller;

import com.weduoo.rest.dao.EmployeeDAO;
import com.weduoo.rest.model.Employee;
import com.weduoo.rest.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDao;

    @GetMapping(path = "/", produces = "application/json")
    public Employees getEmployees(){
        return employeeDao.getAllEmloyees();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee){
        Integer id = employeeDao.getAllEmloyees().getEmployeeList().size() + 1;
        employee.setId(id);
        employeeDao.addEmployee(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
