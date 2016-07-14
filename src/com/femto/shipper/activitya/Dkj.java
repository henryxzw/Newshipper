package com.femto.shipper.activitya;

import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

public class Dkj extends BaseActivity implements OnClickListener,
		OnItemSelectedListener {
	private SharedPreferences sharedpreferences, sharedpreferencesa,
			sharedpreferencesb;
	private Editor editor = null;
	private TextView dkjdhtv, dkjgjztv, dkjfxtv;
	private Spinner dkjspinner;
	private EditText dkjdhmet;
	private List<String> list;
	private ArrayAdapter<String> adapter;
	private String dkj[] = new String[1000], mds[], dds[];
	private String dhm = "0", phonea, passworda, orderamounta, tjmsyf, orderno,
			oldorderamount, dkjsyf = "0", spid, sppwd;
	private Button dkjbtn;
	private int intm = 0, intd = 0;
	private RelativeLayout dkjrla;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (editor != null) {
			editor.clear();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aadkj);
		sharedpreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedpreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedpreferences.getString(
				getResources().getString(R.string.pwd), "");
		sharedpreferencesa = getSharedPreferences("jga", Activity.MODE_PRIVATE);
		orderamounta = sharedpreferencesa.getString("orderamount", "");
		orderno = sharedpreferencesa.getString("orderno", "");
		tjmsyf = sharedpreferencesa.getString("tjmsyf", "");
		spid = sharedpreferencesa.getString("spid", "");
		sppwd = sharedpreferencesa.getString("sppwd", "");
		dkjsyf = sharedpreferencesa.getString("dkjsyf", "");
		oldorderamount = sharedpreferencesa.getString("oldorderamount", "");
		dkjspinner = (Spinner) findViewById(R.id.dkjspinner);
		dkjdhmet = (EditText) findViewById(R.id.dkjdhmet);
		dkjdhtv = (TextView) findViewById(R.id.dkjdhtv);
		dkjgjztv = (TextView) findViewById(R.id.dkjgjztv);
		dkjfxtv = (TextView) findViewById(R.id.dkjfxtv);
		dkjbtn = (Button) findViewById(R.id.dkjbtn);
		dkjrla = (RelativeLayout) findViewById(R.id.dkjrla);
		list = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dkjbtn.setOnClickListener(this);
		dkjrla.setOnClickListener(this);
		dkjfxtv.setOnClickListener(this);
		dkjdhtv.setOnClickListener(this);
		dkjspinner.setOnItemSelectedListener(this);
		getdata();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	private void getdata() {
		showProgressDialog(getResources().getString(R.string.jzz));
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "coupon_list");
		map.put("username", phonea);
		map.put("pwd", passworda);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.YONGHUQUANLIST + jiaMi;
		list.clear();
		list.add(getResources().getString(R.string.qxzdkj));
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				Dkjhelper dkjhelper = GsonUtils.json2Bean(arg0.result,
						Dkjhelper.class);
				if (dkjhelper.status.equals("0")) {
					int dkjzs = dkjhelper.list.size();
					for (int i = 0; i < dkjzs; i++) {
						String dkjname = dkjhelper.list.get(i).Name;
						String dkjamount = ToolUtils.fengbujiequ(dkjhelper.list
								.get(i).Amount);
						String dkjdiscountvalue = ToolUtils
								.fengbujiequ(dkjhelper.list.get(i).DiscountValue);
						String trspinner = dkjname + "	"
								+ getResources().getString(R.string.m)
								+ dkjamount
								+ getResources().getString(R.string.di)
								+ dkjdiscountvalue;
						dkj[i] = dkjhelper.list.get(i).ClaimCode;
						list.add(trspinner);
					}
					float zongmoney = 0;
					for (int i = 0; i < dkjhelper.list.size(); i++) {
						float onemoney = Float.parseFloat(dkjhelper.list.get(i).DiscountValue);
						zongmoney = zongmoney + onemoney;
					}
					String strzongmoney = ToolUtils
							.fenShuBaoErWeiXiaoShu(String.valueOf(zongmoney));
					dkjgjztv.setText(getResources().getString(R.string.gy)
							+ dkjzs + getResources().getString(R.string.zkjs)
							+ strzongmoney
							+ getResources().getString(R.string.ygdyhjqgz));
				} else {
					showToast(getResources().getString(R.string.wlyc));
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				dismissProgressDialog();
				showToast(getResources().getString(R.string.wlyc));
			}
		});
		dkjspinner.setAdapter(adapter);
	}

	public void duihuanyouhuijuan() {
		String nublem = dkjdhmet.getText().toString().trim();
		if (!nublem.equals("")) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", "change_coupon");
			map.put("username", phonea);
			map.put("pwd", passworda);
			map.put("claimcode", nublem);
			String jiaMi = ToolUtils.JiaMi(map);
			String url = Net.KAIPIAODONGZUO + jiaMi;
			application.doget(url, new RequestCallBack<String>() {
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					StatusBean tijiao = GsonUtils.json2Bean(arg0.result,
							StatusBean.class);

					if (tijiao.status.equals("0")) {
						showToast(getResources().getString(R.string.dhcg));
						getdata();
					} else {
						showToast(getResources().getString(R.string.dhsb));
					}

				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					showToast(getResources().getString(R.string.wlyc));
				}
			});
		}

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.dkjfxtv:
			startActivity(Internetfx.class);
			break;
		case R.id.dkjdhtv:
			duihuanyouhuijuan();
			break;
		case R.id.dkjbtn:
			if (Integer.valueOf(ToolUtils.fengbujiequ(orderamounta)) < intm) {
				showToast(getResources().getString(R.string.bmztjwfsydkj));
			} else {
				sharedpreferencesb = getSharedPreferences("jga",
						Activity.MODE_PRIVATE);
				editor = sharedpreferencesb.edit();
				if (dkjsyf.equals("0")) {
					editor.putString("oldorderamount", orderamounta);
					editor.putString(
							"orderamount",
							String.valueOf(Double.valueOf(orderamounta)
									- Double.valueOf(intd)));
				} else {
					editor.putString("oldorderamount", oldorderamount);
					editor.putString(
							"orderamount",
							String.valueOf(Double.valueOf(oldorderamount)
									- Double.valueOf(intd)));
				}
				editor.putString("orderno", orderno);
				editor.putString("tjmsyf", tjmsyf);
				editor.putString("dhm", dhm);
				editor.putString("spid", spid);
				editor.putString("sppwd", sppwd);
				if (intd > 0) {
					editor.putString("dkjsyf", "1");
				} else {
					if (dkjsyf.equals("0")) {
						editor.putString("dkjsyf", "0");
					} else {
						editor.putString("dkjsyf", "1");
					}
				}
				editor.commit();
				// Log.e("orderamounta>>>>", orderamounta);
				// Log.e("orderno>>>>>>>", orderno);
				finish();
			}
			break;
		case R.id.dkjrla:
			finish();
			break;
		}
	}

	// private void sydkj()
	// {
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("key", "use_coupon");
	// map.put("username", phonea);
	// map.put("pwd", passworda);
	// map.put("claimcode", dhm);
	// map.put("order_no", orderno);
	// String jiaMi = ToolUtils.JiaMi(map);
	// String url = Net.KAIPIAODONGZUO + jiaMi;
	// application.doGet(url, new RequestCallBack<String>()
	// {
	// @Override
	// public void onSuccess(ResponseInfo<String> arg0)
	// {
	// StatusBean tijiao = GsonUtils.json2Bean(arg0.result, StatusBean.class);
	//
	// if (tijiao.status.equals("0"))
	// {
	// showToast("success");
	// getdata();
	// } else
	// {
	// showToast(tijiao.msg);
	// }
	//
	// }
	//
	// @Override
	// public void onFailure(HttpException arg0, String arg1)
	// {
	// }
	// });
	// }

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String all = String.valueOf(arg0.getItemAtPosition(arg2)).trim();
		if (arg2 > 0) {
			dhm = dkj[arg2 - 1];
			mds = all.split(getResources().getString(R.string.m));
			dds = mds[1].split(getResources().getString(R.string.di));
			intm = Integer.valueOf(dds[0]);
			intd = Integer.valueOf(dds[1]);
		} else {
			dhm = "0";
			intm = 0;
			intd = 0;
		}
		Log.e("dhm>>>>>>>>", dhm);
		Log.e("intm>>>>>>>>", intm + "");
		Log.e("intd>>>>>>>>", intd + "");
		Log.e("orderamounta>>>>>>>>", orderamounta);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}