package com.chatApp.SecureTeamSynk.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.SecureTeamSynk.Models.Branch;


@Repository
public interface BranchRepository extends JpaRepository<Branch, Byte> {
    
    Branch findByBranchName(String branchName);
    Branch findByBranchId(Byte branchId);
    List<Branch> findByCompanyId(Integer companyId);
    List<Branch> findByIsActive(Boolean isActive);
}