package com.mjt.service.impl;

import java.util.List;
import java.util.Set;

import com.mjt.dao.BaseDAO;
import com.mjt.entity.Group;
import com.mjt.entity.User;
import com.mjt.service.UserService;

public class UserServiceImpl implements UserService {
	
	BaseDAO basedao;
	
	public BaseDAO getBasedao() {
		return basedao;
	}

	public void setBasedao(BaseDAO basedao) {
		this.basedao = basedao;
	}

	public User userLogin(String loginName, String loginPwd) {
		String hql = "from User as a where a.name='"+loginName+"' and a.password='"+loginPwd+"'";
		return (User)basedao.loadObject(hql);
	}

	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		basedao.saveOrUpdate(user);
		return true;
	}

	public List<Group> getGroups(User user) {
		// TODO Auto-generated method stub
		String hql = "select u.groups from User u where u.id = "+user.getId();
		List<Group> list = basedao.query(hql);
		return list;
	}
	
}
