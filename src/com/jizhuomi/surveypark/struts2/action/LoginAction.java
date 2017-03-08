package com.jizhuomi.surveypark.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.UserService;
import com.jizhuomi.surveypark.util.DataUtil;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware {
	private static final long serialVersionUID = 6576980225040220315L;
	@Resource
	private UserService userService;
	private Map<String, Object> sessionMap;
	
	/**
	 * 到达登录页面 
	 */
	public String toLoginPage() {
		return "loginPage";
	}
	
	/**
	 * 进行登录处理
	 */
	public String doLogin() {
		return "success";
	}
	
	/**
	 * 校验登录信息
	 */
	public void validateDoLogin() {
		User user = userService.validateLoginInfo(model.getEmail(), DataUtil.md5(model.getPassword()));
		
		if (null == user) {
			addActionError("email/password错误");
		} else {
			sessionMap.put("user", user);
		}
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.sessionMap = arg0;
	}
}
