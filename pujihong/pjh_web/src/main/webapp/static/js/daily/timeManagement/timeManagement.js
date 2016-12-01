var now = new Date();
var year = now.getFullYear();
var month = now.getMonth() + 1;
var day = now.getDate();
//初始化加载
$(function(){ 
	$(".year").html(year);
	$(".month-day").html(month+'月'+day+'日');
});

$('#datetimepickerStart').datetimepicker({
	lang:'ch',
	yearStart:now.getFullYear()-9,
	yearEnd:now.getFullYear()+1,
	maxDate: year+'/'+month+'/'+day,//设置今天是最大时间
	timepicker:false,
	format: 'Y/m/d',
});
$('#datetimepickerEnd').datetimepicker({
	lang:'ch',
	yearStart:now.getFullYear()-9,
	yearEnd:now.getFullYear()+1,
	//minDate 直接写 $('#datetimepickerStart').val()会默认是今天
    //minDate: $('#datetimepickerStart').val().split("/")[0]+'/'+$('#datetimepickerStart').val().split("/")[1]+'/'+$('#datetimepickerStart').val().split("/")[2],  
	//设置开始时间的日期是结束时间的最小时间  
	timepicker:false,
	format: 'Y/m/d',
});
$(".circle").click(function() {
	$('#datetimepickerStart').show();
	$('#datetimepickerEnd').show();
	$('#queryButton').removeClass("hidden");
	$('#datetimepickerStart').datetimepicker('show');  //只设置开始时间框显示出来，同时不用校验起始时间的值，因为即使不选择会默认值是今天
	/*$('#datetimepickerEnd').datetimepicker('show');*/
});
$("#datetimepickerEnd").blur(function() { //结束时间失去焦点时
    if($('#datetimepickerStart').val() <=  $('#datetimepickerEnd').val()) {
    	if(!$("#errorMsg").hasClass("hidden")) {
			$("#errorMsg").addClass("hidden");
		}
    }
});
$("#queryButton").click(function() {
    if($('#datetimepickerStart').val() >  $('#datetimepickerEnd').val()) {
    	$('#errorMsg').removeClass("hidden");
    	$('#datetimepickerEnd').datetimepicker('show');
    	return;
    }else{
    	var dateStart = $('#datetimepickerStart').val();
    	var dateEnd = $('#datetimepickerEnd').val();
    	//根据日期查询
    	$.ajax({
    		type:"post",
    		url:"/pujihong/daily/timeManagement/timeManagement/saveDateToSession",
    		//dataType:"json",  
    		data:{
    			dateStart : dateStart,
    			dateEnd   : dateEnd
    			},
    		timeout:3000,
    		error:function(){
    			  toastr.error("请求失败，网络异常,请再尝试");
    		},
    		success:function(data){
    			   toastr.info("查询成功！");
    			   setTimeout(function(){location.reload(true);}, 1000);
    		}
    	});
    }
});