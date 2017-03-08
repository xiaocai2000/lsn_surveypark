package com.jizhuomi.surveypark.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.UserService;
import com.jizhuomi.surveypark.util.DataUtil;
import com.jizhuomi.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User> {
	private static final long serialVersionUID = 1L;

	private String confirmPassword;
	@Resource
	private UserService userService;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * 到达注册页面
	 */
	@SkipValidation
	public String toRegPage() {
		return "regPage";
	}
	
	/**
	 * 进行用户注册
	 */
	public String doReg() {
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return SUCCESS;
	}

	/**
	 * 校验
	 */
	public void validate() {
		// 非空
		if (!ValidateUtil.isValid(model.getEmail())) {
			addFieldError("email", "email是必填项！");
		}
		if (!ValidateUtil.isValid(model.getPassword())) {
			addFieldError("password", "password是必填项！");
		}
		if (!ValidateUtil.isValid(model.getNickName())) {
			addFieldError("nickName", "nickName是必填项！");
		}
		if (hasErrors()) {
			return;
		}

		// 密码一致性
		if (!model.getPassword().equals(confirmPassword)) {
			addFieldError("password", "密码不一致！");
			return;
		}
		
		// email占用
		if (userService.isRegisted(model.getEmail())) {
			addFieldError("email", "email已占用！");
		}
	}
}
