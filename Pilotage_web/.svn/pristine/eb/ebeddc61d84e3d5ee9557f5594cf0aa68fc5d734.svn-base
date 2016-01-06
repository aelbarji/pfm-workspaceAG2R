package pilotage.planning.planning;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pilotage.framework.AbstractAction;
import pilotage.metier.Planning_Vacation;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.excel.ExportExcel;

public class ExportPlanningSemaineAction extends AbstractAction{

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
			//récupération des deux listes mapJour et mapSemaine mises en session dans ShowPlanningSemaineAction
			
			//constitution du tableau excel
			HSSFWorkbook lExcelDoc = ExportExcel.exportPlanningSemaine((Map<Integer, String>)session.get(PilotageConstants.PLANNING_LISTE_JOUR_SEMAINE),(List<Map<String, String>>)session.get(PilotageConstants.PLANNING_LISTE_SEMAINE_PILOTE),	(Map<Integer, String>) session.get(PilotageConstants.PLANNING_NUM_SEMAINE),(Map<String, String>) session.get(PilotageConstants.PLANNING_ANNEE),(String)session.get(PilotageConstants.PLANNING_SELECTED_NUM_SEMAINE),(String)session.get(PilotageConstants.PLANNING_SELECTED_ANNEE),(List<Planning_Vacation>) session.get(PilotageConstants.PLANNING_SELECT_COULEUR));
			titre = getExcelTitle();
			
			//mise dans un flux
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			lExcelDoc.write(baos);
			excelStream = new ByteArrayInputStream(baos.toByteArray());
			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Export du planning de la semaine - " , e);
			return ERROR;
		}
	}
	
	/**
	 * Construction du titre de l'excel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  String getExcelTitle(){
		StringBuffer buffer = new StringBuffer();
				
		//valeur de l'année et de la semaine
		int selectedAnnee;
		int selectedSemaine;
		String libelleAnnee="";
		String libelleSemaine="";
		String title="Planning hébdomadaire des pilotes";
		selectedAnnee=Integer.parseInt((String)session.get(PilotageConstants.PLANNING_SELECTED_ANNEE));
		libelleAnnee=((Map<String, String>)session.get(PilotageConstants.PLANNING_ANNEE)).get(String.valueOf(selectedAnnee));
		selectedSemaine=Integer.parseInt((String)session.get(PilotageConstants.PLANNING_SELECTED_NUM_SEMAINE));
		libelleSemaine=((Map<Integer, String>)session.get(PilotageConstants.PLANNING_NUM_SEMAINE)).get(selectedSemaine);
				
		
		buffer.append(title.trim());
		buffer.append('-');
		buffer.append(libelleAnnee.trim());
		buffer.append('-');
		buffer.append(libelleSemaine.trim());
		
		buffer.append(".xls");
		return buffer.toString();
	}

}
