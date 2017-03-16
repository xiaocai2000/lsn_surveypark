package com.jizhuomi.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jizhuomi.surveypark.model.Page;
import com.jizhuomi.surveypark.model.Survey;
import com.jizhuomi.surveypark.service.SurveyService;

@Controller
@Scope("prototype")
public class PageAction extends BaseAction<Page> {
	private Integer pid;
	private Integer sid;
	private SurveyService surveyService;

	public Integer getPid() {
		return pid;
	}

	public Integer getSid() {
		return sid;
	}

	public SurveyService getSurveyService() {
		return surveyService;
	}

	public String saveOrUpdatePage() {
		Survey survey = new Survey();
		survey.setId(sid);
		model.setSurvey(survey);
		surveyService.saveOrUpdatePage(model);
		return "designSurveyAction";
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Resource
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	public String toAddPage() {
		return "addPagePage";
	}
	
	public String editPage() {
		this.model = surveyService.getPage(pid);
		return "editPagePage";
	}
	
	public String deletePage() {
		surveyService.deletePage(pid);
		return "designSurveyAction";
	}
}
