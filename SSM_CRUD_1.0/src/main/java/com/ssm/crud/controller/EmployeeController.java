package com.ssm.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.crud.bean.Employee;
import com.ssm.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wanyu
 * @createTime 2022-03-18 23:09
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        // 这不是一个分页查询；
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        // startPage后面紧跟的这个查询就是一个分页查询
        PageHelper.startPage(pn,5);
        List<Employee> emps =  employeeService.getAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo<Employee> pageInfo = new PageInfo<>(emps,5);
        model.addAttribute("pageInfo",pageInfo);
        return "list";
    }
}
