package pilotage.consignes.bbr;

import java.util.List;

import pilotage.database.consigne.ConsigneBbrDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bbr_Consignes;
import pilotage.metier.Consignes_Fichier;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowConsigneBbrAction extends AbstractAction {
	private static final long serialVersionUID = -4037713125016637085L;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private Pagination<Bbr_Consignes> pagination;
	private List<Bbr_Consignes> listConsigneBbr;
	private List<Consignes_Fichier> listFichier;

	private String filtreOrigine;
	private String filtreComposant;
	private String filtreType;
	private String filtreLocalisation;
	private String filtreString;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
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

	public Pagination<Bbr_Consignes> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Bbr_Consignes> pagination) {
		this.pagination = pagination;
	}

	public List<Bbr_Consignes> getListConsigneBbr() {
		return listConsigneBbr;
	}

	public void setListConsigneBbr(List<Bbr_Consignes> listConsigneBbr) {
		this.listConsigneBbr = listConsigneBbr;
	}

	public List<Consignes_Fichier> getListFichier() {
		return listFichier;
	}

	public void setListFichier(List<Consignes_Fichier> listFichier) {
		this.listFichier = listFichier;
	}

	public String getFiltreOrigine() {
		return filtreOrigine;
	}

	public void setFiltreOrigine(String filtreOrigine) {
		this.filtreOrigine = filtreOrigine;
	}

	public String getFiltreComposant() {
		return filtreComposant;
	}

	public void setFiltreComposant(String filtreComposant) {
		this.filtreComposant = filtreComposant;
	}

	public String getFiltreType() {
		return filtreType;
	}

	public void setFiltreType(String filtreType) {
		this.filtreType = filtreType;
	}

	public String getFiltreLocalisation() {
		return filtreLocalisation;
	}

	public void setFiltreLocalisation(String filtreLocalisation) {
		this.filtreLocalisation = filtreLocalisation;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {		
		//recuperation des users suivant les filtres, le tri et la page
		if(page < 1)
			page = 1;
		else if(nrPages != 0 && page > nrPages)
			page = nrPages;
		if(nrPerPage == 0)
			nrPerPage = PilotageConstants.NB_CONSIGNES_BBR_PER_PAGE;
		if(sens == null || "".equals(sens))
			sens = "asc";
		if(sort == null || "".equals(sort))
			sort = "bbrtype";
		pagination = new Pagination<Bbr_Consignes>(page, nrPerPage);
		listConsigneBbr = ConsigneBbrDatabaseService.getAll(pagination, sort, sens, filtreOrigine, filtreComposant, filtreType, filtreLocalisation);
		
		//listes utiles aux filtres
		//listFichier = ConsigneFichierDatabaseService.getAll();
		
		//filtre
		filtreString = ShowConsigneBbrAction.filtreToString(filtreOrigine, filtreComposant, filtreType, filtreLocalisation);
		
		if(filtreOrigine != null)
			filtreOrigine = filtreOrigine.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		if(filtreComposant != null)
			filtreComposant = filtreComposant.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		if(filtreType != null)
			filtreType = filtreType.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		if(filtreLocalisation != null)
			filtreLocalisation = filtreLocalisation.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
		
		return OK;
	}
	
	/**
	 * Met le filtre sous forme de string pour affichage
	 * @param filtreOrigine
	 * @param filtreComposant
	 * @param filtreType
	 * @param filtreLocalisation
	 * @param listFichier 
	 * @return
	 */
	public static String filtreToString(String filtreOrigine, String filtreComposant, String filtreType, String filtreLocalisation) {
		StringBuffer buffer = new StringBuffer();
		if (filtreOrigine != null && !filtreOrigine.equals("")) {
			buffer.append("Origine : ");
			buffer.append(filtreOrigine);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		if (filtreComposant != null && !filtreComposant.equals("")) {
			buffer.append("Composant : ");
			buffer.append(filtreComposant);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		if (filtreType != null && !filtreType.equals("")) {
			buffer.append("Type : ");
			buffer.append(filtreType);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		if (filtreLocalisation != null && !filtreLocalisation.equals("")) {
			buffer.append("Localisation : ");
			buffer.append(filtreLocalisation);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		if(buffer.length() != 0) {
			return buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR));
		}
		else {
			return null;
		}
	}
}
