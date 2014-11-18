package fr.treeptik.dao;

import fr.treeptik.model.User;

public interface UserDAO extends GenericDAO<User, Integer> {

	Integer CountEmailUseWithoutId(String email, Integer id);

}
