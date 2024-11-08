package com.chatApp.SecureTeamSynk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.SecureTeamSynk.Models.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
    Company findByCompanyName(String companyName);
    Company findByCompanyId(Integer companyId);
    List<Company> findByIsActive(Boolean isActive);

}
