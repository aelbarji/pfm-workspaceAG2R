package pilotage.admin.actions.checklist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.admin.metier.Checklist_color;
import pilotage.database.admin.ChecklistColorDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class ModifyChecklistColorAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 8833737600124146581L;
	
	private static final String COULEUR = "couleur";
	private static final String PREMIER_RETARD = "premierRetard";
	private static final String SECOND_RETARD = "secondRetard";
	
	private List<Checklist_color> listChecklistColor;

	public List<Checklist_color> getListChecklistColor() {
		return listChecklistColor;
	}


	public void setListChecklistColor(List<Checklist_color> listChecklistColor) {
		this.listChecklistColor = listChecklistColor;
	}
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		Map<Integer, List<String>> colorsToAdd = new HashMap<Integer, List<String>>();
		try{
			int i = 0;
			HttpServletRequest request = ServletActionContext.getRequest();
			
			while(request.getParameter("id" + i) != null){
				List<String> colorInfos = new ArrayList<String>();
				colorInfos.add(request.getParameter(COULEUR + i));
				colorInfos.add(request.getParameter(PREMIER_RETARD + i));
				colorInfos.add(request.getParameter(SECOND_RETARD + i));
				
				colorsToAdd.put(Integer.parseInt(request.getParameter("id" + i)), colorInfos);
				
				++i;
			}
			
			ChecklistColorDatabaseService.update(colorsToAdd);
			
			info = getText("checklist.couleur.valide");
			listChecklistColor = ChecklistColorDatabaseService.getAll();
			
			return OK;
		}
		catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification des couleurs de checklist : ", e);
			
			listChecklistColor = new ArrayList<Checklist_color>();
			for(Entry<Integer, List<String>> nextColor : colorsToAdd.entrySet()){
				Checklist_color color = new Checklist_color();
				color.setId(nextColor.getKey());
				color.setCouleur(nextColor.getValue().get(0));
				color.setRetard1(nextColor.getValue().get(1));
				color.setRetard2(nextColor.getValue().get(2));
				
				listChecklistColor.add(color);
			}
			
			return ERROR;
		}
	}
}
