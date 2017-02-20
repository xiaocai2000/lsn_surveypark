package com.jizhuomi.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jizhuomi.surveypark.dao.BaseDao;
import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

}
