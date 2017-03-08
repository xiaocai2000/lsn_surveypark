package com.jizhuomi.surveypark.service;

import java.util.List;

import com.jizhuomi.surveypark.model.Survey;
import com.jizhuomi.surveypark.model.User;

public interface SurveyService {

	/**
	 * 查询调查列表 
	 */
	public List<Survey> findMySurveys(User user);

	/**
	 * 新建调查 
	 */
	public Survey newSurvey(User user);

	/**
	 * 按照id查询survey
	 */
	public Survey getSurvey(Integer sid);

	/**
	 * 更新调查 
	 */
	public void updateSurvey(Survey model);

}
