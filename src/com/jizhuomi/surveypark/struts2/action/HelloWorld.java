package com.jizhuomi.surveypark.struts2.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("helloWorld")
@Scope("prototype")
public class HelloWorld extends ActionSupport {
	private static final long serialVersionUID = 6048467444715347442L;
	private String name;
	private String result;
	private Map<String, Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getName() {
		return name;
	}

	public void setResult(String result) {
		this.result = result;
	}

	// ajax请求参数赋值
	public void setName(String name) {
		this.name = name;
	}

	// ajax返回结果
	public String getResult() {
		return result;
	}

	public String execute() {
		this.result = "Hello! " + this.name + ".";
		this.map = new HashMap();
		this.map.put("list", new ArrayList(Arrays.asList("cc", "sd")));
		return "success";
	}
}
