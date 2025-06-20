package com.employee.EmployeeManagementSystem.service;

import com.employee.EmployeeManagementSystem.entity.EmployeeInfo;
import com.employee.EmployeeManagementSystem.entity.EmployeeTask;
import com.employee.EmployeeManagementSystem.enums.EmployeeTaskStatus;
import com.employee.EmployeeManagementSystem.repository.EmployeeInfoRepository;
import com.employee.EmployeeManagementSystem.serviceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks EmployeeServiceImpl employeeService;
    @Mock EmployeeInfoRepository employeeInfoRepository;
    EmployeeInfo employeeInfo;

    @BeforeEach
    void setUp() {
        employeeService.setEmployeeInfoRepository(employeeInfoRepository);
        EmployeeTask employeeTask=EmployeeTask.builder().id(124578L).description("Development").completed(false).employeeTaskStatus(EmployeeTaskStatus.IN_PROGRESS).build();
        employeeInfo = EmployeeInfo.builder().id(54321L).name("Vishnu").department("Platform_DEV")
                .email("vishnu_dev_test@gmsil.com").employeeTasks(Arrays.asList(employeeTask)).build();
    }

    @Test
    void createEmployee_success() {
        when(employeeInfoRepository.findByEmployeeEmail(employeeInfo.getEmail())).thenReturn(Optional.empty());
        when(employeeInfoRepository.save(employeeInfo)).thenReturn(employeeInfo);

        EmployeeInfo result = employeeService.createEmployeeInfo(employeeInfo);
        assertEquals("Vishnu", result.getName());
        verify(employeeInfoRepository, times(1)).save(employeeInfo);
    }

    @Test
    void createEmployee_duplicateEmail() {
        when(employeeInfoRepository.findByEmployeeEmail(employeeInfo.getEmail())).thenReturn(Optional.of(employeeInfo));
        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployeeInfo(employeeInfo));
    }

    @Test
    void getEmployeeById_success() {
        when(employeeInfoRepository.findById(54321L)).thenReturn(Optional.of(employeeInfo));

        EmployeeInfo result = employeeService.getEmployeeInfoById(54321L);
        assertEquals("Vishnu", result.getName());
    }

    @Test
    void getEmployeeById_notFound() {
        when(employeeInfoRepository.findById(54321L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> employeeService.getEmployeeInfoById(54321L));
    }
}
