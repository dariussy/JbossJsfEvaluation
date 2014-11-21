package fr.treeptik.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.treeptik.dao.StagiaireDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.model.User;
import fr.treeptik.service.StagiaireService;

@Stateless
public class StagiaireServiceImpl implements StagiaireService {
	@EJB
	public StagiaireDAO dao;

	// @EJB
	// public MailUtils mailUtils;

	/* (non-Javadoc)
	 * @see fr.treeptik.service.impl.StagiaireService#saveStagiaire(fr.treeptik.model.Stagiaire)
	 */
	@Override
	public User saveStagiaire(Stagiaire entite) throws ServiceException {
		try {
			if (entite.getId() != null && entite.getId() != 0) {
				entite = dao.update(entite);
			} else {
				entite.setId(null);
				entite.setPassword(generatePassword());
				entite = dao.add(entite);
				// mailUtils.mailSenderToStagiaire(entite);
			}
		} catch (DAOException e) {
			throw new ServiceException("saveStagiaire", e);
		}
		return entite;
	}

	/* (non-Javadoc)
	 * @see fr.treeptik.service.impl.StagiaireService#removeStagiaire(fr.treeptik.model.Stagiaire)
	 */
	@Override
	public void removeStagiaire(Stagiaire entite) throws ServiceException {
		try {

			dao.remove(entite);
		} catch (DAOException e) {
			throw new ServiceException("removeStagiaire", e);
		}
	}

	/* (non-Javadoc)
	 * @see fr.treeptik.service.impl.StagiaireService#getStagiaireById(java.lang.Integer)
	 */
	@Override
	public User getStagiaireById(Integer id) throws ServiceException {
		User u = null;
		try {
			u = dao.findByPk(id);
		} catch (DAOException e) {
			throw new ServiceException("getStagiaire", e);
		}
		return u;
	}

	/* (non-Javadoc)
	 * @see fr.treeptik.service.impl.StagiaireService#getALLStagiaires()
	 */
	@Override
	public List<Stagiaire> getALLStagiaires() throws ServiceException {
		List<Stagiaire> stagiaires = null;
		try {
			stagiaires = dao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("getALLStagiaires", e);
		}
		return stagiaires;
	}

	/* (non-Javadoc)
	 * @see fr.treeptik.service.impl.StagiaireService#generatePassword()
	 */
	@Override
	public String generatePassword() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32).substring(0, 10);
	}

}
