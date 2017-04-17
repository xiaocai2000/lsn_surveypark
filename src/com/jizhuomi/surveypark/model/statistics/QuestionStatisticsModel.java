package com.jizhuomi.surveypark.model.statistics;

import java.util.ArrayList;
import java.util.List;

import com.jizhuomi.surveypark.model.Question;

public class QuestionStatisticsModel {
	private Question question;
	private int count;
	private List<OptionStatisticsModel> oms = new ArrayList<OptionStatisticsModel>();

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<OptionStatisticsModel> getOms() {
		return oms;
	}

	public void setOms(List<OptionStatisticsModel> oms) {
		this.oms = oms;
	}

}
