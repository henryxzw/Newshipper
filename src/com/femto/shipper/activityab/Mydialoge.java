package com.femto.shipper.activityab;

import java.util.ArrayList;

import com.femto.shipper.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

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

public class Mydialoge extends Activity implements OnClickListener {
	private Context context;
	private Dialog dialog;
	private StatusBean statusbean;
	private Button mdldbtn, mdldbtnqx;
	private ImageView mdldimva;
	private TextView mdldtvzdzzl, mdldtvzdzhl, mdldtvqbjg, mdldtvccjg,
			mdldxshctv, tvclsl;
	private WheelView mypaintadwxz, mypaintaccxz, mypaintaclsla;
	private ArrayList<String> dwxzlist, ccxzlist, clslist;
	private int[] imgid = new int[] { R.drawable.xskdz, R.drawable.xskd3,
			R.drawable.xskd5, R.drawable.xskd8, R.drawable.xskd15 };
	private LinearLayout mdldlla, mdldllb;
	private String dwzw = "null", gg = "null", qbj = "null", ccj = "null",
			xhsl = "null", qcmz = "", jqdwa, jqdwb, jqdwc, zhlat, zhlon, xhlat,
			zhl, xhlon, phonea, passworda, d, l, eyyq, mytime, use_time, zhdd,
			xhdd;
	private int djcsa = 0, clsl, carid = 49, maxsize = 24, minsize = 14,
			msychsyuyc;
	private Dialogcallbacke dialogcallbacke;
	private String[] jqdw;
	private HttpUtils http;
	private Mypaintadwxzadapter mypaintadwxzadapter;
	private Mypaintaccxzadapter mypaintaccxzadapter;
	private Mypaintaclsladapter mypaintaclsladapter;
	private Dialogall dialogall;
	@SuppressWarnings("rawtypes")
	private ArrayList aya, ayb;

