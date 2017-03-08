package com.jizhuomi.surveypark.service;

import com.jizhuomi.surveypark.model.User;

public interface UserService extends BaseService<User> {

	/**
	 * 判断email是否占用 
	 */
	boolean isRegisted(String email);

	/**
	 * 验证登录信息 
	 */
	User validateLoginInfo(String email, String md5);

}
