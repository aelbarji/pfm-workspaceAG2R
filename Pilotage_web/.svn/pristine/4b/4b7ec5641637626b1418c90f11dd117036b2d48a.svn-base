package pilotage.meteo.etat;

import java.text.MessageFormat;

import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.framework.AbstractAction;

public class ModifyEtatMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 7793932679403547210L;

	private Integer selectRow;
	private String libelle;
	private String codeCouleur;
	private String definition;
	private String impact;
	
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

	public String getCodeCouleur() {
		return codeCouleur;
	}

	public void setCodeCouleur(String codeCouleur) {
		this.codeCouleur = codeCouleur;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	@Override
	protected boolean validateMetier() {
		try{
			
			if(!EtatPossibleDatabaseService.get(selectRow).getLibelle_etat().equals(libelle) 
					&& EtatPossibleDatabaseService.getByName(libelle)!=null){
				info = MessageFormat.format(getText("meteo.etat.creation.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un état Météo - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			codeCouleur = "#"+codeCouleur;
			EtatPossibleDatabaseService.modify(selectRow, libelle, codeCouleur, definition, Integer.parseInt(impact));
			info = MessageFormat.format(getText("meteo.etat.modification.valide"), new Object[]{libelle});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un état Météo - ", e);
			return ERROR;
		}
	}

}
