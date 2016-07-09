package com.femto.shipper.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;

/*
 * 常量类，工具类
 */
public class Utils
{
	public static final String url = "http://122.114.57.29:8002";
	public static final String imgSDUrl = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/subu/";
	static
	{
		File file = new File(imgSDUrl);
		if (!file.exists())
		{
			file.mkdir();
		}
	}

	// 截图最后截后字符，得到图片名
	public static String imagename(final String Path)
	{
		String[] tt = Path.split("\\/");
		String imagename = Path.split("\\/")[tt.length - 1];
		return imagename;
	}

	// ///下载保存图片到subu
	public static void setimage(final String Path)
	{
		final String urlPath = Net.PICURL + Path;
		if (!new File(urlPath).exists()&&!new File(urlPath).isDirectory())
		{
			new Thread()
			{
				public void run()
				{
					try
					{
						String[] tt = Path.split("\\/");
						String imagename = Path.split("\\/")[tt.length - 1];// 图片名
						LogUtils.i("========Utils=============" + imagename);
						URL url = new URL(urlPath);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setConnectTimeout(6 * 1000); // 注意要设置超时，设置时间不要超过10秒，避免被android系统回收
						if (conn.getResponseCode() != 200)
							throw new RuntimeException("请求url失败");
						InputStream inSream = conn.getInputStream();
						// 把图片保存到项目的根目录
						readAsFile(inSream, new File(Environment.getExternalStorageDirectory() + "/subu/" + imagename));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				};
			}.start();

		}

	}

	public static void readAsFile(InputStream inSream, File file) throws Exception
	{
		FileOutputStream outStream = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inSream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inSream.close();
	}

	// 获得圆角图片的方法
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx, float roundPy)
	{

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawRoundRect(rectF, roundPx, roundPy, paint);
		// paint.setStrokeCap(Paint.Style.STROKE);
		paint.setXfermode(new PorterDuffXfermode(Mode.MULTIPLY));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

}
