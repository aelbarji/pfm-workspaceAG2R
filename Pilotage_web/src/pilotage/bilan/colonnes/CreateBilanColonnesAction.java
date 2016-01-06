package pilotage.bilan.colonnes;

import pilotage.database.bilan.BilanColonnesDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.bilan.IncidentColonnesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Incident_Colonnes;

public class CreateBilanColonnesAction extends AbstractAction {

	private static final long serialVersionUID = 4448133267149851863L;
	
	private Integer bilanSelected;
	private Integer incidentColonneAj;
	private String nomDsBilanAj;
	private String positionAj;
	private String tailleAj;

	
	public Integer getBilanSelected() {
		return bilanSelected;
	}

	public void setBilanSelected(Integer bilanSelected) {
		this.bilanSelected = bilanSelected;
	}

	public Integer getIncidentColonneAj() {
		return incidentColonneAj;
	}

	public void setIncidentColonneAj(Integer incidentColonneAj) {
		this.incidentColonneAj = incidentColonneAj;
	}

	public String getNomDsBilanAj() {
		return nomDsBilanAj;
	}

	public void setNomDsBilanAj(String nomDsBilanAj) {
		this.nomDsBilanAj = nomDsBilanAj;
	}

	public String getPositionAj() {
		return positionAj;
	}

	public void setPositionAj(String positionAj) {
		this.positionAj = positionAj;
	}

	public String getTailleAj() {
		return tailleAj;
	}

	public void setTailleAj(String tailleAj) {
		this.tailleAj = tailleAj;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			Bilan_Envoie b = BilanEnvoieDatabaseService.get(bilanSelected);
			Incident_Colonnes ic = IncidentColonnesDatabaseService.get(incidentColonneAj);
			String s, s1, s2;
			s = positionAj.replace(", ", "");
			s1 = tailleAj.replace(", ", "");
			s2 = nomDsBilanAj.replace(",  ", "");
			
			Integer tailleBilanColCree = Integer.parseInt(s1);
			BilanColonnesDatabaseService.create(s2, Integer.parseInt(s), tailleBilanColCree, ic, b);
			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création bilan colonnes - ", e);
			return ERROR;
		}
		
	}

}
