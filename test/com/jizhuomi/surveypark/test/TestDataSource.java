package com.jizhuomi.surveypark.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDataSource {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getConnection() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		DataSource ds = (DataSource)ac.getBean("dataSource");
		System.out.println(ds.getConnection());
	}

}
