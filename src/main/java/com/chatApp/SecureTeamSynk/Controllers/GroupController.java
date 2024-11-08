package com.chatApp.SecureTeamSynk.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.chatApp.SecureTeamSynk.Models.Group;
import com.chatApp.SecureTeamSynk.Services.DepartmentService;
import com.chatApp.SecureTeamSynk.Services.GroupService;

@Controller
@RequestMapping("/api/group")
public class GroupController {

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/home")
    public String groupHome(Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("departments", departmentService.listDepartments());
        model.addAttribute("message", "Welcome to the Group Page");
        return "group";
    }

    @PostMapping("/create")
    public String createGroup(@ModelAttribute Group group, Model model) {
        try {
            groupService.createGroup(group);
            model.addAttribute("message", "Group created successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error creating group.");
        }
        return "redirect:/api/group/list";
    }

    @GetMapping("/list")
    public String listGroups(Model model) {
        List<Group> groups = groupService.listGroups();
        model.addAttribute("groups", groups);
        return "groupList";
    }

    @PostMapping("/updateStatus")
    public String updateGroupStatus(@RequestParam("groupId") Integer groupId,
                                    @RequestParam("isActive") Boolean isActive) {
        try {
            groupService.updateGroupStatus(groupId, isActive);
        } catch (Exception e) {
            log.error("Error updating group status: ", e);
        }
        return "redirect:/api/group/list";
    }

    @GetMapping("/edit/{id}")
    public String editGroup(@PathVariable("id") Integer groupId, Model model) {
        try {
            Group group = groupService.getGroupById(groupId);
            if (group != null) {
                model.addAttribute("group", group);
                model.addAttribute("departments", departmentService.listDepartments()); // Add Departments to model
                return "editGroup"; // View name for the edit page
            } else {
                model.addAttribute("message", "Group with ID " + groupId + " not found");
                return "redirect:/api/group/list";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error retrieving group.");
            log.error("Error retrieving group: ", e);
            return "redirect:/api/group/list";
        }
    }

    @PostMapping("/update/{id}")
    public String updateGroup(@ModelAttribute Group group, @RequestParam("deptId") Short deptId, Model model) {
        try {
            groupService.updateGroup(group.getGroupId(), group.getGroupName(), deptId, group.getIsActive());
            model.addAttribute("message", "Group updated successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error updating group.");
            log.error("Error updating group: ", e);
        }
        return "redirect:/api/group/list";
    }
}
