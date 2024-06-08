package cn.caam.gs.common.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import cn.caam.gs.common.exception.ShaApiException;

public class EncryptorUtil {
	
	private static final String KEY = "YKo83n14SWf7o8G5";
    private static final String ALGORITHM = "AES";
    
	public static String encrypt(String source) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), ALGORITHM));
	        return new String(Base64.getEncoder().encode(cipher.doFinal(source.getBytes())));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
        		InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new ShaApiException(ex.getMessage());
        }
    }
    
    public static String decrypt(String encryptSource) {
    	try {
	    	Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), ALGORITHM));
	        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptSource.getBytes())));
    	} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
        		InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new ShaApiException(ex.getMessage());
        }
    }

    /*
    public static void main(String[] args) {            

    	String source2 = "f7xCwZUqaA8I3UYzA9Kpkg==";
    	System.out.println("Decrypt1: " + EncryptorUtil.decrypt(source2));
    	
    	String source3 = "1";
    	System.out.println("Encrypt1: " + EncryptorUtil.encrypt(source3));

        String source1 = "sha2017";
        
        String encrypt1 = EncryptorUtil.encrypt(source1);
        String encrypt2 = EncryptorUtil.encrypt(source2);
        String decrypt1 = EncryptorUtil.decrypt(encrypt1);
        String decrypt2 = EncryptorUtil.decrypt(encrypt2);
    	
        
        System.out.println("Source1: " + source1);
        System.out.println("Encrypt1: " + encrypt1);
        System.out.println("Decrypt1: " + decrypt1);
        System.out.println();
        System.out.println("Source2: " + source2);
        System.out.println("Encrypt2: " + encrypt2);
        System.out.println("Decrypt2: " + decrypt2);

        
    }
	*/
}
