package pilotage.meteo.etat;

import java.text.MessageFormat;

import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.framework.AbstractAction;

public class CreateEtatMeteoAction extends AbstractAction{

	private static final long serialVersionUID = 2437584439536464703L;

	private String libelle;
	private String codeCouleur;
	private String definition;
	private String impact;
	
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
			if(EtatPossibleDatabaseService.getByName(libelle)!=null){
				error = MessageFormat.format(getText("meteo.etat.creation.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un état Météo - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			codeCouleur = "#"+codeCouleur;
			EtatPossibleDatabaseService.create(libelle, codeCouleur, definition, Integer.parseInt(impact));
			info = MessageFormat.format(getText("meteo.etat.creation.valide"), new Object[]{libelle});
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un état Météo - ", e);
			return ERROR;
		}
	}

}
