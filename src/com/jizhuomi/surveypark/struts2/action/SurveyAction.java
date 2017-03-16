package com.jizhuomi.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jizhuomi.surveypark.model.Survey;
import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.SurveyService;
import com.jizhuomi.surveypark.struts2.UserAware;

@Controller("surveyAction")
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware {
	private static final long serialVersionUID = -4712180478274769593L;

	private SurveyService surveyService;
	private Integer sid;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	private List<Survey> mySurveys;
	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	private User user;

	public SurveyService getSurveyService() {
		return surveyService;
	}

	@Resource
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	/**
	 * 查询我的调查列表
	 */
	public String mySurveys() {
		this.mySurveys = surveyService.findMySurveys(user);
		System.out.println("survey count:" + mySurveys.isEmpty());
		
		return "mySurveyListPage";
	}
	
	/**
	 *  新建调查
	 */
	public String newSurvey() {
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage";
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		this.user = user;
	}
	
	/**
	 * 设计调查
	 */
	public String designSurvey() {
		this.model = surveyService.getSurvey(sid);
		return "designSurveyPage";
	}
	
	/**
	 * 编辑调查
	 */
	public String editSurvey() {
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	/**
	 * 更新调查
	 */
	public String updateSurvey() {
		this.sid = model.getId();
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "designSurveyAction";
	}
	
	/**
	 * 删除调查
	 */
	public String deleteSurvey() {
		surveyService.deleteSurvey(sid);
		return "findMySurveyAction";
	}
	
	/**
	 * 清除调查
	 */
	public String clearAnswers() {
		surveyService.clearAnswers(sid);
		return "findMySurveyAction";
	}
	
	/**
	 * 切换调查状态
	 */
	public String toggleStatus() {
		surveyService.toggleStatus(sid);
		return "findMySurveyAction";
	}
}
