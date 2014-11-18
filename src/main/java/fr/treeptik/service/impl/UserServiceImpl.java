package fr.treeptik.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.UserDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.User;
import fr.treeptik.service.UserService;

@Stateless
public class UserServiceImpl implements UserService {
	@EJB
	public UserDAO dao;

	public User saveUser(User entite) throws ServiceException {
		try {
			if (entite.getId() != null) {
				entite = dao.update(entite);
			} else {
				entite = dao.add(entite);
			}
		} catch (DAOException e) {
			throw new ServiceException("saveUser", e);
		}
		return entite;
	}

	public void removeUser(User entite) throws ServiceException {
		try {
			dao.remove(entite);
		} catch (DAOException e) {
			throw new ServiceException("removeUser", e);
		}
	}

	public User getUser(Integer id) throws ServiceException {
		User u = null;
		try {
			u = dao.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException("getUser", e);
		}
		return u;
	}

	public List<User> getALLUsers() throws ServiceException {
		List<User> users = null;
		try {
			users = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("getALLUsers", e);
		}
		return users;
	}

	public Integer exist(String email, Integer id) throws ServiceException {
		Integer nb;
		nb = dao.CountEmailUseWithoutId(email, id);
		return nb;
	}

}
