package com.employee.EmployeeManagementSystem.repository;

import com.employee.EmployeeManagementSystem.entity.EmployeeInfo;
import com.employee.EmployeeManagementSystem.entity.EmployeeTask;
import com.employee.EmployeeManagementSystem.enums.EmployeeTaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {
    List<EmployeeTask> findByAssignedTo(EmployeeInfo employeeInfo);
    List<EmployeeTask> findByAssignedToAndStatus(EmployeeInfo employeeInfo, EmployeeTaskStatus employeeTaskStatus);
}
