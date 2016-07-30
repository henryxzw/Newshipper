package com.femto.shipper.method;

public class MethodConfig {
	/**
	 * 去除地址中的 & ，并且保留 & 后的字段
	 * @param value
	 * @return
	 */
	public static String GetAddressExAnd(String value)
	{
		int index = value.indexOf("&");
		return value.substring(index+1);
	}

}
