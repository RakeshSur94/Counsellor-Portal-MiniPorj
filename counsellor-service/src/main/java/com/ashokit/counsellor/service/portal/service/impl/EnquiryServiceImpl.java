package com.ashokit.counsellor.service.portal.service.impl;

import com.ashokit.counsellor.service.portal.dto.VIewEnquiresFilterRequest;
import com.ashokit.counsellor.service.portal.entity.CounsellorEntity;
import com.ashokit.counsellor.service.portal.entity.EnquiryEntity;
import com.ashokit.counsellor.service.portal.exception.CounsellorDoesNotExistException;
import com.ashokit.counsellor.service.portal.repo.CounsellorRepository;
import com.ashokit.counsellor.service.portal.repo.EnquiryRepository;
import com.ashokit.counsellor.service.portal.service.EnquiryService;


import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnquiryServiceImpl implements EnquiryService {
    private final CounsellorRepository counsellorRepository;
    private final EnquiryRepository enquiryRepository;
    @Override
    public Boolean addEnquiry(EnquiryEntity enquiryEntity, Integer counsellor_id) {
        Optional<CounsellorEntity> counsellorEntityOptional = this.counsellorRepository.findById(counsellor_id);
        if(counsellorEntityOptional.isPresent()){
            CounsellorEntity counsellor = counsellorEntityOptional.get();
            enquiryEntity.setCounsellor(counsellor);
            this.enquiryRepository.save(enquiryEntity);
            return true;
        }

        throw new CounsellorDoesNotExistException("Counsellor id does not exist");
    }

    @Override
    public List<EnquiryEntity> getAllEnquires(Integer counsellor_id) {
        Optional<CounsellorEntity> counsellorEntityOptional = this.counsellorRepository.findById(counsellor_id);
        if(counsellorEntityOptional.isPresent()){
            return this.enquiryRepository.findAll();
        }
        throw new CounsellorDoesNotExistException("Counsellor id does not exist");
    }

    @Override
    public List<EnquiryEntity> getEnquiresWithFilter(VIewEnquiresFilterRequest filterRequest, Integer counsellor_id) {
       EnquiryEntity enquiry = null;
       enquiry = new EnquiryEntity();
       if(StringUtils.isNotEmpty(filterRequest.getClassMode())){
           enquiry.setClassMode(filterRequest.getClassMode());
       }
        if(StringUtils.isNotEmpty(filterRequest.getCourseName())){
            enquiry.setCourseName(filterRequest.getCourseName());
        }
        if(StringUtils.isNotEmpty(filterRequest.getInquiriesStatus())){
            enquiry.setInquiriesStatus(filterRequest.getInquiriesStatus());
        }
        CounsellorEntity counsellor = this.counsellorRepository.findById(counsellor_id).orElse(null);
        enquiry.setCounsellor(counsellor);

        Example<EnquiryEntity> of = Example.of(enquiry);
        return this.enquiryRepository.findAll(of);
    }

    @Override
    public EnquiryEntity getEnquiryById(Integer enqId) {
        Optional<EnquiryEntity> enquiryEntityOptional = this.enquiryRepository.findById(enqId);
        if(enquiryEntityOptional.isPresent()){
            return enquiryEntityOptional.get();
        }
        throw new RuntimeException("enquires id does not exist");

    }
}
