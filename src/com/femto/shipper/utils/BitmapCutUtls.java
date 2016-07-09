package com.femto.shipper.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;

public class BitmapCutUtls {
	
	private final static String videopath = Environment.getExternalStorageDirectory() + "/uger/";
	
	private static List<String> cutPicList;//剪裁后的图片的list路径
	
	public static List<String> ForPicList(ArrayList<String> PhotosPathList)
	{
		cutPicList = new ArrayList<String>();
		for (int i = 0; i < PhotosPathList.size(); i++) {
			float yaso = 0;
			File file = new File(PhotosPathList.get(i));
			LogUtils.i("裁剪和压缩前文件大小:"+file.length());
			if (file.length() > 2000000) {//大于2M
				yaso = 0.3f;
			} else if (file.length() > 1000000) {//大于1M
				yaso = 0.5f;
			} else {
				yaso = 0.8f;
			}
			Bitmap resize_img = resize_img(BitmapFactory.decodeFile(PhotosPathList.get(i)), yaso);
			File saveMyBitmap = saveMyBitmap("image" + i + ".jpg",resize_img);
			LogUtils.i("裁剪和压缩后文件大小:"+saveMyBitmap.length());
			
			cutPicList.add(saveMyBitmap.getPath().toString());
		}
		return cutPicList;// 返回压缩图片后的 路径
	}
	
	// 将压缩的bitmap保存到sdcard卡临时文件夹img_interim，用于上传
	public static File saveMyBitmap(String filename, Bitmap bit) {
		LogUtils.i("质量压缩前的 宽高:"+bit.getWidth()+";"+bit.getHeight());
		File dir = new File(videopath);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File f = new File(videopath + filename);
		try {
			f.createNewFile();
			FileOutputStream fOut = null;
			fOut = new FileOutputStream(f);
			bit.compress(Bitmap.CompressFormat.JPEG,90, fOut);
			fOut.flush();
			fOut.close();
		} catch (IOException e1) {
			f = null;
			e1.printStackTrace();
		}

		return f;
	}
	
	// 压缩bitmap
	private static Bitmap resize_img(Bitmap bitmap, float pc) {
		LogUtils.i("压缩前的宽高:"+bitmap.getWidth()+";"+bitmap.getHeight());
		Matrix matrix = new Matrix();
		matrix.postScale(pc, pc); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

		bitmap.recycle();
		bitmap = null;
		System.gc();

		int width = resizeBmp.getWidth();
		int height = resizeBmp.getHeight();
		LogUtils.i("压缩后的 宽高:"+ width+";"+height);
		return resizeBmp;
	}
	
	
	public static int computeInitialSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {

		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 :

		(int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));

		int upperBound = (minSideLength == -1) ? 128 :

		(int) Math.min(Math.floor(w / minSideLength),

		Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {

			return lowerBound;

		}

		if ((maxNumOfPixels == -1) &&

		(minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}
}
