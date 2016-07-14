package com.femto.shipper.activitya;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.view.WindowManager;
import com.femto.shipper.R;
import com.femto.shipper.activity.MainActivity;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class Mywelcome extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.welcomea);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		freelogin();
	}

	public void freelogin()
	{
		String phonea = SharedPreferencesUtils.getString(mContext, Constants.USERPHONE, "");
		String passworda = SharedPreferencesUtils.getString(mContext, Constants.USERPASSWORD, "");
		TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceIda = tm.getDeviceId();
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("key", "user_login");
		mapa.put("username", phonea);
		mapa.put("pwd", passworda);
		mapa.put("code", deviceIda);
		String jiaMia = ToolUtils.JiaMi(mapa);
		String urla = Net.YANZHENGCODE + jiaMia;
		application.doGet(urla, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result, StatusBean.class);
				if (statusBean.status.equals("0"))
				{
					startActivity(new Intent(Mywelcome.this, MainActivity.class));
				} else
				{
					new Handler().postDelayed(new Runnable()
					{
						@Override
						public void run()
						{
							Intent inta = new Intent(Mywelcome.this, LanActivity.class);
							startActivity(inta);
							finish();
						}
					}, 1000);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
				dismissProgressDialog();
			}
		});
	}
}
