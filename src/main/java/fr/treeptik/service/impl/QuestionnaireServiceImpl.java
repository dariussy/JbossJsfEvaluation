package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.QuestionnaireDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Questionnaire;

@Stateless
public class QuestionnaireServiceImpl {
	@EJB
	public QuestionnaireDAO dao;

	public Questionnaire saveQuestionnaire(Questionnaire entite)
			throws ServiceException {
		try {
			if (entite.getId() != null) {
				entite = dao.update(entite);
			} else {
				entite = dao.add(entite);
			}
		} catch (DAOException e) {
			throw new ServiceException("saveQuestionnaire", e);
		}
		return entite;
	}

	public void removeQuestionnaire(Questionnaire entite)
			throws ServiceException {
		try {
			dao.remove(entite);
		} catch (DAOException e) {
			throw new ServiceException("removeQuestionnaire", e);
		}
	}

	public Questionnaire getQuestionnaire(Integer id) throws ServiceException {
		Questionnaire u = null;
		try {
			u = dao.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException("getQuestionnaire", e);
		}
		return u;
	}

	public List<Questionnaire> getALLQuestionnaires() throws ServiceException {
		List<Questionnaire> questionnaires = null;
		try {
			questionnaires = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("getALLQuestionnaires", e);
		}
		return questionnaires;
	}

}
