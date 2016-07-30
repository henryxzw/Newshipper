package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.femto.shipper.R;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.JiZhangBenBean;
import com.femto.shipper.bean.JiZhangBenBean.JiZhangBenBeanZ;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.yue.ChangeBirthDialog;
import com.femto.shipper.yue.ChangeBirthDialog.OnBirthListener;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class JiZhangBenActivity extends BaseActivity implements OnClickListener {

	private ListView listview_jizhangben;
	private MyAdapter adapter;
	private TextView JZB_year, JZB_yue, JZB_money;
	private LinearLayout JZB_ll;
	private TextView month_A;
	private String ServerTime;
	private String year, month, day;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private boolean time_type = true;// /获取到时间为true；

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jizhangben);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		adapter = new MyAdapter(mContext);
		info();
	}

	private void info() {
		month_A = (TextView) findViewById(R.id.month_A);
		JZB_ll = (LinearLayout) findViewById(R.id.JZB_ll);
		JZB_year = (TextView) findViewById(R.id.JZB_year);
		JZB_yue = (TextView) findViewById(R.id.JZB_yue);
		JZB_money = (TextView) findViewById(R.id.JZB_money);
		listview_jizhangben = (ListView) findViewById(R.id.listview_jizhangben);
		findViewById(R.id.left).setOnClickListener(this);
		JZB_ll.setOnClickListener(this);
		month_A.setText("本月");
		setTime();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setTime() {
		HashMap mapa = new HashMap<String, String>();
		mapa.put("key", "get_server_time");
		String jiamia = ToolUtils.JiaMi(mapa);
		String url = Net.SEVERTIME + jiamia;
		// showProgressDialog("加载中...");
		time_type = false;
		application.doGet(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// dismissProgressDialog();// /取消加载框'
				showToast("没有连网络");
				time_type = false;
			}

			@Override
			public void onSuccess(final ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i("arg0.result:" + arg0.result);
				time_type = true;
				String month2 = Integer.valueOf(arg0.result.split("\\ ")[0]
						.split("\\-")[1]) < 10 ? "0"
						+ Integer.valueOf(arg0.result.split("\\ ")[0]
								.split("\\-")[1]) : ""
						+ Integer.valueOf(arg0.result.split("\\ ")[0]
								.split("\\-")[1]);
				ServerTime = arg0.result.split("\\ ")[0].split("\\-")[0]
						+ month2;
				year = arg0.result.split("\\ ")[0].split("\\-")[0];
				month = arg0.result.split("\\ ")[0].split("\\-")[1];
				day = arg0.result.split("\\ ")[0].split("\\-")[2];
				JZB_year.setText(year + "年");
				JZB_yue.setText(month);
				LogUtils.i("----系统时间------>>>" + ServerTime);
				getDataForNet(ServerTime);// 调用当前月的记账本
				listview_jizhangben.setAdapter(adapter);
			}
		});

	}

	private void getDataForNet(String date) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "account_book");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("date", date);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.TONGYONG + jiaMi;
		showProgressDialog("加载中...");
		application.doGet(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				dismissProgressDialog();// /取消加载框
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i("arg0.result:" + arg0.result);
				dismissProgressDialog();// /取消加载框
				JiZhangBenBean JiZhangBenBean = GsonUtils.json2Bean(
						arg0.result, JiZhangBenBean.class);// //用Gson转成json对象
				if (JiZhangBenBean.status.equals("0")) {
					String mountstr = JiZhangBenBean.amount.toString().trim();
					int mountint = Integer.valueOf(mountstr);
					int mountinta = 0 - mountint;
					JZB_money.setText("¥" + mountinta);
					adapter.setList(JiZhangBenBean.list);
					// showToast(JiZhangBenBean.list.get(0).title);
				}
			}
		});
	}

	class MyAdapter extends ListViewAdapter<JiZhangBenBeanZ> {

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			Holder holder = null;
			if (convertView == null) {
				holder = new Holder();
				convertView = View.inflate(mContext, R.layout.jizhangben_itme,
						null);
				holder.jzb_time = (TextView) convertView
						.findViewById(R.id.jzb_time);
				holder.jzb_title = (TextView) convertView
						.findViewById(R.id.jzb_title);
				holder.jzb_money = (TextView) convertView
						.findViewById(R.id.jzb_money);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			JiZhangBenBeanZ JiZhangBenBeanZ = myList.get(position);
			// // 服务器时间（yy-MM-dd HH:MM:MM）
			String time = JiZhangBenBeanZ.date;
			String time_year = JiZhangBenBeanZ.date.split("\\ ")[0]
					.split("\\-")[0];
			String time_month = JiZhangBenBeanZ.date.split("\\ ")[0]
					.split("\\-")[1];
			String time_day = JiZhangBenBeanZ.date.split("\\ ")[0].split("\\-")[2];
			String shijian = JiZhangBenBeanZ.date.split("\\ ")[1].split("\\:")[0]
					+ ":"
					+ JiZhangBenBeanZ.date.split("\\ ")[1].split("\\:")[1];
			LogUtils.i("---------->>" + time);
			LogUtils.i("---------->>" + shijian);
			String month_day = time_month + "-" + time_day;
			String str = aa(Integer.valueOf(time_year),
					(Integer.valueOf(time_month) - 1),
					Integer.valueOf(time_day));

			if (Integer.valueOf(day) == Integer.valueOf(time_day)
					&& Integer.valueOf(month) == Integer.valueOf(time_month)) {
				holder.jzb_time.setText("今天" + "\n" + shijian);
				holder.jzb_title.setText("	" + JiZhangBenBeanZ.title);
				LogUtils.i("---------->>>" + "今天" + shijian);
			} else {
				holder.jzb_time.setText(str.split("\\-")[3] + "\n"
						+ str.split("\\-")[1] + "-" + str.split("\\-")[2]);
				holder.jzb_title.setText(JiZhangBenBeanZ.title);

			}
			String strmoney = JiZhangBenBeanZ.momeny;
			// int money = Integer.valueOf(strmoney);
			holder.jzb_money.setText(strmoney);
			return convertView;
		}
	}

	class Holder {
		TextView jzb_time;
		TextView jzb_title;
		TextView jzb_money;

	}

	private String aa(int year, int monthOfYear, int dayOfMonth) {
		int y = year - 1;
		int m = monthOfYear;
		int c = 20;
		int d = dayOfMonth + 12;
		int w = (y + (y / 4) + (c / 4) - 2 * c + (26 * (m + 1) / 10) + d - 1) % 7;
		String myWeek = null;
		switch (w) {
		case 0:
			myWeek = "日";
			break;
		case 1:
			myWeek = "一";
			break;
		case 2:
			myWeek = "二";
			break;
		case 3:
			myWeek = "三";
			break;
		case 4:
			myWeek = "四";
			break;
		case 5:
			myWeek = "五";
			break;
		case 6:
			myWeek = "六";
			break;
		default:
			break;
		}
		String str = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + "-星期"
				+ myWeek;
		return str;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.left:
			finish();
			break;
		case R.id.JZB_ll:
			if (time_type == false) {
				return;
			}
			if (!year.equals("") || !month.equals("")) {
				ChangeBirthDialog mChangeBirthDialog = new ChangeBirthDialog(
						JiZhangBenActivity.this);
				mChangeBirthDialog.setDate(Integer.valueOf(year),
						Integer.valueOf(month));
				mChangeBirthDialog.show();
				mChangeBirthDialog.setBirthdayListener(new OnBirthListener() {
					@Override
					public void onClick(String year1, String month1) {
						// Toast.makeText(JiZhangBenActivity.this, year1 + "," +
						// month1, Toast.LENGTH_LONG).show();
						JZB_year.setText(year1 + "年");
						month_A.setText(month1 + "月");
						JZB_yue.setText(month1);
						String month2 = Integer.valueOf(month1) < 10 ? "0"
								+ Integer.valueOf(month1) : ""
								+ Integer.valueOf(month1);
						String time = year1 + month2;
						getDataForNet(time);

					}
				});
			} else {

			}
			break;
		default:
			break;
		}
	}

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}
}
