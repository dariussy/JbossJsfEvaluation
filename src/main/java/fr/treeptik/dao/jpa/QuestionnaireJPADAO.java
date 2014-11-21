package fr.treeptik.dao.jpa;

import javax.ejb.Stateless;

import fr.treeptik.dao.QuestionnaireDAO;
import fr.treeptik.model.Questionnaire;

@Stateless
public class QuestionnaireJPADAO extends GenericJPADAO<Questionnaire, Integer>
		implements QuestionnaireDAO {

	public QuestionnaireJPADAO() {
		super(Questionnaire.class);
	}

}
