package com.femto.shipper.activitya;

import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class Sbjjr extends BaseActivity implements OnClickListener {
	private RelativeLayout sbjjrrla;
	private EditText sbjjrheta, sbjjrmmetb;
	private Button sbjjrbtn;
	@SuppressWarnings("unused")
	private String sbjjrnumber, sbjjrpwd, orderamounta, orderno, dkjsyf,
			money = "null", dhm;
	private SharedPreferences sharedpreferencesa, sharedpreferencesb;
	private Editor editor = null;

	@Override
	protected void onDestroy() {
		if (editor != null) {
			editor.clear();
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sbjjr);
		sharedpreferencesa = getSharedPreferences("jga", Activity.MODE_PRIVATE);
		orderamounta = sharedpreferencesa.getString("orderamount", "");
		orderno = sharedpreferencesa.getString("orderno", "");
		dkjsyf = sharedpreferencesa.getString("dkjsyf", "");
		dhm = sharedpreferencesa.getString("dhm", "");
		sbjjrrla = (RelativeLayout) findViewById(R.id.sbjjrrla);
		sbjjrheta = (EditText) findViewById(R.id.sbjjrheta);
		sbjjrmmetb = (EditText) findViewById(R.id.sbjjrmmetb);
		sbjjrbtn = (Button) findViewById(R.id.sbjjrbtn);
		sbjjrrla.setOnClickListener(this);
		sbjjrbtn.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.sbjjrbtn:
			sbjjrnumber = sbjjrheta.getText().toString().trim();
			sbjjrpwd = sbjjrmmetb.getText().toString().trim();
			if (sbjjrheta.length() < 1) {
				sbjjrheta.setError(getResources()
						.getString(R.string.qsrsbjjrzh));

			} else {
				if (sbjjrmmetb.length() < 1) {
					sbjjrmmetb.setError(getResources()
							.getString(R.string.qsrmm));
				} else {
					if (ToolUtils.panduanteshuzhifu(sbjjrpwd) == false) {
						showToast(getResources().getString(
								R.string.gsjhcyfcysbdlxyxyz));
						return;
					}
					yz();
				}
			}
			break;
		case R.id.sbjjrrla:
			finish();
			break;
		}
	}

	private void yz() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "agent_money");
		map.put("username", sbjjrnumber);
		map.put("pwd", sbjjrpwd);
		map.put("order_no", orderno);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.jjrtc + jiaMi;
		showProgressDialog(getResources().getString(R.string.jzz));
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				Sbjjrhelper sbjjrhelper = GsonUtils.json2Bean(arg0.result,
						Sbjjrhelper.class);
				if (sbjjrhelper.status.equals("0")) {
					money = sbjjrhelper.money;
					sharedpreferencesb = getSharedPreferences("jga",
							Activity.MODE_PRIVATE);
					editor = sharedpreferencesb.edit();
					editor.putString("orderamount", orderamounta);
					editor.putString("orderno", orderno);
					editor.putString("dkjsyf", dkjsyf);
					editor.putString("tjmsyf", "1");
					editor.putString("spid", sbjjrnumber);
					editor.putString("sppwd", sbjjrpwd);
					editor.putString("dhm", dhm);
					editor.commit();
					showToast(getResources().getString(R.string.yztg));
					finish();
				} else {
					showToast(getResources().getString(R.string.srdzhhmmcw));
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				dismissProgressDialog();
				showToast(getResources().getString(R.string.wlyc));
			}
		});
	}
}