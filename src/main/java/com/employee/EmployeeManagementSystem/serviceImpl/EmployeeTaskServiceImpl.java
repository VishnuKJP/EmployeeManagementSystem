package com.employee.EmployeeManagementSystem.serviceImpl;

import com.employee.EmployeeManagementSystem.entity.EmployeeInfo;
import com.employee.EmployeeManagementSystem.entity.EmployeeTask;
import com.employee.EmployeeManagementSystem.enums.EmployeeTaskStatus;
import com.employee.EmployeeManagementSystem.repository.EmployeeInfoRepository;
import com.employee.EmployeeManagementSystem.repository.EmployeeTaskRepository;
import com.employee.EmployeeManagementSystem.service.EmployeeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeTaskServiceImpl implements EmployeeTaskService {
    private EmployeeTaskRepository employeeTaskRepository;
    private EmployeeInfoRepository employeeInfoRepository;

    @Autowired
    public void setEmployeeInfoRepository(EmployeeInfoRepository employeeInfoRepository) {
        this.employeeInfoRepository = employeeInfoRepository;
    }

    @Autowired
    public void setEmployeeTaskRepository(EmployeeTaskRepository employeeTaskRepository) {
        this.employeeTaskRepository = employeeTaskRepository;
    }

    @Override
    public EmployeeTask createEmployeeTask(EmployeeTask employeeTask) {
        return employeeTaskRepository.save(employeeTask);
    }

    @Override
    public EmployeeTask assignTaskToEmployee(Long taskId, Long employeeId) {
        EmployeeTask employeeTask = employeeTaskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));
                        EmployeeInfo employee = employeeInfoRepository.findById(employeeId)
                        .orElseThrow(() -> new IllegalArgumentException("Employee not found."));
        employeeTask.setEmployeeInfo(employee);
        return employeeTaskRepository.save(employeeTask);
    }

    @Override
    public EmployeeTask updateTaskStatus(Long taskId, EmployeeTaskStatus employeeTaskStatus) {
        EmployeeTask employeeTask = employeeTaskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));
        employeeTask.setEmployeeTaskStatus(employeeTaskStatus);
        return employeeTaskRepository.save(employeeTask);
    }

    @Override
    public List<EmployeeTask> getTasksByEmployeeId(Long employeeId) {
        EmployeeInfo employeeInfo = employeeInfoRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found."));
        return employeeTaskRepository.findByAssignedTo(employeeInfo);
    }

    @Override
    public List<EmployeeTask> getTasksByEmployeeAndStatus(Long employeeId, EmployeeTaskStatus employeeTaskStatus) {
        EmployeeInfo employeeInfo = employeeInfoRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found."));
        return employeeTaskRepository.findByAssignedToAndStatus(employeeInfo, employeeTaskStatus);
    }

    @Override
    public long countCompletedTasksByEmployee(Long employeeId) {
        EmployeeInfo employeeInfo = employeeInfoRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found."));
        return employeeTaskRepository.findByAssignedTo(employeeInfo).stream()
                .filter(task -> task.getEmployeeTaskStatus() == EmployeeTaskStatus.COMPLETED)
                .count();
    }
}
