package com.jizhuomi.surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jizhuomi.surveypark.dao.BaseDao;
import com.jizhuomi.surveypark.model.Answer;
import com.jizhuomi.surveypark.model.Page;
import com.jizhuomi.surveypark.model.Question;
import com.jizhuomi.surveypark.model.Survey;
import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.SurveyService;
import com.jizhuomi.surveypark.util.DataUtil;

@Service("surveyService")
@Transactional
public class SurveyServiceImpl implements SurveyService {
	private BaseDao<Survey> surveyDao;
	private BaseDao<Page> pageDao;
	private BaseDao<Question> questionDao;
	private BaseDao<Answer> answerDao;

	public BaseDao<Answer> getAnswerDao() {
		return answerDao;
	}

	@Resource(name="answerDao")
	public void setAnswerDao(BaseDao<Answer> answerDao) {
		this.answerDao = answerDao;
	}

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
	
	/**
	 * 保存或更新页面 
	 */
	@Override
	public void saveOrUpdatePage(Page model) {
		pageDao.saveOrUpdateEntity(model);
	}
	
	/**
	 * 根据pid获取页面 
	 */
	public Page getPage(Integer pid) {
		return pageDao.getEntity(pid);
	}
	
	/**
	 * 保存或者更新问题
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}
	
	/**
	 * 删除问题，同时删除答案 
	 */
	public void deleteQuestion(Integer qid) {
		// 删除答案
		String hql = "delete from Answer a where a.questionId=?";
		answerDao.batchEntityByHQL(hql, qid);
		// 删除问题
		hql = "delete from Question q where q.id=?";
		questionDao.batchEntityByHQL(hql, qid);
	}
	
	/**
	 * 删除page，同时删除问题和答案 
	 */
	public void deletePage(Integer pid) {
		String hql = "delete from Answer a where a.questionId in (select q.id from Question q where q.page.id = ?)";
		answerDao.batchEntityByHQL(hql, pid);
		hql = "delete from Question q where q.page.id = ?";
		questionDao.batchEntityByHQL(hql, pid);
		hql = "delete from Page p where p.id = ?";
		pageDao.batchEntityByHQL(hql, pid);
	}
	
	/**
	 * 删除调查，同时删除page、问题和答案
	 */
	public void deleteSurvey(Integer sid) {
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.batchEntityByHQL(hql, sid);
		hql = "delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)";
		questionDao.batchEntityByHQL(hql, sid);
		hql = "delete from Page p where p.survey.id = ?";
		pageDao.batchEntityByHQL(hql, sid);
		hql = "delete from Survey s where s.id = ?";
		surveyDao.batchEntityByHQL(hql, sid);
	}
	
	/**
	 * 获取问题
	 * @param qid
	 * @return
	 */
	public Question getQuestion(Integer qid) {
		return questionDao.getEntity(qid);
	}
	
	/**
	 * 清除调查 
	 */
	public void clearAnswers(Integer sid) {
		String hql = "delete from Answer a where a.surveyId = ?";
		surveyDao.batchEntityByHQL(hql, sid);
	}
	
	/**
	 * 切换调查状态
	 */
	public void toggleStatus(Integer sid) {
		Survey survey = this.getSurvey(sid);
		String hql = "update Survey s set s.closed = ? where s.id = ?";
		surveyDao.batchEntityByHQL(hql, !survey.isClosed(), sid);
	}
	
	public void updateLogoPhotoPath(Integer sid, String path) {
		String hql = "update Survey s set s.logoPhotoPath=? where s.id=?";
		surveyDao.batchEntityByHQL(hql, path, sid);
	}
	
	/**
	 * 查询调查集合，携带pages 
	 */
	public List<Survey> getSurveyWithPages(User user) {
		String hql = "from Survey s where s.user.id=?";
		List<Survey> list = surveyDao.findEntityByHQL(hql, user.getId());
		for (Survey s : list) {
			s.getPages().size();
		}
		return list;
	}
	
	public void moveOrCopyPage(Integer srcPid, Integer targPid, int pos) {
		Page srcPage = this.getPage(srcPid);
		Survey srcSurvey = srcPage.getSurvey();
		Page targPage = this.getPage(targPid);
		Survey targSurvey = targPage.getSurvey();
		
		if (srcSurvey.getId().equals(targSurvey.getId())) {
			setOrderno(srcPage, targPage, pos);
		} else {
			srcPage.getQuestions().size();
			Page copyPage = (Page) DataUtil.deeplyCopy(srcPage);
			copyPage.setSurvey(targSurvey);
			pageDao.saveEntity(copyPage);
			for (Question q : copyPage.getQuestions()) {
				questionDao.saveEntity(q);
			}
			setOrderno(copyPage, targPage, pos);
		}
	}

	private void setOrderno(Page srcPage, Page targPage, int pos) {
		// TODO Auto-generated method stub
		if (0 == pos) {
			if (isFirstPage(targPage)) {
				srcPage.setOrderno(targPage.getOrderno() - 0.01f);
			} else {
				Page prePage = getPrePage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + prePage.getOrderno()) / 2);
			}
		} else {
			if (isLastPage(targPage)) {
				srcPage.setOrderno(targPage.getOrderno() + 0.01f);
			} else {
				Page nextPage = getNextPage(targPage);
				srcPage.setOrderno((targPage.getOrderno() + nextPage.getOrderno()) / 2);
			}
		}
	}

	private Page getNextPage(Page targPage) {
		// TODO Auto-generated method stub
		String hql = "from Page p where p.survey.id = ? and p.orderno > ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return list.get(0);
	}

	private boolean isLastPage(Page targPage) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno > ?";
		Long count = (Long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return count == 0;
	}

	private Page getPrePage(Page targPage) {
		// TODO Auto-generated method stub
		String hql = "from Page p where p.survey.id = ? and p.orderno < ? order by p.orderno desc";
		List<Page> list = pageDao.findEntityByHQL(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return list.get(0);
	}

	private boolean isFirstPage(Page targPage) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno < ?";
		Long count = (Long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return count == 0;
	}
	
	public List<Survey> findAllAvailableSurveys() {
		String hql = "from Survey s where s.closed = ?";
		return surveyDao.findEntityByHQL(hql, false);
	}
	
	public Page getFirstPage(Integer sid) {
		String hql = "from Page p where p.survey.id = ? order by p.orderno asc";
		List<Page> list = pageDao.findEntityByHQL(hql, sid);
		Page p = list.get(0);
		p.getQuestions().size();
		p.getSurvey().getTitle();
		return p;
	}
	
	public Page getPrePage(Integer currPid) {
		Page p = this.getPage(currPid);
		p = this.getPrePage(p);
		p.getQuestions().size();
		return p;
	}
	
	public Page getNextPage(Integer currPid) {
		Page p = this.getPage(currPid);
		p = this.getNextPage(p);
		p.getQuestions().size();
		return p;
	}
}
