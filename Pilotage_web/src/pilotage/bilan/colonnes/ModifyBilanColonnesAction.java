package pilotage.bilan.colonnes;

import pilotage.database.bilan.BilanColonnesDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.bilan.IncidentColonnesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Incident_Colonnes;

public class ModifyBilanColonnesAction extends AbstractAction {

	private static final long serialVersionUID = 120947948017739119L;
	private Integer bilanSelected;
	private String incidentColonne;
	private String nomDsBilan;
	private String position;
	private String taille;
	private Integer selectedID;
	
	public Integer getBilanSelected() {
		return bilanSelected;
	}

	public void setBilanSelected(Integer bilanSelected) {
		this.bilanSelected = bilanSelected;
	}

	public String getNomDsBilan() {
		return nomDsBilan;
	}

	public void setNomDsBilan(String nomDsBilan) {
		this.nomDsBilan = nomDsBilan;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getIncidentColonne() {
		return incidentColonne;
	}

	public void setIncidentColonne(String incidentColonne) {
		this.incidentColonne = incidentColonne;
	}

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Bilan_Envoie b = BilanEnvoieDatabaseService.get(bilanSelected);
			Incident_Colonnes ic = IncidentColonnesDatabaseService.get(Integer.parseInt(incidentColonne));
			BilanColonnesDatabaseService.modify(selectedID, nomDsBilan, Integer.parseInt(position), Integer.parseInt(taille), ic, b);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification bilan colonnes - ", e);
			return ERROR;
		}
		
	}

}
