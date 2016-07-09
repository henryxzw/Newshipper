package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.easemob.chatuidemo.activity.ChatActivity;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.OrderBean;
import com.femto.shipper.bean.OrderBean.OrderBeanZ;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.utils.Utils;
import com.femto.shipper.view.RoundImageView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

/**
 * @author mac 订单信息
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class OrderPropertyActivity extends BaseActivity implements
		OnClickListener {
	private String yid1, yid2, yid3, yid4, yid5;
	private Intent it;
	private OrderBeanZ orderBeanZ;
	private RelativeLayout jiazaiyinchang;
	private LinearLayout order_weizi1, order_weizi2, order_weizi3,
			order_weizi4, order_weizi5;// 查看位置
	private LinearLayout order_lianxi1, order_lianxi2, order_lianxi3,
			order_lianxi4, order_lianxi5;// //联系人
	private LinearLayout order_gongxiangweizi1, order_gongxiangweizi2,
			order_gongxiangweizi3, order_gongxiangweizi4,
			order_gongxiangweizi5;// 位置共享
	private LinearLayout order_quxiao1, order_quxiao2, order_quxiao3,
			order_quxiao4, order_quxiao5;// /取消订单
	private LinearLayout yinchang1, yinchang2, yinchang3, yinchang4, yinchang5;
	private int toggleis = 0;
	private RoundImageView order_tuoxiang1, order_tuoxiang2, order_tuoxiang3,
			order_tuoxiang4, order_tuoxiang5;// 头像
	private TextView order_xinxin1, order_xinxin2, order_xinxin3,
			order_xinxin4, order_xinxin5;// 评价五角星
	private ImageView order_like1, order_like2, order_like3, order_like4,
			order_like5; // //收藏的（心）
	private ImageView order_zhangkai1, order_zhangkai2, order_zhangkai3,
			order_zhangkai4, order_zhangkai5;// 点击显示隐藏部分

	private TextView order_name1, order_name2, order_name3, order_name4,
			order_name5; // /司机
	private TextView order_chepaihao1, order_chepaihao2, order_chepaihao3,
			order_chepaihao4, order_chepaihao5;// 车牌号
	private TextView order_yunhou1, order_yunhou2, order_yunhou3,
			order_yunhou4, order_yunhou5;// 运货状态
	private TextView order_haoping1, order_haoping2, order_haoping3,
			order_haoping4, order_haoping5;// /好评数（4.5）

	private LinearLayout ll_layout1, ll_layout2, ll_layout3, ll_layout4,
			ll_layout5, ll_layout6;
	private ImageView zhuangtaitu1, zhuangtaitu2, zhuangtaitu3, zhuangtaitu4,
			zhuangtaitu5;
	private TextView zhuangtaitu_name1, zhuangtaitu_name2, zhuangtaitu_name3,
			zhuangtaitu_name4, zhuangtaitu_name5;

	private boolean isOpened1 = false; // 默认是关闭
	private boolean isOpened2 = false; // 默认是关闭
	private boolean isOpened3 = false; // 默认是关闭
	private boolean isOpened4 = false; // 默认是关闭
	private boolean isOpened5 = false; // 默认是关闭
	private boolean isOpened6 = false; // 默认是关闭

	private int height1;// //LinearLayout的高度
	private int height2;// //LinearLayout的高度
	private int height3;// //LinearLayout的高度
	private int height4;// //LinearLayout的高度
	private int height5;// //LinearLayout的高度
	private int height6;// //LinearLayout的高度

	private String mobile;
	private int i;
	private String type;
	private String SFjiedan, orderno;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private boolean ButtonType = true;// ///判断有没有数据，有数据可点击
	private Myservice myservice;
	private IntentFilter filter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_property);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		info();
		Intent it = getIntent();
		orderno = it.getStringExtra("order_no");
		// //////////////////////////////
		type = "Y";// ///默认type=“Y”
		// 测量高度
		ll_layout1.measure(0, 0);
		height1 = ll_layout1.getMeasuredHeight();
		// 测量高度
		ll_layout2.measure(0, 0);
		height2 = ll_layout2.getMeasuredHeight();
		// 测量高度
		ll_layout3.measure(0, 0);
		height3 = ll_layout3.getMeasuredHeight();
		// 测量高度
		ll_layout4.measure(0, 0);
		height4 = ll_layout4.getMeasuredHeight();
		// 测量高度
		ll_layout5.measure(0, 0);
		height5 = ll_layout5.getMeasuredHeight();
		// ///默认隐藏
		LayoutParams layoutParams1 = (LayoutParams) ll_layout1
				.getLayoutParams();
		layoutParams1.height = 0;
		ll_layout1.setLayoutParams(layoutParams1);
		// ///默认隐藏
		LayoutParams layoutParams2 = (LayoutParams) ll_layout2
				.getLayoutParams();
		layoutParams2.height = 0;
		ll_layout2.setLayoutParams(layoutParams2);
		// ///默认隐藏
		LayoutParams layoutParams3 = (LayoutParams) ll_layout3
				.getLayoutParams();
		layoutParams3.height = 0;
		ll_layout3.setLayoutParams(layoutParams3);
		// ///默认隐藏
		LayoutParams layoutParams4 = (LayoutParams) ll_layout4
				.getLayoutParams();
		layoutParams4.height = 0;
		ll_layout4.setLayoutParams(layoutParams4);
		// ///默认隐藏
		LayoutParams layoutParams5 = (LayoutParams) ll_layout5
				.getLayoutParams();
		layoutParams5.height = 0;
		ll_layout5.setLayoutParams(layoutParams5);
		// ///////////////
		// 测量高度
		ll_layout6.measure(0, 0);
		height6 = ll_layout6.getMeasuredHeight();
		// ///默认隐藏
		LayoutParams layoutParams6 = (LayoutParams) ll_layout6
				.getLayoutParams();
		layoutParams6.height = 0;
		ll_layout6.setLayoutParams(layoutParams6);
		myservice = new Myservice();
		filter = new IntentFilter();
		filter.addAction("com.femto.shipper.activitya.Aapdbroad");
		OrderPropertyActivity.this.registerReceiver(myservice, filter);
	}

	class Myservice extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Bundle bundle = arg1.getExtras();
			String send = bundle.getString("send");
			if (send.equals("sx")) {
				huoqudangedindan(orderno);
			}
		}
	}

	@Override
	protected void onDestroy() {
		OrderPropertyActivity.this.unregisterReceiver(myservice);
		super.onDestroy();
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
		case R.id.genduo:
			if (ButtonType == true) {
				tiaozhuang();
			}
			break;
		case R.id.orderA_zhangkai1:
			toggle1(true);
			toggleis = 1;
			// Log.e("toggleis>>>>>", toggleis+"");
			if (type.equals("X")) {
				type = "Y";
				toggle6(true, i);

			}
			break;
		case R.id.orderA_zhangkai2:
			toggle2(true);
			if (type.equals("X")) {
				type = "Y";
				toggle6(true, i);
			}
			break;
		case R.id.orderA_zhangkai3:
			toggle3(true);
			if (type.equals("X")) {
				type = "Y";
				toggle6(true, i);
			}
			break;
		case R.id.orderA_zhangkai4:
			toggle4(true);
			if (type.equals("X")) {
				type = "Y";
				toggle6(true, i);
			}
			break;
		case R.id.orderA_zhangkai5:
			toggle5(true);
			if (type.equals("X")) {
				type = "Y";
				toggle6(true, i);
			}
			break;
		// /////////////////////////查看行程///////////////////////////////////////////
		case R.id.orderA_weizi1:
			toggle1(true);
			getinfo(0);
			break;
		case R.id.orderA_weizi2:
			// String cid2 = orderBeanZ.car_list.get(1).cid;
			toggle2(true);
			getinfo(1);
			break;
		case R.id.orderA_weizi3:
			toggle3(true);
			getinfo(2);
			break;
		case R.id.orderA_weizi4:
			toggle4(true);
			getinfo(3);
			break;
		case R.id.orderA_weizi5:
			toggle5(true);
			getinfo(4);
			break;
		// ///////////////////////////////////////////////////////////////
		case R.id.orderA_gongxiangweizi1:
			// demo中直接进入聊天页面
			toggle1(true);
			Intent it_1 = new Intent(this, Order_gengduo.class);
			it_1.putExtra("yid", yid1);
			startActivity(it_1);
			break;
		case R.id.orderA_gongxiangweizi2:
			Intent it_2 = new Intent(this, Order_gengduo.class);
			it_2.putExtra("yid", yid2);
			startActivity(it_2);
			break;
		case R.id.orderA_gongxiangweizi3:
			Intent it_3 = new Intent(this, Order_gengduo.class);
			it_3.putExtra("yid", yid3);
			startActivity(it_3);
			break;
		case R.id.orderA_gongxiangweizi4:
			Intent it_4 = new Intent(this, Order_gengduo.class);
			it_4.putExtra("yid", yid4);
			startActivity(it_4);
			break;
		case R.id.orderA_gongxiangweizi5:
			Intent it_5 = new Intent(this, Order_gengduo.class);
			it_5.putExtra("yid", yid5);
			startActivity(it_5);
			break;
		// /////////联系电话/////////////////////////////////////////////
		case R.id.orderA_lianxi1:
			toggle6(true, 0);
			i = 0;
			toggle1(true);
			break;
		case R.id.orderA_lianxi2:
			toggle6(true, 1);
			i = 1;
			toggle2(true);
			break;
		case R.id.orderA_lianxi3:
			toggle6(true, 2);
			i = 2;
			toggle3(true);
			break;
		case R.id.orderA_lianxi4:
			toggle6(true, 3);
			i = 3;
			toggle4(true);
			break;
		case R.id.orderA_lianxi5:
			toggle6(true, 4);
			i = 4;
			toggle5(true);
			break;
		// /////发送消息///////打电话////////取消//////////////////////////////
		case R.id.duanxin2:
			toggle6(true, i);
			startActivity(new Intent(OrderPropertyActivity.this,
					ChatActivity.class).putExtra("userId", mobile));
			break;
		case R.id.dianhua2:
			toggle6(true, i);
			Uri uri = Uri.parse("tel:" + mobile);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(uri);
			OrderPropertyActivity.this.startActivity(intent);
			break;
		case R.id.quexiao2:
			toggle6(true, i);
			break;
		// ///////////////////判断是否订单完成//////////////
		case R.id.orderA_quxiao1:
			toggle1(true);
			if (zhuangtaitu_name1.getText().toString().equals("待评价")) {
				it = new Intent(OrderPropertyActivity.this, Order_pinjia.class);
				it.putExtra("yid", orderBeanZ.car_list.get(0).yid);
				startActivity(it);
			} else if (zhuangtaitu_name1.getText().toString().equals("取消订单")) {
				it = new Intent(OrderPropertyActivity.this,
						Order_plContext.class);
				it.putExtra("yid", orderBeanZ.car_list.get(0).yid);
				it.putExtra("state", orderBeanZ.car_list.get(0).state);
				startActivity(it);
			}
			break;
		case R.id.orderA_quxiao2:
			toggle2(true);
			if (zhuangtaitu_name2.getText().toString().equals("待评价")) {
				it = new Intent(OrderPropertyActivity.this, Order_pinjia.class);
				it.putExtra("yid", orderBeanZ.car_list.get(1).yid);
				startActivity(it);
			} else if (zhuangtaitu_name2.getText().toString().equals("取消订单")) {
				it = new Intent(OrderPropertyActivity.this,
						Order_plContext.class);
				it.putExtra("yid", orderBeanZ.car_list.get(1).yid);
				it.putExtra("state", orderBeanZ.car_list.get(1).state);
				startActivity(it);
			}
			break;
		case R.id.orderA_quxiao3:
			toggle3(true);
			if (zhuangtaitu_name3.getText().toString().equals("待评价")) {
				it = new Intent(OrderPropertyActivity.this, Order_pinjia.class);
				it.putExtra("yid", orderBeanZ.car_list.get(2).yid);
				startActivity(it);
			} else if (zhuangtaitu_name3.getText().toString().equals("取消订单")) {
				it = new Intent(OrderPropertyActivity.this,
						Order_plContext.class);
				it.putExtra("yid", orderBeanZ.car_list.get(2).yid);
				it.putExtra("state", orderBeanZ.car_list.get(2).state);
				startActivity(it);
			}
			break;
		case R.id.orderA_quxiao4:
			toggle4(true);
			if (zhuangtaitu_name4.getText().toString().equals("待评价")) {
				it = new Intent(OrderPropertyActivity.this, Order_pinjia.class);
				it.putExtra("yid", orderBeanZ.car_list.get(3).yid);
				startActivity(it);
			} else if (zhuangtaitu_name4.getText().toString().equals("取消订单")) {
				it = new Intent(OrderPropertyActivity.this,
						Order_plContext.class);
				it.putExtra("yid", orderBeanZ.car_list.get(3).yid);
				it.putExtra("state", orderBeanZ.car_list.get(3).state);
				startActivity(it);
			}
			break;
		case R.id.orderA_quxiao5:
			toggle5(true);
			if (zhuangtaitu_name5.getText().toString().equals("待评价")) {
				it = new Intent(OrderPropertyActivity.this, Order_pinjia.class);
				it.putExtra("yid", orderBeanZ.car_list.get(4).yid);
				startActivity(it);
			} else if (zhuangtaitu_name5.getText().toString().equals("取消订单")) {
				it = new Intent(OrderPropertyActivity.this,
						Order_plContext.class);
				it.putExtra("yid", orderBeanZ.car_list.get(4).yid);
				it.putExtra("state", orderBeanZ.car_list.get(4).state);
				startActivity(it);
			}
			break;
		// ////////////点击头像
		case R.id.orderA_tuoxiang1:
			Intent it1 = new Intent(OrderPropertyActivity.this,
					SiJi_informationActivity.class);
			it1.putExtra("driver_id", orderBeanZ.car_list.get(0).driver_id);
			startActivity(it1);
			break;
		case R.id.orderA_tuoxiang2:
			Intent it2 = new Intent(OrderPropertyActivity.this,
					SiJi_informationActivity.class);
			it2.putExtra("driver_id", orderBeanZ.car_list.get(1).driver_id);
			startActivity(it2);
			break;
		case R.id.orderA_tuoxiang3:
			Intent it3 = new Intent(OrderPropertyActivity.this,
					SiJi_informationActivity.class);
			it3.putExtra("driver_id", orderBeanZ.car_list.get(2).driver_id);
			startActivity(it3);
			break;
		case R.id.orderA_tuoxiang4:
			Intent it4 = new Intent(OrderPropertyActivity.this,
					SiJi_informationActivity.class);
			it4.putExtra("driver_id", orderBeanZ.car_list.get(3).driver_id);
			startActivity(it4);
			break;
		case R.id.orderA_tuoxiang5:
			Intent it5 = new Intent(OrderPropertyActivity.this,
					SiJi_informationActivity.class);
			it5.putExtra("driver_id", orderBeanZ.car_list.get(4).driver_id);
			startActivity(it5);
			break;
		default:
			break;
		}
	}

	// 打开或是关闭
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void toggle1(boolean animated) {

		if (isOpened1) {// 是打开的。关闭
			if (animated) {// 执行动画
				int start = height1;
				int end = 0;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout1
						.getLayoutParams();
				layoutParams.height = 0;
				ll_layout1.setLayoutParams(layoutParams);
			}
		} else {// 关闭状态，打开
			if (animated) {
				int start = 0;
				int end = height1;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout1
						.getLayoutParams();
				layoutParams.height = height1;
				ll_layout1.setLayoutParams(layoutParams);
			}
		}
		// 改变箭头
		if (isOpened1) {
			ObjectAnimator.ofFloat(order_zhangkai1, "rotation", -180, 0)
					.start();
		} else {
			ObjectAnimator.ofFloat(order_zhangkai1, "rotation", 0, -180)
					.start();

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
				LayoutParams layoutParams = (LayoutParams) ll_layout1
						.getLayoutParams();
				layoutParams.height = value;
				ll_layout1.setLayoutParams(layoutParams);
			};
		});
		// 启动动画
		valueAnimator.start();
	}

	// 打开或是关闭
	private void toggle2(boolean animated) {

		if (isOpened2) {// 是打开的。关闭
			if (animated) {// 执行动画
				int start = height2;
				int end = 0;
				doAnimation2(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout2
						.getLayoutParams();
				layoutParams.height = 0;
				ll_layout2.setLayoutParams(layoutParams);
			}
		} else {// 关闭状态，打开
			if (animated) {
				int start = 0;
				int end = height2;
				doAnimation2(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout2
						.getLayoutParams();
				layoutParams.height = height2;
				ll_layout2.setLayoutParams(layoutParams);
			}
		}
		// 改变箭头
		if (isOpened2) {
			ObjectAnimator.ofFloat(order_zhangkai2, "rotation", -180, 0)
					.start();
		} else {
			ObjectAnimator.ofFloat(order_zhangkai2, "rotation", 0, -180)
					.start();

		}
		isOpened2 = !isOpened2;
	}

	/**
	 * 执行动画
	 * 
	 * @param start
	 * @param end
	 */
	private void doAnimation2(int start, int end) {
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
				LayoutParams layoutParams = (LayoutParams) ll_layout2
						.getLayoutParams();
				layoutParams.height = value;
				ll_layout2.setLayoutParams(layoutParams);
			};
		});
		// 启动动画
		valueAnimator.start();
	}

	// 打开或是关闭
	private void toggle3(boolean animated) {

		if (isOpened3) {// 是打开的。关闭
			if (animated) {// 执行动画
				int start = height3;
				int end = 0;
				doAnimation3(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout3
						.getLayoutParams();
				layoutParams.height = 0;
				ll_layout3.setLayoutParams(layoutParams);
			}
		} else {// 关闭状态，打开
			if (animated) {
				int start = 0;
				int end = height3;
				doAnimation3(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout3
						.getLayoutParams();
				layoutParams.height = height3;
				ll_layout3.setLayoutParams(layoutParams);
			}
		}
		// 改变箭头
		if (isOpened3) {
			ObjectAnimator.ofFloat(order_zhangkai3, "rotation", -180, 0)
					.start();
		} else {
			ObjectAnimator.ofFloat(order_zhangkai3, "rotation", 0, -180)
					.start();

		}
		isOpened3 = !isOpened3;
	}

	/**
	 * 执行动画
	 * 
	 * @param start
	 * @param end
	 */
	private void doAnimation3(int start, int end) {
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
				LayoutParams layoutParams = (LayoutParams) ll_layout3
						.getLayoutParams();
				layoutParams.height = value;
				ll_layout3.setLayoutParams(layoutParams);
			};
		});
		// 启动动画
		valueAnimator.start();
	}

	// 打开或是关闭
	private void toggle4(boolean animated) {

		if (isOpened4) {// 是打开的。关闭
			if (animated) {// 执行动画
				int start = height4;
				int end = 0;
				doAnimation4(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout4
						.getLayoutParams();
				layoutParams.height = 0;
				ll_layout4.setLayoutParams(layoutParams);
			}
		} else {// 关闭状态，打开
			if (animated) {
				int start = 0;
				int end = height4;
				doAnimation4(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout4
						.getLayoutParams();
				layoutParams.height = height4;
				ll_layout4.setLayoutParams(layoutParams);
			}
		}
		// 改变箭头
		if (isOpened4) {
			ObjectAnimator.ofFloat(order_zhangkai4, "rotation", -180, 0)
					.start();
		} else {
			ObjectAnimator.ofFloat(order_zhangkai4, "rotation", 0, -180)
					.start();

		}
		isOpened4 = !isOpened4;
	}

	/**
	 * 执行动画
	 * 
	 * @param start
	 * @param end
	 */
	private void doAnimation4(int start, int end) {
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
				LayoutParams layoutParams = (LayoutParams) ll_layout4
						.getLayoutParams();
				layoutParams.height = value;
				ll_layout4.setLayoutParams(layoutParams);
			};
		});
		// 启动动画
		valueAnimator.start();
	}

	// 打开或是关闭
	private void toggle5(boolean animated) {

		if (isOpened5) {// 是打开的。关闭
			if (animated) {// 执行动画
				int start = height5;
				int end = 0;
				doAnimation5(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout5
						.getLayoutParams();
				layoutParams.height = 0;
				ll_layout5.setLayoutParams(layoutParams);
			}
		} else {// 关闭状态，打开
			if (animated) {
				int start = 0;
				int end = height5;
				doAnimation5(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) ll_layout5
						.getLayoutParams();
				layoutParams.height = height5;
				ll_layout5.setLayoutParams(layoutParams);
			}
		}
		// 改变箭头
		if (isOpened5) {
			ObjectAnimator.ofFloat(order_zhangkai5, "rotation", -180, 0)
					.start();
		} else {
			ObjectAnimator.ofFloat(order_zhangkai5, "rotation", 0, -180)
					.start();

		}
		isOpened5 = !isOpened5;
	}

	/**
	 * 执行动画
	 * 
	 * @param start
	 * @param end
	 */
	private void doAnimation5(int start, int end) {
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
				LayoutParams layoutParams = (LayoutParams) ll_layout5
						.getLayoutParams();
				layoutParams.height = value;
				ll_layout5.setLayoutParams(layoutParams);
			};
		});
		// 启动动画
		valueAnimator.start();
	}

	// 打开或是关闭
	private void toggle6(boolean animated, int i) {

		mobile = orderBeanZ.car_list.get(i).mobile;
		if (isOpened6) {// 是打开的。关闭
			if (animated) {// 执行动画
				type = "Y";
				int start = height6;
				int end = 0;
				doAnimation6(start, end);
			} else {
				LayoutParams layoutParams6 = (LayoutParams) ll_layout6
						.getLayoutParams();
				layoutParams6.height = 0;
				ll_layout6.setLayoutParams(layoutParams6);

			}
		} else {// 关闭状态，打开
			if (animated) {
				type = "X";
				int start = 0;
				int end = height6;
				doAnimation6(start, end);
			} else {
				LayoutParams layoutParams6 = (LayoutParams) ll_layout6
						.getLayoutParams();
				layoutParams6.height = height1;
				ll_layout6.setLayoutParams(layoutParams6);
			}
		}
		isOpened6 = !isOpened6;
	}

	/**
	 * 执行动画
	 * 
	 * @param start
	 * @param end
	 */
	private void doAnimation6(int start, int end) {
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(300);
		valueAnimator.setIntValues(start, end);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();// 获取当前变化的值
				// 修改属性
				LayoutParams layoutParams = (LayoutParams) ll_layout6
						.getLayoutParams();
				layoutParams.height = value;
				ll_layout6.setLayoutParams(layoutParams);
			};
		});
		valueAnimator.start();
	}

	private void getinfo(int i) {
		it = new Intent(this, Order_op_ydxq.class);
		it.putExtra("driver_avatar", orderBeanZ.car_list.get(i).driver_avatar);
		it.putExtra("driver_name", orderBeanZ.car_list.get(i).driver_name);
		it.putExtra("star", orderBeanZ.car_list.get(i).star);
		it.putExtra("plate_number", orderBeanZ.car_list.get(i).plate_number);
		it.putExtra("state", orderBeanZ.car_list.get(i).state);
		it.putExtra("ismycar", orderBeanZ.car_list.get(i).ismycar);
		it.putExtra("yid", orderBeanZ.car_list.get(i).yid);
		it.putExtra("cid", orderBeanZ.car_list.get(i).cid);
		it.putExtra("start_time", orderBeanZ.car_list.get(i).start_time);
		it.putExtra("sign_time", orderBeanZ.car_list.get(i).sign_time);
		it.putExtra("mobile", orderBeanZ.car_list.get(i).mobile);
		// it.putExtra("order_state", orderBeanZ.car_list.get(i).order_state);
		it.putExtra("driver_id", orderBeanZ.car_list.get(i).driver_id);
		startActivity(it);
	}

	// ///跳转传递数据
	private void tiaozhuang() {
		Intent it = new Intent();
		it.putExtra("order_amount", orderBeanZ.order_amount);
		it.putExtra("distance", orderBeanZ.distance);
		it.putExtra("order_no", orderBeanZ.order_no);
		it.putExtra("payment_time", orderBeanZ.payment_time);
		it.putExtra("address_start", orderBeanZ.address_start);
		it.putExtra("address_end", orderBeanZ.address_end);
		it.putExtra("address_midway1", orderBeanZ.address_midway1);
		it.putExtra("address_midway2", orderBeanZ.address_midway2);
		it.putExtra("address_midway3", orderBeanZ.address_midway3);
		it.putExtra("address_midway4", orderBeanZ.address_midway4);
		it.putExtra("address_midway5", orderBeanZ.address_midway5);
		it.putExtra("trip_amount", orderBeanZ.trip_amount);
		it.putExtra("contacts_name", orderBeanZ.contacts_name);
		it.putExtra("contacts_mobile", orderBeanZ.contacts_mobile);
		it.putExtra("accept_name", orderBeanZ.accept_name);
		it.putExtra("accept_mobile", orderBeanZ.accept_mobile);
		it.putExtra("use_time", orderBeanZ.use_time);
		it.putExtra("goods_type", orderBeanZ.goods_type);
		it.putExtra("extra_request", orderBeanZ.extra_request);
		it.putExtra("request_amount", orderBeanZ.request_amount);
		it.putExtra("cartype", orderBeanZ.cartype);
		it.putExtra("car_quantity", orderBeanZ.car_quantity);
		it.putExtra("is_up_goods", orderBeanZ.is_up_goods);
		it.putExtra("is_down_goods", orderBeanZ.is_down_goods);
		it.putExtra("is_supercargo", orderBeanZ.is_supercargo);
		it.putExtra("supercargo_number", orderBeanZ.supercargo_number);
		it.putExtra("is_up_floor", orderBeanZ.is_up_floor);
		it.putExtra("floor_number", orderBeanZ.floor_number);
		it.putExtra("is_lift", orderBeanZ.is_lift);
		it.putExtra("carry_amount", orderBeanZ.carry_amount);
		it.putExtra("remark", orderBeanZ.remark);
		it.putExtra("address_start_contact", orderBeanZ.address_start_contact);
		it.putExtra("address_end_contact", orderBeanZ.address_end_contact);
		it.putExtra("address_midway1_contact",
				orderBeanZ.address_midway1_contact);
		it.putExtra("address_midway2_contact",
				orderBeanZ.address_midway2_contact);
		it.putExtra("address_midway3_contact",
				orderBeanZ.address_midway3_contact);
		it.putExtra("address_midway4_contact",
				orderBeanZ.address_midway4_contact);
		it.putExtra("address_midway5_contact",
				orderBeanZ.address_midway5_contact);
		it.putExtra("pack", orderBeanZ.pack);
		it.putExtra("coupon_amount", orderBeanZ.coupon_amount);
		it.setClass(OrderPropertyActivity.this, Order_op_ddxq.class);
		startActivity(it);
	}

	private void huoqudangedindan(String dindanhao) {
		jiazaiyinchang.setVisibility(View.GONE);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "get_order");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("order_no", dindanhao);
		String jiaMi = ToolUtils.JiaMi(map);// /加密
		String url = Net.ORDERXIANGQING + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		ButtonType = false;
		application.doGet_kuaishu(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				showToast(getResources().getString(R.string.wlyc));
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i("有数据" + arg0.result);
				dismissProgressDialog();
				// ///把反回的数据，放在orderBean
				OrderBean orderBean = GsonUtils.json2Bean(arg0.result,
						OrderBean.class);
				jiazaiyinchang.setVisibility(View.VISIBLE);
				if (orderBean.status.equals("0")) {
					ButtonType = true;
					orderBeanZ = orderBean.order;
					try {
						if (orderBeanZ.car_list.size() == 0) {
							yinchang1.setVisibility(View.GONE);
							yinchang2.setVisibility(View.GONE);
							yinchang3.setVisibility(View.GONE);
							yinchang4.setVisibility(View.GONE);
							yinchang5.setVisibility(View.GONE);
							showToast("没有数据");
							return;
						}
						if (orderBeanZ.car_list.size() == 1) {
							yid1 = orderBeanZ.car_list.get(0).yid;
							// ////////////////如果运货状态是---->>已取消或者是已签收---->>就不显示----->>共享位置/////////////////////////////////
							if (orderBeanZ.car_list.get(0).state.equals("已接单")) {
								order_gongxiangweizi1.setVisibility(View.GONE);
							}
							// /////////////////////////////////////////////////头像///////////////////////////////////////////////////////////
							if (!orderBeanZ.car_list.get(0).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(0).driver_avatar,
												order_tuoxiang1,
												application.options);
							} else {
								order_tuoxiang1
										.setImageResource(R.drawable.siji);
							}
							// /判断司机有几星
							Float xingshu1 = Float.valueOf(orderBeanZ.car_list
									.get(0).star);
							order_haoping1.setText(" "
									+ orderBeanZ.car_list.get(0).star);// 好评数
							order_name1.setText(orderBeanZ.car_list.get(0).driver_name); // /司机名
							order_chepaihao1.setText(orderBeanZ.car_list.get(0).plate_number);// /车牌号
							order_yunhou1.setText(orderBeanZ.car_list.get(0).state);// /
							// 运货状态
							if (orderBeanZ.car_list.get(0).state.equals("已接单")) {
								order_gongxiangweizi1.setVisibility(View.GONE);
							}
							if (xingshu1 == 3) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3 && xingshu1 < 3.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3.5 && xingshu1 < 4) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu1 >= 4 && xingshu1 < 4.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu1 >= 4.5 && xingshu1 < 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu1 == 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu1 == 0) {
								order_xinxin1
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(0).ismycar.equals("0")) {
								order_like1.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(0).ismycar
									.equals("1")) {
								order_like1.setImageResource(R.drawable.like1);
							}
							// ////order_state>4就是取消订单，等于4就是订单完成，改成待评价
							if (orderBeanZ.car_list.get(0).state.equals("已完成")
									|| orderBeanZ.car_list.get(0).state
											.equals("已取消")) {
								order_quxiao1.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(0).state
										.equals("已签收")) {
									zhuangtaitu1
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name1.setText("待评价");
								} else {
									zhuangtaitu1
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name1.setText("取消订单");
								}
							}
							yinchang2.setVisibility(View.GONE);
							yinchang3.setVisibility(View.GONE);
							yinchang4.setVisibility(View.GONE);
							yinchang5.setVisibility(View.GONE);
						}
						if (orderBeanZ.car_list.size() == 2) {
							yid1 = orderBeanZ.car_list.get(0).yid;
							yid2 = orderBeanZ.car_list.get(1).yid;
							if (orderBeanZ.car_list.get(0).state.equals("已接单")) {
								order_gongxiangweizi1.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(1).state.equals("已接单")) {
								order_gongxiangweizi2.setVisibility(View.GONE);
							}
							if (!orderBeanZ.car_list.get(0).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(0).driver_avatar,
												order_tuoxiang1,
												application.options);
							} else {
								order_tuoxiang1
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(1).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(1).driver_avatar,
												order_tuoxiang2,
												application.options);
							} else {
								order_tuoxiang2
										.setImageResource(R.drawable.siji);
							}
							// /判断司机有几星
							Float xingshu1 = Float.valueOf(orderBeanZ.car_list
									.get(0).star);
							order_haoping1.setText(" "
									+ orderBeanZ.car_list.get(0).star);// 好评数
							order_name1.setText(orderBeanZ.car_list.get(0).driver_name); // /司机名
							order_chepaihao1.setText(orderBeanZ.car_list.get(0).plate_number);// /车牌号
							order_yunhou1.setText(orderBeanZ.car_list.get(0).state);// /
																					// 运货状态
							if (xingshu1 == 3) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3 && xingshu1 < 3.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3.5 && xingshu1 < 4) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu1 >= 4 && xingshu1 < 4.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu1 >= 4.5 && xingshu1 < 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu1 == 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu1 == 0) {
								order_xinxin1
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(0).ismycar.equals("0")) {
								order_like1.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(0).ismycar
									.equals("1")) {
								order_like1.setImageResource(R.drawable.like1);
							}
							// ////order_state>4就是取消订单，等于4就是订单完成，改成待评价
							if (orderBeanZ.car_list.get(0).state.equals("已完成")
									|| orderBeanZ.car_list.get(0).state
											.equals("已取消")) {
								order_quxiao1.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(0).state
										.equals("已签收")) {
									zhuangtaitu1
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name1.setText("待评价");
								} else {
									zhuangtaitu1
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name1.setText("取消订单");
								}
							}
							// ////////////////////第二个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu2 = Float.valueOf(orderBeanZ.car_list
									.get(1).star);
							order_haoping2.setText(" "
									+ orderBeanZ.car_list.get(1).star);// 好评数
							order_name2.setText(orderBeanZ.car_list.get(1).driver_name); // /司机名
							order_chepaihao2.setText(orderBeanZ.car_list.get(1).plate_number);// /车牌号
							order_yunhou2.setText(orderBeanZ.car_list.get(1).state);// /
																					// 运货状态
							if (xingshu2 == 3) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu2 >= 3 && xingshu2 < 3.5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu2 >= 3.5 && xingshu2 < 4) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu2 >= 4 && xingshu2 < 4.5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu2 >= 4.5 && xingshu2 < 5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu2 == 5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu2 == 0) {
								order_xinxin2
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(1).ismycar.equals("0")) {
								order_like2.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(1).ismycar
									.equals("1")) {
								order_like2.setImageResource(R.drawable.like1);
							}
							// ////order_state>4就是取消订单，等于4就是订单完成，改成待评价

							if (orderBeanZ.car_list.get(1).state.equals("已完成")
									|| orderBeanZ.car_list.get(1).state
											.equals("已取消")) {
								order_quxiao2.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(1).state
										.equals("已签收")) {
									zhuangtaitu2
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name2.setText("待评价");
								} else {
									zhuangtaitu2
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name2.setText("取消订单");
								}
							}
							yinchang3.setVisibility(View.GONE);
							yinchang4.setVisibility(View.GONE);
							yinchang5.setVisibility(View.GONE);

						}

						if (orderBeanZ.car_list.size() == 3)// //要改成（3）
						{
							yid1 = orderBeanZ.car_list.get(0).yid;
							yid2 = orderBeanZ.car_list.get(1).yid;
							yid3 = orderBeanZ.car_list.get(2).yid;
							if (orderBeanZ.car_list.get(0).state.equals("已接单")) {
								order_gongxiangweizi1.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(1).state.equals("已接单")) {
								order_gongxiangweizi2.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(2).state.equals("已接单")) {
								order_gongxiangweizi3.setVisibility(View.GONE);
							}
							if (!orderBeanZ.car_list.get(0).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(0).driver_avatar,
												order_tuoxiang1,
												application.options);
							} else {
								order_tuoxiang1
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(1).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(1).driver_avatar,
												order_tuoxiang2,
												application.options);
							} else {
								order_tuoxiang2
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(2).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(2).driver_avatar,
												order_tuoxiang3,
												application.options);
							} else {
								order_tuoxiang3
										.setImageResource(R.drawable.siji);
							}
							// /判断司机有几星
							Float xingshu1 = Float.valueOf(orderBeanZ.car_list
									.get(0).star);
							order_haoping1.setText(" "
									+ orderBeanZ.car_list.get(0).star);// 好评数
							order_name1.setText(orderBeanZ.car_list.get(0).driver_name); // /司机名
							order_chepaihao1.setText(orderBeanZ.car_list.get(0).plate_number);// /车牌号
							order_yunhou1.setText(orderBeanZ.car_list.get(0).state);// /
																					// 运货状态
							if (xingshu1 == 3) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3 && xingshu1 < 3.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3.5 && xingshu1 < 4) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu1 >= 4 && xingshu1 < 4.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu1 >= 4.5 && xingshu1 < 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu1 == 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu1 == 0) {
								order_xinxin1
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(0).ismycar.equals("0")) {
								order_like1.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(0).ismycar
									.equals("1")) {
								order_like1.setImageResource(R.drawable.like1);
							}
							// ////////////////////第二个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu2 = Float.valueOf(orderBeanZ.car_list
									.get(1).star);
							order_haoping2.setText(" "
									+ orderBeanZ.car_list.get(1).star);// 好评数
							order_name2.setText(orderBeanZ.car_list.get(1).driver_name); // /司机名
							order_chepaihao2.setText(orderBeanZ.car_list.get(1).plate_number);// /车牌号
							order_yunhou2.setText(orderBeanZ.car_list.get(1).state);// /
																					// 运货状态
							if (xingshu2 == 3) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu2 >= 3 && xingshu2 < 3.5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu2 >= 3.5 && xingshu2 < 4) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu2 >= 4 && xingshu2 < 4.5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu2 >= 4.5 && xingshu2 < 5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu2 == 5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu2 == 0) {
								order_xinxin2
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(1).ismycar.equals("0")) {
								order_like2.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(1).ismycar
									.equals("1")) {
								order_like2.setImageResource(R.drawable.like1);
							}
							// ////////////////////第三个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu3 = Float.valueOf(orderBeanZ.car_list
									.get(2).star);
							order_haoping3.setText(" "
									+ orderBeanZ.car_list.get(2).star);// 好评数
							order_name3.setText(orderBeanZ.car_list.get(2).driver_name); // /司机名
							order_chepaihao3.setText(orderBeanZ.car_list.get(2).plate_number);// /车牌号
							order_yunhou3.setText(orderBeanZ.car_list.get(2).state);// ///
																					// 运货状态
							if (xingshu3 == 3) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu3 >= 3 && xingshu3 < 3.5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu3 >= 3.5 && xingshu3 < 4) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu3 >= 4 && xingshu3 < 4.5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu3 >= 4.5 && xingshu3 < 5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu3 == 5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu3 == 0) {
								order_xinxin3
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(2).ismycar.equals("0")) {
								order_like3.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(2).ismycar
									.equals("1")) {
								order_like3.setImageResource(R.drawable.like1);
							}
							// /////判断是否订单已完成
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(0).order_state)
							// < 4) {
							// zhuangtaitu1
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name1.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(0).order_state) == 4) {
							// zhuangtaitu1.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name1.setText("待评价");
							// }
							if (orderBeanZ.car_list.get(0).state.equals("已完成")
									|| orderBeanZ.car_list.get(0).state
											.equals("已取消")) {
								order_quxiao1.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(0).state
										.equals("已签收")) {
									zhuangtaitu1
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name1.setText("待评价");
								} else {
									zhuangtaitu1
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name1.setText("取消订单");
								}
							}

							if (orderBeanZ.car_list.get(1).state.equals("已完成")
									|| orderBeanZ.car_list.get(1).state
											.equals("已取消")) {
								order_quxiao2.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(1).state
										.equals("已签收")) {
									zhuangtaitu2
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name2.setText("待评价");
								} else {
									zhuangtaitu2
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name2.setText("取消订单");
								}
							}
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(1).order_state)
							// < 4) {
							// zhuangtaitu2
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name2.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(1).order_state) == 4) {
							// zhuangtaitu2.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name2.setText("待评价");
							// }

							if (orderBeanZ.car_list.get(2).state.equals("已完成")
									|| orderBeanZ.car_list.get(2).state
											.equals("已取消")) {
								order_quxiao3.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(2).state
										.equals("已签收")) {
									zhuangtaitu3
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name3.setText("待评价");
								} else {
									zhuangtaitu3
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name3.setText("取消订单");
								}
							}

							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(2).order_state)
							// < 4) {
							// zhuangtaitu3
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name3.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(2).order_state) == 4) {
							// zhuangtaitu3.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name3.setText("待评价");
							// }

							yinchang4.setVisibility(View.GONE);
							yinchang5.setVisibility(View.GONE);

						}

						if (orderBeanZ.car_list.size() == 4) {
							yid1 = orderBeanZ.car_list.get(0).yid;
							yid2 = orderBeanZ.car_list.get(1).yid;
							yid3 = orderBeanZ.car_list.get(2).yid;
							yid4 = orderBeanZ.car_list.get(3).yid;
							if (orderBeanZ.car_list.get(0).state.equals("已接单")) {
								order_gongxiangweizi1.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(1).state.equals("已接单")) {
								order_gongxiangweizi2.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(2).state.equals("已接单")) {
								order_gongxiangweizi3.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(3).state.equals("已接单")) {
								order_gongxiangweizi4.setVisibility(View.GONE);
							}
							if (!orderBeanZ.car_list.get(0).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(0).driver_avatar,
												order_tuoxiang1,
												application.options);
							} else {
								order_tuoxiang1
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(1).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(1).driver_avatar,
												order_tuoxiang2,
												application.options);
							} else {
								order_tuoxiang2
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(2).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(2).driver_avatar,
												order_tuoxiang3,
												application.options);
							} else {
								order_tuoxiang3
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(3).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(3).driver_avatar,
												order_tuoxiang4,
												application.options);
							} else {
								order_tuoxiang4
										.setImageResource(R.drawable.siji);
							}
							// /判断司机有几星
							Float xingshu1 = Float.valueOf(orderBeanZ.car_list
									.get(0).star);
							order_haoping1.setText(" "
									+ orderBeanZ.car_list.get(0).star);// 好评数
							order_name1.setText(orderBeanZ.car_list.get(0).driver_name); // /司机名
							order_chepaihao1.setText(orderBeanZ.car_list.get(0).plate_number);// /车牌号
							order_yunhou1.setText(orderBeanZ.car_list.get(0).state);// /
																					// 运货状态
							if (xingshu1 == 3) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3 && xingshu1 < 3.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3.5 && xingshu1 < 4) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu1 >= 4 && xingshu1 < 4.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu1 >= 4.5 && xingshu1 < 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu1 == 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu1 == 0) {
								order_xinxin1
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(0).ismycar.equals("0")) {
								order_like1.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(0).ismycar
									.equals("1")) {
								order_like1.setImageResource(R.drawable.like1);
							}
							// ////////////////////第二个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu2 = Float.valueOf(orderBeanZ.car_list
									.get(1).star);
							order_haoping2.setText(" "
									+ orderBeanZ.car_list.get(1).star);// 好评数
							order_name2.setText(orderBeanZ.car_list.get(1).driver_name); // /司机名
							order_chepaihao2.setText(orderBeanZ.car_list.get(1).plate_number);// /车牌号
							order_yunhou2.setText(orderBeanZ.car_list.get(1).state);// /
																					// 运货状态
							if (xingshu2 == 3) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu2 >= 3 && xingshu2 < 3.5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu2 >= 3.5 && xingshu2 < 4) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu2 >= 4 && xingshu2 < 4.5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu2 >= 4.5 && xingshu2 < 5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu2 == 5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu2 == 0) {
								order_xinxin2
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(1).ismycar.equals("0")) {
								order_like2.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(1).ismycar
									.equals("1")) {
								order_like2.setImageResource(R.drawable.like1);
							}
							// ////////////////////第三个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu3 = Float.valueOf(orderBeanZ.car_list
									.get(2).star);
							order_haoping3.setText(" "
									+ orderBeanZ.car_list.get(2).star);// 好评数
							order_name3.setText(orderBeanZ.car_list.get(2).driver_name); // /司机名
							order_chepaihao3.setText(orderBeanZ.car_list.get(2).plate_number);// /车牌号
							order_yunhou3.setText(orderBeanZ.car_list.get(2).state);// /
																					// 运货状态
							if (xingshu3 == 3) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu3 >= 3 && xingshu3 < 3.5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu3 >= 3.5 && xingshu3 < 4) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu3 >= 4 && xingshu3 < 4.5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu3 >= 4.5 && xingshu3 < 5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu3 == 5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu3 == 0) {
								order_xinxin3
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(2).ismycar.equals("0")) {
								order_like3.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(2).ismycar
									.equals("1")) {
								order_like3.setImageResource(R.drawable.like1);
							}

							// ////////////////////第四个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu4 = Float.valueOf(orderBeanZ.car_list
									.get(3).star);
							order_haoping4.setText(" "
									+ orderBeanZ.car_list.get(3).star);// 好评数
							order_name4.setText(orderBeanZ.car_list.get(3).driver_name); // /司机名
							order_chepaihao4.setText(orderBeanZ.car_list.get(3).plate_number);// /车牌号
							order_yunhou4.setText(orderBeanZ.car_list.get(3).state);// /
																					// 运货状态
							if (xingshu4 == 3) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu4 >= 3 && xingshu4 < 3.5) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu4 >= 3.5 && xingshu4 < 4) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu4 >= 4 && xingshu4 < 4.5) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu4 >= 4.5 && xingshu4 < 5) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu4 == 5) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu4 == 0) {
								order_xinxin4
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(3).ismycar.equals("0")) {
								order_like4.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(3).ismycar
									.equals("1")) {
								order_like4.setImageResource(R.drawable.like1);
							}
							// ///////////////判断是否订单已完成

							if (orderBeanZ.car_list.get(0).state.equals("已完成")
									|| orderBeanZ.car_list.get(0).state
											.equals("已取消")) {
								order_quxiao1.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(0).state
										.equals("已签收")) {
									zhuangtaitu1
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name1.setText("待评价");
								} else {
									zhuangtaitu1
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name1.setText("取消订单");
								}
							}

							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(0).order_state)
							// < 4) {
							// zhuangtaitu1
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name1.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(0).order_state) == 4) {
							// zhuangtaitu1.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name1.setText("待评价");
							// }
							if (orderBeanZ.car_list.get(1).state.equals("已完成")
									|| orderBeanZ.car_list.get(1).state
											.equals("已取消")) {
								order_quxiao2.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(1).state
										.equals("已签收")) {
									zhuangtaitu2
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name2.setText("待评价");
								} else {
									zhuangtaitu2
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name2.setText("取消订单");
								}
							}
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(1).order_state)
							// < 4) {
							// zhuangtaitu2
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name2.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(1).order_state) == 4) {
							// zhuangtaitu2.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name2.setText("待评价");
							// }
							if (orderBeanZ.car_list.get(2).state.equals("已完成")
									|| orderBeanZ.car_list.get(2).state
											.equals("已取消")) {
								order_quxiao3.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(2).state
										.equals("已签收")) {
									zhuangtaitu3
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name3.setText("待评价");
								} else {
									zhuangtaitu3
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name3.setText("取消订单");
								}
							}
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(2).order_state)
							// < 4) {
							// zhuangtaitu3
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name3.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(2).order_state) == 4) {
							// zhuangtaitu3.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name3.setText("待评价");
							// }
							if (orderBeanZ.car_list.get(3).state.equals("已完成")
									|| orderBeanZ.car_list.get(3).state
											.equals("已取消")) {
								order_quxiao4.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(3).state
										.equals("已签收")) {
									zhuangtaitu4
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name4.setText("待评价");
								} else {
									zhuangtaitu4
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name4.setText("取消订单");
								}
							}
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(3).order_state)
							// < 4) {
							// zhuangtaitu4
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name4.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(3).order_state) == 4) {
							// zhuangtaitu4.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name4.setText("待评价");
							// }
							yinchang5.setVisibility(View.GONE);

						}

						if (orderBeanZ.car_list.size() == 5) {
							yid1 = orderBeanZ.car_list.get(0).yid;
							yid2 = orderBeanZ.car_list.get(1).yid;
							yid3 = orderBeanZ.car_list.get(2).yid;
							yid4 = orderBeanZ.car_list.get(3).yid;
							yid5 = orderBeanZ.car_list.get(4).yid;
							if (orderBeanZ.car_list.get(0).state.equals("已接单")) {
								order_gongxiangweizi1.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(1).state.equals("已接单")) {
								order_gongxiangweizi2.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(2).state.equals("已接单")) {
								order_gongxiangweizi3.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(3).state.equals("已接单")) {
								order_gongxiangweizi4.setVisibility(View.GONE);
							}
							if (orderBeanZ.car_list.get(4).state.equals("已接单")) {
								order_gongxiangweizi5.setVisibility(View.GONE);
							}
							if (!orderBeanZ.car_list.get(0).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(0).driver_avatar,
												order_tuoxiang1,
												application.options);
							} else {
								order_tuoxiang1
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(1).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(1).driver_avatar,
												order_tuoxiang2,
												application.options);
							} else {
								order_tuoxiang2
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(2).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(2).driver_avatar,
												order_tuoxiang3,
												application.options);
							} else {
								order_tuoxiang3
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(3).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(3).driver_avatar,
												order_tuoxiang4,
												application.options);
							} else {
								order_tuoxiang4
										.setImageResource(R.drawable.siji);
							}

							if (!orderBeanZ.car_list.get(4).driver_avatar
									.equals("")) {
								ImageLoader
										.getInstance()
										.displayImage(
												Net.PICURL
														+ orderBeanZ.car_list
																.get(4).driver_avatar,
												order_tuoxiang5,
												application.options);
							} else {
								order_tuoxiang5
										.setImageResource(R.drawable.siji);
							}
							// /判断司机有几星
							Float xingshu1 = Float.valueOf(orderBeanZ.car_list
									.get(0).star);
							order_haoping1.setText(" "
									+ orderBeanZ.car_list.get(0).star);// 好评数
							order_name1.setText(orderBeanZ.car_list.get(0).driver_name); // /司机名
							order_chepaihao1.setText(orderBeanZ.car_list.get(0).plate_number);// /车牌号
							order_yunhou1.setText(orderBeanZ.car_list.get(0).state);// /
																					// 运货状态
							if (xingshu1 == 3) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3 && xingshu1 < 3.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu1 >= 3.5 && xingshu1 < 4) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu1 >= 4 && xingshu1 < 4.5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu1 >= 4.5 && xingshu1 < 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu1 == 5) {
								order_xinxin1
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu1 == 0) {
								order_xinxin1
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(0).ismycar.equals("0")) {
								order_like1.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(0).ismycar
									.equals("1")) {
								order_like1.setImageResource(R.drawable.like1);
							}
							// ////////////////////第二个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu2 = Float.valueOf(orderBeanZ.car_list
									.get(1).star);
							order_haoping2.setText(" "
									+ orderBeanZ.car_list.get(1).star);// 好评数
							order_name2.setText(orderBeanZ.car_list.get(1).driver_name); // /司机名
							order_chepaihao2.setText(orderBeanZ.car_list.get(1).plate_number);// /车牌号
							order_yunhou2.setText(orderBeanZ.car_list.get(1).state);// /
																					// 运货状态
							if (xingshu2 == 3) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu2 >= 3 && xingshu2 < 3.5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu2 >= 3.5 && xingshu2 < 4) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu2 >= 4 && xingshu2 < 4.5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu2 >= 4.5 && xingshu2 < 5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu2 == 5) {
								order_xinxin2
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu2 == 0) {
								order_xinxin2
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(1).ismycar.equals("0")) {
								order_like2.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(1).ismycar
									.equals("1")) {
								order_like2.setImageResource(R.drawable.like1);
							}
							// ////////////////////第三个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu3 = Float.valueOf(orderBeanZ.car_list
									.get(2).star);
							order_haoping3.setText(" "
									+ orderBeanZ.car_list.get(2).star);// 好评数
							order_name3.setText(orderBeanZ.car_list.get(2).driver_name); // /司机名
							order_chepaihao3.setText(orderBeanZ.car_list.get(2).plate_number);// /车牌号
							order_yunhou3.setText(orderBeanZ.car_list.get(2).state);// /
																					// 运货状态
							if (xingshu3 == 3) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu3 >= 3 && xingshu3 < 3.5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu3 >= 3.5 && xingshu3 < 4) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu3 >= 4 && xingshu3 < 4.5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu3 >= 4.5 && xingshu3 < 5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu3 == 5) {
								order_xinxin3
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu3 == 0) {
								order_xinxin3
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(2).ismycar.equals("0")) {
								order_like3.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(2).ismycar
									.equals("1")) {
								order_like3.setImageResource(R.drawable.like1);
							}

							// ////////////////////第四个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu4 = Float.valueOf(orderBeanZ.car_list
									.get(3).star);
							order_haoping4.setText(" "
									+ orderBeanZ.car_list.get(3).star);// 好评数
							order_name4.setText(orderBeanZ.car_list.get(3).driver_name); // /司机名
							order_chepaihao4.setText(orderBeanZ.car_list.get(3).plate_number);// /车牌号
							order_yunhou4.setText(orderBeanZ.car_list.get(3).state);// /
																					// 运货状态
							if (xingshu4 == 3) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu4 >= 3 && xingshu4 < 3.5) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu4 >= 3.5 && xingshu4 < 4) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu4 >= 4 && xingshu4 < 4.5) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu4 >= 4.5 && xingshu4 < 5) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu4 == 5) {
								order_xinxin4
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu4 == 0) {
								order_xinxin4
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(3).ismycar.equals("0")) {
								order_like4.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(3).ismycar
									.equals("1")) {
								order_like4.setImageResource(R.drawable.like1);
							}
							// ////////////////////第五个数组/////////////////////////////////

							// /判断司机有几星
							Float xingshu5 = Float.valueOf(orderBeanZ.car_list
									.get(4).star);
							order_haoping5.setText(" "
									+ orderBeanZ.car_list.get(4).star);// 好评数
							order_name5.setText(orderBeanZ.car_list.get(4).driver_name); // /司机名
							order_chepaihao5.setText(orderBeanZ.car_list.get(4).plate_number);// /车牌号
							order_yunhou5.setText(orderBeanZ.car_list.get(4).state);// /
																					// 运货状态
							if (xingshu5 == 3) {
								order_xinxin5
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu5 >= 3 && xingshu5 < 3.5) {
								order_xinxin5
										.setBackgroundResource(R.drawable.x3);
							} else if (xingshu5 >= 3.5 && xingshu5 < 4) {
								order_xinxin5
										.setBackgroundResource(R.drawable.x3b);
							} else if (xingshu5 >= 4 && xingshu5 < 4.5) {
								order_xinxin5
										.setBackgroundResource(R.drawable.x4);
							} else if (xingshu5 >= 4.5 && xingshu5 < 5) {
								order_xinxin5
										.setBackgroundResource(R.drawable.x4b);
							} else if (xingshu5 == 5) {
								order_xinxin5
										.setBackgroundResource(R.drawable.x5);
							} else if (xingshu5 == 0) {
								order_xinxin5
										.setBackgroundResource(R.drawable.kx5);
							}
							// 判断是否为收藏（”1“已收藏）
							if (orderBeanZ.car_list.get(4).ismycar.equals("0")) {
								order_like5.setImageResource(R.drawable.like2);
							} else if (orderBeanZ.car_list.get(4).ismycar
									.equals("1")) {
								order_like5.setImageResource(R.drawable.like1);
							}
							// ////////////////////////////////////判断订单已完成
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(0).order_state)
							// < 4) {
							// zhuangtaitu1
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name1.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(0).order_state) == 4) {
							// zhuangtaitu1.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name1.setText("待评价");
							// }
							if (orderBeanZ.car_list.get(0).state.equals("已完成")
									|| orderBeanZ.car_list.get(0).state
											.equals("已取消")) {
								order_quxiao1.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(0).state
										.equals("已签收")) {
									zhuangtaitu1
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name1.setText("待评价");
								} else {
									zhuangtaitu1
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name1.setText("取消订单");
								}
							}
							if (orderBeanZ.car_list.get(1).state.equals("已完成")
									|| orderBeanZ.car_list.get(1).state
											.equals("已取消")) {
								order_quxiao2.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(1).state
										.equals("已签收")) {
									zhuangtaitu2
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name2.setText("待评价");
								} else {
									zhuangtaitu2
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name2.setText("取消订单");
								}
							}
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(1).order_state)
							// < 4) {
							// zhuangtaitu2
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name2.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(1).order_state) == 4) {
							// zhuangtaitu2.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name2.setText("待评价");
							// }
							if (orderBeanZ.car_list.get(2).state.equals("已完成")
									|| orderBeanZ.car_list.get(2).state
											.equals("已取消")) {
								order_quxiao3.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(2).state
										.equals("已签收")) {
									zhuangtaitu3
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name3.setText("待评价");
								} else {
									zhuangtaitu3
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name3.setText("取消订单");
								}
							}
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(2).order_state)
							// < 4) {
							// zhuangtaitu3
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name3.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(2).order_state) == 4) {
							// zhuangtaitu3.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name3.setText("待评价");
							// }
							if (orderBeanZ.car_list.get(3).state.equals("已完成")
									|| orderBeanZ.car_list.get(3).state
											.equals("已取消")) {
								order_quxiao4.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(3).state
										.equals("已签收")) {
									zhuangtaitu4
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name4.setText("待评价");
								} else {
									zhuangtaitu4
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name4.setText("取消订单");
								}
							}
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(3).order_state)
							// < 4) {
							// zhuangtaitu4
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name4.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(3).order_state) == 4) {
							// zhuangtaitu4.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name4.setText("待评价");
							// }
							if (orderBeanZ.car_list.get(4).state.equals("已完成")
									|| orderBeanZ.car_list.get(4).state
											.equals("已取消")) {
								order_quxiao5.setVisibility(View.GONE);

							} else {
								if (orderBeanZ.car_list.get(4).state
										.equals("已签收")) {
									zhuangtaitu5
											.setImageResource(R.drawable.pj1);
									zhuangtaitu_name5.setText("待评价");
								} else {
									zhuangtaitu5
											.setImageResource(R.drawable.quxiao);
									zhuangtaitu_name5.setText("取消订单");
								}
							}
							// if
							// (Integer.valueOf(orderBeanZ.car_list.get(4).order_state)
							// < 4) {
							// zhuangtaitu5
							// .setImageResource(R.drawable.quxiao);
							// zhuangtaitu_name5.setText("取消订单");
							// } else if (Integer.valueOf(orderBeanZ.car_list
							// .get(4).order_state) == 4) {
							// zhuangtaitu5.setImageResource(R.drawable.pj1);
							// zhuangtaitu_name5.setText("待评价");
							// }
						}
					} catch (Exception e) {
						// TODO: handle exception
						ButtonType = false;
						showToast("数据出现异常");
					}

				} else {
					jiazaiyinchang.setVisibility(View.GONE);
					showToast("数据加载错误");
					ButtonType = false;
				}

			}
		});

	}

	private void info() {
		// /隐藏司机的列表
		yinchang1 = (LinearLayout) findViewById(R.id.yinchang1);
		yinchang2 = (LinearLayout) findViewById(R.id.yinchang2);
		yinchang3 = (LinearLayout) findViewById(R.id.yinchang3);
		yinchang4 = (LinearLayout) findViewById(R.id.yinchang4);
		yinchang5 = (LinearLayout) findViewById(R.id.yinchang5);

		order_weizi1 = (LinearLayout) findViewById(R.id.orderA_weizi1);
		order_weizi2 = (LinearLayout) findViewById(R.id.orderA_weizi2);
		order_weizi3 = (LinearLayout) findViewById(R.id.orderA_weizi3);
		order_weizi4 = (LinearLayout) findViewById(R.id.orderA_weizi4);
		order_weizi5 = (LinearLayout) findViewById(R.id.orderA_weizi5);// 查看位置
		order_weizi1.setOnClickListener(this);
		order_weizi2.setOnClickListener(this);
		order_weizi3.setOnClickListener(this);
		order_weizi4.setOnClickListener(this);
		order_weizi5.setOnClickListener(this);

		order_lianxi1 = (LinearLayout) findViewById(R.id.orderA_lianxi1);
		order_lianxi2 = (LinearLayout) findViewById(R.id.orderA_lianxi2);
		order_lianxi3 = (LinearLayout) findViewById(R.id.orderA_lianxi3);
		order_lianxi4 = (LinearLayout) findViewById(R.id.orderA_lianxi4);
		order_lianxi5 = (LinearLayout) findViewById(R.id.orderA_lianxi5);// //联系人
		order_lianxi1.setOnClickListener(this);
		order_lianxi2.setOnClickListener(this);
		order_lianxi3.setOnClickListener(this);
		order_lianxi4.setOnClickListener(this);
		order_lianxi5.setOnClickListener(this);

		order_gongxiangweizi1 = (LinearLayout) findViewById(R.id.orderA_gongxiangweizi1);
		order_gongxiangweizi2 = (LinearLayout) findViewById(R.id.orderA_gongxiangweizi2);
		order_gongxiangweizi3 = (LinearLayout) findViewById(R.id.orderA_gongxiangweizi3);
		order_gongxiangweizi4 = (LinearLayout) findViewById(R.id.orderA_gongxiangweizi4);
		order_gongxiangweizi5 = (LinearLayout) findViewById(R.id.orderA_gongxiangweizi5);// 位置共享
		order_gongxiangweizi1.setOnClickListener(this);
		order_gongxiangweizi2.setOnClickListener(this);
		order_gongxiangweizi3.setOnClickListener(this);
		order_gongxiangweizi4.setOnClickListener(this);
		order_gongxiangweizi5.setOnClickListener(this);

		order_quxiao1 = (LinearLayout) findViewById(R.id.orderA_quxiao1);
		order_quxiao2 = (LinearLayout) findViewById(R.id.orderA_quxiao2);
		order_quxiao3 = (LinearLayout) findViewById(R.id.orderA_quxiao3);
		order_quxiao4 = (LinearLayout) findViewById(R.id.orderA_quxiao4);
		order_quxiao5 = (LinearLayout) findViewById(R.id.orderA_quxiao5);// /取消订单

		order_quxiao1.setOnClickListener(this);
		order_quxiao2.setOnClickListener(this);
		order_quxiao3.setOnClickListener(this);
		order_quxiao4.setOnClickListener(this);
		order_quxiao5.setOnClickListener(this);

		order_tuoxiang1 = (RoundImageView) findViewById(R.id.orderA_tuoxiang1);
		order_tuoxiang2 = (RoundImageView) findViewById(R.id.orderA_tuoxiang2);
		order_tuoxiang3 = (RoundImageView) findViewById(R.id.orderA_tuoxiang3);
		order_tuoxiang4 = (RoundImageView) findViewById(R.id.orderA_tuoxiang4);
		order_tuoxiang5 = (RoundImageView) findViewById(R.id.orderA_tuoxiang5);// 头像
		order_tuoxiang1.setOnClickListener(this);
		order_tuoxiang2.setOnClickListener(this);
		order_tuoxiang3.setOnClickListener(this);
		order_tuoxiang4.setOnClickListener(this);
		order_tuoxiang5.setOnClickListener(this);

		order_xinxin1 = (TextView) findViewById(R.id.orderA_xinxin1);
		order_xinxin2 = (TextView) findViewById(R.id.orderA_xinxin2);
		order_xinxin3 = (TextView) findViewById(R.id.orderA_xinxin3);
		order_xinxin4 = (TextView) findViewById(R.id.orderA_xinxin4);
		order_xinxin5 = (TextView) findViewById(R.id.orderA_xinxin5);// 评价五角星

		order_like1 = (ImageView) findViewById(R.id.orderA_like1);
		order_like2 = (ImageView) findViewById(R.id.orderA_like2);
		order_like3 = (ImageView) findViewById(R.id.orderA_like3);
		order_like4 = (ImageView) findViewById(R.id.orderA_like4);
		order_like5 = (ImageView) findViewById(R.id.orderA_like5); // //收藏的（心）

		order_zhangkai1 = (ImageView) findViewById(R.id.orderA_zhangkai1);
		order_zhangkai2 = (ImageView) findViewById(R.id.orderA_zhangkai2);
		order_zhangkai3 = (ImageView) findViewById(R.id.orderA_zhangkai3);
		order_zhangkai4 = (ImageView) findViewById(R.id.orderA_zhangkai4);
		order_zhangkai5 = (ImageView) findViewById(R.id.orderA_zhangkai5);// 点击显示隐藏部分

		order_name1 = (TextView) findViewById(R.id.orderA_name1);
		order_name2 = (TextView) findViewById(R.id.orderA_name2);
		order_name3 = (TextView) findViewById(R.id.orderA_name3);
		order_name4 = (TextView) findViewById(R.id.orderA_name4);
		order_name5 = (TextView) findViewById(R.id.orderA_name5); // /司机

		order_chepaihao1 = (TextView) findViewById(R.id.orderA_chepaihao1);
		order_chepaihao2 = (TextView) findViewById(R.id.orderA_chepaihao2);
		order_chepaihao3 = (TextView) findViewById(R.id.orderA_chepaihao3);
		order_chepaihao4 = (TextView) findViewById(R.id.orderA_chepaihao4);
		order_chepaihao5 = (TextView) findViewById(R.id.orderA_chepaihao5);// //车牌号
																			// 车牌号
		order_yunhou1 = (TextView) findViewById(R.id.orderA_yunhou1);
		order_yunhou2 = (TextView) findViewById(R.id.orderA_yunhou2);
		order_yunhou3 = (TextView) findViewById(R.id.orderA_yunhou3);
		order_yunhou4 = (TextView) findViewById(R.id.orderA_yunhou4);
		order_yunhou5 = (TextView) findViewById(R.id.orderA_yunhou5);// /运货状态

		order_haoping1 = (TextView) findViewById(R.id.orderA_haoping1);
		order_haoping2 = (TextView) findViewById(R.id.orderA_haoping2);
		order_haoping3 = (TextView) findViewById(R.id.orderA_haoping3);
		order_haoping4 = (TextView) findViewById(R.id.orderA_haoping4);
		order_haoping5 = (TextView) findViewById(R.id.orderA_haoping5);// /好评数（4.5）
		// ////////////////////////////////////////
		findViewById(R.id.left).setOnClickListener(this);
		findViewById(R.id.genduo).setOnClickListener(this);
		order_zhangkai1.setOnClickListener(this);
		order_zhangkai2.setOnClickListener(this);
		order_zhangkai3.setOnClickListener(this);
		order_zhangkai4.setOnClickListener(this);
		order_zhangkai5.setOnClickListener(this);
		ll_layout1 = (LinearLayout) findViewById(R.id.ll_layout1);
		ll_layout2 = (LinearLayout) findViewById(R.id.ll_layout2);
		ll_layout3 = (LinearLayout) findViewById(R.id.ll_layout3);
		ll_layout4 = (LinearLayout) findViewById(R.id.ll_layout4);
		ll_layout5 = (LinearLayout) findViewById(R.id.ll_layout5);
		ll_layout6 = (LinearLayout) findViewById(R.id.ll_layout6);
		ll_layout1.setOnClickListener(this);
		ll_layout2.setOnClickListener(this);
		ll_layout3.setOnClickListener(this);
		ll_layout4.setOnClickListener(this);
		ll_layout5.setOnClickListener(this);
		jiazaiyinchang = (RelativeLayout) findViewById(R.id.jiazaiyinchang);
		findViewById(R.id.duanxin2).setOnClickListener(this);
		findViewById(R.id.dianhua2).setOnClickListener(this);
		findViewById(R.id.quexiao2).setOnClickListener(this);
		zhuangtaitu1 = (ImageView) findViewById(R.id.zhuangtaitu1);
		zhuangtaitu2 = (ImageView) findViewById(R.id.zhuangtaitu2);
		zhuangtaitu3 = (ImageView) findViewById(R.id.zhuangtaitu3);
		zhuangtaitu4 = (ImageView) findViewById(R.id.zhuangtaitu4);
		zhuangtaitu5 = (ImageView) findViewById(R.id.zhuangtaitu5);
		zhuangtaitu_name1 = (TextView) findViewById(R.id.zhuangtaitu_name1);
		zhuangtaitu_name2 = (TextView) findViewById(R.id.zhuangtaitu_name2);
		zhuangtaitu_name3 = (TextView) findViewById(R.id.zhuangtaitu_name3);
		zhuangtaitu_name4 = (TextView) findViewById(R.id.zhuangtaitu_name4);
		zhuangtaitu_name5 = (TextView) findViewById(R.id.zhuangtaitu_name5);
	}

	public void onResume() {
		super.onResume();
		huoqudangedindan(orderno);
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
