package com.ashokit.counsellor.service.portal.utils;

import com.ashokit.counsellor.service.portal.entity.CounsellorEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrepareMockObjects {

    public static CounsellorEntity getCounsellor(){
        return CounsellorEntity.builder()
                .email("rakeshsur72@gmail.com")
                .password("1234")
                .phoneNumber("1234567891")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();
    }
}
