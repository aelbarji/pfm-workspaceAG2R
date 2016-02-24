package pilotage.changements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import pilotage.database.changements.ChangementsDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Changements;

public class ReadExcelFile extends AbstractAction implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private List<Changements> list = new ArrayList<Changements>();

	private File myFile;
	private String myFileFileName;

	private HttpServletRequest servletRequest;

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	@Override
	public String executeMetier() {
		try {

			String filePath = servletRequest.getSession().getServletContext()
					.getRealPath("/");
			File fileToCreate = new File(filePath, myFileFileName);
			FileUtils.copyFile(this.myFile, fileToCreate);

			list = ChangementsDatabaseService.importExcel(fileToCreate);
			ChangementsDatabaseService.saveChangements(list);

			return OK;
		} catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			return ERROR;
		}
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}
}
