package pilotage.meteo.etat;


import java.text.MessageFormat;

import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.framework.AbstractAction;

public class DeleteEtatMeteoAction extends AbstractAction{

	private static final long serialVersionUID = -2313909144961169028L;
	
	private Integer selectRow;
	private String libelle;

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
		try{
			libelle = EtatPossibleDatabaseService.get(selectRow).getLibelle_etat();
			EtatPossibleDatabaseService.delete(selectRow);
			info = MessageFormat.format(getText("meteo.etat.suppresion.valide"), new Object[]{libelle});
			return OK;
		}
		catch (Exception e) {
			System.out.println("exce");
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression d'un état Météo - ", e);
			return ERROR;
		}
	
	}

}
