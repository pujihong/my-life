<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../daily/dailyModal.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="/static/css/bootstrap.min.css" rel="stylesheet">
<!-- 引入toastr 提示框css -->
<link href="/static/css/toastr.min.css" rel="stylesheet" type="text/css"/>
<link href="/static/datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css">
<link href="/static/css/daily/timeManagement/timeManagement.css" rel="stylesheet" type="text/css">

<title>漫漫人生路</title>
</head>
<body>
	<div class="main">
	        <div style="height:50px;"></div><!--如果用margin-top 会使datetimepicker插件的弹出框下移  -->
	        <div class="head">
				<h4 class="div-inline">雕栏玉砌应犹在 只是朱颜改</h4>
				<div class="circle div-inline">
					<div class="year"></div>
					<div class="month-day"></div>
				</div>
				<div class="datediv div-inline">
			         <!-- 这里的maxlength是为了不能输入 -->
				     <input type="text" class="form-control" id="datetimepickerStart" placeholder="起始时间" maxlength="0"> 
				     <input type="text" class="form-control" id="datetimepickerEnd" placeholder="结束时间" maxlength="0">
				     <button type="button" class="btn btn-default hidden" id="queryButton">查询</button>
					 <span id="errorMsg" class="alert alert-danger hidden">起始时间不能大于结束时间</span> 
				</div>
				 <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addTaskModal" id="addButton">新增</button>
				 <a class="pull-right" href="<%=request.getContextPath() %>/pujihong/daily/index">返回</a>
		    </div>
            
	         <!--循环产生数据 -->
	         <c:choose>
		         <c:when test="${taskSize == 0}">
		              <div class="promptInfo">今日无任何的计划，可选择时间查看其它计划</div>
		         </c:when>
		         <c:otherwise>
		              <div class="content">
		               <c:forEach items="${task}" var="list">
				              <div>
				                   <div class="date div-inline">
				                     <span style="background: #f0f1f1">
				                       <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${list.getTime()}" />
				                     </span>
				                   </div>
				                   <div class="line-icondiv div-inline">
					                   <div class="line div-inline"></div>
					                   <c:choose>
					                     <c:when test="${list.getStatu() == 1 }">
					                        <div class="icon div-inline" style="border: 2px solid red;"></div>
					                     </c:when>
					                     <c:otherwise>
					                        <div class="icon div-inline"></div>
					                     </c:otherwise>
					                   </c:choose>
					               </div>   
					               <div class="taskdiv div-inline"> 
					                   <p class="title" >${list.getTitle()}
										    <button type="button" class="btn btn-sm btn-danger pull-right" id="delBtn" data-toggle="modal" data-target="#deleteTaskModal" onclick="deleteTaskParams('${list.getId()}')">删除</button>
										   <c:if test="${list.getStatu() == 1 }">
											    <button type="button" class="btn btn-sm btn-info pull-right" id="editTakBtn" data-toggle="modal" data-target="#editTaskModal" 
											    onclick="editTaskModalShow('${list.getId()}','${list.getTitle()}','${list.getDescription()}','${list.getDatetime()}')">编辑</button>
											    <button type="button" class="btn btn-sm btn-primary pull-right" id="finishTaskBtn" onclick="finishTask('${list.getId()}')">完成</button>
					                       </c:if>
					                   </p>
					                   <div class="description">
					                       <span>${list.getDescription()}</span>
					                   </div>
				                   </div>
				               </div>
		                 </c:forEach>
		               </div>  
		          </c:otherwise>
              </c:choose>
           </div>
      
</body>
<!--注意引入顺序哦  -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/js/toastr.min.js" type="text/javascript"></script>
<script src="/static/js/toastrConfiguration.js" type="text/javascript"></script>
<script src="/static/datetimepicker/jquery.datetimepicker.js" type="text/javascript"></script>
<script src="/static/datetimepicker/datetimepicker.js" type="text/javascript"></script>
<script src="/static/js/daily/timeManagement/timeManagement.js" type="text/javascript"></script>
<script src="/static/js/daily/common.js"  type="text/javascript"></script>
</html>