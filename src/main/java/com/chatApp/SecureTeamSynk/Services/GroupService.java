package com.chatApp.SecureTeamSynk.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.SecureTeamSynk.Models.Department;
import com.chatApp.SecureTeamSynk.Models.Group;
import com.chatApp.SecureTeamSynk.Models.GroupMessage;
import com.chatApp.SecureTeamSynk.Repositories.DepartmentRepository;
import com.chatApp.SecureTeamSynk.Repositories.GroupMessageRepository;
import com.chatApp.SecureTeamSynk.Repositories.GroupRepository;


@Service
public class GroupService {

    private static final Logger log = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private GroupMessageRepository messageRepository;

    public List<Group> getAllGroups() {
        List<Group> groups = groupRepository.findAll();  // Fetch all groups from the database

        for (Group group : groups) {
            // Fetch the last message for each group (this query assumes a relationship between Group and Message)
            GroupMessage lastMessage = messageRepository.findTopByGroupIdOrderByTimestampDesc(group.getGroupId());
            if (lastMessage != null) {
                group.setLastMessage(lastMessage.getContent());  // Set the last message content in the group
            } else {
                group.setLastMessage("No messages yet");  // If no messages are found, set a default value
            }
        }
        
        return groupRepository.findAll();
    }
        

    public Group createGroup(Group group) {
        try {
            if (group.getCreatedDateTime() == null) {
                group.setCreatedDateTime(LocalDateTime.now());
            }
            if (group.getCreatedBy() == null) {
                group.setCreatedBy(1); // Default value
            }
            Group createdGroup = groupRepository.save(group);
            log.info("Group created successfully with ID: {}", createdGroup.getGroupId());
            return createdGroup;
        } catch (Exception e) {
            log.error("Error creating group: ", e);
            throw e;
        }
    }

    public List<Group> listGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Integer groupId) {
        return groupRepository.findById(groupId).orElse(null);
    }

    public void updateGroupStatus(Integer groupId, Boolean isActive) {
        try {
            Group group = groupRepository.findById(groupId).orElse(null);
            if (group != null) {
                group.setIsActive(isActive);
                group.setModifiedDateTime(LocalDateTime.now());
                groupRepository.save(group);
                log.info("Group status updated successfully for ID: {}", groupId);
            } else {
                log.warn("Group not found for ID: {}", groupId);
            }
        } catch (Exception e) {
            log.error("Error updating group status: ", e);
            throw e;
        }
    }

    public void updateGroup(Integer groupId, String groupName, Short deptId, Boolean isActive) {
        try {
            Group existingGroup = groupRepository.findById(groupId).orElse(null);
            if (existingGroup != null) {
                existingGroup.setGroupName(groupName);
                Department department = departmentRepository.findById(deptId).orElse(null);
                if (department != null) {
                    existingGroup.setDepartment(department);
                } else {
                    log.warn("Department not found for ID: {}", deptId);
                    throw new IllegalArgumentException("Department not found for ID: " + deptId);
                }
                existingGroup.setIsActive(isActive);
                existingGroup.setModifiedDateTime(LocalDateTime.now());
                groupRepository.save(existingGroup);
                log.info("Group updated successfully with ID: {}", groupId);
            } else {
                log.warn("Group not found for ID: {}", groupId);
                throw new IllegalArgumentException("Group not found for ID: " + groupId);
            }
        } catch (Exception e) {
            log.error("Error updating group: ", e);
            throw e;
        }
    }
}
