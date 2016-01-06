package pilotage.astreintes.actions.type;

import java.util.List;

import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Type;

public class ShowAstreinteTypeAction extends AbstractAction{

	private static final long serialVersionUID = -2896464211724279208L;
	
	private List<Astreinte_Type> aTypes;
	
	private String astreinteType;

	public List<Astreinte_Type> getATypes() {
		return aTypes;
	}

	public void setATypes(List<Astreinte_Type> aTypes) {
		this.aTypes = aTypes;
	}

	public String getAstreinteType() {
		return astreinteType;
	}

	public void setAstreinteType(String astreinteType) {
		this.astreinteType = astreinteType;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		if(error == null || error.equals(""))
			astreinteType = null;
		
		aTypes = AstreinteTypeDatabaseService.getAll();
		return OK;
	}
}
