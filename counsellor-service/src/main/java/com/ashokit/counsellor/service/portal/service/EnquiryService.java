package com.ashokit.counsellor.service.portal.service;

import com.ashokit.counsellor.service.portal.dto.VIewEnquiresFilterRequest;
import com.ashokit.counsellor.service.portal.entity.EnquiryEntity;

import java.util.List;

public interface EnquiryService {
    public Boolean addEnquiry(EnquiryEntity enquiryEntity,Integer counsellor_id);
    public List<EnquiryEntity> getAllEnquires(Integer counsellor_id);
    public List<EnquiryEntity> getEnquiresWithFilter(VIewEnquiresFilterRequest filterRequest,Integer counsellor_id);
    public EnquiryEntity getEnquiryById(Integer enqId);

}
