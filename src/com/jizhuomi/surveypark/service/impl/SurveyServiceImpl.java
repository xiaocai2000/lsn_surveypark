package com.jizhuomi.surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jizhuomi.surveypark.dao.BaseDao;
import com.jizhuomi.surveypark.model.Page;
import com.jizhuomi.surveypark.model.Question;
import com.jizhuomi.surveypark.model.Survey;
import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.SurveyService;

@Service("surveyService")
@Transactional
public class SurveyServiceImpl implements SurveyService {
	private BaseDao<Survey> surveyDao;
	private BaseDao<Page> pageDao;
	private BaseDao<Question> questionDao;

	public BaseDao<Question> getQuestionDao() {
		return questionDao;
	}

	@Resource(name="questionDao")
	public void setQuestionDao(BaseDao<Question> questionDao) {
		this.questionDao = questionDao;
	}

	public BaseDao<Page> getPageDao() {
		return pageDao;
	}

	@Resource(name="pageDao")
	public void setPageDao(BaseDao<Page> pageDao) {
		this.pageDao = pageDao;
	}

	public BaseDao<Survey> getSurveyDao() {
		return surveyDao;
	}

	@Resource(name="surveyDao")
	public void setSurveyDao(BaseDao<Survey> surveyDao) {
		this.surveyDao = surveyDao;
	}

	@Override
	public List<Survey> findMySurveys(User user) {
		// TODO Auto-generated method stub
		String hql = "from Survey s where s.user.id=?";
		return surveyDao.findEntityByHQL(hql, user.getId());
	}

	@Override
	public Survey newSurvey(User user) {
		// TODO Auto-generated method stub
		Survey survey = new Survey();
		Page page = new Page();
		Question question = new Question();
		
		// 设置关联关系
		survey.setUser(user);
		question.setPage(page);
		page.getQuestions().add(question);
		page.setSurvey(survey);
		survey.getPages().add(page);
		
		surveyDao.saveEntity(survey);
		pageDao.saveEntity(page);
		questionDao.saveEntity(question);
		
		return survey;
	}

	/**
	 * 按照id查询survey
	 */
	@Override
	public Survey getSurvey(Integer sid) {
		Set<Page> pageSet;
		Survey survey = surveyDao.getEntity(sid);
		
		Hibernate.initialize(survey.getUser());
		pageSet = survey.getPages();
		Hibernate.initialize(survey.getPages());
		for (Page page : pageSet) {
			Hibernate.initialize(page.getQuestions());
		}
		
		return survey;
	}

	@Override
	public void updateSurvey(Survey model) {
		// TODO Auto-generated method stub
		surveyDao.updateEntity(model);
	}
}
