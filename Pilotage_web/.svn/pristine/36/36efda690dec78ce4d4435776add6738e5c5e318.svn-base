package pilotage.bilan.colonnes;

import java.util.List;

import pilotage.database.bilan.BilanColonnesDatabaseService;
import pilotage.database.bilan.BilanEnvoieDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bilan_Colonnes;
import pilotage.metier.Bilan_Envoie;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowBilanColonnesAction extends AbstractAction {

	private static final long serialVersionUID = 4095636434866388145L;
	private Pagination<Bilan_Colonnes> pagination;
	private List<Bilan_Colonnes> listColonnes;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	private List<Bilan_Envoie> listBilan;
	
	private String bilanSelected;
	
	public Pagination<Bilan_Colonnes> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Bilan_Colonnes> pagination) {
		this.pagination = pagination;
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

	public String getBilanSelected() {
		return bilanSelected;
	}

	public void setBilanSelected(String bilanSelected) {
		this.bilanSelected = bilanSelected;
	}

	public List<Bilan_Envoie> getListBilan() {
		return listBilan;
	}

	public void setListBilan(List<Bilan_Envoie> listBilan) {
		this.listBilan = listBilan;
	}

	public List<Bilan_Colonnes> getListColonnes() {
		return listColonnes;
	}

	public void setListColonnes(List<Bilan_Colonnes> listColonnes) {
		this.listColonnes = listColonnes;
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
			
			listBilan = BilanEnvoieDatabaseService.getAll();
			Integer bilanS = null;
			
			if(!listBilan.isEmpty())
				bilanS = listBilan.get(0).getId();
			if(bilanSelected != null)
				listColonnes = BilanColonnesDatabaseService.getAll(pagination, Integer.parseInt(bilanSelected));
			else listColonnes = BilanColonnesDatabaseService.getAll(pagination, bilanS);

			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Affichage liste bilan colonnes - ", e);
			return ERROR;	
		}
	}

}
