package com.chatApp.SecureTeamSynk.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "m_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name", nullable = false, unique = true, length = 20)
    private String userName;

    @Column(name = "staff_no", nullable = false, unique = true, length = 10)
    private String staffNo;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "staff_first_name", nullable = false, length = 25)
    private String staffFirstName;

    @Column(name = "staff_last_name", nullable = false, length = 25)
    private String staffLastName;

    @Column(name = "role", nullable = false, length = 10)
    private String role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "cdt", nullable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "clmid", nullable = false)
    private Integer createdBy = 1;

    @Column(name = "mdt")
    private LocalDateTime modifiedDateTime;

    @Column(name = "mlmid")
    private Integer modifiedBy;

    // Default constructor
    public User() {
        this.createdDateTime = LocalDateTime.now(); // Initialize with current time
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
