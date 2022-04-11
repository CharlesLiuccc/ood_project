import {setCookie, getCookie, isLogin} from "./cookie.js";
window.onload=function (){

    getSubmittedVisitNum();

    let button = document.getElementById("doctorVisit-button");
    button.addEventListener('click',doctorvisit);
}
function getSubmittedVisitNum(){
    let xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("microsoft.XMLHttp");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let res = parseInt(xhr.responseText);
                let num = document.getElementById("submitted-visit");
                num.innerText=res+" cases submitted";
            } else
                alert("please check your net work: " + xhr.responseText);
        }

    };
    xhr.open("post", "http://localhost:8080/doctor/getAllInfo", false);
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send("catalog_id=" + getCookie("catalog_id"));
}

function doctorvisit(){
    let doctor_name = document.getElementById("doctor_name").value;
    let doctor_date = document.getElementById("visit_date").value;
    let doctor_detail = ""
    //let user_pwd2 = document.getElementById("password2").value;

    if(doctor_name.length==0){
        alert("please input your news detail");
    }
    else if(doctor_date.length==0){
        alert("please input the news amount");
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
        xhr.open("post", "http://localhost:8080/doctor/addInfo", false);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send("catalog_id=" + getCookie ("catalog_id")  + "&name="+doctor_name +"&date=" +doctor_date+"&detail="+doctor_detail);
        
    }
}
