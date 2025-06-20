package com.employee.EmployeeManagementSystem.service;

import com.employee.EmployeeManagementSystem.entity.EmployeeTask;
import com.employee.EmployeeManagementSystem.enums.EmployeeTaskStatus;

import java.util.List;

public interface EmployeeTaskService {
    EmployeeTask createEmployeeTask(EmployeeTask employeeTask);
    EmployeeTask assignTaskToEmployee(Long taskId, Long employeeId);
    EmployeeTask updateTaskStatus(Long taskId, EmployeeTaskStatus status);
    List<EmployeeTask> getTasksByEmployeeId(Long employeeId);
    List<EmployeeTask> getTasksByEmployeeAndStatus(Long employeeId, EmployeeTaskStatus employeeTaskStatus);
    long countCompletedTasksByEmployee(Long employeeId);
}
