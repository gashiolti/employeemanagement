package com.employeemanagementproject.springboot.employeemanagement.controller;


import com.employeemanagementproject.springboot.employeemanagement.entity.Employee;
import com.employeemanagementproject.springboot.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // RETURN A LIST OF EMPLOYEES
    @GetMapping("/employees")
    public String findAll(Model theModel) {

        // get employees from database
        List<Employee> list = employeeService.findAll();

        // add the employees to spring model
        theModel.addAttribute("employees", list);

        // return employeeService.findAll();
        return "employees";
    }

    // ADD MAPPING FOR GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return employee;
    }

    // ADD MAPPING FOR POST /employees - add a new employee
    @PostMapping("/saveemployee")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {

        employeeService.save(employee);

        return "redirect:/employees?added";
    }

    // ADD MAPPING FOR PUT /employees - update an employee
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {

        employeeService.save(employee);

        return "redirect:/employees?updated";
    }

    // ADD MAPPING FOR DELETE /employees/{employeeId} - DELETE AN EMPLOYEE
    @GetMapping("/employees/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {

        employeeService.deleteById(id);

        return "redirect:/employees?deleted";
    }

    // ADD MAPPING TO SHOW ADD FORM FOR EMPLOYEE
    @GetMapping("/employees/addemployee")
    public String addForm(Model model) {

        // create model attribute to bind form data
        Employee employee = new Employee();

        model.addAttribute(employee);

        return "employee-form";
    }

    // ADD MAPPING TO SHOW FORM FOR UPDATE
    @GetMapping("/employees/editform")
    public String updateForm(@RequestParam("employeeId") int id, Model model) {

        // get the employee from the service
        Employee employee = employeeService.findById(id);

        // set the employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);

        return "editemployee";
    }

    // ADD MAPPING TO LOG OUT
    @GetMapping("/login")
    public String logout() {

        // redirect to login page
        return "login";
    }


}
