package com.femto.shipper.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.UpdateTuXiangBean;
import com.femto.shipper.bean.YouHuiQuanBean;
import com.femto.shipper.bean.YouHuiQuanBean.YouHuiQuanZ;
import com.femto.shipper.utils.AllCapTransformationMethod;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

public class AddCouponActivity extends BaseActivity implements OnClickListener {
	private MyAdapter adapter;
	private TextView text_1, text_duihuan;
	private EditText text_duihuanhao;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private String count = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_youhuiquan);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		findViewById(R.id.left).setOnClickListener(this);
		ListView listview = (ListView) findViewById(R.id.listview);
		text_duihuanhao = (EditText) findViewById(R.id.text_duihuanhao);
		text_duihuan = (TextView) findViewById(R.id.text_duihuan);
		text_duihuanhao
				.setTransformationMethod(new AllCapTransformationMethod());
		text_duihuan.setOnClickListener(this);
		text_1 = (TextView) findViewById(R.id.text_1);
		adapter = new MyAdapter(mContext);
		listview.setAdapter(adapter);
		getDataForNet();
	}

	private void getDataForNet() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "coupon_list");
		map.put("username", phonea);
		map.put("pwd", passworda);

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.YONGHUQUANLIST + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		HttpUtils http = new HttpUtils(10000);
		application.doget(url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				YouHuiQuanBean youHuiQuanBean = GsonUtils.json2Bean(
						arg0.result, YouHuiQuanBean.class);
				if (youHuiQuanBean.status.equals("0")) {
					adapter.setList(youHuiQuanBean.list);
					count = String.valueOf(youHuiQuanBean.list.size());
					SharedPreferences mySharedPreferences = getSharedPreferences(
							"count", Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = mySharedPreferences
							.edit();
					editor.putString("dkjcount", count);
					editor.commit();
					float zongMoney = 0;
					for (int i = 0; i < youHuiQuanBean.list.size(); i++) {
						float parseFloat = Float.parseFloat(youHuiQuanBean.list
								.get(i).DiscountValue);
						zongMoney = zongMoney + parseFloat;
					}
					String fenShuBaoYiLiuWeiXiaoShu = ToolUtils
							.fengbujiequ(String.valueOf(zongMoney));
					text_1.setText("共 " + youHuiQuanBean.list.size() + " 张,可节省 "
							+ fenShuBaoYiLiuWeiXiaoShu + " 元" + ",更多优惠请关注");
				} else {
					showToast(youHuiQuanBean.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}
		});
	}

	class MyAdapter extends ListViewAdapter<YouHuiQuanZ> {

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			Holder holder = null;
			if (convertView == null) {
				holder = new Holder();
				convertView = View.inflate(mContext, R.layout.item_youhuiquan,
						null);
				holder.text_money = (TextView) convertView
						.findViewById(R.id.text_money);
				holder.text_quan_Name = (TextView) convertView
						.findViewById(R.id.text_quan_Name);
				holder.text_xiangxishuoming = (TextView) convertView
						.findViewById(R.id.text_xiangxishuoming);
				holder.YHJ_youhuiTime = (TextView) convertView
						.findViewById(R.id.YHJ_youhuiTime);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			YouHuiQuanZ youHuiQuanZ = myList.get(position);

			String money = youHuiQuanZ.DiscountValue;
			holder.text_money.setText(money);
			holder.text_quan_Name.setText(youHuiQuanZ.Name);
			holder.text_xiangxishuoming.setText(Html
					.fromHtml(youHuiQuanZ.Description));
			try {
				String Str1 = youHuiQuanZ.ServerTime;
				String str2 = youHuiQuanZ.ClosingTime;
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss"); // 格式化时间
				Date date = sdf.parse(Str1);
				Date today = sdf.parse(str2);
				int a = daysBetween(date, today);
				// holder.YHJ_youhuiTime.setText("有效期" + String.valueOf(a) +
				// "天");
				holder.YHJ_youhuiTime.setText("抵扣劵");
				LogUtils.i("----------------->>>" + daysBetween(date, today));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return convertView;
		}
	}

	class Holder {
		TextView text_money;
		TextView text_quan_Name;
		TextView text_xiangxishuoming;
		TextView YHJ_youhuiTime;
	}

	// //计算两个日期之间相隔的天数
	private int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public void getinfo() {
		String nublem = text_duihuanhao.getText().toString().trim();

		if (!nublem.equals("")) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", "change_coupon");
			map.put("username", phonea);
			map.put("pwd", passworda);
			map.put("claimcode", nublem);
			String jiaMi = ToolUtils.JiaMi(map);
			String url = Net.KAIPIAODONGZUO + jiaMi;
			LogUtils.i("请求的url:" + url);
			showProgressDialog("提交中...");
			application.doGet(url, new RequestCallBack<String>() {
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					LogUtils.i(arg0.result);
					dismissProgressDialog();
					UpdateTuXiangBean tijiao = GsonUtils.json2Bean(arg0.result,
							UpdateTuXiangBean.class);
					getDataForNet();
					if (tijiao.status.equals("0")) {
						text_duihuanhao.setText("");
						showToast("提交成功");
					}
					if (tijiao.status.equals("3")) {
						showToast(tijiao.msg);
					}
					if (tijiao.status.equals("4")) {
						showToast(tijiao.msg);
					}
					if (tijiao.status.equals("5")) {
						showToast(tijiao.msg);
					}

				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					LogUtils.i("" + arg1);
					dismissProgressDialog();
				}
			});
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left:
			Intent intent = new Intent();
			intent.putExtra("dkjcount", count);
			setResult(Activity.RESULT_OK, intent);
			finish();
			break;
		case R.id.text_duihuan:
			getinfo();
			break;
		}
	}

	// // ////////////单击返回//////////////////////
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 按下键盘上的返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.putExtra("dkjcount", count);
			setResult(Activity.RESULT_OK, intent);
		}
		return super.onKeyDown(keyCode, event);

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
