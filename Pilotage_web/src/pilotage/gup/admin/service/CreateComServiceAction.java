package pilotage.gup.admin.service;

import pilotage.database.gup.ComServiceDatabaseService;
import pilotage.framework.AbstractAction;

public class CreateComServiceAction extends AbstractAction{

	private static final long serialVersionUID = 4353036899481366948L;
	
	private String nom;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			ComServiceDatabaseService.create(nom);
			return OK;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
	}

}
