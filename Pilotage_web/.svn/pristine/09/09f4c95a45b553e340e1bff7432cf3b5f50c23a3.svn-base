package pilotage.admin.actions.sousmodule;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONObject;

import pilotage.admin.metier.Profil_Droits;
import pilotage.admin.metier.Sous_Module;
import pilotage.database.admin.DroitsListeDatabaseService;
import pilotage.database.admin.ProfilDroitsDatabaseService;
import pilotage.database.admin.SousModuleDatabaseService;
import pilotage.framework.AbstractAdminAction;

public class DeleteDroitSousModuleAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = -3791397478614092684L;
	
	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		List<Profil_Droits> listProfilDroit = ProfilDroitsDatabaseService
				.getAll();
		boolean isUse = false;
		String reponse = "";
		
		for (Profil_Droits pdr : listProfilDroit) {
			if (id.equals(pdr.getId_droits().getId())) {
				isUse= true;
				break;
			}
		}
		Sous_Module m = null;
		boolean isAjout = false, isModif = false, isSuppr = false, isDetail = false;
		List<Sous_Module> sm = SousModuleDatabaseService.getAll();
			for(Sous_Module s : sm){
				if(s.getIdAjout() != null){
					if(id.equals(s.getIdAjout().getId())){
						isAjout = true;
						m = s;
						break;
					}
				}
				if(s.getIdModif() != null){
					if(id.equals(s.getIdModif().getId())){
						isModif = true;
						m = s;
						break;
					}
				}
				if(s.getIdSuppr() !=null){
					if(id.equals(s.getIdSuppr().getId())){
						isSuppr = true;
						m = s;
						break;
					}
				}
				if(s.getIdDetail() !=null){
					if(id.equals(s.getIdDetail().getId())){
						isDetail = true;
						m = s;
						break;
					}
				}
			}
		try {
			if(!isUse){
				if(isAjout){
					SousModuleDatabaseService.deleteDroitAjout(m.getId());}
				else if(isModif){
					SousModuleDatabaseService.deleteDroitModif(m.getId());}
				else if(isSuppr){
					SousModuleDatabaseService.deleteDroitSuppr(m.getId());}
				else if(isDetail){
					SousModuleDatabaseService.deleteDroitDetail(m.getId());}
				DroitsListeDatabaseService.delete(id);
				reponse = "1";
			} else reponse = "0";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try{
			response.setContentType("text/text;charset=utf-8");
			PrintWriter out = response.getWriter();
	        out.println(reponse);
	        out.flush();
	        out.close();
		}
        catch (Exception e) {
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("error", "Une erreur est survenue lors la suppression du droit : " + e.getMessage());
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		return null;
	}

}
