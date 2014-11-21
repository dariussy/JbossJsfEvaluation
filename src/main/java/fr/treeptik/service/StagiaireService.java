package fr.treeptik.service;

import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.User;

public interface StagiaireService {

	public User saveStagiaire(Stagiaire entite) throws ServiceException;

	public void removeStagiaire(Stagiaire entite) throws ServiceException;

	public User getStagiaireById(Integer id) throws ServiceException;

	public List<Stagiaire> getALLStagiaires() throws ServiceException;

	public String generatePassword();

}