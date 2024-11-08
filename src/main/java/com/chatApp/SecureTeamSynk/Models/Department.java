package com.chatApp.SecureTeamSynk.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "m_dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Short deptId;

    @Column(name = "dept_name", nullable = false, unique = true, length = 50)
    private String deptName;

    @ManyToOne
    @JoinColumn(name = "dept_type_id", nullable = false)
    private DeptType deptType;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

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
    public Department() {
        this.createdDateTime = LocalDateTime.now(); 
    }

    // Getters and Setters
    public Short getDeptId() {
        return deptId;
    }

    public void setDeptId(Short deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public DeptType getDeptType() {
        return deptType;
    }

    public void setDeptType(DeptType deptType) {
        this.deptType = deptType;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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
