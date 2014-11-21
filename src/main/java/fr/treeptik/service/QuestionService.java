package fr.treeptik.service;

import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Question;

public interface QuestionService {

	public Question saveQuestion(Question entite) throws ServiceException;

	public void removeQuestion(Question entite) throws ServiceException;

	public Question getQuestion(Integer id) throws ServiceException;

	public List<Question> getALLQuestions() throws ServiceException;

}