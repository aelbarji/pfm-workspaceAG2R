/**
 * pilotage.admin.actions.titre
 * 5 juil. 2011
 */
package pilotage.admin.actions.titre;

import java.util.ArrayList;
import java.util.List;

import pilotage.database.admin.TitreDatabaseService;
import pilotage.framework.AbstractAdminAction;
import pilotage.admin.metier.Titre_page;

/**
 * @author xxu
 *
 */
public class ShowTitreAdminAction extends AbstractAdminAction {

	private static final long serialVersionUID = 6449061877090964717L;
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
		listTitre = TitreDatabaseService.getAll();
		listTitreEscaped = new ArrayList<Titre_page>();
		ShowTitreAdminAction.getListTitreForDisplay(listTitre, listTitreEscaped);
		return OK;
	}

	public static void getListTitreForDisplay(List<Titre_page> listTitre, List<Titre_page> listTitreEscaped){
		for(Titre_page tp : listTitre){
			Titre_page newTp = new Titre_page();
			newTp.setId(tp.getId());
			newTp.setDescription(tp.getDescription().replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"").replaceAll("'", "\\\\'"));
			newTp.setLibelle(tp.getLibelle().replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"").replaceAll("'", "\\\\'"));
			listTitreEscaped.add(newTp);
		}
	}
}
