package fr.treeptik.service;

import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Administrateur;
import fr.treeptik.model.User;

public interface AdministateurService {

	public Administrateur saveAdministrateur(Administrateur entite)
			throws ServiceException;

	public void removeAdministrateur(Administrateur entite)
			throws ServiceException;

	public User getAdministrateurById(Integer id) throws ServiceException;

	public List<Administrateur> getALLAdministrateurs() throws ServiceException;

}