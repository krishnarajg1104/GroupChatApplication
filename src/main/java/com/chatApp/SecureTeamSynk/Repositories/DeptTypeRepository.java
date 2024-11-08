package com.chatApp.SecureTeamSynk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.SecureTeamSynk.Models.DeptType;

import java.util.List;

@Repository
public interface DeptTypeRepository extends JpaRepository<DeptType, Byte> {
    
    DeptType findByDeptTypeName(String deptTypeName);
    DeptType findByDeptTypeId(Byte deptTypeId);
    List<DeptType> findByIsActive(Boolean isActive); // Changed Byte to Boolean
}
