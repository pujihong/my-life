package com.pjh.dao;

import org.apache.ibatis.annotations.Select;

import com.pjh.model.User;
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);
    /**注册用户*/
    int insert(User record);//这个方法用的配置

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**根据手机号或邮箱查询用户是否存在*/
    @Select("select * from user where userPhone=#{0} or usermail=#{1}")
    public User getUserByPhoneOrEmail(String userPhone,String userMail);
    /**
     * 检查注册用户名是否存在
     * @param phoneOrEmail
     * @return
     */
     //这里写#{username}报错，没有发现是什么原因,
    @Select("select count(*) from user where username=#{0} or usermail=#{1}")
	public int checkUsernameIsExist(String username,String usermail);
    @Select("select userId from user where username=#{0} or usermail=#{1}")
	int getUserIdByLoginName(String phoneOrEmail, String phoneOrEmail2);
}