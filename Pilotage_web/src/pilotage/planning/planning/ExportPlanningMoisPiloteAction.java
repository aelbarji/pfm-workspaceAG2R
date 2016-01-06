package pilotage.planning.planning;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.excel.ExportExcel;

public class ExportPlanningMoisPiloteAction extends AbstractAction{

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
			//constitution du tableau excel
			HSSFWorkbook lExcelDoc = ExportExcel.exportPlanningMoisPilote((Map<Integer, Map<Integer, Map<String, String>>>)session.get(PilotageConstants.PLANNING_LISTE_MOIS_PILOTE),(List<Users>)session.get(PilotageConstants.PLANNING_LISTE_USER),(Date)session.get(PilotageConstants.PLANNING_SELECT_DATE),(List<Planning_Vacation>) session.get(PilotageConstants.PLANNING_SELECT_COULEUR));
			titre = getExcelTitle();
			
			
			//mise dans un flux
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			lExcelDoc.write(baos);
			excelStream = new ByteArrayInputStream(baos.toByteArray());

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Export Planning mensuelle des pilotes - " , e);
			return ERROR;
		}
	}
	
	/**
	 * Construction du titre de l'excel 
	 * @return
	 */
	public String getExcelTitle(){
		StringBuffer buffer = new StringBuffer();
		
		
		String title="Planning mensuelle des pilotes : ";
		Date date=(Date)session.get(PilotageConstants.PLANNING_SELECT_DATE);
		
		
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		//String DateSelect = String.valueOf(c.get(Calendar.DATE)); 
		//DateSelect += "/" + String.valueOf(c.get(Calendar.MONTH)+1); 
		//DateSelect += "/" + String.valueOf(c.get(Calendar.YEAR)); 
		
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setCalendar(c);
		sdf.applyPattern("MMMM"); 
		String libelleMois=sdf.format(c.getTime());
		String DateSelect= libelleMois.trim() + "_" + String.valueOf(c.get(Calendar.YEAR)).trim();
		
		buffer.append(title.trim());
		
		buffer.append(DateSelect.trim());
		
		buffer.append(".xls");
		return buffer.toString();
	}
	
	

}
