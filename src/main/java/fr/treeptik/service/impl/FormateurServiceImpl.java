package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.FormateurDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Formateur;

@Stateless
public class FormateurServiceImpl {
	@EJB
	public FormateurDAO dao;

	public Formateur saveSession(Formateur entite) throws ServiceException {
		try {
			if (entite.getId() != null) {
				entite = dao.update(entite);
			} else {
				entite = dao.add(entite);
			}
		} catch (DAOException e) {
			throw new ServiceException("saveFormateur", e);
		}
		return entite;
	}

	public void removeFormateur(Formateur entite) throws ServiceException {
		try {
			dao.remove(entite);
		} catch (DAOException e) {
			throw new ServiceException("removeFormateur", e);
		}
	}

	public Formateur getFormateur(Integer id) throws ServiceException {
		Formateur u = null;
		try {
			u = dao.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException("getFormateur", e);
		}
		return u;
	}

	public List<Formateur> getALLFormateurs() throws ServiceException {
		List<Formateur> entites = null;
		try {
			entites = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("getALLFormateurs", e);
		}
		return entites;
	}

}
