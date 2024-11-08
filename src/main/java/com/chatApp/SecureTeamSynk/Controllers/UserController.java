package com.chatApp.SecureTeamSynk.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.chatApp.SecureTeamSynk.Models.User;
import com.chatApp.SecureTeamSynk.Services.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String userHome(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("message", "Welcome to the User Page");
        return "user";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user, Model model) {
        try {
            userService.createUser(user);
            model.addAttribute("message", "User created successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error creating user.");
            log.error("Error creating user: ", e);
        }
        model.addAttribute("user", new User());
        return "redirect:/api/user/list";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @PostMapping("/updateStatus")
    public String updateUserStatus(@RequestParam("userId") Integer userId,
                                   @RequestParam("isActive") Boolean isActive) {
        try {
            userService.updateUserStatus(userId, isActive);
        } catch (Exception e) {
            log.error("Error updating user status: ", e);
        }
        return "redirect:/api/user/list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer userId, Model model) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                model.addAttribute("user", user);
                return "editUser"; // Ensure this view exists
            } else {
                model.addAttribute("message", "User with ID " + userId + " not found");
                return "redirect:/api/user/list";
            }
        } catch (Exception e) {
            model.addAttribute("message", "Error retrieving user details.");
            log.error("Error retrieving user details: ", e);
            return "redirect:/api/user/list";
        }
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("userId") Integer userId,
                             @RequestParam("userName") String userName,
                             @RequestParam("staffNo") String staffNo,
                             @RequestParam("password") String password,
                             @RequestParam("staffFirstName") String staffFirstName,
                             @RequestParam("staffLastName") String staffLastName,
                             @RequestParam("role") String role,
                             @RequestParam("isActive") boolean isActive,
                             Model model) {
        try {
            userService.updateUser(userId, userName, staffNo, password, staffFirstName, staffLastName, role, isActive);
            model.addAttribute("message", "User updated successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Error updating user.");
            log.error("Error updating user: ", e);
        }
        return "redirect:/api/user/list";
    }

//    @PostMapping("/reset")
//    public String resetPassword(@RequestParam("userName") String userName,
//                                @RequestParam("newPassword") String newPassword,
//                                Model model) {
//        boolean result = userService.resetPassword(userName, newPassword);
//        if (result) {
//            model.addAttribute("message", "Password reset successfully.");
//        } else {
//            model.addAttribute("message", "Error resetting password. User not found.");
//        }
//        return "resetPassword";
//    }
}
