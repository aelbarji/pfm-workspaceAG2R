package pilotage.services.actions.liste;

import java.text.MessageFormat;
import java.util.List;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.services.ServicesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Services_Liste;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ModifierServicesListeAction extends AbstractAction{

	private static final long serialVersionUID = -8234699442688020617L;
	private Integer selectRow;
	private String  libelle;
	private Boolean  libelleChanged;
	private String consigne_meteo;
	private Boolean consigneChanged;

	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private String filtreNomService;

	private Pagination<Services_Liste> pagination;
	private List<Services_Liste> services_Listes;
	private Services_Liste services_Liste;
	
	public Integer getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(Integer selectRow) {
		this.selectRow = selectRow;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getConsigne_meteo() {
		return consigne_meteo;
	}

	public void setConsigne_meteo(String consigne_meteo) {
		this.consigne_meteo = consigne_meteo;
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

	public String getFiltreNomService() {
		return filtreNomService;
	}

	public void setFiltreNomService(String filtreNomService) {
		this.filtreNomService = filtreNomService;
	}

	public Pagination<Services_Liste> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Services_Liste> pagination) {
		this.pagination = pagination;
	}

	public List<Services_Liste> getServices_Listes() {
		return services_Listes;
	}

	public void setServices_Listes(List<Services_Liste> services_Listes) {
		this.services_Listes = services_Listes;
	}

	public Services_Liste getServices_Liste() {
		return services_Liste;
	}

	public void setServices_Liste(Services_Liste services_Liste) {
		this.services_Liste = services_Liste;
	}

	public Boolean getLibelleChanged() {
		return libelleChanged;
	}

	public void setLibelleChanged(Boolean libelleChanged) {
		this.libelleChanged = libelleChanged;
	}

	public Boolean getConsigneChanged() {
		return consigneChanged;
	}

	public void setConsigneChanged(Boolean consigneChanged) {
		this.consigneChanged = consigneChanged;
	}

	@Override
	protected boolean validateMetier() {
		try {
			//vérifie que le nouveau nom du service n'existe pas deja
			if (libelleChanged && ServicesDatabaseService.exists(selectRow, libelle)) {
				error = MessageFormat.format(getText("services.liste.creation.existe.deja"), libelle);
				return false;
			}
		} 
		catch (Exception e) {
			error = getText("error.message.generique")+ " : " + e.getMessage();
			erreurLogger.error("Modification d'un service", e );
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try {
			if(libelleChanged || consigneChanged)
				ServicesDatabaseService.modifier(selectRow, libelle, consigne_meteo);

			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.services.liste.modification"),new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_SERVICES);

			info = MessageFormat.format(getText("services.liste.modification.valide"), new Object[]{libelle});
			
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique")+ " : " + e.getMessage();
			erreurLogger.error("Modification d'une liste des services", e);
			return ERROR;
		}
	}

}
