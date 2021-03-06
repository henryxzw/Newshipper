package com.femto.shipper.activitya.util;

import java.util.Collection;

public final class StringUtils {
	private StringUtils() {
	}

	public static String join(Object[] array, String sep) {
		return join(array, sep, null);
	}

	@SuppressWarnings("rawtypes")
	public static String join(Collection list, String sep) {
		return join(list, sep, null);
	}

	@SuppressWarnings("rawtypes")
	public static String join(Collection list, String sep, String prefix) {
		Object[] array = list == null ? null : list.toArray();
		return join(array, sep, prefix);
	}

	public static String join(Object[] array, String sep, String prefix) {
		if (array == null) {
			return "";
		}

		int arraySize = array.length;

		if (arraySize == 0) {
			return "";
		}

		if (sep == null) {
			sep = "";
		}

		if (prefix == null) {
			prefix = "";
		}

		StringBuilder buf = new StringBuilder(prefix);
		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(sep);
			}
			buf.append(array[i] == null ? "" : array[i]);
		}
		return buf.toString();
	}

	public static String jsonJoin(String[] array) {
		int arraySize = array.length;
		int bufSize = arraySize * (array[0].length() + 3);
		StringBuilder buf = new StringBuilder(bufSize);
		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(',');
			}

			buf.append('"');
			buf.append(array[i]);
			buf.append('"');
		}
		return buf.toString();
	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || "".equals(s);
	}

	public static boolean inStringArray(String s, String[] array) {
		for (String x : array) {
			if (x.equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static byte[] utf8Bytes(String data) {
		return data.getBytes(Config.UTF_8);
	}

	public static String utf8String(byte[] data) {
		return new String(data, Config.UTF_8);
	}
}