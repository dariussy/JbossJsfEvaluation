package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.EvaluationDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Evaluation;

@Stateless
public class EvaluationServiceImpl {
	@EJB
	public EvaluationDAO dao;

	public Evaluation saveEvaluation(Evaluation entite) throws ServiceException {
		try {
			if (entite.getId() != null) {
				entite = dao.update(entite);
			} else {
				entite = dao.add(entite);
			}
		} catch (DAOException e) {
			throw new ServiceException("saveEvaluation", e);
		}
		return entite;
	}

	public void removeEvaluation(Evaluation entite) throws ServiceException {
		try {
			dao.remove(entite);
		} catch (DAOException e) {
			throw new ServiceException("removeEvaluation", e);
		}
	}

	public Evaluation getEvaluation(Integer id) throws ServiceException {
		Evaluation u = null;
		try {
			u = dao.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException("getEvaluation", e);
		}
		return u;
	}

	public List<Evaluation> getALLEvaluations() throws ServiceException {
		List<Evaluation> users = null;
		try {
			users = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("getALLEvaluations", e);
		}
		return users;
	}

}
