package com.github.tanxinzheng.demo.service;

import com.github.tanxinzheng.demo.domain.Album;
import com.github.tanxinzheng.demo.domain.Artist;
import com.github.tanxinzheng.demo.domain.Department;
import com.github.tanxinzheng.demo.domain.Employee;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

/**
 * Created by Jeng on 15/11/22.
 */
@Service
public class CompanyService {

    private Map<Integer, Employee> employees;
    private Map<Integer, Department> departments;

    public CompanyService(){
        employees = new HashMap<Integer, Employee>();
        departments = new HashMap<Integer, Department>();
        for (int i = 0; i < 3; i++) {
            Department department = new Department();
            department.setName(MessageFormat.format("第{0}部门", i+1));
            department.setDepartmentId(i);
            department.setDescription("部门职责" + i);
            departments.put(department.getDepartmentId(), department);
        }
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setEmployeeId(new Random().nextInt(100));
            employee.setName("员工" + i + 1);
            employee.setAge(new Random().nextInt(100));
            employee.setBirthDay(new Date());
            employee.setDepartmentId(new Random().nextInt(departments.size() + 1));
            employees.put(employee.getEmployeeId(), employee);
        }

    }

    public Collection<Employee> findEmployees(){
        return employees.values();
    }

    public Employee getEmployee(Integer id){
        return employees.get(id);
    }

    public void addEmployee(Employee employee){
        employee.setEmployeeId(new Random().nextInt(100));
        employees.put(employee.getEmployeeId(), employee);
    }

    public void updateEmployee(Employee employee){
        employees.put(employee.getEmployeeId(), employee);
    }

    public void removeEmployee(Integer id){
        employees.remove(id);
    }

    public Collection<Department> findDepartments(){
        return departments.values();
    }

    public Department getDepartment(Integer id){
        return departments.get(id);
    }
}
