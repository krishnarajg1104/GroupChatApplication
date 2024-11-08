package com.chatApp.SecureTeamSynk.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.SecureTeamSynk.Models.User;
import com.chatApp.SecureTeamSynk.Repositories.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public String validateUser(String username, String password) {
        User user = userRepository.findByUserName(username);
        
        if (user == null) {
            return "Username is invalid";
        }
        
        if (!user.getPassword().equals(password)) {
            return "Password is invalid";
        }
        
        if (!user.getIsActive()) {
            return "User account is inactive";
        }
        
        return "Login successful";
    }
}
