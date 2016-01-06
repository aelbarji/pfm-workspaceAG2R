package pilotage.bilan.alertes.disques;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pilotage.database.bilan.AlertesDisquesDatabaseService;
import pilotage.database.bilan.AlertesDisquesDestinatairesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Alertes_Disques;
import pilotage.metier.Alertes_Disques_Destinataires;
import pilotage.service.date.DateService;
import pilotage.service.mail.MailService;

public class EnvoieAlertesDisquesAction extends AbstractAction {

	private static final long serialVersionUID = -3160643646068731252L;
	private Date selectedDate;
	private String currentDate;
	
	/**
	 * @return the currentDate
	 */
	public String getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	/**
	 * @return the selectedDate
	 */
	public Date getSelectedDate() {
		return selectedDate;
	}

	/**
	 * @param selectedDate the selectedDate to set
	 */
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			List<Alertes_Disques> listAlertes = AlertesDisquesDatabaseService.getByDate(selectedDate);
			List<Alertes_Disques_Destinataires> listDestina = AlertesDisquesDestinatairesDatabaseService.getAll();
			String body = getBody(listAlertes);
			boolean checkSend = true;
			for(Alertes_Disques_Destinataires add : listDestina){
				checkSend = checkSend && MailService.sendEmail(add.getMail(), "Alertes Disques du "+ DateService.dateToStr(selectedDate, DateService.p1), body , null, null);
			}
			if(!checkSend){
				erreurLogger.error("Envoi des alertes disques - Problème d'envoi de mail");
				error = MessageFormat.format(getText("alertes.disques.envoyer.failed"), new Object[]{});
				return ERROR;
			}
	
			info = MessageFormat.format(getText("alertes.disques.envoyer.valide"), DateService.dateToStr(selectedDate, DateService.p1));
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Envoi des alertes disques - ", e);
			return ERROR;
		}
	}
	
	/**
	 * 
	 * @param listAlertes
	 * @return
	 */
	private String getBody(List<Alertes_Disques> listAlertes){
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head></head><body>");
		sb.append("<table width='100%' border='1' rules='all' cellpadding='0' cellspacing='0'>");
		sb.append("<col width='10%'>");
		sb.append("<col width='20%'>");
		sb.append("<col width='20%'>");
		sb.append("<col width='20%'>");
		sb.append("<col width='10%'>");
		sb.append("<col width='20%'>");
		sb.append("<tr>");
		sb.append("<th bgcolor='grey' color='white' align='center' valign='middle' size='2' height='25px' bordercolor='black'>Date</th>");
		sb.append("<th bgcolor='grey' color='white' align='center' valign='middle' size='2' height='25px' bordercolor='black'>Système</th>");
		sb.append("<th bgcolor='grey' color='white' align='center' valign='middle' size='2' height='25px' bordercolor='black'>FS/Table Space</th>");
		sb.append("<th bgcolor='grey' color='white' align='center' valign='middle' size='2' height='25px' bordercolor='black'>Type</th>");
		sb.append("<th bgcolor='grey' color='white' align='center' valign='middle' size='2' height='25px' bordercolor='black'>Alerte</th>");
		sb.append("<th bgcolor='grey' color='white' align='center' valign='middle' size='2' height='25px' bordercolor='black'>Seuil</th>");
		sb.append("</tr>");
		
		if(listAlertes == null || listAlertes.size() == 0){
			sb.append("<tr><td colspan='7' align='center'>Aucun alerte disque trouvé</td></tr>");
			sb.append("</table>");
			return sb.toString();
		}else{
			for(Alertes_Disques ad : listAlertes){
				sb.append("<tr>");
				sb.append("<td align='center'>");
				sb.append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ad.getDate()));
				sb.append("</td><td align='center'>");
				sb.append(ad.getSysteme().getNom());
				sb.append("</td><td align='center'>");
				sb.append(ad.getFs());
				sb.append("</td><td align='center'>");
				sb.append(ad.getType().getType());
				sb.append("</td><td align='center'>");
				sb.append(ad.getAlerte());
				sb.append("</td><td align='center'>");
				sb.append(ad.getSeuil());
				sb.append("</td></tr>");
			}
		}
		sb.append("</table></body></html>");
		return sb.toString();
	}

}
