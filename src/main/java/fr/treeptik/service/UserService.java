package fr.treeptik.service;

import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.User;

public interface UserService {

	public User saveUser(User entite) throws ServiceException;

	public void removeUser(User entite) throws ServiceException;

	public User getUser(Integer id) throws ServiceException;

	public List<User> getALLUsers() throws ServiceException;

	public Integer exist(String email, Integer id) throws ServiceException;

}