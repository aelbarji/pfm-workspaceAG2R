package pilotage.checklist.current;

import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.database.checklist.ChecklistStatutDatabaseService;
import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Current;
import pilotage.metier.Checklist_Status;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ModifyChecklistAction extends AbstractAction {

	private static final long serialVersionUID = 3952808607867940851L;

	private int selectRow;
	private int selectedStatut;
	private Date selectedDate;

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	public int getSelectedStatut() {
		return selectedStatut;
	}

	public void setSelectedStatut(int selectedStatut) {
		this.selectedStatut = selectedStatut;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String executeMetier() {
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try{
			JSONArray mainArray = new JSONArray();
			
			if(selectedDate.after(DateService.getTodayWithoutHour())){
				List<Checklist_Current> listeComplete = (List<Checklist_Current>) session.get(PilotageConstants.CHECKLIST_FUTUR);
				Checklist_Current current = listeComplete.get(selectRow);
				Users user = ((Users)session.get(PilotageConstants.USER_LOGGED));
				
				//ajouter/supprimer en base checklist_annule
				if(selectedStatut == PilotageConstants.STATUT_ANNULE)
					ChecklistDatabaseService.annuleFutureTache(current.getSousTache().getId(), selectedDate, current.getIdHoraire().getId(), user.getId());
				else if(selectedStatut == PilotageConstants.STATUT_A_VENIR)
					ChecklistDatabaseService.deteteAnnuleFutureTache(current.getSousTache().getId(), selectedDate, current.getIdHoraire().getId());
				
				Checklist_Status statut = ChecklistStatutDatabaseService.get(selectedStatut);
				current.setStatus(statut);
				
				//JSON de réponse
				JSONObject json = new JSONObject();
				json.put("id", "statut");
				json.put("value", "ok");
				mainArray.add(json);
				
				json = new JSONObject();
				json.put("id", "id");
				json.put("value", selectRow);
				mainArray.add(json);
				
				json = new JSONObject();
				json.put("id", "color");
				json.put("value", ((Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR)).get(selectedStatut));
				mainArray.add(json);
			}
			else{
				Integer userID = ((Users)session.get(PilotageConstants.USER_LOGGED)).getId();
				ChecklistDatabaseService.modifyStatut(selectRow, selectedStatut, userID);
				
				HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.checklist.modification.statut"), new Object[]{selectRow}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_CHECKLIST);

				if(selectedStatut == PilotageConstants.STATUT_KO){
					Checklist_Current current = ChecklistDatabaseService.get(selectRow);
					Date now = new Date();
					String titreTache = current.getTache().getTache();
					if(current.getSousTache() != null)
						titreTache += " : " + current.getSousTache().getNomSousTache(); 
					
					//JSON de réponse
					JSONObject json = new JSONObject();
					json.put("id", "statut");
					json.put("value", "incident");
					mainArray.add(json);
					
					json = new JSONObject();
					json.put("id", "enviroSelected");
					json.put("value", current.getTache().getEnvironnement().getId());
					mainArray.add(json);
					
					json = new JSONObject();
					json.put("id", "dateDebut");
					json.put("value", DateService.dateToStr(now, DateService.p1));
					mainArray.add(json);
					
					json = new JSONObject();
					json.put("id", "heureDebut");
					json.put("value", DateService.getTime(now, null));
					mainArray.add(json);
					
					json = new JSONObject();
					json.put("id", "observation");
					json.put("value", MessageFormat.format(getText("checklist.ko.incident.obs"), titreTache));
					mainArray.add(json);
				}
				else{
					//JSON de réponse
					JSONObject json = new JSONObject();
					json.put("id", "statut");
					json.put("value", "ok");
					mainArray.add(json);
					
					json = new JSONObject();
					json.put("id", "id");
					json.put("value", selectRow);
					mainArray.add(json);
					
					json = new JSONObject();
					json.put("id", "color");
					json.put("value", ((Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR)).get(selectedStatut));
					mainArray.add(json);
				}
			}
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("modif", mainArray);
			// chaque clé de notre map devient une clé dans l'objet JSON (utilisation de Json-lib)
			JSONObject json = JSONObject.fromObject(jsonObject);
			
			// façon d'envoyer l'objet JSON pour que Prototype puisse le récupérer
			//response.setHeader("X-JSON", json.toString());
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
	        out.println(json.toString());
	        out.flush();
	        out.close();
		}
		catch (Exception e) {
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("error", "Une erreur est survenue lors de la tentative de rafraichissement de la liste : " + e.getMessage());
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		return null;

	}
}
