package pilotage.consignes;

import java.text.MessageFormat;

import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ModifyConsigneAction extends AbstractActionConsigne{

	private static final long serialVersionUID = 9143972703235735488L;
	
	private Integer consigneID;
	private String consigneText;
	private String fichierConsigne;
	private Boolean important;

	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Pagination<Consignes> pagination;

	public Integer getConsigneID() {
		return consigneID;
	}

	public void setConsigneID(Integer consigneID) {
		this.consigneID = consigneID;
	}
	
	public String getConsigneText() {
		return consigneText;
	}

	public void setConsigneText(String consigneText) {
		this.consigneText = consigneText;
	}

	public String getFichierConsigne() {
		return fichierConsigne;
	}

	public void setFichierConsigne(String fichierConsigne) {
		this.fichierConsigne = fichierConsigne;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public Pagination<Consignes> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Consignes> pagination) {
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

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			String historique = "";
			Consignes c = ConsignesDatabaseService.get(consigneID);
			if (! consigneText.equals(c.getText())) {
				historique += "text, ";
			}
			if (! important.equals(c.getCouleur())) {
				historique += "important, ";
			}
			//modifier une consigne
			if (fichierConsigne == null || fichierConsigne == "") {
				ConsignesDatabaseService.modify(consigneID, null, consigneText, important, null, null, false);
			} else {
				ConsignesDatabaseService.modify(consigneID, null, consigneText, important, null, fichierConsigne, false);
			}
			
			if(!historique.equals(""))
				HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.communication.consigne.modification"), new Object[]{historique,consigneID}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CONSIGNES);

			info = getText("consigne.modification.valide");

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'une consigne - ", e);
			return ERROR;
		}
	}
	
}
