package com.jizhuomi.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jizhuomi.surveypark.model.Page;
import com.jizhuomi.surveypark.model.Question;
import com.jizhuomi.surveypark.service.SurveyService;

@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {
	private static final long serialVersionUID = -4106354428019845537L;
	private Integer sid;
	private Integer pid;
	private Integer qid;
	private SurveyService surveyService;

	public SurveyService getSurveyService() {
		return surveyService;
	}

	@Resource
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public String toSelectQuestionType() {
		return "selectQuestionTypePage";
	}
	
	public String toDesignQuestionPage() {
		return String.valueOf(model.getQuestionType());
	}
	
	public String saveOrUpdateQuestion() {
		Page page = new Page();
		page.setId(pid);
		model.setPage(page);
		surveyService.saveOrUpdateQuestion(model);
		return "designSurveyAction";
	}
	
	public String deleteQuestion() {
		surveyService.deleteQuestion(qid);
		return "designSurveyAction";
	}
	
	public String editQuestion() {
		this.model = surveyService.getQuestion(qid);
		return "" + model.getQuestionType();
	}
}
