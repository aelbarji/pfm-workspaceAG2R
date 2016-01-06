package pilotage.database.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;

import pilotage.admin.metier.Checklist_color;
import pilotage.admin.session.ParametreSession;
import pilotage.database.checklist.ChecklistStatutDatabaseService;
import pilotage.metier.Checklist_Status;


public class ChecklistColorDatabaseService {
	
	/**
	 * SELECT de tous les checklist_color
	 * INSERT des checklist_color inexistants pour les nouveaux statuts
	 * DELETE des checklist_color dont le statut est supprimé
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Checklist_color> getAll() throws Exception{
		List<Checklist_Status> listStatuts = ChecklistStatutDatabaseService.getAll();
		Session session = ParametreSession.getCurrentSession();
		
		try{
			//récupération des couleurs en base
			List<Checklist_color> listColorsEnBase = session.createCriteria(Checklist_color.class).list();
			
			//détermination des suppressions et des insertions à faire
			List<Checklist_color> listColorsToAdd = new ArrayList<Checklist_color>();
			List<Checklist_color> listColors = new ArrayList<Checklist_color>();
			for(Checklist_Status statut : listStatuts){
				Checklist_color color = null;
				for(Checklist_color colorEnBase : listColorsEnBase){
					if(statut.getId().equals(colorEnBase.getChecklist_status())){
						colorEnBase.setStatut(statut);
						color = colorEnBase;
						break;
					}
				}
				if(color != null){
					listColorsEnBase.remove(color);
					listColors.add(color);
				}
				else{
					Checklist_color colorToAdd = new Checklist_color();
					colorToAdd.setChecklist_status(statut.getId());
					colorToAdd.setCouleur("FFFFFF");
					
					listColorsToAdd.add(colorToAdd);
					listColors.add(colorToAdd);
				}
			}
			
			//suppression et insertion
			for(Checklist_color colorToDelete : listColorsEnBase){
				session.delete(colorToDelete);
			}
			for(Checklist_color colorToAdd : listColorsToAdd){
				session.save(colorToAdd);
			}
			session.getTransaction().commit();
			
			return listColors;
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}

	/**
	 * UPDATE des couleurs
	 * @param colorsToAdd
	 * @throws Exception 
	 */
	public static void update(Map<Integer, List<String>> listColorsToAdd) throws Exception {
		Session session = ParametreSession.getCurrentSession();
		try{
			List<Checklist_color> listColorsToUpdate = new ArrayList<Checklist_color>();
			for(Entry<Integer, List<String>> colorToAdd : listColorsToAdd.entrySet()){
				Checklist_color color = (Checklist_color)session.load(Checklist_color.class, colorToAdd.getKey());
				List<String> infos = colorToAdd.getValue();
				
				if(infos.get(0).trim().equals(""))
					color.setCouleur("FFFFFF");
				else
					color.setCouleur(infos.get(0).trim());
				
				if(infos.get(1).trim().equals(""))
					color.setRetard1(null);
				else
					color.setRetard1(infos.get(1).trim());
				
				if(infos.get(2).trim().equals(""))
					color.setRetard2(null);
				else
					color.setRetard2(infos.get(2).trim());
				
				listColorsToUpdate.add(color);
			}
			
			for(Checklist_color color : listColorsToUpdate){
				session.update(color);
			}
			
			session.getTransaction().commit();
		}
		catch (Exception e) {
			ParametreSession.rollbackTransaction(session);
			throw e;
		}
	}
	
}
