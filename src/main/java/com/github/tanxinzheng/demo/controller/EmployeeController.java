package com.github.tanxinzheng.demo.controller;

import com.github.tanxinzheng.demo.domain.Album;
import com.github.tanxinzheng.demo.domain.Employee;
import com.github.tanxinzheng.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
