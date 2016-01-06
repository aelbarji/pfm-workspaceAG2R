package pilotage.consignes.bbr;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

import pilotage.database.consigne.ConsigneBbrDatabaseService;
import pilotage.database.consigne.ConsigneFichierDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Consignes_Fichier;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class CreateConsigneBbrAction extends AbstractAction {
	private static final long serialVersionUID = -1299589526541605339L;
	
	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreOrigine;
	private String filtreComposant;
	private String filtreType;
	private String filtreLocalisation;
	
	private List<Consignes_Fichier> listFichier;

	private String origine;
	private String composant;
	private String type;
	private String localisation;
	private BigDecimal priority;
	
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

	public List<Consignes_Fichier> getListFichier() {
		return listFichier;
	}

	public void setListFichier(List<Consignes_Fichier> listFichier) {
		this.listFichier = listFichier;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getComposant() {
		return composant;
	}

	public void setComposant(String composant) {
		this.composant = composant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public BigDecimal getPriority() {
		return priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			localisation = localisation.substring(1);
			if(localisation.charAt(localisation.length()-1)==' '){
				localisation = localisation.substring(0,localisation.length()-1);
			}
			long id = ConsigneBbrDatabaseService.create(origine, composant, type, localisation, priority);
			info = MessageFormat.format(getText("consigne.bbr.creation.valide"), new Object[]{origine});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.consigne.bbr.creation") ,new Object[]{id}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CONSIGNES);
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une consigne bbr - ", e);
			listFichier = ConsigneFichierDatabaseService.getAll();
			return ERROR;
		}
	}
}
