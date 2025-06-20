package com.employee.EmployeeManagementSystem.controller;

import com.employee.EmployeeManagementSystem.entity.EmployeeTask;
import com.employee.EmployeeManagementSystem.enums.EmployeeTaskStatus;
import com.employee.EmployeeManagementSystem.service.EmployeeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeTask")
public class EmployeeTaskController {

    @Autowired
    private EmployeeTaskService employeeTaskService;

    @PostMapping("/createEmployeeTask")
    public ResponseEntity<EmployeeTask> createEmployeeTask(@RequestBody EmployeeTask employeeTask) {
        EmployeeTask createdTask = employeeTaskService.createEmployeeTask(employeeTask);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}/assign/{employeeId}")
    public ResponseEntity<EmployeeTask> assignTaskToEmployee(@PathVariable Long taskId, @PathVariable Long employeeId) {
        try {
            EmployeeTask assignedTask = employeeTaskService.assignTaskToEmployee(taskId, employeeId);
            return ResponseEntity.ok(assignedTask);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<EmployeeTask> updateTaskStatus(@PathVariable Long taskId, @RequestParam EmployeeTaskStatus employeeTaskStatus) {
        try {
            EmployeeTask updated = employeeTaskService.updateTaskStatus(taskId, employeeTaskStatus);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeTask>> getTasksByEmployee(@PathVariable Long employeeId) {
        try {
            List<EmployeeTask> employeeTasks = employeeTaskService.getTasksByEmployeeId(employeeId);
            return ResponseEntity.ok(employeeTasks);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employee/{employeeId}/status")
    public ResponseEntity<List<EmployeeTask>> getTasksByEmployeeAndStatus(@PathVariable Long employeeId,
                                                                  @RequestParam EmployeeTaskStatus employeeTaskStatus) {
        try {
            List<EmployeeTask> employeeTasks = employeeTaskService.getTasksByEmployeeAndStatus(employeeId, employeeTaskStatus);
            return ResponseEntity.ok(employeeTasks);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employee/{employeeId}/statistics")
    public ResponseEntity<Long> countCompletedTasks(@PathVariable Long employeeId) {
        try {
            long count = employeeTaskService.countCompletedTasksByEmployee(employeeId);
            return ResponseEntity.ok(count);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
