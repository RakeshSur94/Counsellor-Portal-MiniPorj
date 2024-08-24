package com.ashokit.counsellor.service.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DashboardResponse {
    private Integer totalEnquires;
    private Integer openEnquires;
    private Integer enrolledEnquires;
    private Integer lostEnquires;
}
