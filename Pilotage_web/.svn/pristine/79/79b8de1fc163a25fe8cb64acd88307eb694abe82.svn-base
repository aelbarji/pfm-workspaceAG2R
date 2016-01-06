
 package pilotage.bilan;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pilotage.database.bilan.BilanDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.bilan.DisqueDatabaseService;
import pilotage.database.bilan.EspacesDisquesDatabaseService;
import pilotage.database.bilan.FluxCFTDatabaseService;
import pilotage.database.bilan.FluxErreurDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Disques;
import pilotage.metier.Espaces_Disques;
import pilotage.metier.Flux_CFT;
import pilotage.metier.Flux_Erreur;
import pilotage.metier.Users;
import pilotage.service.date.DateService;

public class ShowBilanAction extends AbstractAction {

	private static final long serialVersionUID = 1665694048336670091L;
	protected Date selectedDate;
	protected Date dateFin;
	protected List<Bilan_Envoie> listType;
	protected Integer selectedType;
	
	protected String currentDate;
	
	protected Bilan bilan;
	
	protected List<Users> listUsers;
	
	protected List<Flux_CFT> listFlux;
	protected List<Disques> listDisques;
	protected Map<Integer, String> cftErreurMap;
	protected Map<Integer, Float> espaceMap;
	protected Map<Integer, Float> seuilMap;
	
	protected Integer previousStatus;

	protected Integer vacMatin;
	protected Integer vacJournee;
	protected Integer vacSoir;
	
	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public List<Bilan_Envoie> getListType() {
		return listType;
	}

	public void setListType(List<Bilan_Envoie> listType) {
		this.listType = listType;
	}

	public Integer getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(Integer selectedType) {
		this.selectedType = selectedType;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public Bilan getBilan() {
		return bilan;
	}

	public void setBilan(Bilan bilan) {
		this.bilan = bilan;
	}

	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

	public List<Flux_CFT> getListFlux() {
		return listFlux;
	}

	public void setListFlux(List<Flux_CFT> listFlux) {
		this.listFlux = listFlux;
	}

	public List<Disques> getListDisques() {
		return listDisques;
	}

	public void setListDisques(List<Disques> listDisques) {
		this.listDisques = listDisques;
	}

	public Map<Integer, String> getCftErreurMap() {
		return cftErreurMap;
	}

	public void setCftErreurMap(Map<Integer, String> cftErreurMap) {
		this.cftErreurMap = cftErreurMap;
	}

	public Map<Integer, Float> getEspaceMap() {
		return espaceMap;
	}

	public void setEspaceMap(Map<Integer, Float> espaceMap) {
		this.espaceMap = espaceMap;
	}

	public Map<Integer, Float> getSeuilMap() {
		return seuilMap;
	}

	public void setSeuilMap(Map<Integer, Float> seuilMap) {
		this.seuilMap = seuilMap;
	}

	public Integer getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(Integer previousStatus) {
		this.previousStatus = previousStatus;
	}

	public Integer getVacMatin() {
		return vacMatin;
	}

	public void setVacMatin(Integer vacMatin) {
		this.vacMatin = vacMatin;
	}

	public Integer getVacJournee() {
		return vacJournee;
	}

	public void setVacJournee(Integer vacJournee) {
		this.vacJournee = vacJournee;
	}

	public Integer getVacSoir() {
		return vacSoir;
	}

	public void setVacSoir(Integer vacSoir) {
		this.vacSoir = vacSoir;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Date d = new Date();
			
			if(selectedDate == null){
				selectedDate = DateService.isCurrentDay730(d) ? d : DateService.getDateEarlier(d);
			}
			
			listType = BilanEnvoieDatabaseService.getAll();
			bilan = BilanDatabaseService.get(selectedDate);
			listUsers = UsersDatabaseService.getPiloteList();
			listFlux = FluxCFTDatabaseService.getAll();
			listDisques = DisqueDatabaseService.getAll();
	
			currentDate = DateService.dateToStr((DateService.isCurrentDay730(d)== true) ? d : DateService.getDateEarlier(d), DateService.p4);

			if(bilan != null){
				vacMatin = bilan.getVacationmatin() == null ? null : bilan.getVacationmatin().getId();
				vacJournee = bilan.getVacationaprem() == null ? null : bilan.getVacationaprem().getId();
				vacSoir = bilan.getVacationnuit() == null ? null : bilan.getVacationnuit().getId();
			}
			else{
				vacMatin = null;
				vacJournee = null;
				vacSoir = null;
			}
			
			cftErreurMap = new HashMap<Integer, String>();
			List<Flux_Erreur> listError = FluxErreurDatabaseService.getAllByDate(selectedDate);
			for(Flux_Erreur fe : listError){
				cftErreurMap.put(fe.getFlux().getId(), fe.getErreur());
			}
			
			espaceMap = new HashMap<Integer, Float>();
			seuilMap = new HashMap<Integer, Float>();
			List<Espaces_Disques> listEspaces = EspacesDisquesDatabaseService.getAllByDate(selectedDate);
			for(Espaces_Disques ed : listEspaces){
				espaceMap.put(ed.getDisque().getId(), ed.getEspace());
				seuilMap.put(ed.getDisque().getId(), ed.getSeuil());
			}
			
			for(Disques disque : listDisques){
				if(seuilMap.get(disque.getId()) == null){
					seuilMap.put(disque.getId(), disque.getSeuil());
				}
			}
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage du Bilan - ", e);
			
			return ERROR;
		}
	}

}
