


window.onload = function (){
    var hi = "嗨，";
    var welcome = "，欢迎您来到 from zero to expert.";
    var cookieArr = document.cookie.split(";");
    $.post("/FromZerotoExpert/main/getOnlineNums", {"time":currentTime}, function (data){
        document.getElementById("current_online_numbers").innerHTML = data.message;
    })
    for(var i = 0; i < cookieArr.length; i++){
        if(cookieArr[i].split("=")[0].trim() == "username"){
            name = cookieArr[i].split("=")[1].trim();
            var message = hi + welcome + name
            document.getElementById("message").innerHTML = hi + name + welcome;
            break;
        }else{
            document.getElementById("message").innerHTML = "啊哦！你发现了BUG,请速速联系我！";
        }
    }


    $(function(){
        /* 鼠标移动事件 */
        $(document).mouseover(function(){
            document.getElementById("current_status").innerHTML = "在线";
        });
    });



    window.setInterval(autoQuit,check_time);

}