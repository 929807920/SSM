package com.sunjinxu.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunjinxu.mapper.UserMapper;
import com.sunjinxu.pojo.User;
import com.sunjinxu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	// 检查用户是否可登录
	@Override
	public boolean checkUserEnabled(String account) {
		System.out.println("服务层：1");
		return userMapper.checkUserEnabled(account);
	}

	// 用户登录
	@Override
	public User login(String account, String password) {
		return userMapper.login(account, password);
	}

	// 通过account获取用户所有信息
	@Override
	public User getUserByAccount(String account) {
		return userMapper.getUserByAccount(account);
	}

	// 检查账户是否已存在
	@Override
	public boolean checkAccountExist(String account) {
		return userMapper.checkAccountExist(account);
	}

	// 检查用户名是否已存在
	@Override
	public boolean checkUserNameExist(String userName) {
		return userMapper.checkUserNameExist(userName);
	};

	// 检查邮箱是否已存在
	@Override
	public boolean checkEmailExist(String email) {
		return userMapper.checkEmailExist(email);
	};

	// 用户注册
	@Override
	public void regist(User user) {
		int create_at = (int) new Date().getTime();
		int update_at = create_at;
		user.setCreate_at(create_at);
		user.setUpdate_at(update_at);
		user.setAvatar("src/images/small.gif");
		userMapper.regist(user);
		userMapper.regUserInfo(user);
		user.setUserId(user.getId());
	}

	@Override
	public User getUserById(int userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	public void chgUserInfo(User userT, User user) {
		userT.setUserName(user.getUserName());
		userT.setEmail(user.getEmail());
		userT.setPhone(user.getPhone());
		userT.setUpdate_at((int) new Date().getTime());
		userT.setBusiness(user.getBusiness());
		userT.setSex(user.getSex());
		userT.setBirthday(user.getBirthday());
		userMapper.chgUserInfo(userT);
	}
}