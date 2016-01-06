package pilotage.service.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class Base64Convertor {
	private static final Integer NB_CARACTERE_PAR_DECOUPE = 2;
	
	/**
	 * Méthode encodant le paramètre en base 64
	 * 
	 * @param decodedEncode
	 * @return
	 */
	public static String encodeToBase64(String decodedString){
		byte[] encodedBytes = Base64.encodeBase64(decodedString.getBytes());
		return encodedBytes == null ? null : new String(encodedBytes); 
	}
	
	/**
	 * Méthode décodant le paramètre du format base 64
	 * 
	 * @param encodedDecode
	 * @return
	 */
	public static String decodeFromBase64(String encodedDecode){
		byte[] decodedBytes = Base64.decodeBase64(encodedDecode.getBytes());
		return decodedBytes == null ? null : new String(decodedBytes);
	}
	
	/**
	 * Méthode encodant le paramètre de login pour l'intéropérabilité des 2 logiciels (Java et PHP)
	 * 
	 * @param encodedLogin
	 * @return
	 */
	public static String encodeLoginParameter(String decodedLogin){
		//encodage en base 64
		String encodedLogin = encodeToBase64(decodedLogin);
		if(encodedLogin == null)
			return "";	
		
		//découpage en morceaux de NB_CARACTERE_PAR_DECOUPE caractères en partant du début
		int debut = 0;
		int fin = NB_CARACTERE_PAR_DECOUPE;
		List<String> morceaux = new ArrayList<String>(); 
		while(debut < encodedLogin.length()){
			if(fin <= encodedLogin.length())
				morceaux.add(0, encodedLogin.substring(debut, fin));
			else
				morceaux.add(0, encodedLogin.substring(debut));
			
			debut = fin;
			fin += NB_CARACTERE_PAR_DECOUPE;
		}
		
		//assemblage des morceaux de chaine en ordre inverse
		StringBuffer encodedParameter = new StringBuffer();
		for(String bout : morceaux){
			encodedParameter.append(bout);
		}
		
		return encodedParameter.toString();
	}
	
	/**
	 * Méthode décodant le paramètre de login pour l'intéropérabilité des 2 logiciels (Java et PHP)
	 * 
	 * @param encodedLogin
	 * @return
	 */
	public static String decodeLoginParameter(String encodedParameter){
		//découpage en parties de NB_CARACTERE_PAR_DECOUPE caractères en partant de la fin
		int fin = encodedParameter.length();
		int debut = fin - NB_CARACTERE_PAR_DECOUPE;
		StringBuffer encodedLogin = new StringBuffer();
		while(fin > 0){
			if(debut >= 0)
				encodedLogin.append(encodedParameter.substring(debut, fin));
			else
				encodedLogin.append(encodedParameter.substring(0, fin));
			
			fin = debut;
			debut -= NB_CARACTERE_PAR_DECOUPE;
		}
		
		String decodedLogin = decodeFromBase64(encodedLogin.toString());
		
		return decodedLogin == null ? "" : decodedLogin;
	}
}
