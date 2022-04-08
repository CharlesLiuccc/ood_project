package com.gwu.backend.Controller.Info;

import com.alibaba.fastjson.JSONObject;
import com.gwu.backend.DAO.CatalogDAO;
import com.gwu.backend.DAO.Info.TakeoutDAO;
import com.gwu.backend.Model.Catalog;
import com.gwu.backend.Model.Info.Takeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@CrossOrigin(maxAge = 3600,origins = "*")
@RequestMapping("/takeout")
public class TakeoutController {
    @Autowired
    TakeoutDAO takeoutDAO;
    @Autowired
    CatalogDAO catalogDAO;

    @RequestMapping("/getAllInfo")
    public String getAllInfo(@RequestParam String catalog_id){
        ArrayList<Takeout> result = new ArrayList<>();
        result=takeoutDAO.findByCatalog(Integer.parseInt(catalog_id));
        return JSONObject.toJSONString(result);
    }

    @RequestMapping("/addInfo")
    public String addInfo(@RequestParam String catalog_id,String place,String date,String detail){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Takeout current_takeout = new Takeout(Integer.parseInt(catalog_id),timestamp.toString(),place,date,detail);
        if(takeoutDAO.addInfo(current_takeout)){
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
    public String deleteInfo(@RequestParam String info_id){
        Takeout current_takeout = takeoutDAO.findById(Integer.parseInt(info_id));
        if(current_takeout.getCatalog_id()==-1){
            //info does not exist
            return JSONObject.toJSONString(3);
        }
        else{
            if(takeoutDAO.deleteInfo(Integer.parseInt(info_id))){
                Catalog current_catalog = catalogDAO.findById(current_takeout.getCatalog_id());
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
