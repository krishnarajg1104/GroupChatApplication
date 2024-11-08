package com.chatApp.SecureTeamSynk.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.SecureTeamSynk.Models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Group findByGroupName(String groupName);
    List<Group> findByIsActive(Boolean isActive);
}
