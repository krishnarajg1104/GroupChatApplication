package com.chatApp.SecureTeamSynk.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.chatApp.SecureTeamSynk.Models.DeptType;
import com.chatApp.SecureTeamSynk.Services.DeptTypeService;

import java.util.List;

@Controller
@RequestMapping("/api/depttype")
public class DeptTypeController {

    private static final Logger log = LoggerFactory.getLogger(DeptTypeController.class);

    @Autowired
    private DeptTypeService deptTypeService;

    @GetMapping("/home")
    public String deptTypeHome(Model model) {
        model.addAttribute("deptType", new DeptType());
        model.addAttribute("message", "Welcome to the Department Type Page");
        return "deptType";
    }

    @PostMapping("/create")
    public String createDeptType(@ModelAttribute DeptType deptType, Model model) {
        try {
            deptTypeService.createDeptType(deptType);
            model.addAttribute("message", "Department Type created successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error creating department type.");
        }
        model.addAttribute("deptType", new DeptType());
        return "redirect:/api/depttype/list";
    }

    @GetMapping("/list")
    public String listDeptTypes(Model model) {
        List<DeptType> deptTypes = deptTypeService.listDeptTypes();
        model.addAttribute("deptTypes", deptTypes);
        return "deptTypeList";
    }

    @PostMapping("/updateStatus")
    public String updateDeptTypeStatus(@RequestParam("deptTypeId") Byte deptTypeId,
                                       @RequestParam("isActive") Boolean isActive) {
        try {
            deptTypeService.updateDeptTypeStatus(deptTypeId, isActive);
        } catch (Exception e) {
            log.error("Error updating department type status: ", e);
        }
        return "redirect:/api/depttype/list";
    }

    @GetMapping("/edit/{id}")
    public String editDeptType(@PathVariable("id") Byte deptTypeId, Model model) {
        try {
            DeptType deptType = deptTypeService.getDeptTypeById(deptTypeId);
            if (deptType != null) {
                model.addAttribute("deptType", deptType);
                return "editDeptType"; // View name for the edit page
            } else {
                model.addAttribute("message", "Department Type with ID " + deptTypeId + " not found");
                return "redirect:/api/depttype/list";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error retrieving department type details.");
            return "redirect:/api/depttype/list";
        }
    }

    @PostMapping("/update/{id}")
    public String updateDeptType(@PathVariable("id") Byte deptTypeId,
                                 @RequestParam("deptTypeName") String deptTypeName,
                                 @RequestParam("isActive") boolean isActive,
                                 Model model) {
        try {
            deptTypeService.updateDeptType(deptTypeId, deptTypeName, isActive);
        } catch (Exception e) {
            model.addAttribute("message", "Error updating department type.");
        }
        return "redirect:/api/depttype/list";
    }
}