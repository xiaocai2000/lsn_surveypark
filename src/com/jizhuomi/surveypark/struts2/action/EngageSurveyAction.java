package com.jizhuomi.surveypark.struts2.action;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jizhuomi.surveypark.model.Answer;
import com.jizhuomi.surveypark.model.Page;
import com.jizhuomi.surveypark.model.Survey;
import com.jizhuomi.surveypark.service.SurveyService;
import com.jizhuomi.surveypark.util.StringUtil;
import com.jizhuomi.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements ServletContextAware, SessionAware, ParameterAware {
	private static final long serialVersionUID = 8710345559664828022L;
	private List<Survey> surveys;
	private SurveyService surveyService;
	private ServletContext sc;
	private Integer sid;
	private Page currPage;
	private Map<String, Object> sessionMap;
	private Map<String, String[]> paramsMap;
	private static final String CURRENT_SURVEY = "current_survey";
	private static final String ALL_PARAMS_MAP = "all_params_map";
	private Integer currPid;

	public Integer getCurrPid() {
		return currPid;
	}

	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Page getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}

	public SurveyService getSurveyService() {
		return surveyService;
	}

	@Resource
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	public String findAllAvailableSurveys() {
		this.surveys = surveyService.findAllAvailableSurveys();
		return "engageSurveyListPage";
	}
	
	public String getImageUrl(String path) {
		if (ValidateUtil.isValid(path)) {
			String absPath = sc.getRealPath(path);
			File file = new File(absPath);
			if (file.exists()) {
				return sc.getContextPath() + path;
			}
		}
		return sc.getContextPath() + "/question.bmp";
	}

	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		this.sc = context;
	}
	
	public String entry() {
		this.currPage = surveyService.getFirstPage(sid);
		this.sessionMap.put(CURRENT_SURVEY, currPage.getSurvey());
		sessionMap.put(ALL_PARAMS_MAP, new HashMap<Integer, Map<String, String[]>>());
		return "engageSurveyPage";
	}
	
	public String doEngageSurvey() {
		String submitName = getSubmitName();
		// 上一步
		if (submitName.endsWith("pre")) {
			mergeParamsIntoSession();
			this.currPage = surveyService.getPrePage(currPid);
			return "engageSurveyPage";
		}
		// 下一步
		else if (submitName.endsWith("next")) {
			mergeParamsIntoSession();
			this.currPage = surveyService.getNextPage(currPid);
			return "engageSurveyPage";
		}
		// 完成
		else if (submitName.endsWith("done")) {
			mergeParamsIntoSession();
			surveyService.saveAnswers(processAnswers());
			clearSessionData();
			return "engageSurveyAction";
		}
		// 取消
		else if (submitName.endsWith("exit")) {
			clearSessionData();
			return "engageSurveyAction";
		}
		return null;
	}

	private List<Answer> processAnswers() {
		// TODO Auto-generated method stub
		Map<Integer, String> matrixRadioMap = new HashMap<Integer, String>();
		List<Answer> answers = new ArrayList<Answer>();
		Answer a = null;
		String key = null;
		String[] value = null;
		Map<Integer, Map<String, String[]>> allMap = getAllParamsMap();
		for (Map<String, String[]> map : allMap.values()) {
			for (Entry<String, String[]> entry : map.entrySet()) {
				key = entry.getKey();
				value = entry.getValue();
				if (key.startsWith("q")) {
					if (!key.contains("other") &&
							!key.contains("_")) {
						a = new Answer();
						a.setAnswerIds(StringUtil.arr2Str(value));
						a.setQuestionId(getQid(key));
						a.setSurveyId(getCurrentSurvey().getId());
						a.setOtherAnswer(StringUtil.arr2Str(map.get(key + "other")));
						answers.add(a);
					} else if (key.contains("_")) {
						Integer radioQid = getMatrixRadioQid(key);
						String oldValue = matrixRadioMap.get(radioQid);
						if (null == oldValue) {
							matrixRadioMap.put(radioQid, StringUtil.arr2Str(value));
						} else {
							matrixRadioMap.put(radioQid, oldValue + "," + StringUtil.arr2Str(value));
						}
					}
				}
			}
		}
		processMatrixRadioMap(matrixRadioMap, answers);
		return answers;
	}

	private void processMatrixRadioMap(Map<Integer, String> matrixRadioMap, List<Answer> answers) {
		// TODO Auto-generated method stub
		Answer a = null;
		Integer key = null;
		String value = null;
		for (Entry<Integer, String> entry : matrixRadioMap.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			a = new Answer();
			a.setAnswerIds(value);
			a.setQuestionId(key);
			a.setSurveyId(getCurrentSurvey().getId());
			answers.add(a);
		}
	}

	private Integer getMatrixRadioQid(String key) {
		// TODO Auto-generated method stub
		return Integer.parseInt(key.substring(1, key.lastIndexOf("_")));
	}

	private Survey getCurrentSurvey() {
		// TODO Auto-generated method stub
		return (Survey) sessionMap.get(CURRENT_SURVEY);
	}

	private Integer getQid(String key) {
		// TODO Auto-generated method stub
		return Integer.parseInt(key.substring(1));
	}

	private void clearSessionData() {
		// TODO Auto-generated method stub
		sessionMap.remove(CURRENT_SURVEY);
		sessionMap.remove(ALL_PARAMS_MAP);
	}

	private void mergeParamsIntoSession() {
		// TODO Auto-generated method stub
		Map<Integer, Map<String, String[]>> allParamsMap = getAllParamsMap();
		allParamsMap.put(currPid, paramsMap);
	}

	private Map<Integer, Map<String, String[]>> getAllParamsMap() {
		// TODO Auto-generated method stub
		return (Map<Integer, Map<String, String[]>>) sessionMap.get(ALL_PARAMS_MAP);
	}

	private String getSubmitName() {
		// TODO Auto-generated method stub
		for (String key : paramsMap.keySet()) {
			if (key.startsWith("submit_")) {
				return key;
			}
		}
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.sessionMap = session;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		// TODO Auto-generated method stub
		this.paramsMap = parameters;
	}
	
	/**
	 * 设置标记，用于答案回显，主要用于radio、checkbox、select的选中标记
	 */
	public String setTag(String name, String value, String selTag) {
		Map<String, String[]> map = getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		if (StringUtil.contains(values, value)) {
			return selTag;
		}
		return "";
	}
	
	/**
	 * 设置标记，用于答案回显，设置文本框选中
	 */
	public String setText(String name) {
		Map<String, String[]> map = getAllParamsMap().get(currPage.getId());
		String[] values = map.get(name);
		return "value='" + values[0] + "'";
	}
}
