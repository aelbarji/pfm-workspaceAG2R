package pilotage.service.color;

import java.util.List;

public class ColorService {
	private static Double pR = 0.2125;
	private static Double pG = 0.7154;
	private static Double pB = 0.0721;

	public static Boolean isDark(String hex){
		Integer red = Integer.parseInt(hex.substring(0, 2), 16);
		Integer green = Integer.parseInt(hex.substring(2, 4), 16);
		Integer blue = Integer.parseInt(hex.substring(4, 6), 16);
		
		return pR * red + pG * green + pB * blue <= 128;
	}
	
	
	
	//methode qui retourne la valeur byte de la partie à deux hexadecimale qui concerne la partie R (red) du  code couleur RGB à 6 hexadecimales
	//traitement special pour les valeurs superieures à 127 on retranche 256 pour avoir la valeur negative
	public static byte valueRedColor(String hex){
		
		
		int redInt = Integer.parseInt(hex.substring(0, 2),16);
		//if (redInt>127){
		//	redInt=redInt-256;
		//}
		//la valeur du byte est la même que le int dans tous les cas
		byte red = (byte) redInt;
		
		
		return  red  ;
		
	}
	
	//methode qui retourne la valeur byte de la partie à deux hexadecimale qui concerne la partie G (green) du  code couleur RGB à 6 hexadecimales
	public static byte valueGreenColor(String hex){
		
		int greenInt = Integer.parseInt(hex.substring(2, 4),16);
		//if (greenInt>127){
		//	greenInt=greenInt-256;
		//}
		//la valeur du byte est la même que le int dans tous les cas
		byte green = (byte) greenInt;
		
		
		return  green  ;
		
	}
	
	//methode qui retourne la valeur byte de la partie à deux hexadecimale qui concerne la partie B (blue) du  code couleur RGB à 6 hexadecimales
	public static byte valueBlueColor(String hex){
		
		int blueInt = Integer.parseInt(hex.substring(4, 6),16);
		//if (blueInt>127){
		//	blueInt=blueInt-256;
		//}
		//la valeur du byte est la même que le int dans tous les cas
		byte blue = (byte) blueInt;
		
		
		return  blue  ;
		
	}
	
	//methode qui retourne  un index de couleur non utilisé encore car non présent dans la liste listeIndexCouleurDejaUtilise
	
	public static Short getIndexCouleurNonUtilise(List<Short> listeIndexCouleurDejaUtilise){
		
		//la palette de couleur Excel sur HSSF comporte des index utilisable de 8 à 63.
		//les couleurs primaires sont sur les index 8 (noir), 9(blanc), 10(rouge), 11(vert) et 12(Bleu).
		Short Resultat=(short) (63 - listeIndexCouleurDejaUtilise.size());
		if (Resultat < 8) {
			Resultat = null;
		}
		

		 return Resultat;
	}
	

}
