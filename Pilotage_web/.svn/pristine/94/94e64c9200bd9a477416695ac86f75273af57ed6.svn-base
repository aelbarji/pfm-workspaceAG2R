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
public class SupprimerTitreAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = -6368713545629466055L;
	private String id;	
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
		// TODO Auto-generated method stub
		try{
			Titre_page tpToDelete = TitreDatabaseService.get(id);
			TitreDatabaseService.delete(tpToDelete);
			
			info = MessageFormat.format(getText("titre_page.suppression.valide"), new Object[]{tpToDelete.getDescription()});

			return OK;
		}catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Suppression de titre ", e);
			return ERROR;
		}
		finally{
			listTitre = TitreDatabaseService.getAll();
			listTitreEscaped = new ArrayList<Titre_page>();
			ShowTitreAdminAction.getListTitreForDisplay(listTitre, listTitreEscaped);
		}
	}

}
