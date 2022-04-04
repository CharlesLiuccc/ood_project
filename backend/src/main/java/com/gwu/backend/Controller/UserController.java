package com.gwu.backend.Controller;

import com.alibaba.fastjson.JSONObject;
import com.gwu.backend.DAO.UserDAO;
import com.gwu.backend.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
@ResponseBody
@CrossOrigin(maxAge = 3600,origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserDAO userDAO;

    @RequestMapping("/login")
    public String UserLogin(@RequestParam String user_mail,String user_pwd){
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
        return JSONObject.toJSONString(0)+",user_id:"+JSONObject.toJSONString(current_user.getUser_id());
    }

    @RequestMapping("/register")
    public String UserRegister(@RequestParam String user_mail,String user_name,String user_pwd){
        if(userDAO.findByMail(user_mail).getUser_id()==-1){
            User new_user = new User(user_mail,user_name,user_pwd);
            if(userDAO.addUser(new_user)){
                //add new user succeed
                return JSONObject.toJSONString(0);
            }
            else{
                //database error
                return JSONObject.toJSONString(1);
            }
        }
        //mail already exists
        else{
            return JSONObject.toJSONString(0);
        }
    }

    @RequestMapping("/changePwd")
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

    @RequestMapping("/changeMail")
    public String ChangeUserMail(@RequestParam String user_id, String old_pwd,String user_mail){
        User current_user = userDAO.findByID(Integer.parseInt(user_id));
        //incorrect old password
        if(!Objects.equals(current_user.getUser_pwd(), old_pwd)){
            return JSONObject.toJSONString(2);
        }
        else{
            current_user.setUser_mail(user_mail);
            //change pwd succeed
            if(userDAO.editUser(current_user)){
                return JSONObject.toJSONString(0);
            }
            //database error
            else{return JSONObject.toJSONString(1);}
        }
    }


}
