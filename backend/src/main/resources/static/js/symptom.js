import {setCookie, getCookie, isLogin} from "./cookie.js";
window.onload=function (){
    getSubmittedSymptomNum();
    let button = document.getElementById("submit-symptomInfo");
    button.addEventListener('click',sendInfo);
}
function getSubmittedSymptomNum() {
    let xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("microsoft.XMLHttp");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let res = parseInt(xhr.responseText);
                let num = document.getElementById("submitted-symptom");
                num.innerText=res+" cases submitted";
            } else
                alert("please check your net work: " + xhr.responseText);
        }

    };
    xhr.open("post", "http://localhost:8080/symptom/getAllInfo", false);
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send("catalog_id=" + getCookie("catalog_id"));
}

function sendInfo(){
    let symptom_name = document.getElementById("symptom_name").value;
    let start_date = document.getElementById("symptom_start_date").value;
    let detail = document.getElementById("symptom_detail").value;

    if(start_date.length==0){
        alert("please input start date");
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
                        window.location.href="main";
                        alert("submit succeed!");
                    }
                } else
                    alert("please check your net work: " + xhr.responseText);
            }
        };
        xhr.open("post", "http://localhost:8080/symptom/addInfo", false);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send("catalog_id="+getCookie("catalog_id")+"&time=" + start_date + "&type=" + symptom_name+"&detail="+detail);
    }
}
