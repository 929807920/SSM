package com.sunjinxu.mapper;

import org.apache.ibatis.annotations.Param;

import com.sunjinxu.pojo.User;

public interface UserMapper {
	public User getUserByAccount(String accout);
	public User getUserById(Integer id);
	public User login(@Param("account") String account, @Param("password") String password);
	public boolean checkUserEnabled(String account);
	public boolean checkAccountExist(String account);
	public boolean checkUserNameExist(String userName);
	public boolean checkEmailExist(String email);
	public void regist(User user);
	public void regUserInfo(User user);
	public void chgUserInfo(User user);
}