package com.employee.EmployeeManagementSystem.service;

import com.employee.EmployeeManagementSystem.entity.EmployeeInfo;

import java.util.List;

public interface EmployeeService {
    EmployeeInfo createEmployeeInfo(EmployeeInfo employeeInfo);
    EmployeeInfo getEmployeeInfoById(Long id);
    List<EmployeeInfo> getAllEmployeesDetails();
    EmployeeInfo updateEmployeeDetails(Long id, EmployeeInfo updatedEmployeeDetails);
    void deleteEmployeeById(Long id);
}
