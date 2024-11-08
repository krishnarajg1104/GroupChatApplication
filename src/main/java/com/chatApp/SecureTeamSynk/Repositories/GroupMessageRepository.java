package com.chatApp.SecureTeamSynk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.SecureTeamSynk.Models.GroupMessage;

import java.util.List;

@Repository
public interface GroupMessageRepository extends JpaRepository<GroupMessage, Long> {

    List<GroupMessage> findByGroupIdAndIsActive(Integer groupId, Boolean isActive);

    List<GroupMessage> findByGroupId(Integer groupId);

	GroupMessage findTopByGroupIdOrderByTimestampDesc(Integer groupId);
}
