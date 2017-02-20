package com.jizhuomi.surveypark.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jizhuomi.surveypark.model.User;

@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User model;

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	/**
	 * 到达注册页面
	 */
	public String toRegPage() {
		return "regPage";
	}
}
