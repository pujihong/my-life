/*lang	语言选择中文 “ch”	-
format	格式化日期	-       格式化后 月份是英文的
timepicker	是否开启时间选项	false
yearStart	设置最小年份	-
yearEnd	设置最大年份	-
todayButton	关闭选择今天按钮	-*/
var now = new Date();
$('#datetimepicker').datetimepicker({
	lang:'ch',
	yearStart:now.getFullYear(),
	timepicker:true,
});