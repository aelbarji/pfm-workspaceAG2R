package pilotage.checklist.current;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import pilotage.database.checklist.ChecklistDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Current;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ChecklistRefresh extends AbstractAction {

	private static final long serialVersionUID = -822559184215295838L;

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected String executeMetier() {
		Date now = new Date();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String date = request.getParameter("date");
		String heure = request.getParameter("heure");
		//System.out.println("Date et heure de dernier rafraichissement : "+date+" "+heure);
		Date troisSeconde = DateService.strToDate("01/01/1970", "00:00:03");
		Date lastUpdate = null;
		if (date != null && heure != null) {
			lastUpdate = DateService.removeTime(DateService.strToDate(date, heure), troisSeconde);
		}
		
		JSONArray mainArray = new JSONArray();
		
		//date actuelle
		JSONObject jsonsDate = new JSONObject();
		jsonsDate.put("id", "date");
		jsonsDate.put("date", DateService.dateToStr(now, DateService.p1));
		mainArray.add(jsonsDate);
		
		//heure actuelle
		JSONObject jsonsHour = new JSONObject();
		jsonsHour.put("id", "hour");
		jsonsHour.put("hour", DateService.getTime(now, DateService.pt2));
		mainArray.add(jsonsHour);
		
		try {			
			if (lastUpdate != null) {
				List<Checklist_Current> listCurrent = ChecklistDatabaseService.getCurrentUpdate(lastUpdate);
				
				Map<Integer, String> couleurs = (Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR);
				Map<Integer, String> retard1 = (Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR_RETARD1);
				Map<Integer, String> retard2 = (Map<Integer, String>) session.get(PilotageConstants.CHECKLIST_COULEUR_RETARD2);
				String retard1String = session.get(PilotageConstants.CHECKLIST_RETARD_1).toString();
				String retard2String = session.get(PilotageConstants.CHECKLIST_RETARD_2).toString();
				Date tempsRetard1 = DateService.getTimeFromString(DateService.getTime(DateService.strToDate("01/01/1970", retard1String), null));
				Date tempsRetard2 = DateService.getTimeFromString(DateService.getTime(DateService.strToDate("01/01/1970", retard2String), null));
				//System.out.println(" Nombre d'éléments à modifier : "+listCurrent.size());
				completeBeforeDisplay(listCurrent, couleurs, retard1, retard2, tempsRetard1, tempsRetard2);
				//System.out.println(" Nombre d'éléments à envoyer : "+listUpdateTaches.size());
				
			
				for(Checklist_Current current : listCurrent){
					//System.out.println("Id current : "+current.getId().toString()+" status : "+current.getStatus().getId());
					JSONObject jsonsArray = new JSONObject();
					jsonsArray.put("id", current.getId().toString());
					jsonsArray.put("color", current.getColor());
					jsonsArray.put("statut", current.getStatus().getId());
					jsonsArray.put("consigne", current.getSousTache() == null ? "" : current.getSousTache().getIdConsigne().getConsigne());
					jsonsArray.put("user", current.getUser() == null? "" : current.getUser().getNom() + " " + current.getUser().getPrenom());
					jsonsArray.put("updateTime", current.getHeureDateString() == null ? "" : current.getHeureDateString() + "<br/>" + current.getHeureHeureString());
					//System.out.println("Ajout tache id : "+current.getId().toString());
					mainArray.add(jsonsArray);
				}

			}
			
		}
		catch (Exception e) {
			Map<String, String> hm = new HashMap<String, String>();
			System.out.println("Une erreur est survenue lors de la tentative de rafraichissement de la liste : " + e.getMessage());
			hm.put("error", "Une erreur est survenue lors de la tentative de rafraichissement de la liste : " + e.getMessage());
			e.printStackTrace();
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("lignes", mainArray);
		// chaque clé de notre map devient une clé dans l'objet JSON (utilisation de Json-lib)
		JSONObject json = JSONObject.fromObject(jsonObject);
		
		// façon d'envoyer l'objet JSON pour que Prototype puisse le récupérer
		//response.setHeader("X-JSON", json.toString());
		response.setContentType("application/json");
		PrintWriter out;
		try {
			out = response.getWriter();
	        out.println(json.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return OK;
	}

	public static void main(String[] args) {
		JSONArray mainArray = new JSONArray();

		JSONArray jsonsArray = new JSONArray();
		jsonsArray.add("a1");
		jsonsArray.add("a2");
		jsonsArray.add("a3");
		
		mainArray.add(jsonsArray);
		
		jsonsArray = new JSONArray();
		jsonsArray.add("b1");
		jsonsArray.add("b2");
		jsonsArray.add("b3");
		
		mainArray.add(jsonsArray);
		
		
		
		// chaque clé de notre map devient une clé dans l'objet JSON (utilisation de Json-lib)
		JSONArray json = JSONArray.fromObject(mainArray);
		
		System.out.println(json.toString());
	}
	
	/**
	 * Complément d'information sur les taches avant affichage
	 * Mise à jour de l'heure quand executer la tache
	 * Mise à jour des couleurs
	 * @param listeTaches
	 */
	public static void completeBeforeDisplay(List<Checklist_Current> listeTaches, Map<Integer, String> couleurs, Map<Integer, String> retard1, Map<Integer, String> retard2, Date tempsRetard1, Date tempsRetard2) {
		
		Date jourHeureActuel = new Date();

		for(Checklist_Current current : listeTaches){
			Date heureExecution = null;
			String couleur = null;
			if(current.getSousTache() != null)
				heureExecution = DateService.addTime(current.getIdHoraire().getHoraire(), current.getSousTache().getDecalageStamp());
			else
				heureExecution = current.getIdHoraire().getHoraire();
			
			//recupération de l'heure uniquement pour eviter les décalages de jours car en BDD current, jour = le jour reel de l'execution 
			heureExecution = DateService.getTimeFromString(DateService.getTime(heureExecution, DateService.pt1));
			
			current.setHeureExecution(heureExecution);

			Date jourHeureExecution = DateService.getDateHeure(current.getJour(), heureExecution);
						
			//mise à jour du statut et récupération des couleurs
			//si on est en retard pour prise en charge
			
			//System.out.println("Tache à traiter -> Id Current : "+current.getId()+" statut : "+current.getStatus().getId()+" horaire : "+jourHeureExecution.toString());
			//si on a une tache en retard
			if(current.getStatus().getId().equals(PilotageConstants.STATUT_RETARD)){
				//System.out.println("traitement tache en retard");
				if(jourHeureActuel.after(DateService.addTime(jourHeureExecution, tempsRetard2))){
					//System.out.println("Tache statut en retard changement de couleur");
					couleur = retard2.get(PilotageConstants.STATUT_RETARD);
				}
				else if(jourHeureActuel.after(DateService.addTime(jourHeureExecution, tempsRetard1))){
					couleur = retard1.get(PilotageConstants.STATUT_RETARD);
					//System.out.println("statut en retard couleur -> Id Current : "+current.getId()+" statut : "+current.getStatus().getId()+" horaire : "+jourHeureExecution.toString());
				}
				else{
					couleur = couleurs.get(PilotageConstants.STATUT_RETARD);
					//System.out.println("statut en retard couleur -> Id Current : "+current.getId()+" statut : "+current.getStatus().getId()+" horaire : "+jourHeureExecution.toString());
				}
			}
			else {
				//System.out.println("statut ok couleur -> Id Current : "+current.getId()+" statut : "+current.getStatus().getId()+" horaire : "+jourHeureExecution.toString());
				if(current.getHeure().after(DateService.addTime(jourHeureExecution, tempsRetard2))){
					couleur = retard2.get(current.getStatus().getId());
				}
				else if(current.getHeure().after(DateService.addTime(jourHeureExecution, tempsRetard1))){
					couleur = retard1.get(current.getStatus().getId());
				}
				else{
					couleur = couleurs.get(current.getStatus().getId());
				}
			}
			current.setColor(couleur == null ? "FFFFFF" : couleur);
			//System.out.println("Passage while fin");
		}
	}
	

}
