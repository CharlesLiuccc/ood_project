import {delCookie, getCookie, isLogin} from "./cookie.js";

window.onload=function(){
    if(!isLogin()){
        alert("please sign in!");
        window.location.href="login";
    }
    else {
        getRisk();
        let name = document.getElementById("name");
        name.innerHTML = name.innerHTML + getCookie("name");
        //alert(name.innerHTML+getCookie("name"));
        document.getElementById("sign_out").addEventListener("click", logout);
    }
}

function logout(){
    delCookie("id");
    delCookie("name");
    delCookie("catalog_id");
    window.location.replace("http:localhost:8080/login");
}

function getRisk(){
    let xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("microsoft.XMLHttp");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let risk = parseInt(xhr.responseText);
                if(risk<30){
                    document.getElementById("color").style.backgroundColor="#3CF072";
                }
                else if(risk<45){
                    document.getElementById("color").style.backgroundColor="#F5EB2B";
                }
                else{
                    document.getElementById("color").style.backgroundColor="#F51E09";
                }
            } else
                alert("please check your net work" + "login error" + xhr.responseText);
        }

    };
    xhr.open("post", "http://localhost:8080/health/getRisk", false);
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send("catalog_id="+getCookie("catalog_id"));
}