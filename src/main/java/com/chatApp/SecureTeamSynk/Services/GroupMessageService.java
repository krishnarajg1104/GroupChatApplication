package com.chatApp.SecureTeamSynk.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.SecureTeamSynk.Models.GroupMessage;
import com.chatApp.SecureTeamSynk.Repositories.GroupMessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupMessageService {

    @Autowired
    private GroupMessageRepository groupMessageRepository;

    public GroupMessage saveMessage(GroupMessage message) {
        message.setCreatedDate(LocalDateTime.now());
        return groupMessageRepository.save(message);
    }

    public List<GroupMessage> getMessagesByGroupId(Integer groupId) {
        return groupMessageRepository.findByGroupIdAndIsActive(groupId, true);
    }

    public GroupMessage sendMessage(GroupMessage groupMessage) {
        return saveMessage(groupMessage);
    }
}
