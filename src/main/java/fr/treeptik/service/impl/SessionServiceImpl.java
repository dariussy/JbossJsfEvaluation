package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.SessionDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Session;

@Stateless
public class SessionServiceImpl {
	@EJB
	public SessionDAO dao;

	public Session saveSession(Session entite) throws ServiceException {
		try {
			if (entite.getId() != null) {
				entite = dao.update(entite);
			} else {
				entite = dao.add(entite);
			}
		} catch (DAOException e) {
			throw new ServiceException("saveSession", e);
		}
		return entite;
	}

	public void removeSession(Session entite) throws ServiceException {
		try {
			dao.remove(entite);
		} catch (DAOException e) {
			throw new ServiceException("removeSession", e);
		}
	}

	public Session getSession(Integer id) throws ServiceException {
		Session u = null;
		try {
			u = dao.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException("getSession", e);
		}
		return u;
	}

	public List<Session> getALLSessions() throws ServiceException {
		List<Session> sessions = null;
		try {
			sessions = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("getALLSessions", e);
		}
		return sessions;
	}

}
