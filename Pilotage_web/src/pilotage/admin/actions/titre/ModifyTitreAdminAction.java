/**
 * pilotage.admin.actions.titre
 * 6 juil. 2011
 */
package pilotage.admin.actions.titre;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import pilotage.admin.metier.Titre_page;
import pilotage.database.admin.TitreDatabaseService;
import pilotage.framework.AbstractAdminAction;

/**
 * @author xxu
 *
 */
public class ModifyTitreAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 4477843399209044102L;
	private String id;
	private String titre;
	
	private List<Titre_page> listTitre;
	private List<Titre_page> listTitreEscaped;

	/**
	 * Getter de la liste des titre avec les caractères d'échappement
	 * @return the listTitre
	 */
	public List<Titre_page> getListTitreEscaped() {
		return listTitreEscaped;
	}

	/**
	 * Setter de la liste des titre avec les caractères d'échappement
	 * @param listTitre the listTitre to set
	 */
	public void setListTitreEscaped(List<Titre_page> listTitreEscaped) {
		this.listTitreEscaped = listTitreEscaped;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the listTitre
	 */
	public List<Titre_page> getListTitre() {
		return listTitre;
	}

	/**
	 * @param listTitre the listTitre to set
	 */
	public void setListTitre(List<Titre_page> listTitre) {
		this.listTitre = listTitre;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAdminAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		return true;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAdminAction#executeMetier()
	 */
	@Override
	protected String executeMetier() {
		try{
			Titre_page modifyTP = TitreDatabaseService.get(id);
			TitreDatabaseService.modify(id, titre);
			
			info = MessageFormat.format(getText("titre_page.modification.valide"), new Object[]{modifyTP.getDescription()});

			listTitre = TitreDatabaseService.getAll();
			listTitreEscaped = new ArrayList<Titre_page>();
			ShowTitreAdminAction.getListTitreForDisplay(listTitre, listTitreEscaped);
			
			return OK;
		}catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Modification de titre - validation ", e);
			return ERROR;
		}
	}

}
