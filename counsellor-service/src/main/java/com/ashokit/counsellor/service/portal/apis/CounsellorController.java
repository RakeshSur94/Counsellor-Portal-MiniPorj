package com.ashokit.counsellor.service.portal.apis;

import com.ashokit.counsellor.service.portal.dto.DashboardResponse;
import com.ashokit.counsellor.service.portal.dto.ErrorMessage;
import com.ashokit.counsellor.service.portal.dto.ErrorMessageFactory;
import com.ashokit.counsellor.service.portal.entity.CounsellorEntity;
import com.ashokit.counsellor.service.portal.exception.EmailOrPasswordMissMatchException;
import com.ashokit.counsellor.service.portal.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/counsellor")
@RequiredArgsConstructor
@Slf4j
public class CounsellorController {

    private final CounsellorService counsellorService;

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/dashboard-page")
    public String getDashboard(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        Integer counsellor_id = (Integer) session.getAttribute("counsellor_id");
        DashboardResponse dashboardInfo = counsellorService.getDashboardInfo(counsellor_id);
        model.addAttribute("dashboardInfo",dashboardInfo);
        return "dashboard";
    }


    @GetMapping("/login")
    public String showLoginPage(Model model) {
        log.info("enter into login controller class  {}",this.getClass());
        CounsellorEntity counsellor=null;
        counsellor=new CounsellorEntity();
        model.addAttribute("counsellor",counsellor);

        return "login";
    }

    @PostMapping("/login")
    public String loginPageProcessing(CounsellorEntity counsellor, Model model, HttpServletRequest request){


           log.info("enter into loginPageProcessing {}", this.getClass());
           CounsellorEntity login = this.counsellorService.login(counsellor.getEmail(), counsellor.getPassword());
           log.info("Adding Model class object (result) into Model Attribute");
           if(login !=null){
               HttpSession session = request.getSession(true);
               session.setAttribute("counsellor_id",login.getCounsellor_id());
               DashboardResponse dashboardResponse = this.counsellorService.getDashboardInfo(login.getCounsellor_id());
              model.addAttribute("dashboardInfo",dashboardResponse);
              return "dashboard";
           }
           else{
             model.addAttribute("emsg","Invalid Credential");
             return "login";
           }

    }

    @GetMapping("/register")
    public String registerCounsellor( Model model){
        log.info("Entered into register Counsellor controller class {}",this.getClass());
        CounsellorEntity counsellor = null;
        counsellor = new CounsellorEntity();
        model.addAttribute("counsellor",counsellor);
        return "register_form";
    }
    @PostMapping("/register")
    public String registerCounsellorFormProcessing( CounsellorEntity counsellor,Model model){
        log.info("entered into registerCounsellor form page processing controller class {}",this.getClass());
        CounsellorEntity register = this.counsellorService.register(counsellor);
        log.info("add result in flash attribute and redirect it into login page");
        model.addAttribute("register_counsellor",register);
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "redirect:/";


    }

}
