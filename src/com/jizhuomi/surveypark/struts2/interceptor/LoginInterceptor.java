package com.jizhuomi.surveypark.struts2.interceptor;

import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.struts2.UserAware;
import com.jizhuomi.surveypark.struts2.action.BaseAction;
import com.jizhuomi.surveypark.struts2.action.LoginAction;
import com.jizhuomi.surveypark.struts2.action.RegAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {
	private static final long serialVersionUID = -7327900275012029583L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		BaseAction action = (BaseAction) arg0.getAction();
		if (action instanceof LoginAction ||
				action instanceof RegAction) {
			return arg0.invoke();
		} else {
			User user = (User) arg0.getInvocationContext().getSession().get("user");
			if (null == user) {
				return "login";
			} else {
				if (action instanceof UserAware) {
					((UserAware) action).setUser(user);
				}
				return arg0.invoke();
			}
		}
	}
}
