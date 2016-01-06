package pilotage.service.convertor;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import pilotage.service.constants.PilotageConstants;

/**
 * Classe de service permettant de crypter et de décrypter
 *
 */
public final class EncryptDecrypt {
 /**
  * Constructeur par défaut.
  */
 private EncryptDecrypt() {
 }

 private static final String ALGORITHM = "Blowfish";

 
 /**
  * Encrypter un String.
  * 
  * @param toEncrypt
  *            le string à encrypter
  * @param strkey
  *            la clé d'encrypt
  * @param log
  *            log sur serveur
  * @return le string encrypté
  */
 public static String encryptBlowfish(final String toEncrypt, final String strkey) {
  String result = null;
  try {
	   SecretKeySpec key = new SecretKeySpec(strkey.getBytes("ISO-8859-1"), ALGORITHM);
	   Cipher cipher = Cipher.getInstance(ALGORITHM);
	   cipher.init(Cipher.ENCRYPT_MODE, key);
	   result = Base64.encodeBase64String(cipher.doFinal(toEncrypt.getBytes(PilotageConstants.ENCODAGE)));
	  } catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	  } catch (NoSuchPaddingException e) {
		  e.printStackTrace();
	  } catch (InvalidKeyException e) {
	  } catch (IllegalBlockSizeException e) {
		  e.printStackTrace();
	  } catch (BadPaddingException e) {
	  } catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
	  }
  return result;
 }

 /**
  * Decrypter un String.
  * 
  * @param toDecrypt
  *            le string à decrypter
  * @param strkey
  *            la clé d'encrypt
  * @param log
  *            log sur serveur
  * @return le string decrypté
  */
 public static String decryptBlowfish(final String toDecrypt, final String strkey) {
  String result = null;
  try {
   SecretKeySpec key = new SecretKeySpec(strkey.getBytes(PilotageConstants.ENCODAGE), ALGORITHM);
   Cipher cipher = Cipher.getInstance(ALGORITHM);
   cipher.init(Cipher.DECRYPT_MODE, key);
   byte[] decrypted = cipher.doFinal(Base64.decodeBase64(toDecrypt));
   result = new String(decrypted, PilotageConstants.ENCODAGE);
  } catch (IllegalBlockSizeException e) {
	  e.printStackTrace();
  } catch (BadPaddingException e) {
	  e.printStackTrace();
  } catch (InvalidKeyException e) {
	  e.printStackTrace();
  } catch (NoSuchAlgorithmException e) {
	  e.printStackTrace();
  } catch (NoSuchPaddingException e) {
	  e.printStackTrace();
  } catch (UnsupportedEncodingException e) {
	  e.printStackTrace();
  }
  return result;
 }
}
