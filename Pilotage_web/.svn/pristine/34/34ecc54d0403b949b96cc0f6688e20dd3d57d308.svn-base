package pilotage.checklist.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import pilotage.database.admin.ParametreDatabaseService;
import pilotage.database.consigne.ConsignesDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Consignes_Type;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.file.PilotageFile;


public class ShowDocumentationTree extends AbstractAction {
	
	private static final long serialVersionUID = 7251647639968137067L;
	private List<PilotageFile> listFiles;
	private String dirRoot;

	public List<PilotageFile> getListFiles() {
		return listFiles;
	}

	public void setListFiles(List<PilotageFile> listFiles) {
		this.listFiles = listFiles;
	}
	
	public String getDirRoot() {
		return dirRoot;
	}

	public void setDirRoot(String dirRoot) {
		this.dirRoot = dirRoot;
	}


	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() { 
		HttpServletRequest request = ServletActionContext.getRequest();
		Consignes_Type consigneType = null;
		
		if(request!=null && request.getParameter("type")!=null){
			consigneType = ConsignesDatabaseService.getType(Integer.parseInt(request.getParameter("type")));
		}else
			consigneType = ConsignesDatabaseService.getType(PilotageConstants.CONSIGNE_PERMANENTE);
	
//		String path = ParametreDatabaseService.get("DOCUMENT_FOLDER").getValeur();
//		listFiles = getFilesInPath(path);
		
		dirRoot = ParametreDatabaseService.get("DOCUMENT_FOLDER").getValeur()+consigneType.getDossier();
		
		dirRoot = dirRoot.replace('\\', '/');
		if (dirRoot.charAt(dirRoot.length()-1) != '/') {
		    dirRoot += "/";
		}

		return SUCCESS;
	}
	
//    public List<PilotageFile> getFilesInPath(String path) {  
//        List<PilotageFile> fileList = new ArrayList<PilotageFile>();       
//        File file = new File(path);           
//        File[] childFiles = file.listFiles();  
//        
//        try  
//        {  
//            for (int i = 0; i < childFiles.length; i++)  
//            {  
//                String filePath = childFiles[i].getAbsolutePath();  
//            	if (childFiles[i].isDirectory()) {  
//            		PilotageFile file_list = new PilotageFile();
//                	file_list.setChildren(getFilesInPath(filePath));
//                	file_list.setFile(childFiles[i]);
//                    fileList.add(file_list);
//                }  else {
//                	PilotageFile file_list = new PilotageFile();
//                	file_list.setFile(childFiles[i]);
//                	fileList.add(file_list);
//                }
//            }  
//        } catch (Exception e)  {  
//            return fileList;  
//        }  
//        return fileList;  
//    }
}