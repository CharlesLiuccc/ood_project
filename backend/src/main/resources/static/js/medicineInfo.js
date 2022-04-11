import {setCookie, getCookie, isLogin} from "./cookie.js";
window.onload=function (){

    let button = document.getElementById("medicine_button");
    button.addEventListener('click',medicineAdd);
}

function medicineAdd(){
    let medicine_name = document.getElementById("medicine_name").value;
    let medicine_frequency = document.getElementById("medicine_frequency").value;
    let medicine_dosage = document.getElementById("medicine_dosage").value;
    let medicine_detail = "";
    //let user_pwd2 = document.getElementById("password2").value;

    if(medicine_name.length==0){
        alert("please input your medicine name");
    }
    else if(medicine_frequency.length==0){
        alert("please input the frequency");
    }
    else if(medicine_dosage.length==0){
        alert("please input the dosage");
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
        xhr.open("post", "http://localhost:8080/medicine/addInfo", false);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.send("catalog_id=" + getCookie ("catalog_id")  + "&name=" +medicine_name+"&frequency"+medicine_frequency+"&dosage"+medicine_dosage+"&detail="+medicine_detail);
        
    }
}
