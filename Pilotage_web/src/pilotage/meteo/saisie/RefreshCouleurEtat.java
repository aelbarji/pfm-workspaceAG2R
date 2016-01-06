package pilotage.meteo.saisie;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import pilotage.database.meteo.EtatPossibleDatabaseService;
import pilotage.database.meteo.MeteoIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_SeuilPlage;
import pilotage.service.constants.PilotageConstants;

public class RefreshCouleurEtat extends AbstractAction {

	private static final long serialVersionUID = 4449061445594805980L;

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String etat = request.getParameter("etat");
		String format = request.getParameter("format");
		
		String couleur = "";

		if(format.equals("liste")){
			couleur = EtatPossibleDatabaseService.getByName(etat).getCouleur();
		}
		else if(format.equals("reel")){
			etat = etat.replace(" ", "");
			int seuil = Integer.parseInt(request.getParameter("seuil"));
			int totalGAB = Integer.parseInt(request.getParameter("totalGab"));
			Meteo_SeuilPlage s = MeteoIndicateurDatabaseService.getSeuil(seuil, null);
			
			int val = Integer.parseInt(etat);
			
			couleur = PilotageConstants.OK;
			if(!s.getPertu_min().equals("")){
				int pertu_min = totalGAB-Math.round(totalGAB*Integer.parseInt(s.getPertu_min())/100);
				if(val<pertu_min) couleur = PilotageConstants.PERTURBE;
			}
			if(!s.getKo_min().equals("")){
				int ko_min = totalGAB-Math.round(totalGAB*Integer.parseInt(s.getKo_min())/100);
				if(val<ko_min) couleur = PilotageConstants.KO;
			}
			if(!s.getPertu_max().equals("")){
				int pertu_max = totalGAB+Math.round(totalGAB*Integer.parseInt(s.getPertu_max())/100);
				if(val>pertu_max) couleur = PilotageConstants.PERTURBE;
			}
			if(!s.getKo_max().equals("")){
				int ko_max = totalGAB+Math.round(totalGAB*Integer.parseInt(s.getKo_max())/100);
				if(val>ko_max) couleur = PilotageConstants.KO;
			}
		}
		
		try{
			response.setContentType("text/text;charset=utf-8");
			PrintWriter out = response.getWriter();
	        out.println(couleur);
	        out.flush();
	        out.close();
		}
        catch (Exception e) {
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("error", "Une erreur est survenue lors de la tentative de rafraichissement de la liste : " + e.getMessage());
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		return null;
	}

}
