// 长时间未操作自动退出系统
var lastTime = new Date().getTime();
var currentTime = new Date().getTime();
var timeOut =  60 * 1000; //设置离线超时时间： 60秒
var check_time = 3 * 1000; //定时任务的时间间隔：3秒一次

function autoQuit(){
    $(function(){
        /* 鼠标移动事件 */
        $(document).mouseover(function(){
            lastTime = new Date().getTime(); //更新操作时间
        });
    });

    /* 定时器
 * 间隔3秒检测是否长时间未操作页面
 */
    scheduledTasks();

}

function scheduledTasks(){
    currentTime = new Date().getTime(); //更新当前时间
    if(currentTime - lastTime > timeOut){ //判断是否超时.
        //页面未关闭时，检测到超时改为离线
        $.post("/FromZerotoExpert/main/quit", {}, function (data){
            document.getElementById("current_status").innerHTML = "离线";
            document.getElementById("current_status").style.color = 'red';
        })
    }else{
        //页面未关闭时，检测不超时，更新时间戳
        $.post("/FromZerotoExpert/main/updateOnlineTime", {"time":currentTime}, function (data){
            document.getElementById("current_status").innerHTML = "在线";
            document.getElementById("current_status").style.color = 'blue';
        })
    }
    //页面未关闭时，定时更新在线人数
    $.post("/FromZerotoExpert/main/getOnlineNums", {"time":currentTime}, function (data){
        var onlineUsers = "<h4>当前在线用户:</h4>"
        users = data.data;
        users.sort();
        for(var i = 0; i < users.length; i++){
            onlineUsers = onlineUsers + "<p>&nbsp;" + users[i] + "</p>"
        }
        if(!users.length) onlineUsers = onlineUsers + "<p>&nbsp;无人在线~~</p>";
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
}



// 长时间未操作退出系统 end
