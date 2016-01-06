package pilotage.bilan.disques;

import java.text.MessageFormat;

import pilotage.database.bilan.DisqueDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateDisquesAction extends AbstractAction {

	private static final long serialVersionUID = 4632613190344810576L;
	private String libelle;
	private Integer filiale;
	private String seuil;
	
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	/**
	 * @return the filiale
	 */
	public Integer getFiliale() {
		return filiale;
	}

	/**
	 * @param filiale the filiale to set
	 */
	public void setFiliale(Integer filiale) {
		this.filiale = filiale;
	}
	
	/**
	 * @return the seuil
	 */
	public String getSeuil() {
		return seuil;
	}

	/**
	 * @param seuil the seuil to set
	 */
	public void setSeuil(String seuil) {
		this.seuil = seuil;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(DisqueDatabaseService.exists(null, libelle)){
				error = MessageFormat.format(getText("bilan.disques.existe.deja"), libelle);
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un disque - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			DisqueDatabaseService.create(libelle , filiale == 1? true : false, Float.parseFloat(seuil.replaceAll(",", ".")));
			info = MessageFormat.format(getText("bilan.disques.creation.valide"), libelle);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.disque.creation"), new Object[]{libelle, DisqueDatabaseService.getId(libelle)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un disque - ", e);
			return ERROR;
		}
	}

}
