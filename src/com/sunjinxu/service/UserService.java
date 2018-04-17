package com.sunjinxu.service;

import com.sunjinxu.pojo.User;

public interface UserService {
	User getUserByAccount(String account);
	User getUserById(int userId);
	User login(String account, String password);
	boolean checkUserEnabled(String account);
	boolean checkAccountExist(String account);
	boolean checkUserNameExist(String userName);
	boolean checkEmailExist(String email);
	void regist(User user);
	void chgUserInfo(User userT,User user);
}
