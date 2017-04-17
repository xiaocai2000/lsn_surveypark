package com.jizhuomi.surveypark.service;

import com.jizhuomi.surveypark.model.statistics.QuestionStatisticsModel;

public interface StatisticsService {
	public QuestionStatisticsModel statistics(Integer qId);
}
