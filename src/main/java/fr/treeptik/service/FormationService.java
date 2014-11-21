package fr.treeptik.service;

import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Formation;

public interface FormationService {

	public Formation saveFormation(Formation entite) throws ServiceException;

	public void removeFormation(Formation entite) throws ServiceException;

	public Formation getFormation(Integer id) throws ServiceException;

	public List<Formation> getALLFormations() throws ServiceException;

}