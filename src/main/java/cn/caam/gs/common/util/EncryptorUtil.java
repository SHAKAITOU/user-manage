package cn.caam.gs.common.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.exception.ShaApiException;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;

public class EncryptorUtil {
	
	private static final String KEY = "YKo83n14SWf7o8G5";
    private static final String ALGORITHM = "AES";
    private static final String AUTH_IMG_RANDOM = "0123456789";
    private static final String AUTH_IMG_WIDTH = "150";
    private static final String AUTH_IMG_HEIGHT = "50";
    private static final String AUTH_IMG_RANDOM_LENGTH = "4";
    
    private static final String ORDER_ID_RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ORDER_ID_RANDOM_LENGTH = "7";
    
    private static final String MSG_ID_RANDOM_LENGTH = "3";
    
	public static String encrypt(String source) {
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), ALGORITHM));
	        return new String(Base64.encodeBase64(cipher.doFinal(source.getBytes())));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
        		InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new ShaApiException(ex.getMessage());
        }
    }
    
    public static String decrypt(String encryptSource) {
    	try {
	    	Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), ALGORITHM));
	        return new String(cipher.doFinal(Base64.decodeBase64(encryptSource.getBytes())));
    	} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
        		InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new ShaApiException(ex.getMessage());
        }
    }
    
    public static String generateAuthImgStr(HttpServletRequest request) throws IOException {
        
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", AUTH_IMG_WIDTH);
        properties.setProperty("kaptcha.image.height", AUTH_IMG_HEIGHT);
        properties.setProperty("kaptcha.textproducer.char.string", AUTH_IMG_RANDOM);
        properties.setProperty("kaptcha.textproducer.char.length", AUTH_IMG_RANDOM_LENGTH);
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        String text = defaultKaptcha.createText();
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.AUTH_CODE.getValue(), text);
        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        ImageIO.write(image, "jpg", baos);
        return "data:image/;base64," + Base64.encodeBase64String(baos.toByteArray());
    }
    
    public static void clearAuthWhenLogined(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.AUTH_CODE.getValue(), null);
    }
    
    public static String generateOrderId() {
        String prefix = LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUMMDDHHMISS);
        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.char.string", ORDER_ID_RANDOM);
        properties.setProperty("kaptcha.textproducer.char.length", ORDER_ID_RANDOM_LENGTH);
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        String randomSubfix = defaultKaptcha.createText();
        
        return prefix + randomSubfix;
    }
    
    public static String generateMsgId() {
        String prefix = LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUMMDDHHMISS);
        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.char.string", ORDER_ID_RANDOM);
        properties.setProperty("kaptcha.textproducer.char.length", MSG_ID_RANDOM_LENGTH);
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        String randomSubfix = defaultKaptcha.createText();
        
        return prefix + randomSubfix;
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
