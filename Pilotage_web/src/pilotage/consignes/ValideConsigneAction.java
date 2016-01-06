package pilotage.consignes;

import pilotage.database.consigne.ConsignePiloteValidationDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class ValideConsigneAction extends AbstractActionConsigne{

	private static final long serialVersionUID = -2374517700364910901L;
	
	private int consigneID;

	private int page;
	private Pagination<Consignes> pagination;

	public int getConsigneID() {
		return consigneID;
	}

	public void setConsigneID(int consigneID) {
		this.consigneID = consigneID;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Pagination<Consignes> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Consignes> pagination) {
		this.pagination = pagination;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Users user = (Users)session.get(PilotageConstants.USER_LOGGED);
			
			//modifier une validation
			ConsignePiloteValidationDatabaseService.modify(consigneID, user, PilotageConstants.CONSIGNE_LUE_COMPRISE, null);
			info = getText("consigne.valider.valide");
	
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une consigne - ", e);
			return ERROR;
		}

	}

}
