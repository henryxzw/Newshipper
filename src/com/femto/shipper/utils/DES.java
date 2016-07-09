package com.femto.shipper.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import android.util.Base64;


public class DES {
	
	

//	 public final static String DES_KEY_STRING = "ABSujsuu";
     
	    public static String encrypt(String message) throws Exception {
	        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	 
	        DESKeySpec desKeySpec = new DESKeySpec(password.getBytes("UTF-8"));
	 
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
	        IvParameterSpec iv = new IvParameterSpec(password.getBytes("UTF-8"));
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
	 
	        return encodeBase64(cipher.doFinal(message.getBytes("UTF-8")));
	    }
	 
	    public static String decrypt(String message, String key) throws Exception {
	 
	        byte[] bytesrc = decodeBase64(message);//convertHexString(message);
	        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
	        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
	 
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
	 
	        byte[] retByte = cipher.doFinal(bytesrc);
	        return new String(retByte);
	    }
	 
	    public static byte[] convertHexString(String ss) {
	        byte digest[] = new byte[ss.length() / 2];
	        for (int i = 0; i < digest.length; i++) {
	            String byteString = ss.substring(2 * i, 2 * i + 2);
	            int byteValue = Integer.parseInt(byteString, 16);
	            digest[i] = (byte) byteValue;
	        }
	 
	        return digest;
	    }
	 
	    public static String toHexString(byte b[]) {
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < b.length; i++) {
	            String plainText = Integer.toHexString(0xff & b[i]);
	            if (plainText.length() < 2)
	                plainText = "0" + plainText;
	            hexString.append(plainText);
	        }
	 
	        return hexString.toString();
	    }
	 
	     
	    public static String encodeBase64(byte[] b) {//Base64.encodeToString(b, Base64.DEFAULT);
	        return Base64.encodeToString(b, Base64.NO_WRAP);
	    }
	     
	    public static byte[] decodeBase64(String base64String) {//Base64.decode(base64String, Base64.DEFAULT);
	        return Base64.decode(base64String, Base64.NO_WRAP);
	    }
	    
	
//	=================================
	final static String password = "12345678";
	/**
	 * 加密
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static byte[] DESEncode(byte[] datasource) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//DES
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
}
