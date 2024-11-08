package com.chatApp.SecureTeamSynk.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.SecureTeamSynk.Models.Branch;
import com.chatApp.SecureTeamSynk.Models.Company;
import com.chatApp.SecureTeamSynk.Repositories.BranchRepository;
import com.chatApp.SecureTeamSynk.Repositories.CompanyRepository;

@Service
public class BranchService {

    private static final Logger log = LoggerFactory.getLogger(BranchService.class);

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Branch createBranch(Branch branch) {
        try {
            if (branch.getCdt() == null) {
                branch.setCdt(LocalDateTime.now());
            }
            Branch createdBranch = branchRepository.save(branch);
            log.info("Branch created successfully with ID: {}", createdBranch.getBranchId());
            return createdBranch;
        } catch (Exception e) {
            log.error("Error creating branch: ", e);
            throw e;
        }
    }

    public List<Branch> listBranches() {
        return branchRepository.findAll();
    }

    public Branch getBranchById(Byte branchId) {
        return branchRepository.findByBranchId(branchId);
    }

    public void updateBranchStatus(Byte branchId, Boolean isActive) {
        try {
            Branch branch = branchRepository.findByBranchId(branchId);
            if (branch != null) {
                branch.setIsActive(isActive);
                branch.setMdt(LocalDateTime.now());
                branchRepository.save(branch);
                log.info("Branch status updated successfully for ID: {}", branchId);
            } else {
                log.warn("Branch not found for ID: {}", branchId);
                throw new IllegalArgumentException("Branch not found for ID: " + branchId);
            }
        } catch (Exception e) {
            log.error("Error updating branch status: ", e);
            throw e;
        }
    }

    public void updateBranch(Byte branchId, String branchName, Integer companyId, String address, String location, Boolean isActive) {
        try {
            Branch existingBranch = branchRepository.findByBranchId(branchId);
            if (existingBranch != null) {
                existingBranch.setBranchName(branchName);
                existingBranch.setCompanyId(companyId);
                existingBranch.setAddress(address);
                existingBranch.setLocation(location);
                existingBranch.setIsActive(isActive);
                existingBranch.setMdt(LocalDateTime.now());
                branchRepository.save(existingBranch);
                log.info("Branch updated successfully with ID: {}", branchId);
            } else {
                log.warn("Branch not found for ID: {}", branchId);
                throw new IllegalArgumentException("Branch not found for ID: " + branchId);
            }
        } catch (Exception e) {
            log.error("Error updating branch: ", e);
            throw e;
        }
    }

    public List<Company> listCompanies() {
        return companyRepository.findAll();
    }
}