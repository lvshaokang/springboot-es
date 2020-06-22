package com.lsk.es.controller;

import com.lsk.es.model.Employee;
import com.lsk.es.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO:
 *
 * @author red
 * @class_name EmployeeController
 * @date 2020-06-22
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public Employee add(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/{name}")
    public List<Employee> findByName(@PathVariable("name") String name) {
        return employeeRepository.findByName(name);
    }

    @GetMapping("/organization/{organizationName}")
    public List<Employee> findByOrganizationName(@PathVariable("organizationName") String organizationName) {
        return employeeRepository.findByOrganizationName(organizationName);
    }


}
