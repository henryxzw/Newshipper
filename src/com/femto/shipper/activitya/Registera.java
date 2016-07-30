package com.femto.shipper.activitya;

import java.util.HashMap;
import java.util.Map;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class Registera extends BaseActivity implements OnClickListener {
	private EditText etraphonenumber, etrayzm;
	private Button btnrayzm, btnra;
	private RelativeLayout zcrla;
	public static Activity registera;
	private String jiami, url, phone, jiamia, urla;
	private Map<String, String> map, mapa;
	private StatusBean statusbean, statusbeana;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.registera);
		registera = this;
		etraphonenumber = (EditText) findViewById(R.id.etraphonenumber);
		etrayzm = (EditText) findViewById(R.id.etrayzm);
		btnrayzm = (Button) findViewById(R.id.btnrayzm);
		btnra = (Button) findViewById(R.id.btnra);
		zcrla = (RelativeLayout) findViewById(R.id.zcrla);
		zcrla.setOnClickListener(this);
		btnrayzm.setOnClickListener(this);
		btnra.setOnClickListener(this);
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
		case R.id.btnra:
			Tiaozyz();
			break;
		case R.id.btnrayzm:
			GetCode();
			countDownTimer.start();
			break;
		case R.id.zcrla:
			finish();
			break;
		}
	}

	private void Tiaozyz() {
		final String phone = etraphonenumber.getText().toString();
		final String yanzhengCode = etrayzm.getText().toString();
		if (etraphonenumber.getText().length() > 0) {
			if (etraphonenumber.getText().length() != 11) {
				etraphonenumber.setText("");
				etraphonenumber.setError(getResources().getString(
						R.string.qsrhfdsjhm));
				showToast(getResources().getString(R.string.qsrhfdsjhm));
				return;
			} else {
				if (etrayzm.getText().length() > 0) {
					map = new HashMap<String, String>();
					map.put("key", "verify_sms_code");
					map.put("code_type", "reg");
					map.put("code", yanzhengCode);
					map.put("mobile", phone);
					jiami = ToolUtils.JiaMi(map);
					url = Net.YANZHENGCODE + jiami;
					showProgressDialog(getResources().getString(R.string.fsz));
					application.doget(url, new RequestCallBack<String>() {
						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							dismissProgressDialog();
							statusbean = GsonUtils.json2Bean(arg0.result,
									StatusBean.class);
							if (statusbean.status.equals("0")) {
								Intent intenta = new Intent(Registera.this,
										Registerb.class);
								Bundle bundleb = new Bundle();
								bundleb.putString("phone", phone);
								intenta.putExtras(bundleb);
								startActivity(intenta);
							} else {
								showToast(statusbean.msg);
							}
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							dismissProgressDialog();
							showToast(getResources().getString(R.string.wlyc));
						}
					});
				} else {
					showToast(getResources().getString(R.string.qsryzm));
				}
			}
		} else {
			showToast(getResources().getString(R.string.qsrsjhm));
		}
	}

	private void GetCode() {
		phone = etraphonenumber.getText().toString().trim();
		if (etraphonenumber.getText().length() != 11) {
			etraphonenumber.setText("");
			etraphonenumber.setError(getResources().getString(
					R.string.qsrhfdsjhm));
			showToast(getResources().getString(R.string.qsrhfdsjhm));
			return;
		} else {
			mapa = new HashMap<String, String>();
			mapa.put("key", "send_sms");
			mapa.put("mobile", phone);
			mapa.put("code_type", "reg");
			jiamia = ToolUtils.JiaMi(mapa);
			urla = Net.GETCODE + jiamia;
			application.doget(urla, new RequestCallBack<String>() {
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					statusbeana = GsonUtils.json2Bean(arg0.result,
							StatusBean.class);
					if (statusbeana.status.equals("0")) {
						showToast(getResources().getString(R.string.yzmyfsdsj));
						// countDownTimer.start();
					} else {
						showToast(statusbeana.msg);
					}
				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					showToast(getResources().getString(R.string.wlyc));
				}
			});
		}
	}

	private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
		@Override
		public void onTick(long millisUntilFinished) {
			int k = (int) (millisUntilFinished / 1000);
			btnrayzm.setText(k + "");
			btnrayzm.setEnabled(false);
			btnrayzm.setTextColor(getResources().getColor(R.color.text_gray2));
		}

		@Override
		public void onFinish() {
			btnrayzm.setText(getResources().getString(R.string.msd));
			btnrayzm.setTextColor(getResources().getColor(R.color.baise));
			btnrayzm.setEnabled(true);
		}
	};
}