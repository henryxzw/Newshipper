package com.femto.shipper.base;

import java.util.HashMap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.femto.shipper.R;
import com.femto.shipper.application.DemoApplication;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.view.CustomProgressDialog;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BaseActivity extends FragmentActivity {

	public Context mContext;
	public DemoApplication application;
	private CustomProgressDialog pd;
	public ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		application = DemoApplication.getInstance();
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	protected void onResume() {
		IntentFilter filter = new IntentFilter();
		filter.addAction("ExitApp");
		this.registerReceiver(this.broadcastreceiver, filter);
		Myservice myservice = new Myservice();
		IntentFilter filtera = new IntentFilter();
		filtera.addAction("com.femto.shipper.activitya.Aapdbroad");
		this.registerReceiver(myservice, filtera);
		super.onResume();
	}

	class Myservice extends BroadcastReceiver {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Bundle bundle = arg1.getExtras();
			String send = bundle.getString("send");
			if (send.equals("useronlinea")) {
				SharedPreferences sharedPreferences = getSharedPreferences(
						getResources().getString(R.string.sqlname),
						Activity.MODE_PRIVATE);
				String phonea = sharedPreferences.getString(getResources()
						.getString(R.string.phone), "");
				HashMap map = new HashMap<String, String>();
				map.put("key", "user_online");
				map.put("username", phonea);
				String jiami = ToolUtils.JiaMi(map);
				String url = Net.DENGLU + jiami;
				application.doget(url, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						showToast("网络异常");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						StatusBean statusbean = GsonUtils.json2Bean(
								arg0.result, StatusBean.class);
						if (statusbean == null) {
							return;
						}
						if (statusbean.status.equals("0")) {
							showToast("检测app成功");
						} else {
							showToast("检测app失败");
						}
					}
				});
			}

		}
	}

	private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			finish();
		}
	};

	@Override
	protected void onDestroy() {
		this.unregisterReceiver(this.broadcastreceiver);
		super.onDestroy();
	}

	// ////toast鏄剧ず
	public void showToast(String message) {
		Toast.makeText(mContext, message, 0).show();
	}

	// ////蹇嵎璺宠浆
	public <T> void startActivity(Class<T> class1) {
		startActivity(new Intent(this, class1));
	}

	/**
	 * 鏄剧ず鍔犺浇妗�
	 */
	public void showProgressDialog(String message) {
		if (pd == null) {
			pd = CustomProgressDialog.createDialog(this);
			pd.setMessage(message);
		}
		pd.setCanceledOnTouchOutside(false);
		pd.show();
	}

	/**
	 * 鍙栨秷鍔犺浇妗�
	 */
	public void dismissProgressDialog() {
		if (pd != null) {
			pd.dismiss();
			pd = null;
		} else {
			return;
		}
	}

	public void run() {
		// TODO Auto-generated method stub

	}

	private long lastClickTime;

	public boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 500) {

			return true;
		}
		lastClickTime = time;
		return false;
	}

	public boolean areAllItemsEnabled() {
		return false;
	}

	public boolean isEnabled(int position) {
		return false;
	}

}
