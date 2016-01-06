package pilotage.bilan.envoie;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Environnement;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;

public class ModifyBilanEnvoieAction extends AbstractAction {

	private static final long serialVersionUID = 7147929913309898436L;
	
	private Integer selectedID;
	private String nom;
	private String libelle;
	private String[] clauseSelect;
	
	private boolean vacation;
	private boolean actionEPI;
	private boolean espaceDisk;
	private boolean disknonOCEOR;
	private boolean etatCFT;
	
	private Integer nbDailySent;
	
	private Boolean nomChanged;
	
	private List<Environnement> listEnvi;

	public Integer getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Integer selectedID) {
		this.selectedID = selectedID;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String[] getClauseSelect() {
		return clauseSelect;
	}

	public void setClauseSelect(String[] clauseSelect) {
		this.clauseSelect = clauseSelect;
	}

	public boolean isVacation() {
		return vacation;
	}

	public void setVacation(boolean vacation) {
		this.vacation = vacation;
	}

	public boolean isActionEPI() {
		return actionEPI;
	}

	public void setActionEPI(boolean actionEPI) {
		this.actionEPI = actionEPI;
	}

	public boolean isEspaceDisk() {
		return espaceDisk;
	}

	public void setEspaceDisk(boolean espaceDisk) {
		this.espaceDisk = espaceDisk;
	}

	public boolean isDisknonOCEOR() {
		return disknonOCEOR;
	}

	public void setDisknonOCEOR(boolean disknonOCEOR) {
		this.disknonOCEOR = disknonOCEOR;
	}

	public boolean isEtatCFT() {
		return etatCFT;
	}

	public void setEtatCFT(boolean etatCFT) {
		this.etatCFT = etatCFT;
	}

	public Integer getNbDailySent() {
		return nbDailySent;
	}

	public void setNbDailySent(Integer ndDailySent) {
		this.nbDailySent = ndDailySent;
	}

	public Boolean getNomChanged() {
		return nomChanged;
	}

	public void setNomChanged(Boolean nomChanged) {
		this.nomChanged = nomChanged;
	}

	public List<Environnement> getListEnvi() {
		return listEnvi;
	}

	public void setListEnvi(List<Environnement> listEnvi) {
		this.listEnvi = listEnvi;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(nomChanged && BilanEnvoieDatabaseService.exists(selectedID, nom)){
				error = MessageFormat.format(getText("bilan.envoie.existe.deja"), nom);
				listEnvi = EnvironmentDatabaseService.getAll();
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type de bilan - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String historique = "";
			Bilan_Envoie be = BilanEnvoieDatabaseService.get(selectedID);
			if (!nom.equals(be.getNom())) {
				historique += "nom, ";
			}
			if (!libelle.equals(be.getLibelle())) {
				historique += "libelle, ";
			}
			if (!(StringConverter.arrayToString(clauseSelect == null ? new String[]{""} : clauseSelect, PilotageConstants.BILAN_ENVOIE_SEPARATEUR)).equals(be.getClauseSelect())) {
				historique += "clauseSelect, ";
			}
			if (vacation != be.isVacation()) {
				historique += "vacation, ";
			}
			if (actionEPI != be.isActionEPI()) {
				historique += "actionEPI, ";
			}
			if (espaceDisk != be.isEspaceDisk()) {
				historique += "espaceDisk, ";
			}
			if (disknonOCEOR != be.isDisknonOCEOR()) {
				historique += "disknonOCEOR, ";
			}
			if (etatCFT != be.isEtatCFT()) {
				historique += "etatCFT, ";
			}
			if (!nbDailySent.equals(be.getNbDailySent())) {
				historique += "nbDailySent, ";
			}
			BilanEnvoieDatabaseService.update(selectedID, nom, libelle, StringConverter.arrayToString(clauseSelect == null ? new String[]{""} : clauseSelect, PilotageConstants.BILAN_ENVOIE_SEPARATEUR), vacation, actionEPI, espaceDisk, disknonOCEOR, etatCFT, nbDailySent);
			info = MessageFormat.format(getText("bilan.envoie.modification.valide"), nom);
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.type.modification"), new Object[]{selectedID, historique}) , (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);

			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un type de bilan - ", e);
			return ERROR;
		}
	}

}
