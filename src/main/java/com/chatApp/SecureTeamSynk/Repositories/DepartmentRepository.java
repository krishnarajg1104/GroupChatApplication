package com.chatApp.SecureTeamSynk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.SecureTeamSynk.Models.Department;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Short> {
    
    Department findByDeptName(String deptName);
    Department findByDeptId(Short deptId);
    List<Department> findByIsActive(Boolean isActive);

}
