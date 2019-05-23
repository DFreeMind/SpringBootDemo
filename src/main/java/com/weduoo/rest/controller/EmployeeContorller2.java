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
@RequestMapping(path = "employees")
public class EmployeeContorller2 {
    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping(path = "/", produces = "application/json")
    public Employees getEmployees(){
        return employeeDAO.getAllEmloyees();
    }

    @PostMapping(value = "/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> addEmployee(
            @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
            @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
            @RequestBody Employee employee
    ){
        Integer id = employeeDAO.getAllEmloyees().getEmployeeList().size() + 1;
        employee.setId(id);
        employeeDAO.addEmployee(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