	@SuppressWarnings("rawtypes")
	public Mydialoge(Context con, String zhlat, String zhlon, String xhlat,
			String xhlon, String phonea, String passworda, String qcmz,
			String d, String l, int msychsyuyc, String eyyq, String mytime,
			String zhdd, String xhdd, ArrayList aya, ArrayList ayb) {
		this.aya = aya;
		this.ayb = ayb;
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
		this.qcmz = qcmz;
		this.d = d;
		this.l = l;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogd);
		dialog.setCanceledOnTouchOutside(false);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.BOTTOM);
		lp.y = 30;
		dialogWindow.setAttributes(lp);
		cshid();
		tjcshsj();
		mypaintadwxz();
		mypaintaccxz(0);
	}

	public interface Dialogcallbacke {
		public void dialogdoe(String a, String b, String c, String d, String e,
				String f, int g, String h);
	}

	public void setDialogcallbacke(Dialogcallbacke dialogcallbacke) {
		this.dialogcallbacke = dialogcallbacke;

	}

	public void show() {
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.mdldbtn:
			if (djcsa == 0) {
				clsl();
			} else {
				qbj = mdldtvqbjg.getText().toString();
				ccj = mdldtvccjg.getText().toString();
				zhl = mdldtvzdzhl.getText().toString().trim();
				dialogcallbacke.dialogdoe(qcmz, dwzw, gg, xhsl, qbj, ccj,
						carid, zhl);
				dismiss();
			}
			break;
		case R.id.mdldbtnqx:
			dismiss();
			break;
		}
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
					tvclsl.setText(clsl + "");
					changea();
					addcls();

				} else {
					clsl = 0;
					tvclsl.setText(clsl + "");
					mdldxshctv.setText(statusbean.msg);
					changea();
					addcls();
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				clsl = 0;
				tvclsl.setText(clsl + "");
				dialogall.dismiss();
				changea();
				addcls();
			}
		});
	}

	private void changea() {
		djcsa = 1;
		mdldlla.setVisibility(View.GONE);
		if (dwzw == "null") {
			jqdwa = dwxzlist.get(0);
			jqdw = jqdwa.split("T");
			jqdwb = jqdw[0];
			jqdwc = jqdwb + d;
			dwzw = jqdwc;
		}
		if (gg == "null") {
			gg = ccxzlist.get(0);
		}
		mdldllb.setVisibility(View.VISIBLE);
		mdldxshctv.setTextColor(Color.RED);
		mdldxshctv.setText(qcmz + dwzw + "		" + gg);
	}

	private void tjcshsj() {
		dwxzlist = new ArrayList<String>();
		ccxzlist = new ArrayList<String>();
		clslist = new ArrayList<String>();
		mdldbtn.setOnClickListener(this);
		mdldbtnqx.setOnClickListener(this);
		mdldxshctv.setText(qcmz);
	}

	private void cshid() {
		mdldbtnqx = (Button) dialog.findViewById(R.id.mdldbtnqx);
		mdldbtn = (Button) dialog.findViewById(R.id.mdldbtn);
		tvclsl = (TextView) dialog.findViewById(R.id.tvclsl);
		mdldxshctv = (TextView) dialog.findViewById(R.id.mdldxshctv);
		mypaintadwxz = (WheelView) dialog.findViewById(R.id.mypaintadwxz);
		mypaintaccxz = (WheelView) dialog.findViewById(R.id.mypaintaccxz);
		mypaintaclsla = (WheelView) dialog.findViewById(R.id.mypaintaclsla);
		mdldimva = (ImageView) dialog.findViewById(R.id.mdldimva);
		mdldtvzdzzl = (TextView) dialog.findViewById(R.id.mdldtvzdzzl);
		mdldtvzdzhl = (TextView) dialog.findViewById(R.id.mdldtvzdzhl);
		mdldtvqbjg = (TextView) dialog.findViewById(R.id.mdldtvqbjg);
		mdldtvccjg = (TextView) dialog.findViewById(R.id.mdldtvccjg);
		mdldlla = (LinearLayout) dialog.findViewById(R.id.mdldlla);
		mdldllb = (LinearLayout) dialog.findViewById(R.id.mdldllb);
		mdldllb.setVisibility(View.GONE);
	}

	private void mypaintadwxz() {
		for (int h = 0; h < ayb.size(); h++) {
			if (ayb.get(h).toString().trim().split(",")[0].equals(qcmz)) {
				dwxzlist.add(ayb.get(h).toString().trim().split(",")[1]);
			}
		}
		mypaintadwxzadapter = new Mypaintadwxzadapter(context, dwxzlist,
				getdwitem(dwxzlist.get(0)), maxsize, minsize);
		mypaintadwxz.setVisibleItems(5);
		mypaintadwxz.setViewAdapter(mypaintadwxzadapter);
		mypaintadwxz.setCurrentItem(getdwitem(dwxzlist.get(0)));
		mypaintadwxz.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String text = (String) mypaintadwxzadapter.getItemText(wheel
						.getCurrentItem());
				ccxzlist.clear();
				for (int i = 0; i < aya.size(); i++) {
					if (text.equals(aya.get(i).toString().trim().split(",")[1])
							&& qcmz.equals(aya.get(i).toString().trim()
									.split(",")[0])) {
						ccxzlist.add(aya.get(i).toString().trim().split(",")[3]
								+ "M");
						mypaintaccxz(1);
						if (ccxzlist.get(0).equals(
								aya.get(i).toString().trim().split(",")[3]
										+ "M")) {
							mdldtvzdzzl.setText(aya.get(i).toString().trim()
									.split(",")[7]
									+ d);
							mdldtvzdzhl.setText(aya.get(i).toString().trim()
									.split(",")[6]
									+ "方");
							mdldtvqbjg.setText(aya.get(i).toString().trim()
									.split(",")[8]
									+ "元/10公里");
							mdldtvccjg.setText(aya.get(i).toString().trim()
									.split(",")[9]
									+ "元/公里");
							carid = Integer.valueOf(aya.get(i).toString()
									.trim().split(",")[4].trim());
						}
					}
				}
				for (int k = 0; k < dwxzlist.size(); k++) {
					if (text.equals(dwxzlist.get(k))) {
						jqdwa = dwxzlist.get(k);
						jqdw = jqdwa.split("T");
						jqdwb = jqdw[0];
						jqdwc = jqdwb + d;
						dwzw = jqdwc;
						gg = ccxzlist.get(0);
					}
				}
				if (text.equals("0.6T")) {
					mdldimva.setBackgroundResource(imgid[0]);
				} else if (text.equals("3T")) {
					mdldimva.setBackgroundResource(imgid[1]);
				} else if (text.equals("5T")) {
					mdldimva.setBackgroundResource(imgid[2]);
				} else if (text.equals("8T")) {
					mdldimva.setBackgroundResource(imgid[3]);
				} else if (text.equals("10T") || text.equals("15T")) {
					mdldimva.setBackgroundResource(imgid[4]);
				}
				setTextviewSize(text, mypaintadwxzadapter);
			}
		});
		mypaintadwxz.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) mypaintadwxzadapter
						.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mypaintadwxzadapter);
			}

			@Override
			public void onScrollingStarted(WheelView wheel) {
			}
		});
	}

	public void setTextviewSize(String curriteItemText,
			Mypaintadwxzadapter adapter) {
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

	public void setTextviewSizea(String curriteItemText,
			Mypaintaccxzadapter adapter) {
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

	private class Mypaintaccxzadapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected Mypaintaccxzadapter(Context context, ArrayList<String> list,
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

	private class Mypaintadwxzadapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected Mypaintadwxzadapter(Context context, ArrayList<String> list,
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

	public int getdwitem(String date) {
		int size = dwxzlist.size();
		int dateindex = 0;
		for (int i = 0; i < size; i++) {
			if (date.equals(dwxzlist.get(i))) {
				return dateindex;
			} else {
				dateindex++;
			}
		}
		return dateindex;
	}

	public int getclslitem(String date) {
		int size = clslist.size();
		int dateindex = 0;
		for (int i = 0; i < size; i++) {
			if (date.equals(clslist.get(i))) {
				return dateindex;
			} else {
				dateindex++;
			}
		}
		return dateindex;
	}

	public int getccxzitem(String date) {
		int size = ccxzlist.size();
		int dateindex = 0;
		for (int i = 0; i < size; i++) {
			if (date.equals(ccxzlist.get(i))) {
				return dateindex;
			} else {
				dateindex++;
			}
		}
		return dateindex;
	}

	private void mypaintaccxz(int m) {
		if (m == 0) {
			for (int h = 0; h < aya.size(); h++) {
				if (aya.get(h).toString().trim().split(",")[0].equals(qcmz)
						&& dwxzlist.get(0).equals(
								aya.get(h).toString().trim().split(",")[1])) {
					ccxzlist.add(aya.get(h).toString().split(",")[3] + "M");
				}
			}
			for (int n = 0; n < aya.size(); n++) {
				if (aya.get(n).toString().trim().split(",")[0].trim().equals(
						qcmz)
						&& dwxzlist.get(0).equals(
								aya.get(n).toString().trim().split(",")[1]
										.trim())
						&& ccxzlist.get(0).equals(
								aya.get(n).toString().trim().split(",")[3]
										.trim() + "M")) {
					mdldtvzdzzl
							.setText(aya.get(n).toString().trim().split(",")[7]
									+ d);
					mdldtvzdzhl
							.setText(aya.get(n).toString().trim().split(",")[6]
									+ "方");
					mdldtvqbjg
							.setText(aya.get(n).toString().trim().split(",")[8]
									+ "元/10公里");
					mdldtvccjg
							.setText(aya.get(n).toString().trim().split(",")[9]
									+ "元/公里");
					carid = Integer.valueOf(aya.get(n).toString().trim()
							.split(",")[4].trim());
					jqdwa = dwxzlist.get(0);
					gg = ccxzlist.get(0);
				}
			}
			if (dwxzlist.get(0).equals("0.6T")) {
				mdldimva.setBackgroundResource(imgid[0]);
			} else if (dwxzlist.get(0).equals("3T")) {
				mdldimva.setBackgroundResource(imgid[1]);
			} else if (dwxzlist.get(0).equals("5T")) {
				mdldimva.setBackgroundResource(imgid[2]);
			} else if (dwxzlist.get(0).equals("8T")) {
				mdldimva.setBackgroundResource(imgid[3]);
			} else if (dwxzlist.get(0).equals("10T")
					|| dwxzlist.get(0).equals("15T")) {
				mdldimva.setBackgroundResource(imgid[4]);
			}
		}
		mypaintaccxzadapter = new Mypaintaccxzadapter(context, ccxzlist,
				getccxzitem(ccxzlist.get(0)), maxsize, minsize);
		mypaintaccxz.setVisibleItems(5);
		mypaintaccxz.setViewAdapter(mypaintaccxzadapter);
		mypaintaccxz.setCurrentItem(getccxzitem(ccxzlist.get(0)));
		mypaintaccxz.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String text = (String) mypaintaccxzadapter.getItemText(wheel
						.getCurrentItem());
				for (int i = 0; i < aya.size(); i++) {
					if (aya.get(i).toString().trim().split(",")[0].trim()
							.equals(qcmz)
							&& jqdwa.equals(aya.get(i).toString().trim()
									.split(",")[1].trim())
							&& text.equals(aya.get(i).toString().trim()
									.split(",")[3].trim()
									+ "M")) {
						mdldtvzdzzl.setText(aya.get(i).toString().trim()
								.split(",")[7]
								+ d);
						mdldtvzdzhl.setText(aya.get(i).toString().trim()
								.split(",")[6]
								+ "方");
						mdldtvqbjg.setText(aya.get(i).toString().trim()
								.split(",")[8]
								+ "元/10公里");
						mdldtvccjg.setText(aya.get(i).toString().trim()
								.split(",")[9]
								+ "元/公里");
						carid = Integer.valueOf(aya.get(i).toString().trim()
								.split(",")[4].trim());
					}
				}
				gg = text;
				setTextviewSizea(text, mypaintaccxzadapter);
			}
		});
		mypaintaccxz.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) mypaintaccxzadapter
						.getItemText(wheel.getCurrentItem());
				setTextviewSizea(currentText, mypaintaccxzadapter);
			}

			@Override
			public void onScrollingStarted(WheelView wheel) {
			}
		});
	}

	private void addcls() {
		clslist.clear();
		if (clsl == 0) {
			xhsl = 0 + l;
			clslist.add(0 + l);
			mypaintaclsladapter = new Mypaintaclsladapter(context, clslist,
					getclslitem(0 + l), maxsize, minsize);
			mypaintaclsla.setVisibleItems(5);
			mypaintaclsla.setViewAdapter(mypaintaclsladapter);
			mypaintaclsla.setCurrentItem(getclslitem(0 + l));
		} else {
			if (clsl > 5) {
				for (int i = 1; i < 6; i++) {
					clslist.add(i + l);
				}
			} else {
				for (int i = 1; i < clsl + 1; i++) {
					clslist.add(i + l);
				}
			}
			mypaintaclsladapter = new Mypaintaclsladapter(context, clslist,
					getclslitem(1 + l), maxsize, minsize);
			mypaintaclsla.setVisibleItems(5);
			mypaintaclsla.setViewAdapter(mypaintaclsladapter);
			mypaintaclsla.setCurrentItem(getclslitem(1 + l));
		}
		mypaintaclsla.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String text = (String) mypaintaclsladapter.getItemText(wheel
						.getCurrentItem());
				xhsl = text;
				setTextviewSizeb(text, mypaintaclsladapter);
			}
		});
		mypaintaclsla.addScrollingListener(new OnWheelScrollListener() {

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
}