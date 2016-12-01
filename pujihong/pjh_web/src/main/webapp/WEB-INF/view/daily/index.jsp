<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../daily/dailyModal.html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	    <!-- 新 Bootstrap 核心 CSS 文件 -->
	    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	    <!-- 引入toastr 提示框css -->
	    <link href="/static/css/toastr.min.css" rel="stylesheet" type="text/css"/>
	    <link href="/static/css/daily/index.css" rel="stylesheet" type="text/css">
	    <link href="/static/datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css">
	    <title>成功之路</title>
	</head>

	<body>
	  <div>
	      <header>
	      <div class="page-header">
	       <h1>My life <small> &nbsp;pujihong</small></h1>
	       </div>
	     </header> 
	    <!-- 左侧菜单div -->
	     <div id="menu">
	       <!-- 动态时钟 -->
	        <div class="clockBox">
	                <!-- 因为我css使用的是相对位置，所以div的顺序很重要 -->
			        <div class="hour-hand"></div>   <!-- 时针 -->
			        <div class="minute-hand"></div> <!-- 分针 -->
			        <div class="second-hand"></div> <!-- 秒针 -->
			        <div class="center-dot"></div>  <!-- 三指针重合的圆圈 -->
			</div>
			<!-- end动态时钟 -->
			
	        <ul class="nav nav-tabs nav-stacked" role="tablist">
	           <li><a href="<%=request.getContextPath() %>/pujihong">我的首页</a></li>
			   <li><a href="#timeManagement">时间管理 </a></li>
               <li><a href="#lifeNotes">生活笔记</a></li>
			</ul>
	     </div>
	     <div id="container">
	       <!-- 时间管理-->
            <section id="timeManagement">
                <div class="col-md-12">
                    <div class="header-content">
                        <h2>时间管理</h2>
                        <h3>"进程切换非常昂贵，避免多任务，保持单进程。"</h3>
                    </div>
                </div>
              <div class="row">
                    <div>
                       <h4>今日计划  
                         <!--  <span class="unread">4</span>  -->
                          <a class="pull-right" data-toggle="modal" data-target="#addTaskModal" id="addButton" ><span class="glyphicon glyphicon-plus">增加</span></a>
                       </h4>
                    </div>
                    <div class="col-md-12">
                        <div class="row">
                        <!--循环产生数据 -->
                         <c:forEach items="${task}" var="list">

                            <div class="col-md-4">
                                <div class="content">
                                    <div class="showTime">
                                         <span class="showTime" id = "showTime">  <fmt:formatDate pattern="HH:mm" value="${list.getTime()}" /></span>
                                    </div>
                                   <h2 class="title">${list.getTitle()}</h2>
                                   <p class="description">${list.getDescription()}</p>
                                  <!-- 如果是模板数据 就不生产按钮组  -->  
                                   <c:if test="${list.getId() >= 0}">
	                                   <div class="btn-group btn-group-sm btnDiv">
										    <button type="button" class="btn btn-primary" onclick="finishTask('${list.getId()}')">完成</button>
										    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#editTaskModal" 
										    onclick="editTaskModalShow('${list.getId()}','${list.getTitle()}','${list.getDescription()}','${list.getDatetime()}')">编辑</button>
										    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteTaskModal" onclick="deleteTaskParams('${list.getId()}')">删除</button>
										</div>
									</c:if>
				                </div>
                            </div>
                            
                         </c:forEach>
                        </div>
                    </div>                   
                </div>
                  <div><a class="pull-right" href="<%=request.getContextPath() %>/pujihong/daily/timeManagement">查看更多</a></div>
            </section>
           <!-- end 时间管理 -->
	       <!-- 人生笔记-->
            <section id="notes" class="gray">
               <div><a class="pull-right" href="<%=request.getContextPath() %>/pujihong/daily/lifeNotes">查看更多</a></div>
            </section>
          
	     </div>
	  </div>
	</body>
	
<!--js文件放在文档最后加载，有利于提高页面加载速度-->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<!-- jQuery文件。务必在bootstrap.min.js 和toastr.js之前引入 -->
<script src="/static/js/jquery.min.js" type="text/javascript"></script>
<script src="/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/js/toastr.min.js" type="text/javascript"></script>
<script src="/static/datetimepicker/jquery.datetimepicker.js" type="text/javascript"></script>
<script src="/static/datetimepicker/datetimepicker.js" type="text/javascript"></script>
<script src="/static/js/toastrConfiguration.js" type="text/javascript"></script>
<script src="/static/js/daily/index.js" type="text/javascript"></script>
<script src="/static/js/daily/common.js"></script>
</html>