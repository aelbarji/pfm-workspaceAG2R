package pilotage.astreintes.actions.type;

import pilotage.database.astreintes.AstreinteTypeDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Astreinte_Type;

public class RedirectModifyAstreinteTypeAction extends AbstractAction{

	private static final long serialVersionUID = 6758365363414404226L;
	
	private Integer typeID;
	private String astreinteType;
	
	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
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
		Astreinte_Type type =  AstreinteTypeDatabaseService.get(typeID);
		typeID = type.getId();
		astreinteType = type.getType();
		return OK;
	}

}
