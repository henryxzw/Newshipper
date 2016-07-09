package com.femto.shipper.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.OnTrackListener;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.Order_ZT;
import com.femto.shipper.bean.YundanXQBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

public class Order_plContext extends BaseActivity implements OnClickListener {
	private ImageView OrderD_zhuangtai;
	private TextView orderD_xuanzhe1, orderD_xuanzhe2, orderD_xuanzhe3,
			orderD_xuanzhe4;
	private EditText orderD_shuru;
	private Button orderD_tijiao;
	private Intent it;
	private String yid, sjid, kkall, kka, ooa, ooall, entityname,
			sfendtime = "0001", gls = "0";
	private String state;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	// private Trace trace;
	private int startTime, endTime;
	private OnTrackListener ontracklistener;
	private long serviceid = 114430;
	private LBSTraceClient lbstraceclient;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_plcontext);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		info();

		it = getIntent();
		yid = it.getStringExtra("yid");
		state = it.getStringExtra("state");
		if(state!=null){
			if (state.equals("已接单")) {
				OrderD_zhuangtai.setImageResource(R.drawable.yhzt_yjd);
			} else if (state.equals("接货中")) {
				OrderD_zhuangtai.setImageResource(R.drawable.yszt_yhz);
			} else if (state.equals("装货中")) {
				OrderD_zhuangtai.setImageResource(R.drawable.yszt_zhz);
			} else if (state.equals("送货中")) {
				OrderD_zhuangtai.setImageResource(R.drawable.yszt_shz);
			}
		}
		lbstraceclient = new LBSTraceClient(this);
		lbstraceclient
				.setLocationMode(com.baidu.trace.LocationMode.High_Accuracy);
		getintentYD(yid);
	}

	public void getintentYD(String yid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "waybill_info");
		map.put("username", phonea);
		map.put("yid", yid);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ORDERLIST + jiaMi;
		application.doGet_kuaishu(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				YundanXQBean yundanxqBean = GsonUtils.json2Bean(arg0.result,
						YundanXQBean.class);
				if (yundanxqBean.status.equals("0")) {
					sjid = yundanxqBean.waybill_info.driver_id;
					String kk = yundanxqBean.waybill_info.start_time;
					String oo = yundanxqBean.waybill_info.sign_time;
					kka = kk.substring(0, 4);
					String kkb = kk.substring(5, 7);
					String kkc = kk.substring(8, 10);
					String kkd = kk.substring(11, 13);
					String kke = kk.substring(14, 16);
					String kkf = kk.substring(17, 19);
					kkall = kka + "年" + kkb + "月" + kkc + "日" + kkd + "时" + kke
							+ "分" + kkf + "秒";
					ooa = oo.substring(0, 4);
					String oob = oo.substring(5, 7);
					String ooc = oo.substring(8, 10);
					String ood = oo.substring(11, 13);
					String ooe = oo.substring(14, 16);
					String oof = oo.substring(17, 19);
					ooall = ooa + "年" + oob + "月" + ooc + "日" + ood + "时" + ooe
							+ "分" + oof + "秒";
					yygj();
				} else {
					showToast("没有运单信息");
				}
			}
		});
	}

	private void yygj() {
		entityname = sjid;
		ontracklistener = new OnTrackListener() {

			@Override
			public void onRequestFailedCallback(String arg0) {
				Looper.prepare();
				Looper.loop();
			}

			@Override
			public void onQueryHistoryTrackCallback(String arg0) {
				super.onQueryHistoryTrackCallback(arg0);
				showHistoryTrack(arg0);
			}
		};
		lbstraceclient.setOnTrackListener(ontracklistener);
		startTime = Integer.parseInt(DateUtils.getTimeToStamp(kkall));
		if (ooa.equals(sfendtime)) {
			endTime = (int) (System.currentTimeMillis() / 1000);
		} else {
			endTime = Integer.parseInt(DateUtils.getTimeToStamp(ooall));
		}
		lbstraceclient.queryProcessedHistoryTrack(serviceid, entityname, 0, 1,
				startTime, endTime, 1000, 1, ontracklistener);
	}

	protected void showHistoryTrack(String historyTrack) {
		HistoryTrackData historyTrackData = GsonService.parseJson(historyTrack,
				HistoryTrackData.class);
		List<LatLng> latLngList = new ArrayList<LatLng>();
		if (historyTrackData != null && historyTrackData.getStatus() == 0) {
			if (historyTrackData.getListPoints() != null) {
				latLngList.addAll(historyTrackData.getListPoints());
			}
			drawHistoryTrack(latLngList, historyTrackData.distance);

		}
	}

	private void drawHistoryTrack(final List<LatLng> points,
			final double distance) {
		if (points == null || points.size() == 0) {
			Looper.prepare();
			Looper.loop();
		} else if (points.size() > 1) {
			Looper.prepare();
			gls = String.valueOf(distance / 1000);
			Looper.loop();
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (isFastDoubleClick()) {
			return;
		}
		switch (arg0.getId()) {

		case R.id.orderD_xuanzhe1:
			orderD_shuru.setText(orderD_xuanzhe1.getText().toString());
			orderD_shuru.setSelection(orderD_xuanzhe1.getText().toString()
					.length());
			break;
		case R.id.orderD_xuanzhe2:
			orderD_shuru.setText(orderD_xuanzhe2.getText().toString());
			orderD_shuru.setSelection(orderD_xuanzhe2.getText().toString()
					.length());
			break;
		case R.id.orderD_xuanzhe3:
			orderD_shuru.setText(orderD_xuanzhe3.getText().toString());
			orderD_shuru.setSelection(orderD_xuanzhe3.getText().toString()
					.length());
			break;
		case R.id.orderD_xuanzhe4:
			orderD_shuru.setText(orderD_xuanzhe4.getText().toString());
			orderD_shuru.setSelection(orderD_xuanzhe4.getText().toString()
					.length());
			break;
		case R.id.orderD_tijiao:
			setintent();
			break;
		case R.id.left:
			finish();
			break;

		default:
			break;
		}
	}

	private void setintent() {
		String neirong = orderD_shuru.getText().toString().trim();
		if (neirong.equals("")) {
			showToast("内容为空");
			return;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "cancel_waybill");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("yid", yid);
		map.put("total_distance", gls);
		map.put("reason", neirong);
		String jiaMi = ToolUtils.JiaMi(map);// /加密
		String url = Net.ORDERXIANGQING + jiaMi;
		showProgressDialog("加载中...");
		application.doGet_kuaishu(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
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
					showToast("取消运单成功");
					// startActivity(MainActivity.class);
					finish();
				} else {
					showToast(Order_ZT.status + Order_ZT.msg);
				}
			}
		});

	}

	private void info() {
		OrderD_zhuangtai = (ImageView) findViewById(R.id.OrderD_zhuangtai);
		orderD_xuanzhe1 = (TextView) findViewById(R.id.orderD_xuanzhe1);
		orderD_xuanzhe2 = (TextView) findViewById(R.id.orderD_xuanzhe2);
		orderD_xuanzhe3 = (TextView) findViewById(R.id.orderD_xuanzhe3);
		orderD_xuanzhe4 = (TextView) findViewById(R.id.orderD_xuanzhe4);
		orderD_shuru = (EditText) findViewById(R.id.orderD_shuru);
		orderD_tijiao = (Button) findViewById(R.id.orderD_tijiao);
		orderD_xuanzhe1.setOnClickListener(this);
		orderD_xuanzhe2.setOnClickListener(this);
		orderD_xuanzhe3.setOnClickListener(this);
		orderD_xuanzhe4.setOnClickListener(this);
		orderD_tijiao.setOnClickListener(this);
		findViewById(R.id.left).setOnClickListener(this);

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
