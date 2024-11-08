package com.chatApp.SecureTeamSynk.Controllers;

import java.time.LocalDateTime;
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

import com.chatApp.SecureTeamSynk.Models.Company;
import com.chatApp.SecureTeamSynk.Services.CompanyService;

@Controller
@RequestMapping("/api/company")
public class CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @GetMapping("/home")
    public String companyHome(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("message", "Welcome to the Company Page");
        return "company";
    }

    @PostMapping("/create")
    public String createCompany(@ModelAttribute Company company, Model model) {
        try {
            companyService.createCompany(company);
            model.addAttribute("message", "Company created successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error creating company.");
        }
        model.addAttribute("company", new Company());
        return "redirect:/api/company/list";
    }

    @GetMapping("/list")
    public String listCompanies(Model model) {
        List<Company> companies = companyService.listCompanies();
        model.addAttribute("companies", companies);
        return "companyList";
    }

    @PostMapping("/updateStatus")
    public String updateCompanyStatus(@RequestParam("companyId") Integer companyId,
                                       @RequestParam("isActive") Boolean isActive) {
        try {
            companyService.updateCompanyStatus(companyId, isActive);
        } catch (Exception e) {
            log.error("Error updating company status: ", e);
        }
        return "redirect:/api/company/list";
    }

    @GetMapping("/edit/{id}")
    public String editCompany(@PathVariable("id") Integer companyId, Model model) {
        try {
            Company company = companyService.getCompanyById(companyId);
            if (company != null) {
                model.addAttribute("company", company);
                return "editCompany"; // View name for the edit page
            } else {
                model.addAttribute("message", "Company with ID " + companyId + " not found");
                return "redirect:/api/company/list";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error retrieving company details.");
            return "redirect:/api/company/list";
        }
    }

    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable("id") Integer companyId,
                                @RequestParam("companyName") String companyName,
                                @RequestParam("address") String address,
                                @RequestParam("location") String location,
                                @RequestParam("isActive") boolean isActive,
                                Model model) {
        try {
            companyService.updateCompany(companyId, companyName, address, location, isActive);
        } catch (Exception e) {
            model.addAttribute("message", "Error updating company.");
        }
        return "redirect:/api/company/list";
    }
}