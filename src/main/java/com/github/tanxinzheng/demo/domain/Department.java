package com.github.tanxinzheng.demo.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

/**
 * Created by Jeng on 15/11/22.
 */
public class Department {

    private Integer departmentId;
    @NotEmpty
    private String name;
    @Length(min = 5, max = 10, message = "字符长度范围［5-10］")
    private String description;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
