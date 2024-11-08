package com.chatApp.SecureTeamSynk.Models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "m_group_msg")
public class GroupMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_msg_id")
    private Long groupMsgId;

    @Column(name = "msg", nullable = false)
    private String msg;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @Column(name = "dept_id", nullable = false)
    private Short deptId;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "cdt", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "clmid", nullable = false)
    private Integer createdBy;

    @Column(name = "mdt")
    private LocalDateTime modifiedDate;

    @Column(name = "mlmid")
    private Integer modifiedBy;

	public Long getGroupMsgId() {
		return groupMsgId;
	}

	public void setGroupMsgId(Long groupMsgId) {
		this.groupMsgId = groupMsgId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Short getDeptId() {
		return deptId;
	}

	public void setDeptId(Short deptId) {
		this.deptId = deptId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

    
}
