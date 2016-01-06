package pilotage.url;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import pilotage.database.url.ListeURLDatabaseService;
import pilotage.metier.Liste_URL;

import com.opensymphony.xwork2.ActionSupport;

public class ImageFavoriFromDatabase extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Integer urlID;
	
	public Integer getUrlID() {
		return urlID;
	}

	public void setUrlID(Integer urlID) {
		this.urlID = urlID;
	}

	public String execute() throws Exception{
		Liste_URL url = ListeURLDatabaseService.get(urlID);
		
		//mise dans la response
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setContentType("image/png");  
        OutputStream out = response.getOutputStream(); 
        out.write(url.getImage());  
  
        return null;
    }
}
