/**
 * pilotage.consignes
 * 7 juil. 2011
 */
package pilotage.consignes;

import pilotage.database.consigne.ConsignePiloteValidationDatabaseService;
import pilotage.database.users.management.UsersDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Consignes_Pilotes_Validation;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.mail.MailService;

/**
 * @author xxu
 * 
 */
public class ResponseQuestionAction extends AbstractActionConsigne {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8639555348522749286L;
	private int status;
	private int piloteID;
	private int questionID;
	private String question;
	private String response;
	private String piloteName;

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
	 * @return the piloteID
	 */
	public int getPiloteID() {
		return piloteID;
	}

	/**
	 * @param piloteID
	 *            the piloteID to set
	 */
	public void setPiloteID(int piloteID) {
		this.piloteID = piloteID;
	}

	/**
	 * @return the questionID
	 */
	public int getQuestionID() {
		return questionID;
	}

	/**
	 * @param questionID
	 *            the questionID to set
	 */
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
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

	/**
	 * @return the piloteName
	 */
	public String getPiloteName() {
		return piloteName;
	}

	/**
	 * @param piloteName
	 *            the piloteName to set
	 */
	public void setPiloteName(String piloteName) {
		this.piloteName = piloteName;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		//récupération du pilote et de la question
		Users pilote = UsersDatabaseService.get(piloteID);
		Consignes_Pilotes_Validation cpv = ConsignePiloteValidationDatabaseService.get(questionID);
		
		if (response == null || response.equals("")) {
			piloteName = pilote.getPrenom() + " " + pilote.getNom();
			question = cpv.getQuestion();
			status = 0;
			return OK;
		}

		String[] para = new String[3];
		para[0] = cpv.getIdConsigne().getText();
		para[1] = cpv.getQuestion().replaceAll("\n", "<br/>");
		para[2] = response.replaceAll("\n", "<br/>");

		if(!MailService.sendEmail(pilote.getEmail(),PilotageConstants.MAILSERVER_CONSIGNE_RESPONSE_SUBJECT, PilotageConstants.MAILSERVER_RESPONSE_CONSIGNE_QUESTION_TEXTE, para, null)){
			error = getText("consigne.reponse.sendEmail.failed");
			status = 0;
		}
		else{
			info = getText("consigne.reponse.valide");
			status = 1;
		}
		
		return OK;
	}
}
