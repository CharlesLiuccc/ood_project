import {setCookie, getCookie, isLogin} from "./cookie.js";
window.onload=function (){
    getSubmittedNewsNum();
    let button = document.getElementById("relatednews-button");
    button.addEventListener('click',relatednews);
}


function getSubmittedNewsNum(){
    let xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("microsoft.XMLHttp");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let res = parseInt(xhr.responseText);
                let num = document.getElementById("submitted-news");
                num.innerText=res+" cases submitted";
            } else
                alert("please check your net work: " + xhr.responseText);
        }

    };
    xhr.open("post", "http://localhost:8080/news/getAllInfo", false);
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send("catalog_id=" + getCookie("catalog_id"));
}
function relatednews(){
    let news_detail = document.getElementById("news_detail").value;
    let news_amount = document.getElementById("news_amount").value;
    let news_date = document.getElementById("news_date").value;
    //let user_pwd2 = document.getElementById("password2").value;

    if(news_detail.length==0){
        alert("please input your news detail");
    }
    else if(news_amount.length==0){
        alert("please input the news amount");
    }
    else if(news_date.length==0){
        alert("please input the date");
    }
  //  else if(user_pwd1!=user_pwd2){
   //     alert("retyped password not match");
   // }
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
        xhr.open("post", "http://localhost:8080/news/addInfo", false);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send("catalog_id=" + getCookie ("catalog_id")  + "&date=" +news_date+"&detail="+news_detail);
        
    }
}
