/*
 * To change this license header, choose License Headers in Department Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.controllers;

import mcc53.com.front.models.Department;
import mcc53.com.front.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Xvitas
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
//==============================================================================
//    GETALL

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("departmentAja", departmentService.getAll());
        return "department/view";
    }
//==============================================================================    
//    CREATE

    @GetMapping("/add")
    public String showForm(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        return "department/add-department";
    }

    @PostMapping("/add")
    public String postDeparment(@ModelAttribute("department") Department department) {
        departmentService.createDepartment(department);
        return "redirect:/department";
    }
//==============================================================================
//    UPDATE

    @GetMapping("/update/{id}")
    public String updateDepartment(Model model, @PathVariable("id") Integer id) {
        Department p = departmentService.getDepartmentById(id);
        model.addAttribute("department", p);
        return "department/update-department";
    }

    @PostMapping("/update/{id}")
    public String updateDepartment(Department department,
            @PathVariable("id") Integer id) {
        departmentService.updateDepartment(id, department);
        return "redirect:/department";
    }
//==============================================================================
//    DELETE

    @GetMapping("/delete/{id}")
    public String postDepartment(@PathVariable("id") Integer id) {
        departmentService.deleteDepartment(id);
        return "redirect:/department";
    }
//==============================================================================
}
