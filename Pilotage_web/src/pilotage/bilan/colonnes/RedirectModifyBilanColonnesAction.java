package pilotage.bilan.colonnes;

import java.util.ArrayList;
import java.util.List;

import pilotage.database.bilan.BilanColonnesDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.database.bilan.IncidentColonnesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Colonnes;
import pilotage.metier.Bilan_Envoie;
import pilotage.metier.Incident_Colonnes;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class RedirectModifyBilanColonnesAction extends AbstractAction {

	private static final long serialVersionUID = 7356761989064052243L;
	private Pagination<Bilan_Colonnes> pagination;
	private List<Bilan_Colonnes> listColonnes;
	private List<Incident_Colonnes> listIncidentCol;
	private int page;
	private int nrPages;
	private int nrPerPage;
	private String bilanSelected;
	private List<Bilan_Envoie> listBilan;
	private List<Integer> listIdBL;
	private Integer nbColonnes;
	
	public Pagination<Bilan_Colonnes> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Bilan_Colonnes> pagination) {
		this.pagination = pagination;
	}

	public List<Incident_Colonnes> getListIncidentCol() {
		return listIncidentCol;
	}

	public void setListIncidentCol(List<Incident_Colonnes> listIncidentCol) {
		this.listIncidentCol = listIncidentCol;
	}

	public List<Bilan_Colonnes> getListColonnes() {
		return listColonnes;
	}

	public void setListColonnes(List<Bilan_Colonnes> listColonnes) {
		this.listColonnes = listColonnes;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNrPages() {
		return nrPages;
	}

	public void setNrPages(int nrPages) {
		this.nrPages = nrPages;
	}

	public int getNrPerPage() {
		return nrPerPage;
	}

	public void setNrPerPage(int nrPerPage) {
		this.nrPerPage = nrPerPage;
	}

	public List<Bilan_Envoie> getListBilan() {
		return listBilan;
	}

	public void setListBilan(List<Bilan_Envoie> listBilan) {
		this.listBilan = listBilan;
	}

	public List<Integer> getListIdBL() {
		return listIdBL;
	}

	public void setListIdBL(List<Integer> listIdBL) {
		this.listIdBL = listIdBL;
	}

	public String getBilanSelected() {
		return bilanSelected;
	}

	public void setBilanSelected(String bilanSelected) {
		this.bilanSelected = bilanSelected;
	}

	public Integer getNbColonnes() {
		return nbColonnes;
	}

	public void setNbColonnes(Integer nbColonnes) {
		this.nbColonnes = nbColonnes;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;		
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_INCIDENTS_PER_PAGE;
			pagination = new Pagination<Bilan_Colonnes>(page, nrPerPage);
			
			listIdBL = new ArrayList<Integer>();
			
			listIncidentCol = IncidentColonnesDatabaseService.getAll();
			listBilan = BilanEnvoieDatabaseService.getAll();
			Integer bilanS = null;
			if(!listBilan.isEmpty())
				bilanS = listBilan.get(0).getId();
			if(bilanSelected != null)
				listColonnes = BilanColonnesDatabaseService.getAll(pagination, Integer.parseInt(bilanSelected));
			else listColonnes = BilanColonnesDatabaseService.getAll(pagination, bilanS);
			nbColonnes = listColonnes.size();
			for(Bilan_Colonnes ic : listColonnes){
					listIdBL.add(ic.getIncidentColonne().getId());  
			}
			return OK;
		}catch (Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification bilan colonnes - ", e);
			return ERROR;
		}
	}

}
