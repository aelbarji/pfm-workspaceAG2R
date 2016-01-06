package pilotage.environnement.actions.type;

import java.util.List;

import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Environnement_Type;

public class ShowEnvironnementTypeAction extends AbstractAction{

	private static final long serialVersionUID = -1785395727651063779L;

	private String libelle;
	private List<Environnement_Type> environnement_Types;
	
	public void setEnvironnement_Types(List<Environnement_Type> environnement_Types) {
		this.environnement_Types = environnement_Types;
	}

	public List<Environnement_Type> getEnvironnement_Types() {
		return environnement_Types;
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
		environnement_Types = EnvironnementTypeDatabaseService.getAll();
		return OK;
	}

}

