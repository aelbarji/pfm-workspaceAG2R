package pilotage.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import pilotage.admin.metier.Checklist_color;
import pilotage.admin.metier.Parametre;
import pilotage.admin.metier.Titre_page;
import pilotage.database.admin.ChecklistColorDatabaseService;
import pilotage.database.admin.TitreDatabaseService;
import pilotage.database.login.LoginDatabaseService;
import pilotage.service.constants.PilotageConstants;

import com.opensymphony.xwork2.ActionSupport;

public class EnterAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	protected Map session;

	@SuppressWarnings("rawtypes")
	public Map getSession() {
		return session;
	}

	@SuppressWarnings("rawtypes")
	public void setSession(Map session) {
		this.session = session;
	}
	
	
	@Override
	public String execute() throws Exception {
		if(!Boolean.TRUE.equals((Boolean)session.get(PilotageConstants.PARAM_IN_SESSION))){
			EnterAction.putParametersInSession(session);
		}
		return "ok";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void putParametersInSession(Map session){
		//parametres
		List<Parametre> parametre = LoginDatabaseService.getParameterValue();
		for(Parametre p : parametre){
			session.put(p.getLibelle(), p.getValeur());
		}
		
		//titres de page
		List<Titre_page> titres = TitreDatabaseService.getAll();
		Map<String, String> titreMap = new HashMap<String, String>();
		for(Titre_page titre : titres){
			titreMap.put(titre.getId(), titre.getLibelle());
		}
		session.put(PilotageConstants.TITLE_IN_SESSION, titreMap);
		
		
		
		//couleurs de la checklist
		try{
			List<Checklist_color> listColors = ChecklistColorDatabaseService.getAll();
			Map<Integer, String> couleurs = new HashMap<Integer, String>();
			Map<Integer, String> retard1 = new HashMap<Integer, String>();
			Map<Integer, String> retard2 = new HashMap<Integer, String>();
			
			for(Checklist_color color : listColors){
				Integer id = color.getChecklist_status();
				couleurs.put(id, color.getCouleur());
				retard1.put(id, color.getRetard1());
				retard2.put(id, color.getRetard2());
			}
			session.put(PilotageConstants.CHECKLIST_COULEUR, couleurs);
			session.put(PilotageConstants.CHECKLIST_COULEUR_RETARD1, retard1);
			session.put(PilotageConstants.CHECKLIST_COULEUR_RETARD2, retard2);
		}
		catch (Exception e) {
		}
		
		
		session.put(PilotageConstants.PARAM_IN_SESSION, true);
	}
	
	//methode permettant de savoir si l'action qui vient d'être appelée est un export consecutif à un export
	//si c'est le cas on ne supprime pas les elements en session nécessaires à l'export
	//sinon on les supprime de la session
	@SuppressWarnings({ "rawtypes" })
	public static void gereSessionExportEnCours(Map session,String nameAction){
		
	    if(!(nameAction.equals(PilotageConstants.NAME_CLASS_BEFORE_EXPORT_1)||nameAction.equals(PilotageConstants.NAME_CLASS_BEFORE_EXPORT_2)||nameAction.equals(PilotageConstants.NAME_CLASS_BEFORE_EXPORT_3))){
				
				//variables planning semaine
				if(session.get(PilotageConstants.PLANNING_LISTE_JOUR_SEMAINE)!=null)
					session.remove(PilotageConstants.PLANNING_LISTE_JOUR_SEMAINE);
				if(session.get(PilotageConstants.PLANNING_LISTE_SEMAINE_PILOTE)!=null)
					session.remove(PilotageConstants.PLANNING_LISTE_SEMAINE_PILOTE);
				if(session.get(PilotageConstants.PLANNING_NUM_SEMAINE)!=null)
					session.remove(PilotageConstants.PLANNING_NUM_SEMAINE);
				if(session.get(PilotageConstants.PLANNING_ANNEE)!=null)
					session.remove(PilotageConstants.PLANNING_ANNEE);
				if(session.get(PilotageConstants.PLANNING_SELECTED_NUM_SEMAINE)!=null)
					session.remove(PilotageConstants.PLANNING_SELECTED_NUM_SEMAINE);
				if(session.get(PilotageConstants.PLANNING_SELECTED_ANNEE)!=null)
					session.remove(PilotageConstants.PLANNING_SELECTED_ANNEE);
				
				//variables planning mois pilote
				if(session.get(PilotageConstants.PLANNING_LISTE_MOIS_PILOTE)!=null)
					session.remove(PilotageConstants.PLANNING_LISTE_MOIS_PILOTE);
				if(session.get(PilotageConstants.PLANNING_LISTE_USER)!=null)
					session.remove(PilotageConstants.PLANNING_LISTE_USER);
				if(session.get(PilotageConstants.PLANNING_SELECT_DATE)!=null)
					session.remove(PilotageConstants.PLANNING_SELECT_DATE);
				if(session.get(PilotageConstants.PLANNING_SELECT_COULEUR)!=null)
					session.remove(PilotageConstants.PLANNING_SELECT_COULEUR);
				
				
				
				//variables planning mois
				if(session.get(PilotageConstants.PLANNING_SELECT_DATE)!=null)
					session.remove(PilotageConstants.PLANNING_SELECT_DATE);
				if(session.get(PilotageConstants.PLANNING_EQUIPE)!=null)
					session.remove(PilotageConstants.PLANNING_EQUIPE);
				if(session.get(PilotageConstants.PLANNING_LISTE_JOUR)!=null)
					session.remove(PilotageConstants.PLANNING_LISTE_JOUR);
				if(session.get(PilotageConstants.PLANNING_LISTE_EQUIPE)!=null)
					session.remove(PilotageConstants.PLANNING_LISTE_EQUIPE);
				if(session.get(PilotageConstants.PLANNING_COULEUR)!=null)
					session.remove(PilotageConstants.PLANNING_COULEUR);
				if(session.get(PilotageConstants.PLANNING_LISTE_MOIS)!=null)
					session.remove(PilotageConstants.PLANNING_LISTE_MOIS);
				
				
				
				
	    }

	}
	
}
