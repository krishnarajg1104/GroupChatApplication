package com.chatApp.SecureTeamSynk.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatApp.SecureTeamSynk.Models.User;
import com.chatApp.SecureTeamSynk.Repositories.UserRepository;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        try {
            if (user.getCreatedDateTime() == null) {
                user.setCreatedDateTime(LocalDateTime.now());
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User createdUser = userRepository.save(user);
            log.info("User created successfully with ID: {}", createdUser.getUserId());
            return createdUser;
        } catch (Exception e) {
            log.error("Error creating user: ", e);
            throw e;
        }
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void updateUserStatus(Integer userId, Boolean isActive) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                user.setIsActive(isActive);
                user.setModifiedDateTime(LocalDateTime.now());
                userRepository.save(user);
                log.info("User status updated successfully for ID: {}", userId);
            } else {
                log.warn("User not found for ID: {}", userId);
            }
        } catch (Exception e) {
            log.error("Error updating user status: ", e);
            throw e;
        }
    }

    public void updateUser(Integer userId, String userName, String staffNo, String password, 
                           String staffFirstName, String staffLastName, String role, boolean isActive) {
        try {
            User existingUser = userRepository.findById(userId).orElse(null);
            if (existingUser != null) {
                existingUser.setUserName(userName);
                existingUser.setStaffNo(staffNo);
                if (password != null && !password.isEmpty()) {
                    existingUser.setPassword(passwordEncoder.encode(password));
                }
                existingUser.setStaffFirstName(staffFirstName);
                existingUser.setStaffLastName(staffLastName);
                existingUser.setRole(role);
                existingUser.setIsActive(isActive);
                existingUser.setModifiedDateTime(LocalDateTime.now());
                userRepository.save(existingUser);
                log.info("User updated successfully with ID: {}", userId);
            } else {
                log.warn("User not found for ID: {}", userId);
                throw new IllegalArgumentException("User not found for ID: " + userId);
            }
        } catch (Exception e) {
            log.error("Error updating user: ", e);
            throw e;
        }
    }

    public boolean resetPassword(String userName, String newPassword) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            log.info("Password reset successfully for user: {}", userName);
            return true;
        } else {
            log.warn("User not found for username: {}", userName);
            return false;
        }
    }
}
