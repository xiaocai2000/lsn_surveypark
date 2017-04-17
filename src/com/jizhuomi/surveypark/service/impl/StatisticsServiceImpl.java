package com.jizhuomi.surveypark.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jizhuomi.surveypark.dao.BaseDao;
import com.jizhuomi.surveypark.model.Answer;
import com.jizhuomi.surveypark.model.Question;
import com.jizhuomi.surveypark.model.statistics.OptionStatisticsModel;
import com.jizhuomi.surveypark.model.statistics.QuestionStatisticsModel;
import com.jizhuomi.surveypark.service.StatisticsService;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	private BaseDao<Question> questionDao;
	private BaseDao<Answer> answerDao;
	

	public BaseDao<Question> getQuestionDao() {
		return questionDao;
	}

	@Resource
	public void setQuestionDao(BaseDao<Question> questionDao) {
		this.questionDao = questionDao;
	}


	public BaseDao<Answer> getAnswerDao() {
		return answerDao;
	}

	@Resource
	public void setAnswerDao(BaseDao<Answer> answerDao) {
		this.answerDao = answerDao;
	}


	@Override
	public QuestionStatisticsModel statistics(Integer qId) {
		// TODO Auto-generated method stub
		Question q = questionDao.getEntity(qId);
		QuestionStatisticsModel qsm = new QuestionStatisticsModel();
		qsm.setQuestion(q);
		
		String qhql = "select count(*) from Answer a where a.questionId=?";
		Long qCount = (Long) answerDao.uniqueResult(qhql, qId);
		qsm.setCount(qCount.intValue());
		
		String ohql = "select count(*) from Answer a where a.questionId=? and concat(',',a.answerIds,',') like ?";
		Long ocount = null;
		OptionStatisticsModel osm = null;
		
		int qt = q.getQuestionType();
		switch (qt) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			String[] arr = q.getOptionArr();
			
			for (int i = 0; i < arr.length; i ++) {
				osm = new OptionStatisticsModel();
				osm.setOptionLabel(arr[i]);
				osm.setOptionIndex(i);
				ocount = (Long)answerDao.uniqueResult(ohql, qId, "%," + i + ",%");
				osm.setCount(ocount.intValue());
				qsm.getOms().add(osm);
			}
			if (q.isOther()) {
				osm = new OptionStatisticsModel();
				osm.setOptionLabel("其他");
				ocount = (Long)answerDao.uniqueResult(ohql, qId, "%,other,%");
				osm.setCount(ocount.intValue());
				qsm.getOms().add(osm);
			}
			break;
		case 6:
		case 7:
		case 8:
			String[] rows = q.getMatrixRowTitleArr();
			String[] cols = q.getMatrixColTitleArr();
			String[] opts = q.getMatrixSelectOptionArr();
			
			for (int i = 0; i < rows.length; i ++) {
				for (int j = 0; j < cols.length; j ++) {
					if (8 != qt) {
						osm = new OptionStatisticsModel();
						osm.setMatrixRowLabel(rows[i]);
						osm.setMatrixRowIndex(i);
						osm.setMatrixColIndex(j);
						osm.setMatrixColLabel(cols[j]);
						ocount = (Long) answerDao.uniqueResult(ohql, qId, "%," + i + "_" + j + ",%");
						osm.setCount(ocount.intValue());
						qsm.getOms().add(osm);
					} else {
						for (int k = 0; k < opts.length; k ++) {
							osm = new OptionStatisticsModel();
							osm.setMatrixRowLabel(rows[i]);
							osm.setMatrixRowIndex(i);
							osm.setMatrixColIndex(j);
							osm.setMatrixColLabel(cols[j]);
							ocount = (Long) answerDao.uniqueResult(ohql, qId, "%," + i + "_" + j + "_" + k + ",%");
							osm.setCount(ocount.intValue());
							qsm.getOms().add(osm);
						}
					}
				}
			}
			break;
		}
		return null;
	}

}
