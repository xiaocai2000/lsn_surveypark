package com.jizhuomi.surveypark.service;

import java.util.List;

import com.jizhuomi.surveypark.model.Answer;
import com.jizhuomi.surveypark.model.Page;
import com.jizhuomi.surveypark.model.Question;
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

	/**
	 * 保存或更新页面 
	 */
	public void saveOrUpdatePage(Page model);

	/**
	 * 根据pid获取页面 
	 */
	public Page getPage(Integer pid);

	/**
	 * 保存或者更新问题
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * 删除问题，同时删除答案 
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * 删除page，同时删除问题和答案 
	 */
	public void deletePage(Integer pid);

	/**
	 * 删除调查，同时删除page、问题和答案
	 */
	public void deleteSurvey(Integer sid);

	/**
	 * 获取问题
	 * @param qid
	 * @return
	 */
	public Question getQuestion(Integer qid);

	/**
	 * 清除调查 
	 */
	public void clearAnswers(Integer sid);

	/**
	 * 切换调查状态
	 */
	public void toggleStatus(Integer sid);

	public void updateLogoPhotoPath(Integer sid, String path);

	/**
	 * 查询调查集合，携带pages 
	 */
	public List<Survey> getSurveyWithPages(User user);

	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos);

	public List<Survey> findAllAvailableSurveys();

	public Page getFirstPage(Integer sid);

	public Page getPrePage(Integer currPid);
	public Page getNextPage(Integer currPid);

	public void saveAnswers(List<Answer> processAnswers);
}
