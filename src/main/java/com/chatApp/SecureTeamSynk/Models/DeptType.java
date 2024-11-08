package com.chatApp.SecureTeamSynk.Models;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "m_dept_type")
public class DeptType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_type_id")
    private Byte deptTypeId;

    @Column(name = "dept_type_name", unique = true, nullable = false, length = 25)
    private String deptTypeName;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive; // Using Boolean

    @Column(name = "cdt", nullable = false)
    private LocalDateTime cdt;

    @Column(name = "clmid", nullable = false)
    private Integer clmid;

    @Column(name = "mdt")
    private LocalDateTime mdt;

    @Column(name = "mlmid")
    private Integer mlmid;

    // Getters and Setters
    public Byte getDeptTypeId() {
        return deptTypeId;
    }

    public void setDeptTypeId(Byte deptTypeId) {
        this.deptTypeId = deptTypeId;
    }

    public String getDeptTypeName() {
        return deptTypeName;
    }

    public void setDeptTypeName(String deptTypeName) {
        this.deptTypeName = deptTypeName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCdt() {
        return cdt;
    }

    public void setCdt(LocalDateTime cdt) {
        this.cdt = cdt;
    }

    public Integer getClmid() {
        return clmid;
    }

    public void setClmid(Integer clmid) {
        this.clmid = clmid;
    }

    public LocalDateTime getMdt() {
        return mdt;
    }

    public void setMdt(LocalDateTime mdt) {
        this.mdt = mdt;
    }

    public Integer getMlmid() {
        return mlmid;
    }

    public void setMlmid(Integer mlmid) {
        this.mlmid = mlmid;
    }
}
