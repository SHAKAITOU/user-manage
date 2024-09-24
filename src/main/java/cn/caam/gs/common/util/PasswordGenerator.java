package cn.caam.gs.common.util;

import java.security.SecureRandom;

public class PasswordGenerator {
//	 private static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//	 private static final String CHAR_SET = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnpqrstuvwxyz123456789";
	private static final String CHAR_SET = "0123456789";
	    private static final int PASSWORD_LENGTH = 8;
	 
	    public static String generatePassword() {
	        SecureRandom random = new SecureRandom();
	        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
	        for (int i = 0; i < PASSWORD_LENGTH; i++) {
	            int randomIndex = random.nextInt(CHAR_SET.length());
	            password.append(CHAR_SET.charAt(randomIndex));
	        }
	        return password.toString();
	    }
	 
	    public static void main(String[] args) {
	        String password = generatePassword();
	        System.out.println("Generated Password: " + password);
	    }
}
