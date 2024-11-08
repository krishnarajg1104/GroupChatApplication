package com.chatApp.SecureTeamSynk.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chatApp.SecureTeamSynk.Services.LoginService;
import com.chatApp.SecureTeamSynk.Services.UserService;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }
    
    @GetMapping("/resetPassword")
    public String resetPasswordPage(Model model) {
        return "resetPassword";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {
        
        String message = loginService.validateUser(username, password);

        if (message.equals("Login successful")) {
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/api/login";
        }
    }
    
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("userName") String userName,
                                @RequestParam("newPassword") String newPassword,
                                RedirectAttributes redirectAttributes) {

        boolean result = userService.resetPassword(userName, newPassword);

        if (result) {
            
            redirectAttributes.addFlashAttribute("message", "Password reset successfully.");
        } else {
            
            redirectAttributes.addFlashAttribute("message", "Error resetting password. User not found.");
        }
        
        return "redirect:/api/login";
    }

}
