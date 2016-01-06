package pilotage.consignes.rechercher;

import java.util.ArrayList;
import java.util.List;

import pilotage.database.consigne.ConsigneFichierDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Consignes_Fichier;
import pilotage.metier.Consignes_Mots;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class RechercherConsignesAction extends AbstractAction{

	private static final long serialVersionUID = -111482322537082833L;

	private String motCle;
	private String rechercherString;
	private String filtreString;
	private List<Consignes_Fichier> cFichiers;
	private String filtreNom;
	
	private Integer fichierNombre;
	private Integer debNombre;
	private Integer finNombre;
	
	private Pagination<Consignes_Fichier> pagination;
	private String sens;
	private String sort;
	private int page;
	private int nrPages;
	private int nrPerPage;

	public Integer getDebNombre() {
		return debNombre;
	}

	public void setDebNombre(Integer debNombre) {
		this.debNombre = debNombre;
	}

	public Integer getFinNombre() {
		return finNombre;
	}

	public void setFinNombre(Integer finNombre) {
		this.finNombre = finNombre;
	}

	public Pagination<Consignes_Fichier> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Consignes_Fichier> pagination) {
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
	
	public String getFiltreString() {
		return filtreString;
	}

	public void setFiltreString(String filtreString) {
		this.filtreString = filtreString;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getFichierNombre() {
		return fichierNombre;
	}

	public void setFichierNombre(Integer fichierNombre) {
		this.fichierNombre = fichierNombre;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}

	public String getFiltreNom() {
		return filtreNom;
	}

	public void setFiltreNom(String filtreNom) {
		this.filtreNom = filtreNom;
	}

	public String getRechercherString() {
		return rechercherString;
	}

	public void setRechercherString(String rechercherString) {
		this.rechercherString = rechercherString;
	}

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public List<Consignes_Fichier> getCFichiers() {
		return cFichiers;
	}

	public void setCFichiers(List<Consignes_Fichier> cFichiers) {
		this.cFichiers = cFichiers;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			//recuperation des consigne fichiers suivant les filtres, le tri et la page
			if(page < 1)
				page = 1;
			else if(nrPages!= 0 && page > nrPages)
				page = nrPages;
			if(nrPerPage == 0)
				nrPerPage = PilotageConstants.NB_CONSIGNES_PER_PAGE;
			if(sort == null || "".equals(sort))
				sort = "nomFichier";
			if(sens == null || "".equals(sens))
				sens = "asc";
			pagination = new Pagination<Consignes_Fichier>(page, nrPerPage);
			
			//trouver l'ID qui est conforme aux conditions
			List<String> listMots = new ArrayList<String>();
			if(motCle != null && motCle.length() > 0)
				listMots.add(motCle.toLowerCase().substring(0, (motCle.length() < 9 ? motCle.length():9)));
			if(rechercherString != null){
				for(String mot : rechercherString.split(" ")){
					if(mot.trim().length() > 0)
						listMots.add(mot.toLowerCase().substring(0, (mot.length() < 9 ? mot.length():9)));
				}
			}
			List<Consignes_Mots> cMots = ConsigneFichierDatabaseService.getKeyWordsList(listMots);
			if((cMots.size() != listMots.size()) || cMots.isEmpty()){
				cFichiers = new ArrayList<Consignes_Fichier>();
				pagination.setNrPages(1);
			}
			else{
				cFichiers = ConsigneFichierDatabaseService.getAllConsigneFichier(pagination, sort, sens, filtreNom, cMots);
			}
			
			//Met les filtres et le titre sous forme string pour affichage
			fichierNombre = pagination.getTotalElements();
			debNombre = RechercherConsignesAction.getDebNombre(page, nrPerPage);
			finNombre = RechercherConsignesAction.getFinNombre(page, nrPerPage, fichierNombre);
			rechercherString = RechercherConsignesAction.rechercherToString(rechercherString, motCle);
			filtreString = RechercherConsignesAction.filtreToString(filtreNom);
			
			//met à jour le nombre de recherche sur le mot
			ConsigneFichierDatabaseService.updateSearchNumber(motCle);
			
			motCle = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Recherche de fichiers - ", e);
			return ERROR;
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param rechercherString
	 * @param motCle
	 * @return
	 */
	public static String rechercherToString(String rechercherString, String motCle){
		StringBuffer buffer = new StringBuffer();
		if(rechercherString == null || "".equals(rechercherString)){
			return motCle;
		}
		else {
			String[] arr = rechercherString.split(" ");
			Boolean bl = false;
			for(int i = 0;i < arr.length ; i++){
				if(arr[i].equals(motCle)){
					bl = true;
				}
			} 
			if(bl){
				return rechercherString;
			}else {
				buffer.append(rechercherString);
				buffer.append(" ");
				buffer.append(motCle);
				return buffer.toString();
			}
		}
	}
	
	/**
	 * Met les filtres sous forme string pour affichage
	 * @param filtreNom
	 * @return
	 */
	public static String filtreToString(String filtreNom){
		StringBuffer buffer = new StringBuffer();
		if (filtreNom != null && !filtreNom.equals("")) {
			buffer.append("Nom : ");
			buffer.append(filtreNom);
			buffer.append(PilotageConstants.SEPARATEUR);
		}
		if(buffer.length() != 0)
			return buffer.substring(0, buffer.lastIndexOf(PilotageConstants.SEPARATEUR));
		else
			return null;
	}
	
	/**
	 * Début du nombre de fichiers de la page courante 
	 * @param page
	 * @param nrPerPage
	 * @return
	 */
	private static Integer getDebNombre(Integer page, Integer nrPerPage){
		return (page-1) * nrPerPage+1;
	}
	
	/**
	 * Fin du nombre de fichiers de la page courante
	 * @param page
	 * @param nrPerPage
	 * @param maxNombre
	 * @return
	 */
	private static Integer getFinNombre(Integer page, Integer nrPerPage, Integer maxNombre){
		int finNombre = page*nrPerPage;
		if(finNombre > maxNombre){
			return maxNombre;
		}
		else {
			return finNombre;
		}
	}

}
