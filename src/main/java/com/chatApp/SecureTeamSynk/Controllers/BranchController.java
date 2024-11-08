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
import com.chatApp.SecureTeamSynk.Services.BranchService;



@Controller
@RequestMapping("/api/branch")
public class BranchController {

    private static final Logger log = LoggerFactory.getLogger(BranchController.class);

    @Autowired
    private BranchService branchService;

    @GetMapping("/home")
    public String branchHome(Model model) {
        model.addAttribute("branch", new Branch());
        model.addAttribute("companies", branchService.listCompanies());
        model.addAttribute("message", "Welcome to the Branch Page");
        return "branch";
    }

    @PostMapping("/create")
    public String createBranch(@ModelAttribute Branch branch, Model model) {
        try {
        	
            branchService.createBranch(branch);
            model.addAttribute("message", "Branch created successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error creating branch.");
        }
        model.addAttribute("branch", new Branch());
        model.addAttribute("companies", branchService.listCompanies());
        return "redirect:/api/branch/list";
    }

    @GetMapping("/list")
    public String listBranches(Model model) {
        List<Branch> branches = branchService.listBranches();
        model.addAttribute("branches", branches);
        return "branchList";
    }

    @PostMapping("/updateStatus")
    public String updateBranchStatus(@RequestParam("branchId") Byte branchId,
                                      @RequestParam("isActive") Boolean isActive) {
        try {
            branchService.updateBranchStatus(branchId, isActive);
        } catch (Exception e) {
            log.error("Error updating branch status: ", e);
        }
        return "redirect:/api/branch/list";
    }

    @GetMapping("/edit/{id}")
    public String editBranch(@PathVariable("id") Byte branchId, Model model) {
        try {
            Branch branch = branchService.getBranchById(branchId);
            if (branch != null) {
                model.addAttribute("branch", branch);
                model.addAttribute("companies", branchService.listCompanies());
                return "editBranch"; // View name for the edit page
            } else {
                model.addAttribute("message", "Branch with ID " + branchId + " not found");
                return "redirect:/api/branch/list";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error retrieving branch details.");
            return "redirect:/api/branch/list";
        }
    }

    @PostMapping("/update/{id}")
    public String updateBranch(@PathVariable("id") Byte branchId,
                               @RequestParam("branchName") String branchName,
                               @RequestParam("companyId") Integer companyId,
                               @RequestParam("address") String address,
                               @RequestParam("location") String location,
                               @RequestParam("isActive") Boolean isActive,
                               Model model) {
        try {
            branchService.updateBranch(branchId, branchName, companyId, address, location, isActive);
        } catch (Exception e) {
            model.addAttribute("message", "Error updating branch.");
        }
        return "redirect:/api/branch/list";
    }
}