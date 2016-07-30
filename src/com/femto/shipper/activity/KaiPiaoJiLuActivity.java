package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.femto.shipper.R;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.KaiPiaoJiLuBean;
import com.femto.shipper.bean.KaiPiaoJiLuBean.KaiPiaoJiLuZ;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class KaiPiaoJiLuActivity extends BaseActivity implements
		OnClickListener {

	private MyAdapter adapter;
	private TextView kaipiao_putong, kaipiao_zengzhishui;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private LinearLayout kaipiao_layout_ll;
	private ListView listview;
	private int a = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kaipiaojilu);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		listview = (ListView) findViewById(R.id.listview);
		kaipiao_putong = (TextView) findViewById(R.id.kaipiao_putong);
		kaipiao_zengzhishui = (TextView) findViewById(R.id.kaipiao_zengzhishui);
		kaipiao_layout_ll = (LinearLayout) findViewById(R.id.kaipiao_layout_ll);
		kaipiao_putong.setOnClickListener(this);
		kaipiao_zengzhishui.setOnClickListener(this);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		LayoutParams rlawidth1 = (LayoutParams) kaipiao_putong
				.getLayoutParams();
		LayoutParams rlawidth2 = (LayoutParams) kaipiao_zengzhishui
				.getLayoutParams();
		rlawidth1.width = width * 1 / 2;
		rlawidth2.width = width * 1 / 2;
		kaipiao_putong.setLayoutParams(rlawidth1);
		kaipiao_zengzhishui.setLayoutParams(rlawidth2);
		adapter = new MyAdapter(mContext);
		listview.setAdapter(adapter);
		findViewById(R.id.left).setOnClickListener(this);
		kaipiao_layout_ll.setBackgroundResource(R.drawable.jilu);
		getDataForNet("0");

	}

	class MyAdapter extends ListViewAdapter<KaiPiaoJiLuZ> {

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			convertView = View.inflate(mContext, R.layout.item_kaipiaojilu,
					null);
			TextView text_biaoti = (TextView) convertView
					.findViewById(R.id.text_biaoti);
			TextView text_neirong = (TextView) convertView
					.findViewById(R.id.text_neirong);
			TextView text_edu = (TextView) convertView
					.findViewById(R.id.text_edu);
			TextView text_is = (TextView) convertView
					.findViewById(R.id.text_is);
			TextView fpxx = (TextView) convertView.findViewById(R.id.fpxx);
			LinearLayout fpll = (LinearLayout) convertView
					.findViewById(R.id.fpll);
			KaiPiaoJiLuZ kaiPiaoJiLuZ = myList.get(position);
			if (a == 0) {
				fpxx.setText("开票抬头:");
				fpll.setVisibility(View.VISIBLE);
				text_biaoti.setText(kaiPiaoJiLuZ.title);
				text_neirong.setText(kaiPiaoJiLuZ.text);
			} else {
				fpxx.setText("单位名称:");
				fpll.setVisibility(View.GONE);
				text_biaoti.setText(kaiPiaoJiLuZ.danweimingcheng);
			}
			text_edu.setText("¥"
					+ ToolUtils.fenShuBaoYiLiuWeiXiaoShu(kaiPiaoJiLuZ.amount));
			if (kaiPiaoJiLuZ.check.equals("1")) {
				text_is.setText("审核中");
			} else if (kaiPiaoJiLuZ.check.equals("0")) {
				text_is.setText("已开票");
			}

			return convertView;
		}

	}

	private void getDataForNet(final String i) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "invoice_list");
		map.put("username", phonea);
		map.put("type", i);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.KAIPIAOJILU + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");

		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				KaiPiaoJiLuBean kaiPiaoJiLuBean = GsonUtils.json2Bean(
						arg0.result, KaiPiaoJiLuBean.class);
				if (kaiPiaoJiLuBean.status.equals("0")) {
					if (i.equals("0")) {
						a = 0;
					} else {
						a = 1;
					}
					adapter.setList(kaiPiaoJiLuBean.list);
					listview.setAdapter(adapter);
				} else {
					showToast(kaiPiaoJiLuBean.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left:
			finish();
			break;
		case R.id.kaipiao_putong:
			kaipiao_layout_ll.setBackgroundResource(R.drawable.jilu);
			getDataForNet("0");
			break;
		case R.id.kaipiao_zengzhishui:
			kaipiao_layout_ll.setBackgroundResource(R.drawable.shocang);
			getDataForNet("1");
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
