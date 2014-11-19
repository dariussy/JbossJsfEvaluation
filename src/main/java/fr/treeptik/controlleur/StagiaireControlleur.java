package fr.treeptik.controlleur;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Stagiaire;
import fr.treeptik.service.impl.StagiaireServiceImpl;

@ManagedBean
@RequestScoped
public class StagiaireControlleur {
	private Stagiaire stagiaire = new Stagiaire();

	@EJB
	private StagiaireServiceImpl stagiaireService;

	// **********LISTES*****************************************************
	private List<Stagiaire> listStagiaire = new ArrayList<Stagiaire>();

	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel stagiaires;

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Stagiaire entity = (Stagiaire) stagiaires.getRowData();
		try {
			entity = (Stagiaire) stagiaireService.getStagiaireById(entity
					.getId());
			System.out.println(entity);
			stagiaireService.removeStagiaire(entity);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		stagiaires = new ListDataModel();
		try {
			stagiaires.setWrappedData(stagiaireService.getALLStagiaires());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listStagiaire";
	}

	public String doSelectUpdate() {
		stagiaire = (Stagiaire) stagiaires.getRowData();
		System.out.println(stagiaire);
		return "editStagiaire";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		try {
			stagiaireService.saveStagiaire(stagiaire);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		stagiaires = new ListDataModel();
		try {
			stagiaires.setWrappedData(stagiaireService.getALLStagiaires());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listStagiaire";
	}

	public String doSave() {
		try {
			stagiaireService.saveStagiaire(stagiaire);
		} catch (ServiceException e) {
			throw new RuntimeException();
		}
		return "listStagiaire";
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getStagiaires() {

		if (stagiaires == null) {
			stagiaires = new ListDataModel();
			try {
				stagiaires.setWrappedData(stagiaireService.getALLStagiaires());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return stagiaires;
	}

	@SuppressWarnings("rawtypes")
	public void setStagiaires(DataModel stagiaires) {
		this.stagiaires = stagiaires;
	}

	public List<Stagiaire> getListStagiaire() {
		return listStagiaire;
	}

	public void setListStagiaire(List<Stagiaire> listStagiaire) {
		this.listStagiaire = listStagiaire;
	}

}
