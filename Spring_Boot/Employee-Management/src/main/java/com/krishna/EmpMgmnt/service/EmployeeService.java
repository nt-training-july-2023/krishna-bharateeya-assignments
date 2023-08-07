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

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setAge(updatedEmployee.getAge());
        existingEmployee.setCity(updatedEmployee.getCity());
        existingEmployee.setDesignation(updatedEmployee.getDesignation());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

        employeeRepository.delete(employee);
    }
}

//
//@Service
//public class EmployeeService {
//
//	private final EmployeeRepository employeeRepository;
//
//    @Autowired
//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.getAllEmployees();
//    }
//
//    public Employee getEmployeeById(Long id) {
//        Employee employee = employeeRepository.getEmployeeById(id);
//        if (employee == null) {
//            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
//        }
//        return employee;
//    }
//
//    public Employee addEmployee(Employee employee) {
//        return employeeRepository.addEmployee(employee);
//    }
//
//    public Employee updateEmployee(Employee updatedEmployee) {
//        Employee employee = employeeRepository.updateEmployee(updatedEmployee);
//        if (employee == null) {
//            throw new EmployeeNotFoundException("Employee with ID " + updatedEmployee.getId() + " not found");
//        }
//        return employee;
//    }
//
//    public void deleteEmployee(Long id) {
//        Employee employee = getEmployeeById(id);
//        if (employee == null) {
//            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
//        }
//        employeeRepository.deleteEmployee(id);
//    }
//}
