package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.QuestionDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Question;
import fr.treeptik.service.QuestionService;

@Stateless
public class QuestionServiceImpl implements QuestionService {
	@EJB
	public QuestionDAO dao;

	@Override
	public Question saveQuestion(Question entite) throws ServiceException {
		try {
			if (entite.getId() != null) {
				entite = dao.update(entite);
			} else {
				entite = dao.add(entite);
			}
		} catch (DAOException e) {
			throw new ServiceException("saveQuestion", e);
		}
		return entite;
	}

	@Override
	public void removeQuestion(Question entite) throws ServiceException {
		try {
			dao.remove(entite);
		} catch (DAOException e) {
			throw new ServiceException("removeQuestion", e);
		}
	}

	@Override
	public Question getQuestion(Integer id) throws ServiceException {
		Question u = null;
		try {
			u = dao.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException("getQuestion", e);
		}
		return u;
	}

	@Override
	public List<Question> getALLQuestions() throws ServiceException {
		List<Question> questions = null;
		try {
			questions = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("getALLQuestions", e);
		}
		return questions;
	}

}
