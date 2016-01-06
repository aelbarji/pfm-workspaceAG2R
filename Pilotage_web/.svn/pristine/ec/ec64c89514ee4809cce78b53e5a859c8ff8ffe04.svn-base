package pilotage.admin.actions.parametre;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import pilotage.admin.metier.Parametre;
import pilotage.database.admin.ParametreDatabaseService;
import pilotage.framework.AbstractAdminAction;

/**
 * This class is used to delete the specific parametre
 * Success : ShowParaAdminAction
 * Failed :
 * 
 * @author Xxu
 *
 */

public class SupprimerParaAdminAction extends AbstractAdminAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1518695009862098972L;
	private String libelle;
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


	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			ParametreDatabaseService.delete(libelle);
			info = MessageFormat.format(getText("parametre.suppression.valide"), new Object[]{libelle});

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Supression d'un paramètre ", e);
			return ERROR;
		}
		finally{
			listEscapedPara = new ArrayList<Parametre>();
			listPara = (List<Parametre>) ParametreDatabaseService.getAll();
			ShowParaAdminAction.getListParaForDisplay(listPara, listEscapedPara);
		}
	}

}
