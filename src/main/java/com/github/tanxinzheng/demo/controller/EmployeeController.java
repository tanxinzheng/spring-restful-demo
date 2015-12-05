package com.github.tanxinzheng.demo.controller;

import com.github.tanxinzheng.demo.domain.Employee;
import com.github.tanxinzheng.demo.exceptions.ArgumentValidaErrorException;
import com.github.tanxinzheng.demo.exceptions.NotFoundResourcesException;
import com.github.tanxinzheng.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

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
    public Collection<Resource<Employee>> getEmployees(
            @RequestParam(value = "sorts", required = false) String sorts,      // 排序 +name,-age指姓名升序，年龄降序，注：+号可省略
            @RequestParam(value = "fields", required = false) String fields,    // 只查询需要的字段
            @RequestParam(value = "limit", required = false) Integer limit,         // 指定返回记录的数量，每页页数
            @RequestParam(value = "offset", required = false) Integer offset,       // 指定返回记录的开始位置，当前页第一条数据的页码
            @RequestParam(value = "expends", required = false) String expends   // 自动加载相关子资源,如?expends=department则加载部门资源信息
    ) {
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
    @ResponseStatus(HttpStatus.OK)
    public Resource<Employee> getEmployee(@PathVariable(value = "id") Integer id, HttpServletRequest request) throws NoSuchRequestHandlingMethodException {
        Employee employee = companyService.getEmployee(id);
        if(employee == null){
            throw new NotFoundResourcesException("资源为空");
        }
        return new Resource<Employee>(employee);
    }

    /**
     * 新增员工信息
     * @param employee
     * @return
     */
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Employee> addEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) throws ArgumentValidaErrorException {
        if(bindingResult.hasErrors()){
            throw new ArgumentValidaErrorException(bindingResult);
        }
        Employee employeeResult = companyService.addEmployee(employee);
        Resource<Employee> resource = new Resource<Employee>(employeeResult);
        return resource;
    }

    /**
     * 更新单个员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEmployee(@PathVariable(value = "id") Integer id) {
        Employee employee = companyService.getEmployee(id);
        if(employee == null){
            throw new NotFoundResourcesException("未找到需操作的资源");
        }
        companyService.removeEmployee(id);
    }

    /**
     * 删除单个员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable(value = "id") Integer id) {
        Employee employee = companyService.getEmployee(id);
        if(employee == null){
            throw new NotFoundResourcesException("未找到需操作的资源");
        }
        companyService.removeEmployee(id);
    }

}
