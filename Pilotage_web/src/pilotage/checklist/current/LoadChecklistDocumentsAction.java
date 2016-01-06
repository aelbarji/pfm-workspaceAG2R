package pilotage.checklist.current;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import pilotage.database.checklist.ChecklistBaseSousTacheDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Checklist_Consigne_Documents;

public class LoadChecklistDocumentsAction extends AbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5075513893609010033L;
	/**
	 * 
	 */

	private int idConsigne;
	
	public int getIdConsigne() {
		return idConsigne;
	}


	public void setIdConsigne(int idConsigne) {
		this.idConsigne = idConsigne;
	}


	@Override
	protected boolean validateMetier() {
		return true;
	}

	
	@Override
	protected String executeMetier() {
		HttpServletResponse response = ServletActionContext.getResponse();

		try {
				JSONArray mainArray = new JSONArray();
				// Récuperation des consignes de la base
				List<Checklist_Consigne_Documents> listeDocuments = (List<Checklist_Consigne_Documents>) ChecklistBaseSousTacheDatabaseService.getAllDocumentsForSousTache(idConsigne);
				
				// JSON de réponse
				for(Checklist_Consigne_Documents document :listeDocuments){ 
					JSONObject json = new JSONObject();
					json.put("id", document.getId());
					json.put("textDoc", document.getDocument());
					mainArray.add(json);
				}
			
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("doc", mainArray);
			// chaque clé de notre map devient une clé dans l'objet JSON
			// (utilisation de Json-lib)
			JSONObject json = JSONObject.fromObject(jsonObject);

			// façon d'envoyer l'objet JSON pour que Prototype puisse le
			// récupérer
			// response.setHeader("X-JSON", json.toString());
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("error",
					"Une erreur est survenue lors de la tentative de rafraichissement de la liste : "
							+ e.getMessage());
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		return null;

	}
}
