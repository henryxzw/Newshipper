package com.femto.shipper.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.femto.shipper.R;
import com.femto.shipper.activitya.Bimp;
import com.femto.shipper.activitya.Ycactivitya;
import com.femto.shipper.adapter.SlideCutListView;
import com.femto.shipper.adapter.SlideCutListView.RemoveDirection;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.My_LuXianBean;
import com.femto.shipper.bean.My_LuXianBean.My_LuXianBeanZ;
import com.femto.shipper.bean.UpdateTuXiangBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

public class My_LuXian extends BaseActivity implements
		com.femto.shipper.adapter.SlideCutListView.RemoveListener {
	private SlideCutListView my_luxian_listview;

	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private My_LuXianBean My_LuXianBean;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences, sharedpreferencesb;
	private String shijancuo = "0";
	private String count = "0";
	private Editor editora;
	private Button msycbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_luxianactivity);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		info();
	}

	private void msyc() {
		qcsj();
		Intent intenta = new Intent(mContext, Ycactivitya.class);
		Ycactivitya.msychsyuyc = 0;
		startActivity(intenta);
	}

	private void qcsj() {
		sharedpreferencesb = getSharedPreferences("yball",
				Activity.MODE_PRIVATE);
		editora = sharedpreferencesb.edit();
		editora.putString("bz", "");
		editora.putString("fzrxm", "");
		editora.putString("fzrdh", "");
		editora.putString("hwsx", "");
		editora.putString("bzzl", "");
		editora.putString("shi", "");
		editora.putString("xhi", "");
		editora.putString("sli", "");
		editora.putString("dt", "");
		editora.putString("slcs", "");
		editora.putString("gci", "");
		editora.putString("gcrs", "");
		editora.commit();
		Bimp.tempSelectBitmap.clear();
	}

	private void info() {
		msycbtn = (Button) findViewById(R.id.msycbtn);
		my_luxian_listview = (SlideCutListView) findViewById(R.id.my_luxian_listview);
		my_luxian_listview.setRemoveListener(this);
		findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("lxcount", count);
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});
		msycbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				msyc();
			}
		});
		getTuXiang();
	}

	public void getTuXiang() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "my_line");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("timestamp", shijancuo);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.TONGYONG + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1);// //1秒之后清空缓存
		application.doGet_kuaishu(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				LogUtils.i("加载失败" + arg1);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				list.clear();
				My_LuXianBean = GsonUtils.json2Bean(arg0.result,
						My_LuXianBean.class);
				Map<String, String> map1;
				My_LuXianBeanZ My_LuXianBeanZ;
				// shijancuo = My_LuXianBean.timestamp;
				if (My_LuXianBean.status.equals("0")) {

					if (My_LuXianBean.list != null) {
						count = String.valueOf(My_LuXianBean.list.size());
						SharedPreferences mySharedPreferences = getSharedPreferences(
								"count", Activity.MODE_PRIVATE);
						SharedPreferences.Editor editor = mySharedPreferences
								.edit();
						editor.putString("lxcount", count);
						editor.commit();

						for (int i = 0; i < My_LuXianBean.list.size(); i++) {
							map1 = new HashMap<String, String>();
							My_LuXianBeanZ = My_LuXianBean.list.get(i);
							int a = 19;
							if (My_LuXianBeanZ.start_addr.split("\\|")[0]
									.length() > 22) {
								map1.put(
										"start",
										My_LuXianBeanZ.start_addr.split("\\|")[0]
												.substring(0, 22) + "...");
							} else {
								map1.put(
										"start",
										My_LuXianBeanZ.start_addr.split("\\|")[0]);
							}
							if (My_LuXianBeanZ.end_addr.split("\\|")[0]
									.length() > 22) {
								map1.put(
										"end",
										My_LuXianBeanZ.end_addr.split("\\|")[0]
												.substring(0, 22) + "...");
							} else {
								map1.put("end",
										My_LuXianBeanZ.end_addr.split("\\|")[0]);
							}

							list.add(map1);
						}
						SimpleAdapter adapter = new SimpleAdapter(mContext,
								list, R.layout.my_luxian_itme, new String[] {
										"start", "end" }, new int[] {
										R.id.my_luxian_start,
										R.id.my_luxian_end });
						my_luxian_listview.setAdapter(adapter);
					} else {
						showToast("没有数据");
					}

				}
			}
		});
	}

	@Override
	public void removeItem(RemoveDirection direction, int position) {
		// TODO Auto-generated method stub
		switch (direction) {
		case RIGHT:
			String id1 = My_LuXianBean.list.get(position).id;
			tankuang(id1);
			break;
		case LEFT:
			String id2 = My_LuXianBean.list.get(position).id;
			tankuang(id2);
			break;

		default:
			break;
		}
	}

	private void tankuang(final String id) {

		Builder builder = new Builder(this);
		builder.setTitle("是否确认删除");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dele(id);
				dialog.dismiss();

			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.create().show();
	}

	private void dele(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "delete_my_line");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("line_id", id);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.TONGYONG + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet_kuaishu(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				LogUtils.i("加载失败" + arg1);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				UpdateTuXiangBean UpdateTuXiangBean = GsonUtils.json2Bean(
						arg0.result, UpdateTuXiangBean.class);
				if (UpdateTuXiangBean.status.equals("0")) {
					list.clear();
					getTuXiang();
				}
			}
		});

	}

	// // ////////////单击返回//////////////////////
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 按下键盘上的返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.putExtra("lxcount", count);
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
