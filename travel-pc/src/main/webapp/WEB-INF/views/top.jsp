<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<link href="/css/top.css" rel="stylesheet" type="text/css"/>
<style>
    .login{
        margin-top: 20px;
        background-color: #fff;
        z-index: 6;
        opacity:1;
        width: 400px;
        display: none;
        position: absolute;
        top: 191px;
        height: 141px;
        left: 38%;
        padding: 20px;
        box-shadow: #666 0px 0px 76px;
        border-radius: 4px;
    }
    .register{
        margin-top: 20px;
        background-color: #fff;
        z-index: 6;
        opacity:1;
        width: 400px;
        display: none;
        box-shadow: #666 0px 0px 76px;
        border-radius: 4px;
    }
    .login_btn{
        margin-left: 40px;
    }
</style>
<div class="search_div">
    <input class="search_input" placeholder="你想去哪"/>
    <button class="search_btn">
        <svg t="1532588741571" class="icon" style="" viewBox="0 0 1024 1024" version="1.1"
             xmlns="http://www.w3.org/2000/svg" p-id="1028" xmlns:xlink="http://www.w3.org/1999/xlink" width="32"
             height="32">
            <defs>
                <style type="text/css"></style>
            </defs>
            <path d="M705.046 748.841c71.71-60.801 117.226-151.546 117.226-252.927 0-183.054-148.397-331.455-331.451-331.455s-331.451 148.4-331.451 331.455c0 183.060 148.397 331.455 331.451 331.455 63.311 0 122.468-17.753 172.781-48.548l114.524 119.408c4.731 5.091 12.982 5.631 18.421 1.201l19.708-16.048c5.44-4.428 6.014-12.147 1.282-17.242l-112.492-117.296zM210.361 495.911c0-154.892 125.566-280.457 280.46-280.457s280.46 125.566 280.46 280.457c0 154.895-125.566 280.463-280.46 280.463s-280.46-125.57-280.46-280.463z"
                  p-id="1029" fill="#ffffff"></path>
        </svg>
    </button>
    <span class="user_box">
            <a href="javascript:void(0);" onclick="showLogin()">登录/注册</a>
    </span>
    </span>
</div>
<div class="login" id="login_box">
    <p class="p_box"><span>邮箱:</span><input id="email" class="user_input"/></p>
    <p class="p_box"><span>密码:</span><input id="password" class="user_input" type="password"/></p>
    <p class="p_box"><button id="login" class="login_btn">登录</button><button class="login_btn">关闭</button></p>
</div>

<script>
    function showLogin() {
        $("#login_box").show();
    }
    $("#login").click(function () {
        var email = $("#email").val();
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if(!myreg.test(email)){
            alert("请输入正确的邮箱");
            return;
        }
        var password = $("#password").val();
        $.ajax({
            url: "/user/login",
            data: JSON.stringify({email: email,password:password}),
            type: "POST",
            contentType : 'application/json',
            success: function(data) {
            }
        });
    });
</script>