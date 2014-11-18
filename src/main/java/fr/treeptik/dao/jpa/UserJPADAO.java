package fr.treeptik.dao.jpa;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.treeptik.dao.UserDAO;
import fr.treeptik.model.User;

@Stateless
public class UserJPADAO extends GenericJPADAO<User, Integer> implements UserDAO {

	public UserJPADAO() {
		super(User.class);
	}

	@Override
	public Integer CountEmailUseWithoutId(String email, Integer id) {
		String req = "";
		if (id != null) {
			req = "Select count(*) from  User where email=:email and id!=:id ";
		} else {
			req = "Select count(*) from  User where email=:email ";
		}

		Query createQuery = getEntityManager().createQuery(req);

		createQuery.setParameter("email", email);
		if (id != null) {
			createQuery.setParameter("id", id);
		}

		Number result = (Number) createQuery.getSingleResult();

		return result.intValue();
	}

}
