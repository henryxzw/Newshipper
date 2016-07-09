package com.femto.shipper.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.femto.shipper.R;
import com.femto.shipper.activity.Dhcxhelper;
import com.femto.shipper.activity.OrderPropertyActivity;
import com.femto.shipper.activitya.Bimp;
import com.femto.shipper.activitya.Ycactivitya;
import com.femto.shipper.base.BaseFragment;
import com.femto.shipper.bean.OrderListBean;
import com.femto.shipper.fragment.XListView.IXListViewListener;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

public class Order_DHCX extends BaseFragment implements IXListViewListener,
		OnClickListener {
	private XListView Order_listView;
	@SuppressWarnings("unused")
	private String newtimestamp, oldtimestamp, strtimea, strtimeb, strnumber,
			check, phonea, passworda, carano, caratype, caraststus,
			carbno = "", carbtype = "", carbststus = "", carcno = "",
			carctype = "", carcststus = "", cardno = "", cardtype = "",
			cardststus = "", careno = "", caretype = "", careststus = "";
	private SharedPreferences sharedPreferences, sharedPreferencesa,
			sharedpreferencesb;
	private Dhcxhelper sh = null;
	private Mysimplecursoradapter mysimplecursoradapter;
	private Cursor cursor = null;
	// private int selectnumber = 10, selectnumbera;
	private long timea, timeb;
	private SimpleDateFormat formatb, formata;
	private Date datea, dateb;
	private Editor editor = null, editora = null;
	private Bundle bundle = null, bundlea = null;
	private LinearLayout myorderll;
	private Button myordermsycbtn;
	private TextView myordertv;
	private Myservice myservice;
	private IntentFilter filter;
	@SuppressWarnings("rawtypes")
	private ArrayList listData = null;
	@SuppressWarnings("rawtypes")
	private HashMap hashmap = null;
	private View inflate;
	@SuppressWarnings("rawtypes")
	private Map newmap = null, mapa = null;
	private Intent it, intenta;
	private HashMap<String, Object> hashmapa = null;

	@SuppressLint("SimpleDateFormat")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflate = View.inflate(mContext, R.layout.order_dhcx, null);
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
		myordertv.setText("您已无更多订单");
		myorderll.setVisibility(View.GONE);
		sh = new Dhcxhelper(getActivity());
		// strnumber = String.valueOf(selectnumber);
		bundle = this.getArguments();
		check = bundle.getString("check");
		getdindanliebiao(0);
		Order_listView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				newmap = (Map) listData.get(arg2 - 1);
				String order_no = String.valueOf(newmap.get("orderno"));
				it = new Intent();
				it.putExtra("order_no", order_no);
				it.setClass(getActivity(), OrderPropertyActivity.class);
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

	@Override
	public void onRefresh() {
		cxbd();
		onLoad();
	}

	class Myservice extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			bundlea = arg1.getExtras();
			String send = bundlea.getString("send");
			if (send.equals("sx")) {
				getdindanliebiao(2);
			}
		}
	}

	private void selectlike(String check) {
		// selectnumbera = 10;
		// strnumber = String.valueOf(selectnumbera);
		cursor = sh.selectlikea(check);
		getAllData();
		if (cursor.moveToFirst()) {
			myorderll.setVisibility(View.GONE);
			Order_listView.setVisibility(View.VISIBLE);
		} else {
			myorderll.setVisibility(View.VISIBLE);
			Order_listView.setVisibility(View.GONE);
		}
	}

	private void cxbd() {
		if (check.equals("") || check == null) {
			cursor = sh.select();
			getAllData();
			if (cursor.moveToFirst()) {
				myorderll.setVisibility(View.GONE);
				Order_listView.setVisibility(View.VISIBLE);
			} else {
				myorderll.setVisibility(View.VISIBLE);
				Order_listView.setVisibility(View.GONE);
			}
		} else {
			selectlike(check);
		}
	}

	public void onResume() {
		super.onResume();
		getdindanliebiao(0);
		MobclickAgent.onPageStart("Order_DHCX");
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("Order_DHCX");
	}

	@Override
	public void onLoadMore() {
		Order_listView.stopRefresh();
		Order_listView.stopLoadMore();
	}

	@SuppressLint("SimpleDateFormat")
	private void onLoad() {
		timeb = System.currentTimeMillis();
		formatb = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateb = new Date(timeb);
		strtimeb = formatb.format(dateb);
		Order_listView.stopRefresh();
		Order_listView.stopLoadMore();
		Order_listView.setRefreshTime(strtimeb);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getAllData() {
		if (listData != null) {
			listData.clear();
		}
		listData = new ArrayList<>();
		int columnsSize = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			hashmap = new HashMap();
			for (int i = 0; i < columnsSize; i++) {
				hashmap.put("orderno", cursor.getString(1));
				hashmap.put("paytime", cursor.getString(8));
				hashmap.put("startadd", cursor.getString(2));
				hashmap.put("endadd", cursor.getString(5));
				hashmap.put("carano", cursor.getString(9));
				hashmap.put("carbno", cursor.getString(12));
				hashmap.put("carcno", cursor.getString(15));
				hashmap.put("cardno", cursor.getString(18));
				hashmap.put("careno", cursor.getString(21));
				hashmap.put("caratype", cursor.getString(10));
				hashmap.put("carbtype", cursor.getString(13));
				hashmap.put("carctype", cursor.getString(16));
				hashmap.put("cardtype", cursor.getString(19));
				hashmap.put("caretype", cursor.getString(22));
				hashmap.put("caraststus", cursor.getString(11));
				hashmap.put("carbststus", cursor.getString(14));
				hashmap.put("carcststus", cursor.getString(17));
				hashmap.put("cardststus", cursor.getString(20));
				hashmap.put("careststus", cursor.getString(23));
			}
			listData.add(hashmap);
		}
		mysimplecursoradapter = new Mysimplecursoradapter(getActivity(),
				listData, R.layout.myorder, new String[] { "orderno",
						"paytime", "startadd", "endadd", "carano", "caratype",
						"caraststus", "carbno", "carbtype", "carbststus",
						"carcno", "carctype", "carcststus", "cardno",
						"cardtype", "cardststus", "careno", "caretype",
						"careststus" }, new int[] { R.id.myorderno,
						R.id.myordertime, R.id.myorderstart, R.id.myorderend,
						R.id.carnoa, R.id.carnamea, R.id.carstatusa,
						R.id.carnob, R.id.carnameb, R.id.carstatusb,
						R.id.carnoc, R.id.carnamec, R.id.carstatusc,
						R.id.carnod, R.id.carnamed, R.id.carstatusd,
						R.id.carnoe, R.id.carnamee, R.id.carstatuse });
		Order_listView.setAdapter(mysimplecursoradapter);
	}

	private class Mysimplecursoradapter extends SimpleAdapter {
		private LayoutInflater layoutinflater;
		private View convertview = null;
		private RelativeLayout myorderrla, myorderrlb, myorderrlc, myorderrld,
				myorderrle;
		private TextView myorderno, myordertime, myorderstart, myorderend,
				carnoa, carnob, carnoc, carnod, carnoe, carnamea, carnameb,
				carnamec, carnamed, carnamee, carstatusa, carstatusb,
				carstatusc, carstatusd, carstatuse;

		public Mysimplecursoradapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public View getView(int position, View view, ViewGroup parent) {
			layoutinflater = getActivity().getLayoutInflater();
			if (view == null) {
				convertview = layoutinflater.inflate(R.layout.myorder, parent,
						false);
			} else {
				convertview = view;
			}
			myorderrla = (RelativeLayout) convertview
					.findViewById(R.id.myorderrla);
			myorderrlb = (RelativeLayout) convertview
					.findViewById(R.id.myorderrlb);
			myorderrlc = (RelativeLayout) convertview
					.findViewById(R.id.myorderrlc);
			myorderrld = (RelativeLayout) convertview
					.findViewById(R.id.myorderrld);
			myorderrle = (RelativeLayout) convertview
					.findViewById(R.id.myorderrle);
			myorderno = (TextView) convertview.findViewById(R.id.myorderno);
			myordertime = (TextView) convertview.findViewById(R.id.myordertime);
			myorderstart = (TextView) convertview
					.findViewById(R.id.myorderstart);
			myorderend = (TextView) convertview.findViewById(R.id.myorderend);
			carnoa = (TextView) convertview.findViewById(R.id.carnoa);
			carnob = (TextView) convertview.findViewById(R.id.carnob);
			carnoc = (TextView) convertview.findViewById(R.id.carnoc);
			carnod = (TextView) convertview.findViewById(R.id.carnod);
			carnoe = (TextView) convertview.findViewById(R.id.carnoe);
			carnamea = (TextView) convertview.findViewById(R.id.carnamea);
			carnameb = (TextView) convertview.findViewById(R.id.carnameb);
			carnamec = (TextView) convertview.findViewById(R.id.carnamec);
			carnamed = (TextView) convertview.findViewById(R.id.carnamed);
			carnamee = (TextView) convertview.findViewById(R.id.carnamee);
			carstatusa = (TextView) convertview.findViewById(R.id.carstatusa);
			carstatusb = (TextView) convertview.findViewById(R.id.carstatusb);
			carstatusc = (TextView) convertview.findViewById(R.id.carstatusc);
			carstatusd = (TextView) convertview.findViewById(R.id.carstatusd);
			carstatuse = (TextView) convertview.findViewById(R.id.carstatuse);
			mapa = (Map) listData.get(position);
			myorderno.setText(String.valueOf(mapa.get("orderno")));
			myordertime.setText(String.valueOf(mapa.get("paytime")));
			myorderstart.setText(String.valueOf(mapa.get("startadd")));
			myorderend.setText(String.valueOf(mapa.get("endadd")));
			carnoa.setText(String.valueOf(mapa.get("carano")));
			carnob.setText(String.valueOf(mapa.get("carbno")));
			carnoc.setText(String.valueOf(mapa.get("carcno")));
			carnod.setText(String.valueOf(mapa.get("cardno")));
			carnoe.setText(String.valueOf(mapa.get("careno")));
			carnamea.setText(String.valueOf(mapa.get("caratype")));
			carnameb.setText(String.valueOf(mapa.get("carbtype")));
			carnamec.setText(String.valueOf(mapa.get("carctype")));
			carnamed.setText(String.valueOf(mapa.get("cardtype")));
			carnamee.setText(String.valueOf(mapa.get("caretype")));
			carstatusa.setText(String.valueOf(mapa.get("caraststus")));
			carstatusb.setText(String.valueOf(mapa.get("carbststus")));
			carstatusc.setText(String.valueOf(mapa.get("carcststus")));
			carstatusd.setText(String.valueOf(mapa.get("cardststus")));
			carstatuse.setText(String.valueOf(mapa.get("careststus")));
			if (carnoa.getText().toString().trim().equals("")) {
				myorderrla.setVisibility(View.GONE);
			} else {
				myorderrla.setVisibility(View.VISIBLE);
			}
			if (carnob.getText().toString().trim().equals("")) {
				myorderrlb.setVisibility(View.GONE);
			} else {
				myorderrlb.setVisibility(View.VISIBLE);
			}
			if (carnoc.getText().toString().trim().equals("")) {
				myorderrlc.setVisibility(View.GONE);
			} else {
				myorderrlc.setVisibility(View.VISIBLE);
			}
			if (carnod.getText().toString().trim().equals("")) {
				myorderrld.setVisibility(View.GONE);
			} else {
				myorderrld.setVisibility(View.VISIBLE);
			}
			if (carnoe.getText().toString().trim().equals("")) {
				myorderrle.setVisibility(View.GONE);
			} else {
				myorderrle.setVisibility(View.VISIBLE);
			}
			return convertview;
		}
	}

	@SuppressLint("SimpleDateFormat")
	private void getdindanliebiao(final int yn) {
		sharedPreferencesa = getActivity().getSharedPreferences(
				"spinneraordertimestamp", Activity.MODE_PRIVATE);
		if (yn == 0) {
			oldtimestamp = sharedPreferencesa.getString("timestamp", "").trim();
			if (oldtimestamp == null || oldtimestamp.equals("")) {
				oldtimestamp = "0";
			}
		} else {
			oldtimestamp = "0";
		}
		hashmapa = new HashMap<String, Object>();
		hashmapa.put("key", "order_list");
		hashmapa.put("username", phonea);
		hashmapa.put("pwd", passworda);
		hashmapa.put("timestamp", oldtimestamp);
		String jiaMi = ToolUtils.JiaMi(hashmapa);
		String url = Net.ORDERLIST + jiaMi;
		if (yn == 0) {
			showProgressDialog(getResources().getString(R.string.jzz));
		}
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				if (yn == 0) {
					dismissProgressDialog();
				} else if (yn == 1) {
					onLoad();
				}
				showToast(getResources().getString(R.string.wlyc));
				cxbd();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				OrderListBean orderListBean = GsonUtils.json2Bean(arg0.result,
						OrderListBean.class);
				if (orderListBean.status.equals("0")) {
					sh.deletehi();
					for (int i = 0; i < orderListBean.list.size(); i++) {
						String alls = orderListBean.list.get(i).addr_start;
						String allsf[] = alls.split("\\|");
						String alle = orderListBean.list.get(i).addr_end;
						String allef[] = alle.split("\\|");
						int carsize = orderListBean.list.get(i).car_list.size();
						if (carsize == 1) {
							carano = orderListBean.list.get(i).car_list.get(0).plate_number;

							caratype = orderListBean.list.get(i).car_list
									.get(0).car_type.split(",")[0];
							caraststus = orderListBean.list.get(i).car_list
									.get(0).car_status;
							carbno = "";
							carbtype = "";
							carbststus = "";
							carcno = "";
							carctype = "";
							carcststus = "";
							cardno = "";
							cardtype = "";
							cardststus = "";
							careno = "";
							caretype = "";
							careststus = "";
						} else if (carsize == 2) {
							carano = orderListBean.list.get(i).car_list.get(0).plate_number;
							caratype = orderListBean.list.get(i).car_list
									.get(0).car_type.split(",")[0];
							caraststus = orderListBean.list.get(i).car_list
									.get(0).car_status;
							carbno = orderListBean.list.get(i).car_list.get(1).plate_number;
							carbtype = orderListBean.list.get(i).car_list
									.get(1).car_type.split(",")[0];
							carbststus = orderListBean.list.get(i).car_list
									.get(1).car_status;
							carcno = "";
							carctype = "";
							carcststus = "";
							cardno = "";
							cardtype = "";
							cardststus = "";
							careno = "";
							caretype = "";
							careststus = "";
						} else if (carsize == 3) {
							carano = orderListBean.list.get(i).car_list.get(0).plate_number;
							caratype = orderListBean.list.get(i).car_list
									.get(0).car_type.split(",")[0];
							caraststus = orderListBean.list.get(i).car_list
									.get(0).car_status;
							carbno = orderListBean.list.get(i).car_list.get(1).plate_number;
							carbtype = orderListBean.list.get(i).car_list
									.get(1).car_type.split(",")[0];
							carbststus = orderListBean.list.get(i).car_list
									.get(1).car_status;
							carcno = orderListBean.list.get(i).car_list.get(2).plate_number;
							carctype = orderListBean.list.get(i).car_list
									.get(2).car_type.split(",")[0];
							carcststus = orderListBean.list.get(i).car_list
									.get(2).car_status;
							cardno = "";
							cardtype = "";
							cardststus = "";
							careno = "";
							caretype = "";
							careststus = "";
						} else if (carsize == 4) {
							carano = orderListBean.list.get(i).car_list.get(0).plate_number;
							caratype = orderListBean.list.get(i).car_list
									.get(0).car_type.split(",")[0];
							caraststus = orderListBean.list.get(i).car_list
									.get(0).car_status;
							carbno = orderListBean.list.get(i).car_list.get(1).plate_number;
							carbtype = orderListBean.list.get(i).car_list
									.get(1).car_type.split(",")[0];
							carbststus = orderListBean.list.get(i).car_list
									.get(1).car_status;
							carcno = orderListBean.list.get(i).car_list.get(2).plate_number;
							carctype = orderListBean.list.get(i).car_list
									.get(2).car_type.split(",")[0];
							carcststus = orderListBean.list.get(i).car_list
									.get(2).car_status;
							cardno = orderListBean.list.get(i).car_list.get(3).plate_number;
							cardtype = orderListBean.list.get(i).car_list
									.get(3).car_type.split(",")[0];
							cardststus = orderListBean.list.get(i).car_list
									.get(3).car_status;
							careno = "";
							caretype = "";
							careststus = "";
						} else if (carsize == 5) {
							carano = orderListBean.list.get(i).car_list.get(0).plate_number;
							caratype = orderListBean.list.get(i).car_list
									.get(0).car_type.split(",")[0];
							caraststus = orderListBean.list.get(i).car_list
									.get(0).car_status;
							carbno = orderListBean.list.get(i).car_list.get(1).plate_number;
							carbtype = orderListBean.list.get(i).car_list
									.get(1).car_type.split(",")[0];
							carbststus = orderListBean.list.get(i).car_list
									.get(1).car_status;
							carcno = orderListBean.list.get(i).car_list.get(2).plate_number;
							carctype = orderListBean.list.get(i).car_list
									.get(2).car_type.split(",")[0];
							carcststus = orderListBean.list.get(i).car_list
									.get(2).car_status;
							cardno = orderListBean.list.get(i).car_list.get(3).plate_number;
							cardtype = orderListBean.list.get(i).car_list
									.get(3).car_type.split(",")[0];
							cardststus = orderListBean.list.get(i).car_list
									.get(3).car_status;
							careno = orderListBean.list.get(i).car_list.get(4).plate_number;
							caretype = orderListBean.list.get(i).car_list
									.get(4).car_type.split(",")[0];
							careststus = orderListBean.list.get(i).car_list
									.get(4).car_status;
						}
						String time = orderListBean.list.get(i).payment_time;
						String timea = time.split("\\ ")[0].split("\\-")[1];
						String timeb = time.split("\\ ")[0].split("\\-")[2];
						String timec = time.split("\\ ")[1].split("\\:")[0];
						String timed = time.split("\\ ")[1].split("\\:")[1];
						String mytime = timea + "-" + timeb + "		" + timec
								+ ":" + timed;
						sh.insert(orderListBean.list.get(i).order_no, allsf[0],
								allsf[1], allsf[2], allef[0], allef[1],
								allef[1], mytime, carano, caratype, caraststus,
								carbno, carbtype, carbststus, carcno, carctype,
								carcststus, cardno, cardtype, cardststus,
								careno, caretype, careststus);
					}
					cxbd();
					newtimestamp = orderListBean.timestamp;
					editor = sharedPreferencesa.edit();
					editor.putString("timestamp", newtimestamp);
					editor.commit();
				} else if (orderListBean.status.equals("200")) {
					cxbd();
				} else {
					showToast(orderListBean.status + orderListBean.msg);
					cxbd();
				}
				if (yn == 0) {
					dismissProgressDialog();
				} else if (yn == 1) {
					onLoad();
				}
			}
		});
	}

	private void msyc() {
		qcsj();
		intenta = new Intent(mContext, Ycactivitya.class);
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

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		getActivity().unregisterReceiver(myservice);
		Order_listView.stopRefresh();
		Order_listView.stopLoadMore();
		if (hashmapa != null) {
			hashmapa.clear();
		}
		if (newmap != null) {
			newmap.clear();
		}
		if (mapa != null) {
			mapa.clear();
		}
		if (hashmap != null) {
			hashmap.clear();
		}
		if (listData != null) {
			listData.clear();
		}
		if (bundlea != null) {
			bundlea.clear();
		}
		if (bundle != null) {
			bundle.clear();
		}
		if (cursor != null) {
			cursor.close();
		}
		if (editor != null) {
			editor.clear();
		}
		if (editora != null) {
			editora.clear();
		}
		if (sh != null) {
			sh.close();
		}
	}
	// private void selectlikemore(String check) {
	// selectnumbera = selectnumbera + 10;
	// strnumber = String.valueOf(selectnumbera);
	// cursor = sh.selectlike(check, strnumber);
	// mysimplecursoradapter = new Mysimplecursoradapter(getActivity(),
	// R.layout.myorder, cursor, new String[] { "orderno", "paytime",
	// "startadd", "endadd", "carano", "caratype",
	// "caraststus", "carbno", "carbtype", "carbststus",
	// "carcno", "carctype", "carcststus", "cardno",
	// "cardtype", "cardststus", "careno", "caretype",
	// "careststus" }, new int[] { R.id.myorderno,
	// R.id.myordertime, R.id.myorderstart, R.id.myorderend,
	// R.id.carnoa, R.id.carnamea, R.id.carstatusa,
	// R.id.carnob, R.id.carnameb, R.id.carstatusb,
	// R.id.carnoc, R.id.carnamec, R.id.carstatusc,
	// R.id.carnod, R.id.carnamed, R.id.carstatusd,
	// R.id.carnoe, R.id.carnamee, R.id.carstatuse });
	// Order_listView.setAdapter(mysimplecursoradapter);
	// Order_listView.stopRefresh();
	// Order_listView.stopLoadMore();
	// }
	// private void zjcx() {
	// selectnumber = selectnumber + 10;
	// strnumber = String.valueOf(selectnumber);
	// cursor = sh.selectnumber(strnumber);
	// mysimplecursoradapter = new Mysimplecursoradapter(getActivity(),
	// R.layout.myorder, cursor, new String[] { "orderno", "paytime",
	// "startadd", "endadd", "carano", "caratype",
	// "caraststus", "carbno", "carbtype", "carbststus",
	// "carcno", "carctype", "carcststus", "cardno",
	// "cardtype", "cardststus", "careno", "caretype",
	// "careststus" }, new int[] { R.id.myorderno,
	// R.id.myordertime, R.id.myorderstart, R.id.myorderend,
	// R.id.carnoa, R.id.carnamea, R.id.carstatusa,
	// R.id.carnob, R.id.carnameb, R.id.carstatusb,
	// R.id.carnoc, R.id.carnamec, R.id.carstatusc,
	// R.id.carnod, R.id.carnamed, R.id.carstatusd,
	// R.id.carnoe, R.id.carnamee, R.id.carstatuse });
	// Order_listView.setAdapter(mysimplecursoradapter);
	// if (cursor.moveToFirst()) {
	// myorderll.setVisibility(View.GONE);
	// Order_listView.setVisibility(View.VISIBLE);
	// } else {
	// myorderll.setVisibility(View.VISIBLE);
	// Order_listView.setVisibility(View.GONE);
	// }
	// Order_listView.stopRefresh();
	// Order_listView.stopLoadMore();
	// }
}