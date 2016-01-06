package pilotage.admin.actions.parametre;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import pilotage.admin.metier.Parametre;
import pilotage.database.admin.ParametreDatabaseService;
import pilotage.framework.AbstractAdminAction;

/**
 * This class is used to modify the specific parametre
 * Success : ShowParaAdminAction
 * Failed : 
 * 
 * @author Xxu
 *
 */

public class ModifyParaAdminAction extends AbstractAdminAction {
	private static final long serialVersionUID = 1L;
	private String libelle;
	private String valeur;
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

	/**
	 * @return the valeur
	 */
	public String getValeur() {
		return valeur;
	}

	/**
	 * @param valeur the valeur to set
	 */
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
		try{
			ParametreDatabaseService.save(libelle, valeur);
			info = MessageFormat.format(getText("parametre.modification.valide"), new Object[]{libelle});
			
			listEscapedPara = new ArrayList<Parametre>();
			listPara = (List<Parametre>) ParametreDatabaseService.getAll();
			ShowParaAdminAction.getListParaForDisplay(listPara, listEscapedPara);

			return OK;
		}
		catch (Exception e) {
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification d'un paramètre - validation ", e);
			return ERROR;
		}
	}

}
