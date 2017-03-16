package com.jizhuomi.surveypark.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.UserService;

public class TestUserService {
	private static UserService us;
	
	@BeforeClass
	public static void initUserService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		us = (UserService) ctx.getBean("userService");
	}

	/**
	 * 插入用户
	 * @throws SQLException
	 */
	@Test
	public void insertUser() throws SQLException {
		User u = new User();
		u.setEmail("q@163.com");
		u.setPassword("123");
		u.setNickName("stone");
		us.saveEntity(u);
	}

}
