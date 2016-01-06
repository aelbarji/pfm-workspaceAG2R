package pilotage.consignes.rechercher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pilotage.database.consigne.ConsigneFichierDatabaseService;
import pilotage.framework.AbstractAction;
import pilotage.metier.Consignes_Mots;
import pilotage.service.string.StringOperation;

public class RechercherMotsAction extends AbstractAction{

	private static final long serialVersionUID = -111482322537082833L;

	private String term;
	private String[] mots;
	
	private List<Consignes_Mots> cMots;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<Consignes_Mots> getCMots() {
		return cMots;
	}

	public void setCMots(List<Consignes_Mots> cMots) {
		this.cMots = cMots;
	}

	public String [] getMots() {
		return mots;
	}

	public void setMots(String[] mots) {
		this.mots = mots;
	}

	@Override
	protected boolean validateMetier() {
		return true;
	}

	@Override
	protected String executeMetier() {
				
		try {
			if (term != null && term.length() > 1)
			{
				List<Consignes_Mots> cMots = ConsigneFichierDatabaseService.getKeyWordsList(StringOperation.sansAccent(term.toLowerCase()));
                ArrayList<String> tmp = new ArrayList<String>();
				for(Consignes_Mots current : cMots){
                    tmp.add(current.getMot());
				}
				Collections.sort(tmp);
				mots=tmp.toArray(new String[tmp.size()]);
			}
			return "ok";
		}
		catch (Exception e) {
			System.out.println("Une erreur est survenue lors de la tentative de rafraichissement de la liste : " + e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
	
	
}
