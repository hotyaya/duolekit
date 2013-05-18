package com.mjt.service;


import java.util.List;

import com.mjt.entity.Group;
import com.mjt.entity.User;

public interface UserService {
	public User userLogin(String loginName,String loginPwd);
	public boolean saveUser(User user);
	public List getGroups(User user);
}
