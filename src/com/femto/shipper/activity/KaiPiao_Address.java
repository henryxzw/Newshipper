package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.UpdateTuXiangBean;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class KaiPiao_Address extends BaseActivity implements OnClickListener
{
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private EditText kp_add_name, kp_add_phone, kp_add_dizhi, kp_add_youbian;
	private LinearLayout kp_add_tijiao;
	private String address, mobile, contact_name, zipcode, id;
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kaipiao_addressactivity);
		sharedPreferences = getSharedPreferences(getResources().getString(R.string.sqlname), Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(getResources().getString(R.string.pwd), "");
		info();
	}

	private void info()
	{
		Intent it = getIntent();
		type = it.getStringExtra("type");
		kp_add_name = (EditText) findViewById(R.id.kp_add_name);
		kp_add_phone = (EditText) findViewById(R.id.kp_add_phone);
		kp_add_dizhi = (EditText) findViewById(R.id.kp_add_dizhi);
		kp_add_youbian = (EditText) findViewById(R.id.kp_add_youbian);

		kp_add_tijiao = (LinearLayout) findViewById(R.id.kp_add_tijiao);
		kp_add_tijiao.setOnClickListener(this);
		findViewById(R.id.left).setOnClickListener(this);

		if (type.equals("1"))
		{
			address = it.getStringExtra("address");
			mobile = it.getStringExtra("mobile");
			contact_name = it.getStringExtra("contact_name");
			zipcode = it.getStringExtra("zipcode");
			id = it.getStringExtra("id");
			kp_add_name.setText(contact_name);
			kp_add_phone.setText(mobile);
			kp_add_dizhi.setText(address);
			kp_add_youbian.setText(zipcode);

		}

	}

	private void getinfo()
	{
		String name = kp_add_name.getText().toString().trim();
		String phone = kp_add_phone.getText().toString().trim();
		String dizi = kp_add_dizhi.getText().toString().trim();
		String youbian = kp_add_youbian.getText().toString().trim();
		LogUtils.i("----" + name + "------" + phone + "------" + dizi + "-------" + youbian + "---------");
		if (!name.equals(""))
		{
			contact_name = name;
		} else
		{
			showToast("姓名不能为空");
		}
		if (!phone.equals(""))
		{

			mobile = phone;
		} else
		{
			showToast("电话不能为空");
		}
		if (!dizi.equals(""))
		{
			address = dizi;
		} else
		{
			showToast("地址不能为空");
		}
		if (!youbian.equals(""))
		{
			zipcode = youbian;
		} else
		{
			showToast("邮编不能为空");
		}

		if (!phonea.equals("") || address.equals("") || !mobile.equals("") || !contact_name.equals("") || !zipcode.equals(""))
		{
			Map<String, String> map = new HashMap<String, String>();
			if (type.equals("0"))
			{
				map.put("key", "add_invoice_addr");
			}
			if (type.equals("1"))
			{
				map.put("key", "edit_invoice_addr");
				map.put("id", id);
			}

			map.put("username", phonea);
			map.put("address", address);
			map.put("mobile", mobile);
			map.put("contact_name", contact_name);
			map.put("zipcode", zipcode);
			String jiaMi = ToolUtils.JiaMi(map);
			String url = Net.TONGYONG + jiaMi;
			LogUtils.i("请求的url:" + url);
			showProgressDialog("加载中...");
			application.doget(url, new RequestCallBack<String>()
			{

				@Override
				public void onFailure(HttpException arg0, String arg1)
				{
					// TODO Auto-generated method stub
					LogUtils.i("加载失败" + arg1);
					dismissProgressDialog();
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0)
				{
					// TODO Auto-generated method stub
					LogUtils.i(arg0.result);
					dismissProgressDialog();
					UpdateTuXiangBean UpdateTuXiangBean = GsonUtils.json2Bean(arg0.result, UpdateTuXiangBean.class);
					if (UpdateTuXiangBean.status.equals("0"))
					{
						showToast("提交成功");
						finish();
					} else
					{
						showToast(UpdateTuXiangBean.msg);
					}
				}
			});

		} else
		{
			showToast("请输入完整信息");
		}

	}

	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub
		if (isFastDoubleClick())
		{
			return;
		}
		switch (arg0.getId())
		{
		case R.id.left:
			KaiPiao_Address.this.finish();
			break;
		case R.id.kp_add_tijiao:
			getinfo();
			break;

		default:
			break;
		}
	}

	public void onResume()
	{
		super.onResume();
	}

	public void onPause()
	{
		super.onPause();
	}
}
