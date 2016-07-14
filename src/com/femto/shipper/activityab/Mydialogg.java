package com.femto.shipper.activityab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.activitya.Dialogall;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Mydialogg extends Activity implements OnClickListener {
	private WheelView mypaintagclsla, mypaintb;
	private Context context;
	private Dialog dialog;
	private TextView mdlgxshctv, mdlgtvzdzzl, mdlgtvzdzhl, mdlgtvqbjg,
			mdlgtvccjg, tvqcsl;
	private ImageView mdlgimva;
	private LinearLayout mdlglla, mdlgllb;
	private Button mdlgbtn, mdlgbtnqx;
	private ArrayList<String> ggccxzlist, qclist;
	private int[] imgid = new int[] { R.drawable.jzx };
	@SuppressWarnings("unused")
	private LinearLayout mdldlla, mdldllb;
	@SuppressWarnings("unused")
	private String dwzw = "null", gg = "null", qbj = "null", ccj = "null",
			xhsl = "null", jqdwa, jqdwb, jqdwc, qcmz = "", d, l, zhlat, zhlon,
			c, allescc, es = "20", escc = "	5.890*2.342*2.388M", jgcdm = "no",
			xhlat, xhlon, phonea, passworda, zhl, eyyq, mytime, use_time, zhdd,
			xhdd;
	private int djcsa = 0, clsl, carid = 31, maxsize = 24, minsize = 14,
			msychsyuyc;
	@SuppressWarnings("unused")
	private String[] jqdw;
	Dialogcallbackg dialogcallbackg;
	private StatusBean statusbean;
	private HttpUtils http;
	private Mypaintadwyccxzadapter mypaintadwyccxzadapter;
	private Mypaintaclsladapter mypaintaclsladapter;
	private Dialogall dialogall;
	@SuppressWarnings("rawtypes")
	private ArrayList aya;

	@SuppressWarnings("rawtypes")
	public Mydialogg(Context con, String zhlat, String zhlon, String xhlat,
			String xhlon, String phonea, String passworda, String d, String l,
			String qcmz, String c, int msychsyuyc, String eyyq, String mytime,
			String zhdd, String xhdd, ArrayList aya) {
		this.aya = aya;
		this.context = con;
		this.zhlat = zhlat;
		this.zhlon = zhlon;
		this.xhlat = xhlat;
		this.xhlon = xhlon;
		this.phonea = phonea;
		this.passworda = passworda;
		this.msychsyuyc = msychsyuyc;
		this.eyyq = eyyq;
		this.mytime = mytime;
		this.zhdd = zhdd;
		this.xhdd = xhdd;
		this.c = c;
		this.qcmz = qcmz;
		this.d = d;
		this.l = l;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogg);
		dialog.setCanceledOnTouchOutside(false);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.BOTTOM);
		lp.y = 30;
		dialogWindow.setAttributes(lp);
		allescc = es + c + escc;
		cshid();
		tjcshsj();
		mypaintagclsla();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.mdlgbtn:
			if (djcsa == 0) {
				clsl();
			} else {
				qbj = mdlgtvqbjg.getText().toString();
				ccj = mdlgtvccjg.getText().toString();
				zhl = mdlgtvzdzhl.getText().toString();
				dialogcallbackg.dialogdog(qcmz, gg, xhsl, qbj, ccj, carid, zhl);
				dismiss();
			}
			break;
		case R.id.mdlgbtnqx:
			dismiss();
			break;
		}
	}

	private class Mypaintadwyccxzadapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected Mypaintadwyccxzadapter(Context context,
				ArrayList<String> list, int currentItem, int maxsize,
				int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem,
					maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	private class Mypaintaclsladapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected Mypaintaclsladapter(Context context, ArrayList<String> list,
				int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem,
					maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	public int getclslitem(String date) {
		int size = qclist.size();
		int dateindex = 0;
		for (int i = 0; i < size; i++) {
			if (date.equals(qclist.get(i))) {
				return dateindex;
			} else {
				dateindex++;
			}
		}
		return dateindex;
	}

	public void setTextviewSizeb(String curriteItemText,
			Mypaintaclsladapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(24);
			} else {
				textvew.setTextSize(14);
			}
		}
	}

	private void addcls() {
		qclist.clear();
		if (clsl == 0) {
			xhsl = 0 + l;
			qclist.add(0 + l);
			mypaintaclsladapter = new Mypaintaclsladapter(context, qclist,
					getclslitem(0 + l), maxsize, minsize);
			mypaintb.setVisibleItems(5);
			mypaintb.setViewAdapter(mypaintaclsladapter);
			mypaintb.setCurrentItem(getclslitem(0 + l));
		} else {
			if (clsl > 5) {
				for (int i = 1; i < 6; i++) {
					qclist.add(i + l);
				}
			} else {
				for (int i = 1; i < clsl + 1; i++) {
					qclist.add(i + l);
				}
			}
			mypaintaclsladapter = new Mypaintaclsladapter(context, qclist,
					getclslitem(1 + l), maxsize, minsize);
			mypaintb.setVisibleItems(5);
			mypaintb.setViewAdapter(mypaintaclsladapter);
			mypaintb.setCurrentItem(getclslitem(1 + l));
		}
		mypaintb.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String text = (String) mypaintaclsladapter.getItemText(wheel
						.getCurrentItem());
				xhsl = text;
				setTextviewSizeb(text, mypaintaclsladapter);
			}
		});
		mypaintb.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) mypaintaclsladapter
						.getItemText(wheel.getCurrentItem());
				setTextviewSizeb(currentText, mypaintaclsladapter);
			}
		});
	}

	private void changea() {
		djcsa = 1;
		mdlglla.setVisibility(View.GONE);
		mdlgllb.setVisibility(View.VISIBLE);
		if (gg == "null") {
			gg = ggccxzlist.get(0);
		}
		mdlgxshctv.setTextColor(Color.RED);
		mdlgxshctv.setText(qcmz + gg);
	}

	private void clsl() {
		if (mytime.equals("00000000000000")) {
			use_time = "";
		} else {
			use_time = mytime.substring(2);
		}
		dialogall = new Dialogall(context);
		dialogall.show();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", "get_car_count");
		map.put("pwd", passworda);
		map.put("username", phonea);
		map.put("start_location", zhdd + "|" + zhlon + "|" + zhlat);
		map.put("end_location", xhdd + "|" + xhlon + "|" + xhlat);
		map.put("cartype", carid);
		map.put("use_time", use_time);
		map.put("extra_request", eyyq);
		map.put("order_type", msychsyuyc);
		String jiami = ToolUtils.JiaMi(map);
		String url = Net.Car + jiami;
		http = new HttpUtils(5000);
		http.configTimeout(5000);
		http.configSoTimeout(5000);
		http.configCurrentHttpCacheExpiry(1);
		http.send(HttpMethod.GET, url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dialogall.dismiss();
				statusbean = GsonUtils.json2Bean(arg0.result, StatusBean.class);
				if (statusbean.status.equals("0")) {
					clsl = Integer.valueOf(statusbean.count);
					tvqcsl.setText(clsl + "");
					// jgcdm = "yes";
					changea();
					addcls();
				} else {
					clsl = 0;
					tvqcsl.setText(clsl + "");
					mdlgxshctv.setText(statusbean.msg);
					changea();
					addcls();
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				clsl = 0;
				tvqcsl.setText(clsl + "");
				dialogall.dismiss();
				changea();
				addcls();
			}
		});
	}

	public interface Dialogcallbackg {
		public void dialogdog(String a, String b, String c, String d, String e,
				int f, String h);
	}

	public void setDialogCallbackg(Dialogcallbackg dialogcallbackg) {
		this.dialogcallbackg = dialogcallbackg;

	}

	public void show() {
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

	public int getdwyccitem(String date) {
		int size = ggccxzlist.size();
		int dateindex = 0;
		for (int i = 0; i < size; i++) {
			if (date.equals(ggccxzlist.get(i))) {
				return dateindex;
			} else {
				dateindex++;
			}
		}
		return dateindex;
	}

	public void setTextviewSize(String curriteItemText,
			Mypaintadwyccxzadapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(24);
			} else {
				textvew.setTextSize(14);
			}
		}
	}

	private void mypaintagclsla() {
		for (int h = 0; h < aya.size(); h++) {
			if (aya.get(h).toString().trim().split(",")[0].equals(qcmz)) {
				ggccxzlist.add(aya.get(h).toString().trim().split(",")[1]
						.trim()
						+ "	"
						+ aya.get(h).toString().trim().split(",")[3].trim());
			}
		}
		for (int h = 0; h < aya.size(); h++) {
			if (aya.get(h).toString().trim().split(",")[0].equals(qcmz)
					&& ggccxzlist
							.get(0)
							.equals(aya.get(h).toString().trim().split(",")[1]
									.trim()
									+ "	"
									+ aya.get(h).toString().trim().split(",")[3]
											.trim())) {
				mdlgtvzdzzl.setText(aya.get(h).toString().trim().split(",")[7]
						+ d);
				mdlgtvzdzhl.setText(aya.get(h).toString().trim().split(",")[6]
						+ "方");
				mdlgtvqbjg.setText(aya.get(h).toString().trim().split(",")[8]
						+ "元/10公里");
				mdlgtvccjg.setText(aya.get(h).toString().trim().split(",")[9]
						+ "元/公里");
				carid = Integer
						.valueOf(aya.get(h).toString().trim().split(",")[4]
								.trim());
			}
		}
		mypaintadwyccxzadapter = new Mypaintadwyccxzadapter(context,
				ggccxzlist, getdwyccitem(ggccxzlist.get(0)), maxsize, minsize);
		mypaintagclsla.setVisibleItems(5);
		mypaintagclsla.setViewAdapter(mypaintadwyccxzadapter);
		mypaintagclsla.setCurrentItem(getdwyccitem(ggccxzlist.get(0)));
		mypaintagclsla.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String text = (String) mypaintadwyccxzadapter.getItemText(wheel
						.getCurrentItem());
				for (int i = 0; i < aya.size(); i++) {
					if (aya.get(i).toString().trim().split(",")[0].equals(qcmz)
							&& text.equals(aya.get(i).toString().trim()
									.split(",")[1].trim()
									+ "	"
									+ aya.get(i).toString().trim().split(",")[3]
											.trim())) {
						mdlgtvzdzzl.setText(aya.get(i).toString().trim()
								.split(",")[7]
								+ d);
						mdlgtvzdzhl.setText(aya.get(i).toString().trim()
								.split(",")[6]
								+ "方");
						mdlgtvqbjg.setText(aya.get(i).toString().trim()
								.split(",")[8]
								+ "元/10公里");
						mdlgtvccjg.setText(aya.get(i).toString().trim()
								.split(",")[9]
								+ "元/公里");
						carid = Integer.valueOf(aya.get(i).toString().trim()
								.split(",")[4].trim());
					}
				}
				gg = text;
				setTextviewSize(text, mypaintadwyccxzadapter);
			}
		});
		mypaintagclsla.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) mypaintadwyccxzadapter
						.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mypaintadwyccxzadapter);
			}

			@Override
			public void onScrollingStarted(WheelView wheel) {
			}
		});
	}

	private void cshid() {
		mypaintagclsla = (WheelView) dialog.findViewById(R.id.mypaintagclsla);
		mypaintb = (WheelView) dialog.findViewById(R.id.mypaintb);
		mdlgxshctv = (TextView) dialog.findViewById(R.id.mdlgxshctv);
		mdlgtvzdzzl = (TextView) dialog.findViewById(R.id.mdlgtvzdzzl);
		mdlgtvzdzhl = (TextView) dialog.findViewById(R.id.mdlgtvzdzhl);
		mdlgtvqbjg = (TextView) dialog.findViewById(R.id.mdlgtvqbjg);
		mdlgtvccjg = (TextView) dialog.findViewById(R.id.mdlgtvccjg);
		tvqcsl = (TextView) dialog.findViewById(R.id.tvqcsl);
		mdlgimva = (ImageView) dialog.findViewById(R.id.mdlgimva);
		mdlgbtnqx = (Button) dialog.findViewById(R.id.mdlgbtnqx);
		mdlgbtn = (Button) dialog.findViewById(R.id.mdlgbtn);
		mdlglla = (LinearLayout) dialog.findViewById(R.id.mdlglla);
		mdlgllb = (LinearLayout) dialog.findViewById(R.id.mdlgllb);
		mdlgllb.setVisibility(View.GONE);
		mdlgbtn.setOnClickListener(this);
		mdlgbtnqx.setOnClickListener(this);
	}

	public void tjcshsj() {
		mdlgxshctv.setText(qcmz);
		mdlgimva.setBackgroundResource(imgid[0]);
		ggccxzlist = new ArrayList<String>();
		qclist = new ArrayList<String>();
	}
}