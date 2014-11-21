package fr.treeptik.controlleur;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Administrateur;
import fr.treeptik.service.AdministateurService;
import fr.treeptik.utils.SessionBean;

@ManagedBean
@RequestScoped
public class AdministrateurControlleur {

	private Administrateur administrateur = new Administrateur();

	@EJB
	private AdministateurService serviceAdmin;

	@EJB
	private SessionBean sessionBean;

	// **********LISTES*****************************************************
	private List<Administrateur> listAdministrateur = new ArrayList<Administrateur>();

	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel administrateurs;

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Administrateur entity = (Administrateur) administrateurs.getRowData();
		try {
			entity = (Administrateur) serviceAdmin.getAdministrateurById(entity
					.getId());
			System.out.println(entity);
			serviceAdmin.removeAdministrateur(entity);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		administrateurs = new ListDataModel();
		try {
			administrateurs
					.setWrappedData(serviceAdmin.getALLAdministrateurs());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAdmin";
	}

	public String doSelectUpdate() {
		administrateur = (Administrateur) administrateurs.getRowData();
		System.out.println("idAdmin : " + administrateur);
		return "editAdmin";
	}

	public String doPrepareCreate() {
		administrateur = new Administrateur();
		return "editAdmin";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		try {
			serviceAdmin.saveAdministrateur(administrateur);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		administrateurs = new ListDataModel();
		try {
			administrateurs
					.setWrappedData(serviceAdmin.getALLAdministrateurs());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listAdmin";
	}

	public String doSave() {
		try {
			serviceAdmin.saveAdministrateur(this.administrateur);
		} catch (ServiceException e) {
			throw new RuntimeException();
		}
		return "listAdmin";
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public AdministateurService getService() {
		return serviceAdmin;
	}

	public void setService(AdministateurService service) {
		this.serviceAdmin = service;
	}

	public List<Administrateur> getListAdministrateur() {
		return listAdministrateur;
	}

	public void setListAdministrateur(List<Administrateur> listAdministrateur) {
		this.listAdministrateur = listAdministrateur;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getAdministrateurs() {
		if (administrateurs == null) {
			administrateurs = new ListDataModel();
			try {
				administrateurs.setWrappedData(serviceAdmin
						.getALLAdministrateurs());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return administrateurs;
	}

	@SuppressWarnings("rawtypes")
	public void setAdministrateurs(DataModel administrateurs) {
		this.administrateurs = administrateurs;
	}

}
