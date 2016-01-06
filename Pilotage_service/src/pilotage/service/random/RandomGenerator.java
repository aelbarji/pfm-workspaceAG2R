package pilotage.service.random;

import java.util.Random;

public class RandomGenerator {
	/**
	 * generate random password 
	 * @return  a random password which the length is 12.
	 */
	public static String getRandomString() { 
		
		int length = 12;   //the length of the password
	    String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
	    
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
}
