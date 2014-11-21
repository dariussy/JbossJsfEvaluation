package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.ResultatDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Resultat;

@Stateless
public class ResultatServiceImpl {
	@EJB
	public ResultatDAO dao;

	public Resultat saveResultat(Resultat entite) throws ServiceException {
		try {
			if (entite.getId() != null) {
				entite = dao.update(entite);
			} else {
				entite = dao.add(entite);
			}
		} catch (DAOException e) {
			throw new ServiceException("saveResultat", e);
		}
		return entite;
	}

	public void removeResultat(Resultat entite) throws ServiceException {
		try {
			dao.remove(entite);
		} catch (DAOException e) {
			throw new ServiceException("removeResultat", e);
		}
	}

	public Resultat getResultat(Integer id) throws ServiceException {
		Resultat u = null;
		try {
			u = dao.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException("getResultat", e);
		}
		return u;
	}

	public List<Resultat> getALLResultats() throws ServiceException {
		List<Resultat> resultats = null;
		try {
			resultats = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("getALLResultats", e);
		}
		return resultats;
	}

}
