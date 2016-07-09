package com.femto.shipper.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Base64;
import android.widget.Toast;

import com.femto.shipper.activitya.util.Auth;
import com.google.gson.Gson;

@SuppressLint({ "SimpleDateFormat", "ShowToast" })
public class ToolUtils {

	/**
	 * �Ƚ�ʱ��1�Ƿ���ʱ��2֮ǰ
	 * 
	 * @param date1
	 *            ����"2018-05-01"
	 * @param date2
	 *            ���� "2019-05-03"
	 * @return
	 */
	public static String Token() {
		// Auth auth = Auth.create("a00aWOwjACeeasiSGMXz57WsrOYowyMsboSl8Etu",
		// "rxV7gM6Ph2ZD267uLysUp-XJ_5QxrdAyZDwc6iDi");
		Auth auth = Auth.create(Net.ak, Net.sk);
		String token = auth.uploadToken(Net.zyname, null, 1000 * 3600 * 24 * 12
				* 10, null);
		return token;
	}

	public static String Timea() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date dataa = new Date(System.currentTimeMillis());
		String rime = sdFormat.format(dataa);
		return rime;
	}

	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] file = baos.toByteArray();
		return file;
	}

	public static Bitmap Piczoom(Bitmap bmp, int width, int height) {
		int bmpWidth = bmp.getWidth();
		int bmpHeght = bmp.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale((float) width / bmpWidth, (float) height / bmpHeght);
		return Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeght, matrix, true);
	}

	@SuppressLint("SimpleDateFormat")
	public static boolean date1BeforeDate2(String date1, String date2) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = fmt.parse(date1);
			Date dt2 = fmt.parse(date2);
			return dt1.before(dt2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * �����������������(��γ��)����
	 * 
	 * @param long1
	 *            ��һ�㾭��
	 * @param lat1
	 *            ��һ��γ��
	 * @param long2
	 *            �ڶ��㾭��
	 * @param lat2
	 *            �ڶ���γ��
	 * @return ���ؾ��� ��λ����
	 */
	public static double Distance(double long1, double lat1, double long2,
			double lat2) {
		double a, b, R;
		R = 6378137; // ����뾶
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2
				* R
				* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
						* Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	public static String DownloadDemo(String namekey) {
		Auth auth = Auth.create(Net.ak, Net.sk);
		String URL = Net.newpicturl + namekey;
		String downloadRUL = auth.privateDownloadUrl(URL, 1000 * 3600 * 24 * 12
				* 10);
		return downloadRUL;
	}

	// �����̵�URL���� ����encryptDES
	@SuppressWarnings("rawtypes")
	public static String JiaMi(Map map) {
		String json = new Gson().toJson(map);
		String encodeToString = Base64.encodeToString(json.getBytes(),
				Base64.NO_WRAP);
		String encryptDES = null;
		try {
			encryptDES = DES.encrypt(encodeToString);
			String md5 = MD5.code(encryptDES, 16);
			encryptDES = encryptDES + md5;
			// LogUtils.i("MD5��֤" + md5);
			// LogUtils.i("�����Ĳ�des����:" + encryptDES);
			return encryptDES;
		} catch (Exception e) {
			LogUtils.i("���ܴ���");
		}
		return null;
	}

	/**
	 * �ж��ֻ������ʽ�Ƿ�Ϸ�
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((1[0-9][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * �ж������Ƿ�Ϸ�
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //��ƥ��
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// ����ƥ��
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * ��������λС��
	 * 
	 * @param fenShu
	 * @return
	 */
	public static String fenShuBaoLiuLiuWeiXiaoShu(String fenShu) {
		double x = Double.parseDouble(fenShu);
		String result = new DecimalFormat("0.000000").format(x);
		return result;
	}

	/**
	 * ��������λС��
	 * 
	 * @param fenShu
	 * @return
	 */
	public static String fenShuBaoYiLiuWeiXiaoShu(String fenShu) {
		double x = Double.parseDouble(fenShu);
		String result = new DecimalFormat("0.0").format(x);
		return result;
	}

	/**
	 * ��������λС��
	 * 
	 * @param fenShu
	 * @return
	 */
	public static String fenShuBaoErWeiXiaoShu(String fenShu) {
		double x = Double.parseDouble(fenShu);
		String result = new DecimalFormat("0.00").format(x);
		return result;
	}

	// �����ַ��� ��ȡ�ַ����е������ַ�
	public static List<String> JieQuZiFuChuan(String zifuchuan,
			String jiequfuhao) {

		List<String> list = new ArrayList<String>();
		StringTokenizer token = new StringTokenizer(zifuchuan, jiequfuhao);
		while (token.hasMoreTokens()) {
			list.add(token.nextToken());
		}

		return list;
	}

	/**
	 * ����绰
	 * 
	 * @param context
	 * @param phone
	 */
	public static void Phone(Context context, String phone) {
		try {
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ phone));// +dingDan.dianHua
			context.startActivity(intent);
		} catch (Exception e) {
			Toast.makeText(context, "�������,�Ժ�����", 0).show();
		}
	}

	// �Ƚ����ָ�ʽ��ʱ�� ��ǰ˳��
	public static boolean date1BeforeDate3(String date1, String date2) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");
		try {
			Date dt1 = fmt.parse(date1);
			Date dt2 = fmt.parse(date2);
			return dt1.before(dt2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * �õ�������ʱ��
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static String getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		Date time = now.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy��MM��dd�� HH:mm");
		String format = simpleDateFormat.format(time);
		return format;
	}

	/**
	 * �õ���Сʱ֮���ʱ��
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static String getDateHourAfter(Date d, int hour) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
		Date time = now.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy��MM��dd�� HH:mm");
		String format = simpleDateFormat.format(time);
		return format;
	}

	public static boolean panduanteshuzhifu(String str) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~��@#��%����&*��������+|{}������������������������]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return false;
		}
		return true;
	}

	public static String fengbujiequ(String fenShu) {
		double x = Double.parseDouble(fenShu);
		String result = new DecimalFormat("0").format(x);
		return result;
	}

	// /����ȫ�����˶೤ʱ��(����)
	@SuppressWarnings("unused")
	private static String shijiancha(String nowtime, String testtime) {
		try {
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ʽ��ʱ��
			// ����ʱ��Сʱ��
			long result = (d.parse(testtime).getTime() - d.parse(nowtime)
					.getTime()) / 3600000;
			// /��������ķ���
			long a = (d.parse(testtime).getTime() - d.parse(nowtime).getTime()) / 60000 % 60;
			String time = String.valueOf(result * 60 + a);
			return time;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		int options = 100;
		while (baos.toByteArray().length / 1024 > 60) {
			baos.reset();
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
			options -= 10;
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}
}
