/**
 * pilotage.consignes
 * 7 juil. 2011
 */
package pilotage.consignes;

import java.util.List;

import pilotage.database.consigne.ConsignePiloteValidationDatabaseService;
import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.mail.MailService;

/**
 * This actions display the question page Input: Consignes consigne;
 * 
 * Output: if question == null return OK -> sendQuestion.jsp else add data to
 * table consignes_pilote_validation and return SUCCESS -> back to the page
 * 
 * @author xxu
 * 
 */
public class SendQuestionAction extends AbstractActionConsigne {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6358267231534908629L;
	private int consigneID;
	private String question;
	private int status;

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the consigneID
	 */
	public int getConsigneID() {
		return consigneID;
	}

	/**
	 * @param consigneID the consigneID to set
	 */
	public void setConsigneID(int consigneID) {
		this.consigneID = consigneID;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		status = 0;
		if (question == null || question.equals("")) {
			return OK;
		} 
		else {
			try{
				Users pilote = (Users) session.get(PilotageConstants.USER_LOGGED);
				ConsignePiloteValidationDatabaseService.modify(consigneID, pilote, PilotageConstants.CONSIGNE_LUE_NON_COMPRISE, question);
	
				Consignes consigne = ConsignesDatabaseService.get(consigneID);
				String[] para = new String[3];
				para[0] = consigne.getText();
				para[1] = pilote.getNom() + " " + pilote.getPrenom();
				para[2] = question.replaceAll("\n", "<br/>");
	
				List<Users> adminList = UsersDatabaseService.getAdminList();
				boolean allMailsSent = true;
				for(int i = 0; i < adminList.size(); ++i){
					Users u = adminList.get(i);
					if(!MailService.sendEmail(u.getEmail(), PilotageConstants.MAILSERVER_CONSIGNE_QUESTION_SUBJECT, PilotageConstants.MAILSERVER_CREATE_CONSIGNE_QUESTION_TEXTE, para, null))
						allMailsSent = false;
				}
				if(!allMailsSent)
					error = getText("consigne.question.sendEmail.failed");
				
				info = getText("consigne.question.valide");
				status = 1;
				return OK;
			}
			catch (Exception e) {
				error = getText("error.message.generique") + " : " + e.getMessage();
				erreurLogger.error("Consignes : Sauvegarde et envoi d'une question - ", e);
				return ERROR;
			}
		}
	}

}
