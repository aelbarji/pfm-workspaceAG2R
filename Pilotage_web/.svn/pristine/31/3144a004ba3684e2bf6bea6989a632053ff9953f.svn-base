package pilotage.bilan.alertes.types;

import java.util.List;

import pilotage.database.bilan.AlertesTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Type;

public class ShowTypesAction extends AbstractAction {

	private static final long serialVersionUID = -3804080495534907370L;
	private List<Alertes_Type> listType;
	private String libelle;

	public List<Alertes_Type> getListType() {
		return listType;
	}

	public void setListType(List<Alertes_Type> listType) {
		this.listType = listType;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if(error == null || error.length() == 0)
			libelle = null;
		
		listType = AlertesTypeDatabaseService.getAll();
		return OK;
	}
}
