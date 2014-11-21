package fr.treeptik.controlleur;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Question;
import fr.treeptik.service.QuestionService;

@ManagedBean
@RequestScoped
public class QuestionControlleur {
	private Question question = new Question();

	@EJB
	private QuestionService questionService;

	// **********LISTES*****************************************************
	private List<Question> listQuestion = new ArrayList<Question>();

	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel questions;

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Question entity = (Question) questions.getRowData();
		try {
			entity = (Question) questionService.getQuestion(entity.getId());
			System.out.println(entity);
			questionService.removeQuestion(entity);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		questions = new ListDataModel();
		try {
			questions.setWrappedData(questionService.getALLQuestions());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listQuestion";
	}

	public String doSelectUpdate() {
		question = (Question) questions.getRowData();
		System.out.println(question);
		return "edit";
	}

	public String doPrepareCreate() {
		question = new Question();
		return "edit";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		try {
			questionService.saveQuestion(question);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		questions = new ListDataModel();
		try {
			questions.setWrappedData(questionService.getALLQuestions());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String doSave() {
		try {
			questionService.saveQuestion(question);
		} catch (ServiceException e) {
			throw new RuntimeException();
		}
		return "list";
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getQuestions() {

		if (questions == null) {
			questions = new ListDataModel();
			try {
				questions.setWrappedData(questionService.getALLQuestions());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return questions;
	}

	@SuppressWarnings("rawtypes")
	public void setQuestions(DataModel questions) {
		this.questions = questions;
	}

	public List<Question> getListQuestion() {
		return listQuestion;
	}

	public void setListQuestion(List<Question> listQuestion) {
		this.listQuestion = listQuestion;
	}

}
