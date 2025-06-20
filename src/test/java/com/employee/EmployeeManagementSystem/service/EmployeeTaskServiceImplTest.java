package com.employee.EmployeeManagementSystem.service;

import com.employee.EmployeeManagementSystem.entity.EmployeeInfo;
import com.employee.EmployeeManagementSystem.entity.EmployeeTask;
import com.employee.EmployeeManagementSystem.enums.EmployeeTaskStatus;
import com.employee.EmployeeManagementSystem.repository.EmployeeInfoRepository;
import com.employee.EmployeeManagementSystem.repository.EmployeeTaskRepository;
import com.employee.EmployeeManagementSystem.serviceImpl.EmployeeTaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class EmployeeTaskServiceImplTest {

    @Mock private EmployeeTaskRepository employeeTaskRepository;
    @Mock private EmployeeInfoRepository employeeInfoRepository;
    @InjectMocks private EmployeeTaskServiceImpl employeeTaskService;

    private EmployeeTask employeeTask;
    private EmployeeInfo employeeInfo;

    @BeforeEach
    void setUp() {
        employeeTaskService.setEmployeeInfoRepository(employeeInfoRepository);
        employeeTaskService.setEmployeeTaskRepository(employeeTaskRepository);
        employeeTask=EmployeeTask.builder().id(124578L).description("Development").completed(false).employeeTaskStatus(EmployeeTaskStatus.IN_PROGRESS).build();
        employeeInfo = EmployeeInfo.builder().id(54321L).name("Vishnu").department("Platform_DEV")
                .email("vishnu_dev_test@gmsil.com").employeeTasks(Arrays.asList(employeeTask)).build();
    }

    @Test
    void assignTask_success() {
        when(employeeTaskRepository.findById(124578L)).thenReturn(Optional.of(employeeTask));
        when(employeeInfoRepository.findById(54321L)).thenReturn(Optional.of(employeeInfo));
        when(employeeTaskRepository.save(employeeTask)).thenReturn(employeeTask);

        EmployeeTask result = employeeTaskService.assignTaskToEmployee(124578L, 54321L);
        assertEquals(employeeInfo, result.getEmployeeInfo());
    }

    @Test
    void countCompletedTasksByEmployee() {
        when(employeeInfoRepository.findById(54321L)).thenReturn(Optional.of(employeeInfo));
        when(employeeTaskRepository.findByAssignedTo(employeeInfo)).thenReturn(Collections.singletonList(employeeTask));
        long count = employeeTaskService.countCompletedTasksByEmployee(54321L);
        assertEquals(0, count);
    }
}
