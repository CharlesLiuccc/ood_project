import {setCookie, getCookie, isLogin} from "./cookie.js";
window.onload=function (){
    if(isLogin()){
        window.location.replace("main");
    }
    let button = document.getElementById("login-button");
    button.addEventListener('click',login);
}

function login(){
    let user_mail = document.getElementById("username").value;
    let user_pwd = document.getElementById("password").value;
    if(user_mail.length==0){
        alert("please input the user mail");
    }
    else if(user_pwd.length==0){
        alert("please input the password");
    }
    else {
        let xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("microsoft.XMLHttp");
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    let str = xhr.responseText.split(",");

                    if (parseInt(str[0]) == 1) {
                        alert("Wrong Password!");
                        location.reload();
                    } else if (parseInt(str[0]) == 2) {
                        alert("Mail does not exist!");
                        location.reload();
                    } else {
                        //alert(str[2].split(":")[1]);
                        setCookie("id", str[1].split(":")[1], 1);
                        let name = str[2].split(":")[1];
                        name = name.substr(1, name.length - 2);
                        setCookie("name", name, 1);
                        setCookie("catalog_id", str[3].split(":")[1], 1);
                        window.location.replace("localhost:8080/main");
                    }
                } else
                    alert("请检查您的网络连接" + "login error" + xhr.responseText);
            }

        };
        xhr.open("post", "http://localhost:8080/user/login", false);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send("user_mail=" + user_mail + "&user_pwd=" + user_pwd);
    }
}