package com.ashokit.counsellor.service.portal.service.impl;

import com.ashokit.counsellor.service.portal.dto.DashboardResponse;
import com.ashokit.counsellor.service.portal.entity.CounsellorEntity;
import com.ashokit.counsellor.service.portal.entity.EnquiryEntity;
import com.ashokit.counsellor.service.portal.exception.AccountAlreadyRegisterException;
import com.ashokit.counsellor.service.portal.exception.AccountNotRegisterException;
import com.ashokit.counsellor.service.portal.exception.CounsellorDoesNotExistException;
import com.ashokit.counsellor.service.portal.exception.EmailOrPasswordMissMatchException;
import com.ashokit.counsellor.service.portal.repo.CounsellorRepository;
import com.ashokit.counsellor.service.portal.repo.EnquiryRepository;
import com.ashokit.counsellor.service.portal.service.CounsellorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounsellorServiceImpl implements CounsellorService {
    private final CounsellorRepository counsellorRepository;
    private final EnquiryRepository enquiryRepository;

    @Override
    public Optional<CounsellorEntity> findByEmail(String email) {
        return this.counsellorRepository.findByEmail(email);
    }

    @Override
    public CounsellorEntity login(String email, String password) {
        log.info("Entered into login service {}",this.getClass());
        Optional<CounsellorEntity> optionalCounsellor = this.counsellorRepository.findByEmail(email);
        if(optionalCounsellor.isPresent()){
           CounsellorEntity counsellor = optionalCounsellor.get();
           if(counsellor.getEmail().equals(email) && counsellor.getPassword().equals(password)){
               log.debug("entered email {} and password {} is ",email, password);
               return counsellor;
           }
           log.debug("You have entered wrong email {} or password {}",email,password);
          throw new EmailOrPasswordMissMatchException("Email or Password is wrong");
        }
        throw new AccountNotRegisterException("You have not register with this email id");
    }

    @Override
    public CounsellorEntity register(CounsellorEntity counsellor) {
        log.info("Entered into register service {}",this.getClass());
        Optional<CounsellorEntity> counsellorEntityOptional= this.counsellorRepository.findByEmail(counsellor.getEmail());
        if(counsellorEntityOptional.isPresent()){
            throw new AccountAlreadyRegisterException("you have already register with this email");
        }
       return this.counsellorRepository.save(counsellor);
    }

    @Override
    public DashboardResponse getDashboardInfo(Integer counsellor_id) {
        log.info("Entered into dashboard Response service {}", this.getClass());
        DashboardResponse dashboardResponse = new DashboardResponse();
        Optional<CounsellorEntity> counsellorEntityOptional= this.counsellorRepository.findById(counsellor_id);
        if(counsellorEntityOptional.isPresent()){
          CounsellorEntity counsellor = counsellorEntityOptional.get();
            List<EnquiryEntity> enqList = this.enquiryRepository.getEnquiresByCounsellorId(counsellor.getCounsellor_id());
            int totalEnquires = enqList.size();
            int enrolledEnquires = enqList.stream().filter(e -> e.getInquiriesStatus().equals("Enrolled"))
                    .toList().size();
            int openEnquires = enqList.stream().filter(e -> e.getInquiriesStatus().equals("Open"))
                    .toList().size();
            int lostEnquires = enqList.stream().filter(e -> e.getInquiriesStatus().equals("Lost"))
                    .toList().size();
            dashboardResponse.setTotalEnquires(totalEnquires);
            dashboardResponse.setEnrolledEnquires(enrolledEnquires);
            dashboardResponse.setOpenEnquires(openEnquires);
            dashboardResponse.setLostEnquires(lostEnquires);
            return dashboardResponse;
        }
        log.debug("Counsellor does not exist {}",counsellor_id);
        throw new CounsellorDoesNotExistException("Counsellor Doest not exit by this id");




    }
}
