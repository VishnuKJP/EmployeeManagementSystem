package com.employee.EmployeeManagementSystem.entity;

import com.employee.EmployeeManagementSystem.enums.EmployeeTaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EmployeeTask")
@Builder
public class EmployeeTask {
    @Id
    @Column(name = "employee_task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_description")
    private String description;
    @Column(name = "task_completed")
    private boolean completed;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private EmployeeTaskStatus employeeTaskStatus;

    @ManyToOne
    @JoinColumn(name = "employee_info_id", nullable = false)
    private EmployeeInfo employeeInfo;
}
