package com.employee.EmployeeManagementSystem.repository;

import com.employee.EmployeeManagementSystem.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {
    Optional<EmployeeInfo> findByEmployeeEmail(String email);
}
