<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="loginRegister/loginRegister.html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- 引入toastr 提示框css -->
    <link href="/static/css/toastr.min.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/index.css" rel="stylesheet">
    <title>My Life</title>
</head>
<body>
    <div class="container">
         <header>
             <div class="page-header">
                <!-- 全文检索框在右上角位置-->
             <!--    <div class="row">
                     <div class="col-md-6">
                         <h1>My life <small> &nbsp;pujihong</small></h1>
                     </div>
                     <div class="col-md-6">
                         <form class="navbar-form navbar-right" role="search">
                             <div class="form-group">
                                 <input type="text" class="form-control" placeholder="Search">
                                 <button type="button" class="btn btn-default">Search</button>
                             </div>
                         </form>
                     </div>
                 </div>-->
                <h1>My life <small> &nbsp;pujihong</small></h1>
                 <form class="navbar-form navbar-right" role="search">
                     <div class="form-group">
                         <input type="text" class="form-control" placeholder="Search">
                         <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                     </div>
                 </form>
             </div>
         </header>
        <div style="margin:50px;">
        <!-- 导航-->
        <ul class="nav nav-tabs">
            <li class="active"><a href="pujihong">首页</a></li>
            <li><a href="#">音乐</a></li>
            <li><a href="#">电影</a></li>
            <li><a href="pujihong/daily/index">成功之路</a></li> 
            <li class="hidden" style="float:right"><a id="showUsername" href="#">${username}</a></li>
            <li class="dropdown pull-right" style="float:right;">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">登录/注册
                    <!--下拉框的小三角-->
                    <!-- <span class="caret"></span>-->
                </a>
                <ul class="dropdown-menu" style="min-width:80px;">
                    <li><a class="cursor" data-toggle="modal" data-target="#login">登 录</a></li>
                    <li><a class="cursor" data-toggle="modal" data-target="#register">注 册</a></li>
                </ul>
            </li>
        </ul>
            <!-- 巨幕-->
            <div class="jumbotron">
                <h1>pujihong</h1>
                <p>不要轻易用过去来衡量生活的幸与不幸，每个人的生命都是可以绽放美丽的，只要你珍惜。</p>
                <p><a class="btn btn-primary btn-lg" href="#">Learn more</a></p>
            </div>
        </div>
  </div>
</body>
<!--js文件放在文档最后加载，有利于提高页面加载速度-->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<!-- jQuery文件。务必在bootstrap.min.js 和toastr.js之前引入 -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/toastr.min.js" ></script>
<script src="/static/js/toastrConfiguration.js" ></script>
<script src="/static/js/index.js"></script>
</html>