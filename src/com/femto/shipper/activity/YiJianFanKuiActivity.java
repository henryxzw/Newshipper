package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;

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

public class YiJianFanKuiActivity extends BaseActivity implements OnClickListener
{
	private EditText editext_yijian;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yijianfankui);
		sharedPreferences = getSharedPreferences(getResources().getString(R.string.sqlname), Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(getResources().getString(R.string.pwd), "");
		findViewById(R.id.btn_tijiao).setOnClickListener(this);
		editext_yijian = (EditText) findViewById(R.id.editext_yijian);
		findViewById(R.id.left).setOnClickListener(this);
		// /设置收藏和黑名单位置
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int height = dm.heightPixels;
		LayoutParams rlawidth1 = (LayoutParams) editext_yijian.getLayoutParams();
		rlawidth1.height = height * 3 / 5;
		editext_yijian.setLayoutParams(rlawidth1);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.left:
			finish();
			break;
		case R.id.btn_tijiao:// 提交

			String StrNeiRong = editext_yijian.getText().toString();
			if (TextUtils.isEmpty(StrNeiRong))
			{
				showToast("请输入意见内容");
				return;
			}

			Map<String, String> map = new HashMap<String, String>();
			map.put("key", "feedback");
			map.put("username", phonea);
			map.put("content", StrNeiRong);

			String jiaMi = ToolUtils.JiaMi(map);
			String url = Net.YIJIANFANKUI + jiaMi;
			LogUtils.i("请求的url:" + url);
			showProgressDialog("加载中...");

			application.doGet(url, new RequestCallBack<String>()
			{
				@Override
				public void onSuccess(ResponseInfo<String> arg0)
				{
					LogUtils.i(arg0.result);
					dismissProgressDialog();
					StatusBean statusBean = GsonUtils.json2Bean(arg0.result, StatusBean.class);
					if (statusBean.status.equals("0"))
					{
						showToast("意见提交成功");
						finish();
					} else
					{
						showToast(statusBean.msg);
					}
				}

				@Override
				public void onFailure(HttpException arg0, String arg1)
				{
					LogUtils.i("" + arg1);
					dismissProgressDialog();
				}
			});

			break;
		}
	}

	public void onResume()
	{
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause()
	{
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
