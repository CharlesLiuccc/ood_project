package com.gwu.backend.Controller.Info;


import com.alibaba.fastjson.JSONObject;
import com.gwu.backend.DAO.CatalogDAO;
import com.gwu.backend.DAO.Info.SymptomDAO;
import com.gwu.backend.Model.Catalog;
import com.gwu.backend.Model.Info.Symptom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@CrossOrigin(maxAge = 3600,origins = "*")
@RequestMapping("/symptom")
public class SymptomController {
    @Autowired
    SymptomDAO symptomDAO;
    @Autowired
    CatalogDAO catalogDAO;

    @RequestMapping(value = "/getAllInfo",method = RequestMethod.POST)
    public String getAllInfo(@RequestParam String catalog_id){
        ArrayList<Symptom> result = new ArrayList<>();
        result=symptomDAO.findByCatalog(Integer.parseInt(catalog_id));
        int amount = result.size();
        return JSONObject.toJSONString(amount);
    }

    @RequestMapping(value = "/addInfo",method = RequestMethod.POST)
    public String addInfo(@RequestParam String catalog_id,String time,String type,String detail){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Symptom current_symptom = new Symptom(Integer.parseInt(catalog_id),timestamp.toString(),time,type,detail);
        if(symptomDAO.addInfo(current_symptom)){
            Catalog current_catalog = catalogDAO.findById(Integer.parseInt(catalog_id));
            current_catalog.addInfo();
            if(catalogDAO.updateAmount(Integer.parseInt(catalog_id),current_catalog.getAmount())){
                //add succeed
                current_catalog.setRisk(current_catalog.getRisk()+5);
                catalogDAO.updateRisk(Integer.parseInt(catalog_id),current_catalog.getRisk());
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

    @RequestMapping(value = "/deleteInfo",method = RequestMethod.POST)
    public String deleteInfo(@RequestParam String info_id){
        Symptom current_symptom = symptomDAO.findById(Integer.parseInt(info_id));
        if(current_symptom.getCatalog_id()==-1){
            //info does not exist
            return JSONObject.toJSONString(3);
        }
        else{
            if(symptomDAO.deleteInfo(Integer.parseInt(info_id))){
                Catalog current_catalog = catalogDAO.findById(current_symptom.getCatalog_id());
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
