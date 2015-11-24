package com.github.tanxinzheng.demo.controller;

import com.github.tanxinzheng.demo.domain.Album;
import com.github.tanxinzheng.demo.domain.Employee;
import com.github.tanxinzheng.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jeng on 15/11/22.
 */
@Controller
public class EmployeeController {

    @Autowired
    CompanyService companyService;

    /**
     * 查询员工列表信息
     * @return
     */
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Resource<Employee>> getEmployees() {
        Collection<Employee> albums = companyService.findEmployees();
        List<Resource<Employee>> resources = new ArrayList<Resource<Employee>>();
        for (Employee album : albums) {
            Resource<Employee> resource = new Resource<Employee>(album);
            resources.add(resource);
        }
        return resources;
    }

    /**
     * 查询单个员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Resource<Employee> getEmployee(@PathVariable(value = "id") Integer id) {
        Employee employee = companyService.getEmployee(id);
        Resource<Employee> resource = new Resource<Employee>(employee);
        return resource;
    }

    /**
     * 新增员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    @ResponseBody
    public Resource<Employee> addEmployee(@RequestBody Employee employee) {
        Employee employeeResult = companyService.addEmployee(employee);
        Resource<Employee> resource = new Resource<Employee>(employeeResult);
        return resource;
    }

    /**
     * 删除单个员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable(value = "id") Integer id) {
        companyService.removeEmployee(id);
    }

}
