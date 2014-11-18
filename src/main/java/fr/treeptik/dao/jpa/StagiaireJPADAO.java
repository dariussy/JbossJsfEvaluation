package fr.treeptik.dao.jpa;

import javax.ejb.Stateless;

import fr.treeptik.dao.StagiaireDAO;
import fr.treeptik.model.Stagiaire;

@Stateless
public class StagiaireJPADAO extends GenericJPADAO<Stagiaire, Integer>
		implements StagiaireDAO {

	public StagiaireJPADAO() {
		super(Stagiaire.class);
	}

}
