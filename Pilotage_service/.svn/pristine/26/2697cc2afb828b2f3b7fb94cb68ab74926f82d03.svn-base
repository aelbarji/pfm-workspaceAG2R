package pilotage.service.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PilotageFile {
	private File file;
	private List<PilotageFile> children;
	
	public PilotageFile() {
		children = new ArrayList<PilotageFile>();
	}
	
	public PilotageFile(File file2) {
		children = new ArrayList<PilotageFile>();
		setFile(file2);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<PilotageFile> getChildren() {
		return children;
	}

	public void setChildren(List<PilotageFile> children) {
		this.children = children;
	}
}
