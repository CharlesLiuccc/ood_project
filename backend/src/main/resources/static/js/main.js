import {delCookie, getCookie, isLogin} from "./cookie.js";

window.onload=function(){
    if(!isLogin()){
        alert("please sign in!");
        window.location.href="login";
    }
    let name = document.getElementById("name");
    name.innerHTML=name.innerHTML+getCookie("name");
    //alert(name.innerHTML+getCookie("name"));

    document.getElementById("sign_out").addEventListener("click",logout);

}

function logout(){
    delCookie("id");
    delCookie("name");
    delCookie("catalog_id");
    window.location.href("/login")
}