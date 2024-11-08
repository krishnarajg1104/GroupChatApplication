package com.chatApp.SecureTeamSynk.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.SecureTeamSynk.Models.DeptType;
import com.chatApp.SecureTeamSynk.Repositories.DeptTypeRepository;

@Service
public class DeptTypeService {

    private static final Logger log = LoggerFactory.getLogger(DeptTypeService.class);

    @Autowired
    private DeptTypeRepository deptTypeRepository;

    public DeptType createDeptType(DeptType deptType) {
        try {
            if (deptType.getCdt() == null) {
                deptType.setCdt(LocalDateTime.now());
            }
            DeptType createdDeptType = deptTypeRepository.save(deptType);
            log.info("Department Type created successfully with ID: {}", createdDeptType.getDeptTypeId());
            return createdDeptType;
        } catch (Exception e) {
            log.error("Error creating department type: ", e);
            throw e;
        }
    }

    public List<DeptType> listDeptTypes() {
        return deptTypeRepository.findAll();
    }

    public DeptType getDeptTypeById(Byte deptTypeId) {
        return deptTypeRepository.findByDeptTypeId(deptTypeId);
    }

    public void updateDeptTypeStatus(Byte deptTypeId, Boolean isActive) {
        try {
            DeptType deptType = deptTypeRepository.findByDeptTypeId(deptTypeId);
            if (deptType != null) {
                deptType.setIsActive(isActive);
                deptType.setMdt(LocalDateTime.now());
                deptTypeRepository.save(deptType);
                log.info("Department Type status updated successfully for ID: {}", deptTypeId);
            } else {
                log.warn("Department Type not found for ID: {}", deptTypeId);
                throw new IllegalArgumentException("Department Type not found for ID: " + deptTypeId);
            }
        } catch (Exception e) {
            log.error("Error updating department type status: ", e);
            throw e;
        }
    }

    public void updateDeptType(Byte deptTypeId, String deptTypeName, Boolean isActive) {
        try {
            DeptType existingDeptType = deptTypeRepository.findByDeptTypeId(deptTypeId);
            if (existingDeptType != null) {
                existingDeptType.setDeptTypeName(deptTypeName);
                existingDeptType.setIsActive(isActive);
                existingDeptType.setMdt(LocalDateTime.now());
                deptTypeRepository.save(existingDeptType);
                log.info("Department Type updated successfully with ID: {}", deptTypeId);
            } else {
                log.warn("Department Type not found for ID: {}", deptTypeId);
                throw new IllegalArgumentException("Department Type not found for ID: " + deptTypeId);
            }
        } catch (Exception e) {
            log.error("Error updating department type: ", e);
            throw e;
        }
    }
}