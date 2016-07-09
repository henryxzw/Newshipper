package com.femto.shipper.activitya;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import cn.jpush.android.api.JPushInterface;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

public class ForgetPasswordActivity extends BaseActivity implements
		OnClickListener {

	private Button text_daojishi;
	private EditText editext_xinmima;
	private String phone, code, password, phonea;
	private EditText editext_phone, editext_code;
	private ImageButton wjimbtn;
	private SharedPreferences sharedpreferencesa, sharedpreferencesb;
	private Editor editor = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_forget_password);
		sharedpreferencesa = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedpreferencesa.getString(
				getResources().getString(R.string.phone), "");
		wjimbtn = (ImageButton) findViewById(R.id.wjimbtn);
		text_daojishi = (Button) findViewById(R.id.text_daojishi);
		text_daojishi.setOnClickListener(this);
		editext_phone = (EditText) findViewById(R.id.editext_phone);
		editext_code = (EditText) findViewById(R.id.editext_code);
		editext_xinmima = (EditText) findViewById(R.id.editext_xinmima);
		findViewById(R.id.btn_denglu).setOnClickListener(this);
		wjimbtn.setOnClickListener(this);
		if (!phonea.equals("") || phonea != null) {
			editext_phone.setText(phonea);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.wjimbtn:
			finish();
			break;
		case R.id.text_daojishi:
			GetCode();
			countDownTimer.start();
			break;
		case R.id.btn_denglu:
			ChangPassword();
			break;
		}
	}

	@Override
	protected void onDestroy() {
		if (editor != null) {
			editor.clear();
		}
		super.onDestroy();
	}

	private void ChangPassword() {
		phone = editext_phone.getText().toString().trim();
		code = editext_code.getText().toString().trim();
		password = editext_xinmima.getText().toString().trim();
		if (editext_phone.getText().length() != 11) {
			showToast(getResources().getString(R.string.qsrhfdsjhm));
			return;
		}
		if (TextUtils.isEmpty(code)) {
			showToast(getResources().getString(R.string.qsryzm));
			return;
		}
		if (ToolUtils.panduanteshuzhifu(password) == false) {
			showToast(getResources().getString(R.string.srdmmbnbkbdfhdtszf));
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showToast(getResources().getString(R.string.qsrmm));
			return;
		}
		if (editext_xinmima.getText().length() < 6) {
			showToast(getResources().getString(R.string.mmbnsylw));
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", "forget_pwd");
			map.put("username", phone);
			map.put("new_pwd", password);
			map.put("code", code);
			String jiaMi = ToolUtils.JiaMi(map);
			String url = Net.FORGETPASSWORD + jiaMi;
			showProgressDialog(getResources().getString(R.string.jzz));
			application.doget(url, new RequestCallBack<String>() {
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					LogUtils.i("" + arg0.result);
					dismissProgressDialog();
					StatusBean statusBean = GsonUtils.json2Bean(arg0.result,
							StatusBean.class);
					if (statusBean.status.equals("0")) {
						sharedpreferencesb = getSharedPreferences(
								getResources().getString(R.string.sqlname),
								Activity.MODE_PRIVATE);
						editor = sharedpreferencesb.edit();
						editor.putString(
								getResources().getString(R.string.phone), phone);
						editor.putString(
								getResources().getString(R.string.pwd),
								password);
						editor.commit();
						ForgetPasswordActivity.this.finish();
						showToast(getResources().getString(R.string.xgcg));
					} else {
						showToast(statusBean.msg);
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

	private void GetCode() {
		String phone = editext_phone.getText().toString();
		if (editext_phone.getText().length() != 11) {
			showToast(getResources().getString(R.string.qsrhfdsjhm));
			return;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "send_sms");
		map.put("code_type", "pwd");
		map.put("mobile", phone);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.GETCODE + jiaMi;

		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result,
						StatusBean.class);
				if (statusBean.status.equals("0")) {
					showToast(getResources().getString(R.string.yzmyfsdsj));
					// countDownTimer.start();
				} else {
					showToast(statusBean.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast(getResources().getString(R.string.wlyc));
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(mContext);
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(mContext);
		MobclickAgent.onPause(this);
	}

	private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
		@Override
		public void onTick(long millisUntilFinished) {
			int k = (int) (millisUntilFinished / 1000);
			text_daojishi.setText(k + "");
			text_daojishi.setEnabled(false);
			text_daojishi.setTextColor(getResources().getColor(
					R.color.text_gray2));
		}

		@Override
		public void onFinish() {
			text_daojishi.setText(getResources().getString(R.string.msd));
			text_daojishi.setTextColor(getResources().getColor(R.color.baise));
			text_daojishi.setEnabled(true);
		}
	};
}