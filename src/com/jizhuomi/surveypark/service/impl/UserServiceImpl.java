package com.jizhuomi.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jizhuomi.surveypark.dao.BaseDao;
import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.UserService;
import com.jizhuomi.surveypark.util.ValidateUtil;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	@Override
	public boolean isRegisted(String email) {
		String hql = "from User u where u.email=?";
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValid(list);
	}

	@Override
	public User validateLoginInfo(String email, String md5) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.email=? and u.password=?";
		List<User> list = this.findEntityByHQL(hql, email, md5);
		return ValidateUtil.isValid(list) ? list.get(0) : null;
	}

}
