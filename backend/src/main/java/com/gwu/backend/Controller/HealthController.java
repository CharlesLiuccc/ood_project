package com.gwu.backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.gwu.backend.DAO.CatalogDAO;
import com.gwu.backend.DAO.UserDAO;
import com.gwu.backend.Model.Health;
import com.gwu.backend.Service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(maxAge = 3600,origins = "*")
@RequestMapping("/health")
public class HealthController {
    @Autowired
    HealthService healthService;
    @Autowired
    CatalogDAO catalogDAO;

    private Health health = new Health("963700760249-rugv53i8ubvj9plf6rqg5d8l8upv84rt.apps.googleusercontent.com","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");

    @RequestMapping("/sendToken")
    public String sendToken(@RequestParam String token) throws IOException {
        health.setToken(token);
        System.out.println(token);
        healthService.getFromGoogle(health);
        return JSONObject.toJSONString(0);
    }

    @RequestMapping(value = "/getRisk",method = RequestMethod.POST)
    public String getRisk(@RequestParam String catalog_id){
        return JSONObject.toJSONString(catalogDAO.findById(Integer.parseInt(catalog_id)).getRisk());
    }


}
