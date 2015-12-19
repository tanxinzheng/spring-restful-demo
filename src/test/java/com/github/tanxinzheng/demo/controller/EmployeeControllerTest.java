package com.github.tanxinzheng.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.tanxinzheng.demo.ServletConfig;
import com.github.tanxinzheng.demo.domain.Employee;
import com.github.tanxinzheng.demo.exceptions.RestError;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.MessageFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Jeng on 15/11/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MockServletContext.class, ServletConfig.class})
@WebAppConfiguration
public class EmployeeControllerTest  {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private Integer id;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * 新增资源单元测试
     * @throws Exception
     */
    @Test
    public void testAddEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setAge(1);
        employee.setBirthDay(new Date());
        employee.setName("张三");
        String jsonParams = JSONObject.toJSONString(employee);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParams))
                .andDo(print());
        actions.andExpect(status().isCreated());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        Employee result = JSONObject.parseObject(resultJson, Employee.class);
        Assert.assertNotNull(result.getEmployeeId());
        id = result.getEmployeeId();
    }

    /**
     * 新增资源单元测试
     * @throws Exception
     */
    @Test
    public void testAddEmployeeByError() throws Exception {
        Employee employee = new Employee();
        employee.setAge(1);
        employee.setBirthDay(new Date());
        employee.setName("tanxinzheng");
        String jsonParams = JSONObject.toJSONString(employee);
        //System.out.println("params:");
        //System.out.println(jsonParams);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParams))
                .andDo(print());
        actions.andExpect(status().isBadRequest());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        RestError restError = JSONObject.parseObject(resultJson, RestError.class);
        Assert.assertNotNull(restError);
        Assert.assertEquals("请求参数校验数目不正确", 2, restError.getErrors().size());
        //System.out.println("result:");
        //System.out.println(resultJson);
    }

    /**
     * 查询单个资源测试案例
     * @throws Exception
     */
    @Test
    public void testGetEmployee() throws Exception {
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/employees/{0}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        actions.andExpect(status().isOk());
    }

    /**
     * 查询资源集合单元测试
     * @throws Exception
     */
    @Test
    public void testGetEmployees() throws Exception {
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()); // 使用print()可打印出当前测试设计的HTTP Request/Responsed的所有信息，方便定位问题
        actions.andExpect(status().isOk());
    }

    /**
     * 删除资源单元案例
     * @throws Exception
     */
    @Test
    //@Ignore
    public void testDeleteEmployee() throws Exception {
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.delete("/employees/{0}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        actions.andExpect(status().isNoContent());
    }

    /**
     * 更新资源测试案例
     * @throws Exception
     */
    @Test
    //@Ignore
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("李四");
        employee.setEmployeeId(id);
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.put("/employees/{0}", id)
                .accept(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(employee)))
                .andDo(print());
        actions.andExpect(status().isAccepted());
    }
}