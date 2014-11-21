package fr.treeptik.controlleur;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Formation;
import fr.treeptik.service.FormationService;

@ManagedBean
@SessionScoped
public class FormationControlleur {
	private Formation formation = new Formation();

	@EJB
	private FormationService formationService;

	// **********LISTES*****************************************************
	private List<Formation> listFormation = new ArrayList<Formation>();

	// **********DATAMODEL**************************************************
	@SuppressWarnings("rawtypes")
	private DataModel formations;

	@SuppressWarnings("rawtypes")
	public String doDelete() {
		Formation entity = (Formation) formations.getRowData();
		try {
			entity = (Formation) formationService.getFormation(entity.getId());
			System.out.println(entity);
			formationService.removeFormation(entity);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		formations = new ListDataModel();
		try {
			formations.setWrappedData(formationService.getALLFormations());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "listFormation";
	}

	public String doPrepareCreate() {
		formation = new Formation();
		return "edit";
	}

	public String doSelectUpdate() {
		formation = (Formation) formations.getRowData();
		System.out.println(formation);
		return "edit";
	}

	@SuppressWarnings("rawtypes")
	public String doUpdate() {
		try {
			formationService.saveFormation(formation);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		formations = new ListDataModel();
		try {
			formations.setWrappedData(formationService.getALLFormations());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String doSave() {
		try {
			formationService.saveFormation(formation);
		} catch (ServiceException e) {
			throw new RuntimeException();
		}
		return "list";
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	@SuppressWarnings("rawtypes")
	public DataModel getFormations() {

		if (formations == null) {
			formations = new ListDataModel();
			try {
				formations.setWrappedData(formationService.getALLFormations());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return formations;
	}

	@SuppressWarnings("rawtypes")
	public void setFormations(DataModel formations) {
		this.formations = formations;
	}

	public List<Formation> getListFormation() {
		return listFormation;
	}

	public void setListFormation(List<Formation> listFormation) {
		this.listFormation = listFormation;
	}

}
