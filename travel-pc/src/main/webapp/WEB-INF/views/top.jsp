<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<link href="/css/top.css" rel="stylesheet" type="text/css"/>
<style>
    .login_box{
        width: 100%;
        height: 100%;
        background-color: #666;
        z-index: 5;
        display: none;
        position: absolute;
        left: 0;
        top: 0;
        opacity:0.9;
        text-align: center;
    }
    .login{
        margin-top: 20px;
        background-color: #fff;
        z-index: 6;
        opacity:1;
        width: 400px;
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
<div class="login_box">
    <div class="login" id="login">
        <p><span>账号:</span><input/></p>
        <p><span>密码:</span><input type="password"/></p>
        <p><button>登录</button></p>
    </div>
    <div class="login" id="register">
        <p><span>邮件:</span><input/></p>
        <p><span>密码:</span><input type="password"/></p>
        <p><button>注册</button></p>
    </div>
</div>
<script>
    function showLogin() {
        $(".login_box").show();
    }
</script>