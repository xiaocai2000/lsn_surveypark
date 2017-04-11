package com.jizhuomi.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jizhuomi.surveypark.model.Page;
import com.jizhuomi.surveypark.model.Survey;
import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.SurveyService;
import com.jizhuomi.surveypark.struts2.UserAware;

@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware {
	private Integer srcPid;
	private List<Survey> mySurveys;
	private SurveyService surveyService;
	private User user;
	private Integer targPid;
	private int pos;
	private Integer sid;

	public Integer getTargPid() {
		return targPid;
	}

	public void setTargPid(Integer targPid) {
		this.targPid = targPid;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getSrcPid() {
		return srcPid;
	}

	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	public SurveyService getSurveyService() {
		return surveyService;
	}

	@Resource
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	public String toSelectTargetPage() {
		this.mySurveys = surveyService.getSurveyWithPages(user);
		return "moveOrCopyPageListPage";
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		this.user = user;
	}
	
	public String doMoveOrCopyPage() {
		surveyService.moveOrCopyPage(srcPid, targPid, pos);
		return "designSurveyAction";
	}
}
