package pilotage.planning.planning;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.excel.ExportExcel;

public class ExportPlanningMoisAction extends AbstractAction{

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

	@SuppressWarnings("unchecked")
	@Override
	protected String executeMetier() {
		
		try {
			HSSFWorkbook lExcelDoc = ExportExcel.exportPlanningMois((Date)session.get(PilotageConstants.PLANNING_SELECT_DATE), (Map<Integer,Integer>)session.get(PilotageConstants.PLANNING_LISTE_JOUR),
					(Planning_Nom_Equipe)session.get("equipe"), (Map<Integer, String>)session.get(PilotageConstants.PLANNING_COULEUR), 
					(Map<Integer,String>) session.get(PilotageConstants.PLANNING_SELECT_COULEUR), (Map<Integer,Integer>)session.get("mapJourMois"));
			titre = getExcelTitle();
			
			//mise dans un flux
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			lExcelDoc.write(baos);
			excelStream = new ByteArrayInputStream(baos.toByteArray());
	        
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Export du planning mensuel par équipe - " , e);
			return ERROR;
		}
	}
	
	/**
	 * Construction du titre de l'excel
	 * @return
	 */
	public String getExcelTitle(){
		StringBuffer buffer = new StringBuffer();
		String title="Planning mensuel des équipes : ";
		
		Date date=(Date)session.get(PilotageConstants.PLANNING_SELECT_DATE);
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setCalendar(c);
		sdf.applyPattern("MMMM"); 
		String libelleMois = sdf.format(c.getTime());
		String DateSelect = libelleMois.trim() + "_" + String.valueOf(c.get(Calendar.YEAR)).trim();
		
		buffer.append(title.trim());
		buffer.append(DateSelect.trim());
		buffer.append(".xls");
		return buffer.toString();
	}

}
