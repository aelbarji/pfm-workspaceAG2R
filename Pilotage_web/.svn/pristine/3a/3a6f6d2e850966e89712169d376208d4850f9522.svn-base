/**
 * pilotage.admin.actions.titre
 * 5 juil. 2011
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
public class CreateTitreAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = -8387762891551013222L;

	private String id;
	private String titre;
	private String description;
	
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAdminAction#validateMetier()
	 */
	@Override
	protected boolean validateMetier() {
		Titre_page tp = TitreDatabaseService.get(id);
		if(tp != null){
			error = MessageFormat.format(getText("titre_page.creation.existe.deja"), new Object[]{id});
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see pilotage.framework.AbstractAdminAction#executeMetier()
	 */
	@Override
	protected String executeMetier() {
		try{
			TitreDatabaseService.create(id, description, titre);
			info = MessageFormat.format(getText("titre_page.creation.valide"), new Object[]{description});
			
			listTitre = TitreDatabaseService.getAll();
			listTitreEscaped = new ArrayList<Titre_page>();
			ShowTitreAdminAction.getListTitreForDisplay(listTitre, listTitreEscaped);
			
			return OK;
		}catch(Exception e){
			error = getText("error.message.generique") + " : " + e.getMessage();
			erreurLogger.error("Création de titre - validation ", e);
			return ERROR;
		}
	}

}
