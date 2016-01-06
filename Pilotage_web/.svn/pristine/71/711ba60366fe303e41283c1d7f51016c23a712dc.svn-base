package pilotage.charts;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import pilotage.admin.metier.Images;
import pilotage.database.images.ImagesDatabaseService;

import com.opensymphony.xwork2.ActionSupport;

public class ImageFromDatabase extends ActionSupport {  

	private static final long serialVersionUID = 1L;
	private Integer imageID;
	
	public Integer getImageID() {
		return imageID;
	}

	public void setImageID(Integer imageID) {
		this.imageID = imageID;
	}

	public String execute() throws Exception{
		Images image = ImagesDatabaseService.get(imageID);
  
		//mise dans la response
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setContentType(image.getType());  
        OutputStream out = response.getOutputStream();  
        out.write(image.getImage());  
  
        return null;
    }
}
