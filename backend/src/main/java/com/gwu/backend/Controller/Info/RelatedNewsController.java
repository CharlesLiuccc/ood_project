package com.gwu.backend.Controller.Info;

import com.alibaba.fastjson.JSONObject;
import com.gwu.backend.DAO.CatalogDAO;
import com.gwu.backend.DAO.Info.RelatedNewsDAO;
import com.gwu.backend.Model.Catalog;
import com.gwu.backend.Model.Info.RelatedNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@CrossOrigin(maxAge = 3600,origins = "*")
@RequestMapping("/news")
public class RelatedNewsController {
    @Autowired
    RelatedNewsDAO relatedNewsDAO;
    @Autowired
    CatalogDAO catalogDAO;

    @RequestMapping("/getAllInfo")
    public String getAllInfo(@RequestParam String catalog_id){
        ArrayList<RelatedNews> result = new ArrayList<>();
        result=relatedNewsDAO.findByCatalog(Integer.parseInt(catalog_id));
        return JSONObject.toJSONString(result);
    }

    @RequestMapping("/addInfo")
    public String addInfo(@RequestParam String catalog_id,String date,String detail){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        RelatedNews current_news = new RelatedNews(Integer.parseInt(catalog_id),timestamp.toString(),date,detail);
        if(relatedNewsDAO.addInfo(current_news)){
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
        RelatedNews current_news = relatedNewsDAO.findById(Integer.parseInt(info_id));
        if(current_news.getCatalog_id()==-1){
            //info does not exist
            return JSONObject.toJSONString(3);
        }
        else{
            if(relatedNewsDAO.deleteInfo(Integer.parseInt(info_id))){
                Catalog current_catalog = catalogDAO.findById(current_news.getCatalog_id());
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