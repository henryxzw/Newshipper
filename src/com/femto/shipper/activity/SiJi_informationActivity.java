package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.easemob.chatuidemo.activity.ChatActivity;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.My_CheDuiBean;
import com.femto.shipper.bean.SiJiXinXiBean;
import com.femto.shipper.bean.UpdateTuXiangBean;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.utils.Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SiJi_informationActivity extends BaseActivity implements
		OnClickListener {
	private ImageView siji_tupian, siji_shoucang, siji_name1, siji_name2,
			siji_name3, siji_name4, siji_name5, siji_name6, siji_name7,
			siji_name8, siji_img_cemen;
	private TextView siji_name, siji_wujiaoxing, sijitv_shoucang, siji_zdd,
			siji_jqdd, siji_hpn, siji_jl, siji_clxx1, siji_clxx2, siji_clxx3,
			siji_clxx4, siji_clxx5, siji_clxx6, my_siji_lianxi;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private boolean isOpened1 = false; // 默认是关闭
	private int height;
	private LinearLayout My_siji_ll_layout1, siji_yincang, sjxxlla, sjxxllb,
			sjxxllaa;
	private Button my_siji_duanxi, my_siji_dianhua, my_siji_qx;
	private String phone = "";
	private String image1, image2, image3, image4, image5, image6, image7,
			image8;
	private TextView text_sh;
	private String driver_id;
	private String category;
	private int tupian = 0;
	private boolean shujufanhui = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectfk);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		info();
		Intent it = getIntent();
		String cid = it.getStringExtra("driver_id");
		getinfo(cid);
		// 测量高度
		My_siji_ll_layout1.measure(0, 0);
		height = My_siji_ll_layout1.getMeasuredHeight();
		// ///默认隐藏
		LayoutParams layoutParams1 = (LayoutParams) My_siji_ll_layout1
				.getLayoutParams();
		layoutParams1.height = 0;
		My_siji_ll_layout1.setLayoutParams(layoutParams1);

	}

	public void getinfo(String cid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "driver_info");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("driver_id", cid);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.UPDATE_IMSGE + jiaMi; // 本工程的URL加密,
		showProgressDialog("加载中...");
		application.doGet_kuaishu(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("网络异常");
				sjxxlla.setVisibility(View.GONE);
				my_siji_lianxi.setVisibility(View.GONE);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				shujufanhui = false;
				SiJiXinXiBean SiJiXinXiBean = GsonUtils.json2Bean(arg0.result,
						SiJiXinXiBean.class);
				siji_yincang.setVisibility(View.VISIBLE);// /没有取出数据前，隐藏界面
				if (SiJiXinXiBean.status.equals("0")) {
					if (!SiJiXinXiBean.avatar.equals("")) {
						ImageLoader.getInstance().displayImage(
								Net.PICURL + SiJiXinXiBean.avatar, siji_tupian,
								application.options);
					}
					// siji_wujiaoxing.setBackgroundResource(R.drawable.x4);
					if (Float.valueOf(SiJiXinXiBean.credit) == 3) {
						siji_wujiaoxing.setBackgroundResource(R.drawable.x3);
					} else if (Float.valueOf(SiJiXinXiBean.credit) >= 3
							&& Float.valueOf(SiJiXinXiBean.credit) < 3.5) {
						siji_wujiaoxing.setBackgroundResource(R.drawable.x3);
					} else if (Float.valueOf(SiJiXinXiBean.credit) >= 3.5
							&& Float.valueOf(SiJiXinXiBean.credit) < 4) {
						siji_wujiaoxing.setBackgroundResource(R.drawable.x3b);
					} else if (Float.valueOf(SiJiXinXiBean.credit) >= 4
							&& Float.valueOf(SiJiXinXiBean.credit) < 4.5) {
						siji_wujiaoxing.setBackgroundResource(R.drawable.x4);
					} else if (Float.valueOf(SiJiXinXiBean.credit) >= 4.5
							&& Float.valueOf(SiJiXinXiBean.credit) < 5) {
						siji_wujiaoxing.setBackgroundResource(R.drawable.x4b);
					} else if (Float.valueOf(SiJiXinXiBean.credit) == 5) {
						siji_wujiaoxing.setBackgroundResource(R.drawable.x5);
					} else if (Float.valueOf(SiJiXinXiBean.credit) == 0) {
						siji_wujiaoxing.setBackgroundResource(R.drawable.kx5);
					}
					driver_id = SiJiXinXiBean.driver_id;
					category = SiJiXinXiBean.category;
					// showToast("---------收藏状态-------->>" + category);
					if (category.equals("0")) {
						tupian = 1;
						text_sh.setBackgroundResource(R.drawable.plheimingdan);
					}
					if (category.equals("1")) {
						tupian = 2;
						text_sh.setBackgroundResource(R.drawable.plyilahei);
					}

					siji_name.setText(SiJiXinXiBean.nick_name);
					sijitv_shoucang.setText(SiJiXinXiBean.collect + "人收藏");
					siji_zdd.setText(SiJiXinXiBean.order_total);
					siji_jqdd.setText(SiJiXinXiBean.order_near);
					siji_hpn.setText(SiJiXinXiBean.goods_comment);
					siji_jl.setText(SiJiXinXiBean.driving_age);
					if (!SiJiXinXiBean.car_picture1.equals("")) {
						ImageLoader.getInstance().displayImage(ToolUtils
								.DownloadDemo(SiJiXinXiBean.car_picture1),
								siji_name1, application.options);
						image1 = SiJiXinXiBean.car_picture1;
					}
					if (!SiJiXinXiBean.car_picture1.equals("")) {
						ImageLoader.getInstance().displayImage(
								ToolUtils
								.DownloadDemo(SiJiXinXiBean.car_picture2),
								siji_name2, application.options);
						image2 = SiJiXinXiBean.car_picture2;
					}
					if (!SiJiXinXiBean.car_picture1.equals("")) {
						ImageLoader.getInstance().displayImage(
								ToolUtils
								.DownloadDemo(SiJiXinXiBean.car_picture3),
								siji_name3, application.options);
						image3 = SiJiXinXiBean.car_picture3;
					}
					if (!SiJiXinXiBean.car_picture1.equals("")) {
						ImageLoader.getInstance().displayImage(
								ToolUtils
								.DownloadDemo(SiJiXinXiBean.car_picture4),
								siji_name4, application.options);
						image4 = SiJiXinXiBean.car_picture4;
					}
					if (!SiJiXinXiBean.car_picture1.equals("")) {
						ImageLoader.getInstance().displayImage(
								ToolUtils
								.DownloadDemo(SiJiXinXiBean.driving_license1),
								siji_name5, application.options);
						image5 = SiJiXinXiBean.driving_license1;
					}
					if (!SiJiXinXiBean.car_picture1.equals("")) {
						ImageLoader.getInstance().displayImage(
								ToolUtils
								.DownloadDemo(SiJiXinXiBean.taxi_license),
								siji_name6, application.options);
						image6 = SiJiXinXiBean.taxi_license;
					}
					if (!SiJiXinXiBean.car_picture1.equals("")) {
						String a = SiJiXinXiBean.identity_card;
						String b[] = a.split("\\|");
						ImageLoader.getInstance().displayImage(ToolUtils
								.DownloadDemo(b[0]),
								siji_name7, application.options);
						image7 = b[0];
					}
					if (!SiJiXinXiBean.car_picture1.equals("")) {
						ImageLoader.getInstance().displayImage(
								ToolUtils
								.DownloadDemo(SiJiXinXiBean.car_safe),
								siji_name8, application.options);
						image8 = SiJiXinXiBean.car_safe;
						// showToast("car_safe" + SiJiXinXiBean.car_safe);
					}
					siji_clxx1.setText(SiJiXinXiBean.plate_number);
					siji_clxx2.setText(SiJiXinXiBean.cartype_format);
					if (!SiJiXinXiBean.max_tonnage.equals("")) {
						siji_clxx3.setText(SiJiXinXiBean.max_tonnage + "吨");
					} else {
						siji_clxx3.setText("0 吨");
					}

					if (!SiJiXinXiBean.max_load.equals("")) {
						siji_clxx4.setText(SiJiXinXiBean.max_load + " 方");
					} else {
						siji_clxx4.setText("0 方");
					}

					String str1 = "";
					if (SiJiXinXiBean.is_weiban.equals("1")) {
						str1 = str1 + "有尾板";
					} else if (SiJiXinXiBean.is_haiguang.equals("1")) {
						str1 = str1 + "海关监管";
					}
					siji_clxx5.setText(str1);
					if (str1.equals("")) {
						sjxxllaa.setVisibility(View.GONE);
					}
					if (SiJiXinXiBean.is_dancemen.equals("1")) {

						siji_clxx6.setText("单侧门");
						siji_img_cemen.setImageResource(R.drawable.cemen);
					} else if (SiJiXinXiBean.is_shuangcemen.equals("1")) {
						siji_img_cemen.setImageResource(R.drawable.cemen2);
						siji_clxx6.setText("双侧门");
					}
					if (!SiJiXinXiBean.is_dancemen.equals("1")
							&& !SiJiXinXiBean.is_shuangcemen.equals("1")) {
						sjxxllb.setVisibility(View.GONE);
					}
					if (!SiJiXinXiBean.mobile.equals("")) {
						phone = SiJiXinXiBean.mobile;
					}

				} else {
					showToast("该司机不存在！");
					sjxxlla.setVisibility(View.GONE);
					my_siji_lianxi.setVisibility(View.GONE);
				}

			}
		});

	}

	public void info() {
		sjxxllaa = (LinearLayout) findViewById(R.id.sjxxllaa);
		sjxxllb = (LinearLayout) findViewById(R.id.sjxxllb);
		sjxxlla = (LinearLayout) findViewById(R.id.sjxxlla);
		siji_yincang = (LinearLayout) findViewById(R.id.siji_yincang);
		siji_yincang.setVisibility(View.GONE);// /没有取出数据前，隐藏界面
		siji_tupian = (ImageView) findViewById(R.id.siji_tupian);
		siji_shoucang = (ImageView) findViewById(R.id.siji_shoucang);
		siji_name1 = (ImageView) findViewById(R.id.siji_name1);
		siji_name2 = (ImageView) findViewById(R.id.siji_name2);
		siji_name3 = (ImageView) findViewById(R.id.siji_name3);
		siji_name4 = (ImageView) findViewById(R.id.siji_name4);
		siji_name4 = (ImageView) findViewById(R.id.siji_name4);
		siji_name5 = (ImageView) findViewById(R.id.siji_name5);
		siji_name6 = (ImageView) findViewById(R.id.siji_name6);
		siji_name7 = (ImageView) findViewById(R.id.siji_name7);
		siji_name8 = (ImageView) findViewById(R.id.siji_name8);
		siji_img_cemen = (ImageView) findViewById(R.id.siji_img_cemen);
		siji_name = (TextView) findViewById(R.id.siji_name);
		sijitv_shoucang = (TextView) findViewById(R.id.sijitv_shoucang);
		siji_zdd = (TextView) findViewById(R.id.siji_zdd);
		siji_jqdd = (TextView) findViewById(R.id.siji_jqdd);
		siji_hpn = (TextView) findViewById(R.id.siji_hpn);
		siji_jl = (TextView) findViewById(R.id.siji_jl);
		siji_clxx1 = (TextView) findViewById(R.id.siji_clxx1);
		siji_clxx2 = (TextView) findViewById(R.id.siji_clxx2);
		siji_clxx3 = (TextView) findViewById(R.id.siji_clxx3);
		siji_clxx4 = (TextView) findViewById(R.id.siji_clxx4);
		siji_clxx5 = (TextView) findViewById(R.id.siji_clxx5);
		siji_clxx6 = (TextView) findViewById(R.id.siji_clxx6);
		siji_wujiaoxing = (TextView) findViewById(R.id.siji_wujiaoxing);
		findViewById(R.id.left).setOnClickListener(this);
		My_siji_ll_layout1 = (LinearLayout) findViewById(R.id.My_siji_ll_layout1);
		my_siji_lianxi = (TextView) findViewById(R.id.my_siji_lianxi);
		my_siji_lianxi.setOnClickListener(this);
		my_siji_duanxi = (Button) findViewById(R.id.my_siji_duanxi);
		my_siji_dianhua = (Button) findViewById(R.id.my_siji_dianhua);
		my_siji_qx = (Button) findViewById(R.id.my_siji_qx);
		text_sh = (TextView) findViewById(R.id.text_sh);
		text_sh.setOnClickListener(this);
		my_siji_duanxi.setOnClickListener(this);
		my_siji_dianhua.setOnClickListener(this);
		my_siji_qx.setOnClickListener(this);
		siji_name1.setOnClickListener(this);
		siji_name2.setOnClickListener(this);
		siji_name3.setOnClickListener(this);
		siji_name4.setOnClickListener(this);
		siji_name5.setOnClickListener(this);
		siji_name6.setOnClickListener(this);
		siji_name7.setOnClickListener(this);
		siji_name8.setOnClickListener(this);

	}

	// 打开或是关闭
	private void toggle1(boolean animated) {

		if (isOpened1) {// 是打开的。关闭
			if (animated) {// 执行动画
				int start = height;
				int end = 0;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams1 = (LayoutParams) My_siji_ll_layout1
						.getLayoutParams();
				layoutParams1.height = 0;
				My_siji_ll_layout1.setLayoutParams(layoutParams1);

			}
		} else {// 关闭状态，打开
			if (animated) {
				int start = 0;
				int end = height;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams1 = (LayoutParams) My_siji_ll_layout1
						.getLayoutParams();
				layoutParams1.height = height;
				My_siji_ll_layout1.setLayoutParams(layoutParams1);
			}
		}
		isOpened1 = !isOpened1;
	}

	/**
	 * 执行动画
	 * 
	 * @param start
	 * @param end
	 */
	private void doAnimation1(int start, int end) {
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(300);
		// 设置值的变化
		valueAnimator.setIntValues(start, end);
		// 设置监听
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();// 获取当前变化的值
				// 修改属性
				LayoutParams layoutParams = (LayoutParams) My_siji_ll_layout1
						.getLayoutParams();
				layoutParams.height = value;
				My_siji_ll_layout1.setLayoutParams(layoutParams);
			};
		});
		// 启动动画
		valueAnimator.start();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (shujufanhui) {
			return;
		}
		if (isFastDoubleClick()) {
			return;
		}
		Intent it;
		switch (arg0.getId()) {
		case R.id.left:
			finish();
			break;
		case R.id.my_siji_lianxi:
			toggle1(true);
			break;
		case R.id.my_siji_dianhua:
			toggle1(true);
			Uri uri = Uri.parse("tel:" + phone);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(uri);
			SiJi_informationActivity.this.startActivity(intent);
			break;
		case R.id.my_siji_duanxi:
			toggle1(true);
			startActivity(new Intent(SiJi_informationActivity.this,
					ChatActivity.class).putExtra("userId", phone));
			break;
		case R.id.my_siji_qx:
			toggle1(true);
			break;
		case R.id.siji_name1:
			if (!image1.equals("")) {
				it = new Intent(this, ImageShow.class);
				it.putExtra("image", image1);
				startActivity(it);
			} else {
				showToast("没有图片无法显示");
			}
			break;
		case R.id.siji_name2:
			if (!image2.equals("")) {
				it = new Intent(this, ImageShow.class);
				it.putExtra("image", image2);
				startActivity(it);
			} else {
				showToast("没有图片无法显示");
			}
			break;
		case R.id.siji_name3:
			if (!image3.equals("")) {
				it = new Intent(this, ImageShow.class);
				it.putExtra("image", image3);
				startActivity(it);
			} else {
				showToast("没有图片无法显示");
			}
			break;
		case R.id.siji_name4:
			if (!image4.equals("")) {
				it = new Intent(this, ImageShow.class);
				it.putExtra("image", image4);
				startActivity(it);
			} else {
				showToast("没有图片无法显示");
			}
			break;
		case R.id.siji_name5:
			if (!image5.equals("")) {
				it = new Intent(this, ImageShow.class);
				it.putExtra("image", image5);
				startActivity(it);
			} else {
				showToast("没有图片无法显示");
			}
			break;
		case R.id.siji_name6:
			if (!image6.equals("")) {
				it = new Intent(this, ImageShow.class);
				it.putExtra("image", image6);
				startActivity(it);
			} else {
				showToast("没有图片无法显示");
			}
			break;
		case R.id.siji_name7:
			if (!image7.equals("")) {
				it = new Intent(this, ImageShow.class);
				it.putExtra("image", image7);
				startActivity(it);
			} else {
				showToast("没有图片无法显示");
			}
			break;
		case R.id.siji_name8:
			if (!image8.equals("")) {
				it = new Intent(this, ImageShow.class);
				it.putExtra("image", image8);
				startActivity(it);
			} else {
				showToast("没有图片无法显示");
			}
			break;
		case R.id.text_sh:
			if (tupian == 1) {
				scsj(driver_id, "1");
				return;
			}
			if (tupian == 2) {
				qxscsj(driver_id);
				return;
			}

			break;
		default:
			break;
		}
	}

	private void qxscsj(String car_id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "driver_delete");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("driver_id", car_id);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.UPDATE_IMSGE + jiaMi;
		showProgressDialog("加载中...");
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1);
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("网络异常");
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				UpdateTuXiangBean SiJiXinXiBean = GsonUtils.json2Bean(
						arg0.result, UpdateTuXiangBean.class);
				if (SiJiXinXiBean.status.equals("0")) {
					showToast("取消收藏成功");
					tupian = 1;
					text_sh.setBackgroundResource(R.drawable.plheimingdan);
				} else {
					showToast("取消收藏失败");
					showToast(SiJiXinXiBean.msg);
				}

			}
		});
	}

	private void scsj(String car_id, final String i) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "driver_add");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("driver_id", car_id);
		map.put("category", i);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.UPDATE_IMSGE + jiaMi;
		showProgressDialog("加载中...");
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1);
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("网络异常");
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				UpdateTuXiangBean SiJiXinXiBean = GsonUtils.json2Bean(
						arg0.result, UpdateTuXiangBean.class);
				if (SiJiXinXiBean.status.equals("0")) {
					showToast("收藏成功");
					tupian = 2;
					text_sh.setBackgroundResource(R.drawable.plyilahei);
				} else {
					showToast("收藏失败");
					showToast(SiJiXinXiBean.msg);
				}

			}
		});
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
