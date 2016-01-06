package pilotage.derogation.type.changement;

import java.util.List;

import pilotage.database.derogation.DerogationTypeChangementDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Derogation_Type_Changement;

public class ShowDerogationTypeChangementAction extends AbstractAction {

	private static final long serialVersionUID = 4543670084739141460L;
	private List<Derogation_Type_Changement> listDTC;
	private String libelle;

	public List<Derogation_Type_Changement> getListDTC() {
		return listDTC;
	}

	public void setListDTC(List<Derogation_Type_Changement> listDTC) {
		this.listDTC = listDTC;
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
		if(error == null || error.equals(""))
			libelle = null;
		
		listDTC = DerogationTypeChangementDatabaseService.getAll();
		return OK;
	}

	
}
