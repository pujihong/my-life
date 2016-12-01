var time_empty = "时间不能为空！";
var title_empty = "请输入事件！";
var description_empty = "请输入描述！";
function finishTask(taskId) {
	//完成了自己的計劃
	$.ajax({
		type:"post",
		url:"/pujihong/daily/timeManagement/finishTask",
		//dataType:"json",  
		data:{
			 id : taskId
			},
		timeout:3000,
		error:function(){
			  toastr.error("请求失败，网络异常,请再尝试");
		},
		success:function(data){
			//成功
			if(data == 1) {
			   toastr.info("哟，不错，再接再厉！");
			   setTimeout(function(){
					  window.location.reload();
					  },1000);
			}else{
				toastr.error("操作失败,请再尝试!");
			}
		}
	});
}
//删除任务
function deleteTaskParams(deleteTaskId) {
	$('#deleteTaskId').val(deleteTaskId);
}

$("#deleteTask").click(function() {
	var taskId = $('#deleteTaskId').val();
	$.ajax({
		type:"post",
		url:"/pujihong/daily/timeManagement/deleteTask",
		//dataType:"json",  
		data:{
			 id : taskId
			},
		timeout:3000,
		error:function(){
			  toastr.error("请求失败，网络异常,请再尝试");
		},
		success:function(data){
			//成功
			if(data == 1) {
			   toastr.success("删除成功！");
			   setTimeout(function(){
					  window.location.reload();
					  },1000);
			}else{
				toastr.error("操作失败,请再尝试!");
			}
		}
	});
});
//显示编辑时候同时传递参数
function editTaskModalShow(taskId,taskTitle,taskDescription,taskTime) {
	$('#editTaskId').val(taskId);
	$('#datetimepicker').val(taskTime);
	$('#title').val(taskTitle);
	$('#description').val(taskDescription);
}
$("#saveTask").click(function() {
	var taskId = $('#editTaskId').val();
	var taskTime = $('#datetimepicker').val();
	var taskTitle = $('#title').val();
	var taskDescription = $('#description').val();
	if(taskTime.length == 0) {
		$("#editErrorMsg").removeClass('hidden');
		$('#editErrorMsg').text(time_empty);
		$('#datetimepicker').focus();
		return;
	}else if(taskTitle.length == 0) {
		$("#editErrorMsg").removeClass('hidden');
		$('#editErrorMsg').text(title_empty);
		$('#title').focus();
		return;
	}else if(taskDescription.length == 0){
		$("#editErrorMsg").removeClass('hidden');
		$('#editErrorMsg').text(description_empty);
		$('#description').focus();
		return;
	}
	$.ajax({
		type:"post",
		url:"/pujihong/daily/timeManagement/editTask",
		//dataType:"json",  
		data:{
			 id : taskId,
			 time : taskTime,
	         title : taskTitle,
	         description : taskDescription
			},
		timeout:3000,
		error:function(){
			  toastr.error("请求失败，网络异常,请再尝试");
		},
		success:function(data){
			//编辑成功
			if(data == 1) {
			   toastr.info("保存成功！");
			   setTimeout(function(){
					  window.location.reload();
					  },1000);
			}else {
				toastr.error("操作失败,请再尝试!");
			}
		}
	});
});
//新增任务
var now = new Date();
$('#addDatetimepicker').datetimepicker({
	lang:'ch',
	yearStart:now.getFullYear(),
	timepicker:true,
});
$("#addTask").click(function() {
	var taskTime = $('#addDatetimepicker').val();
	var taskTitle = $('#addTitle').val();
	var taskDescription = $('#addDescription').val();
	if(taskTime.length == 0) {
		$("#addErrorMsg").removeClass('hidden');
		$('#addErrorMsg').text(time_empty);
		$('#addDatetimepicker').focus();
		return;
	}else if(taskTitle.length == 0) {
		$("#addErrorMsg").removeClass('hidden');
		$('#addErrorMsg').text(title_empty);
		$('#addTitle').focus();
		return;
	}else if(taskDescription.length == 0){
		$("#addErrorMsg").removeClass('hidden');
		$('#addErrorMsg').text(description_empty);
		$('#addDescription').focus();
		return;
	}
	$.ajax({
		type:"post",
		url:"/pujihong/daily/timeManagement/timeManagement/addTask",
		//dataType:"json",  
		data:{
			 time : taskTime,
	         title : taskTitle,
	         description : taskDescription
			},
		timeout:3000,
		error:function(){
			  toastr.error("请求失败，网络异常,请再尝试");
		},
		success:function(data){
			//编辑成功
			if(data == 1) {
			   toastr.info("新增成功！");
			   setTimeout(function(){
					  window.location.reload();
					  },1000);
			}else {
				toastr.error("操作失败,请再尝试!");
			}
		}
	});
});