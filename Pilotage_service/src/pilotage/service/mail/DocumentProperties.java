package pilotage.service.mail;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DocumentProperties {
	
	private Properties properties;
	
	private InputStream inputFile = null;
	
	public DocumentProperties(){
		properties = new Properties();
	}
	
	/**
	 * 
	 * @param filePath   le nom du document
	 * @throws IOException 
	 */
	public DocumentProperties(String filePath) throws IOException{
		properties = new Properties();
		try {
			inputFile = new BufferedInputStream(new FileInputStream(filePath));
            properties.load(inputFile);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(e.toString());
		} catch (IOException e) {
			throw new IOException(e.toString());
		}
	}
	
	/**
	 * 
	 * @param key     pour obtenir la valeur
	 * @return        la valeur d'une document properties
	 */
	public String getValue(String key){
		if(properties.containsKey(key)){
			String value = properties.getProperty(key);
			return value;
		}else{
			return "";
		}
	}
}
