package pilotage.admin.actions.parametre;

import java.util.ArrayList;
import java.util.List;

import pilotage.admin.metier.Parametre;
import pilotage.database.admin.ParametreDatabaseService;
import pilotage.framework.AbstractAdminAction;

/**
 * This class is used to get data for parametres page.
 * Success : listPara.jsp
 * Error :
 * 
 * @author Xxu
 *
 */

public class ShowParaAdminAction extends AbstractAdminAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4284171520557744426L;
	private List<Parametre> listEscapedPara;
	private List<Parametre> listPara;
	
	/**
	 * Getter de la liste de paramètres ayant les caractères d'échappement
	 * @return
	 */
	public List<Parametre> getListEscapedPara() {
		return listEscapedPara;
	}

	/**
	 * Setter de la liste de paramètres ayant les caractères d'échappement
	 * @param listEscapedPara
	 */
	
	public void setListEscapedPara(List<Parametre> listEscapedPara) {
		this.listEscapedPara = listEscapedPara;
	}

	/**
	 * @return the listPara
	 */
	public List<Parametre> getListPara() {
		return listPara;
	}

	/**
	 * @param listPara the listPara to set
	 */
	public void setListPara(List<Parametre> listPara) {
		this.listPara = listPara;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		listEscapedPara = new ArrayList<Parametre>();
		listPara = (List<Parametre>) ParametreDatabaseService.getAll();
		ShowParaAdminAction.getListParaForDisplay(listPara, listEscapedPara);
		return OK;
	}
	
	public static void getListParaForDisplay(List<Parametre> listPara, List<Parametre> listEscapedPara){
		for(Parametre param : listPara){
			Parametre newParam = new Parametre();
			newParam.setValeur(param.getValeur().replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"").replaceAll("'", "\\\\'"));
			newParam.setLibelle(param.getLibelle().replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"").replaceAll("'", "\\\\'"));
			listEscapedPara.add(newParam);
		}
	}

}
