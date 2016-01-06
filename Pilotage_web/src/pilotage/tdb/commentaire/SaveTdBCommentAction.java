package pilotage.tdb.commentaire;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.joda.time.DateTime;

import pilotage.database.tdb.TdBCommentDatabaseService;
import pilotage.database.environnement.EnvironnementDatabaseService;
import pilotage.framework.AbstractActionConsigne;
import pilotage.metier.Environnement;
import pilotage.metier.TdB_Commentaire;
import pilotage.metier.Users;
import pilotage.service.constants.PilotageConstants;

public class SaveTdBCommentAction extends AbstractActionConsigne{

	private static final long serialVersionUID = -7518143106699878947L;

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String comment = request.getParameter("comment");
		String envirID = request.getParameter("envir");
		
		Environnement envir = EnvironnementDatabaseService.get(Integer.parseInt(envirID));
		
		DateTime datetime = new DateTime();
		TdB_Commentaire tdbComment = TdBCommentDatabaseService.getByDateEnvir(datetime.minusHours(12), envir);
		Users user = (Users)session.get(PilotageConstants.USER_LOGGED);
		
		try {
			if(tdbComment == null) TdBCommentDatabaseService.create(datetime, envir, comment, user);
			else if(!comment.equals("")) TdBCommentDatabaseService.update(tdbComment.getId(), datetime, comment, user);
			else TdBCommentDatabaseService.delete(tdbComment.getId());
			response.setContentType("text/text;charset=utf-8");
			PrintWriter out = response.getWriter();
	        out.println("Commentaire mis à jour");
	        out.flush();
	        out.close();
			
		}catch (Exception e) {
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("error", "Une erreur est survenue lors de la tentative de rafraichissement de la liste : " + e.getMessage());
			JSONObject json = JSONObject.fromObject(hm);
			response.setHeader("X-JSON", json.toString());
		}
		return null;
	}

}
