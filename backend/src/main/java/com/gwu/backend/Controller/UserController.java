package com.gwu.backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.gwu.backend.DAO.CatalogDAO;
import com.gwu.backend.DAO.UserDAO;
import com.gwu.backend.Model.Catalog;
import com.gwu.backend.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@CrossOrigin(maxAge = 3600,origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserDAO userDAO;
    @Autowired
    public CatalogDAO catalogDAO;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String UserLogin(@RequestParam String user_mail,String user_pwd){
        System.out.println(user_mail+user_pwd);

        User current_user=userDAO.findByMail(user_mail);
        //mail does not exist
        if(current_user.getUser_id()==-1){
            return JSONObject.toJSONString(2);
        }
        //wrong password
        if(!Objects.equals(current_user.getUser_pwd(), user_pwd)){
            return JSONObject.toJSONString(1);
        }
        //login success
        Catalog current_catalog = catalogDAO.findByUser(current_user.getUser_id());
        return JSONObject.toJSONString(0)+",user_id:"+JSONObject.toJSONString(current_user.getUser_id())+",user_name:"+JSONObject.toJSONString(current_user.getUser_name())+",catalog_id:"+JSONObject.toJSONString(current_catalog.getCatalog_id());
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public String UserRegister(@RequestParam String user_mail,String user_name,String user_pwd){
        if(userDAO.findByMail(user_mail).getUser_id()==-1){
            User new_user = new User(user_mail,user_name,user_pwd);
            if(userDAO.addUser(new_user)){

                int new_user_id=userDAO.findByMail(new_user.getUser_mail()).getUser_id();
                if (catalogDAO.addCatalog(new_user_id)) {
                    //add new user succeed
                    return JSONObject.toJSONString(0);
                }
                else {
                    //database error: add new catalog fail
                    userDAO.deleteUser(user_mail);
                    return JSONObject.toJSONString(1);
                }
            }
            else{
                //database error: add new user fail
                return JSONObject.toJSONString(1);
            }
        }
        //mail already exists
        else{
            return JSONObject.toJSONString(2);
        }
    }

    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    @ResponseBody
    public String ChangePassword(@RequestParam String user_id, String old_pwd,String new_pwd){
        User current_user = userDAO.findByID(Integer.parseInt(user_id));
        //incorrect old password
        if(!Objects.equals(current_user.getUser_pwd(), old_pwd)){
            return JSONObject.toJSONString(2);
        }
        else{
            current_user.setUser_pwd(new_pwd);
            //change pwd succeed
            if(userDAO.editUser(current_user)){
                return JSONObject.toJSONString(0);
            }
            //database error
            else{return JSONObject.toJSONString(1);}
        }
    }

    @RequestMapping(value = "/changeInfo",method = RequestMethod.POST)
    @ResponseBody
    public String ChangeUserInfo(@RequestParam String user_id, String old_pwd,String user_mail,String user_name){
        User current_user = userDAO.findByID(Integer.parseInt(user_id));
        //incorrect old password
        if(!Objects.equals(current_user.getUser_pwd(), old_pwd)){
            return JSONObject.toJSONString(2);
        }
        else{
            current_user.setUser_mail(user_mail);
            current_user.setUser_name(user_name);
            //change pwd succeed
            if(userDAO.editUser(current_user)){
                return JSONObject.toJSONString(0);
            }
            //database error
            else{return JSONObject.toJSONString(1);}
        }
    }

    @RequestMapping(value = "/catalog",method = RequestMethod.POST)
    @ResponseBody
    public String getCatalog_id(@RequestParam String user_id){
        Catalog current_catalog = catalogDAO.findByUser(Integer.parseInt(user_id));
        if(current_catalog.getCatalog_id()==-1){
            //no such catalog, please contact the administrator
            return JSONObject.toJSONString(2);
        }
        else{
            return JSONObject.toJSONString(0)+",catalog_id:"+JSONObject.toJSONString(current_catalog.getCatalog_id())
                    +",amount:"+JSONObject.toJSONString(current_catalog.getAmount());
        }
    }

    @RequestMapping(value = "/clearRisk",method = RequestMethod.POST)
    @ResponseBody
    public String clearRisk(@RequestParam String catalog_id){
        catalogDAO.updateRisk(Integer.parseInt(catalog_id),0);
        return JSONObject.toJSONString(0);
    }
}
