package com.krishna.EmpMgmnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishna.EmpMgmnt.exception.EmployeeNotFoundException;
import com.krishna.EmpMgmnt.model.Employee;
import com.krishna.EmpMgmnt.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        return employee;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public Employee updateEmployee(Employee updatedEmployee) {
        Employee employee = employeeRepository.updateEmployee(updatedEmployee);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with ID " + updatedEmployee.getId() + " not found");
        }
        return employee;
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        employeeRepository.deleteEmployee(id);
    }
}
