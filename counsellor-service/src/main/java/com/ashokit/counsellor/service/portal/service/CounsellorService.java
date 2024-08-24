package com.ashokit.counsellor.service.portal.service;

import com.ashokit.counsellor.service.portal.dto.DashboardResponse;
import com.ashokit.counsellor.service.portal.entity.CounsellorEntity;

import java.util.Optional;

public interface CounsellorService {
    public Optional<CounsellorEntity> findByEmail(String email);
    public CounsellorEntity login(String email, String password);
    public CounsellorEntity register(CounsellorEntity counsellor);
    public DashboardResponse getDashboardInfo(Integer counsellor_id);
}
