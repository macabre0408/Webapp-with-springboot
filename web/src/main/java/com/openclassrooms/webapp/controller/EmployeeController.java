package com.openclassrooms.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Employee> listEmployee = employeeService.getEmployees();
        model.addAttribute("employees", listEmployee);
        return "home";
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final Long id) {
        employeeService.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/addEmployee")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee e) {
        employeeService.saveEmployee(e);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/employee/{id}")
    public String updateEmployeeForm(@PathVariable("id") final Long id, Model model) {
        Employee e = employeeService.getEmployee(id);
        model.addAttribute("employee", e);
        return "upEmployee";
    }

    @PostMapping("/upEmployee")
    public ModelAndView updateEmployeeAction(@ModelAttribute Employee e) {
        employeeService.saveEmployee(e);
        return new ModelAndView("redirect:/");
    }
}
