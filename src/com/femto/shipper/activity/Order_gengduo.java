package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.Order_gengduoBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

public class Order_gengduo extends BaseActivity implements OnClickListener {
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private LinearLayout orderE_gaitupian1, orderE_gaitupian2,
			orderE_gaitupian3, orderE_gaitupian4, orderE_gaitupian5,
			orderE_gaitupian6, orderE_gaitupian7;
	private RelativeLayout orderE_ll1, orderE_ll2, orderE_ll3, orderE_ll4,
			orderE_ll5, orderE_ll6, orderE_ll7;
	private ImageView orderE_tupianA1, orderE_tupianB1, orderE_tupianC1,
			orderE_tupianD1;
	private ImageView orderE_tupianA2, orderE_tupianB2, orderE_tupianC2,
			orderE_tupianD2;
	private ImageView orderE_tupianA3, orderE_tupianB3, orderE_tupianC3,
			orderE_tupianD3;
	private ImageView orderE_tupianA4, orderE_tupianB4, orderE_tupianC4,
			orderE_tupianD4;
	private ImageView orderE_tupianA5, orderE_tupianB5, orderE_tupianC5,
			orderE_tupianD5;
	private ImageView orderE_tupianA6, orderE_tupianB6, orderE_tupianC6,
			orderE_tupianD6;
	private ImageView orderE_tupianA7, orderE_tupianB7, orderE_tupianC7,
			orderE_tupianD7;
	private ImageView image_tu1, image_tu2, image_tu3, image_tu4, image_tu5,
			image_tu6, image_tu7;
	private TextView orderE_weizhi1, orderE_weizhi2, orderE_weizhi3,
			orderE_weizhi4, orderE_weizhi5, orderE_weizhi6, orderE_weizhi7;
	private TextView orderE_sijname1, orderE_sijname2, orderE_sijname3,
			orderE_sijname4, orderE_sijname5, orderE_sijname6, orderE_sijname7;
	private TextView orderE_time1, orderE_time2, orderE_time3, orderE_time4,
			orderE_time5, orderE_time6, orderE_time7;
	private TextView orderE_sijiphone1, orderE_sijiphone2, orderE_sijiphone3,
			orderE_sijiphone4, orderE_sijiphone5, orderE_sijiphone6,
			orderE_sijiphone7;
	private LinearLayout OrderE_fankui;
	private TextView orderE_sijifankui;
	private String imageA1, imageB1, imageC1, imageD1;
	private String imageA2, imageB2, imageC2, imageD2;
	private String imageA3, imageB3, imageC3, imageD3;
	private String imageA4, imageB4, imageC4, imageD4;
	private String imageA5, imageB5, imageC5, imageD5;
	private String imageA6, imageB6, imageC6, imageD6;
	private String imageA7, imageB7, imageC7, imageD7;
	private String yid;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_gengduo);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		info();
		Intent it = getIntent();
		yid = it.getStringExtra("yid");
		getintent();

	}

	private void getintent() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "waybill_info_img");
		map.put("yid", yid);
		map.put("username", phonea);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ADDORDER + jiaMi; // 本工程的URL加密, 返回(string)encryptDES
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				dismissProgressDialog();
				showProgressDialog(getResources().getString(R.string.wlyc));
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				Order_gengduoBean Order = GsonUtils.json2Bean(arg0.result,
						Order_gengduoBean.class);// //用Gson转成json对象
				if (Order.status.equals("0")) {
					// ///////////////////////////////////起点////////////////////////////////////
					if (!Order.waybill_info_img.addr_start.equals("")) {
						orderE_weizhi1
								.setText(Order.waybill_info_img.addr_start
										.split("\\|")[0]);

						if (!Order.waybill_info_img.addr_start_img.equals("")) {
							if (Order.waybill_info_img.addr_start_img
									.split("\\|").length == 1) {
								imageA1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[0];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[0],
								// orderE_tupianA1,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.addr_start_img
												.split("\\|")[0],
										orderE_tupianA1, application.options);
							}
							if (Order.waybill_info_img.addr_start_img
									.split("\\|").length == 2) {
								imageA1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[0];
								imageB1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[1];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[0],
								// orderE_tupianA1,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[1],
								// orderE_tupianB1,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.addr_start_img
												.split("\\|")[0],
										orderE_tupianA1, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.addr_start_img
												.split("\\|")[1],
										orderE_tupianB1, application.options);
							}
							if (Order.waybill_info_img.addr_start_img
									.split("\\|").length == 3) {
								imageA1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[0];
								imageB1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[1];
								imageC1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[2];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[0],
								// orderE_tupianA1,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[1],
								// orderE_tupianB1,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[2],
								// orderE_tupianC1,
								// application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.addr_start_img
												.split("\\|")[0],
										orderE_tupianA1, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.addr_start_img
												.split("\\|")[1],
										orderE_tupianB1, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.addr_start_img
												.split("\\|")[2],
										orderE_tupianC1, application.options);
							}
							if (Order.waybill_info_img.addr_start_img
									.split("\\|").length == 4) {
								imageA1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[0];
								imageB1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[1];
								imageC1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[2];
								imageD1 = Order.waybill_info_img.addr_start_img
										.split("\\|")[3];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[0],
								// orderE_tupianA1,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[1],
								// orderE_tupianB1,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[2],
								// orderE_tupianC1,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.addr_start_img
								// .split("\\|")[3],
								// orderE_tupianD1,
								// application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.addr_start_img
																.split("\\|")[0]),
												orderE_tupianA1,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.addr_start_img
																.split("\\|")[1]),
												orderE_tupianB1,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.addr_start_img
																.split("\\|")[2]),
												orderE_tupianC1,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.addr_start_img
																.split("\\|")[3]),
												orderE_tupianD1,
												application.options);
							}

						} else {
							orderE_gaitupian1.setVisibility(View.GONE);
						}
						if (Order.waybill_info_img.addr_start_state.equals("0")) {
							image_tu1.setImageResource(R.drawable.wu);
						} else {
							image_tu1.setImageResource(R.drawable.se);
						}
						if (!Order.waybill_info_img.addr_start_time.equals("")) {
							String time = Order.waybill_info_img.addr_start_time;
							if (time.equals("0001-01-01 00:00:00")) {
								orderE_time1.setVisibility(View.GONE);
							}
							orderE_time1.setText(time.split("\\ ")[0]
									.split("\\-")[1]
									+ "-"
									+ time.split("\\ ")[0].split("\\-")[2]
									+ "  "
									+ time.split("\\ ")[1].split("\\:")[0]
									+ ":"
									+ time.split("\\ ")[1].split("\\:")[1]);
						}

						if (!Order.waybill_info_img.address_start_contact
								.equals("")) {
							orderE_sijname1
									.setText(Order.waybill_info_img.address_start_contact
											.split("\\|")[0]);
							orderE_sijiphone1
									.setText(Order.waybill_info_img.address_start_contact
											.split("\\|")[1]);
						} else {
							orderE_sijname1.setText("");
						}

					}
					// /////////////////////////////////////////终点/////////////////////////////////////
					// /////////////////////////////////////////终点//////////////////////////////
					if (!Order.waybill_info_img.address_end.equals("")) {
						orderE_weizhi7
								.setText(Order.waybill_info_img.address_end
										.split("\\|")[0]);

						if (!Order.waybill_info_img.address_end_img.equals("")) {
							if (Order.waybill_info_img.address_end_img
									.split("\\|").length == 1) {
								imageA7 = Order.waybill_info_img.address_end_img
										.split("\\|")[0];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[0],
								// orderE_tupianA7,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.address_end_img
												.split("\\|")[0],
										orderE_tupianA7, application.options);
							}
							if (Order.waybill_info_img.address_end_img
									.split("\\|").length == 2) {
								imageA7 = Order.waybill_info_img.address_end_img
										.split("\\|")[0];
								imageB7 = Order.waybill_info_img.address_end_img
										.split("\\|")[1];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[0],
								// orderE_tupianA7,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[1],
								// orderE_tupianB7,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.address_end_img
												.split("\\|")[0],
										orderE_tupianA7, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.address_end_img
												.split("\\|")[1],
										orderE_tupianB7, application.options);
							}
							if (Order.waybill_info_img.address_end_img
									.split("\\|").length == 3) {
								imageA7 = Order.waybill_info_img.address_end_img
										.split("\\|")[0];
								imageB7 = Order.waybill_info_img.address_end_img
										.split("\\|")[1];
								imageC7 = Order.waybill_info_img.address_end_img
										.split("\\|")[2];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[0],
								// orderE_tupianA7,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[1],
								// orderE_tupianB7,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[2],
								// orderE_tupianC7,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.address_end_img
												.split("\\|")[0],
										orderE_tupianA7, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.address_end_img
												.split("\\|")[1],
										orderE_tupianB7, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.address_end_img
												.split("\\|")[2],
										orderE_tupianC7, application.options);
							}
							if (Order.waybill_info_img.address_end_img
									.split("\\|").length == 4) {
								imageA7 = Order.waybill_info_img.address_end_img
										.split("\\|")[0];
								imageB7 = Order.waybill_info_img.address_end_img
										.split("\\|")[1];
								imageC7 = Order.waybill_info_img.address_end_img
										.split("\\|")[2];
								imageD7 = Order.waybill_info_img.address_end_img
										.split("\\|")[3];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[0],
								// orderE_tupianA7,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[1],
								// orderE_tupianB7,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[2],
								// orderE_tupianC7,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.address_end_img
								// .split("\\|")[3],
								// orderE_tupianD7,
								// application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.address_end_img
																.split("\\|")[0]),
												orderE_tupianA7,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.address_end_img
																.split("\\|")[1]),
												orderE_tupianB7,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.address_end_img
																.split("\\|")[2]),
												orderE_tupianC7,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.address_end_img
																.split("\\|")[3]),
												orderE_tupianD7,
												application.options);
							}

						} else {
							orderE_gaitupian7.setVisibility(View.GONE);
						}
						if (Order.waybill_info_img.address_end_state
								.equals("0")) {
							image_tu7.setImageResource(R.drawable.wu);
						} else {
							image_tu7.setImageResource(R.drawable.se);
						}
						if (!Order.waybill_info_img.address_end_time.equals("")) {
							String time = Order.waybill_info_img.address_end_time;
							if (time.equals("0001-01-01 00:00:00")) {
								orderE_time7.setVisibility(View.GONE);
							}
							// showToast("a" + time + "b");
							orderE_time7.setText(time.split("\\ ")[0]
									.split("\\-")[1]
									+ "-"
									+ time.split("\\ ")[0].split("\\-")[2]
									+ "  "
									+ time.split("\\ ")[1].split("\\:")[0]
									+ ":"
									+ time.split("\\ ")[1].split("\\:")[1]);
						}
						if (!Order.waybill_info_img.address_end_contact
								.equals("")) {

							orderE_sijname7
									.setText(Order.waybill_info_img.address_end_contact
											.split("\\|")[0]);
							orderE_sijiphone7
									.setText(Order.waybill_info_img.address_end_contact
											.split("\\|")[1]);
						} else {
							orderE_sijname7.setText("");
						}
					}
					// /////////////////////////////////////////中点站1//////////////////////////////
					if (!Order.waybill_info_img.address_midway1.equals("")) {
						orderE_weizhi2
								.setText(Order.waybill_info_img.address_midway1
										.split("\\|")[0]);

						if (!Order.waybill_info_img.midway1_img.equals("")) {
							if (Order.waybill_info_img.midway1_img.split("\\|").length == 1) {
								imageA2 = Order.waybill_info_img.midway1_img
										.split("\\|")[0];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[0],
								// orderE_tupianA2,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway1_img
												.split("\\|")[0],
										orderE_tupianA2, application.options);
							}
							if (Order.waybill_info_img.midway1_img.split("\\|").length == 2) {
								imageA2 = Order.waybill_info_img.midway1_img
										.split("\\|")[0];
								imageB2 = Order.waybill_info_img.midway1_img
										.split("\\|")[1];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[0],
								// orderE_tupianA2,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[1],
								// orderE_tupianB2,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway1_img
												.split("\\|")[0],
										orderE_tupianA2, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway1_img
												.split("\\|")[1],
										orderE_tupianB2, application.options);
							}
							if (Order.waybill_info_img.midway1_img.split("\\|").length == 3) {
								imageA2 = Order.waybill_info_img.midway1_img
										.split("\\|")[0];
								imageB2 = Order.waybill_info_img.midway1_img
										.split("\\|")[1];
								imageC2 = Order.waybill_info_img.midway1_img
										.split("\\|")[2];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[0],
								// orderE_tupianA2,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[1],
								// orderE_tupianB2,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[2],
								// orderE_tupianC2,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway1_img
												.split("\\|")[0],
										orderE_tupianA2, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway1_img
												.split("\\|")[1],
										orderE_tupianB2, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway1_img
												.split("\\|")[2],
										orderE_tupianC2, application.options);
							}
							if (Order.waybill_info_img.midway1_img.split("\\|").length == 4) {
								imageA2 = Order.waybill_info_img.midway1_img
										.split("\\|")[0];
								imageB2 = Order.waybill_info_img.midway1_img
										.split("\\|")[1];
								imageC2 = Order.waybill_info_img.midway1_img
										.split("\\|")[2];
								imageD2 = Order.waybill_info_img.midway1_img
										.split("\\|")[3];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[0],
								// orderE_tupianA2,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[1],
								// orderE_tupianB2,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[2],
								// orderE_tupianC2,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway1_img
								// .split("\\|")[3],
								// orderE_tupianD2,
								// application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway1_img
																.split("\\|")[0]),
												orderE_tupianA2,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway1_img
																.split("\\|")[1]),
												orderE_tupianB2,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway1_img
																.split("\\|")[2]),
												orderE_tupianC2,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway1_img
																.split("\\|")[3]),
												orderE_tupianD2,
												application.options);
							}

						} else {
							orderE_gaitupian2.setVisibility(View.GONE);
						}
						if (Order.waybill_info_img.midway1_state.equals("0")) {
							image_tu2.setImageResource(R.drawable.wu);
						} else {
							image_tu2.setImageResource(R.drawable.se);
						}
						if (!Order.waybill_info_img.midway1_time.equals("")) {
							String time = Order.waybill_info_img.midway1_time;
							if (time.equals("0001-01-01 00:00:00")) {
								orderE_time2.setVisibility(View.GONE);
							}
							orderE_time2.setText(time.split("\\ ")[0]
									.split("\\-")[1]
									+ "-"
									+ time.split("\\ ")[0].split("\\-")[2]
									+ "  "
									+ time.split("\\ ")[1].split("\\:")[0]
									+ ":"
									+ time.split("\\ ")[1].split("\\:")[1]);
						}

						if (!Order.waybill_info_img.address_midway1_contact
								.equals("")) {
							orderE_sijname2
									.setText(Order.waybill_info_img.address_midway1_contact
											.split("\\|")[0]);
							orderE_sijiphone2
									.setText(Order.waybill_info_img.address_midway1_contact
											.split("\\|")[1]);
						} else {
							orderE_sijname2.setText("");
						}

					} else {
						orderE_ll2.setVisibility(View.GONE);
					}
					// //////////////////////////////////////////////////////////////////////////////
					// /////////////////////////////////////////中点站2////////////////////////////////////////////////////////////
					if (!Order.waybill_info_img.address_midway2.equals("")) {
						orderE_weizhi3
								.setText(Order.waybill_info_img.address_midway2
										.split("\\|")[0]);

						if (!Order.waybill_info_img.midway2_img.equals("")) {
							if (Order.waybill_info_img.midway2_img.split("\\|").length == 1) {
								imageA3 = Order.waybill_info_img.midway2_img
										.split("\\|")[0];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[0],
								// orderE_tupianA3,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway2_img
												.split("\\|")[0],
										orderE_tupianA3, application.options);
							}
							if (Order.waybill_info_img.midway2_img.split("\\|").length == 2) {
								imageA3 = Order.waybill_info_img.midway2_img
										.split("\\|")[0];
								imageB3 = Order.waybill_info_img.midway2_img
										.split("\\|")[1];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[0],
								// orderE_tupianA3,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[1],
								// orderE_tupianB3,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway2_img
												.split("\\|")[0],
										orderE_tupianA3, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway2_img
												.split("\\|")[1],
										orderE_tupianB3, application.options);
							}
							if (Order.waybill_info_img.midway2_img.split("\\|").length == 3) {
								imageA3 = Order.waybill_info_img.midway2_img
										.split("\\|")[0];
								imageB3 = Order.waybill_info_img.midway2_img
										.split("\\|")[1];
								imageC3 = Order.waybill_info_img.midway2_img
										.split("\\|")[2];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[0],
								// orderE_tupianA3,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[1],
								// orderE_tupianB3,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[2],
								// orderE_tupianC3,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway2_img
												.split("\\|")[0],
										orderE_tupianA3, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway2_img
												.split("\\|")[1],
										orderE_tupianB3, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway2_img
												.split("\\|")[2],
										orderE_tupianC3, application.options);
							}
							if (Order.waybill_info_img.midway2_img.split("\\|").length == 4) {
								imageA3 = Order.waybill_info_img.midway2_img
										.split("\\|")[0];
								imageB3 = Order.waybill_info_img.midway2_img
										.split("\\|")[1];
								imageC3 = Order.waybill_info_img.midway2_img
										.split("\\|")[2];
								imageD3 = Order.waybill_info_img.midway2_img
										.split("\\|")[3];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[0],
								// orderE_tupianA3,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[1],
								// orderE_tupianB3,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[2],
								// orderE_tupianC3,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway2_img
								// .split("\\|")[3],
								// orderE_tupianD3,
								// application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway2_img
																.split("\\|")[0]),
												orderE_tupianA3,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway2_img
																.split("\\|")[1]),
												orderE_tupianB3,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway2_img
																.split("\\|")[2]),
												orderE_tupianC3,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway2_img
																.split("\\|")[3]),
												orderE_tupianD3,
												application.options);
							}

						} else {
							orderE_gaitupian3.setVisibility(View.GONE);
						}
						if (Order.waybill_info_img.midway2_state.equals("0")) {
							image_tu3.setImageResource(R.drawable.wu);
						} else {
							image_tu3.setImageResource(R.drawable.se);
						}
						if (!Order.waybill_info_img.midway2_time.equals("")) {
							String time = Order.waybill_info_img.midway2_time;
							if (time.equals("0001-01-01 00:00:00")) {
								orderE_time3.setVisibility(View.GONE);
							}
							orderE_time3.setText(time.split("\\ ")[0]
									.split("\\-")[1]
									+ "-"
									+ time.split("\\ ")[0].split("\\-")[2]
									+ "  "
									+ time.split("\\ ")[1].split("\\:")[0]
									+ ":"
									+ time.split("\\ ")[1].split("\\:")[1]);
						}

						if (!Order.waybill_info_img.address_midway2_contact
								.equals("")) {
							orderE_sijname3
									.setText(Order.waybill_info_img.address_midway2_contact
											.split("\\|")[0]);
							orderE_sijiphone3
									.setText(Order.waybill_info_img.address_midway2_contact
											.split("\\|")[1]);
						} else {
							orderE_sijname3.setText("");
							orderE_sijiphone3.setText("");
						}

					} else {
						orderE_ll3.setVisibility(View.GONE);
					}
					// ///////////////////////////////////////////////
					// /////////////////////////////////////////中点站3////////////////////////////////////////////////////////////
					if (!Order.waybill_info_img.address_midway3.equals("")) {
						orderE_weizhi4
								.setText(Order.waybill_info_img.address_midway3
										.split("\\|")[0]);

						if (!Order.waybill_info_img.midway3_img.equals("")) {
							if (Order.waybill_info_img.midway3_img.split("\\|").length == 1) {
								imageA4 = Order.waybill_info_img.midway3_img
										.split("\\|")[0];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[0],
								// orderE_tupianA4,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway3_img
												.split("\\|")[0],
										orderE_tupianA4, application.options);
							}
							if (Order.waybill_info_img.midway3_img.split("\\|").length == 2) {
								imageA4 = Order.waybill_info_img.midway3_img
										.split("\\|")[0];
								imageB4 = Order.waybill_info_img.midway3_img
										.split("\\|")[1];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[0],
								// orderE_tupianA4,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[1],
								// orderE_tupianB4,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway3_img
												.split("\\|")[0],
										orderE_tupianA4, application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway3_img
												.split("\\|")[1],
										orderE_tupianB4, application.options);
							}
							if (Order.waybill_info_img.midway3_img.split("\\|").length == 3) {
								imageA4 = Order.waybill_info_img.midway3_img
										.split("\\|")[0];
								imageB4 = Order.waybill_info_img.midway3_img
										.split("\\|")[1];
								imageC4 = Order.waybill_info_img.midway3_img
										.split("\\|")[2];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[0],
								// orderE_tupianA4,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[1],
								// orderE_tupianB4,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[2],
								// orderE_tupianC4,
								// application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway3_img
												.split("\\|")[0],
										orderE_tupianA4, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway3_img
												.split("\\|")[1],
										orderE_tupianB4, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway3_img
												.split("\\|")[2],
										orderE_tupianC4, application.options);
							}
							if (Order.waybill_info_img.midway3_img.split("\\|").length == 4) {
								imageA4 = Order.waybill_info_img.midway3_img
										.split("\\|")[0];
								imageB4 = Order.waybill_info_img.midway3_img
										.split("\\|")[1];
								imageC4 = Order.waybill_info_img.midway3_img
										.split("\\|")[2];
								imageD4 = Order.waybill_info_img.midway3_img
										.split("\\|")[3];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[0],
								// orderE_tupianA4,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[1],
								// orderE_tupianB4,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[2],
								// orderE_tupianC4,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway3_img
								// .split("\\|")[3],
								// orderE_tupianD4,
								// application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway3_img
																.split("\\|")[0]),
												orderE_tupianA4,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway3_img
																.split("\\|")[1]),
												orderE_tupianB4,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway3_img
																.split("\\|")[2]),
												orderE_tupianC4,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway3_img
																.split("\\|")[3]),
												orderE_tupianD4,
												application.options);
							}

						} else {
							orderE_gaitupian4.setVisibility(View.GONE);
						}
						if (Order.waybill_info_img.midway3_state.equals("0")) {
							image_tu4.setImageResource(R.drawable.wu);
						} else {
							image_tu4.setImageResource(R.drawable.se);
						}
						if (!Order.waybill_info_img.midway3_time.equals("")) {
							String time = Order.waybill_info_img.midway3_time;
							if (time.equals("0001-01-01 00:00:00")) {
								orderE_time4.setVisibility(View.GONE);
							}
							orderE_time4.setText(time.split("\\ ")[0]
									.split("\\-")[1]
									+ "-"
									+ time.split("\\ ")[0].split("\\-")[2]
									+ "  "
									+ time.split("\\ ")[1].split("\\:")[0]
									+ ":"
									+ time.split("\\ ")[1].split("\\:")[1]);
						}

						if (!Order.waybill_info_img.address_midway3_contact
								.equals("")) {
							orderE_sijname4
									.setText(Order.waybill_info_img.address_midway3_contact
											.split("\\|")[0]);
							orderE_sijiphone4
									.setText(Order.waybill_info_img.address_midway3_contact
											.split("\\|")[1]);
						} else {
							orderE_sijname4.setText("");
							orderE_sijiphone4.setText("");
						}

					} else {
						orderE_ll4.setVisibility(View.GONE);
					}
					// /////////////////////////////////////////中点站4////////////////////////////////////////////////////////////
					if (!Order.waybill_info_img.address_midway4.equals("")) {
						orderE_weizhi5
								.setText(Order.waybill_info_img.address_midway4
										.split("\\|")[0]);

						if (!Order.waybill_info_img.midway4_img.equals("")) {
							if (Order.waybill_info_img.midway4_img.split("\\|").length == 1) {
								imageA5 = Order.waybill_info_img.midway4_img
										.split("\\|")[0];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[0],
								// orderE_tupianA5,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway4_img
												.split("\\|")[0],
										orderE_tupianA5, application.options);
							}
							if (Order.waybill_info_img.midway4_img.split("\\|").length == 2) {
								imageA5 = Order.waybill_info_img.midway4_img
										.split("\\|")[0];
								imageB5 = Order.waybill_info_img.midway4_img
										.split("\\|")[1];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[0],
								// orderE_tupianA5,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[1],
								// orderE_tupianB5,
								// application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway4_img
												.split("\\|")[0],
										orderE_tupianA5, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway4_img
												.split("\\|")[1],
										orderE_tupianB5, application.options);
							}
							if (Order.waybill_info_img.midway4_img.split("\\|").length == 3) {
								imageA5 = Order.waybill_info_img.midway4_img
										.split("\\|")[0];
								imageB5 = Order.waybill_info_img.midway4_img
										.split("\\|")[1];
								imageC5 = Order.waybill_info_img.midway4_img
										.split("\\|")[2];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[0],
								// orderE_tupianA5,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[1],
								// orderE_tupianB5,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[2],
								// orderE_tupianC5,
								// application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway4_img
												.split("\\|")[0],
										orderE_tupianA5, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway4_img
												.split("\\|")[1],
										orderE_tupianB5, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway4_img
												.split("\\|")[2],
										orderE_tupianC5, application.options);
							}
							if (Order.waybill_info_img.midway4_img.split("\\|").length == 4) {
								imageA5 = Order.waybill_info_img.midway4_img
										.split("\\|")[0];
								imageB5 = Order.waybill_info_img.midway4_img
										.split("\\|")[1];
								imageC5 = Order.waybill_info_img.midway4_img
										.split("\\|")[2];
								imageD5 = Order.waybill_info_img.midway4_img
										.split("\\|")[3];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[0],
								// orderE_tupianA5,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[1],
								// orderE_tupianB5,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[2],
								// orderE_tupianC5,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway4_img
								// .split("\\|")[3],
								// orderE_tupianD5,
								// application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway4_img
																.split("\\|")[0]),
												orderE_tupianA5,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway4_img
																.split("\\|")[1]),
												orderE_tupianB5,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway4_img
																.split("\\|")[2]),
												orderE_tupianC5,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway4_img
																.split("\\|")[3]),
												orderE_tupianD5,
												application.options);
							}

						} else {
							orderE_gaitupian5.setVisibility(View.GONE);
						}
						if (Order.waybill_info_img.midway4_state.equals("0")) {
							image_tu5.setImageResource(R.drawable.wu);
						} else {
							image_tu5.setImageResource(R.drawable.se);
						}
						if (!Order.waybill_info_img.midway4_time.equals("")) {
							String time = Order.waybill_info_img.midway4_time;
							if (time.equals("0001-01-01 00:00:00")) {
								orderE_time5.setVisibility(View.GONE);
							}
							orderE_time5.setText(time.split("\\ ")[0]
									.split("\\-")[1]
									+ "-"
									+ time.split("\\ ")[0].split("\\-")[2]
									+ "  "
									+ time.split("\\ ")[1].split("\\:")[0]
									+ ":"
									+ time.split("\\ ")[1].split("\\:")[1]);
						}

						if (!Order.waybill_info_img.address_midway4_contact
								.equals("")) {
							orderE_sijname5
									.setText(Order.waybill_info_img.address_midway4_contact
											.split("\\|")[0]);
							orderE_sijiphone5
									.setText(Order.waybill_info_img.address_midway4_contact
											.split("\\|")[1]);
						} else {
							orderE_sijname5.setText("");
							orderE_sijiphone5.setText("");
						}

					} else {
						orderE_ll5.setVisibility(View.GONE);
					}
					// ///////////////////////////////////////////////////////////////////////
					// /////////////////////////////////////////中点站5////////////////////////////////////////////////////////////
					if (!Order.waybill_info_img.address_midway5.equals("")) {
						orderE_weizhi6
								.setText(Order.waybill_info_img.address_midway5
										.split("\\|")[0]);

						if (!Order.waybill_info_img.midway5_img.equals("")) {
							if (Order.waybill_info_img.midway5_img.split("\\|").length == 1) {
								imageA6 = Order.waybill_info_img.midway5_img
										.split("\\|")[0];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[0],
								// orderE_tupianA6,
								// application.options);
								ImageLoader.getInstance().displayImage(
										Order.waybill_info_img.midway5_img
												.split("\\|")[0],
										orderE_tupianA6, application.options);
							}
							if (Order.waybill_info_img.midway5_img.split("\\|").length == 2) {
								imageA6 = Order.waybill_info_img.midway5_img
										.split("\\|")[0];
								imageB6 = Order.waybill_info_img.midway5_img
										.split("\\|")[1];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[0],
								// orderE_tupianA6,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[1],
								// orderE_tupianB6,
								// application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway5_img
												.split("\\|")[0],
										orderE_tupianA6, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway5_img
												.split("\\|")[1],
										orderE_tupianB6, application.options);
							}
							if (Order.waybill_info_img.midway5_img.split("\\|").length == 3) {
								imageA6 = Order.waybill_info_img.midway5_img
										.split("\\|")[0];
								imageB6 = Order.waybill_info_img.midway5_img
										.split("\\|")[1];
								imageC6 = Order.waybill_info_img.midway5_img
										.split("\\|")[2];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[0],
								// orderE_tupianA6,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[1],
								// orderE_tupianB6,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[2],
								// orderE_tupianC6,
								// application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway5_img
												.split("\\|")[0],
										orderE_tupianA6, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway5_img
												.split("\\|")[1],
										orderE_tupianB6, application.options);
								ImageLoader.getInstance().displayImage(

										Order.waybill_info_img.midway5_img
												.split("\\|")[2],
										orderE_tupianC6, application.options);
							}
							if (Order.waybill_info_img.midway5_img.split("\\|").length == 4) {
								imageA6 = Order.waybill_info_img.midway5_img
										.split("\\|")[0];
								imageB6 = Order.waybill_info_img.midway5_img
										.split("\\|")[1];
								imageC6 = Order.waybill_info_img.midway5_img
										.split("\\|")[2];
								imageD6 = Order.waybill_info_img.midway5_img
										.split("\\|")[3];
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[0],
								// orderE_tupianA6,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[1],
								// orderE_tupianB6,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[2],
								// orderE_tupianC6,
								// application.options);
								// ImageLoader
								// .getInstance()
								// .displayImage(
								// Net.PICURL
								// + Order.waybill_info_img.midway5_img
								// .split("\\|")[3],
								// orderE_tupianD6,
								// application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway5_img
																.split("\\|")[0]),
												orderE_tupianA6,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway5_img
																.split("\\|")[1]),
												orderE_tupianB6,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway5_img
																.split("\\|")[2]),
												orderE_tupianC6,
												application.options);
								ImageLoader
										.getInstance()
										.displayImage(
												ToolUtils
														.DownloadDemo(Order.waybill_info_img.midway5_img
																.split("\\|")[3]),
												orderE_tupianD6,
												application.options);
							}

						} else {
							orderE_gaitupian6.setVisibility(View.GONE);
						}
						if (Order.waybill_info_img.midway5_state.equals("0")) {
							image_tu6.setImageResource(R.drawable.wu);
						} else {
							image_tu6.setImageResource(R.drawable.se);
						}
						if (!Order.waybill_info_img.midway5_time.equals("")) {
							String time = Order.waybill_info_img.midway5_time;
							if (time.equals("0001-01-01 00:00:00")) {
								orderE_time6.setVisibility(View.GONE);
							}
							orderE_time6.setText(time.split("\\ ")[0]
									.split("\\-")[1]
									+ "-"
									+ time.split("\\ ")[0].split("\\-")[2]
									+ "  "
									+ time.split("\\ ")[1].split("\\:")[0]
									+ ":"
									+ time.split("\\ ")[1].split("\\:")[1]);
						}

						if (!Order.waybill_info_img.address_midway5_contact
								.equals("")) {
							orderE_sijname6
									.setText(Order.waybill_info_img.address_midway5_contact
											.split("\\|")[0]);
							orderE_sijiphone6
									.setText(Order.waybill_info_img.address_midway5_contact
											.split("\\|")[1]);
						} else {
							orderE_sijname6.setText("");
							orderE_sijiphone6.setText("");
						}

					} else {
						orderE_ll6.setVisibility(View.GONE);
					}

					// ///////////////////////////////////////////////////////////////////////////
					// ////判断有多少个装货地址
					int i = 0;
					int j = 0;
					if (!Order.waybill_info_img.addr_start.equals("")) {
						i = i + 1;
					}
					if (!Order.waybill_info_img.address_midway1.equals("")) {
						i = i + 1;
					}
					if (!Order.waybill_info_img.address_midway2.equals("")) {
						i = i + 1;
					}
					if (!Order.waybill_info_img.address_midway3.equals("")) {
						i = i + 1;
					}
					if (!Order.waybill_info_img.address_midway4.equals("")) {
						i = i + 1;
					}
					if (!Order.waybill_info_img.address_midway5.equals("")) {
						i = i + 1;
					}
					if (!Order.waybill_info_img.address_end.equals("")) {
						i = i + 1;
					}
					if (Order.waybill_info_img.addr_start_state.equals("1")) {
						j = j + 1;
					}
					if (Order.waybill_info_img.midway1_state.equals("1")) {
						j = j + 1;
					}
					if (Order.waybill_info_img.midway2_state.equals("1")) {
						j = j + 1;
					}
					if (Order.waybill_info_img.midway3_state.equals("1")) {
						j = j + 1;
					}
					if (Order.waybill_info_img.midway4_state.equals("1")) {
						j = j + 1;
					}
					if (Order.waybill_info_img.midway5_state.equals("1")) {
						j = j + 1;
					}
					if (Order.waybill_info_img.address_end_state.equals("1")) {
						j = j + 1;
					}
					// //////判断是否完成运单
					if (i == j) {
						orderE_sijifankui.setText("司机反馈: “"
								+ Order.waybill_info_img.driver_comment + " ”");
						if (Order.waybill_info_img.driver_comment.equals("")) {
							OrderE_fankui.setVisibility(View.GONE);
						}
					} else {
						OrderE_fankui.setVisibility(View.GONE);
					}
					dismissProgressDialog();// /取消加载框
				}
			}
		});
	}

	private void info() {

		image_tu1 = (ImageView) findViewById(R.id.image_tu1);
		image_tu2 = (ImageView) findViewById(R.id.image_tu2);
		image_tu3 = (ImageView) findViewById(R.id.image_tu3);
		image_tu4 = (ImageView) findViewById(R.id.image_tu4);
		image_tu5 = (ImageView) findViewById(R.id.image_tu5);
		image_tu6 = (ImageView) findViewById(R.id.image_tu6);
		image_tu7 = (ImageView) findViewById(R.id.image_tu7);
		// ////控制是否显示图片布局
		orderE_gaitupian1 = (LinearLayout) findViewById(R.id.orderE_gaitupian1);
		orderE_gaitupian2 = (LinearLayout) findViewById(R.id.orderE_gaitupian2);
		orderE_gaitupian3 = (LinearLayout) findViewById(R.id.orderE_gaitupian3);
		orderE_gaitupian4 = (LinearLayout) findViewById(R.id.orderE_gaitupian4);
		orderE_gaitupian5 = (LinearLayout) findViewById(R.id.orderE_gaitupian5);
		orderE_gaitupian6 = (LinearLayout) findViewById(R.id.orderE_gaitupian6);
		orderE_gaitupian7 = (LinearLayout) findViewById(R.id.orderE_gaitupian7);
		// //// 控制是否显示整个布局
		orderE_ll1 = (RelativeLayout) findViewById(R.id.orderE_ll1);
		orderE_ll2 = (RelativeLayout) findViewById(R.id.orderE_ll2);
		orderE_ll3 = (RelativeLayout) findViewById(R.id.orderE_ll3);
		orderE_ll4 = (RelativeLayout) findViewById(R.id.orderE_ll4);
		orderE_ll5 = (RelativeLayout) findViewById(R.id.orderE_ll5);
		orderE_ll6 = (RelativeLayout) findViewById(R.id.orderE_ll6);
		orderE_ll7 = (RelativeLayout) findViewById(R.id.orderE_ll7);
		// /////输入位置
		orderE_weizhi1 = (TextView) findViewById(R.id.orderE_weizhi1);
		orderE_weizhi2 = (TextView) findViewById(R.id.orderE_weizhi2);
		orderE_weizhi3 = (TextView) findViewById(R.id.orderE_weizhi3);
		orderE_weizhi4 = (TextView) findViewById(R.id.orderE_weizhi4);
		orderE_weizhi5 = (TextView) findViewById(R.id.orderE_weizhi5);
		orderE_weizhi6 = (TextView) findViewById(R.id.orderE_weizhi6);
		orderE_weizhi7 = (TextView) findViewById(R.id.orderE_weizhi7);

		orderE_sijname1 = (TextView) findViewById(R.id.orderE_sijname1);
		orderE_sijname2 = (TextView) findViewById(R.id.orderE_sijname2);
		orderE_sijname3 = (TextView) findViewById(R.id.orderE_sijname3);
		orderE_sijname4 = (TextView) findViewById(R.id.orderE_sijname4);
		orderE_sijname5 = (TextView) findViewById(R.id.orderE_sijname5);
		orderE_sijname6 = (TextView) findViewById(R.id.orderE_sijname6);
		orderE_sijname7 = (TextView) findViewById(R.id.orderE_sijname7);

		orderE_time1 = (TextView) findViewById(R.id.orderE_time1);
		orderE_time2 = (TextView) findViewById(R.id.orderE_time2);
		orderE_time3 = (TextView) findViewById(R.id.orderE_time3);
		orderE_time4 = (TextView) findViewById(R.id.orderE_time4);
		orderE_time5 = (TextView) findViewById(R.id.orderE_time5);
		orderE_time6 = (TextView) findViewById(R.id.orderE_time6);
		orderE_time7 = (TextView) findViewById(R.id.orderE_time7);

		orderE_sijiphone1 = (TextView) findViewById(R.id.orderE_sijiphone1);
		orderE_sijiphone2 = (TextView) findViewById(R.id.orderE_sijiphone2);
		orderE_sijiphone3 = (TextView) findViewById(R.id.orderE_sijiphone3);
		orderE_sijiphone4 = (TextView) findViewById(R.id.orderE_sijiphone4);
		orderE_sijiphone5 = (TextView) findViewById(R.id.orderE_sijiphone5);
		orderE_sijiphone6 = (TextView) findViewById(R.id.orderE_sijiphone6);
		orderE_sijiphone7 = (TextView) findViewById(R.id.orderE_sijiphone7);

		orderE_tupianA1 = (ImageView) findViewById(R.id.orderE_tupianA1);
		orderE_tupianB1 = (ImageView) findViewById(R.id.orderE_tupianB1);
		orderE_tupianC1 = (ImageView) findViewById(R.id.orderE_tupianC1);
		orderE_tupianD1 = (ImageView) findViewById(R.id.orderE_tupianD1);
		orderE_tupianA1.setOnClickListener(this);
		orderE_tupianB1.setOnClickListener(this);
		orderE_tupianC1.setOnClickListener(this);
		orderE_tupianD1.setOnClickListener(this);

		orderE_tupianA2 = (ImageView) findViewById(R.id.orderE_tupianA2);
		orderE_tupianB2 = (ImageView) findViewById(R.id.orderE_tupianB2);
		orderE_tupianC2 = (ImageView) findViewById(R.id.orderE_tupianC2);
		orderE_tupianD2 = (ImageView) findViewById(R.id.orderE_tupianD2);
		orderE_tupianA2.setOnClickListener(this);
		orderE_tupianB2.setOnClickListener(this);
		orderE_tupianC2.setOnClickListener(this);
		orderE_tupianD2.setOnClickListener(this);

		orderE_tupianA3 = (ImageView) findViewById(R.id.orderE_tupianA3);
		orderE_tupianB3 = (ImageView) findViewById(R.id.orderE_tupianB3);
		orderE_tupianC3 = (ImageView) findViewById(R.id.orderE_tupianC3);
		orderE_tupianD3 = (ImageView) findViewById(R.id.orderE_tupianD3);
		orderE_tupianA3.setOnClickListener(this);
		orderE_tupianB3.setOnClickListener(this);
		orderE_tupianC3.setOnClickListener(this);
		orderE_tupianD3.setOnClickListener(this);

		orderE_tupianA4 = (ImageView) findViewById(R.id.orderE_tupianA4);
		orderE_tupianB4 = (ImageView) findViewById(R.id.orderE_tupianB4);
		orderE_tupianC4 = (ImageView) findViewById(R.id.orderE_tupianC4);
		orderE_tupianD4 = (ImageView) findViewById(R.id.orderE_tupianD4);
		orderE_tupianA4.setOnClickListener(this);
		orderE_tupianB4.setOnClickListener(this);
		orderE_tupianC4.setOnClickListener(this);
		orderE_tupianD4.setOnClickListener(this);

		orderE_tupianA5 = (ImageView) findViewById(R.id.orderE_tupianA5);
		orderE_tupianB5 = (ImageView) findViewById(R.id.orderE_tupianB5);
		orderE_tupianC5 = (ImageView) findViewById(R.id.orderE_tupianC5);
		orderE_tupianD5 = (ImageView) findViewById(R.id.orderE_tupianD5);
		orderE_tupianA5.setOnClickListener(this);
		orderE_tupianB5.setOnClickListener(this);
		orderE_tupianC5.setOnClickListener(this);
		orderE_tupianD5.setOnClickListener(this);

		orderE_tupianA6 = (ImageView) findViewById(R.id.orderE_tupianA6);
		orderE_tupianB6 = (ImageView) findViewById(R.id.orderE_tupianB6);
		orderE_tupianC6 = (ImageView) findViewById(R.id.orderE_tupianC6);
		orderE_tupianD6 = (ImageView) findViewById(R.id.orderE_tupianD6);
		orderE_tupianA6.setOnClickListener(this);
		orderE_tupianB6.setOnClickListener(this);
		orderE_tupianC6.setOnClickListener(this);
		orderE_tupianD6.setOnClickListener(this);

		orderE_tupianA7 = (ImageView) findViewById(R.id.orderE_tupianA7);
		orderE_tupianB7 = (ImageView) findViewById(R.id.orderE_tupianB7);
		orderE_tupianC7 = (ImageView) findViewById(R.id.orderE_tupianC7);
		orderE_tupianD7 = (ImageView) findViewById(R.id.orderE_tupianD7);
		orderE_tupianA7.setOnClickListener(this);
		orderE_tupianB7.setOnClickListener(this);
		orderE_tupianC7.setOnClickListener(this);
		orderE_tupianD7.setOnClickListener(this);

		OrderE_fankui = (LinearLayout) findViewById(R.id.OrderE_fankui);
		orderE_sijifankui = (TextView) findViewById(R.id.orderE_sijifankui);
		findViewById(R.id.left).setOnClickListener(this);
		findViewById(R.id.orderE_shuaxin).setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (isFastDoubleClick()) {
			return;
		}
		switch (arg0.getId()) {
		case R.id.left:
			finish();
			break;

		case R.id.orderE_shuaxin:
			getintent();
			break;
		case R.id.orderE_tupianA1:

			Intentimage(imageA1);
			break;
		case R.id.orderE_tupianB1:
			Intentimage(imageB1);
			break;
		case R.id.orderE_tupianC1:
			Intentimage(imageC1);
			break;
		case R.id.orderE_tupianD1:
			Intentimage(imageD1);
			break;
		case R.id.orderE_tupianA2:
			Intentimage(imageA2);
			break;
		case R.id.orderE_tupianB2:
			Intentimage(imageB2);
			break;
		case R.id.orderE_tupianC2:
			Intentimage(imageC2);
			break;
		case R.id.orderE_tupianD2:
			Intentimage(imageD2);
			break;
		case R.id.orderE_tupianA3:
			Intentimage(imageA3);
			break;
		case R.id.orderE_tupianB3:
			Intentimage(imageB3);
			break;
		case R.id.orderE_tupianC3:
			Intentimage(imageC3);
			break;
		case R.id.orderE_tupianD3:
			Intentimage(imageD3);
			break;
		case R.id.orderE_tupianA4:
			Intentimage(imageA4);
			break;
		case R.id.orderE_tupianB4:
			Intentimage(imageB4);
			break;
		case R.id.orderE_tupianC4:
			Intentimage(imageC4);
			break;
		case R.id.orderE_tupianD4:
			Intentimage(imageD4);
			break;
		case R.id.orderE_tupianA5:
			Intentimage(imageA5);
			break;
		case R.id.orderE_tupianB5:
			Intentimage(imageB5);
			break;
		case R.id.orderE_tupianC5:
			Intentimage(imageC5);
			break;
		case R.id.orderE_tupianD5:
			Intentimage(imageD5);
			break;
		case R.id.orderE_tupianA6:
			Intentimage(imageA6);
			break;
		case R.id.orderE_tupianB6:
			Intentimage(imageB6);
			break;
		case R.id.orderE_tupianC6:
			Intentimage(imageC6);
			break;
		case R.id.orderE_tupianD6:
			Intentimage(imageD6);
			break;
		case R.id.orderE_tupianA7:
			Intentimage(imageA7);
			break;
		case R.id.orderE_tupianB7:
			Intentimage(imageB7);
			break;
		case R.id.orderE_tupianC7:
			Intentimage(imageC7);
			break;
		case R.id.orderE_tupianD7:
			Intentimage(imageD7);
			break;
		default:
			break;
		}
	}

	private void Intentimage(String image) {
		Intent it = new Intent(this, ImageShowa.class);
		it.putExtra("image", image);
		startActivity(it);
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
