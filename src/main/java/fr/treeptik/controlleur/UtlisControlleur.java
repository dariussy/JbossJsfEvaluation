package fr.treeptik.controlleur;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.treeptik.utils.SessionBean;

@ManagedBean
@RequestScoped
public class UtlisControlleur {

	@EJB
	private SessionBean sessionBean;

	public String logout() {
		return sessionBean.logout();
	}

}
