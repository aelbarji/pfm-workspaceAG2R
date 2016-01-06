/**
 * pilotage.incidents
 * 26 juil. 2011
 */
package pilotage.service.string;

import pilotage.service.constants.PilotageConstants;

/**
 * @author xxu
 *
 */
public class StringConverter {
	/**
	 * Transforme le tableau en string, chaque élément étant séparé par le séparateur
	 * @param str
	 * @param separateur
	 * @return
	 */
	public static String arrayToString(String[] str, String separateur) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			sb.append(str[i]);
			if (i < (str.length - 1)) {
				sb.append(separateur);
			}
		}
		
		return sb.toString();
	}

	/**
	 * Transforme le string en tableau suivant le séparateur
	 * @param str
	 * @param separateur
	 * @return
	 */
	public static String[] stringToArray(String str, String separateur) {
		return str.split(separateur);
	}

	/**
	 * Crée une string en respectant le format Json.
	 * @param nomKey
	 * @param valeur
	 * @return
	 */
	public static String toJson(String nomKey, String valeur){
		return (PilotageConstants.SEPARATEUR_5 + nomKey + PilotageConstants.SEPARATEUR_5 + ":" 
				+ PilotageConstants.SEPARATEUR_5 + valeur + PilotageConstants.SEPARATEUR_5)
				+ PilotageConstants.SEPARATEUR_3;
	}
	
	/**
	 * Transforme une String issue d'un objet Json pour affichage.
	 * @param chaine
	 * @return
	 */
	public static String afficheFiltre (String chaine){
		return chaine.replace("\"", " ").replace("[", "").replace("]", "")
		.replace("{", "").replace("}", "");
	}

}
