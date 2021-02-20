package ekh.support;

import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptionPassword {
	public static String MD5(String str) {
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] messageDigest=md.digest(str.getBytes());
			BigInteger number=new BigInteger(1, messageDigest);
			String hashtext=number.toString(16);
			while(hashtext.length()<32) {
				hashtext="0"+hashtext;
			}
			return hashtext;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
