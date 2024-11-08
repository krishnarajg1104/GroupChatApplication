package com.chatApp.SecureTeamSynk.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatApp.SecureTeamSynk.Models.Branch;
import com.chatApp.SecureTeamSynk.Models.Department;
import com.chatApp.SecureTeamSynk.Models.DeptType;
import com.chatApp.SecureTeamSynk.Services.BranchService;
import com.chatApp.SecureTeamSynk.Services.DepartmentService;
import com.chatApp.SecureTeamSynk.Services.DeptTypeService;

@Controller
@RequestMapping("/api/department")
public class DepartmentController {

    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DeptTypeService deptTypeService; // Inject DeptTypeService

    @Autowired
    private BranchService branchService; // Inject BranchService

    @GetMapping("/home")
    public String departmentHome(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("deptTypes", deptTypeService.listDeptTypes());
        model.addAttribute("branches", branchService.listBranches()); // Add Branches to model
        model.addAttribute("message", "Welcome to the Department Page");
        return "department";
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute Department department, Model model) {
        try {
            departmentService.createDepartment(department);
            model.addAttribute("message", "Department created successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error creating department.");
        }
        return "redirect:/api/department/list";
    }

    @GetMapping("/list")
    public String listDepartments(Model model) {
        List<Department> departments = departmentService.listDepartments();
        model.addAttribute("departments", departments);
        return "departmentList";
    }

    @PostMapping("/updateStatus")
    public String updateDepartmentStatus(@RequestParam("deptId") Short deptId,
                                          @RequestParam("isActive") Boolean isActive) {
        try {
            departmentService.updateDepartmentStatus(deptId, isActive);
        } catch (Exception e) {
            log.error("Error updating department status: ", e);
        }
        return "redirect:/api/department/list";
    }

    @GetMapping("/edit/{id}")
    public String editDepartment(@PathVariable("id") Short deptId, Model model) {
        try {
            Department department = departmentService.getDepartmentById(deptId);
            if (department != null) {
                model.addAttribute("department", department);
                model.addAttribute("deptTypes", deptTypeService.listDeptTypes()); // Add DeptTypes to model
                model.addAttribute("branches", branchService.listBranches()); // Add Branches to model
                return "editDepartment"; // View name for the edit page
            } else {
                model.addAttribute("message", "Department with ID " + deptId + " not found");
                return "redirect:/api/department/list";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error retrieving department details.");
            return "redirect:/api/department/list";
        }
    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable("id") Short deptId,
                                @RequestParam("deptName") String deptName,
                                @RequestParam("deptTypeId") Byte deptTypeId,
                                @RequestParam("branchId") Byte branchId,
                                @RequestParam("isActive") Boolean isActive,
                                Model model) {
        try {
            DeptType deptType = deptTypeService.getDeptTypeById(deptTypeId); // Get DeptType
            Branch branch = branchService.getBranchById(branchId); // Get Branch
            
            if (deptType == null || branch == null) {
                model.addAttribute("message", "Invalid DeptType or Branch.");
                return "redirect:/api/department/list";
            }

            departmentService.updateDepartment(deptId, deptName, deptType, branch, isActive);
            model.addAttribute("message", "Department updated successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error updating department.");
        }
        return "redirect:/api/department/list";
    }
}
