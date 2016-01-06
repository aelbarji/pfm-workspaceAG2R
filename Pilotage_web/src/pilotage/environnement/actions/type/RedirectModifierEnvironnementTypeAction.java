package pilotage.environnement.actions.type;

import pilotage.database.environnement.EnvironnementTypeDatabaseService;
import pilotage.framework.AbstractAction;

public class RedirectModifierEnvironnementTypeAction extends AbstractAction{

	private static final long serialVersionUID = -3979152144001593713L;
	
	private Integer selectRow;
	private String libelle;
	private Integer principalSelected;
	
	

	public Integer getPrincipalSelected() {
		return principalSelected;
	}

	public void setPrincipalSelected(Integer principalSelected) {
		this.principalSelected = principalSelected;
	}

	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
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
		
		principalSelected = EnvironnementTypeDatabaseService.get(selectRow).getPrincipal();
		libelle = EnvironnementTypeDatabaseService.get(selectRow).getType();
		
		return OK;
	}

}

