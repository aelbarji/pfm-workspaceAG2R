package pilotage.derogation.type.changement;

import pilotage.database.derogation.DerogationTypeChangementDatabaseService;
import pilotage.framework.AbstractAction;

public class RedirectModifyDerogationTypeChangementAction extends AbstractAction{
	
	private static final long serialVersionUID = -6970512789903279478L;
	
	private Integer id;
	private String libelle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	protected String executeMetier()  {
		libelle = DerogationTypeChangementDatabaseService.get(id).getTypeChangement();
		return OK;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

}
