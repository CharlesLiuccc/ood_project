package com.gwu.backend.Controller.Info;


import com.alibaba.fastjson.JSONObject;
import com.gwu.backend.DAO.CatalogDAO;
import com.gwu.backend.DAO.Info.DoctorVisitDAO;
import com.gwu.backend.Model.Catalog;
import com.gwu.backend.Model.Info.DoctorVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Controller
@CrossOrigin(maxAge = 3600,origins = "*")
@RequestMapping("/doctor")
public class DoctorVisitController {
    @Autowired
    DoctorVisitDAO doctorVisitDAO;
    @Autowired
    CatalogDAO catalogDAO;

    @RequestMapping("/getAllInfo")
    @ResponseBody
    public String getAllInfo(@RequestParam String catalog_id){
        ArrayList<DoctorVisit> result = new ArrayList<>();
        result = doctorVisitDAO.findByCatalog(Integer.parseInt(catalog_id));
        int amount = result.size();
        return JSONObject.toJSONString(amount);
    }

    @RequestMapping("/addInfo")
    @ResponseBody
    public String addInfo(@RequestParam String catalog_id,String name,String date,String detail){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DoctorVisit current_visit = new DoctorVisit(Integer.parseInt(catalog_id),timestamp.toString(),name,date,detail);
        if(doctorVisitDAO.addInfo(current_visit)){
            Catalog current_catalog = catalogDAO.findById(Integer.parseInt(catalog_id));
            current_catalog.addInfo();
            if(catalogDAO.updateAmount(Integer.parseInt(catalog_id),current_catalog.getAmount())){
                //add succeed
                return JSONObject.toJSONString(0);
            }
            else{
                //doctorVisitDAO.deleteInfo();
                //database catalog error
                return JSONObject.toJSONString(1);
            }
        }
        else{
            //database doctor_visit_info error
            return JSONObject.toJSONString(2);
        }
    }

    @RequestMapping("/deleteInfo")
    @ResponseBody
    public String deleteInfo(@RequestParam String info_id){
        DoctorVisit current_visit = doctorVisitDAO.findById(Integer.parseInt(info_id));
        if(current_visit.getCatalog_id()==-1){
            //info does not exist
            return JSONObject.toJSONString(3);
        }
        else{
            if(doctorVisitDAO.deleteInfo(Integer.parseInt(info_id))){
                //delete succeed
                Catalog current_catalog = catalogDAO.findById(current_visit.getCatalog_id());
                current_catalog.removeInfo();
                if(catalogDAO.updateAmount(current_catalog.getCatalog_id(),current_catalog.getAmount())) {
                    return JSONObject.toJSONString(0);
                }
                //database catalog error
                else{return JSONObject.toJSONString(1);}
            }
            else{
                //database doctor_visit_info error
                return JSONObject.toJSONString(2);
            }
        }
    }
}
