import {setCookie, getCookie, isLogin} from "./cookie.js";
window.onload=function (){
    let button = document.getElementById("submit_tripInfo");
    button.addEventListener('click',trip);
}

function trip() {
    let destination = document.getElementById("destination").value;
    let trips_start_time = document.getElementById("trips_start_time").value;
    let trips_end_time = document.getElementById("trips_end_time").value;
    let trip_detail = document.getElementById("trip_detail").value;

    let xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("microsoft.XMLHttp");
    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                if (parseInt(xhr.responseText) == 1) {
                    alert("database error, please contact the administrator");
                } else if (parseInt(xhr.responseText) == 2) {
                    alert("database error, please contact the administrator");
                } else {
                    alert("submit succeed!");
                    window.location.href="main";
                }

            } else
                alert("please check your net work" + "login error" + xhr.responseText);
        }

    };
    xhr.open("post", "http://localhost:8080/trip/addInfo", false);
    xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    xhr.send("catalog_id=" + getCookie("catalog_id") + "&start=" + trips_start_time + "&end=" + trips_end_time + "&place=" + destination + "&detail=" + trip_detail);


}
