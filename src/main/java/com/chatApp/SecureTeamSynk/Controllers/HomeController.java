package com.chatApp.SecureTeamSynk.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chatApp.SecureTeamSynk.Models.Company;
import com.chatApp.SecureTeamSynk.Models.Group;
import com.chatApp.SecureTeamSynk.Services.GroupService;

@Controller
public class HomeController {
	
	@Autowired
    private GroupService groupService;
	
	@GetMapping("/home")
    public String companyHome(Model model) {
        List<Group> groups = groupService.listGroups(); 

        // Pass the groups to the Thymeleaf template
        model.addAttribute("groups", groups);
        model.addAttribute("message", "Welcome to the Company Page");
        return "home";
    }
	
	@GetMapping("/master")
    public String master() {
        return "master";
    }
	
	@GetMapping("/chatScreen")
    public String chatScreen() {
        return "chatScreen";
    }

}
