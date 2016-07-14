package com.femto.shipper.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.femto.shipper.R;
import com.femto.shipper.activitya.Bimp;
import com.femto.shipper.activitya.Ycactivitya;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.My_CheDuiBean;
import com.femto.shipper.bean.My_CheDuiBean.CheDuiList;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

public class My_CheDuiActivity extends BaseActivity implements OnClickListener {
	private ListView my_list;
	List<CheDuiList> CheDuiListZ = new ArrayList<CheDuiList>();
	private MyAdapter adapter;
	private String phonea;
	private SharedPreferences sharedPreferences, sharedpreferencesb;
	private String i;
	private String count = "0";
	private Button msycben;
	private Editor editora;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_cheduiactivity);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		adapter = new MyAdapter(mContext);
		msycben = (Button) findViewById(R.id.msycben);
		findViewById(R.id.left).setOnClickListener(this);
		my_list = (ListView) findViewById(R.id.my_list);
		i = "1";
		msycben.setOnClickListener(this);
	}

	public void getlis(final String category) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "driver_list");
		map.put("username", phonea);
		map.put("category", category);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.UPDATE_IMSGE + jiaMi;
		showProgressDialog("加载中...");
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("网络异常");
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				My_CheDuiBean My_CheDuiBean = GsonUtils.json2Bean(arg0.result,
						My_CheDuiBean.class);
				if (My_CheDuiBean.status.equals("0")) {
					CheDuiListZ.clear();
					if (My_CheDuiBean.list.size() != 0
							|| My_CheDuiBean.list == null) {
						CheDuiListZ.addAll(My_CheDuiBean.list);
						adapter.setList(CheDuiListZ);
						my_list.setAdapter(adapter);
						if (My_CheDuiBean.list.size() != 0
								&& category.equals("1")) {
							count = String.valueOf(My_CheDuiBean.list.size());
							SharedPreferences mySharedPreferences = getSharedPreferences(
									"count", Activity.MODE_PRIVATE);
							SharedPreferences.Editor editor = mySharedPreferences
									.edit();
							editor.putString("cdcount", count);
							editor.commit();
						}
					}else{
						adapter.setList(CheDuiListZ);
						my_list.setAdapter(adapter);
					}
				} else {
					showToast(My_CheDuiBean.msg);
					showToast("数据有误!");
				}

			}
		});

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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (isFastDoubleClick()) {
			return;
		}
		switch (arg0.getId()) {
		case R.id.msycben:
			msyc();
			break;
		// case R.id.my_shochang1:
		// layout_ll.setBackgroundResource(R.drawable.jilu);
		// CheDuiListZ.clear();
		// i = "1";
		// getlis(i);
		// my_list.setAdapter(adapter);
		// break;
		// case R.id.my_heimingdan:
		// layout_ll.setBackgroundResource(R.drawable.shocang);
		// CheDuiListZ.clear();
		// i = "0";
		// getlis(i);
		// my_list.setAdapter(adapter);
		// break;
		case R.id.left:
			Intent intent = new Intent();
			intent.putExtra("cdcount", count);
			setResult(Activity.RESULT_OK, intent);
			finish();
			break;

		default:
			break;
		}
	}

	class MyAdapter extends ListViewAdapter<CheDuiList> {

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			Holder holder;
			if (convertView == null) {
				holder = new Holder();
				convertView = View.inflate(mContext, R.layout.my_chedui_itme,
						null);
				holder.tuoxiang = (ImageView) convertView
						.findViewById(R.id.my_chedui_sijiImg);
				holder.pingxing = (TextView) convertView
						.findViewById(R.id.my_chedui_siji_star);
				holder.phone = (ImageView) convertView
						.findViewById(R.id.my_chedui_siji_call);
				holder.nice_siji = (TextView) convertView
						.findViewById(R.id.my_chedui_siji_name);
				holder.car_xing = (TextView) convertView
						.findViewById(R.id.my_chedui_siji_plate_number);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			final CheDuiList orderz = myList.get(position);
			if (myList.size() != 0) {
				if (!orderz.avatar.equals("")) {
					ImageLoader.getInstance().displayImage(
							Net.PICURL + orderz.avatar, holder.tuoxiang,
							application.options);
				}
				if (Float.valueOf(orderz.star) == 3) {
					holder.pingxing.setBackgroundResource(R.drawable.x3);
				} else if (Float.valueOf(orderz.star) >= 3
						&& Float.valueOf(orderz.star) < 3.5) {
					holder.pingxing.setBackgroundResource(R.drawable.x3);
				} else if (Float.valueOf(orderz.star) >= 3.5
						&& Float.valueOf(orderz.star) < 4) {
					holder.pingxing.setBackgroundResource(R.drawable.x3b);
				} else if (Float.valueOf(orderz.star) >= 4
						&& Float.valueOf(orderz.star) < 4.5) {
					holder.pingxing.setBackgroundResource(R.drawable.x4);
				} else if (Float.valueOf(orderz.star) >= 4.5
						&& Float.valueOf(orderz.star) < 5) {
					holder.pingxing.setBackgroundResource(R.drawable.x4b);
				} else if (Float.valueOf(orderz.star) == 5) {
					holder.pingxing.setBackgroundResource(R.drawable.x5);
				} else if (Float.valueOf(orderz.star) == 0) {
					holder.pingxing.setBackgroundResource(R.drawable.kx5);
				}
				holder.nice_siji.setText(orderz.nick_name);
				holder.car_xing.setText(orderz.plate_number);
			}

			holder.phone.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setAction("android.intent.action.DIAL");
					intent.setData(Uri.parse("tel:" + orderz.phone));
					startActivity(intent);
				}
			});
			holder.tuoxiang.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent it = new Intent(mContext,
							SiJi_informationActivity.class);
					it.putExtra("driver_id", orderz.driver_id);
					startActivity(it);
				}
			});
			return convertView;
		}

	}

	class Holder {
		ImageView tuoxiang;
		TextView pingxing;
		TextView nice_siji;
		TextView car_xing;
		ImageView phone;

	}

	// // ////////////单击返回//////////////////////
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 按下键盘上的返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.putExtra("cdcount", count);
			setResult(Activity.RESULT_OK, intent);
		}
		return super.onKeyDown(keyCode, event);

	}

	public void onResume() {
		super.onResume();
		getlis(i);
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
