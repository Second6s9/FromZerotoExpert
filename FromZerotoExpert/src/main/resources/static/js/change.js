function checkUsername(){
    var username = document.getElementById("username");
    // 对username进行post校验
    var name = $(username).val();
    name = $.trim(name);
    var url = "checkUsername";
    var args = {"username":name};

    $.post(url, args, function (data){
        var qualified = data.qualified;
        var message = data.message;
        var username_message = document.getElementById("username_message");
        if(qualified){
            username_message.innerHTML = "";
            $(username).css('border', '1px solid #adadad');
            return true;
        }else{
            username_message.innerHTML = message;
            $(username_message).css('color', 'red');
            $(username_message).css('font-size', 4);
            $(username).css('border', '1px solid red');
            return false;
        }
    });

}

function checkPassword(){

    var password = document.getElementById("password");

    // 对password进行post校验
    var pwd = $(password).val();
    pwd = $.trim(pwd);
    var url = "checkPassword";
    var args = {"password":pwd};
    $.post(url, args, function (data){
        var qualified = data.qualified;
        var message = data.message;
        var password_message = document.getElementById("password_message");
        if(qualified){
            password_message.innerHTML = "";
            $(password).css('border', '1px solid #adadad');
        }else{
            password_message.innerHTML = message;
            $(password_message).css('color', 'red');
            $(password_message).css('font-size', 4);
            $(password).css('border', '1px solid red');
        }
    })
}

function checkEmail(){

    var email = document.getElementById("email");

    // 对email进行post校验
    var em = $(email).val();
    em = $.trim(em);
    var url = "checkEmail";
    var args = {"email":em};
    $.post(url, args, function (data){
        var qualified = data.qualified;
        var message = data.message;
        var email_message = document.getElementById("email_message");
        if(qualified){
            email_message.innerHTML = "";
            $(email).css('border', '1px solid #adadad');
        }else{
            email_message.innerHTML = message;
            $(email_message).css('color', 'red');
            $(email_message).css('font-size', 4);
            $(email).css('border', '1px solid red');
        }
    })
}




window.onload = function (){
    var login = document.getElementById("login");
    $(login).click(function (){
        $(location).attr("href", "login")
    });

    var register = document.getElementById("register");
    $(register).click(function (){
        $(location).attr("href", "register");
    });

    $("#username").blur(function (){
       checkUsername();
    });

    $("#password").blur(function (){
        checkPassword();
    });

    $("#email").blur(function (){
        checkEmail()
    });

    $("#register_submit").click(function (){
        $.post("checkAll", {"username": $("#username").val(),
            "password": $("#password").val(),
            "email": $("#email").val()}, function (data){
            if(data.qualified){
                $("#submit_form").submit();
            }else{
                checkUsername();
                checkEmail();
                checkPassword();
            }
        })
    })


}
