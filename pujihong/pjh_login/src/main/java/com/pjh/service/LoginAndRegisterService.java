package com.pjh.service;

import com.pjh.model.User;

public interface LoginAndRegisterService {
	/**
	 * 注册人员
	 * @param user
	 * @return
	 */
	public int insert(User user);
    /**
     * 登陆检验用户名和密码
     * @param mD5Password 
     * @param usernameOrEmail
     * @param mD5Password
     */
	public String loginCheck(String phoneOrEmail, String MD5Password);
	/**
	 * 检查注册用户名是否存在
	 * @param phoneOrEmail
	 * @return
	 */
	public int checkUsernameIsExist(String phoneOrEmail);
	/**
	 * 通过登录名获取UserId
	 * @param phoneOrEmail
	 * @return
	 */
	public int getUserIdByLoginName(String phoneOrEmail);
}
