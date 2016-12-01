package com.pjh.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pjh.model.User;
import com.pjh.service.LoginAndRegisterService;
import com.pjh.util.Constant;
import com.pjh.util.MD5;

@Controller
@RequestMapping(value="pujihong")
public class LoginAndRegisterController {
  private Logger logger = LoggerFactory.getLogger(LoginAndRegisterController.class);
  @Autowired
  private LoginAndRegisterService loginAndRegisterService;
  /**
   * 注册
   * @param request
   * @return
   * @throws IOException
   */
  @RequestMapping(value="/register", method = RequestMethod.POST)
  @ResponseBody
  public String register(HttpServletRequest request) throws IOException {
	  String phoneOrEmail = request.getParameter("username");
	  String password = request.getParameter("password");
	  String MD5Password = MD5.MD5Encode(password);
	  User user=new User();
	  //判断用户是用手机号还是邮箱登录
	  Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
	  Matcher m = p.matcher(phoneOrEmail);
	  if(m.matches()) {
		 user.setUserphone(phoneOrEmail);
	  }else{
		 user.setUsermail(phoneOrEmail);
	  }
	  //新增用户插入数据库
	  user.setUsername(phoneOrEmail);
	  user.setUserpassword(MD5Password);
	  logger.info("注册用户"+phoneOrEmail);
	  int result = loginAndRegisterService.insert(user);		
      return result > 0 ? Constant.ZCCG:Constant.ZCSB; 
	  
  }
  /**
   * 登录
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value="/login",method = RequestMethod.POST)
  public @ResponseBody String login(HttpServletRequest request,HttpSession session) {
	  String phoneOrEmail = request.getParameter("username");
	  String password = request.getParameter("password");
	  String MD5Password = MD5.MD5Encode(password);
	  String result = loginAndRegisterService.loginCheck(phoneOrEmail,MD5Password);
	  logger.info(result);
      if (result.equals(Constant.DLCG)) {
          //若是session中有用户存在则会覆盖原来的user，当session中的user存在时判定用户存在
          session.setAttribute("username",phoneOrEmail);
          int userId = loginAndRegisterService.getUserIdByLoginName(phoneOrEmail);
          session.setAttribute("userId", userId);
      } 
	  return result;
  }
  /**
   * 检查用户名是否已注册
   * @param request
   * @return
   */
  @RequestMapping(value="/checkUsername",method = RequestMethod.POST)
  @ResponseBody
  public int chenkUsername(HttpServletRequest request) { 
	  String username = request.getParameter("username");
	  logger.info("检验用户名是否注册");
	  return loginAndRegisterService.checkUsernameIsExist(username);
  }
}
