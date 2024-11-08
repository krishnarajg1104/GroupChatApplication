package com.chatApp.SecureTeamSynk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.SecureTeamSynk.Models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUserName(String userName);
    User findByStaffNo(String staffNo);
    List<User> findByIsActive(Boolean isActive);

}
