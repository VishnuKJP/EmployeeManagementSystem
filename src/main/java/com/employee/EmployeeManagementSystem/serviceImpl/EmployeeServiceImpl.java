package com.employee.EmployeeManagementSystem.serviceImpl;

import com.employee.EmployeeManagementSystem.entity.EmployeeInfo;
import com.employee.EmployeeManagementSystem.repository.EmployeeInfoRepository;
import com.employee.EmployeeManagementSystem.repository.EmployeeTaskRepository;
import com.employee.EmployeeManagementSystem.service.EmployeeService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeInfoRepository employeeInfoRepository;

    @Autowired
    public void setEmployeeInfoRepository(EmployeeInfoRepository employeeInfoRepository) {
        this.employeeInfoRepository = employeeInfoRepository;
    }

    @Override
    public EmployeeInfo createEmployeeInfo(EmployeeInfo employeeInfo) {
        if (employeeInfoRepository.findByEmployeeEmail(employeeInfo.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }
        return employeeInfoRepository.save(employeeInfo);
    }

    @Override
    public EmployeeInfo getEmployeeInfoById(Long id) {
        return employeeInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EmployeeInfo not found with id: " + id));
    }

    @Override
    public List<EmployeeInfo> getAllEmployeesDetails() {
        return employeeInfoRepository.findAll();
    }

    @Override
    public EmployeeInfo updateEmployeeDetails(Long id, EmployeeInfo updatedEmployee) {
        EmployeeInfo existing = getEmployeeInfoById(id);
        existing.setName(updatedEmployee.getName());
        existing.setDepartment(updatedEmployee.getDepartment());
        return employeeInfoRepository.save(existing);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeInfoRepository.deleteById(id);
    }

}
