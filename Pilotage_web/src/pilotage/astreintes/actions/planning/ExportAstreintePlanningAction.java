package pilotage.astreintes.actions.planning;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pilotage.database.astreintes.AstreintePlanningDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Planning;
import pilotage.service.date.DateService;
import pilotage.service.excel.ExportExcel;

public class ExportAstreintePlanningAction extends AbstractAction{

	private static final long serialVersionUID = -3028091544153552980L;
	private String titre;
	private InputStream excelStream;

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		
		try {
			Date firstMonday = DateService.getNextMonday(1);  
			Date secondMonday = DateService.getNextMonday(2);
			Date thirdMonday = DateService.getNextMonday(3);  
			
			//récupération des astreintes de la 1ère semaine qui suit
			List<Astreinte_Planning> firstWeekPlanning = AstreintePlanningDatabaseService.getWeekPlanning(firstMonday, secondMonday);
			Map<Astreinte_Domaine, List<Astreinte_Planning>> firstWeekMap = groupAPlanningByDomaine(firstWeekPlanning);
			
			//récupération des astreintes de la 2ème semaine qui suit
			List<Astreinte_Planning> sencondWeekPlanning = AstreintePlanningDatabaseService.getWeekPlanning(secondMonday, thirdMonday);
			Map<Astreinte_Domaine, List<Astreinte_Planning>> sencondWeekMap = groupAPlanningByDomaine(sencondWeekPlanning);
			
			//constitution du tableau excel
			HSSFWorkbook lExcelDoc = ExportExcel.exportAstreintePlanning(firstWeekMap,sencondWeekMap);
			titre = getExcelTitle(firstMonday, secondMonday);
			
			//mise dans un flux
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			lExcelDoc.write(baos);
			excelStream = new ByteArrayInputStream(baos.toByteArray());

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Export des astreintes - " , e);
			return ERROR;
		}
	}
	
	/**
	 * Construction du titre de l'excel
	 * @param secondMonday 
	 * @param firstMonday 
	 * @return
	 */
	public static String getExcelTitle(Date firstMonday, Date secondMonday){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Planning_des_Astreintes_Semaine_");
		buffer.append(DateService.getWeekOfYear(firstMonday));
		buffer.append("-");
		buffer.append(DateService.getWeekOfYear(secondMonday));
		buffer.append(".xls");
		return buffer.toString();
	}
	
	/**
	 * Groupement des plannings par domaine
	 * @param aPlannings
	 * @return
	 */
	private Map<Astreinte_Domaine, List<Astreinte_Planning>> groupAPlanningByDomaine(List<Astreinte_Planning> aPlannings){
		Map<Astreinte_Domaine, List<Astreinte_Planning>> map = new LinkedHashMap<Astreinte_Domaine, List<Astreinte_Planning>>();
		
		for(Astreinte_Planning ap : aPlannings){
			List<Astreinte_Planning> listParDomaine = map.get(ap.getDomaine());
			if(listParDomaine == null)
				listParDomaine = new ArrayList<Astreinte_Planning>();
			
			listParDomaine.add(ap);
			map.put(ap.getDomaine(), listParDomaine);
		}
		
		return map;
	}

}
