package pilotage.bilan.envoie;

import java.util.List;

import pilotage.database.applicatif.EnvironmentDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Environnement;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.string.StringConverter;

public class RedirectBilanEnvoieAction extends AbstractAction {

	private static final long serialVersionUID = 5996070308674508836L;
	private List<Environnement> listEnvi;
	
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

	public List<Environnement> getListEnvi() {
		return listEnvi;
	}

	public void setListEnvi(List<Environnement> listEnvi) {
		this.listEnvi = listEnvi;
	}

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

	public void setNbDailySent(Integer nbDailySent) {
		this.nbDailySent = nbDailySent;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listEnvi = EnvironmentDatabaseService.getAll();
		
		if(selectedID != null){
			Bilan_Envoie be = BilanEnvoieDatabaseService.get(selectedID);
			
			nom = be.getNom();
			libelle = be.getLibelle();
			clauseSelect = StringConverter.stringToArray(be.getClauseSelect(), PilotageConstants.BILAN_ENVOIE_SEPARATEUR);
			vacation = be.isVacation();
			actionEPI = be.isActionEPI();
			espaceDisk = be.isEspaceDisk();
			disknonOCEOR = be.isDisknonOCEOR();
			etatCFT = be.isEtatCFT();
			listEnvi = EnvironmentDatabaseService.getAll();
			nbDailySent = be.getNbDailySent();
		}
		
		return OK;
	}
}
