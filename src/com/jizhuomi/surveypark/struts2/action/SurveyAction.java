package com.jizhuomi.surveypark.struts2.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jizhuomi.surveypark.model.Survey;
import com.jizhuomi.surveypark.model.User;
import com.jizhuomi.surveypark.service.SurveyService;
import com.jizhuomi.surveypark.struts2.UserAware;
import com.jizhuomi.surveypark.util.ValidateUtil;

@Controller("surveyAction")
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware, ServletContextAware {
	private static final long serialVersionUID = -4712180478274769593L;

	private SurveyService surveyService;
	private Integer sid;
	private File logoPhoto;
	private String logoPhotoFileName;
	private User user;
	private ServletContext sc;
	private String inputPage;

	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}

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
	
	public File getLogoPhoto() {
		return logoPhoto;
	}

	public void setLogoPhoto(File logoPhotoFile) {
		this.logoPhoto = logoPhotoFile;
	}

	public String getLogoPhotoFileName() {
		return logoPhotoFileName;
	}

	public void setLogoPhotoFileName(String logoPhotoFileName) {
		this.logoPhotoFileName = logoPhotoFileName;
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
	
	/**
	 * 到达增加logo的页面
	 */
	public String toAddLogoPage() {
		return "addLogoPage";
	}
	
	public String doAddLogo() {
		if (ValidateUtil.isValid(logoPhotoFileName)) {
			String dir = sc.getRealPath("/upload");
			String ext = logoPhotoFileName.substring(logoPhotoFileName.lastIndexOf("."));
			long l = System.nanoTime();
			File newFile = new File(dir, l + ext);
			logoPhoto.renameTo(newFile);
			System.out.println("upload success " + newFile.getAbsolutePath());
			surveyService.updateLogoPhotoPath(sid, "/upload/" + l + ext);
		}
		return "designSurveyAction";
	}
	
	public void prepareUpdateSurvey() {
		inputPage = "/editSurvey.jsp";
	}
	
	public void prepareDoAddLogo() {
		inputPage = "/addLogo.jsp";
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.sc = arg0;
	}
	
	public boolean photoExists() {
		String path = model.getLogoPhotoPath();
		if (ValidateUtil.isValid(path)) {
			String absPath = sc.getRealPath(path);
			File file = new File(absPath);
			return file.exists();
		}
		return false;
	}
}
