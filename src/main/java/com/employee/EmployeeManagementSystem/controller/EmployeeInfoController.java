package com.employee.EmployeeManagementSystem.controller;

import com.employee.EmployeeManagementSystem.entity.EmployeeInfo;
import com.employee.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeInfo")
public class EmployeeInfoController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeInfo> createEmployee(@RequestBody EmployeeInfo employeeInfo) {
        try {
            EmployeeInfo newEmployee = employeeService.createEmployeeInfo(employeeInfo);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<EmployeeInfo> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeInfo employee = employeeService.getEmployeeInfoById(id);
            return ResponseEntity.ok(employee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeInfo>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployeesDetails());
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeInfo> updateEmployee(@PathVariable Long id, @RequestBody EmployeeInfo updatedEmployee) {
        try {
            EmployeeInfo updated = employeeService.updateEmployeeDetails(id, updatedEmployee);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}


