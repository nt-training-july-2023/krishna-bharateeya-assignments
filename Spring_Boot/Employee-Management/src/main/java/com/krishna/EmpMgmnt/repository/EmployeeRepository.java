package com.krishna.EmpMgmnt.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishna.EmpMgmnt.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // You can add custom query methods here if needed
}

//
//@Repository
//public class EmployeeRepository {
//
//    private final List<Employee> employees = new ArrayList<>();
//    private Long nextId = 100L;
//
//    public List<Employee> getAllEmployees() {
//        return employees;
//    }
//
//    public Employee getEmployeeById(Long id) {
//        for (Employee employee : employees) {
//            if (employee.getId().equals(id)) {
//                return employee;
//            }
//        }
//        return null;
//    }
//
//    public Employee addEmployee(Employee employee) {
//        employee.setId(nextId++);
//        employees.add(employee);
//        return employee;
//    }
//
//    public Employee updateEmployee(Employee updatedEmployee) {
//        Employee existingEmployee = getEmployeeById(updatedEmployee.getId());
//        if (existingEmployee != null) {
//            existingEmployee.setName(updatedEmployee.getName());
//            existingEmployee.setAge(updatedEmployee.getAge());
//            existingEmployee.setCity(updatedEmployee.getCity());
//            existingEmployee.setDesignation(updatedEmployee.getDesignation());
//        }
//        return existingEmployee;
//    }
//
//    public void deleteEmployee(Long id) {
//        for (Employee employee : employees) {
//            if (employee.getId().equals(id)) {
//                employees.remove(employee);
//                break;
//            }
//        }
//    }
//    
//}
