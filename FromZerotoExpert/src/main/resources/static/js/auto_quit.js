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
        })
    }else{
        //页面未关闭时，检测不超时，更新时间戳
        $.post("/FromZerotoExpert/main/updateOnlineTime", {"time":currentTime}, function (data){
            document.getElementById("current_status").innerHTML = "在线";
        })
    }
    //页面未关闭时，定时更新在线人数
    $.post("/FromZerotoExpert/main/getOnlineNums", {"time":currentTime}, function (data){
        var onlineUsers = "<p>当前在线用户:</p>"
        users = data.data;
        for(var i = 0; i < users.length; i++){
            onlineUsers = onlineUsers + "<p>" + users[i] + "</p>"
        }
        document.getElementById("current_online_numbers").innerHTML = users.length;
        document.getElementById("current_online_users").innerHTML = onlineUsers;
    })
}



// 长时间未操作退出系统 end
