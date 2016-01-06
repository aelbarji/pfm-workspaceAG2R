package pilotage.bilan.envoie;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Environnement;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;

public class CreateBilanEnvoieAction extends AbstractAction {

	private static final long serialVersionUID = -7529798428126608855L;
	
	private String nom;
	private String libelle;
	private String[] clauseSelect;
	
	private boolean vacation;
	private boolean actionEPI;
	private boolean espaceDisk;
	private boolean disknonOCEOR;
	private boolean etatCFT;
	
	private Integer nbDailySent;

	private List<Environnement> listEnvi;

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

	public void setNbDailySent(Integer nbDailySent) {
		this.nbDailySent = nbDailySent;
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
			if(BilanEnvoieDatabaseService.exists(null, nom)){
				error = MessageFormat.format(getText("bilan.envoie.existe.deja"), nom);
				listEnvi = EnvironmentDatabaseService.getAll();
				return false;
			}
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type de bilan - ", e);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			BilanEnvoieDatabaseService.create(nom, libelle, StringConverter.arrayToString(clauseSelect == null ? new String[]{""} : clauseSelect , PilotageConstants.BILAN_ENVOIE_SEPARATEUR), vacation, actionEPI, espaceDisk, disknonOCEOR, etatCFT, nbDailySent);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.bilan.type.creation"), new Object[]{nom, BilanEnvoieDatabaseService.getId(nom)}) , (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_BILAN);
			
			
			info = MessageFormat.format(getText("bilan.envoie.creation.valide"), nom);
			return OK;
		}
		catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un type de bilan - ", e);
			return ERROR;
		}
	}

}
