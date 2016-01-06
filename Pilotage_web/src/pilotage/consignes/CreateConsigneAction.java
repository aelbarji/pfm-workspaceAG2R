package pilotage.consignes;

import java.text.MessageFormat;
import java.util.Date;

import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes;
import pilotage.metier.Consignes_Type;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.utils.Pagination;

public class CreateConsigneAction extends AbstractActionConsigne{

	private static final long serialVersionUID = -478915291659842379L;
	
	private String consigneText;
	private String fichierConsigne;
	private Boolean important;
	private Integer typeConsigne;
	
	private int page;
	private int nrPages;
	private int nrPerPage;
	
	private Pagination<Consignes> pagination;

	public Pagination<Consignes> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<Consignes> pagination) {
		this.pagination = pagination;
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

	public Integer getTypeConsigne() {
		return typeConsigne;
	}

	public void setTypeConsigne(Integer typeConsigne) {
		this.typeConsigne = typeConsigne;
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

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			Users user = (Users)session.get(PilotageConstants.USER_LOGGED);
			
			//Récupération du type de consigne
			Consignes_Type type = null;
			if(typeConsigne != null && typeConsigne != 0)
				type = ConsignesDatabaseService.getType(typeConsigne);
			else
				type = ConsignesDatabaseService.getType(PilotageConstants.CONSIGNE_PERMANENTE);
			
			//creation d'une nouvelle consigne
			if (fichierConsigne == null || fichierConsigne == "") {
				ConsignesDatabaseService.create(new Date(), consigneText, important, user, null, false, type);				
			} else {
				ConsignesDatabaseService.create(new Date(), consigneText, important, user, fichierConsigne, false, type);
			}
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.communication.consigne.creation"), new Object[]{ConsignesDatabaseService.getId(consigneText, important)}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CONSIGNES);

			info = getText("consigne.creation.valide");

			//pour chaque pilote, on envoie un mail
			//List<Users> piloteList = UsersDatabaseService.getPiloteList();
			/* try{
				boolean allMailsSent = true;
				for(int i = 0; i < piloteList.size(); ++i){
					Users u = piloteList.get(i);
					if(!MailService.sendEmail(u.getEmail(),PilotageConstants.MAILSERVER_CREATE_CONSIGNE_SUBJECT, PilotageConstants.MAILSERVER_CREATE_CONSIGNE_TEXTE, null, null))
						allMailsSent = false;
				}
				if(!allMailsSent)
					error = getText("consigne.creation.sendEmail.failed");
			}
			catch (Exception e) {
				error = getText("consigne.creation.sendEmail.failed");
			}
			*/
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'une consigne - ", e);
			return ERROR;
		}
	}

}
