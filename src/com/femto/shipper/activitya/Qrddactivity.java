package com.femto.shipper.activitya;

import java.util.HashMap;
import android.os.Bundle;
import android.view.Window;
import com.femto.shipper.R;
import com.femto.shipper.activitya.Mydialogl.Dialogcallbackl;
import com.femto.shipper.activitya.Mydialogone.Dialogcallbackone;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.umeng.analytics.MobclickAgent;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Qrddactivity extends BaseActivity implements OnClickListener {
	private SharedPreferences sharedpreferences, sharedpreferencesa,
			sharedpreferencese, sharedpreferencesb, sharedpreferencesc,
			sharedpreferencesd;
	private Editor editor = null, editora = null, editorb = null;
	private LinearLayout tjmll, zfbll, yhjll;
	private RelativeLayout qrddrla, qrddrlc, sxsg, zkyssrl;
	private TextView qrddjetv, qrddglstv, qrddtva, qrddtvb, qrddtvc, qrddtvd,
			qrddtve, qrddtvf, qrddtvg, qrddfzrnametv, qrddfzrteltv,
			qrddtimetva, qrddsxtva, qrddbztvb, qrddclnametva, qrddcldwtvb,
			qrddclggtvc, qrddclsltvd, qrddyqxivtva, zffstv, qrddtvaname,
			qrddtvbname, qrddtvcname, qrddtvdname, qrddtvename, qrddtvfname,
			qrddtvgname, qrddtvatel, qrddtvbtel, qrddtvctel, qrddtvdtel,
			qrddtvetel, qrddtvftel, qrddtvgtel, zkysstv;
	@SuppressWarnings("unused")
	private String mytime, eyyq, qcmc, qcdw, qcgg, qcsl, ztdalon, ztdalat,
			one = "one", ztdblon, ztdblat, ztdclon, ztdclat, ztddlon, ztddlat,
			ztdelon, ztdelat, groupid, sp_pwd, claimcode, sp_id, tjmsyf,
			middle_addr1_contact, start_addr_contact, newaddress, zxsga = "",
			zxsgb = "", bzzl, ztatel, ztbtel, ztcdd, ztbdd, ztadd, xhtel, spid,
			sppwd, zhdd, middle_addr1, middle_addr2, middle_addr3,
			middle_addr4, middle_addr5, nwqxsyggn, middle_addr3_contact,
			middle_addr4_contact, middle_addr5_contact, orderamounta, zhlxr,
			zhtel, ztdda, ztddb, xzdsj, qczhl, end_addr_contact, zhlat, zhlon,
			xhlat, xhlon, ztcname, dhm, ztctel, ztaname, ztbname, fzrxm,
			start_addr, end_addr, middle_addr2_contact, nwktylzfgn, payment_id,
			fzrdh, hwsx, ztddd, alljulioo, ztdname, ztdtel, ztedd, xhname,
			phonea, passworda, dkjsyf, zhname, orderamount, orderno, xhlxr,
			xhdd, xhdh, ztename, ztetel;
	private ImageView zffsiv, zkyssiv;
	@SuppressWarnings("unused")
	private int carid, shi, xhi, gci, gcrs, slcs, sli, dt, swcylx = 0,
			djlzfb = 1, car_quantity, height, ztdllalpheight, ztdllblpheight,
			ztdllclpheight, ztdlldlpheight, ztdllelpheight;
	private int zffs[] = { R.drawable.zhifubao, R.drawable.yuejie,
			R.drawable.yla };
	public static String qrdd = "hello";
	private Bundle bundlea = null;
	private Button qrddbtnxyb;
	public static Activity qrddactivity;
	private LinearLayout ztdlle, ztdlla, ztdllb, ztdllc, ztdlld, qrddssll;
	private boolean isOpened = false;
	private LayoutParams layoutall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.qrddactivity);
		qrddactivity = this;
		cshid();
		pd();
		pdsfyj();
	}

	private void cshid() {
		sharedpreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedpreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedpreferences.getString(
				getResources().getString(R.string.pwd), "");
		sharedpreferencesc = getSharedPreferences("user_xinxi",
				Activity.MODE_PRIVATE);
		groupid = sharedpreferencesc.getString("group_id", "").trim();
		sharedpreferencese = getSharedPreferences("onesql",
				Activity.MODE_PRIVATE);
		one = sharedpreferencese.getString("one", "");
		nwqxsyggn = getResources().getString(R.string.nwqxsyggn);
		nwktylzfgn = getResources().getString(R.string.nwktylzfgn);
		qrddrla = (RelativeLayout) findViewById(R.id.qrddrla);
		qrddrlc = (RelativeLayout) findViewById(R.id.qrddrlc);
		sxsg = (RelativeLayout) findViewById(R.id.sxsg);
		zkyssrl = (RelativeLayout) findViewById(R.id.zkyssrl);
		zfbll = (LinearLayout) findViewById(R.id.zfbll);
		yhjll = (LinearLayout) findViewById(R.id.yhjll);
		tjmll = (LinearLayout) findViewById(R.id.tjmll);
		qrddssll = (LinearLayout) findViewById(R.id.qrddssll);
		ztdllb = (LinearLayout) findViewById(R.id.ztdllb);
		ztdllc = (LinearLayout) findViewById(R.id.ztdllc);
		ztdlld = (LinearLayout) findViewById(R.id.ztdlld);
		ztdlle = (LinearLayout) findViewById(R.id.ztdlle);
		ztdlla = (LinearLayout) findViewById(R.id.ztdlla);
		qrddjetv = (TextView) findViewById(R.id.qrddjetv);
		qrddglstv = (TextView) findViewById(R.id.qrddglstv);
		qrddtva = (TextView) findViewById(R.id.qrddtva);
		qrddtvb = (TextView) findViewById(R.id.qrddtvb);
		qrddtvc = (TextView) findViewById(R.id.qrddtvc);
		qrddtvd = (TextView) findViewById(R.id.qrddtvd);
		qrddtve = (TextView) findViewById(R.id.qrddtve);
		qrddtvf = (TextView) findViewById(R.id.qrddtvf);
		qrddtvg = (TextView) findViewById(R.id.qrddtvg);
		qrddtvaname = (TextView) findViewById(R.id.qrddtvaname);
		qrddtvbname = (TextView) findViewById(R.id.qrddtvbname);
		qrddtvcname = (TextView) findViewById(R.id.qrddtvcname);
		qrddtvdname = (TextView) findViewById(R.id.qrddtvdname);
		qrddtvename = (TextView) findViewById(R.id.qrddtvename);
		qrddtvfname = (TextView) findViewById(R.id.qrddtvfname);
		qrddtvgname = (TextView) findViewById(R.id.qrddtvgname);
		qrddtvatel = (TextView) findViewById(R.id.qrddtvatel);
		qrddtvbtel = (TextView) findViewById(R.id.qrddtvbtel);
		qrddtvctel = (TextView) findViewById(R.id.qrddtvctel);
		qrddtvdtel = (TextView) findViewById(R.id.qrddtvdtel);
		qrddtvetel = (TextView) findViewById(R.id.qrddtvetel);
		qrddtvftel = (TextView) findViewById(R.id.qrddtvftel);
		qrddtvgtel = (TextView) findViewById(R.id.qrddtvgtel);
		zkysstv = (TextView) findViewById(R.id.zkysstv);
		qrddfzrnametv = (TextView) findViewById(R.id.qrddfzrnametv);
		qrddfzrteltv = (TextView) findViewById(R.id.qrddfzrteltv);
		// qrddiva = (ImageView) findViewById(R.id.qrddiva);
		qrddtimetva = (TextView) findViewById(R.id.qrddtimetva);
		qrddsxtva = (TextView) findViewById(R.id.qrddsxtva);
		qrddbztvb = (TextView) findViewById(R.id.qrddbztvb);
		qrddclnametva = (TextView) findViewById(R.id.qrddclnametva);
		qrddcldwtvb = (TextView) findViewById(R.id.qrddcldwtvb);
		qrddclggtvc = (TextView) findViewById(R.id.qrddclggtvc);
		qrddclsltvd = (TextView) findViewById(R.id.qrddclsltvd);
		qrddyqxivtva = (TextView) findViewById(R.id.qrddyqxivtva);
		zffstv = (TextView) findViewById(R.id.zffstv);
		zffsiv = (ImageView) findViewById(R.id.zffsiv);
		zkyssiv = (ImageView) findViewById(R.id.zkyssiv);
		qrddbtnxyb = (Button) findViewById(R.id.qrddbtnxyb);
		zffstv.setText(getResources().getString(R.string.zfb));
		zffsiv.setBackgroundResource(zffs[0]);
		qrddrlc.setOnClickListener(this);
		qrddrla.setOnClickListener(this);
		tjmll.setOnClickListener(this);
		zfbll.setOnClickListener(this);
		yhjll.setOnClickListener(this);
		qrddbtnxyb.setOnClickListener(this);
		zkyssrl.setOnClickListener(this);
	}

	private void pdsfyj() {
		if (groupid.equals("8") || groupid.equals("9")) {
			zffstv.setText(getResources().getString(R.string.yjzh));
			zffsiv.setBackgroundResource(zffs[1]);
			djlzfb = 2;
		} else {
			zffstv.setText(getResources().getString(R.string.zfb));
			zffsiv.setBackgroundResource(zffs[0]);
			djlzfb = 1;
		}
	}

	private void toggle(boolean animated) {

		if (isOpened) {
			if (animated) {
				int start = height;
				int end = 0;
				doAnimation(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) qrddssll
						.getLayoutParams();
				layoutParams.height = 0;
				qrddssll.setLayoutParams(layoutParams);
			}
		} else {
			if (animated) {
				int start = 0;
				int end = height;
				doAnimation(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) qrddssll
						.getLayoutParams();
				layoutParams.height = height;
				qrddssll.setLayoutParams(layoutParams);
			}
		}
		if (isOpened) {
			ObjectAnimator.ofFloat(zkyssiv, "rotation", -180, 0).start();
			zkysstv.setText(getResources().getString(R.string.zk));
		} else {
			ObjectAnimator.ofFloat(zkyssiv, "rotation", 0, -180).start();
			zkysstv.setText(getResources().getString(R.string.sq));
		}
		isOpened = !isOpened;
	}

	private void doAnimation(int start, int end) {
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(1000);
		valueAnimator.setIntValues(start, end);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();
				LayoutParams layoutParams = (LayoutParams) qrddssll
						.getLayoutParams();
				layoutParams.height = value;
				qrddssll.setLayoutParams(layoutParams);
			};
		});
		valueAnimator.start();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.zkyssrl:
			toggle(true);
			break;
		case R.id.qrddbtnxyb:
			xybyza();
			break;
		case R.id.qrddrla:
			finish();
			break;
		case R.id.qrddrlc:
			if (swcylx == 0) {
				putlx();
				swcylx++;
			}
			break;
		case R.id.tjmll:
			if (groupid.equals("6") || groupid.equals("9")) {
				if (tjmsyf.equals("1")) {
					startActivity(Sbjjr.class);
				} else {
					startActivity(Sbjjr.class);
				}
			} else {
				showToast(getResources().getString(R.string.nwqxsyggn));
			}
			break;
		case R.id.zfbll:
			Mydialogl mydialogl = new Mydialogl(Qrddactivity.this, djlzfb,
					groupid, nwqxsyggn, nwktylzfgn);
			mydialogl.setDialogcallbackl(mydialogldissmiss);
			mydialogl.show();
			break;
		case R.id.yhjll:
			if (groupid.equals("5")) {
				if (dkjsyf.equals("1")) {
					startActivity(Dkj.class);
				} else {
					startActivity(Dkj.class);
				}
			} else {
				showToast(getResources().getString(R.string.hyyhwfsydkj));
			}
			break;

		}
	}

	Dialogcallbackl mydialogldissmiss = new Dialogcallbackl() {

		@Override
		public void dialogdol(int a) {
			djlzfb = a;
			if (a == 1) {
				zffstv.setText(getResources().getString(R.string.zfb));
			}
			if (a == 2) {
				zffstv.setText(getResources().getString(R.string.yjzh));
			}
			if (a == 3) {
				zffstv.setText(getResources().getString(R.string.xyk));
			}
			zffsiv.setBackgroundResource(zffs[a - 1]);
		}

	};

	@Override
	protected void onResume() {
		super.onResume();
		sharedpreferencesb = getSharedPreferences("jga", Activity.MODE_PRIVATE);
		orderamounta = ToolUtils.fengbujiequ(sharedpreferencesb.getString(
				"orderamount", ""));
		orderno = sharedpreferencesb.getString("orderno", "");
		dkjsyf = sharedpreferencesb.getString("dkjsyf", "");
		dhm = sharedpreferencesb.getString("dhm", "");
		tjmsyf = sharedpreferencesb.getString("tjmsyf", "");
		spid = sharedpreferencesb.getString("spid", "");
		sppwd = sharedpreferencesb.getString("sppwd", "");
		qrddjetv.setText(ToolUtils.fengbujiequ(orderamounta));
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		qrdd = "hello";
		MobclickAgent.onPause(this);
	}

	private void xybyza() {
		if (!one.equals("two")) {
			editorb = sharedpreferencese.edit();
			editorb.putString("one", "two");
			editorb.commit();
			Mydialogone mydialogone = new Mydialogone(Qrddactivity.this);
			mydialogone.setDialogcallbackone(mydialogpdissmiss);
			mydialogone.show();
		} else {
			xybyz();
		}
	}

	Dialogcallbackone mydialogpdissmiss = new Dialogcallbackone() {
		@Override
		public void dialogdop(int a) {
			xybyz();
		}
	};

	private void xybyz() {
		if (dhm.equals("0")) {
			claimcode = "";
		} else {
			claimcode = dhm;
		}
		if (spid.equals("0")) {
			sp_id = "";
		} else {
			sp_id = spid;
		}
		if (sppwd.equals("0")) {
			sp_pwd = "";
		} else {
			sp_pwd = sppwd;
		}
		if (djlzfb == 1) {
			payment_id = "2";
		}
		if (djlzfb == 2) {
			payment_id = "1";
		}
		if (djlzfb == 3) {
			payment_id = "3";
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "order_pay");
		map.put("order_no", orderno);
		map.put("pwd", passworda);
		map.put("username", phonea);
		map.put("claimcode", claimcode);
		map.put("sp_id", sp_id);
		map.put("sp_pwd", sp_pwd);
		map.put("payment_id", payment_id);
		// String json = new Gson().toJson(map);
		// Log.e("json>>>", json);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.jjrtc + jiaMi;
		showProgressDialog(getResources().getString(R.string.jzz));
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				StatusBean tijiao = GsonUtils.json2Bean(arg0.result,
						StatusBean.class);
				if (tijiao.status.equals("0")) {
					sharedpreferencesd = getSharedPreferences("allnoandcls",
							Activity.MODE_PRIVATE);
					editora = sharedpreferencesd.edit();
					editora.putString("orderamounta", orderamounta);
					editora.putString("orderno", orderno);
					editora.putString("car_quantity",
							String.valueOf(car_quantity));
					editora.putString("payment_id", payment_id);
					editora.commit();
					showToast(getResources().getString(
							R.string.qndddtjcgqddsjjd));
					startActivity(new Intent(Qrddactivity.this, Aapd.class));
					Ycactivityb.ycactivityb.finish();
					Qrddactivity.this.finish();
					// intenta = new Intent(Qrddactivity.this, Aapd.class);
					// bundleb = new Bundle();
					// bundleb.putInt("car_quantity", car_quantity);
					// intenta.putExtras(bundleb);
					// startActivity(intenta);
					// Aapd.aapd = "qrto";
				} else {
					showToast("failed" + tijiao.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				dismissProgressDialog();
				showToast(getResources().getString(R.string.wlyc));
			}
		});
	}

	@Override
	protected void onDestroy() {
		if (bundlea != null) {
			bundlea.clear();
		}
		if (editor != null) {
			editor.clear();
		}
		if (editora != null) {
			editora.clear();
		}
		super.onDestroy();
	}

	private void pd() {
		if (qrdd.equals("ybto")) {
			bundlea = new Bundle();
			bundlea = getIntent().getExtras();
			try {
				mytime = bundlea.getString("mytime");
				xzdsj = bundlea.getString("xzdsj");
				eyyq = bundlea.getString("eyyq");
				qcmc = bundlea.getString("qcmc");
				qcdw = bundlea.getString("qcdw");
				qcgg = bundlea.getString("qcgg");
				qcsl = bundlea.getString("qcsl");
				carid = bundlea.getInt("carid");
				alljulioo = bundlea.getString("alljulioo");
				qczhl = bundlea.getString("qczhl");
				ztdalat = bundlea.getString("ztdalat");
				ztdalon = bundlea.getString("ztdalon");
				ztdblat = bundlea.getString("ztdblat");
				ztdblon = bundlea.getString("ztdblon");
				ztdclat = bundlea.getString("ztdclat");
				ztdclon = bundlea.getString("ztdclon");
				ztddlat = bundlea.getString("ztddlat");
				ztddlon = bundlea.getString("ztddlon");
				ztdelat = bundlea.getString("ztdelat");
				ztdelon = bundlea.getString("ztdelon");
				zhlat = bundlea.getString("zhlat");
				zhlon = bundlea.getString("zhlon");
				xhlat = bundlea.getString("xhlat");
				xhlon = bundlea.getString("xhlon");
				zhdd = bundlea.getString("zhdd");
				zhname = bundlea.getString("zhname");
				zhtel = bundlea.getString("zhtel");
				xhdd = bundlea.getString("xhdd");
				xhname = bundlea.getString("xhname");
				xhtel = bundlea.getString("xhtel");
				ztadd = bundlea.getString("ztadd");
				ztbdd = bundlea.getString("ztbdd");
				ztcdd = bundlea.getString("ztcdd");
				ztddd = bundlea.getString("ztddd");
				ztedd = bundlea.getString("ztedd");
				ztaname = bundlea.getString("ztaname");
				ztbname = bundlea.getString("ztbname");
				ztcname = bundlea.getString("ztcname");
				ztdname = bundlea.getString("ztdname");
				ztename = bundlea.getString("ztename");
				ztatel = bundlea.getString("ztatel");
				ztbtel = bundlea.getString("ztbtel");
				ztctel = bundlea.getString("ztctel");
				ztdtel = bundlea.getString("ztdtel");
				ztetel = bundlea.getString("ztetel");
				fzrxm = bundlea.getString("fzrxm");
				fzrdh = bundlea.getString("fzrdh");
				hwsx = bundlea.getString("hwsx");
				bzzl = bundlea.getString("bzzl");
				shi = bundlea.getInt("shi");
				xhi = bundlea.getInt("xhi");
				gci = bundlea.getInt("gci");
				gcrs = bundlea.getInt("gcrs");
				slcs = bundlea.getInt("slcs");
				sli = bundlea.getInt("sli");
				dt = bundlea.getInt("dt");
				car_quantity = bundlea.getInt("car_quantity");
				orderamount = bundlea.getString("orderamount");
				orderno = bundlea.getString("orderno");
				// showToast(zhlat + "," + zhlon + "," + xhlat + "," + xhlon);
			} catch (Exception e) {
				// e.printStackTrace();
			}
			qrddjetv.setText(ToolUtils.fengbujiequ(orderamount));
			qrddglstv
					.setText(alljulioo + getResources().getString(R.string.gl));
			qrddfzrnametv.setText(fzrxm);
			qrddfzrteltv.setText(fzrdh);
			qrddtimetva.setText(xzdsj);
			qrddsxtva.setText(hwsx);
			qrddbztvb.setText(bzzl);
			qrddclnametva.setText(qcmc);
			sharedpreferencesa = getSharedPreferences("jga",
					Activity.MODE_PRIVATE);
			editor = sharedpreferencesa.edit();
			editor.putString("orderamount", orderamount);
			editor.putString("orderno", orderno);
			editor.putString("dkjsyf", "0");
			editor.putString("dhm", "0");
			editor.putString("tjmsyf", "0");
			editor.putString("spid", "0");
			editor.putString("sppwd", "0");
			editor.commit();
			if (qcmc.equals(getResources().getString(R.string.jzxcar))) {
				qrddclggtvc.setText(getResources().getString(R.string.zh)
						+ qczhl);
			} else {
				qrddclggtvc.setText(qcgg);
			}
			qrddclsltvd.setText(qcsl);
			if (qcdw.equals("null")) {
				qrddcldwtvb.setVisibility(View.GONE);
			} else {
				qrddcldwtvb.setText(qcdw);
			}
			if (shi == 0) {
				zxsga = "";
			} else {
				zxsga = getResources().getString(R.string.shanghuo) + "		";
				zxsgb = zxsga + zxsgb;
			}
			if (sli == 0) {
				zxsga = "";
			} else {
				zxsga = getResources().getString(R.string.shanglou) + "("
						+ slcs + getResources().getString(R.string.lou) + ")		";
				zxsgb = zxsga + zxsgb;
			}
			if (xhi == 0) {
				zxsga = "";
			} else {
				zxsga = getResources().getString(R.string.xiahuo) + "		";
				zxsgb = zxsga + zxsgb;
			}
			if (gci == 0) {
				zxsga = "";
			} else {
				zxsga = getResources().getString(R.string.gence) + "(" + gcrs
						+ getResources().getString(R.string.ren) + ")		";
				zxsgb = zxsga + zxsgb;
			}
			qrddyqxivtva.setText(zxsgb);
			if (shi == 0 && gci == 0 && sli == 0 && xhi == 0) {
				sxsg.setVisibility(View.GONE);
			}
			if (ztadd.equals("null")) {
				zkyssrl.setVisibility(View.GONE);
				ztdlle.setVisibility(View.GONE);
				ztdlld.setVisibility(View.GONE);
				ztdllc.setVisibility(View.GONE);
				ztdllb.setVisibility(View.GONE);
				ztdlla.setVisibility(View.GONE);
				// ztdllalp = (LayoutParams) ztdlla.getLayoutParams();
				// ztdllalp.height = 0;
				// ztdlla.setLayoutParams(ztdllalp);
				// ztdllblp = (LayoutParams) ztdllb.getLayoutParams();
				// ztdllblp.height = 0;
				// ztdllb.setLayoutParams(ztdllblp);
				// ztdllclp = (LayoutParams) ztdllc.getLayoutParams();
				// ztdllclp.height = 0;
				// ztdllc.setLayoutParams(ztdllclp);
				// ztdlldlp = (LayoutParams) ztdlld.getLayoutParams();
				// ztdlldlp.height = 0;
				// ztdlld.setLayoutParams(ztdlldlp);
				// ztdllelp = (LayoutParams) ztdlle.getLayoutParams();
				// ztdllelp.height = 0;
				// ztdlle.setLayoutParams(ztdllelp);

				qrddtva.setText(zhdd);
				qrddtvg.setText(xhdd);
				if (zhtel.equals("") || zhtel.equals("null") || zhtel == null) {
				} else {
					qrddtvatel.setText(zhtel);
				}
				if (zhname.equals("") || zhname.equals("null")
						|| zhname == null) {
				} else {
					qrddtvaname.setText(zhname);
				}
				if (xhname.equals("") || xhname.equals("null")
						|| xhname == null) {
				} else {
					qrddtvgname.setText(xhname);
				}
				if (xhtel.equals("") || xhtel.equals("null") || xhtel == null) {
				} else {
					qrddtvgtel.setText(xhtel);
				}
			} else {
				if (ztbdd.equals("null")) {
					ztdlle.setVisibility(View.GONE);
					ztdlld.setVisibility(View.GONE);
					ztdllc.setVisibility(View.GONE);
					ztdllb.setVisibility(View.GONE);
					qrddtva.setText(zhdd);
					qrddtvg.setText(xhdd);
					qrddtvb.setText(ztadd);
					if (zhtel.equals("") || zhtel.equals("null")
							|| zhtel == null) {
					} else {
						qrddtvatel.setText(zhtel);
					}
					if (zhname.equals("") || zhname.equals("null")
							|| zhname == null) {
					} else {
						qrddtvaname.setText(zhname);
					}
					if (xhname.equals("") || xhname.equals("null")
							|| xhname == null) {
					} else {
						qrddtvgname.setText(xhname);
					}
					if (xhtel.equals("") || xhtel.equals("null")
							|| xhtel == null) {
					} else {
						qrddtvgtel.setText(xhtel);
					}
					if (ztaname.equals("") || ztaname.equals("null")
							|| ztaname == null) {
					} else {
						qrddtvbname.setText(ztaname);
					}
					if (ztatel.equals("") || ztatel.equals("null")
							|| ztatel == null) {
					} else {
						qrddtvbtel.setText(ztatel);
					}
				} else {
					if (ztcdd.equals("null")) {
						ztdlle.setVisibility(View.GONE);
						ztdlld.setVisibility(View.GONE);
						ztdllc.setVisibility(View.GONE);
						qrddtva.setText(zhdd);
						qrddtvg.setText(xhdd);
						qrddtvb.setText(ztadd);
						qrddtvc.setText(ztbdd);
						if (zhtel.equals("") || zhtel.equals("null")
								|| zhtel == null) {
						} else {
							qrddtvatel.setText(zhtel);
						}
						if (zhname.equals("") || zhname.equals("null")
								|| zhname == null) {
						} else {
							qrddtvaname.setText(zhname);
						}
						if (xhname.equals("") || xhname.equals("null")
								|| xhname == null) {
						} else {
							qrddtvgname.setText(xhname);
						}
						if (xhtel.equals("") || xhtel.equals("null")
								|| xhtel == null) {
						} else {
							qrddtvgtel.setText(xhtel);
						}
						if (ztaname.equals("") || ztaname.equals("null")
								|| ztaname == null) {
						} else {
							qrddtvbname.setText(ztaname);
						}
						if (ztatel.equals("") || ztatel.equals("null")
								|| ztatel == null) {
						} else {
							qrddtvbtel.setText(ztatel);
						}
						if (ztbname.equals("") || ztbname.equals("null")
								|| ztbname == null) {
						} else {
							qrddtvcname.setText(ztbname);
						}
						if (ztbtel.equals("") || ztbtel.equals("null")
								|| ztbtel == null) {
						} else {
							qrddtvctel.setText(ztbtel);
						}
						if (ztcname.equals("") || ztcname.equals("null")
								|| ztcname == null) {
						} else {
							qrddtvdname.setText(ztcname);
						}
						if (ztctel.equals("") || ztctel.equals("null")
								|| ztctel == null) {
						} else {
							qrddtvdtel.setText(ztctel);
						}
						if (ztdname.equals("") || ztdname.equals("null")
								|| ztdname == null) {
						} else {
							qrddtvename.setText(ztdname);
						}
						if (ztdtel.equals("") || ztdtel.equals("null")
								|| ztdtel == null) {
						} else {
							qrddtvetel.setText(ztdtel);
						}
						if (ztename.equals("") || ztename.equals("null")
								|| ztename == null) {
						} else {
							qrddtvfname.setText(ztename);
						}
						if (ztetel.equals("") || ztetel.equals("null")
								|| ztetel == null) {
						} else {
							qrddtvftel.setText(ztetel);
						}
					} else {
						if (ztddd.equals("null")) {
							ztdlle.setVisibility(View.GONE);
							ztdlld.setVisibility(View.GONE);
							qrddtva.setText(zhdd);
							qrddtvg.setText(xhdd);
							qrddtvb.setText(ztadd);
							qrddtvc.setText(ztbdd);
							qrddtvd.setText(ztcdd);
							if (zhtel.equals("") || zhtel.equals("null")
									|| zhtel == null) {
							} else {
								qrddtvatel.setText(zhtel);
							}
							if (zhname.equals("") || zhname.equals("null")
									|| zhname == null) {
							} else {
								qrddtvaname.setText(zhname);
							}
							if (xhname.equals("") || xhname.equals("null")
									|| xhname == null) {
							} else {
								qrddtvgname.setText(xhname);
							}
							if (xhtel.equals("") || xhtel.equals("null")
									|| xhtel == null) {
							} else {
								qrddtvgtel.setText(xhtel);
							}
							if (ztaname.equals("") || ztaname.equals("null")
									|| ztaname == null) {
							} else {
								qrddtvbname.setText(ztaname);
							}
							if (ztatel.equals("") || ztatel.equals("null")
									|| ztatel == null) {
							} else {
								qrddtvbtel.setText(ztatel);
							}
							if (ztbname.equals("") || ztbname.equals("null")
									|| ztbname == null) {
							} else {
								qrddtvcname.setText(ztbname);
							}
							if (ztbtel.equals("") || ztbtel.equals("null")
									|| ztbtel == null) {
							} else {
								qrddtvctel.setText(ztbtel);
							}
							if (ztcname.equals("") || ztcname.equals("null")
									|| ztcname == null) {
							} else {
								qrddtvdname.setText(ztcname);
							}
							if (ztctel.equals("") || ztctel.equals("null")
									|| ztctel == null) {
							} else {
								qrddtvdtel.setText(ztctel);
							}
							if (ztdname.equals("") || ztdname.equals("null")
									|| ztdname == null) {
							} else {
								qrddtvename.setText(ztdname);
							}
							if (ztdtel.equals("") || ztdtel.equals("null")
									|| ztdtel == null) {
							} else {
								qrddtvetel.setText(ztdtel);
							}
							if (ztename.equals("") || ztename.equals("null")
									|| ztename == null) {
							} else {
								qrddtvfname.setText(ztename);
							}
							if (ztetel.equals("") || ztetel.equals("null")
									|| ztetel == null) {
							} else {
								qrddtvftel.setText(ztetel);
							}
						} else {
							if (ztedd.equals("null")) {
								ztdlle.setVisibility(View.GONE);
								qrddtva.setText(zhdd);
								qrddtvg.setText(xhdd);
								qrddtvb.setText(ztadd);
								qrddtvc.setText(ztbdd);
								qrddtvd.setText(ztcdd);
								qrddtve.setText(ztddd);
								if (zhtel.equals("") || zhtel.equals("null")
										|| zhtel == null) {
								} else {
									qrddtvatel.setText(zhtel);
								}
								if (zhname.equals("") || zhname.equals("null")
										|| zhname == null) {
								} else {
									qrddtvaname.setText(zhname);
								}
								if (xhname.equals("") || xhname.equals("null")
										|| xhname == null) {
								} else {
									qrddtvgname.setText(xhname);
								}
								if (xhtel.equals("") || xhtel.equals("null")
										|| xhtel == null) {
								} else {
									qrddtvgtel.setText(xhtel);
								}
								if (ztaname.equals("")
										|| ztaname.equals("null")
										|| ztaname == null) {
								} else {
									qrddtvbname.setText(ztaname);
								}
								if (ztatel.equals("") || ztatel.equals("null")
										|| ztatel == null) {
								} else {
									qrddtvbtel.setText(ztatel);
								}
								if (ztbname.equals("")
										|| ztbname.equals("null")
										|| ztbname == null) {
								} else {
									qrddtvcname.setText(ztbname);
								}
								if (ztbtel.equals("") || ztbtel.equals("null")
										|| ztbtel == null) {
								} else {
									qrddtvctel.setText(ztbtel);
								}
								if (ztcname.equals("")
										|| ztcname.equals("null")
										|| ztcname == null) {
								} else {
									qrddtvdname.setText(ztcname);
								}
								if (ztctel.equals("") || ztctel.equals("null")
										|| ztctel == null) {
								} else {
									qrddtvdtel.setText(ztctel);
								}
								if (ztdname.equals("")
										|| ztdname.equals("null")
										|| ztdname == null) {
								} else {
									qrddtvename.setText(ztdname);
								}
								if (ztdtel.equals("") || ztdtel.equals("null")
										|| ztdtel == null) {
								} else {
									qrddtvetel.setText(ztdtel);
								}
								if (ztename.equals("")
										|| ztename.equals("null")
										|| ztename == null) {
								} else {
									qrddtvfname.setText(ztename);
								}
								if (ztetel.equals("") || ztetel.equals("null")
										|| ztetel == null) {
								} else {
									qrddtvftel.setText(ztetel);
								}
							} else {
								qrddtva.setText(zhdd);
								qrddtvg.setText(xhdd);
								qrddtvb.setText(ztadd);
								qrddtvc.setText(ztbdd);
								qrddtvd.setText(ztcdd);
								qrddtve.setText(ztddd);
								qrddtvf.setText(ztedd);
								if (zhtel.equals("") || zhtel.equals("null")
										|| zhtel == null) {
								} else {
									qrddtvatel.setText(zhtel);
								}
								if (zhname.equals("") || zhname.equals("null")
										|| zhname == null) {
								} else {
									qrddtvaname.setText(zhname);
								}
								if (xhname.equals("") || xhname.equals("null")
										|| xhname == null) {
								} else {
									qrddtvgname.setText(xhname);
								}
								if (xhtel.equals("") || xhtel.equals("null")
										|| xhtel == null) {
								} else {
									qrddtvgtel.setText(xhtel);
								}
								if (ztaname.equals("")
										|| ztaname.equals("null")
										|| ztaname == null) {
								} else {
									qrddtvbname.setText(ztaname);
								}
								if (ztatel.equals("") || ztatel.equals("null")
										|| ztatel == null) {
								} else {
									qrddtvbtel.setText(ztatel);
								}
								if (ztbname.equals("")
										|| ztbname.equals("null")
										|| ztbname == null) {
								} else {
									qrddtvcname.setText(ztbname);
								}
								if (ztbtel.equals("") || ztbtel.equals("null")
										|| ztbtel == null) {
								} else {
									qrddtvctel.setText(ztbtel);
								}
								if (ztcname.equals("")
										|| ztcname.equals("null")
										|| ztcname == null) {
								} else {
									qrddtvdname.setText(ztcname);
								}
								if (ztctel.equals("") || ztctel.equals("null")
										|| ztctel == null) {
								} else {
									qrddtvdtel.setText(ztctel);
								}
								if (ztdname.equals("")
										|| ztdname.equals("null")
										|| ztdname == null) {
								} else {
									qrddtvename.setText(ztdname);
								}
								if (ztdtel.equals("") || ztdtel.equals("null")
										|| ztdtel == null) {
								} else {
									qrddtvetel.setText(ztdtel);
								}
								if (ztename.equals("")
										|| ztename.equals("null")
										|| ztename == null) {
								} else {
									qrddtvfname.setText(ztename);
								}
								if (ztetel.equals("") || ztetel.equals("null")
										|| ztetel == null) {
								} else {
									qrddtvftel.setText(ztetel);
								}
							}
						}
					}
				}
			}
			qrddssll.measure(0, 0);
			height = qrddssll.getMeasuredHeight();
			layoutall = (LayoutParams) qrddssll.getLayoutParams();
			layoutall.height = 0;
			qrddssll.setLayoutParams(layoutall);
		}
	}

	private void putlx() {
		if ((zhname.equals("null") && zhtel.equals("null"))
				|| (zhname.equals("") && zhtel.equals(""))) {
			start_addr_contact = "";
		} else {
			if (zhname.equals("null") || zhname.equals("")) {
				start_addr_contact = "|" + zhtel;
			} else {
				if (zhtel.equals("null") || zhtel.equals("")) {
					start_addr_contact = zhname + "|";
				} else {
					start_addr_contact = zhname + "|" + zhtel;
				}
			}
		}
		if ((xhname.equals("null") && xhtel.equals("null"))
				|| (xhname.equals("") && xhtel.equals(""))) {
			end_addr_contact = "";
		} else {
			if (xhname.equals("null") || xhname.equals("")) {
				end_addr_contact = "|" + xhtel;
			} else {
				if (xhtel.equals("null") || xhtel.equals("")) {
					end_addr_contact = xhname + "|";
				} else {
					end_addr_contact = xhname + "|" + xhtel;
				}
			}
		}
		if ((ztaname.equals("null") && ztatel.equals("null"))
				|| ztaname.equals("") && ztatel.equals("")) {
			middle_addr1_contact = "";
		} else {
			if (ztaname.equals("null") || ztaname.equals("")) {
				middle_addr1_contact = "|" + ztatel;
			} else {
				if (ztatel.equals("null") || ztatel.equals("")) {
					middle_addr1_contact = ztaname + "|";
				} else {
					middle_addr1_contact = ztaname + "|" + ztatel;
				}
			}
		}
		if ((ztbname.equals("null") && ztbtel.equals("null"))
				|| (ztbname.equals("") && ztbtel.equals(""))) {
			middle_addr2_contact = "";
		} else {
			if (ztbname.equals("null") || ztbname.equals("")) {
				middle_addr2_contact = "|" + ztbtel;
			} else {
				if (ztbtel.equals("null") || ztbtel.equals("")) {
					middle_addr2_contact = ztbname + "|";
				} else {
					middle_addr2_contact = ztbname + "|" + ztbtel;
				}
			}
		}
		if ((ztcname.equals("null") && ztctel.equals("null"))
				|| (ztcname.equals("") && ztctel.equals(""))) {
			middle_addr3_contact = "";
		} else {
			if (ztcname.equals("null") || ztcname.equals("")) {
				middle_addr3_contact = "|" + ztctel;
			} else {
				if (ztctel.equals("null") || ztctel.equals("")) {
					middle_addr3_contact = ztcname + "|";
				} else {
					middle_addr3_contact = ztcname + "|" + ztctel;
				}
			}
		}
		if ((ztdname.equals("null") && ztdtel.equals("null"))
				|| (ztdname.equals("") && ztdtel.equals(""))) {
			middle_addr4_contact = "";
		} else {
			if (ztdname.equals("null") || ztdname.equals("")) {
				middle_addr4_contact = "|" + ztdtel;
			} else {
				if (ztdtel.equals("null") || ztdtel.equals("")) {
					middle_addr4_contact = ztdname + "|";
				} else {
					middle_addr4_contact = ztdname + "|" + ztdtel;
				}
			}
		}
		if ((ztename.equals("null") && ztetel.equals("null"))
				|| (ztename.equals("") && ztetel.equals(""))) {
			middle_addr5_contact = "";
		} else {
			if (ztename.equals("null") || ztename.equals("")) {
				middle_addr5_contact = "|" + ztetel;
			} else {
				if (ztetel.equals("null") || ztetel.equals("")) {
					middle_addr5_contact = ztename + "|";
				} else {
					middle_addr5_contact = ztename + "|" + ztetel;
				}
			}
		}
		if (ztadd.equals("null") || ztadd.equals("")) {
			middle_addr1 = "";
		} else {
			middle_addr1 = ztadd + "|" + ztdalon + "|" + ztdalat;
		}
		if (ztbdd.equals("null") || ztbdd.equals("")) {
			middle_addr2 = "";
		} else {
			middle_addr2 = ztbdd + "|" + ztdblon + "|" + ztdblat;
		}
		if (ztcdd.equals("null") || ztcdd.equals("")) {
			middle_addr3 = "";
		} else {
			middle_addr3 = ztcdd + "|" + ztdclon + "|" + ztdclat;
		}
		if (ztddd.equals("null") || ztddd.equals("")) {
			middle_addr4 = "";
		} else {
			middle_addr4 = ztddd + "|" + ztddlon + "|" + ztddlat;
		}
		if (ztedd.equals("null") || ztedd.equals("")) {
			middle_addr5 = "";
		} else {
			middle_addr5 = ztedd + "|" + ztdelon + "|" + ztdelat;
		}
		start_addr = zhdd + "|" + zhlon + "|" + zhlat;
		end_addr = xhdd + "|" + xhlon + "|" + xhlat;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "add_my_line");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("start_addr_contact", start_addr_contact);
		map.put("middle_addr1_contact", middle_addr1_contact);
		map.put("middle_addr2_contact", middle_addr2_contact);
		map.put("middle_addr3_contact", middle_addr3_contact);
		map.put("middle_addr4_contact", middle_addr4_contact);
		map.put("middle_addr5_contact", middle_addr5_contact);
		map.put("end_addr_contact", end_addr_contact);
		map.put("start_addr", start_addr);
		map.put("middle_addr1", middle_addr1);
		map.put("middle_addr2", middle_addr2);
		map.put("middle_addr3", middle_addr3);
		map.put("middle_addr4", middle_addr4);
		map.put("middle_addr5", middle_addr5);
		map.put("end_addr", end_addr);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.cyadda;
		RequestParams params = new RequestParams();
		params.addBodyParameter(Net.cyaddb, jiaMi);
		application.doPost(url, params, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Cyhelper cy = GsonUtils.json2Bean(arg0.result, Cyhelper.class);
				if (cy.status.equals("0")) {
					showToast(getResources().getString(R.string.yswcydz));
				} else {
					showToast(getResources().getString(R.string.tjsb)
							+ cy.status);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast(getResources().getString(R.string.wlyc));
			}
		});
	}
}