package com.gwu.backend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("main")
    public String main(){
        return "main";
    }
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    @RequestMapping("SymptomInfo")
    public String SymptomInfo(){
        return "SymptomInfo";
    }
    @RequestMapping("DoctorVisitInfo")
    public String DoctorVisitInfo(){
        return "DoctorVisitInfo";
    }
    @RequestMapping("form")
    public String form(){
        return "form";
    }
    @RequestMapping("MedicineInfo")
    public String MedicineInfo(){
        return "MedicineInfo";
    }
    @RequestMapping("Register")
    public String Register(){
        return "Register";
    }
    @RequestMapping("RelatedNews")
    public String RelatedNews(){
        return "RelatedNews";
    }
    @RequestMapping("TakeOutsInfo")
    public String TakeOutsInfo(){
        return "TakeOutsInfo";
    }
    @RequestMapping("table")
    public String table(){
        return "table";
    }
    @RequestMapping("TripsInfo")
    public String TripsInfo(){
        return "TripsInfo";
    }
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }
}
