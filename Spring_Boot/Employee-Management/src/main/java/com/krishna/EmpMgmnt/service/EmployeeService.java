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
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(updatedEmployee.getId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + updatedEmployee.getId() + " not found"));

        if(updatedEmployee.getName()!=null)
        	existingEmployee.setName(updatedEmployee.getName());
        
        if(updatedEmployee.getAge()!=0)
        	existingEmployee.setAge(updatedEmployee.getAge());
        	
        
        if(updatedEmployee.getCity()!=null)
        	existingEmployee.setCity(updatedEmployee.getCity());
        
        if(updatedEmployee.getDesignation()!=null)
        	existingEmployee.setDesignation(updatedEmployee.getDesignation());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        employeeRepository.delete(employee);
    }
}