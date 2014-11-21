package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.FormationDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Formation;
import fr.treeptik.service.FormationService;

@Stateless
public class FormationServiceImpl implements FormationService {
	@EJB
	public FormationDAO dao;

	@Override
	public Formation saveFormation(Formation entite) throws ServiceException {
		try {
			if (entite.getId() != null) {
				entite = dao.update(entite);
			} else {
				entite = dao.add(entite);
			}
		} catch (DAOException e) {
			throw new ServiceException("saveFormation", e);
		}
		return entite;
	}

	@Override
	public void removeFormation(Formation entite) throws ServiceException {
		try {
			dao.remove(entite);
		} catch (DAOException e) {
			throw new ServiceException("removeFormation", e);
		}
	}

	@Override
	public Formation getFormation(Integer id) throws ServiceException {
		Formation u = null;
		try {
			u = dao.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException("getFormation", e);
		}
		return u;
	}

	@Override
	public List<Formation> getALLFormations() throws ServiceException {
		List<Formation> entites = null;
		try {
			entites = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("getALLFormations", e);
		}
		return entites;
	}

}
