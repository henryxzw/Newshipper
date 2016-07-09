package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @author mac
 * 派单失败
 */
public class TaskFailureActivity extends BaseActivity implements OnClickListener
{
	
	private String order_no;
	private String remark;
	private String userphone;
	private String password;
	private TextView text_yuany;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taskfail);
		sharedPreferences = getSharedPreferences(getResources().getString(R.string.sqlname), Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(getResources().getString(R.string.pwd), "");
		LogUtils.i("onCreate");
		Intent intent = getIntent();
		order_no = intent.getStringExtra("order_no");
		remark = intent.getStringExtra("remark");
		text_yuany = (TextView) findViewById(R.id.text_yuany);
		findViewById(R.id.btn_cancle).setOnClickListener(this);
		findViewById(R.id.btn_chongxin).setOnClickListener(this);
		findViewById(R.id.left).setOnClickListener(this);
        application.userPhone =  userphone = SharedPreferencesUtils.getString(mContext, Constants.USERPHONE, "");
        application.password =  password = SharedPreferencesUtils.getString(mContext, Constants.USERPASSWORD, "");
        if(!TextUtils.isEmpty(remark)){
        	text_yuany.setText(remark);
        }
	}
	
	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		LogUtils.i("onNewIntent");
		order_no = intent.getStringExtra("order_no");
		remark = intent.getStringExtra("remark");
		userphone = SharedPreferencesUtils.getString(mContext, Constants.USERPHONE, "");
        password = SharedPreferencesUtils.getString(mContext, Constants.USERPASSWORD, "");
        if(!TextUtils.isEmpty(remark)){
        	text_yuany.setText(remark);
        }
	}
	

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.left:
			finish();
			break;
		case R.id.btn_cancle://取消派单 并退款
			cancleorder();
			break;
		case R.id.btn_chongxin:////重新派单
			chongxinpaidan();
			break;
		}
	}

	//取消派单 并退款   没有借口
	private void cancleorder()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "cancel_order");
		map.put("username",userphone);
		map.put("pwd", password);
		map.put("order_no",order_no);
		map.put("reason", "");////取消原因

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.QUXIAODINGDAN + jiaMi;
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
				if(statusBean.status.equals("0")){
					showToast("取消成功");
					finish();
				}else{
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
	}

	//重新派单
	private void chongxinpaidan()
	{
		String username = SharedPreferencesUtils.getString(TaskFailureActivity.this, Constants.USERPHONE, "");
		String pwd = SharedPreferencesUtils.getString(TaskFailureActivity.this, Constants.USERPASSWORD, "");
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "push_order_again");
		map.put("username",username);
		map.put("pwd", pwd);
		map.put("order_no", order_no);
		
		
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.CHONGXINPAIDAN + jiaMi;
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
				if(statusBean.status.equals("0")){
					
					AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
					builder.setMessage("重新派单成功");
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
							finish();
						}
					});
					builder.show();
					
				}else{
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
	}
}
