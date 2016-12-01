/**
 * 动态时钟
 */
//初始化加载
$(function(){ 
    //menu的div层宽度是150 表盘的直径是120 
	//把指针移到中间
	$(".second-hand").css("left",59);  //指针本身的2px;
	$(".minute-hand").css("left",59);
	$(".hour-hand")  .css("left",59);
	
	//var height = $(".clockBox").offset().top; 
	$(".hour-hand")  .css("top",20);  //时针长45 半径60    -45+60+往下移5px;
	$(".minute-hand").css("top",-35); //因为css使用的position: relative; -55 + 20
	$(".second-hand").css("top",-95);  

	var $second = $(".second-hand"), /* 秒针 */
        $minute = $(".minute-hand"), /* 分针 */
        $hour = $(".hour-hand"), /* 时针 */
        timer = setInterval(nowTime,1000); /* 循环调用，一秒后调用一次 */
		function nowTime(){
			function getTime(){
			    var now = new Date();
			    return {
			        hours: now.getHours() + now.getMinutes() / 60, /* 小时数，包括分钟数 */
			        minutes: now.getMinutes() + now.getSeconds() / 60, /* 分针数，包括秒数 */
			        seconds: now.getSeconds() /* 秒数 */
			    };
			}
		var _date = getTime(); /* 接收的时间对象 */
		var _secondRotate = Math.floor(_date.seconds) * 6; /* 秒针，一圈360度总共是60秒（60格），一秒（一格）就是6度，乘以6的主要原因就是秒数乘以一格的度数等于总度数 */
		var _minuteRotate = _date.minutes * 6; /* 分针，一圈360度总共是60分钟，和秒数解释类似 */
		var _hourRotate =  (_date.hours % 12) * 30; /* 时针，一圈360度是12个小时，一个小时就是30度（其实也是5格），小时数乘以一小时的度数就是总度数。但是要考虑大于12的小时数，这里采取整除12的方发即可实现 */
		$second.css({"transform":"rotate("+_secondRotate+"deg)"}); /* 设置秒针旋转度 */
		$minute.css({"transform":"rotate("+_minuteRotate+"deg)"}); /* 设置分针旋转度 */
		$hour.css({"transform":"rotate("+_hourRotate+"deg)"}); /* 设置时针旋转度 */
}
});
//这里已用 css 的 ：hover选择器代替更简单
////鼠标移上去触发
//$(".content").mouseenter(function() {
//	$(".content").css("border","1px solid #9B59B6");
//	//$("#showTime").css("background","#c0c0c0");
//});
//$(".content").mouseleave(function() {
//	//$("#showTime").css("background","");
//	$(".content").css("border","1px solid #ddd");
//});

