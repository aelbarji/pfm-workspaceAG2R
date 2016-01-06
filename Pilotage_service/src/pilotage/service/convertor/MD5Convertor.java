package pilotage.service.convertor;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class MD5Convertor {
	/**
	 * USE MD5 to encrypt the password
	 * @param password
	 * @return   MD5 encryption password
	 */
	public static String standardMd5(String password){
		
		String md5pwd = null;
		int i;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte b[] = md.digest();
			StringBuffer buf = new StringBuffer("");
			for(int offset = 0; offset < b.length; offset++){
				i = b[offset];
				if(i<0)i+=256;
				if(i<16)
				buf.append("0");
				buf.append(Integer.toHexString(i));
				md5pwd = buf.toString(); 
			}
		} catch (Exception e) {
			Logger erreurLogger = Logger.getLogger("erreur");
			erreurLogger.error(e);
		}
		return md5pwd;
	}
	
	 /**
    *
    * Command line test rig.
    *
    */

   static public void main(String argv[]) {
       System.out.println(MD5Convertor.crypt("password", "4jfRL*/!"));
   }

   static private final String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

   static private final String itoa64 = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

   static private final String to64(long v, int size) {
       StringBuffer result = new StringBuffer();

       while (--size >= 0) {
           result.append(itoa64.charAt((int) (v & 0x3f)));
           v >>>= 6;
       }

       return result.toString();
   }

   static private final void clearbits(byte bits[]) {
       for (int i = 0; i < bits.length; i++) {
           bits[i] = 0;
       }
   }

   /**
    * convert an encoded unsigned byte value into a int
    * with the unsigned value.
    */

   static private final int bytes2u(byte inp) {
       return (int) inp & 0xff;
   }

   /**
    * <p>This method actually generates a OpenBSD/FreeBSD/Linux PAM compatible
    * md5-encoded password hash from a plaintext password and a
    * salt.</p>
    *
    * <p>The resulting string will be in the form '$1$&lt;salt&gt;$&lt;hashed mess&gt;</p>
    *
    * @param password Plaintext password
    *
    * @return An OpenBSD/FreeBSD/Linux-compatible md5-hashed password field.
    */

   static public final String crypt(String password) {
       StringBuffer salt = new StringBuffer();
       java.util.Random randgen = new java.util.Random();

       /* -- */

       while (salt.length() < 8) {
           int index = (int) (randgen.nextFloat() * SALTCHARS.length());
           salt.append(SALTCHARS.substring(index, index + 1));
       }

       return MD5Convertor.crypt(password, salt.toString());
   }

   /**
    * <p>This method actually generates a OpenBSD/FreeBSD/Linux PAM compatible
    * md5-encoded password hash from a plaintext password and a
    * salt.</p>
    *
    * <p>The resulting string will be in the form '$1$&lt;salt&gt;$&lt;hashed mess&gt;</p>
    *
    * @param password Plaintext password
    * @param salt A short string to use to randomize md5.  May start with $1$, which
    *             will be ignored.  It is explicitly permitted to pass a pre-existing
    *             MD5Convertor'ed password entry as the salt.  crypt() will strip the salt
    *             chars out properly.
    *
    * @return An OpenBSD/FreeBSD/Linux-compatible md5-hashed password field.
    */

   static public final String crypt(String password, String salt) {
       return MD5Convertor.crypt(password, salt, "$1$");
   }

   /**
    * <p>This method generates an Apache MD5 compatible
    * md5-encoded password hash from a plaintext password and a
    * salt.</p>
    *
    * <p>The resulting string will be in the form '$apr1$&lt;salt&gt;$&lt;hashed mess&gt;</p>
    *
    * @param password Plaintext password
    *
    * @return An Apache-compatible md5-hashed password string.
    */

   static public final String apacheCrypt(String password) {
       StringBuffer salt = new StringBuffer();
       java.util.Random randgen = new java.util.Random();

       /* -- */

       while (salt.length() < 8) {
           int index = (int) (randgen.nextFloat() * SALTCHARS.length());
           salt.append(SALTCHARS.substring(index, index + 1));
       }

       return MD5Convertor.apacheCrypt(password, salt.toString());
   }

   /**
    * <p>This method actually generates an Apache MD5 compatible
    * md5-encoded password hash from a plaintext password and a
    * salt.</p>
    *
    * <p>The resulting string will be in the form '$apr1$&lt;salt&gt;$&lt;hashed mess&gt;</p>
    *
    * @param password Plaintext password
    * @param salt A short string to use to randomize md5.  May start with $apr1$, which
    *             will be ignored.  It is explicitly permitted to pass a pre-existing
    *             MD5Convertor'ed password entry as the salt.  crypt() will strip the salt
    *             chars out properly.
    *
    * @return An Apache-compatible md5-hashed password string.
    */

   static public final String apacheCrypt(String password, String salt) {
       return MD5Convertor.crypt(password, salt, "$apr1$");
   }

   /**
    * <p>This method actually generates md5-encoded password hash from
    * a plaintext password, a salt, and a magic string.</p>
    *
    * <p>There are two magic strings that make sense to use here.. '$1$' is the
    * magic string used by the FreeBSD/Linux/OpenBSD MD5Convertor algorithm, and
    * '$apr1$' is the magic string used by the Apache MD5Convertor algorithm.</p>
    *
    * <p>The resulting string will be in the form '&lt;magic&gt;&lt;salt&gt;$&lt;hashed mess&gt;</p>
    *
    * @param password Plaintext password @param salt A short string to
    * use to randomize md5.  May start with the magic string, which
    * will be ignored.  It is explicitly permitted to pass a
    * pre-existing MD5Convertor'ed password entry as the salt.  crypt()
    * will strip the salt chars out properly.
    * 
    * @return An md5-hashed password string. 
    */

   static public final String crypt(String password, String salt,
           String magic) {
       /* This string is magic for this algorithm.  Having it this way,
        * we can get get better later on */

       byte finalState[];
       MD5 ctx, ctx1;
       long l;

       /* -- */

       /* Refine the Salt first */

       /* If it starts with the magic string, then skip that */

       if (salt.startsWith(magic)) {
           salt = salt.substring(magic.length());
       }

       /* It stops at the first '$', max 8 chars */

       if (salt.indexOf('$') != -1) {
           salt = salt.substring(0, salt.indexOf('$'));
       }

       if (salt.length() > 8) {
           salt = salt.substring(0, 8);
       }

       ctx = new MD5();

       ctx.Update(password); // The password first, since that is what is most unknown
       ctx.Update(magic); // Then our magic string
       ctx.Update(salt); // Then the raw salt

       /* Then just as many characters of the MD5(pw,salt,pw) */

       ctx1 = new MD5();
       ctx1.Update(password);
       ctx1.Update(salt);
       ctx1.Update(password);
       finalState = ctx1.Final();

       for (int pl = password.length(); pl > 0; pl -= 16) {
           ctx.Update(finalState, pl > 16 ? 16 : pl);
       }

       /* the original code claimed that finalState was being cleared
          to keep dangerous bits out of memory, but doing this is also
          required in order to get the right output. */

       clearbits(finalState);

       /* Then something really weird... */

       for (int i = password.length(); i != 0; i >>>= 1) {
           if ((i & 1) != 0) {
               ctx.Update(finalState, 1);
           } else {
               ctx.Update(password.getBytes(), 1);
           }
       }

       finalState = ctx.Final();

       /*
        * and now, just to make sure things don't run too fast
        * On a 60 Mhz Pentium this takes 34 msec, so you would
        * need 30 seconds to build a 1000 entry dictionary...
        *
        * (The above timings from the C version)
        */

       for (int i = 0; i < 1000; i++) {
           ctx1 = new MD5();

           if ((i & 1) != 0) {
               ctx1.Update(password);
           } else {
               ctx1.Update(finalState, 16);
           }

           if ((i % 3) != 0) {
               ctx1.Update(salt);
           }

           if ((i % 7) != 0) {
               ctx1.Update(password);
           }

           if ((i & 1) != 0) {
               ctx1.Update(finalState, 16);
           } else {
               ctx1.Update(password);
           }

           finalState = ctx1.Final();
       }

       /* Now make the output string */

       StringBuffer result = new StringBuffer();

       result.append(magic);
       result.append(salt);
       result.append("$");

       l = (bytes2u(finalState[0]) << 16)
               | (bytes2u(finalState[6]) << 8)
               | bytes2u(finalState[12]);
       result.append(to64(l, 4));

       l = (bytes2u(finalState[1]) << 16)
               | (bytes2u(finalState[7]) << 8)
               | bytes2u(finalState[13]);
       result.append(to64(l, 4));

       l = (bytes2u(finalState[2]) << 16)
               | (bytes2u(finalState[8]) << 8)
               | bytes2u(finalState[14]);
       result.append(to64(l, 4));

       l = (bytes2u(finalState[3]) << 16)
               | (bytes2u(finalState[9]) << 8)
               | bytes2u(finalState[15]);
       result.append(to64(l, 4));

       l = (bytes2u(finalState[4]) << 16)
               | (bytes2u(finalState[10]) << 8)
               | bytes2u(finalState[5]);
       result.append(to64(l, 4));

       l = bytes2u(finalState[11]);
       result.append(to64(l, 2));

       /* Don't leave anything around in vm they could use. */
       clearbits(finalState);

       return result.toString();
   }
}
