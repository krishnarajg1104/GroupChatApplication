package com.chatApp.SecureTeamSynk.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chatApp.SecureTeamSynk.Models.GroupMessage;
import com.chatApp.SecureTeamSynk.Services.GroupMessageService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class GroupMessageController {

    @Autowired
    private GroupMessageService groupMessageService;

    @PostMapping
    public GroupMessage sendMessage(@RequestBody GroupMessage groupMessage) {
        return groupMessageService.sendMessage(groupMessage);
    }

    @GetMapping("/group/{groupId}")
    public List<GroupMessage> getMessagesByGroupId(@PathVariable Integer groupId) {
        return groupMessageService.getMessagesByGroupId(groupId);
    }
}
