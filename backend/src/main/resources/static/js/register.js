import {setCookie, getCookie, isLogin} from "./cookie.js";
window.onload=function (){
    if(isLogin()){
        window.location.replace("main");
    }
    let button = document.getElementById("register-button");
    button.addEventListener('click',register);
}

function register(){
    let user_mail = document.getElementById("email").value;
    let user_name = document.getElementById("name").value;
    let user_pwd1 = document.getElementById("password1").value;
    let user_pwd2 = document.getElementById("password2").value;

    if(user_mail.length==0){
        alert("please input your valid mail");
    }
    else if(user_name.length==0){
        alert("please input the name");
    }
    else if(user_pwd1.length==0||user_pwd2.length==0){
        alert("please input the password");
    }
    else if(user_pwd1!=user_pwd2){
        alert("retyped password not match");
    }
    else {
        let xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("microsoft.XMLHttp");
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    if(parseInt(xhr.responseText)==1){
                        alert("database error, please contact the administrator");
                    }
                    else if(parseInt(xhr.responseText)==2){
                        alert("mail has already exists!");
                    }
                    else{
                        alert("resgister succeed!");
                        window.location.href="login";
                    }

                } else
                    alert("please check your net work" + "login error" + xhr.responseText);
            }

        };
        xhr.open("post", "http://localhost:8080/user/register", false);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send("user_mail=" + user_mail + "&user_name=" + user_name+"&user_pwd="+user_pwd1);
    }
}
