package com.ashokit.counsellor.service.portal.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VIewEnquiresFilterRequest {
    private String courseName;
    private String studentPhoneNumber;
    private String classMode;
    private String inquiriesStatus;
}
