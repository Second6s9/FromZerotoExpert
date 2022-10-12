


window.onload = function (){
    var hi = "嗨，";
    var welcome = "，欢迎您来到 from zero to expert.";
    var cookieArr = document.cookie.split(";");
    $.post("/FromZerotoExpert/main/getOnlineNums", {"time":currentTime}, function (data){
        var onlineUsers = "<h4>当前在线用户:</h4>"
        users = data.data;
        users.sort();
        for(var i = 0; i < users.length; i++){
            onlineUsers = onlineUsers + "<p>&nbsp;" + users[i] + "</p>"
        }
        document.getElementById("current_online_numbers").innerHTML = users.length;
        document.getElementById("current_online_users").innerHTML = onlineUsers;
    })

    $.post("/FromZerotoExpert/webInformation/getPV", {}, function (data){
        document.getElementById("today_pv").innerHTML = data;
    })
    $.post("/FromZerotoExpert/webInformation/getUV", {}, function (data){
        document.getElementById("today_uv").innerHTML = data;
    })
    $.post("/FromZerotoExpert/webInformation/getIP", {}, function (data){
        document.getElementById("today_ip").innerHTML = data;
    })
    $.post("/FromZerotoExpert/webInformation/getLastDayInformation", {}, function (data){
        document.getElementById("yesterday_pv").innerHTML = data.pv;
        document.getElementById("yesterday_uv").innerHTML = data.uv;
        document.getElementById("yesterday_ip").innerHTML = data.ip;
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
            document.getElementById("current_status").style.color = 'blue';
        });
    });

    $("#total_count").blur(function (){
        total_value = $("#total_count").val();
        try{
            total_value = (parseInt(total_value));
        }catch (e){
            total_value = 0;
        }
        if(total_value > 30) total_value = 30;
        $("#total_count").val(total_value);
        if(total_value == 0){
            document.getElementById("total_pv").innerHTML = "-";
            document.getElementById("total_uv").innerHTML = "-";
            document.getElementById("total_ip").innerHTML = "-";
        }

        $("#total_count_submit").click(function (){
            total_value = $("#total_count").val();
            try{
                total_value = (parseInt(total_value));
            }catch (e){
                total_value = 0;
            }
            if(total_value > 30) total_value = 30;

            $.post("/FromZerotoExpert/webInformation/getRangeWebInformation", {"days":total_value}, function (data){
                document.getElementById("total_uv").innerHTML = data.uv;
                document.getElementById("total_ip").innerHTML = data.ip;
                document.getElementById("total_pv").innerHTML = data.pv;
            })
        })
    })

    window.setInterval(autoQuit,check_time);

}