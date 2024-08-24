package com.ashokit.counsellor.service.portal.apis;


import com.ashokit.counsellor.service.portal.dto.DashboardResponse;
import com.ashokit.counsellor.service.portal.dto.VIewEnquiresFilterRequest;
import com.ashokit.counsellor.service.portal.entity.EnquiryEntity;
import com.ashokit.counsellor.service.portal.service.CounsellorService;
import com.ashokit.counsellor.service.portal.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/inquiries")
@RequiredArgsConstructor
@Slf4j
public class InquiriesController {
    private final EnquiryService enquiryService;
    private final CounsellorService counsellorService;

    @GetMapping("/inq")
    public String addInquiries(Model model){
        EnquiryEntity enquiries = null;
        enquiries = new EnquiryEntity();
        model.addAttribute("enquiries",enquiries);
        return "add_inq";
    }


    @PostMapping("/inq")
    public String addInquiriesFormProcessing(EnquiryEntity enquiries, HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        Integer counsellor_id = (Integer)session.getAttribute("counsellor_id");

        Boolean result = this.enquiryService.addEnquiry(enquiries,counsellor_id);
        if(result){
            model.addAttribute("successMessage","Inquires Added");
        }
        else{
            model.addAttribute("failMessage","Inquires Not added");
        }
        enquiries = new EnquiryEntity();
        model.addAttribute("enquiries",enquiries);
        return "add_inq";


    }
    @PostMapping("/filter-enq")
    public String filterEnqForm(VIewEnquiresFilterRequest vIewEnquiresFilterRequest,HttpServletRequest request,Model model){

        HttpSession session = request.getSession(false);
        Integer counsellor_id = (Integer)session.getAttribute("counsellor_id");

        List<EnquiryEntity> enqList = this.enquiryService.getEnquiresWithFilter(vIewEnquiresFilterRequest, counsellor_id);
        model.addAttribute("enquires",enqList);
        model.addAttribute("enquiresList",vIewEnquiresFilterRequest);
        return "view_inq";
    }
    @GetMapping("/view-enq")
    public String viewEnquires(HttpServletRequest request,Model model){

        HttpSession session = request.getSession(false);
        Integer counsellor_id = (Integer)session.getAttribute("counsellor_id");
        List<EnquiryEntity> enqList = this.enquiryService.getAllEnquires(counsellor_id);
        model.addAttribute("enquires",enqList);
        //search form binding object
        VIewEnquiresFilterRequest enquiresList = new VIewEnquiresFilterRequest();
        model.addAttribute("enquiresList",enquiresList);
        return "view_inq";
    }


    @GetMapping("/edit-enquiry")
    public String editEnquiry(@RequestParam("id") int id, Model model){
        EnquiryEntity enquiries =this.enquiryService.getEnquiryById(id);

        model.addAttribute("enquiries",enquiries);

        return "add_inq";

    }


}
