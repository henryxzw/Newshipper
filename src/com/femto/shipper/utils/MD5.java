package com.femto.shipper.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年12月7日 下午4:21:55
 * @version 1.0
 */
public final class MD5 {
	

	
//	=============
	
    private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5',  
        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
	private static String bytesToHex(byte[] bytes) {  
	    StringBuffer sb = new StringBuffer();  
	    int t;  
	    for (int i = 0; i < 16; i++) {  
	        t = bytes[i];  
	        if (t < 0)  
	            t += 256;  
	        sb.append(hexDigits[(t >>> 4)]);  
	        sb.append(hexDigits[(t % 16)]);  
	    }  
	    return sb.toString();  
	}  
	public static String md5(String input) throws Exception {  
	    return code(input, 32);  
	}  
	public static String code(String input, int bit) throws Exception {  
	    try {  
	        MessageDigest md = MessageDigest.getInstance(System.getProperty(  
	                "MD5.algorithm", "MD5"));  
	        if (bit == 16)  
	            return bytesToHex(md.digest(input.getBytes("utf-8")))  
	                    .substring(8, 24);  
	        return bytesToHex(md.digest(input.getBytes("utf-8")));  
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	        throw new Exception("Could not found MD5 algorithm.", e);  
	    }  
	}  
	public static String md5_3(String b) throws Exception{  
	    MessageDigest md = MessageDigest.getInstance(System.getProperty(  
	            "MD5.algorithm", "MD5"));  
	    byte[] a = md.digest(b.getBytes());  
	    a = md.digest(a);  
	    a = md.digest(a);  
	      
	    return bytesToHex(a);  
	} 
	/**
	 * MD5加密方法
	 * @param str 明文
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String getMD5(String str) throws Exception{
		/** 创建MD5加密对象 */
		MessageDigest md = MessageDigest.getInstance("MD5");
		/** 进行加密 */
		md.update(str.getBytes());
		/** 获取加密后的字节数组  长度16 */
		byte[] md5Bytes = md.digest();
		
		String res = "";
		/**  
		 * 将16位长度的字节数组转化成32位长度的字符串 
		 * (将其中的一位转化成16进制的二位，不够两位前面补零) 
		 */
		for (int i = 0; i < md5Bytes.length; i++){
			int temp = md5Bytes[i] & 0xFF;
			if (temp <= 0xf){
				res += "0";
			}
			res += Integer.toHexString(temp);
		}
		return res;
	}
}