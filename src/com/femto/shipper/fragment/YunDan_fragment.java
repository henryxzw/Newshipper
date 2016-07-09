package com.femto.shipper.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.femto.shipper.R;
import com.femto.shipper.activity.Order_op_ydxq;
import com.femto.shipper.activity.YunDansqlhelper;
import com.femto.shipper.activitya.Bimp;
import com.femto.shipper.activitya.Ycactivitya;
import com.femto.shipper.base.BaseFragment;
import com.femto.shipper.bean.YundanBean;
import com.femto.shipper.fragment.XListView.IXListViewListener;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

@SuppressLint("SimpleDateFormat")
public class YunDan_fragment extends BaseFragment implements
		IXListViewListener, OnClickListener {
	private String phonea, passworda, strtimea, strtimeb, strnumber, type = "",
			id = "", newtimestamp, strnumbera, oldtimestamp, yid;
	private SharedPreferences sharedPreferences, sharedPreferencesa,
			sharedpreferencesb;
	private XListView Order_listView;
	// private Handler mHandler;
	private long timea, timeb;
	private SimpleDateFormat formatb, formata;
	private Date datea, dateb;
	private YunDansqlhelper sh = null;
	private Mysimplecursoradapter mysimplecursoradapter;
	private Cursor cursor = null;
	private int selectnumber = 10, selectnumbera;
	private Bundle bundle;
	private Editor editor, editora;
	private LinearLayout myorderll;
	private Button myordermsycbtn;
	private TextView myordertv;
	private Intent it;
	private Myservice myservice;
	private IntentFilter filter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View inflate = View.inflate(mContext, R.layout.order_dhcx, null);
		Order_listView = (XListView) inflate.findViewById(R.id.Order_listView);
		myorderll = (LinearLayout) inflate.findViewById(R.id.myorderll);
		myordermsycbtn = (Button) inflate.findViewById(R.id.myordermsycbtn);
		myordertv = (TextView) inflate.findViewById(R.id.myordertv);
		sharedPreferences = getActivity().getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		myordertv.setText("您已无更多运单");
		myorderll.setVisibility(View.GONE);
		strnumber = String.valueOf(selectnumber);
		sh = new YunDansqlhelper(getActivity());
		cursor = sh.selectnumber(strnumber);
		// mHandler = new Handler();
		mysimplecursoradapter = new Mysimplecursoradapter(getActivity(),
				R.layout.myyundan, cursor, new String[] { "yid", "sjname",
						"carnumber", "carstatus" }, new int[] {
						R.id.myyundanhao, R.id.myyundandi, R.id.myyundancar,
						R.id.myyundancarstatus });
		Order_listView.setAdapter(mysimplecursoradapter);
		bundle = this.getArguments();
		type = bundle.getString("type");
		id = bundle.getString("id");
		Order_listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				yid = cursor.getString(3);
				it = new Intent();
				it.putExtra("yid", yid);
				it.setClass(getActivity(), Order_op_ydxq.class);
				startActivity(it);
			}
		});
		Order_listView.setXListViewListener(this);
		Order_listView.setPullLoadEnable(true);
		timea = System.currentTimeMillis();
		formata = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		datea = new Date(timea);
		strtimea = formata.format(datea);
		Order_listView.setRefreshTime(strtimea);
		Order_listView.requestLayout();
		myservice = new Myservice();
		filter = new IntentFilter();
		filter.addAction("com.femto.shipper.activitya.Aapdbroad");
		getActivity().registerReceiver(myservice, filter);
		myordermsycbtn.setOnClickListener(this);
		return inflate;
	}

	private class Mysimplecursoradapter extends SimpleCursorAdapter {
		private LayoutInflater layoutinflater;

		@SuppressWarnings("deprecation")
		public Mysimplecursoradapter(Context context, int layout, Cursor c,
				String[] from, int[] to) {
			super(context, layout, c, from, to);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			super.bindView(view, context, cursor);
			View convertview = null;
			if (view == null) {
				convertview = layoutinflater.inflate(R.layout.myyundan, null);
			} else {
				convertview = view;
			}
			ImageView pingxing = (ImageView) convertview
					.findViewById(R.id.pingxing);
			if (cursor.getString(cursor.getColumnIndex("credit")).equals("3")) {
				pingxing.setBackgroundResource(R.drawable.x3);
			} else if (cursor.getString(cursor.getColumnIndex("credit"))
					.equals("3.5")) {
				pingxing.setBackgroundResource(R.drawable.x3b);
			} else if (cursor.getString(cursor.getColumnIndex("credit"))
					.equals("4")) {
				pingxing.setBackgroundResource(R.drawable.x4);
			} else if (cursor.getString(cursor.getColumnIndex("credit"))
					.equals("4.5")) {
				pingxing.setBackgroundResource(R.drawable.x4b);
			} else if (cursor.getString(cursor.getColumnIndex("credit"))
					.equals("5")) {
				pingxing.setBackgroundResource(R.drawable.x5);
			}
		}
	}

	private void getyundan(final int yd) {
		sharedPreferencesa = getActivity().getSharedPreferences(
				"spinnerayundantimestamp", Activity.MODE_PRIVATE);
		if (yd == 0) {
			oldtimestamp = sharedPreferencesa.getString("timestamp", "").trim();
			if (oldtimestamp == null || oldtimestamp.equals("")) {
				oldtimestamp = "0";
			}
		} else {
			oldtimestamp = "0";
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "waybill_list");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("timestamp", oldtimestamp);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ORDERLIST + jiaMi;
		if (yd == 0) {
			showProgressDialog(getResources().getString(R.string.jzz));
		}
		application.doget(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				if (yd == 0) {
					dismissProgressDialog();
				} else if (yd == 1) {
					onLoad();
				}
				if (cursor.moveToFirst()) {
					myorderll.setVisibility(View.GONE);
					Order_listView.setVisibility(View.VISIBLE);
				} else {
					myorderll.setVisibility(View.VISIBLE);
					Order_listView.setVisibility(View.GONE);
				}
				showToast(getResources().getString(R.string.wlyc));
			}

			@SuppressWarnings("deprecation")
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				YundanBean yundanbean = GsonUtils.json2Bean(arg0.result,
						YundanBean.class);
				if (yundanbean.status.equals("0")) {
					sh.deletehi();
					for (int i = 0; i < yundanbean.list.size(); i++) {
						sh.insert(yundanbean.list.get(i).avatar,
								yundanbean.list.get(i).credit,
								yundanbean.list.get(i).yid,
								yundanbean.list.get(i).nick_name,
								yundanbean.list.get(i).plate_number,
								yundanbean.list.get(i).mobile,
								yundanbean.list.get(i).ismycar,
								yundanbean.list.get(i).car_status,
								yundanbean.list.get(i).driver_id);
					}
					newtimestamp = yundanbean.timestamp;
					editor = sharedPreferencesa.edit();
					editor.putString("timestamp", newtimestamp);
					editor.commit();
					cursor.requery();
					if (cursor.moveToFirst()) {
						myorderll.setVisibility(View.GONE);
						Order_listView.setVisibility(View.VISIBLE);
					} else {
						myorderll.setVisibility(View.VISIBLE);
						Order_listView.setVisibility(View.GONE);
					}
				} else if (yundanbean.status.equals("200")) {
					cursor.requery();
					if (cursor.moveToFirst()) {
						myorderll.setVisibility(View.GONE);
						Order_listView.setVisibility(View.VISIBLE);
					} else {
						myorderll.setVisibility(View.VISIBLE);
						Order_listView.setVisibility(View.GONE);
					}
				} else {
					showToast(yundanbean.status);
				}
				if (yd == 0) {
					dismissProgressDialog();
				} else if (yd == 1) {
					onLoad();
				}
			}
		});
	}

	private void onLoad() {
		timeb = System.currentTimeMillis();
		formatb = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateb = new Date(timeb);
		strtimeb = formatb.format(dateb);
		Order_listView.stopRefresh();
		Order_listView.stopLoadMore();
		Order_listView.setRefreshTime(strtimeb);
	}

	private void select(String type, String id) {
		selectnumbera = 10;
		strnumbera = String.valueOf(selectnumbera);
		cursor = sh.selectwhereand(id, type, strnumbera);
		mysimplecursoradapter = new Mysimplecursoradapter(getActivity(),
				R.layout.myyundan, cursor, new String[] { "yid", "sjname",
						"carnumber", "carstatus" }, new int[] {
						R.id.myyundanhao, R.id.myyundandi, R.id.myyundancar,
						R.id.myyundancarstatus });
		Order_listView.setAdapter(mysimplecursoradapter);
		if (cursor.moveToFirst()) {
			myorderll.setVisibility(View.GONE);
			Order_listView.setVisibility(View.VISIBLE);
		} else {
			myorderll.setVisibility(View.VISIBLE);
			Order_listView.setVisibility(View.GONE);
		}
	}

	private void selectmore(String type, String id) {
		selectnumbera = selectnumbera + 10;
		strnumbera = String.valueOf(selectnumbera);
		cursor = sh.selectwhereand(id, type, strnumbera);
		mysimplecursoradapter = new Mysimplecursoradapter(getActivity(),
				R.layout.myyundan, cursor, new String[] { "yid", "sjname",
						"carnumber", "carstatus" }, new int[] {
						R.id.myyundanhao, R.id.myyundandi, R.id.myyundancar,
						R.id.myyundancarstatus });
		Order_listView.setAdapter(mysimplecursoradapter);
		if (cursor.moveToFirst()) {
			myorderll.setVisibility(View.GONE);
			Order_listView.setVisibility(View.VISIBLE);
		} else {
			myorderll.setVisibility(View.VISIBLE);
			Order_listView.setVisibility(View.GONE);
		}
		Order_listView.stopRefresh();
		Order_listView.stopLoadMore();
	}

	private void selectmorea() {
		selectnumber = selectnumber + 10;
		strnumber = String.valueOf(selectnumber);
		cursor = sh.selectnumber(strnumber);
		mysimplecursoradapter = new Mysimplecursoradapter(getActivity(),
				R.layout.myyundan, cursor, new String[] { "yid", "sjname",
						"carnumber", "carstatus" }, new int[] {
						R.id.myyundanhao, R.id.myyundandi, R.id.myyundancar,
						R.id.myyundancarstatus });
		Order_listView.setAdapter(mysimplecursoradapter);
		if (cursor.moveToFirst()) {
			myorderll.setVisibility(View.GONE);
			Order_listView.setVisibility(View.VISIBLE);
		} else {
			myorderll.setVisibility(View.VISIBLE);
			Order_listView.setVisibility(View.GONE);
		}
		Order_listView.stopRefresh();
		Order_listView.stopLoadMore();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		getActivity().unregisterReceiver(myservice);
		Order_listView.stopRefresh();
		Order_listView.stopLoadMore();
		if (bundle != null) {
			bundle.clear();
		}
		if (editor != null) {
			editor.clear();
		}
		if (editora != null) {
			editora.clear();
		}
		if (cursor != null) {
			cursor.close();
		}
		if (sh != null) {
			sh.close();
		}
	}

	public void onResume() {
		super.onResume();
		if ((type.equals("") || type == null) && (id.equals("") || id == null)) {
			getyundan(0);
		} else {
			select(type, id);
		}
		MobclickAgent.onPageStart("YunDan_fragment");
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("YunDan_fragment");
	}

	@Override
	public void onRefresh() {
		// mHandler.postDelayed(new Runnable() {
		// @Override
		// public void run() {
		// onLoad();
		// }
		// }, 2000);
		// getyundan(1);
		onLoad();
	}

	class Myservice extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Bundle bundle = arg1.getExtras();
			String send = bundle.getString("send");
			if (send.equals("sx")) {
				getyundan(2);
			}
		}
	}

	@Override
	public void onLoadMore() {
		// mHandler.postDelayed(new Runnable() {
		// @Override
		// public void run() {
		// Order_listView.stopRefresh();
		// Order_listView.stopLoadMore();
		if ((type.equals("") || type == null) && (id.equals("") || id == null)) {
			selectmorea();
		} else {
			selectmore(type, id);
		}
		// }
		// }, 2000);
	}

	private void msyc() {
		qcsj();
		Intent intenta = new Intent(mContext, Ycactivitya.class);
		Ycactivitya.msychsyuyc = 0;
		startActivity(intenta);
	}

	private void qcsj() {
		sharedpreferencesb = getActivity().getSharedPreferences("yball",
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
		switch (arg0.getId()) {
		case R.id.myordermsycbtn:
			msyc();
			break;
		}
	}
}