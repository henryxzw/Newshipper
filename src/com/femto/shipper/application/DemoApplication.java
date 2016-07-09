/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.femto.shipper.application;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import cn.jpush.android.api.JPushInterface;

import com.baidu.mapapi.SDKInitializer;
import com.easemob.EMCallBack;
import com.femto.shipper.R;
import com.femto.shipper.utils.LogUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class DemoApplication extends Application {

	public static Context applicationContext;
	private static DemoApplication instance;
	// login user name
	public final String PREF_USERNAME = "username";

	/**
	 * 褰撳墠鐢ㄦ埛nickname,涓轰簡鑻规灉鎺ㄩ�佷笉鏄痷serid鑰屾槸鏄电О
	 */
	public static String currentUserNick = "";
	public static DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();
	public HttpUtils http;
	public DisplayImageOptions options;
	public String userPhone;
	public String password;

	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext = this;
		instance = this;
		LogUtils.i("鍒濆鍖朼pplication");
		/**
		 * this function will initialize the HuanXin SDK
		 * 
		 * @return boolean true if caller can continue to call HuanXin related
		 *         APIs after calling onInit, otherwise false.
		 * 
		 *         鐜俊鍒濆鍖朣DK甯姪鍑芥暟
		 *         杩斿洖true濡傛灉姝ｇ‘鍒濆鍖栵紝鍚﹀垯false锛屽鏋滆繑鍥炰负false锛岃鍦ㄥ悗缁
		 *         殑璋冪敤涓笉瑕佽皟鐢ㄤ换浣曞拰鐜俊鐩稿叧鐨勪唬鐮�
		 * 
		 *         for example: 渚嬪瓙锛�
		 * 
		 *         public class DemoHXSDKHelper extends HXSDKHelper
		 * 
		 *         HXHelper = new DemoHXSDKHelper();
		 *         if(HXHelper.onInit(context)){ // do HuanXin related work }
		 */

		http = new HttpUtils(10000);
		initImageLoader(instance);
		intOptions();
		/** 鍒濆鍖栫櫨搴﹀湴鍥� **/
		initBaiduMap();
		hxSDKHelper.onInit(applicationContext);
		JPushInterface.init(this); // 鍒濆鍖� JPush

	}

	public static DemoApplication getInstance() {
		return instance;
	}

	/**
	 * 鍒濆鍖栫櫨搴﹀湴鍥�
	 */
	private void initBaiduMap() {
		// 鍦ㄤ娇鐢⊿DK鍚勭粍浠朵箣鍓嶅垵濮嬪寲context淇℃伅锛屼紶鍏pplicationContext
		// 娉ㄦ剰璇ユ柟娉曡鍐峴etContentView鏂规硶涔嬪墠瀹炵幇
		SDKInitializer.initialize(getApplicationContext());
	}

	public void doPost(String url, RequestParams params,
			final RequestCallBack<String> callBack) {
		http.configTimeout(3000);
		http.configSoTimeout(3000);
		http.configCurrentHttpCacheExpiry(1);// //1绉掍箣鍚庢竻绌虹紦瀛�
		http.send(HttpMethod.POST, url, params, callBack);
	}

	// GET璇锋眰
	public void doGet_kuaishu(String url,
			RequestCallBack<String> requestCallBack) {
		http.configTimeout(5000);
		http.configSoTimeout(5000);
		http.configCurrentHttpCacheExpiry(1);
		http.send(HttpMethod.GET, url, requestCallBack);
	}

	// GET璇锋眰
	public void doGet(String url, RequestCallBack<String> requestCallBack) {
		http.configTimeout(5000);
		http.configSoTimeout(5000);
		http.configCurrentHttpCacheExpiry(1000 * 60);
		http.send(HttpMethod.GET, url, requestCallBack);
	}

	public void doget(String url, RequestCallBack<String> requestCallBack) {
		http.configTimeout(5000);
		http.configSoTimeout(5000);
		http.configCurrentHttpCacheExpiry(1);
		http.send(HttpMethod.GET, url, requestCallBack);
	}

	public static void initImageLoader(Context context) {
		File cacheDir;
		if (Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			System.out.println("zuo===SD鍗″瓨鍦�");
			File sdcardDir = Environment.getExternalStorageDirectory();
			// 寰楀埌涓�涓矾寰勶紝鍐呭鏄痵dcard鐨勬枃浠跺す璺緞鍜屽悕瀛�
			String path = sdcardDir.getPath() + "/cardImages";
			File path1 = new File(path);
			cacheDir = new File(Environment.getExternalStorageDirectory()
					.getPath() + "/cardImages");
			if (!cacheDir.exists()) {
				// 鑻ヤ笉瀛樺湪锛屽垱寤虹洰褰曪紝鍙互鍦ㄥ簲鐢ㄥ惎鍔ㄧ殑鏃跺�欏垱寤�
				cacheDir.mkdirs();
			}

		} else {
			cacheDir = StorageUtils.getOwnCacheDirectory(context,
					"imageloader/Cache");
		}
		// diskCacheExtraOptions
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.memoryCacheExtraOptions(480, 800)
				// maxwidth, max height锛屽嵆淇濆瓨鐨勬瘡涓紦瀛樻枃浠剁殑鏈�澶ч暱瀹�
				.threadPoolSize(3)

				// default = device screen dimensions
				// .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75,
				// null)
				// 绾跨▼姹犲唴鍔犺浇鐨勬暟閲�
				.threadPriority(2)
				.denyCacheImageMultipleSizesInMemory()
				// .memoryCache(new UsingFreqLimitedMemoryCache(5 * 1024 *
				// 1024))
				// You can pass your own memory cache
				// implementation/浣犲彲浠ラ�氳繃鑷繁鐨勫唴瀛樼紦瀛樺疄鐜�
				// .memoryCacheSize(5 * 1024 * 1024)
				.discCacheSize(200 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 灏嗕繚瀛樼殑鏃跺�欑殑URI鍚嶇О鐢∕D5 鍔犲瘑
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(1000)
				// 缂撳瓨鐨勬枃浠舵暟閲�
				.discCache(new UnlimitedDiscCache(cacheDir))
				// 鑷畾涔夌紦瀛樿矾寰�
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout
																				// (5
																				// s),
																				// readTimeout
																				// (30
																				// s)瓒呮椂鏃堕棿
				.writeDebugLogs() // Remove for releaseapp
				.build();// 寮�濮嬫瀯寤�
		ImageLoader.getInstance().init(config);
	}

	public void intOptions() {
		options = new DisplayImageOptions.Builder()
		// 璁剧疆鍥剧墖鍦ㄤ笅杞芥湡闂存樉绀虹殑鍥剧墖
				.showStubImage(R.drawable.morentouxiang)
				// 璁剧疆鍥剧墖鍔犺浇/瑙ｇ爜杩囩▼涓敊璇椂鍊欐樉绀虹殑鍥剧墖
				.showImageOnFail(R.drawable.morentouxiang)
				// 璁剧疆鍥剧墖Uri涓虹┖鎴栨槸閿欒鐨勬椂鍊欐樉绀虹殑鍥剧墖
				.showImageForEmptyUri(R.drawable.morentouxiang)
				// 璁剧疆涓嬭浇鐨勫浘鐗囨槸鍚︾紦瀛樺湪鍐呭瓨涓�
				.cacheInMemory(true) // 鍔犺浇鍥剧墖鏃朵細鍦ㄥ唴瀛樹腑鍔犺浇缂撳瓨
				.cacheOnDisc(true) // 鍔犺浇鍥剧墖鏃朵細鍦ㄧ鐩樹腑鍔犺浇缂撳瓨
				/**
				 * 璁剧疆鍥剧墖缂╂斁鏂瑰紡锛� EXACTLY :鍥惧儚灏嗗畬鍏ㄦ寜姣斾緥缂╁皬鍒扮洰鏍囧ぇ灏�
				 * EXACTLY_STRETCHED:鍥剧墖浼氱缉鏀惧埌鐩爣澶у皬瀹屽叏
				 * IN_SAMPLE_INT:鍥惧儚灏嗚浜屾閲囨牱鐨勬暣鏁板��
				 * IN_SAMPLE_POWER_OF_2:鍥剧墖灏嗛檷浣
				 * �2鍊嶏紝鐩村埌涓嬩竴鍑忓皯姝ラ锛屼娇鍥惧儚鏇村皬鐨勭洰鏍囧ぇ灏� NONE:鍥剧墖涓嶄細璋冩暣
				 ***/
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				// 璁剧疆鍥剧墖鐨勮В鐮佺被鍨�
				.bitmapConfig(Bitmap.Config.RGB_565)
				// 璁剧疆鍥剧墖涓嬭浇鍓嶇殑寤惰繜
				.delayBeforeLoading(100)
				// delayInMillis涓轰綘璁剧疆鐨勫欢杩熸椂闂�
				// 璁剧疆鍥剧墖鍔犲叆缂撳瓨鍓嶏紝瀵筨itmap杩涜璁剧疆
				// .preProcessor(BitmapProcessor preProcessor)

				/**
				 * 鍥剧墖鏄剧ず鏂瑰紡锛� RoundedBitmapDisplayer锛坕nt roundPixels锛夎缃渾瑙掑浘鐗�
				 * FakeBitmapDisplayer锛堬級杩欎釜绫讳粈涔堥兘娌″仛 FadeInBitmapDisplayer锛坕nt
				 * durationMillis锛夎缃浘鐗囨笎鏄剧殑鏃堕棿 銆�銆�銆�銆�
				 * *銆�SimpleBitmapDisplayer()姝ｅ父鏄剧ず涓�寮犲浘鐗�
				 **/
				.displayer(new FadeInBitmapDisplayer(1000))// 娓愭樉--璁剧疆鍥剧墖娓愭樉鐨勬椂闂�
				.build();
	}

	// ======================================
	/**
	 * 鑾峰彇褰撳墠鐧婚檰鐢ㄦ埛鍚�
	 * 
	 * @return
	 */
	public String getUserName() {
		return hxSDKHelper.getHXId();
	}

	/**
	 * 鑾峰彇瀵嗙爜
	 * 
	 * @return
	 */
	public String getPassword() {
		return hxSDKHelper.getPassword();
	}

	/**
	 * 璁剧疆鐢ㄦ埛鍚�
	 * 
	 * @param user
	 */
	public void setUserName(String username) {
		hxSDKHelper.setHXId(username);
	}

	/**
	 * 璁剧疆瀵嗙爜 涓嬮潰鐨勫疄渚嬩唬鐮� 鍙槸demo锛屽疄闄呯殑搴旂敤涓渶瑕佸姞password 鍔犲瘑鍚庡瓨鍏� preference
	 * 鐜俊sdk 鍐呴儴鐨勮嚜鍔ㄧ櫥褰曢渶瑕佺殑瀵嗙爜锛屽凡缁忓姞瀵嗗瓨鍌ㄤ簡
	 * 
	 * @param pwd
	 */
	public void setPassword(String pwd) {
		hxSDKHelper.setPassword(pwd);
	}

	/**
	 * 閫�鍑虹櫥褰�,娓呯┖鏁版嵁
	 */
	public void logout(final boolean isGCM, final EMCallBack emCallBack) {
		// 鍏堣皟鐢╯dk logout锛屽湪娓呯悊app涓嚜宸辩殑鏁版嵁
		hxSDKHelper.logout(isGCM, emCallBack);
	}
}
