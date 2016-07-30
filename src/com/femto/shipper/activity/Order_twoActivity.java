package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.chatuidemo.db.DBSqlite;
import com.easemob.chatuidemo.db.DBSqlite_Y;
import com.easemob.chatuidemo.db.Order_SQLite_Two_D;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.DanDin_SOSO;
import com.femto.shipper.bean.YunDanBean_SOSO;
import com.femto.shipper.bean.YundanPLBrean;
import com.femto.shipper.bean.YundanXQBean;
import com.femto.shipper.bean.DanDin_SOSO.OrderListB;
import com.femto.shipper.fragment.Dindan_Fragment_two;
import com.femto.shipper.fragment.YunDan_fragment_two;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.utils.Utils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class Order_twoActivity extends BaseActivity implements OnClickListener
{
	private Button bt_clear;// /清空
	private EditText txt_shuru;// 输入框
	private TextView F_dindan, F_yundan;// 历史搜索订单按钮，
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private Bundle bundle;
	private FrameLayout fm_two;
	private String dy;
	private String order_no, addr_start, addr_end, payment_time;
	private OrderListB orderListB;
	private DBSqlite_Y dbY;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private YundanPLBrean yundanPLBrean;
	private Order_SQLite_Two_D db;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Order_twoActivity.this.setTheme(R.style.All);
		setContentView(R.layout.activity_order_two);
		sharedPreferences = getSharedPreferences(getResources().getString(R.string.sqlname), Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(getResources().getString(R.string.pwd), "");
		db = new Order_SQLite_Two_D(this);
		dbY = new DBSqlite_Y(this.mContext);
		info();
		Intent it = getIntent();
		txt_shuru.setText(it.getStringExtra("shuru"));
		dy = "D";
		F_yundan.setBackgroundResource(R.drawable.kuang3);
		F_dindan.setBackgroundResource(R.drawable.kuang2);
		F_yundan.setTextColor(R.color.huise);
		F_dindan.setTextColor(R.color.heise);
		// 默认显示订单
		manager = this.getSupportFragmentManager();
		transaction = manager.beginTransaction();
		Dindan_Fragment_two dindan = new Dindan_Fragment_two();
		transaction.replace(R.id.fragment_two, dindan);
		transaction.commit();

	}

	private void info()
	{
		findViewById(R.id.left).setOnClickListener(this);// 返回
		findViewById(R.id.image_queren).setOnClickListener(this);
		findViewById(R.id.bt_clear).setOnClickListener(this);
		F_dindan = (TextView) findViewById(R.id.F_dindan);
		F_dindan.setOnClickListener(this);
		F_yundan = (TextView) findViewById(R.id.F_yundan);
		F_yundan.setOnClickListener(this);
		txt_shuru = (EditText) findViewById(R.id.txt_shuru);
		fm_two = (FrameLayout) findViewById(R.id.fragment_two);
	}

	@Override
	public void onClick(View arg0)
	{
		if (isFastDoubleClick())
		{
			return;
		}
		// TODO Auto-generated method stub
		switch (arg0.getId())
		{
		case R.id.left:
			finish();
			break;
		case R.id.image_queren:// /输入点击确定的时候显示的
			String str = txt_shuru.getText().toString();
			if (str.contains("B"))
			{
				getdindanliebiao(str);
				txt_shuru.setText("");
			} else if (str.contains("b"))
			{
				String dindanhao = "B" + str.substring(1);// /小写字母，改成大写字母
				getdindanliebiao(dindanhao);
				txt_shuru.setText("");
			} else if (str.contains("Y"))
			{
				String dindanhao = str;
				getyundan(dindanhao);
				getintentYD(dindanhao);
				txt_shuru.setText("");
			} else if (str.contains("y"))
			{
				String dindanhao = "Y" + str.substring(1);// /小写字母，改成大写字母
				getyundan(dindanhao);
				getintentYD(dindanhao);
				txt_shuru.setText("");
			} else if (str.equals(""))
			{
				showToast("输入首字母不正确！！");
			} else
			{
				showToast("输入的不对");
			}

			break;
		case R.id.bt_clear:// /清空
			if (dy.equals("D"))
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				builder.setMessage("是否清空订单信息？");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						db.delectAll("order");
						manager = getSupportFragmentManager();
						transaction = manager.beginTransaction();
						Dindan_Fragment_two dindan = new Dindan_Fragment_two();
						transaction.replace(R.id.fragment_two, dindan);
						transaction.commit();
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{

					}
				});
				builder.show();
			} else if (dy.equals("Y"))
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("是否清空运单信息？");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						dbY.delect();
						manager = getSupportFragmentManager();
						transaction = manager.beginTransaction();
						YunDan_fragment_two yundan = new YunDan_fragment_two();
						transaction.replace(R.id.fragment_two, yundan);
						transaction.commit();
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{

					}
				});
				builder.show();
			}

			break;
		case R.id.F_dindan:// 订单
			F_yundan.setTextColor(R.color.huise);
			F_dindan.setTextColor(R.color.heise);
			// 默认显示订单
			dy = "D";
			F_yundan.setBackgroundResource(R.drawable.kuang3);
			F_dindan.setBackgroundResource(R.drawable.kuang2);
			manager = this.getSupportFragmentManager();
			transaction = manager.beginTransaction();
			Dindan_Fragment_two dindan = new Dindan_Fragment_two();
			transaction.replace(R.id.fragment_two, dindan);
			transaction.commit();
			break;
		case R.id.F_yundan:// /运单
			F_yundan.setTextColor(R.color.heise);
			F_dindan.setTextColor(R.color.huise);
			dy = "Y";
			F_yundan.setBackgroundResource(R.drawable.kuang2);
			F_dindan.setBackgroundResource(R.drawable.kuang3);
			manager = this.getSupportFragmentManager();
			transaction = manager.beginTransaction();
			YunDan_fragment_two yundan = new YunDan_fragment_two();
			transaction.replace(R.id.fragment_two, yundan);
			transaction.commit();
			break;

		default:
			break;
		}
	}

	private void getdindanliebiao(String status)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "search_order");
		map.put("username", phonea);
		map.put("order_no", status);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ORDERLIST + jiaMi; // 本工程的URL加密, 返回(string)encryptDES
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet(url, new RequestCallBack<String>()
		{
			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
				// TODO Auto-generated method stub
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				// TODO Auto-generated method stub
				LogUtils.i("arg0.result:" + arg0.result);
				dismissProgressDialog();// /取消加载框
				DanDin_SOSO danDin_SOSO = GsonUtils.json2Bean(arg0.result, DanDin_SOSO.class);// //用Gson转成json对象
				if (danDin_SOSO.status.equals("0") && danDin_SOSO.count.equals("1"))
				{
					orderListB = danDin_SOSO.orderinfo;
					order_no = orderListB.order_no;
					addr_start = orderListB.addr_start.split("\\|")[0];
					addr_end = orderListB.addr_end.split("\\|")[0];
					payment_time = orderListB.payment_time.split("\\ ")[0].split("\\-")[1] + "-"
							+ orderListB.payment_time.split("\\ ")[0].split("\\-")[2] + " " + orderListB.payment_time.split("\\ ")[1].split("\\:")[0]
							+ ":" + orderListB.payment_time.split("\\ ")[1].split("\\:")[1];
					db.intser(arg0.result, order_no);
					Intent it = new Intent();
					it.putExtra("order_no", order_no);
					it.setClass(Order_twoActivity.this, OrderPropertyActivity.class);
					startActivity(it);

				} else
				{
					showToast("没有找到该订单号");
				}

			}

		});
	}

	private void getyundan(String yundan)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "search_waybill");
		map.put("yid", yundan);
		map.put("username", phonea);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ORDERLIST + jiaMi; // 本工程的URL加密, 返回(string)encryptDES
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet(url, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				// TODO Auto-generated method stub
				LogUtils.i("arg0.result:" + arg0.result);
				dismissProgressDialog();// /取消加载框
				YunDanBean_SOSO yundan = GsonUtils.json2Bean(arg0.result, YunDanBean_SOSO.class);// //用Gson转成json对象
				if (yundan.status.equals("0") && yundan.count.equals("1"))
				{
					String avatar = yundan.yundaninfo.avatar;
					Utils.setimage(avatar);
					String credit = yundan.yundaninfo.credit;
					String yid = yundan.yundaninfo.yid;
					String nick_name = yundan.yundaninfo.nick_name;
					String plate_number = yundan.yundaninfo.plate_number;
					String mobile = yundan.yundaninfo.mobile;
					String ismycar = yundan.yundaninfo.ismycar;
					String car_status = yundan.yundaninfo.car_status;
					String driver_id = yundan.yundaninfo.driver_id;
					dbY.insert(avatar, credit, yid, nick_name, plate_number, mobile, ismycar, car_status, driver_id);

				} else
					showToast("没有找到该运单");
			}

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
				LogUtils.i("" + arg1);
				dismissProgressDialog();

			}

		});

	}

	public void getintentYD(String yid)
	{

		Map<String, String> m = new HashMap<String, String>();
		m.put("key", "waybill_info");
		m.put("username", phonea);// phonea
		m.put("yid", yid);
		String jiaMi = ToolUtils.JiaMi(m);
		String url = Net.ORDERLIST + jiaMi; // 本工程的URL加密, 返回(string)encryptDES
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet(url, new RequestCallBack<String>()
		{

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
				// TODO Auto-generated method stub
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				// TODO Auto-generated method stub
				LogUtils.i("arg0.result:" + arg0.result);
				dismissProgressDialog();// /取消加载框
				YundanXQBean yundanxqBean = GsonUtils.json2Bean(arg0.result, YundanXQBean.class);// //用Gson转成json对象
				if (yundanxqBean.status.equals("0"))
				{
					Intent it = new Intent();
					it.putExtra("driver_avatar", yundanxqBean.waybill_info.avatar);
					it.putExtra("driver_name", yundanxqBean.waybill_info.nick_name);
					it.putExtra("star", yundanxqBean.waybill_info.credit);
					it.putExtra("plate_number", yundanxqBean.waybill_info.plate_number);
					it.putExtra("state", yundanxqBean.waybill_info.car_status);
					it.putExtra("ismycar", yundanxqBean.waybill_info.ismycar);
					it.putExtra("yid", yundanxqBean.waybill_info.yid);
					it.putExtra("cid", yundanxqBean.waybill_info.cid);
					it.putExtra("start_time", yundanxqBean.waybill_info.arrive_time);
					it.putExtra("sign_time", yundanxqBean.waybill_info.sign_time);
					it.putExtra("mobile", yundanxqBean.waybill_info.mobile);
					it.putExtra("order_state", yundanxqBean.waybill_info.waybill_state);
					it.putExtra("driver_id", yundanxqBean.waybill_info.driver_id);
					it.setClass(Order_twoActivity.this, Order_op_ydxq.class);
					startActivity(it);

				} else
				{
					showToast("没有运单信息");
				}
			}
		});

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