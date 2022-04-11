import {setCookie, getCookie, isLogin} from "./cookie.js";
window.onload=function (){

    let button = document.getElementById("takeOuts_button");
    button.addEventListener('click',takeouts);
}

function takeouts(){
    let restaurant_name = document.getElementById("restaurant_name").value;
    //let news_amount = document.getElementById("news_amount").value;
    let take_outs_date = document.getElementById("take_outs_date").value;
    let takeouts_detail = " ";
    //let user_pwd2 = document.getElementById("password2").value;

    if(restaurant_name.length==0){
        alert("please input your restaurant name");
    }

    else if(take_outs_date.length==0){
        alert("please input the date");
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
                        alert("database error, please contact the administrator");
                    }
                    else{
                        alert("submit succeed!");
                        window.location.href="main";
                    }

                } else
                    alert("please check your net work" + "login error" + xhr.responseText);
            }

        };
        xhr.open("post", "http://localhost:8080/takeout/addInfo", false);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send("catalog_id=" + getCookie ("catalog_id")  + "&place=" +restaurant_name+"&date"+take_outs_date+"&detail="+takeouts_detail);
        
    }
}
