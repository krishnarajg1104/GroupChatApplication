package com.chatApp.SecureTeamSynk.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.SecureTeamSynk.Models.Company;
import com.chatApp.SecureTeamSynk.Repositories.CompanyRepository;

@Service
public class CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;

    public Company createCompany(Company company) {
        try {
            if (company.getCreatedDateTime() == null) {
                company.setCreatedDateTime(LocalDateTime.now());
            }
            Company createdCompany = companyRepository.save(company);
            log.info("Company created successfully with ID: {}", createdCompany.getCompanyId());
            return createdCompany;
        } catch (Exception e) {
            log.error("Error creating company: ", e);
            throw e;
        }
    }

    public List<Company> listCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepository.findByCompanyId(companyId);
    }

    public void updateCompanyStatus(Integer companyId, Boolean isActive) {
        try {
            Company company = companyRepository.findByCompanyId(companyId);
            if (company != null) {
                company.setIsActive(isActive);
                company.setModifiedDateTime(LocalDateTime.now());
                companyRepository.save(company);
                log.info("Company status updated successfully for ID: {}", companyId);
            } else {
                log.warn("Company not found for ID: {}", companyId);
            }
        } catch (Exception e) {
            log.error("Error updating company status: ", e);
            throw e;
        }
    }

    public void updateCompany(Integer companyId, String companyName, String address, String location, boolean isActive) {
        try {
            Company existingCompany = companyRepository.findByCompanyId(companyId);
            if (existingCompany != null) {
                existingCompany.setCompanyName(companyName);
                existingCompany.setAddress(address);
                existingCompany.setLocation(location);
                existingCompany.setIsActive(isActive);
                existingCompany.setModifiedDateTime(LocalDateTime.now());
                companyRepository.save(existingCompany);
                log.info("Company updated successfully with ID: {}", companyId);
            } else {
                log.warn("Company not found for ID: {}", companyId);
                throw new IllegalArgumentException("Company not found for ID: " + companyId);
            }
        } catch (Exception e) {
            log.error("Error updating company: ", e);
            throw e;
        }
    }
}