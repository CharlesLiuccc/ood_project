import {isLogin} from "./cookie.js"

window.onload=function() {
    if (!isLogin()) {
        window.location.href = "/login";
    } else {
        //alert("f");
        window.location.href = "/main";
    }
}
