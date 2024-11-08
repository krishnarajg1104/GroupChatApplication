package com.chatApp.SecureTeamSynk.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.SecureTeamSynk.Models.Branch;
import com.chatApp.SecureTeamSynk.Models.Department;
import com.chatApp.SecureTeamSynk.Models.DeptType;
import com.chatApp.SecureTeamSynk.Repositories.DepartmentRepository;

@Service
public class DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        try {
            if (department.getCreatedDateTime() == null) {
                department.setCreatedDateTime(LocalDateTime.now());
            }
            Department createdDepartment = departmentRepository.save(department);
            log.info("Department created successfully with ID: {}", createdDepartment.getDeptId());
            return createdDepartment;
        } catch (Exception e) {
            log.error("Error creating department: ", e);
            throw e;
        }
    }

    public List<Department> listDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Short deptId) {
        return departmentRepository.findByDeptId(deptId);
    }

    public void updateDepartmentStatus(Short deptId, Boolean isActive) {
        try {
            Department department = departmentRepository.findByDeptId(deptId);
            if (department != null) {
                department.setIsActive(isActive);
                department.setModifiedDateTime(LocalDateTime.now());
                departmentRepository.save(department);
                log.info("Department status updated successfully for ID: {}", deptId);
            } else {
                log.warn("Department not found for ID: {}", deptId);
            }
        } catch (Exception e) {
            log.error("Error updating department status: ", e);
            throw e;
        }
    }

    public void updateDepartment(Short deptId, String deptName, DeptType deptType, Branch branch, Boolean isActive) {
        try {
            Department existingDepartment = departmentRepository.findByDeptId(deptId);
            if (existingDepartment != null) {
                existingDepartment.setDeptName(deptName);
                existingDepartment.setDeptType(deptType);
                existingDepartment.setBranch(branch);
                existingDepartment.setIsActive(isActive);
                existingDepartment.setModifiedDateTime(LocalDateTime.now());
                departmentRepository.save(existingDepartment);
                log.info("Department updated successfully with ID: {}", deptId);
            } else {
                log.warn("Department not found for ID: {}", deptId);
                throw new IllegalArgumentException("Department not found for ID: " + deptId);
            }
        } catch (Exception e) {
            log.error("Error updating department: ", e);
            throw e;
        }
    }
}
