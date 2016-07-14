package com.femto.shipper.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.adapter.SlideCutListView;
import com.femto.shipper.adapter.SlideCutListView.RemoveDirection;
import com.femto.shipper.adapter.SlideCutListView.RemoveListener;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.KaipiaoBean;
import com.femto.shipper.bean.UpdateTuXiangBean;
import com.femto.shipper.bean.KaipiaoBean.KaipiaoBeanZ;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

/**
 * @author mac 开票信息
 */
public class KaiPiaoXinXiActivity extends BaseActivity implements
		OnClickListener, RemoveListener {

	private String phonea, passworda;
	private SharedPreferences sharedPreferences;

	private String str;
	private EditText kaipiao_money;
	private EditText kaipiao_moneyTT;
	private EditText kaipiao_moneycontext;
	private Button kaipiao_new_dizi;
	private TextView kaipiao_moneyMax;
	private LinearLayout line_kiapiao_tijiao;
	private KaipiaoBean Kaipiao;
	private MyAdapter adapter;
	private SlideCutListView kaipiao_listView;
	List<KaipiaoBeanZ> Kaipiaolist = new ArrayList<KaipiaoBeanZ>();
	private String name, phone, dizi, youbian, max_money;
	private int temp = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kaipiaoxinxi);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		adapter = new MyAdapter(mContext);
		str = "0";
		info();

	}

	private void info() {
		max_money = getIntent().getStringExtra("max_money");
		findViewById(R.id.left).setOnClickListener(this);
		kaipiao_money = (EditText) findViewById(R.id.kaipiao_money);
		kaipiao_moneyTT = (EditText) findViewById(R.id.kaipiao_moneyTT);
		kaipiao_new_dizi = (Button) findViewById(R.id.kaipiao_new_dizi);
		kaipiao_listView = (SlideCutListView) findViewById(R.id.kaipiao_listView);
		line_kiapiao_tijiao = (LinearLayout) findViewById(R.id.line_kiapiao_tijiao);
		kaipiao_moneycontext = (EditText) findViewById(R.id.kaipiao_moneycontext);
		kaipiao_moneyMax = (TextView) findViewById(R.id.kaipiao_moneyMax);
		kaipiao_moneyMax.setText("¥" + max_money);
		kaipiao_listView.setRemoveListener(this);
		kaipiao_moneyTT.setOnClickListener(this);
		kaipiao_new_dizi.setOnClickListener(this);
		line_kiapiao_tijiao.setOnClickListener(this);

		getinfo();

	}

	public void getinfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "invoice_addr_list");
		map.put("username", phonea);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.KAIPIAODONGZUO + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("提交中...");
		application.doGet_kuaishu(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				Kaipiaolist.clear();
				Kaipiao = GsonUtils.json2Bean(arg0.result, KaipiaoBean.class);
				if (Kaipiao.status.equals("0")) {

					if (Kaipiao.list.size() != 0) {
						Kaipiaolist.addAll(Kaipiao.list);
						adapter.setList(Kaipiaolist);

					} else {
					}
					kaipiao_listView.setAdapter(adapter);
				} else {
					showToast(Kaipiao.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}
		});
	}

	private void tankuang(final String id) {

		Builder builder = new Builder(this);
		builder.setTitle("是否确认删除");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dele(id);
				LogUtils.i("------id-------->>" + id);
				dialog.dismiss();

			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.create().show();
	}

	private void dele(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "del_invoice_addr");
		map.put("id", id);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.TONGYONG + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet_kuaishu(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				LogUtils.i("加载失败" + arg1);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				UpdateTuXiangBean UpdateTuXiangBean = GsonUtils.json2Bean(
						arg0.result, UpdateTuXiangBean.class);
				if (UpdateTuXiangBean.status.equals("0")) {
					getinfo();
				}
			}
		});

	}

	private void getinto(String money, String money1, String context) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "add_invoice_normal");
		map.put("username", phonea);
		map.put("amount", money);
		map.put("address", dizi);
		map.put("mobile", phone);
		map.put("name", name);
		map.put("content", context);
		map.put("title", money1);

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.TONGYONG + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet_kuaishu(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				LogUtils.i("加载失败" + arg1);
				showToast("网络异常");
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				UpdateTuXiangBean UpdateTuXiangBean = GsonUtils.json2Bean(
						arg0.result, UpdateTuXiangBean.class);
				if (UpdateTuXiangBean.status.equals("0")) {
					KaiPiaoXinXiActivity.this.finish();
					showToast("提交成功");
				} else {
					showToast("提交失败");
				}
			}
		});
	}

	class MyAdapter extends ListViewAdapter<KaipiaoBeanZ> {

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup arg2) {
			final Holder holder;
			if (convertView == null) {
				holder = new Holder();
				convertView = View.inflate(mContext, R.layout.kaipiao_itme,
						null);
				holder.kaipiao_itme_moren = (RadioButton) convertView
						.findViewById(R.id.kaipiao_itme_moren);
				holder.kaipiao_itme_name = (TextView) convertView
						.findViewById(R.id.kaipiao_itme_name);
				holder.kaipiao_itme_phone = (TextView) convertView
						.findViewById(R.id.kaipiao_itme_phone);
				holder.kaipiao_itme_dizi = (TextView) convertView
						.findViewById(R.id.kaipiao_itme_dizi);
				holder.kaipiao_itme_next = (ImageView) convertView
						.findViewById(R.id.kaipiao_itme_next);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();

			}

			final KaipiaoBeanZ orderz = myList.get(position);
			holder.kaipiao_itme_name.setText(orderz.contact_name);
			holder.kaipiao_itme_dizi.setText(orderz.address);
			holder.kaipiao_itme_phone.setText(orderz.mobile);
			if (orderz.status.equals("1")) {
				holder.kaipiao_itme_moren.setChecked(true);
				name = orderz.contact_name;
				phone = orderz.mobile;
				dizi = orderz.address;
				youbian = orderz.zipcode;
				str = "1";
			} else {
				holder.kaipiao_itme_moren.setChecked(false);
			}
			// 单选监听
			holder.kaipiao_itme_moren.setId(position);
			holder.kaipiao_itme_moren
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							if (holder.kaipiao_itme_moren.isChecked()) {

								if (temp != -1) {

									RadioButton tempButton = (RadioButton) findViewById(temp);
									// /判断点击同一个itme时的反应
									if (temp == position) {
										// 默认保持不变
										if (tempButton != null) {

											tempButton.setChecked(true);

										}
									} else {
										if (tempButton != null) {
											tempButton.setChecked(false);

										}
									}

								}
								temp = holder.kaipiao_itme_moren.getId();
								name = orderz.contact_name;
								phone = orderz.mobile;
								dizi = orderz.address;
								youbian = orderz.zipcode;
								str = "1";
								LogUtils.i("---------temp------->>" + temp);
								LogUtils.i("--------position-------->>"
										+ position);
								LogUtils.i("-------地址--------->>" + name
										+ "---->>" + phone + "---->>" + dizi
										+ "---->>" + youbian);
							}

						}
					});

			holder.kaipiao_itme_next
					.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Intent it = new Intent(KaiPiaoXinXiActivity.this,
									KaiPiao_Address.class);
							it.putExtra("type", "1");
							it.putExtra("address", orderz.address);
							it.putExtra("mobile", orderz.mobile);
							it.putExtra("contact_name", orderz.contact_name);
							it.putExtra("zipcode", orderz.zipcode);
							it.putExtra("id", orderz.id);
							startActivity(it);
						}
					});

			return convertView;
		}
	}

	class Holder {
		RadioButton kaipiao_itme_moren;
		TextView kaipiao_itme_name;
		TextView kaipiao_itme_phone;
		TextView kaipiao_itme_dizi;
		ImageView kaipiao_itme_next;

	}

	@Override
	public void onClick(View v) {
		if (isFastDoubleClick()) {
			return;
		}
		switch (v.getId()) {
		case R.id.line_kiapiao_tijiao:// 开票提交
			Intent it = new Intent(this, KaiPiao_Address.class);
			it.putExtra("type", "0");
			startActivity(it);
			break;
		case R.id.kaipiao_new_dizi:// ////////增加地址
			String money1 = kaipiao_money.getText().toString();
			String money2 = kaipiao_moneyTT.getText().toString();
			String context = kaipiao_moneycontext.getText().toString();
			if (money1.equals("") && money2.equals("")) {
				showToast("填写不完整");
			} else {
				if (Double.valueOf(money1) > Double.valueOf(max_money)
						|| Double.valueOf(money1) == 0) {
					showToast("输入金额不能大于最大金额度");
					kaipiao_money.setText("");
				} else {
					if (str.equals("1")) {
						getinto(money1, money2, context);
					} else {
						showToast("请选着地址");
					}
				}

			}

			break;
		case R.id.left:
			finish();
			break;

		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		getinfo();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		getinfo();
	}

	@Override
	public void removeItem(RemoveDirection direction, int position) {

		switch (direction) {
		case RIGHT:
			LogUtils.i("----------------->向右滑动");
			String id1 = Kaipiao.list.get(position).id;
			tankuang(id1);
			break;
		case LEFT:
			LogUtils.i("----------------->向左滑动");
			String id2 = Kaipiao.list.get(position).id;
			tankuang(id2);
			break;
		default:
			break;
		}
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
