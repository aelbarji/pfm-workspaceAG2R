package pilotage.derogation.type;

import java.util.List;

import pilotage.database.derogation.DerogationTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Derogation_Type;

public class ShowDerogationTypeAction extends AbstractAction {

	private static final long serialVersionUID = -3898273710346761810L;

	private List<Derogation_Type> listDT;
	private String libelle;

	public List<Derogation_Type> getListDT() {
		return listDT;
	}

	public void setListDT(List<Derogation_Type> listDT) {
		this.listDT = listDT;
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
		
		listDT = DerogationTypeDatabaseService.getAll();
		return OK;
	}

	
}
