import {setCookie, getCookie, isLogin} from "./cookie.js";
window.onload=function (){
    let button = document.getElementById("form-submit");
    button.addEventListener('click',formsubmit);
    let NegativeButton = document.getElementById("negative");
    NegativeButton.addEventListener('click',negativeSubmit);
}

function negativeSubmit(){
    let xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("microsoft.XMLHttp");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                if (parseInt(xhr.responseText) == 1) {
                    alert("database error, please contact the administrator");
                } else if (parseInt(xhr.responseText) == 2) {
                    alert("database error, please contact the administrator");
                } else {

                    window.location.href="main";
                    alert("submit succeed!");

                }

            } else
                alert("please check your net work" + "login error" + xhr.responseText);
        }

    };
    xhr.open("post", "http://localhost:8080/user/clearRisk", false);
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send("catalog_id=" + getCookie("catalog_id"));
}


function formsubmit() {
    let questions = document.getElementsByName("Frequency");
    let values=[];
    let QSum = 0;
    window.console.log(questions);
    for(var i = 0; i<questions.length;i++){
        let name = "Q" + (i+1) + "Frequency";
        let target = document.getElementsByName(name);
        for(var j=0;j<target.length;j++){
            if(target[j].checked){
                values[i]=target[j].value;
                QSum = QSum+values[i];
            }
        }
    }
    if(QSum>=20){
        alert("According to our system data, you have high quality that get Covid-19, please get a Covid test as soon as possible");
        window.location.href="main";
    }
    else{
        window.location.href="main";
        alert("submit succeed!");

    }

   

}
