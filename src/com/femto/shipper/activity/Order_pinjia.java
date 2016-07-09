package com.femto.shipper.activity;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.Order_ZT;
import com.femto.shipper.bean.UpdateTuXiangBean;
import com.femto.shipper.bean.YundanPLBrean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

public class Order_pinjia extends BaseActivity implements OnClickListener {
	private Intent it;
	private YundanPLBrean yundanPLBrean;
	private Button orderC_shuaxin, orderC_tijiao;
	private ImageView orderC_tuoxiang;
	private TextView orderC_yundanhao, orderC_dindanshijian,
			order_wanchengshijian, orderC_money, orderC_siji, orderC_car_hao,
			orderC_shijian, order_sh;
	private ImageView orderC_xing1, orderC_xing2, orderC_xing3, orderC_xing4,
			orderC_xing5;
	private LinearLayout orderC_yingchang;
	private EditText orderC_context;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private int sx = R.drawable.xing4;
	private int kx = R.drawable.xing5;
	private String xinshu = "5";
	private String neirong;
	private String yid;
	private String state;
	private String driver_id;// //司机ID
	private String category;
	private boolean type_dianjia = true;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_pinjia);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		info();
		xingxing(sx, sx, sx, sx, sx);
		it = getIntent();
		yid = it.getStringExtra("yid");
		state = it.getStringExtra("state");
		getintent(yid);

	}

	@Override
	public void onClick(View arg0) {
		if (isFastDoubleClick()) {
			return;
		}
		switch (arg0.getId()) {
		case R.id.left:
			finish();
			break;
		// 刷新
		case R.id.orderC_shuaxin:
			getintent(yid);
			break;
		// 提交
		case R.id.orderC_tijiao:
			neirong = orderC_context.getText().toString().trim();
			if (xinshu.equals("0") || xinshu.equals("1") || xinshu.equals("2")
					|| xinshu.equals("3")) {
				if (neirong.equals("")) {
					showToast("您的评价低于三星，请输入原因....");
				} else {
					if (neirong.length() < 5) {
						showToast("输入的字数不能少于5....");
					} else {
						settijiao(yid, xinshu, neirong);
					}

				}

			} else if (xinshu.equals("4") || xinshu.equals("5")) {
				settijiao(yid, xinshu, neirong);
			}

			break;
		// 填充星星
		case R.id.orderC_xing1:
			xinshu = "1";
			xingxing(sx, sx, sx, kx, kx);
			break;
		case R.id.orderC_xing2:
			xinshu = "2";
			xingxing(sx, sx, sx, kx, kx);
			break;
		case R.id.orderC_xing3:
			xinshu = "3";
			xingxing(sx, sx, sx, kx, kx);
			break;
		case R.id.orderC_xing4:
			xinshu = "4";
			xingxing(sx, sx, sx, sx, kx);
			break;
		case R.id.orderC_xing5:
			xingxing(sx, sx, sx, sx, sx);
			xinshu = "5";
			break;
		case R.id.order_sh:
			scsj(driver_id, "1");
			order_sh.setEnabled(false);
			break;
		default:
			break;
		}

	}

	private void xingxing(int a, int b, int c, int d, int e) {
		orderC_xing1.setImageResource(a);
		orderC_xing2.setImageResource(b);
		orderC_xing3.setImageResource(c);
		orderC_xing4.setImageResource(d);
		orderC_xing5.setImageResource(e);
	}

	// //把数据提交给服务器
	private void settijiao(String yid, String credit, String comment) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "waybill_finish");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("yid", yid);
		map.put("credit", credit);// /信誉度
		map.put("comment", comment);// //评论
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ORDERLIST + jiaMi; // 本工程的URL加密, 返回(string)encryptDES
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				dismissProgressDialog();
				// ///把反回的数据，放在orderBean
				Order_ZT Order_ZT = GsonUtils.json2Bean(arg0.result,
						Order_ZT.class);
				if (Order_ZT.status.equals("0")) {
					// startActivity(MainActivity.class);
					finish();
				} else {
					showToast(Order_ZT.msg);
				}
			}
		});

	}

	// ////从服务器上取数据
	private void getintent(String yid) {
		orderC_yingchang.setVisibility(View.INVISIBLE);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "waybill_finish_info");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("yid", yid);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ORDERLIST + jiaMi; // 本工程的URL加密, 返回(string)encryptDES
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet_kuaishu(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.i("arg0.result:" + arg0.result);
				dismissProgressDialog();
				orderC_yingchang.setVisibility(View.VISIBLE);
				// ///把反回的数据，放在orderBean
				yundanPLBrean = GsonUtils.json2Bean(arg0.result,
						YundanPLBrean.class);
				if (yundanPLBrean.status.equals("0")) {
					// /////////判断是否是已被收藏////////////////
					driver_id = yundanPLBrean.info.driver_id;
					category = yundanPLBrean.info.category;

					if (!category.equals("1")) {
						order_sh.setBackgroundResource(R.drawable.plheimingdan);
					} else {
						order_sh.setBackgroundResource(R.drawable.plyilahei);
						order_sh.setEnabled(false);
					}
					orderC_yundanhao.setText(yundanPLBrean.info.yid);
					orderC_dindanshijian.setText(yundanPLBrean.info.start_time);
					order_wanchengshijian.setText(yundanPLBrean.info.end_time);
					orderC_money.setText(ToolUtils
							.fenShuBaoYiLiuWeiXiaoShu(yundanPLBrean.info.money));
					if (!yundanPLBrean.info.avatar.equals("")) {
						ImageLoader.getInstance().displayImage(
								Net.PICURL + yundanPLBrean.info.avatar,
								orderC_tuoxiang, application.options);
					}
					orderC_siji.setText(yundanPLBrean.info.nick_name);
					orderC_car_hao.setText(yundanPLBrean.info.plate_number);
					shijiancha(yundanPLBrean.info.start_time,
							yundanPLBrean.info.end_time);

				} else {
					showToast(yundanPLBrean.msg);
				}
			}
		});

	}

	// /计算全程用了多长时间
	private void shijiancha(String nowtime, String testtime) {
		try {
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
			// 两个时间差（小时）
			long result = (d.parse(testtime).getTime() - d.parse(nowtime)
					.getTime()) / 3600000;
			// /多余出来的分钟
			long a = (d.parse(testtime).getTime() - d.parse(nowtime).getTime()) / 60000 % 60;
			orderC_shijian.setText("全程" + String.valueOf(result) + "小时"
					+ String.valueOf(a) + "分钟");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void info() {
		findViewById(R.id.left).setOnClickListener(this);
		findViewById(R.id.orderC_shuaxin).setOnClickListener(this);// /刷新
		findViewById(R.id.orderC_tijiao).setOnClickListener(this);// //提交
		orderC_yundanhao = (TextView) findViewById(R.id.orderC_yundanhao);
		orderC_dindanshijian = (TextView) findViewById(R.id.orderC_dindanshijian);
		order_wanchengshijian = (TextView) findViewById(R.id.order_wanchengshijian);
		orderC_money = (TextView) findViewById(R.id.orderC_money);
		orderC_siji = (TextView) findViewById(R.id.orderC_siji);
		orderC_car_hao = (TextView) findViewById(R.id.orderC_car_hao);
		orderC_shijian = (TextView) findViewById(R.id.orderC_shijian);
		orderC_tuoxiang = (ImageView) findViewById(R.id.orderC_tuoxiang);

		order_sh = (TextView) findViewById(R.id.order_sh);
		orderC_xing1 = (ImageView) findViewById(R.id.orderC_xing1);
		orderC_xing2 = (ImageView) findViewById(R.id.orderC_xing2);
		orderC_xing3 = (ImageView) findViewById(R.id.orderC_xing3);
		orderC_xing4 = (ImageView) findViewById(R.id.orderC_xing4);
		orderC_xing5 = (ImageView) findViewById(R.id.orderC_xing5);
		orderC_xing1.setOnClickListener(this);
		orderC_xing2.setOnClickListener(this);
		orderC_xing3.setOnClickListener(this);
		orderC_xing4.setOnClickListener(this);
		orderC_xing5.setOnClickListener(this);
		order_sh.setOnClickListener(this);
		orderC_context = (EditText) findViewById(R.id.orderC_context);
		orderC_yingchang = (LinearLayout) findViewById(R.id.orderC_yingchang);
	}

	// ///收藏司机（car_id ： 车辆ID，i ： 1收藏司机 0黑名单）
	private void scsj(String car_id, final String i) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "driver_add");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("driver_id", car_id);
		map.put("category", i);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.UPDATE_IMSGE + jiaMi; // 本工程的URL加密,
		// 返回(string)encryptDES
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000);// //1秒之后清空缓存
		application.doGet(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				dismissProgressDialog();
				UpdateTuXiangBean SiJiXinXiBean = GsonUtils.json2Bean(
						arg0.result, UpdateTuXiangBean.class);
				if (SiJiXinXiBean.status.equals("0")) {
					order_sh.setBackgroundResource(R.drawable.plyilahei);
					showToast("成功");
				} else {
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
