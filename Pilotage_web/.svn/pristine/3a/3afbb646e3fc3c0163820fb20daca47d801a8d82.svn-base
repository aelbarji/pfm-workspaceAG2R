package pilotage.consignes.bbr;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

import pilotage.database.consigne.ConsigneBbrDatabaseService;
import pilotage.database.consigne.ConsigneFichierDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Bbr_Consignes;
import pilotage.metier.Consignes_Fichier;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class ModifyConsigneBbrAction extends AbstractAction{

	private static final long serialVersionUID = 7013818341148152623L;

	private long selectRow;

	private String sort;
	private String sens;
	private int page;
	private int nrPages;
	private int nrPerPage;

	private String filtreOrigine;
	private String filtreComposant;
	private String filtreType;
	private Integer filtreLocalisation;
	
	private List<Consignes_Fichier> listFichier;
	
	private long id;
	private String origine;
	private String composant;
	private String type;
	private String localisation;
	private BigDecimal priority;
	
	private Integer idFichier;

	public long getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(long selectRow) {
		this.selectRow = selectRow;
	}

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

	public Integer getFiltreLocalisation() {
		return filtreLocalisation;
	}

	public void setFiltreLocalisation(Integer filtreLocalisation) {
		this.filtreLocalisation = filtreLocalisation;
	}

	public List<Consignes_Fichier> getListFichier() {
		return listFichier;
	}

	public void setListFichier(List<Consignes_Fichier> listFichier) {
		this.listFichier = listFichier;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getIdFichier() {
		return idFichier;
	}

	public void setIdFichier(Integer idFichier) {
		this.idFichier = idFichier;
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
			String historique = " ";
			Bbr_Consignes bbr = ConsigneBbrDatabaseService.get(selectRow);
			if (!origine.equals(bbr.getBbrorigine())) {
				historique += "origine, ";
			}
			if (!composant.equals(bbr.getBbrcomposant())) {
				historique += "composant, ";
			}
			if (!type.equals(bbr.getBbrtype())) {
				historique += "type, ";
			}
			if (!bbr.getLocalisation().equals(localisation)) {
				historique += "localisation, ";
			}
			if (!priority.equals(bbr.getBbrpriority())) {
				historique += "priority, ";
			}

			ConsigneBbrDatabaseService.modify(selectRow, origine, composant, type, localisation, priority);
			
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.consigne.bbr.modification") ,new Object[]{historique, selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CONSIGNES);
			
			info = MessageFormat.format(getText("consigne.bbr.modification.valide"), new Object[]{origine});
			
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une consigne bbr - ", e);
			prepareRedirectToModificationPage();
			return ERROR;
		}
	}
	
	private void prepareRedirectToModificationPage(){
		listFichier = ConsigneFichierDatabaseService.getAll();
		Bbr_Consignes bbr = ConsigneBbrDatabaseService.get(selectRow);
		id = bbr.getId();
		origine = bbr.getBbrorigine();
		composant = bbr.getBbrcomposant();
		type = bbr.getBbrtype();
		localisation = bbr.getLocalisation();
		priority = bbr.getBbrpriority();
		idFichier = ConsigneFichierDatabaseService.getId(localisation);
	}
}
