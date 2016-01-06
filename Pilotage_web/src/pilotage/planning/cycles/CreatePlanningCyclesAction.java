package pilotage.planning.cycles;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.historique.HistoriqueDatabaseService;
import pilotage.database.planning.PlanningCyclesDatabaseService;
import pilotage.database.planning.PlanningVacationsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;


public class CreatePlanningCyclesAction extends AbstractAction {
	
	private static final long serialVersionUID = -1299589526541605339L;
	
	public static final String NumSemaine = "numSemaine";
	public static final String LUNDI    = "lundi";
	public static final String MARDI    = "mardi";
	public static final String MERCREDI = "mercredi";
	public static final String JEUDI    = "jeudi";
	public static final String VENDREDI = "vendredi";
	public static final String SAMEDI   = "samedi";
	public static final String DIMANCHE = "dimanche";
	
	private String nomCycle;
	private List<Map<String, String>> listSemaine;
	private List<Planning_Vacation> listVacation;

	public String getNomCycle() {
		return nomCycle;
	}

	public void setNomCycle(String nomCycle) {
		this.nomCycle = nomCycle;
	}

	public List<Map<String, String>> getListSemaine() {
		return listSemaine;
	}

	public void setListSemaine(List<Map<String, String>> listSemaine) {
		this.listSemaine = listSemaine;
	}

	public List<Planning_Vacation> getListVacation() {
		return listVacation;
	}

	public void setListVacation(List<Planning_Vacation> listVacation) {
		this.listVacation = listVacation;
	}

	@Override
	protected boolean validateMetier() {
		try{
			if(PlanningCyclesDatabaseService.exists(null, nomCycle)){
				error = MessageFormat.format(getText("planning.cycle.creation.existe.deja"), nomCycle);
				listVacation = PlanningVacationsDatabaseService.getAll();
				listSemaine = new ArrayList<Map<String,String>>();
				getListSemaine(ServletActionContext.getRequest(), listSemaine);
				return false;
			}
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un cycle de planning - ", e);
			listSemaine = new ArrayList<Map<String,String>>();
			getListSemaine(ServletActionContext.getRequest(), listSemaine);
			return false;
		}
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			
			listSemaine = new ArrayList<Map<String,String>>();
			getListSemaine(request, listSemaine);
			
			Integer id = PlanningCyclesDatabaseService.create(nomCycle, listSemaine);
			info = MessageFormat.format(getText("planning.cycle.creation.valide"), new Object[]{nomCycle});
			HistoriqueDatabaseService.create(null, MessageFormat.format(getText("historique.planning.cycle.creation"), new Object[]{nomCycle,id}), (Users)session.get(PilotageConstants.USER_LOGGED), PilotageConstants.HISTORIQUE_MODULE_PLANNING);
			nomCycle = null;
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création d'un cycle de planning - ", e);
			listVacation = PlanningVacationsDatabaseService.getAll();
			return ERROR;
		}
	}
	
	public static void getListSemaine(HttpServletRequest request, List<Map<String, String>> listSemaine) {
		int i = 1;
		while(request.getParameter("semaine" + i) != null){
			Map<String, String> se = new HashMap<String, String>();
			se.put(NumSemaine, request.getParameter("semaine"+i));
			se.put(LUNDI, request.getParameter("lundi" + i));
			se.put(MARDI, request.getParameter("mardi" + i));
			se.put(MERCREDI, request.getParameter("mercredi" + i));
			se.put(JEUDI, request.getParameter("jeudi" + i));
			se.put(VENDREDI, request.getParameter("vendredi" + i));
			se.put(SAMEDI, request.getParameter("samedi" + i));
			se.put(DIMANCHE, request.getParameter("dimanche" + i));
			listSemaine.add(se);
			++i;
		}
	}
}