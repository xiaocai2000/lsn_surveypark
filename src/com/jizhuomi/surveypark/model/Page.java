package com.jizhuomi.surveypark.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Page implements Serializable {
	private static final long serialVersionUID = 6329566776787831241L;
	private Integer id;
	private String title = "未命名";
	private String description;

	// 建立从页面到调查的多对一关联
	private transient Survey survey;
	// 建立从问题到页面的一对多关联
	private Set<Question> questions = new HashSet<>();
	
	private float orderno;

	public float getOrderno() {
		return orderno;
	}

	public void setOrderno(float orderno) {
		this.orderno = orderno;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if (id != null) {
			this.orderno = id;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
}
