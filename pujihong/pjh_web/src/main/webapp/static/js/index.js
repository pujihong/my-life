var username_empty = "请输入用户名！";  
var password_empty = "请输入密码！";
var password_errorInfo = "只能输入6-20个字母、数字、下划线";
var username_error = "请输入正确手机号或者邮箱！";
var username_exist = "用户名已注册";
var email = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/;
var phone = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;  

/**
 * 鼠标划过就展开子菜单，免得需要点击才能展开
 */
$(document).ready(function(){
  dropdownOpen();//调用
  showUsername();
});
function dropdownOpen() {
    var $dropdownLi = $('li.dropdown');
    $dropdownLi.mouseover(function() {
        $(this).addClass('open');
    }).mouseout(function() {
        $(this).removeClass('open');
    });
}

/**
 * 按钮的隐藏显示切换
 */
/*点击按钮隐藏/显示密码*/
var visible=document.getElementById('pwd-visible');//text
var invisible=document.getElementById('pwd-invisible');//password
//隐藏text，显示password
function showPwd() {
    var val=$('#invisiblePwd').val();//将password的值传给text
    $('#visiblePwd').val(val);
//    invisible.style.display = "none";
//    visible.style.display = "";
    $('#pwd-invisible').addClass('hidden');
    $('#pwd-visible').removeClass('hidden');
}
//隐藏password，显示text
function hidePwd(){
    var val=$('#visiblePwd').val();//将text的值传给password
    $('#invisiblePwd').val(val);
//    invisible.style.display = "";
//    visible.style.display = "none";
    $('#pwd-visible').addClass('hidden');
    $('#pwd-invisible').removeClass('hidden');
}


/**
 * 检验登录用户名
 */
function checkUsername() {
	var username = $('#username').val().trim();
	if(username.length == 0) {
		$("#errorMsg").removeClass('hidden');
		$('#errorInfo').text(username_empty);
		$('#username').focus();
		return;
	}else if(!email.test(username) && !phone.test(username)) {
		$("#errorMsg").removeClass('hidden');
		$('#errorInfo').text(username_error);
		$('#username').focus();
		return;
	}else{//输入正确后隐藏提示框
		if(!$("#errorMsg").hasClass("hidden")) {
			$("#errorMsg").addClass("hidden");
		}
	}
}

function login() {
	var username = $('#username').val().trim();
	var password = $('#password').val().trim();
	if(username.length == 0) {
		$("#errorMsg").removeClass('hidden');
		$('#errorInfo').text(username_empty);
		$('#username').focus();
		return;
	}else if(password.length == 0) {
		$("#errorMsg").removeClass('hidden');
		$('#errorInfo').text(password_empty);
		$('#password').focus();
		return;
	}else{
	$.ajax({
		type:"post",
		url:"/pujihong/login",
		//dataType:"json",  
		data:{username:username,password:password},
		timeout:3000,
		error:function(){
			  toastr.error("请求失败，网络异常,请再尝试");
		},
		success:function(data){
		  if(data != "登录成功"){
			 $("#errorMsg").removeClass('hidden');
			 $('#errorInfo').text(data);
		  }else{
			  toastr.success("登录成功！");
			  setTimeout(function(){
				  window.location.reload();
				  },1000);
		  }
		}
	});
	}  
}
//end登录校验

/**
 * 登录后显示用户名，隐藏登录注册按钮
 */
//判断用户名是否为空，可知是否登录，或者session过期
function showUsername(){
if($('#showUsername').text() != ""){
	//这句话要放在li.hidden前面，若放在后面dropdown也有了class="hideen"
	$('li.hidden').removeClass('hidden');
	$('li.dropdown').addClass('hidden');
 }
}
//end 显示用户

/**
 * 注册校验用户名
 */
function checkRegisterUsername() {
	var username = $('#user').val().trim();
	if(username.length == 0) {
		$('#registerErrorMsg').removeClass('hidden');
		$('#registerErrorInfo').text(username_empty);
		$('#user').focus();
		return;
	}else if(!email.test(username) && !phone.test(username)) {
		$("#registerErrorMsg").removeClass('hidden');
		$('#registerErrorInfo').text(username_error);
		$('#user').focus();
		return;
	}else{
		//检查用户名是否已经注册
		$.ajax({
			type:"post",
			url:"/pujihong/checkUsername",
			//dataType:"json",  
			data:{
				username:username
				},
			timeout:3000,
			error:function(){
				  toastr.error("请求失败，网络异常,请再尝试");
			},
			success:function(data){
				if(data > 0) {
					$('#registerErrorMsg').removeClass('hidden');
					$('#registerErrorInfo').text(username_exist);
					$('#user').focus();
				}else if(!$("#registerErrorMsg").hasClass("hidden")) { //输入正确后隐藏提示框
						$("#registerErrorMsg").addClass("hidden");
					}
			}
		});
	}
}	
//end注册校验用户名	
/**
 * 注册按钮
 */
function register() {
	var username = $('#user').val().trim();
	var password = $('#invisiblePwd').val().trim();
	var passwordPattern = /^(\w){6,20}$/;

	if(username.length == 0) {
		$('#registerErrorMsg').removeClass('hidden');
		$('#registerErrorInfo').text(username_empty);
		$('#user').focus();
		return;
	}else if(!passwordPattern.test(password)) {
		$('#registerErrorMsg').removeClass('hidden');
		$('#registerErrorInfo').text(password_errorInfo);
		//这里要判断是那个密码的输入框
		if($('#pwd-invisible').hasClass('hidden')) {
			$('#visiblePwd').focus();
		}else{
			$('#invisiblePwd').focus();
		}
		return;
	}else{
		//注册
		$.ajax({
			type:"post",
			url:"/pujihong/register",
			//dataType:"json",  
			data:{
				username:username,
				password:password
				},
			timeout:3000,
			error:function(){
				  toastr.error("请求失败，网络异常,请再尝试");
			},
			success:function(data){
				 if(data == "注册失败"){
					 $("#registerErrorMsg").removeClass('hidden');
					 $('#registerErrorInfo').text(data);
					 return;
				  }else{
					  toastr.success("注册成功！请登录");
					  setTimeout(function(){
						  window.location.reload();
						  },1000);
				  }
			}
		});
	}
	
}