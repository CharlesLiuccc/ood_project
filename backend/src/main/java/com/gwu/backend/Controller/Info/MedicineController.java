package com.gwu.backend.Controller.Info;

import com.alibaba.fastjson.JSONObject;
import com.gwu.backend.DAO.CatalogDAO;
import com.gwu.backend.DAO.Info.MedicineDAO;
import com.gwu.backend.Model.Catalog;
import com.gwu.backend.Model.Info.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@CrossOrigin(maxAge = 3600,origins = "*")
@RequestMapping(value = "/medicine")
public class MedicineController {
    @Autowired
    MedicineDAO medicineDAO;
    @Autowired
    CatalogDAO catalogDAO;

    @RequestMapping(value = "/getAllInfo",method = RequestMethod.POST)
    public String getAllInfo(@RequestParam String catalog_id){
        ArrayList<Medicine> result = new ArrayList<>();
        result=medicineDAO.findByCatalog(Integer.parseInt(catalog_id));
        int amount = result.size();
        return JSONObject.toJSONString(amount);
    }

    @RequestMapping(value = "/addInfo",method = RequestMethod.POST)
    public String addInfo(@RequestParam String catalog_id,String name,String frequency,String dosage,String detail){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Medicine current_medicine = new Medicine(Integer.parseInt(catalog_id),timestamp.toString(),name,frequency,dosage,detail);
        if(medicineDAO.addInfo(current_medicine)){
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

    @RequestMapping(value = "/deleteInfo",method = RequestMethod.POST)
    @ResponseBody
    public String deleteInfo(@RequestParam String info_id){
        Medicine current_medicine = medicineDAO.findById(Integer.parseInt(info_id));
        if(current_medicine.getCatalog_id()==-1){
            //info does not exist
            return JSONObject.toJSONString(3);
        }
        else{
            if(medicineDAO.deleteInfo(Integer.parseInt(info_id))){
                Catalog current_catalog = catalogDAO.findById(current_medicine.getCatalog_id());
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
