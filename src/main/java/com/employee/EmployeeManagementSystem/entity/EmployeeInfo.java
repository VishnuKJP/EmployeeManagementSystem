package com.employee.EmployeeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.config.Task;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee_info", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Builder
public class EmployeeInfo {
    @Id
    @Column(name = "employee_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_info_name")
    private String name;
    @Column(name = "employee_department")
    private String department;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "employeeInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeTask> employeeTasks;
}
