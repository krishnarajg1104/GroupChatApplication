package com.chatApp.SecureTeamSynk.Models;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "m_branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Byte branchId;

    @Column(name = "branch_name", unique = true, nullable = false, length = 25)
    private String branchName;

    @Column(name = "company_id", nullable = false)
    private Integer companyId; // Using Integer

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "location", nullable = false, length = 25)
    private String location;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "cdt", nullable = false)
    private LocalDateTime cdt;

    @Column(name = "clmid", nullable = false)
    private Integer clmid;

    @Column(name = "mdt")
    private LocalDateTime mdt;

    @Column(name = "mlmid")
    private Integer mlmid;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private Company company;

	public Byte getBranchId() {
		return branchId;
	}

	public void setBranchId(Byte branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

   
}
