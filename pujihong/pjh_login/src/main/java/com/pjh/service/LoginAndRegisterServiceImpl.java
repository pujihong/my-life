package com.pjh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjh.dao.UserMapper;
import com.pjh.model.User;
import com.pjh.util.Constant;
@Service("loginAndRegisterService")
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {
    private Logger logger = LoggerFactory.getLogger(LoginAndRegisterServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Override
	public int insert(User user) {
		logger.info("注册用户");
		return userMapper.insert(user);
	}
	  /**
     * 登陆检验手机号和密码
     * @param usernameOrEmail
     * @param mD5Password
     */
	@Override
	public String loginCheck(String phoneOrEmail,String MD5Password) {
		logger.info(phoneOrEmail+"用户登录");
		 //通过用户名获取用户
        User dbUser = userMapper.getUserByPhoneOrEmail(phoneOrEmail,phoneOrEmail);
        //若获取失败
        if (dbUser == null) {
            return Constant.MYGYH;
        }
        //获取成功后，将获取用户的密码和传入密码对比
        else if (!dbUser.getUserpassword().equals(MD5Password)){
            return Constant.MMCW;
        }
        else {
            return Constant.DLCG;
        }
    }
	/**
	 * 检查用户名是否存在
	 */
	@Override
	public int checkUsernameIsExist(String phoneOrEmail) {
		logger.info("检查用户名是否存在"+phoneOrEmail);
		return userMapper.checkUsernameIsExist(phoneOrEmail,phoneOrEmail);
	}
	/**
	 * 根据登录名获取userId
	 */
	@Override
	public int getUserIdByLoginName(String phoneOrEmail) {
		return userMapper.getUserIdByLoginName(phoneOrEmail,phoneOrEmail);
	}
	

}
