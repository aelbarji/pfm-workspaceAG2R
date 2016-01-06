package pilotage.applications;

import java.util.List;

import pilotage.database.applicatif.ApplicatifDatabaseService;
import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Applicatifs_Liste;
import pilotage.metier.Environnement;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ShowApplicationListeAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNom;
	private String filtreDescription;
	private String filtreDocument;
	private String filtreString;

	private Pagination<Applicatifs_Liste> pagination;
	private List<Environnement> listeEnvironnements;
	private List<Applicatifs_Liste> listeApplications;

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

	public String getFiltreNom() {
		return filtreNom;
	}

	public void setFiltreNom(String filtreNom) {
		this.filtreNom = filtreNom;
	}

	public String getFiltreDescription() {
		return filtreDescription;
	}

	public void setFiltreDescription(String filtreDescription) {
		this.filtreDescription = filtreDescription;
	}

	public String getFiltreDocument() {
		return filtreDocument;
	}

	public void setFiltreDocument(String filtreDocument) {
		this.filtreDocument = filtreDocument;
	}

	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	public Pagination<Applicatifs_Liste> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Applicatifs_Liste> pagination) {
		this.pagination = pagination;
	}

	public List<Environnement> getListeEnvironnements() {
		return listeEnvironnements;
	}

	public void setListeEnvironnements(List<Environnement> listeEnvironnements) {
		this.listeEnvironnements = listeEnvironnements;
	}

	public List<Applicatifs_Liste> getListeApplications() {
		return listeApplications;
	}

	public void setListeApplications(List<Applicatifs_Liste> listeApplications) {
		this.listeApplications = listeApplications;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			//recuperation des users suivant les filtres, le tri et la page
			if(page < 1)
				page = 1;
			else if(nrPages != 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_APPLI_PER_PAGE;
			if(sens == null || "".equals(sens))
				sens = "asc";
			if(sort == null || "".equals(sort))
				sort = "applicatif";
			pagination = new Pagination<Applicatifs_Liste>(page, nrPerPage);
	
			//récupération de la liste des appli
			listeApplications = ApplicatifDatabaseService.getAll(pagination, filtreNom, filtreDescription, filtreDocument, sort, sens);
			
			//Récupération de la liste des envs pour le filtre
			listeEnvironnements = EnvironnementDatabaseService.getAll();
			
			//Constitution du filtre sous forme de string
			filtreString = ShowApplicationListeAction.filtreToString(filtreNom, filtreDescription, filtreDocument);
			
			if(filtreNom != null)
				filtreNom = filtreNom.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
			if(filtreDescription != null)
				filtreDescription = filtreDescription.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
			if(filtreDocument != null)
				filtreDocument = filtreDocument.replaceAll("\\\\", "\\\\\\\\").replaceAll("'", "\\\\'");
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Liste des utilisateurs - ", e);
			return ERROR;
		}
	}

	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreDocument 
	 * @param filtreDescription 
	 * @param filtreEnvironnement 
	 * @param filtreNom 
	 */
	public static String filtreToString(String filtreNom, String filtreDescription, String filtreDocument){
		StringBuffer buffer = new StringBuffer();
		if (filtreNom != null && !filtreNom.equals("")) {
			buffer.append("Nom : ");
			buffer.append(filtreNom);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		if (filtreDescription != null && !filtreDescription.equals("")) {
			buffer.append("Description : ");
			buffer.append(filtreDescription);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		if (filtreDocument != null && !filtreDocument.equals("")) {
			buffer.append("Document : ");
			buffer.append(filtreDocument);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		
		if(buffer.length() != 0)
			return buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR));
		else
			return null;
	}
}