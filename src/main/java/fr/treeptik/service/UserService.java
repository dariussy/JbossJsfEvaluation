package fr.treeptik.service;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.User;

public interface UserService {
	public User saveUser(User entite) throws ServiceException;

	public Integer exist(String email, Integer id) throws ServiceException;
}
