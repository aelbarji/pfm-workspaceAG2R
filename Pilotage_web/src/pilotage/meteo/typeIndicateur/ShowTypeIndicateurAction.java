package pilotage.meteo.typeIndicateur;

import java.util.List;

import pilotage.database.meteo.TypeIndicateurDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Meteo_TypeIndicateur;

public class ShowTypeIndicateurAction extends AbstractAction{

	private static final long serialVersionUID = 3918761222783306683L;

	private List<Meteo_TypeIndicateur> listTypeIndic;
	
	public List<Meteo_TypeIndicateur> getListTypeIndic() {
		return listTypeIndic;
	}

	public void setListTypeIndic(List<Meteo_TypeIndicateur> listTypeIndic) {
		this.listTypeIndic = listTypeIndic;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			listTypeIndic = TypeIndicateurDatabaseService.getAllListe();
			return OK;
		} 
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Creation d'une caisse - ", e);
			return ERROR;
		}
	}

}
