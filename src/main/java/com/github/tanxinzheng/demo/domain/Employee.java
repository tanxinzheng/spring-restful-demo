package com.github.tanxinzheng.demo.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Jeng on 15/11/22.
 */
public class Employee {

    private Integer employeeId;
    @NotEmpty(message = "名称不能为空")
    @Length(min = 4, max = 10, message = "字符长度范围[4,10]")
    private String name;
    @Min(value = 0, message = "字符长度范围[1, 999]")
    @Max(value = 999, message = "字符长度范围[1, 999]")
    private Integer age;
    private Date birthDay;
    private Integer departmentId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
