package com.femto.shipper.activitya;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption.DrivingPolicy;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.femto.shipper.R;
import com.femto.shipper.activitya.Mydialoga.Dialogcallback;
import com.femto.shipper.activityab.Mydialogb.Dialogcallbackb;
import com.femto.shipper.activityab.Mydialogd.Dialogcallbackd;
import com.femto.shipper.activityab.Mydialoge.Dialogcallbacke;
import com.femto.shipper.activityab.Mydialogf;
import com.femto.shipper.activityab.Mydialogb;
import com.femto.shipper.activityab.Mydialogf.Dialogcallbackf;
import com.femto.shipper.activityab.Mydialogg.Dialogcallbackg;
import com.femto.shipper.activityab.Mydialogg;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;
import com.femto.shipper.activityab.Mydialogd;
import com.femto.shipper.activityab.Mydialoge;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
@SuppressWarnings("deprecation")
public class Ycactivitya extends BaseActivity implements OnClickListener,
		OnItemSelectedListener, OnItemClickListener, BDLocationListener {
	private int alljuli = 0, bcztqcgs = 0, bczthgjg = 0, bcztbswl = 0,
			bcztdwb = 0, bcztdcm = 0, bcztscm = 0, gls;
	private boolean ntype = false;
	private TextView ycatva, gallerytva, ycatvb;
	@SuppressWarnings("unused")
	private RelativeLayout ycarla, ycarlb;
	private LinearLayout ycalla;
	private int[] im = new int[] { R.drawable.xiangshi, R.drawable.kaiding,
			R.drawable.gaolan, R.drawable.jzxa };
	private String[] imtv = { "", "", "", "" };
	private int[] of = new int[] { 0, 0, 0, 0 };
	private ImageButton zjztdianimbtn, delimbtna, delimbtnb, delimbtnc,
			delimbtnd, delimbtne;
	private Button ycbtnxyba;
	private TextView ztaddresse, ztaddressd, ztaddressb, ztaddressc,
			ztaddressa, addztdlxrnamea, addztdlxrnameb, addztdlxrnamec,
			addztdlxrnamed, addztdlxrnamee, addztdlxrtela, addztdlxrtelb,
			addztdlxrtelc, addztdlxrteld, addztdlxrtele, yfgztv, zhaddress,
			zhlxrname, zhlxrtel, xhaddress, xhlxrname, xhlxrdh, ygfy, ygfyxs;
	@SuppressWarnings("unused")
	private String startcity, endcity, eyyqa = "", eyyqb, ztaddressestr,
			strnewsdfminute, strnewsdfday, strnewsdfmonth, strnewsdfyear,
			strnewsyatemtime, strnewsdfhour, ztaddressdstr, ztaddressbstr,
			ztaddresscstr, qcmz, qcmzjzx, ztdaddress, ztdbddress, nickname,
			ztdcddress, ztddddress, ztdeddress, strxz, c, stry, strr, strzy,
			strze, strzs, strzsi, strzw, strzl, strzr, alljulioo,
			addztdlxrnamebstr, addztdlxrnamecstr, addztdlxrnamedstr,
			addztdlxrnameestr, addztdlxrtelbstr, addztdlxrtelcstr, ycsj,
			addztdlxrteldstr, addztdlxrtelestr, zhdd = "null", zhname = "null",
			zhtel = "null", xhdd = "null", xhname = "null", xhtel = "null",
			ztadd = "null", ztaname = "null", ztatel = "null", ztbdd = "null",
			ztbname = "null", ztbtel = "null", ztcdd = "null",
			ztcname = "null", ztctel = "null", ztddd = "null",
			ztdname = "null", ztdtel = "null", ztedd = "null",
			ztename = "null", ztetel = "null", qcmc, jqdtimea, jqdmonth, yeara,
			montha, daya, qcdw = "null", qcgg, qcsl = "", jqdaya, jqdayc,
			jqtimea, qczhl, newtel = "null", newaddress = "null",
			newlat = "null", newname = "null", newlon, ayear = "0000",
			month = "00", djddf = "null", day = "00", hh = "00", mm = "00",
			ss = "00", mytime = "00000000000000", zhlat = "null",
			zhlon = "null", xzdsj = "null", xhlat = "null", xhlon = "null",
			ztdalat = "null", ztdalon = "null", ztdblat = "null",
			wdlxnewaddress, strztdblat = "null", strztdblon = "null",
			strztdclat = "null", strztdclon = "null", strztddlat = "null",
			strztddlon = "null", strztdelat = "null", strztdelon = "null",
			ztdblon = "null", ztdclat = "null", ztdclon = "null",
			ztddlat = "null", ztddlon = "null", ztdelat = "null",
			ztdelon = "null", phonea, passworda, eyyq = "", zdzhlz, zdzhlo,
			zdzhlt, zdzhlth, zdzhlf, zdzzlz, zdzzlo, zdzzlt, strztadd,
			strztbdd, strztcdd, strztddd, strztedd, strztaname, strztbname,
			strztcname, strztdname, strztename, strztatel, strztbtel,
			strztctel, strztdtel, strztetel, zdzhljzxa, zdzhljzxb, zdzhljzxc,
			zdzhljzxd, zdzzla, zdzzlb, zdzzlc, address, lat, lon, zdzzlth,
			zdzzlf, zdzzlfi, zdzhlfi, addnewaddress, zhadd, xhadd, zdzhlsix,
			zdzhlsenver, zdzhleight, d, l, qcmze, qcmzf, strycatvb;
	private RelativeLayout ztdadjrl, ztdbdjrl, ztdcdjrl, ztdddjrl, ztdedjrl,
			cyxzrl, ycaddrla, ycaddrlb, ycaddrlc, ycaddrld, ycaddrle, ztdarl,
			ztdfrl;
	@SuppressWarnings("unused")
	private int i = 0, btnazt = 0, btnbzt = 0, btnczt = 0, btndzt = 0,
			btnezt = 0, ztdsl = 0, intyeara, intmonth, intday, intyear, sj,
			week, intmonthaa, intyearaa, monthdayzg, intdaya, intmontha,
			jianday, qcgs = 0, hgjg = 0, bswl = 0, dwb = 0, dcm = 0, scm = 0,
			jqdmonthint, jqqcslint, jqddayint, jqtimeint, jqsecondint, year,
			carid = 0;
	@SuppressWarnings("unused")
	private static int ztda = 0, ztdb = 0, ztdc = 0, ztdd = 0, ztde = 0;
	public static String ada = "hello";
	private Bundle bundlea = null, bundleb = null;
	private Intent intenta;
	private String jqdtimeb[], jqddaya[], qcslstr[];
	public static Activity ycactivitya;
	public static int msychsyuyc;
	private ImageButton ycimbtna;
	private RoutePlanSearch search = null;
	private Gallery gallerya;
	private SharedPreferences sharedPreferences, sharedPreferencesb;
	private Date datea, newdate;
	private SimpleDateFormat sdfyear, sdfmonth, sdfday, newsdfminute,
			newsdfday, newsdfmonth, newsdfyear, newsdfhour;
	private ArrayList<PlanNode> list = null;
	private PlanNode startnode, endnode;
	private LatLng startlatlng, endlatlng;
	private ConnectivityManager connectivitymanagera = null;
	private NetworkInfo networkinfoa = null;
	private LocationClient loclient = null;
	private LocationClientOption loclientoption = null;
	private GeoCoder geocodera = null, geocoderb = null;
	private LatLng xhlatlng, zhlatlng;
	@SuppressWarnings("rawtypes")
	private static ArrayList aya, ayb, ayc;

	private void hqclsj() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "car_type");
		String jiami = ToolUtils.JiaMi(map);
		String url = Net.Allcar + jiami;
		showProgressDialog(getResources().getString(R.string.jzz));
		application.doget(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				dismissProgressDialog();
				showToast(getResources().getString(R.string.wlyc));
			}

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				Carlist carlist = GsonUtils.json2Bean(arg0.result,
						Carlist.class);
				if (carlist.status.equals("0")) {
					aya.clear();
					ayb.clear();
					ayc.clear();
					ayc.add("ylbsj");
					for (int i = 0; i < carlist.list.size(); i++) {
						for (int m = 0; m < carlist.list.get(i).sub_title
								.size(); m++) {
							ayb.add(carlist.list.get(i).title.trim()
									+ ","
									+ carlist.list.get(i).sub_title.get(m).title
											.trim());
							for (int n = 0; n < carlist.list.get(i).sub_title
									.get(m).sub_title.size(); n++) {
								aya.add(carlist.list.get(i).title.trim()
										+ ","
										+ carlist.list.get(i).sub_title.get(m).title
												.trim()
										+ ","
										+ carlist.list.get(i).sub_title.get(m).big_img_url
												.trim()
										+ ","
										+ carlist.list.get(i).sub_title.get(m).sub_title
												.get(n).title.trim()
										+ ","
										+ carlist.list.get(i).sub_title.get(m).sub_title
												.get(n).id.trim()
										+ ","
										+ carlist.list.get(i).sub_title.get(m).sub_title
												.get(n).amount.trim()
										+ ","
										+ carlist.list.get(i).sub_title.get(m).sub_title
												.get(n).max_load.trim()
										+ ","
										+ carlist.list.get(i).sub_title.get(m).sub_title
												.get(n).max_tonnage.trim()
										+ ","
										+ carlist.list.get(i).sub_title.get(m).sub_title
												.get(n).start_momey.trim()
										+ ","
										+ carlist.list.get(i).sub_title.get(m).sub_title
												.get(n).exceed_momey.trim());
							}
						}
					}
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		connectivitymanagera = null;
		networkinfoa = null;
		loclient = null;
		loclientoption = null;
		startlatlng = null;
		endlatlng = null;
		startnode = null;
		endnode = null;
		sharedPreferences = null;
		sdfyear = null;
		sdfmonth = null;
		sdfday = null;
		datea = null;
		sharedPreferences = null;
		sharedPreferencesb = null;
		// sharedPreferencesc = null;
		gallerya = null;
		if (geocodera != null) {
			geocodera.destroy();
		}
		if (geocoderb != null) {
			geocoderb.destroy();
		}
		if (loclient != null) {
			loclient.stop();
		}
		if (list != null) {
			list.clear();
		}
		if (search != null) {
			search.destroy();
		}
		if (bundlea != null) {
			bundlea.clear();
		}
		if (bundleb != null) {
			bundleb.clear();
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ycactivitya);
		ycactivitya = this;
		cshid();
		if (ayc.size() != 0) {
			if (!ayc.get(0).equals("ylbsj")) {
				hqclsj();
			}
		} else {
			hqclsj();
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.ycbtnxyba:
			zhadd = zhaddress.getText().toString().trim();
			xhadd = xhaddress.getText().toString().trim();
			ztdaddress = ztaddressa.getText().toString().trim();
			ztdbddress = ztaddressb.getText().toString().trim();
			ztdcddress = ztaddressc.getText().toString().trim();
			ztddddress = ztaddressd.getText().toString().trim();
			ztdeddress = ztaddresse.getText().toString().trim();
			qcslstr = qcsl.split(getResources().getString(R.string.l));
			if (zhadd.equals(getResources().getString(R.string.cnlzh))
					|| xhadd.equals(getResources().getString(R.string.dnlxh))) {
				showToast(getResources().getString(R.string.qxzhhxhdd));
			} else {
				if (Integer.valueOf(qcslstr[0]) < 1) {
					showToast(getResources().getString(R.string.dqwkyclqcxxz));
					gallerytva.setError(getResources().getString(
							R.string.dqwkyclqcxxz));
				} else {
					zcpd();
				}
			}
			break;
		case R.id.cyxzrl:
			ycsj = ycatva.getText().toString().trim();
			ada = "hello";
			strycatvb = ycatvb.getText().toString().trim();
			xzdsj = ycatva.getText().toString().trim();
			intenta = new Intent(Ycactivitya.this, Wdlxadd.class);
			bundleb = new Bundle();
			bundleb.putInt("bcztqcgs", bcztqcgs);
			bundleb.putInt("bczthgjg", bczthgjg);
			bundleb.putInt("bcztbswl", bcztbswl);
			bundleb.putInt("bcztdwb", bcztdwb);
			bundleb.putInt("bcztdcm", bcztdcm);
			bundleb.putInt("bcztscm", bcztscm);
			bundleb.putString("eyyq", eyyq);
			bundleb.putString("strycatvb", strycatvb);
			bundleb.putString("mytime", mytime);
			bundleb.putString("xzdsj", xzdsj);
			intenta.putExtras(bundleb);
			startActivity(intenta);
			break;
		case R.id.yfgztv:
			ada = "hello";
			startActivity(new Intent(Ycactivitya.this, Intentjfbz.class));
			break;
		case R.id.ztdarl:
			ycsj = ycatva.getText().toString().trim();
			ada = "hello";
			djddf = "zhdd";
			Addressposition.adb = "yato";
			xzdsj = ycatva.getText().toString().trim();
			strycatvb = ycatvb.getText().toString().trim();
			intenta = new Intent(Ycactivitya.this, Addressposition.class);
			bundleb = new Bundle();
			bundleb.putString("eyyq", eyyq);
			bundleb.putString("strycatvb", strycatvb);
			bundleb.putString("djddf", djddf);
			bundleb.putString("mytime", mytime);
			bundleb.putString("xzdsj", xzdsj);
			bundleb.putString("ztdalat", ztdalat);
			bundleb.putString("ztdalon", ztdalon);
			bundleb.putString("ztdblat", ztdblat);
			bundleb.putString("ztdblon", ztdblon);
			bundleb.putString("ztdclat", ztdclat);
			bundleb.putString("ztdclon", ztdclon);
			bundleb.putString("ztddlat", ztddlat);
			bundleb.putString("ztddlon", ztddlon);
			bundleb.putString("ztdelat", ztdelat);
			bundleb.putString("ztdelon", ztdelon);
			bundleb.putString("zhlat", zhlat);
			bundleb.putString("zhlon", zhlon);
			bundleb.putString("xhlat", xhlat);
			bundleb.putString("xhlon", xhlon);
			bundleb.putString("zhdd", zhdd);
			bundleb.putString("zhname", zhname);
			bundleb.putString("zhtel", zhtel);
			bundleb.putString("xhdd", xhdd);
			bundleb.putString("xhname", xhname);
			bundleb.putString("xhtel", xhtel);
			bundleb.putString("ztadd", ztadd);
			bundleb.putString("ztbdd", ztbdd);
			bundleb.putString("ztcdd", ztcdd);
			bundleb.putString("ztddd", ztddd);
			bundleb.putString("ztedd", ztedd);
			bundleb.putString("ztaname", ztaname);
			bundleb.putString("ztbname", ztbname);
			bundleb.putString("ztcname", ztcname);
			bundleb.putString("ztdname", ztdname);
			bundleb.putString("ztename", ztename);
			bundleb.putString("ztatel", ztatel);
			bundleb.putString("ztbtel", ztbtel);
			bundleb.putString("ztctel", ztctel);
			bundleb.putString("ztdtel", ztdtel);
			bundleb.putString("ztetel", ztetel);
			bundleb.putInt("bcztqcgs", bcztqcgs);
			bundleb.putInt("bczthgjg", bczthgjg);
			bundleb.putInt("bcztbswl", bcztbswl);
			bundleb.putInt("bcztdwb", bcztdwb);
			bundleb.putInt("bcztdcm", bcztdcm);
			bundleb.putInt("bcztscm", bcztscm);
			bundleb.putInt("ztdsl", ztdsl);
			intenta.putExtras(bundleb);
			startActivity(intenta);
			break;
		case R.id.ztdfrl:
			ycsj = ycatva.getText().toString().trim();
			ada = "hello";
			djddf = "xhdd";
			Addressposition.adb = "yato";
			xzdsj = ycatva.getText().toString().trim();
			strycatvb = ycatvb.getText().toString().trim();
			intenta = new Intent(Ycactivitya.this, Addressposition.class);
			bundleb = new Bundle();
			bundleb.putString("eyyq", eyyq);
			bundleb.putString("strycatvb", strycatvb);
			bundleb.putString("ztdalat", ztdalat);
			bundleb.putString("ztdalon", ztdalon);
			bundleb.putString("ztdblat", ztdblat);
			bundleb.putString("ztdblon", ztdblon);
			bundleb.putString("ztdclat", ztdclat);
			bundleb.putString("ztdclon", ztdclon);
			bundleb.putString("ztddlat", ztddlat);
			bundleb.putString("ztddlon", ztddlon);
			bundleb.putString("ztdelat", ztdelat);
			bundleb.putString("ztdelon", ztdelon);
			bundleb.putString("zhlat", zhlat);
			bundleb.putString("zhlon", zhlon);
			bundleb.putString("xhlat", xhlat);
			bundleb.putString("xhlon", xhlon);
			bundleb.putString("zhdd", zhdd);
			bundleb.putString("zhname", zhname);
			bundleb.putString("zhtel", zhtel);
			bundleb.putString("xhdd", xhdd);
			bundleb.putString("xhname", xhname);
			bundleb.putString("xhtel", xhtel);
			bundleb.putString("ztadd", ztadd);
			bundleb.putString("ztbdd", ztbdd);
			bundleb.putString("ztcdd", ztcdd);
			bundleb.putString("ztddd", ztddd);
			bundleb.putString("ztedd", ztedd);
			bundleb.putString("ztaname", ztaname);
			bundleb.putString("ztbname", ztbname);
			bundleb.putString("ztcname", ztcname);
			bundleb.putString("ztdname", ztdname);
			bundleb.putString("ztename", ztename);
			bundleb.putString("ztatel", ztatel);
			bundleb.putString("ztbtel", ztbtel);
			bundleb.putString("ztctel", ztctel);
			bundleb.putString("ztdtel", ztdtel);
			bundleb.putString("ztetel", ztetel);
			bundleb.putString("djddf", djddf);
			bundleb.putString("mytime", mytime);
			bundleb.putString("xzdsj", xzdsj);
			bundleb.putInt("ztdsl", ztdsl);
			bundleb.putInt("bcztqcgs", bcztqcgs);
			bundleb.putInt("bczthgjg", bczthgjg);
			bundleb.putInt("bcztbswl", bcztbswl);
			bundleb.putInt("bcztdwb", bcztdwb);
			bundleb.putInt("bcztdcm", bcztdcm);
			bundleb.putInt("bcztscm", bcztscm);
			intenta.putExtras(bundleb);
			startActivity(intenta);
			break;
		case R.id.ztdadjrl:
			ycsj = ycatva.getText().toString().trim();
			ada = "hello";
			djddf = "ztdda";
			Addressposition.adb = "yato";
			xzdsj = ycatva.getText().toString().trim();
			strycatvb = ycatvb.getText().toString().trim();
			intenta = new Intent(Ycactivitya.this, Addressposition.class);
			bundleb = new Bundle();
			bundleb.putString("eyyq", eyyq);
			bundleb.putString("strycatvb", strycatvb);
			bundleb.putString("ztdalat", ztdalat);
			bundleb.putString("ztdalon", ztdalon);
			bundleb.putString("ztdblat", ztdblat);
			bundleb.putString("ztdblon", ztdblon);
			bundleb.putString("ztdclat", ztdclat);
			bundleb.putString("ztdclon", ztdclon);
			bundleb.putString("ztddlat", ztddlat);
			bundleb.putString("ztddlon", ztddlon);
			bundleb.putString("ztdelat", ztdelat);
			bundleb.putString("ztdelon", ztdelon);
			bundleb.putString("zhlat", zhlat);
			bundleb.putString("zhlon", zhlon);
			bundleb.putInt("ztdsl", ztdsl);
			bundleb.putString("xhlat", xhlat);
			bundleb.putString("xhlon", xhlon);
			bundleb.putString("zhdd", zhdd);
			bundleb.putString("zhname", zhname);
			bundleb.putString("zhtel", zhtel);
			bundleb.putString("xhdd", xhdd);
			bundleb.putString("xhname", xhname);
			bundleb.putString("xhtel", xhtel);
			bundleb.putString("ztadd", ztadd);
			bundleb.putString("ztbdd", ztbdd);
			bundleb.putString("ztcdd", ztcdd);
			bundleb.putString("ztddd", ztddd);
			bundleb.putString("ztedd", ztedd);
			bundleb.putString("ztaname", ztaname);
			bundleb.putString("ztbname", ztbname);
			bundleb.putString("ztcname", ztcname);
			bundleb.putString("ztdname", ztdname);
			bundleb.putString("ztename", ztename);
			bundleb.putString("ztatel", ztatel);
			bundleb.putString("ztbtel", ztbtel);
			bundleb.putString("ztctel", ztctel);
			bundleb.putString("ztdtel", ztdtel);
			bundleb.putString("ztetel", ztetel);
			bundleb.putString("djddf", djddf);
			bundleb.putString("mytime", mytime);
			bundleb.putString("xzdsj", xzdsj);
			bundleb.putInt("bcztqcgs", bcztqcgs);
			bundleb.putInt("bczthgjg", bczthgjg);
			bundleb.putInt("bcztbswl", bcztbswl);
			bundleb.putInt("bcztdwb", bcztdwb);
			bundleb.putInt("bcztdcm", bcztdcm);
			bundleb.putInt("bcztscm", bcztscm);
			intenta.putExtras(bundleb);
			startActivity(intenta);
			break;
		case R.id.ztdbdjrl:
			ycsj = ycatva.getText().toString().trim();
			ada = "hello";
			djddf = "ztddb";
			Addressposition.adb = "yato";
			xzdsj = ycatva.getText().toString().trim();
			strycatvb = ycatvb.getText().toString().trim();
			intenta = new Intent(Ycactivitya.this, Addressposition.class);
			bundleb = new Bundle();
			bundleb.putString("eyyq", eyyq);
			bundleb.putString("strycatvb", strycatvb);
			bundleb.putString("ztdalat", ztdalat);
			bundleb.putString("ztdalon", ztdalon);
			bundleb.putString("ztdblat", ztdblat);
			bundleb.putString("ztdblon", ztdblon);
			bundleb.putString("ztdclat", ztdclat);
			bundleb.putString("ztdclon", ztdclon);
			bundleb.putString("ztddlat", ztddlat);
			bundleb.putString("ztddlon", ztddlon);
			bundleb.putString("ztdelat", ztdelat);
			bundleb.putInt("ztdsl", ztdsl);
			bundleb.putString("ztdelon", ztdelon);
			bundleb.putString("zhlat", zhlat);
			bundleb.putString("zhlon", zhlon);
			bundleb.putString("xhlat", xhlat);
			bundleb.putString("xhlon", xhlon);
			bundleb.putString("zhdd", zhdd);
			bundleb.putString("zhname", zhname);
			bundleb.putString("zhtel", zhtel);
			bundleb.putString("xhdd", xhdd);
			bundleb.putString("xhname", xhname);
			bundleb.putString("xhtel", xhtel);
			bundleb.putString("ztadd", ztadd);
			bundleb.putString("ztbdd", ztbdd);
			bundleb.putString("ztcdd", ztcdd);
			bundleb.putString("ztddd", ztddd);
			bundleb.putString("ztedd", ztedd);
			bundleb.putString("ztaname", ztaname);
			bundleb.putString("ztbname", ztbname);
			bundleb.putString("ztcname", ztcname);
			bundleb.putString("ztdname", ztdname);
			bundleb.putString("ztename", ztename);
			bundleb.putString("ztatel", ztatel);
			bundleb.putString("ztbtel", ztbtel);
			bundleb.putString("ztctel", ztctel);
			bundleb.putString("ztdtel", ztdtel);
			bundleb.putString("ztetel", ztetel);
			bundleb.putString("djddf", djddf);
			bundleb.putString("mytime", mytime);
			bundleb.putString("xzdsj", xzdsj);
			bundleb.putInt("bcztqcgs", bcztqcgs);
			bundleb.putInt("bczthgjg", bczthgjg);
			bundleb.putInt("bcztbswl", bcztbswl);
			bundleb.putInt("bcztdwb", bcztdwb);
			bundleb.putInt("bcztdcm", bcztdcm);
			bundleb.putInt("bcztscm", bcztscm);
			intenta.putExtras(bundleb);
			startActivity(intenta);
			break;
		case R.id.ztdcdjrl:
			ycsj = ycatva.getText().toString().trim();
			ada = "hello";
			djddf = "ztddc";
			Addressposition.adb = "yato";
			xzdsj = ycatva.getText().toString().trim();
			strycatvb = ycatvb.getText().toString().trim();
			intenta = new Intent(Ycactivitya.this, Addressposition.class);
			bundleb = new Bundle();
			bundleb.putString("eyyq", eyyq);
			bundleb.putString("strycatvb", strycatvb);
			bundleb.putString("ztdalat", ztdalat);
			bundleb.putString("ztdalon", ztdalon);
			bundleb.putString("ztdblat", ztdblat);
			bundleb.putString("ztdblon", ztdblon);
			bundleb.putString("ztdclat", ztdclat);
			bundleb.putString("ztdclon", ztdclon);
			bundleb.putString("ztddlat", ztddlat);
			bundleb.putInt("ztdsl", ztdsl);
			bundleb.putString("ztddlon", ztddlon);
			bundleb.putString("ztdelat", ztdelat);
			bundleb.putString("ztdelon", ztdelon);
			bundleb.putString("zhlat", zhlat);
			bundleb.putString("zhlon", zhlon);
			bundleb.putString("xhlat", xhlat);
			bundleb.putString("xhlon", xhlon);
			bundleb.putString("zhdd", zhdd);
			bundleb.putString("zhname", zhname);
			bundleb.putString("zhtel", zhtel);
			bundleb.putString("xhdd", xhdd);
			bundleb.putString("xhname", xhname);
			bundleb.putString("xhtel", xhtel);
			bundleb.putString("ztadd", ztadd);
			bundleb.putString("ztbdd", ztbdd);
			bundleb.putString("ztcdd", ztcdd);
			bundleb.putString("ztddd", ztddd);
			bundleb.putString("ztedd", ztedd);
			bundleb.putString("ztaname", ztaname);
			bundleb.putString("ztbname", ztbname);
			bundleb.putString("ztcname", ztcname);
			bundleb.putString("ztdname", ztdname);
			bundleb.putString("ztename", ztename);
			bundleb.putString("ztatel", ztatel);
			bundleb.putString("ztbtel", ztbtel);
			bundleb.putString("ztctel", ztctel);
			bundleb.putString("ztdtel", ztdtel);
			bundleb.putString("ztetel", ztetel);
			bundleb.putString("djddf", djddf);
			bundleb.putString("mytime", mytime);
			bundleb.putString("xzdsj", xzdsj);
			bundleb.putInt("bcztqcgs", bcztqcgs);
			bundleb.putInt("bczthgjg", bczthgjg);
			bundleb.putInt("bcztbswl", bcztbswl);
			bundleb.putInt("bcztdwb", bcztdwb);
			bundleb.putInt("bcztdcm", bcztdcm);
			bundleb.putInt("bcztscm", bcztscm);
			intenta.putExtras(bundleb);
			startActivity(intenta);
			break;
		case R.id.ztdddjrl:
			ycsj = ycatva.getText().toString().trim();
			ada = "hello";
			djddf = "ztddd";
			Addressposition.adb = "yato";
			xzdsj = ycatva.getText().toString().trim();
			strycatvb = ycatvb.getText().toString().trim();
			intenta = new Intent(Ycactivitya.this, Addressposition.class);
			bundleb = new Bundle();
			bundleb.putString("eyyq", eyyq);
			bundleb.putString("strycatvb", strycatvb);
			bundleb.putString("ztdalat", ztdalat);
			bundleb.putString("ztdalon", ztdalon);
			bundleb.putString("ztdblat", ztdblat);
			bundleb.putString("ztdblon", ztdblon);
			bundleb.putString("ztdclat", ztdclat);
			bundleb.putString("ztdclon", ztdclon);
			bundleb.putString("ztddlat", ztddlat);
			bundleb.putString("ztddlon", ztddlon);
			bundleb.putString("ztdelat", ztdelat);
			bundleb.putString("ztdelon", ztdelon);
			bundleb.putString("zhlat", zhlat);
			bundleb.putString("zhlon", zhlon);
			bundleb.putString("xhlat", xhlat);
			bundleb.putString("xhlon", xhlon);
			bundleb.putInt("ztdsl", ztdsl);
			bundleb.putString("zhdd", zhdd);
			bundleb.putString("zhname", zhname);
			bundleb.putString("zhtel", zhtel);
			bundleb.putString("xhdd", xhdd);
			bundleb.putString("xhname", xhname);
			bundleb.putString("xhtel", xhtel);
			bundleb.putString("ztadd", ztadd);
			bundleb.putString("ztbdd", ztbdd);
			bundleb.putString("ztcdd", ztcdd);
			bundleb.putString("ztddd", ztddd);
			bundleb.putString("ztedd", ztedd);
			bundleb.putString("ztaname", ztaname);
			bundleb.putString("ztbname", ztbname);
			bundleb.putString("ztcname", ztcname);
			bundleb.putString("ztdname", ztdname);
			bundleb.putString("ztename", ztename);
			bundleb.putString("ztatel", ztatel);
			bundleb.putString("ztbtel", ztbtel);
			bundleb.putString("ztctel", ztctel);
			bundleb.putString("ztdtel", ztdtel);
			bundleb.putString("ztetel", ztetel);
			bundleb.putString("djddf", djddf);
			bundleb.putString("mytime", mytime);
			bundleb.putString("xzdsj", xzdsj);
			bundleb.putInt("bcztqcgs", bcztqcgs);
			bundleb.putInt("bczthgjg", bczthgjg);
			bundleb.putInt("bcztbswl", bcztbswl);
			bundleb.putInt("bcztdwb", bcztdwb);
			bundleb.putInt("bcztdcm", bcztdcm);
			bundleb.putInt("bcztscm", bcztscm);
			intenta.putExtras(bundleb);
			startActivity(intenta);
			break;
		case R.id.ztdedjrl:
			ycsj = ycatva.getText().toString().trim();
			ada = "hello";
			djddf = "ztdde";
			Addressposition.adb = "yato";
			xzdsj = ycatva.getText().toString().trim();
			strycatvb = ycatvb.getText().toString().trim();
			intenta = new Intent(Ycactivitya.this, Addressposition.class);
			bundleb = new Bundle();
			bundleb.putString("eyyq", eyyq);
			bundleb.putString("strycatvb", strycatvb);
			bundleb.putString("ztdalat", ztdalat);
			bundleb.putString("ztdalon", ztdalon);
			bundleb.putString("ztdblat", ztdblat);
			bundleb.putString("ztdblon", ztdblon);
			bundleb.putInt("ztdsl", ztdsl);
			bundleb.putString("ztdclat", ztdclat);
			bundleb.putString("ztdclon", ztdclon);
			bundleb.putString("ztddlat", ztddlat);
			bundleb.putString("ztddlon", ztddlon);
			bundleb.putString("ztdelat", ztdelat);
			bundleb.putString("ztdelon", ztdelon);
			bundleb.putString("zhlat", zhlat);
			bundleb.putString("zhlon", zhlon);
			bundleb.putString("xhlat", xhlat);
			bundleb.putString("xhlon", xhlon);
			bundleb.putString("zhdd", zhdd);
			bundleb.putString("zhname", zhname);
			bundleb.putString("zhtel", zhtel);
			bundleb.putString("xhdd", xhdd);
			bundleb.putString("xhname", xhname);
			bundleb.putString("xhtel", xhtel);
			bundleb.putString("ztadd", ztadd);
			bundleb.putString("ztbdd", ztbdd);
			bundleb.putString("ztcdd", ztcdd);
			bundleb.putString("ztddd", ztddd);
			bundleb.putString("ztedd", ztedd);
			bundleb.putString("ztaname", ztaname);
			bundleb.putString("ztbname", ztbname);
			bundleb.putString("ztcname", ztcname);
			bundleb.putString("ztdname", ztdname);
			bundleb.putString("ztename", ztename);
			bundleb.putString("ztatel", ztatel);
			bundleb.putString("ztbtel", ztbtel);
			bundleb.putString("ztctel", ztctel);
			bundleb.putString("ztdtel", ztdtel);
			bundleb.putString("ztetel", ztetel);
			bundleb.putString("djddf", djddf);
			bundleb.putString("mytime", mytime);
			bundleb.putString("xzdsj", xzdsj);
			bundleb.putInt("bcztqcgs", bcztqcgs);
			bundleb.putInt("bczthgjg", bczthgjg);
			bundleb.putInt("bcztbswl", bcztbswl);
			bundleb.putInt("bcztdwb", bcztdwb);
			bundleb.putInt("bcztdcm", bcztdcm);
			bundleb.putInt("bcztscm", bcztscm);
			intenta.putExtras(bundleb);
			startActivity(intenta);
			break;
		case R.id.zjztdianimbtn:
			if (btnazt == 0) {
				ztda = 1;
				ztdsl = 1;
				ycaddrla.setVisibility(View.VISIBLE);
				btnazt = 1;
				i = 1;
			} else if (btnbzt == 0) {
				ztdb = 1;
				ztdsl = 2;
				ycaddrlb.setVisibility(View.VISIBLE);
				btnbzt = 1;
				i = 2;
			} else if (btnczt == 0) {
				ztdc = 1;
				ztdsl = 3;
				ycaddrlc.setVisibility(View.VISIBLE);
				btnczt = 1;
				i = 3;
			} else if (btndzt == 0) {
				ztdd = 1;
				ztdsl = 4;
				ycaddrld.setVisibility(View.VISIBLE);
				btndzt = 1;
				i = 4;
			} else if (btnezt == 0) {
				ztde = 1;
				ztdsl = 5;
				ycaddrle.setVisibility(View.VISIBLE);
				zjztdianimbtn.setVisibility(View.INVISIBLE);
				btnezt = 1;
				i = 5;
			}
			break;
		case R.id.delimbtna:

			delete();
			if (ztdb == 0) {
				ztadd = "null";
				ztaname = "null";
				ztatel = "null";
				ztdalat = "null";
				ztdalon = "null";
				addztdlxrtela.setText("");
				addztdlxrnamea.setText("");
				ztaddressa.setText(getResources().getString(R.string.ztd));
				ztaddressa.setTextColor(Color.parseColor("#AEAEAE"));
			} else {
				if (ztdc == 0) {
					ztdb = 0;
					db();
					sa();
					sbz();
				} else {
					if (ztdd == 0) {
						ztdc = 0;
						dc();
						db();
						sa();
						sb();
						sc();
						scz();
					} else {
						if (ztde == 0) {
							ztdd = 0;
							dd();
							dc();
							db();
							sa();
							sb();
							sc();
							sdz();
						} else {
							ztde = 0;
							de();
							dd();
							dc();
							db();
							sa();
							sb();
							sc();
							sd();
							se();
						}
					}
				}
			}
			qa();
			ya();
			yb();
			yc();
			if (ztaddressa.getText().toString()
					.equals(getResources().getString(R.string.ztd))) {
				ztaddressa.setTextColor(Color.parseColor("#AEAEAE"));
			} else {
				ztaddressa.setTextColor(Color.BLACK);
			}
			if (!zhlat.equals("null") && !xhlat.equals("null")) {
				// pdsfjs();
			}
			break;
		case R.id.delimbtnb:
			delete();
			qa();
			if (ztdc == 0) {
				ztdb = 0;
				sbz();
			} else {
				if (ztde == 0) {
					if (ztdd == 0) {
						ztdc = 0;
						dc();
						sb();
						scz();
					} else {
						ztdd = 0;
						dd();
						dc();
						sb();
						sc();
						sdz();
					}
				} else {
					ztde = 0;
					de();
					dd();
					dc();
					sb();
					sc();
					sd();
					se();
				}
			}
			ya();
			yb();
			yc();
			if (!zhlat.equals("null") && !xhlat.equals("null")) {
				// pdsfjs();
			}
			break;
		case R.id.delimbtnc:

			delete();
			qa();
			if (ztdd == 0) {

				ztdc = 0;
				scz();
			} else {
				if (ztde == 0) {
					ztdd = 0;
					dd();
					sc();
					sdz();
				} else {

					ztde = 0;
					de();
					dd();
					sc();
					sd();
					se();
				}

			}
			ya();
			yb();
			if (!zhlat.equals("null") && !xhlat.equals("null")) {
				// pdsfjs();
			}
			break;
		case R.id.delimbtnd:
			delete();
			qa();
			if (ztde == 0) {
				ztdd = 0;
				sdz();
			} else {
				ztde = 0;
				de();
				sd();
				se();
			}
			ya();
			if (!zhlat.equals("null") && !xhlat.equals("null")) {
				// pdsfjs();
			}
			break;
		case R.id.delimbtne:
			delete();
			se();
			i = 4;
			ztde = 0;
			if (!zhlat.equals("null") && !xhlat.equals("null")) {
				// pdsfjs();
			}
			break;
		case R.id.ycimbtna:
			ada = "hello";
			Ycactivitya.this.finish();
			break;
		case R.id.ycarlb:
			Mydialoga mydialoga = new Mydialoga(Ycactivitya.this, bcztqcgs,
					bczthgjg, bcztbswl, bcztdwb, bcztdcm, bcztscm);
			mydialoga.setDialogCallback(mydialogadissmiss);
			mydialoga.show();
			break;
		case R.id.ycalla:
			if (msychsyuyc == 1) {
				fwqsjjd();
			}
			break;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void fwqsjjd() {
		HashMap mapa = new HashMap<String, String>();
		mapa.put("key", "get_server_time");
		String jiamia = ToolUtils.JiaMi(mapa);
		String urla = Net.SEVERTIME + jiamia;
		showProgressDialog(getResources().getString(R.string.jzz));
		application.doget(urla, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				dismissProgressDialog();
				showToast(getResources().getString(R.string.wlyc));
			}

			@Override
			public void onSuccess(final ResponseInfo<String> arg0) {
				dismissProgressDialog();
				String systemyear = arg0.result.split("\\ ")[0].split("\\-")[0];
				String systemmonth = arg0.result.split("\\ ")[0].split("\\-")[1];
				String systemday = arg0.result.split("\\ ")[0].split("\\-")[2];
				String systemhour = arg0.result.split("\\ ")[1].split("\\:")[0];
				String systemsecond = arg0.result.split("\\ ")[1].split("\\:")[1];
				String systemtime = systemyear + systemmonth + systemday
						+ systemhour;
				newsdfyear = new SimpleDateFormat("yyyy");
				newsdfmonth = new SimpleDateFormat("MM");
				newsdfday = new SimpleDateFormat("dd");
				newsdfhour = new SimpleDateFormat("HH");
				newsdfminute = new SimpleDateFormat("mm");
				newdate = new Date(System.currentTimeMillis());
				strnewsdfyear = newsdfyear.format(newdate);
				strnewsdfmonth = newsdfmonth.format(newdate);
				strnewsdfday = newsdfday.format(newdate);
				strnewsdfhour = newsdfhour.format(newdate);
				strnewsdfminute = newsdfminute.format(newdate);
				strnewsyatemtime = strnewsdfyear + strnewsdfmonth
						+ strnewsdfday + strnewsdfhour;
				try {
					if (systemtime.equals(strnewsyatemtime)) {
						int intsystemsecond = Integer.valueOf(systemsecond);
						int intstrnewsdfminute = Integer
								.valueOf(strnewsdfminute);
						int sjnsc = Math.abs(intstrnewsdfminute
								- intsystemsecond);
						if (sjnsc > 5) {
							showToast("亲，您的手机时间不正确，请设置好时间！");
						} else {
							tdsjxz();
						}
					} else {
						showToast("亲，您的手机时间不正确，请设置好时间！");
					}
				} catch (Exception e) {
					tdsjxz();
				}
			}
		});
	}

	private void tdsjxz() {
		Mydialogb mydialogb = new Mydialogb(Ycactivitya.this, strxz, stry,
				strr, strzy, strze, strzs, strzsi, strzw, strzl, strzr);
		mydialogb.setDialogCallbackb(mydialogbdissmiss);
		mydialogb.show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (ycatva.getText().toString().trim()
				.equals(getResources().getString(R.string.qxzycsj))) {
			ycatva.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ycatva.setTextColor(Color.parseColor("#EC494A"));
		}
		if (zhaddress.getText().toString().trim()
				.equals(getResources().getString(R.string.cnlzh))) {
			zhaddress.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			zhaddress.setTextColor(Color.BLACK);
		}
		if (xhaddress.getText().toString().trim()
				.equals(getResources().getString(R.string.dnlxh))) {
			xhaddress.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			xhaddress.setTextColor(Color.BLACK);
		}
		yy();
		// dk(ztdsl);
		MobclickAgent.onResume(this);
	}

	private void zx() {
		if (ada.equals("wdlx")) {
			bundlea = getIntent().getExtras();
			mytime = bundlea.getString("mytime");
			xzdsj = bundlea.getString("xzdsj");
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
			bcztqcgs = bundlea.getInt("bcztqcgs");
			bczthgjg = bundlea.getInt("bczthgjg");
			bcztbswl = bundlea.getInt("bcztbswl");
			bcztdwb = bundlea.getInt("bcztdwb");
			bcztdcm = bundlea.getInt("bcztdcm");
			bcztscm = bundlea.getInt("bcztscm");
			ztdsl = bundlea.getInt("ztdsl");
			eyyq = bundlea.getString("eyyq");
			strycatvb = bundlea.getString("strycatvb");
			ycatvb.setText(strycatvb);
			if (!ztadd.trim().equals("null")) {
				ztaddressa.setText(ztadd);
				if (!ztaname.trim().equals("null")) {
					addztdlxrnamea.setText(ztaname);
				}
				if (!ztatel.trim().equals("null")) {
					addztdlxrtela.setText(ztatel);
				}
			}
			if (!ztbdd.trim().equals("null")) {
				ztaddressb.setText(ztbdd);
				if (!ztbname.trim().equals("null")) {
					addztdlxrnameb.setText(ztbname);
				}
				if (!ztbtel.trim().equals("null")) {
					addztdlxrtelb.setText(ztbtel);
				}
			}
			if (!ztcdd.trim().equals("null")) {
				ztaddressc.setText(ztcdd);
				if (!ztcname.trim().equals("null")) {
					addztdlxrnamec.setText(ztcname);
				}
				if (!ztctel.trim().equals("null")) {
					addztdlxrtelc.setText(ztctel);
				}
			}
			if (!ztddd.trim().equals("null")) {
				ztaddressd.setText(ztddd);
				if (!ztdname.trim().equals("null")) {
					addztdlxrnamed.setText(ztdname);
				}
				if (!ztdtel.trim().equals("null")) {
					addztdlxrteld.setText(ztdtel);
				}
			}
			if (!ztedd.trim().equals("null")) {
				ztaddresse.setText(ztedd);
				if (!ztename.trim().equals("null")) {
					addztdlxrnamee.setText(ztename);
				}
				if (!ztetel.trim().equals("null")) {
					addztdlxrtele.setText(ztetel);
				}
			}
			if (!zhdd.trim().equals("null")) {
				zhaddress.setText(zhdd);
				if (!zhname.trim().equals("null")) {
					zhlxrname.setText(zhname);
				}
				if (!zhtel.trim().equals("null")) {
					zhlxrtel.setText(zhtel);
				}
			}
			if (!xhdd.trim().equals("null")) {
				xhaddress.setText(xhdd);
				if (!xhname.trim().equals("null")) {
					xhlxrname.setText(xhname);
				}
				if (!xhtel.trim().equals("null")) {
					xhlxrdh.setText(xhtel);
				}
			}
			ycatva.setText(xzdsj);
			ycatva.setTextColor(Color.parseColor("#EC494A"));
			dk(ztdsl);
			yy();
			if (!zhlat.equals("null") && !xhlat.equals("null")) {
				// pdsfjs();
			}
		}
		if (ada.equals("addto")) {
			bundlea = getIntent().getExtras();
			newaddress = bundlea.getString("newaddress");
			newtel = bundlea.getString("newtel");
			newlat = bundlea.getString("newlat");
			newname = bundlea.getString("newname");
			newlon = bundlea.getString("newlon");
			djddf = bundlea.getString("djddf");
			mytime = bundlea.getString("mytime");
			xzdsj = bundlea.getString("xzdsj");
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
			bcztqcgs = bundlea.getInt("bcztqcgs");
			bczthgjg = bundlea.getInt("bczthgjg");
			bcztbswl = bundlea.getInt("bcztbswl");
			bcztdwb = bundlea.getInt("bcztdwb");
			bcztdcm = bundlea.getInt("bcztdcm");
			bcztscm = bundlea.getInt("bcztscm");
			ztdsl = bundlea.getInt("ztdsl");
			eyyq = bundlea.getString("eyyq");
			strycatvb = bundlea.getString("strycatvb");
			ycatvb.setText(strycatvb);
			if (!ztadd.trim().equals("null")) {
				ztaddressa.setText(ztadd);
				if (!ztaname.trim().equals("null")) {
					addztdlxrnamea.setText(ztaname);
				}
				if (!ztatel.trim().equals("null")) {
					addztdlxrtela.setText(ztatel);
				}
			}
			if (!ztbdd.trim().equals("null")) {
				ztaddressb.setText(ztbdd);
				if (!ztbname.trim().equals("null")) {
					addztdlxrnameb.setText(ztbname);
				}
				if (!ztbtel.trim().equals("null")) {
					addztdlxrtelb.setText(ztbtel);
				}
			}
			if (!ztcdd.trim().equals("null")) {
				ztaddressc.setText(ztcdd);
				if (!ztcname.trim().equals("null")) {
					addztdlxrnamec.setText(ztcname);
				}
				if (!ztctel.trim().equals("null")) {
					addztdlxrtelc.setText(ztctel);
				}
			}
			if (!ztddd.trim().equals("null")) {
				ztaddressd.setText(ztddd);
				if (!ztdname.trim().equals("null")) {
					addztdlxrnamed.setText(ztdname);
				}
				if (!ztdtel.trim().equals("null")) {
					addztdlxrteld.setText(ztdtel);
				}
			}
			if (!ztedd.trim().equals("null")) {
				ztaddresse.setText(ztedd);
				if (!ztename.trim().equals("null")) {
					addztdlxrnamee.setText(ztename);
				}
				if (!ztetel.trim().equals("null")) {
					addztdlxrtele.setText(ztetel);
				}
			}
			if (!zhdd.trim().equals("null")) {
				zhaddress.setText(zhdd);
				if (!zhname.trim().equals("null")) {
					zhlxrname.setText(zhname);
				}
				if (!zhtel.trim().equals("null")) {
					zhlxrtel.setText(zhtel);
				}
			}
			if (!xhdd.trim().equals("null")) {
				xhaddress.setText(xhdd);
				if (!xhname.trim().equals("null")) {
					xhlxrname.setText(xhname);
				}
				if (!xhtel.trim().equals("null")) {
					xhlxrdh.setText(xhtel);
				}
			}
			if (djddf.equals("zhdd")) {
				zhdd = newaddress;
				zhname = newname;
				zhtel = newtel;
				zhaddress.setText(newaddress);
				zhlxrname.setText(newname);
				zhlxrtel.setText(newtel);
				zhlat = newlat;
				zhlon = newlon;
			} else if (djddf.equals("xhdd")) {
				xhdd = newaddress;
				xhname = newname;
				xhtel = newtel;
				xhaddress.setText(newaddress);
				xhlxrname.setText(newname);
				xhlxrdh.setText(newtel);
				xhlat = newlat;
				xhlon = newlon;
			} else if (djddf.equals("ztdda")) {
				ztadd = newaddress;
				ztaname = newname;
				ztatel = newtel;
				ztaddressa.setText(newaddress);
				addztdlxrnamea.setText(newname);
				addztdlxrtela.setText(newtel);
				ztdalat = newlat;
				ztdalon = newlon;
			} else if (djddf.equals("ztddb")) {
				ztbdd = newaddress;
				ztbname = newname;
				ztbtel = newtel;
				ztaddressb.setText(newaddress);
				addztdlxrnameb.setText(newname);
				addztdlxrtelb.setText(newtel);
				ztdblat = newlat;
				ztdblon = newlon;
			} else if (djddf.equals("ztddc")) {
				ztcdd = newaddress;
				ztcname = newname;
				ztctel = newtel;
				ztaddressc.setText(newaddress);
				addztdlxrnamec.setText(newname);
				addztdlxrtelc.setText(newtel);
				ztdclat = newlat;
				ztdclon = newlon;
			} else if (djddf.equals("ztddd")) {
				ztddd = newaddress;
				ztdname = newname;
				ztdtel = newtel;
				ztaddressd.setText(newaddress);
				addztdlxrnamed.setText(newname);
				addztdlxrteld.setText(newtel);
				ztddlat = newlat;
				ztddlon = newlon;
			} else if (djddf.equals("ztdde")) {
				ztedd = newaddress;
				ztename = newname;
				ztetel = newtel;
				ztaddresse.setText(newaddress);
				addztdlxrnamee.setText(newname);
				addztdlxrtele.setText(newtel);
				ztdelat = newlat;
				ztdelon = newlon;
			}
			ycatva.setText(xzdsj);
			ycatva.setTextColor(Color.parseColor("#EC494A"));
			dk(ztdsl);
			yy();
			if (!zhlat.equals("null") && !xhlat.equals("null")) {
				// pdsfjs();
			}
		}
		zhadd = zhaddress.getText().toString().trim();
		if (zhadd.equals(getResources().getString(R.string.cnlzh))) {
			dw();
		}
	}

	private void dk(int a) {
		if (a == 1) {
			ztda = 1;
			ztdsl = 1;
			ycaddrla.setVisibility(View.VISIBLE);
			btnazt = 1;
			i = 1;
		} else if (a == 2) {
			ztda = 1;
			ztdb = 1;
			ztdsl = 2;
			ycaddrla.setVisibility(View.VISIBLE);
			ycaddrlb.setVisibility(View.VISIBLE);
			btnbzt = 1;
			btnazt = 1;
			i = 2;
		} else if (a == 3) {
			ztda = 1;
			ztdb = 1;
			ztdc = 1;
			ztdsl = 3;
			ycaddrla.setVisibility(View.VISIBLE);
			ycaddrlb.setVisibility(View.VISIBLE);
			ycaddrlc.setVisibility(View.VISIBLE);
			btnczt = 1;
			btnbzt = 1;
			btnazt = 1;
			i = 3;
		} else if (a == 4) {
			ztda = 1;
			ztdb = 1;
			ztdc = 1;
			ztdd = 1;
			ztdsl = 4;
			ycaddrla.setVisibility(View.VISIBLE);
			ycaddrlb.setVisibility(View.VISIBLE);
			ycaddrlc.setVisibility(View.VISIBLE);
			ycaddrld.setVisibility(View.VISIBLE);
			btndzt = 1;
			btnczt = 1;
			btnbzt = 1;
			btnazt = 1;
			i = 4;
		} else if (a == 5) {
			ztda = 1;
			ztdb = 1;
			ztdc = 1;
			ztdd = 1;
			ztde = 1;
			ztdsl = 5;
			ycaddrla.setVisibility(View.VISIBLE);
			ycaddrlb.setVisibility(View.VISIBLE);
			ycaddrlc.setVisibility(View.VISIBLE);
			ycaddrld.setVisibility(View.VISIBLE);
			ycaddrle.setVisibility(View.VISIBLE);
			zjztdianimbtn.setVisibility(View.INVISIBLE);
			btnezt = 1;
			btndzt = 1;
			btnczt = 1;
			btnbzt = 1;
			btnazt = 1;
			i = 5;
		}
	}

	private void qa() {
		if (i == 5) {
			i = 4;
		} else if (i == 4) {
			i = 3;
		} else if (i == 3) {
			i = 2;
		} else if (i == 2) {
			i = 1;
		} else if (i == 1) {
			i = 0;
		}
	}

	private void yy() {
		if (ztaddressa.getText().toString()
				.equals(getResources().getString(R.string.ztd))) {
			ztaddressa.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ztaddressa.setTextColor(Color.BLACK);
		}
		if (ztaddressb.getText().toString()
				.equals(getResources().getString(R.string.ztd))) {
			ztaddressb.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ztaddressb.setTextColor(Color.BLACK);
		}
		if (ztaddressc.getText().toString()
				.equals(getResources().getString(R.string.ztd))) {
			ztaddressc.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ztaddressc.setTextColor(Color.BLACK);
		}
		if (ztaddressd.getText().toString()
				.equals(getResources().getString(R.string.ztd))) {
			ztaddressd.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ztaddressd.setTextColor(Color.BLACK);
		}
		if (ztaddresse.getText().toString()
				.equals(getResources().getString(R.string.ztd))) {
			ztaddresse.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ztaddresse.setTextColor(Color.BLACK);
		}
		if (ycatva.getText().toString().trim()
				.equals(getResources().getString(R.string.qxzycsj))) {
			ycatva.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ycatva.setTextColor(Color.parseColor("#EC494A"));
		}
		if (zhaddress.getText().toString().trim()
				.equals(getResources().getString(R.string.cnlzh))) {
			zhaddress.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			zhaddress.setTextColor(Color.BLACK);
		}
		if (xhaddress.getText().toString().trim()
				.equals(getResources().getString(R.string.dnlxh))) {
			xhaddress.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			xhaddress.setTextColor(Color.BLACK);
		}
	}

	private void yc() {
		if (ztaddressb.getText().toString()
				.equals(getResources().getString(R.string.ztd))) {
			ztaddressb.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ztaddressb.setTextColor(Color.BLACK);
		}
	}

	private void yb() {
		if (ztaddressc.getText().toString()
				.equals(getResources().getString(R.string.ztd))) {
			ztaddressc.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ztaddressc.setTextColor(Color.BLACK);
		}
	}

	private void ya() {
		if (ztaddressd.getText().toString()
				.equals(getResources().getString(R.string.ztd))) {
			ztaddressd.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ztaddressd.setTextColor(Color.BLACK);
		}
		if (ztaddresse.getText().toString()
				.equals(getResources().getString(R.string.ztd))) {
			ztaddresse.setTextColor(Color.parseColor("#AEAEAE"));
		} else {
			ztaddresse.setTextColor(Color.BLACK);
		}
	}

	private void sbz() {
		ztbdd = "null";
		ztbname = "null";
		ztbtel = "null";
		ztdblat = "null";
		ztdblon = "null";
		addztdlxrtelb.setText("");
		addztdlxrnameb.setText("");
		ztaddressb.setText(getResources().getString(R.string.ztd));
		ztaddressb.setTextColor(Color.parseColor("#AEAEAE"));
	}

	private void scz() {
		ztcdd = "null";
		ztcname = "null";
		ztctel = "null";
		ztdclat = "null";
		ztdclon = "null";
		addztdlxrtelc.setText("");
		addztdlxrnamec.setText("");
		ztaddressc.setText(getResources().getString(R.string.ztd));
		ztaddressc.setTextColor(Color.parseColor("#AEAEAE"));
	}

	private void sdz() {
		ztddd = "null";
		ztdname = "null";
		ztdtel = "null";
		ztddlat = "null";
		ztddlon = "null";
		addztdlxrteld.setText("");
		addztdlxrnamed.setText("");
		ztaddressd.setText(getResources().getString(R.string.ztd));
		ztaddressd.setTextColor(Color.parseColor("#AEAEAE"));
	}

	private void sa() {
		ztadd = strztbdd;
		ztaname = strztbname;
		ztatel = strztbtel;
		ztdalat = strztdblat;
		ztdalon = strztdblon;
		addztdlxrtela.setText(addztdlxrtelbstr);
		addztdlxrnamea.setText(addztdlxrnamebstr);
		ztaddressa.setText(ztaddressbstr);
	}

	private void sb() {
		ztbdd = strztcdd;
		ztbname = strztcname;
		ztbtel = strztctel;
		ztdblat = strztdclat;
		ztdblon = strztdclon;
		addztdlxrtelb.setText(addztdlxrtelcstr);
		addztdlxrnameb.setText(addztdlxrnamecstr);
		ztaddressb.setText(ztaddresscstr);
	}

	private void sc() {
		ztcdd = strztddd;
		ztcname = strztdname;
		ztctel = strztdtel;
		ztdclat = strztddlat;
		ztdclon = strztddlon;
		addztdlxrtelc.setText(addztdlxrteldstr);
		addztdlxrnamec.setText(addztdlxrnamedstr);
		ztaddressc.setText(ztaddressdstr);
	}

	private void sd() {
		ztddd = strztedd;
		ztdname = strztename;
		ztdtel = strztetel;
		ztddlat = strztdelat;
		ztddlon = strztdelon;
		addztdlxrteld.setText(addztdlxrtelestr);
		addztdlxrnamed.setText(addztdlxrnameestr);
		ztaddressd.setText(ztaddressestr);
	}

	private void se() {
		ztedd = "null";
		ztename = "null";
		ztetel = "null";
		ztdelat = "null";
		ztdelon = "null";
		addztdlxrtele.setText("");
		addztdlxrnamee.setText("");
		ztaddresse.setText(getResources().getString(R.string.ztd));
		ztaddresse.setTextColor(Color.parseColor("#AEAEAE"));
	}

	private void db() {
		strztbdd = ztbdd;
		strztbname = ztbname;
		strztbtel = ztbtel;
		strztdblat = ztdblat;
		strztdblon = ztdblon;
		addztdlxrtelbstr = addztdlxrtelb.getText().toString();
		addztdlxrnamebstr = addztdlxrnameb.getText().toString();
		ztaddressbstr = ztaddressb.getText().toString();
	}

	private void dc() {
		strztcdd = ztcdd;
		strztcname = ztcname;
		strztctel = ztctel;
		strztdclat = ztdclat;
		strztdclon = ztdclon;
		addztdlxrtelcstr = addztdlxrtelc.getText().toString();
		addztdlxrnamecstr = addztdlxrnamec.getText().toString();
		ztaddresscstr = ztaddressc.getText().toString();
	}

	private void dd() {
		strztddd = ztddd;
		strztdname = ztdname;
		strztdtel = ztdtel;
		strztddlat = ztddlat;
		strztddlon = ztddlon;
		addztdlxrteldstr = addztdlxrteld.getText().toString();
		addztdlxrnamedstr = addztdlxrnamed.getText().toString();
		ztaddressdstr = ztaddressd.getText().toString();
	}

	private void de() {

		strztedd = ztedd;
		strztename = ztename;
		strztetel = ztetel;
		strztdelat = ztdelat;
		strztdelon = ztdelon;
		addztdlxrtelestr = addztdlxrtele.getText().toString();
		addztdlxrnameestr = addztdlxrnamee.getText().toString();
		ztaddressestr = ztaddresse.getText().toString();
	}

	private void delete() {
		if (btnezt == 1) {
			ztdsl = 4;
			ycaddrle.setVisibility(View.GONE);
			btnezt = 0;
		} else if (btndzt == 1) {
			ztdsl = 3;
			ycaddrld.setVisibility(View.GONE);
			btndzt = 0;
		} else if (btnczt == 1) {
			ztdsl = 2;
			ycaddrlc.setVisibility(View.GONE);
			btnczt = 0;
		} else if (btnbzt == 1) {
			ztdsl = 1;
			ycaddrlb.setVisibility(View.GONE);
			btnbzt = 0;
		} else if (btnazt == 1) {
			ztdsl = 0;
			ycaddrla.setVisibility(View.GONE);
			btnazt = 0;
		}
		zjztdianimbtn.setVisibility(View.VISIBLE);
	}

	@SuppressWarnings("rawtypes")
	private void cshid() {
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		// sharedPreferencesa = getSharedPreferences("dinwei_xinxi",
		// Activity.MODE_PRIVATE);
		// address = sharedPreferencesa.getString("address", "");
		// lat = sharedPreferencesa.getString("lat", "");
		// lon = sharedPreferencesa.getString("lng", "");
		sharedPreferencesb = getSharedPreferences("user_xinxi",
				Activity.MODE_PRIVATE);
		nickname = sharedPreferencesb.getString("nc", "");
		// sharedPreferencesc = getSharedPreferences("ccjghqbjg",
		// Activity.MODE_PRIVATE);
		// dqbjgxa = sharedPreferencesc.getString("dqbjgxa", "");
		// dqbjgxb = sharedPreferencesc.getString("dqbjgxb", "");
		// dqbjgxc = sharedPreferencesc.getString("dqbjgxc", "");
		// dqbjgxd = sharedPreferencesc.getString("dqbjgxd", "");
		// dqbjgxe = sharedPreferencesc.getString("dqbjgxe", "");
		// dqbjgxf = sharedPreferencesc.getString("dqbjgxf", "");
		// dqbjgxg = sharedPreferencesc.getString("dqbjgxg", "");
		// dqbjgxh = sharedPreferencesc.getString("dqbjgxh", "");
		// dqbjgxj = sharedPreferencesc.getString("dqbjgxj", "");
		// dqbjgxk = sharedPreferencesc.getString("dqbjgxk", "");
		// dqbjgxl = sharedPreferencesc.getString("dqbjgxl", "");
		// dqbjgxi = sharedPreferencesc.getString("dqbjgxi", "");
		// dccjgxa = sharedPreferencesc.getString("dccjgxa", "");
		// dccjgxb = sharedPreferencesc.getString("dccjgxb", "");
		// dccjgxc = sharedPreferencesc.getString("dccjgxc", "");
		// dccjgxd = sharedPreferencesc.getString("dccjgxd", "");
		// dccjgxe = sharedPreferencesc.getString("dccjgxe", "");
		// dccjgxf = sharedPreferencesc.getString("dccjgxf", "");
		// dccjgxg = sharedPreferencesc.getString("dccjgxg", "");
		// dccjgxh = sharedPreferencesc.getString("dccjgxh", "");
		// dccjgxj = sharedPreferencesc.getString("dccjgxj", "");
		// dccjgxk = sharedPreferencesc.getString("dccjgxk", "");
		// dccjgxl = sharedPreferencesc.getString("dccjgxl", "");
		// dccjgxi = sharedPreferencesc.getString("dccjgxi", "");
		// dqbjgka = sharedPreferencesc.getString("dqbjgka", "");
		// dqbjgkb = sharedPreferencesc.getString("dqbjgkb", "");
		// dqbjgkc = sharedPreferencesc.getString("dqbjgkc", "");
		// dqbjgkd = sharedPreferencesc.getString("dqbjgkd", "");
		// dqbjgke = sharedPreferencesc.getString("dqbjgke", "");
		// dqbjgkf = sharedPreferencesc.getString("dqbjgkf", "");
		// dccjgka = sharedPreferencesc.getString("dccjgka", "");
		// dccjgkb = sharedPreferencesc.getString("dccjgkb", "");
		// dccjgkc = sharedPreferencesc.getString("dccjgkc", "");
		// dccjgkd = sharedPreferencesc.getString("dccjgkd", "");
		// dccjgke = sharedPreferencesc.getString("dccjgke", "");
		// dccjgkf = sharedPreferencesc.getString("dccjgkf", "");
		// dqbjgga = sharedPreferencesc.getString("dqbjgga", "");
		// dqbjggb = sharedPreferencesc.getString("dqbjggb", "");
		// dqbjggc = sharedPreferencesc.getString("dqbjggc", "");
		// dqbjggd = sharedPreferencesc.getString("dqbjggd", "");
		// dqbjgge = sharedPreferencesc.getString("dqbjgge", "");
		// dqbjggf = sharedPreferencesc.getString("dqbjggf", "");
		// dqbjggg = sharedPreferencesc.getString("dqbjggg", "");
		// dqbjggh = sharedPreferencesc.getString("dqbjggh", "");
		// dqbjggi = sharedPreferencesc.getString("dqbjggi", "");
		// dqbjggj = sharedPreferencesc.getString("dqbjggj", "");
		// dqbjggk = sharedPreferencesc.getString("dqbjggk", "");
		// dqbjggl = sharedPreferencesc.getString("dqbjggl", "");
		// dccjggg = sharedPreferencesc.getString("dccjggg", "");
		// dccjggh = sharedPreferencesc.getString("dccjggh", "");
		// dccjggi = sharedPreferencesc.getString("dccjggi", "");
		// dccjggj = sharedPreferencesc.getString("dccjggj", "");
		// dccjggk = sharedPreferencesc.getString("dccjggk", "");
		// dccjggl = sharedPreferencesc.getString("dccjggl", "");
		// dqbjgkg = sharedPreferencesc.getString("dqbjgkg", "");
		// dqbjgkh = sharedPreferencesc.getString("dqbjgkh", "");
		// dqbjgki = sharedPreferencesc.getString("dqbjgki", "");
		// dqbjgkj = sharedPreferencesc.getString("dqbjgkj", "");
		// dqbjgkk = sharedPreferencesc.getString("dqbjgkk", "");
		// dqbjgkl = sharedPreferencesc.getString("dqbjgkl", "");
		// dccjgkg = sharedPreferencesc.getString("dccjgkg", "");
		// dccjgkh = sharedPreferencesc.getString("dccjgkh", "");
		// dccjgki = sharedPreferencesc.getString("dccjgki", "");
		// dccjgkj = sharedPreferencesc.getString("dccjgkj", "");
		// dccjgkk = sharedPreferencesc.getString("dccjgkk", "");
		// dccjgkl = sharedPreferencesc.getString("dccjgkl", "");
		// dccjgga = sharedPreferencesc.getString("dccjgga", "");
		// dccjggb = sharedPreferencesc.getString("dccjggb", "");
		// dccjggc = sharedPreferencesc.getString("dccjggc", "");
		// dccjggd = sharedPreferencesc.getString("dccjggd", "");
		// dccjgge = sharedPreferencesc.getString("dccjgge", "");
		// dccjggf = sharedPreferencesc.getString("dccjggf", "");
		// dqbjgja = sharedPreferencesc.getString("dqbjgja", "");
		// dqbjgjb = sharedPreferencesc.getString("dqbjgjb", "");
		// dqbjgjc = sharedPreferencesc.getString("dqbjgjc", "");
		// dqbjgjd = sharedPreferencesc.getString("dqbjgjd", "");
		// dccjgja = sharedPreferencesc.getString("dccjgja", "");
		// dccjgjb = sharedPreferencesc.getString("dccjgjb", "");
		// dccjgjc = sharedPreferencesc.getString("dccjgjc", "");
		// dccjgjd = sharedPreferencesc.getString("dccjgjd", "");
		// zhlat = lat;
		// zhlon = lon;
		// zhdd = address;
		zhtel = phonea;
		zhname = nickname;
		imtv[0] = getResources().getString(R.string.xscar);
		imtv[1] = getResources().getString(R.string.xskdcar);
		imtv[2] = getResources().getString(R.string.glpbcar);
		imtv[3] = getResources().getString(R.string.jzxcar);
		zdzhlz = getResources().getString(R.string.firefang);
		zdzhlo = getResources().getString(R.string.thirteenfang);
		zdzhlt = getResources().getString(R.string.fourteenfang);
		zdzhlth = getResources().getString(R.string.twentyfivefang);
		zdzhlf = getResources().getString(R.string.thirtyfang);
		zdzhlfi = getResources().getString(R.string.thirtyonefang);
		zdzhlsix = getResources().getString(R.string.thirtysenverfang);
		zdzhlsenver = getResources().getString(R.string.thirtyninefang);
		zdzhleight = getResources().getString(R.string.fortyfivefang);
		zdzzlz = getResources().getString(R.string.onedun);
		zdzzlo = getResources().getString(R.string.threedun);
		zdzzlt = getResources().getString(R.string.firedun);
		zdzzlth = getResources().getString(R.string.eightdun);
		zdzzlf = getResources().getString(R.string.tendun);
		zdzzlfi = getResources().getString(R.string.fifteendun);
		qcmz = getResources().getString(R.string.xscar);
		qcmze = getResources().getString(R.string.xskdcar);
		qcmzf = getResources().getString(R.string.glpbcar);
		d = getResources().getString(R.string.d);
		l = getResources().getString(R.string.l);
		zdzhljzxa = getResources().getString(R.string.twentyeightfang);
		zdzhljzxb = getResources().getString(R.string.wslfang);
		zdzhljzxc = getResources().getString(R.string.lsbfang);
		zdzhljzxd = getResources().getString(R.string.qsbfang);
		zdzzla = getResources().getString(R.string.sqdwdun);
		zdzzlb = getResources().getString(R.string.esedun);
		zdzzlc = getResources().getString(R.string.esjdun);
		qcmzjzx = getResources().getString(R.string.jzxcar);
		strxz = getResources().getString(R.string.nxzdsjbfhyqqcxxz);
		stry = getResources().getString(R.string.y);
		strr = getResources().getString(R.string.r);
		strzy = getResources().getString(R.string.zy);
		strze = getResources().getString(R.string.ze);
		strzs = getResources().getString(R.string.zs);
		strzsi = getResources().getString(R.string.zsi);
		strzw = getResources().getString(R.string.zw);
		strzl = getResources().getString(R.string.zl);
		strzr = getResources().getString(R.string.zr);
		c = getResources().getString(R.string.c);
		qcsl = "0" + getResources().getString(R.string.l);
		gallerytva = (TextView) this.findViewById(R.id.ycagallerytva);
		gallerya = (Gallery) this.findViewById(R.id.ycagallerya);
		gallerya.setAdapter(adaptera);
		ycarlb = (RelativeLayout) findViewById(R.id.ycarlb);
		ycimbtna = (ImageButton) findViewById(R.id.ycimbtna);
		ycatva = (TextView) findViewById(R.id.ycatva);
		ycalla = (LinearLayout) findViewById(R.id.ycalla);
		ygfy = (TextView) findViewById(R.id.ygfy);
		ycarlb.setOnClickListener(this);
		ycalla.setOnClickListener(this);
		ycimbtna.setOnClickListener(this);
		gallerya.setOnItemSelectedListener(this);
		gallerya.setOnItemClickListener(this);
		ycbtnxyba = (Button) findViewById(R.id.ycbtnxyba);
		yfgztv = (TextView) findViewById(R.id.yfgztv);
		ygfyxs = (TextView) findViewById(R.id.ygfyxs);
		zhaddress = (TextView) findViewById(R.id.zhaddress);
		zhlxrname = (TextView) findViewById(R.id.zhlxrname);
		zhlxrtel = (TextView) findViewById(R.id.zhlxrtel);
		xhaddress = (TextView) findViewById(R.id.xhaddress);
		xhlxrname = (TextView) findViewById(R.id.xhlxrname);
		xhlxrdh = (TextView) findViewById(R.id.xhlxrdh);
		ycatvb = (TextView) findViewById(R.id.ycatvb);
		addztdlxrnamea = (TextView) findViewById(R.id.addztdlxrnamea);
		addztdlxrnameb = (TextView) findViewById(R.id.addztdlxrnameb);
		addztdlxrnamec = (TextView) findViewById(R.id.addztdlxrnamec);
		addztdlxrnamed = (TextView) findViewById(R.id.addztdlxrnamed);
		addztdlxrnamee = (TextView) findViewById(R.id.addztdlxrnamee);
		addztdlxrtela = (TextView) findViewById(R.id.addztdlxrtela);
		addztdlxrtelb = (TextView) findViewById(R.id.addztdlxrtelb);
		addztdlxrtelc = (TextView) findViewById(R.id.addztdlxrtelc);
		addztdlxrteld = (TextView) findViewById(R.id.addztdlxrteld);
		addztdlxrtele = (TextView) findViewById(R.id.addztdlxrtele);
		ztaddressa = (TextView) findViewById(R.id.ztaddressa);
		ztaddressb = (TextView) findViewById(R.id.ztaddressb);
		ztaddressc = (TextView) findViewById(R.id.ztaddressc);
		ztaddressd = (TextView) findViewById(R.id.ztaddressd);
		ztaddresse = (TextView) findViewById(R.id.ztaddresse);
		cyxzrl = (RelativeLayout) findViewById(R.id.cyxzrl);
		ycaddrla = (RelativeLayout) findViewById(R.id.ycaddrla);
		ycaddrlb = (RelativeLayout) findViewById(R.id.ycaddrlb);
		ycaddrlc = (RelativeLayout) findViewById(R.id.ycaddrlc);
		ycaddrld = (RelativeLayout) findViewById(R.id.ycaddrld);
		ycaddrle = (RelativeLayout) findViewById(R.id.ycaddrle);
		zjztdianimbtn = (ImageButton) findViewById(R.id.zjztdianimbtn);
		delimbtna = (ImageButton) findViewById(R.id.delimbtna);
		delimbtnb = (ImageButton) findViewById(R.id.delimbtnb);
		delimbtnc = (ImageButton) findViewById(R.id.delimbtnc);
		delimbtnd = (ImageButton) findViewById(R.id.delimbtnd);
		delimbtne = (ImageButton) findViewById(R.id.delimbtne);
		ztdfrl = (RelativeLayout) findViewById(R.id.ztdfrl);
		ztdarl = (RelativeLayout) findViewById(R.id.ztdarl);
		ztdadjrl = (RelativeLayout) findViewById(R.id.ztdadjrl);
		ztdbdjrl = (RelativeLayout) findViewById(R.id.ztdbdjrl);
		ztdcdjrl = (RelativeLayout) findViewById(R.id.ztdcdjrl);
		ztdddjrl = (RelativeLayout) findViewById(R.id.ztdddjrl);
		ztdedjrl = (RelativeLayout) findViewById(R.id.ztdedjrl);
		search = RoutePlanSearch.newInstance();
		search.setOnGetRoutePlanResultListener(new MyongetrouteplanresultListener());
		aya = new ArrayList();
		ayb = new ArrayList();
		ayc = new ArrayList();
		ycbtnxyba.setOnClickListener(this);
		ztdadjrl.setOnClickListener(this);
		ztdbdjrl.setOnClickListener(this);
		ztdcdjrl.setOnClickListener(this);
		ztdddjrl.setOnClickListener(this);
		ztdedjrl.setOnClickListener(this);
		zjztdianimbtn.setOnClickListener(this);
		delimbtna.setOnClickListener(this);
		delimbtnb.setOnClickListener(this);
		delimbtnc.setOnClickListener(this);
		delimbtnd.setOnClickListener(this);
		delimbtne.setOnClickListener(this);
		cyxzrl.setOnClickListener(this);
		yfgztv.setOnClickListener(this);
		ztdarl.setOnClickListener(this);
		ztdfrl.setOnClickListener(this);
		ycbtnxyba.setClickable(false);
		ycbtnxyba.setBackgroundResource(R.drawable.nextb);
		zhlxrname.setText(zhname);
		zhlxrtel.setText(zhtel);
		if (msychsyuyc == 0) {
			ycatva.setText(getResources().getString(R.string.xz));
			ycatva.setTextColor(Color.parseColor("#EC494A"));
		} else {
			ycatva.setText(getResources().getString(R.string.qxzycsj));
			ycatva.setTextColor(Color.parseColor("#AEAEAE"));
		}
		zx();
		yy();
	}

	private void dw() {
		loclient = new LocationClient(this);
		loclient.registerLocationListener(this);
		loclientoption = new LocationClientOption();
		loclientoption.setLocationMode(LocationMode.Hight_Accuracy);
		// loclientoption.setOpenGps(true);
		loclientoption.setCoorType("bd09ll");
		loclientoption.setAddrType("all");
		// loclientoption.setPriority(LocationClientOption.NetWorkFirst);
		loclientoption.setScanSpan(1000);
		loclientoption.disableCache(true);
		loclientoption.setIsNeedLocationPoiList(true);
		loclientoption.setIsNeedAddress(true);
		loclientoption.setNeedDeviceDirect(true);
		loclient.setLocOption(loclientoption);
		try {
			connectivitymanagera = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			networkinfoa = connectivitymanagera.getActiveNetworkInfo();
		} catch (Exception e) {
			connectivitymanagera = null;
			networkinfoa = null;
		}
		if (networkinfoa == null) {
			ntype = false;
		} else {
			ntype = connectivitymanagera.getActiveNetworkInfo().isAvailable();
		}
		if (ntype == true) {
			loclient.start();
		} else {
			showToast(getResources().getString(R.string.wlyc));
		}
		// zx();
	}

	@Override
	public void onReceiveLocation(BDLocation arg0) {
		if (arg0 == null) {
			return;
		}
		// String zhddaaaaa = arg0.getAddrStr();
		// showToast("启动定位");
		zhdd = null;
		zhdd = arg0.getAddrStr();
		zhlat = String.valueOf(arg0.getLatitude());
		zhlon = String.valueOf(arg0.getLongitude());
		loclient.requestLocation();
		loclient.stop();
		if (zhdd == null) {
			zhdd = "null";
			zhlat = "null";
			zhlon = "null";
			showToast(getResources().getString(R.string.wlyc));
			return;
		}
		zhaddress.setText(zhdd);
		yy();
		loclient.requestLocation();
		loclient.stop();
	}

	class MyongetrouteplanresultListener implements
			OnGetRoutePlanResultListener {
		@Override
		public void onGetDrivingRouteResult(DrivingRouteResult result) {
			if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
				showToast(getResources().getString(R.string.bqwzdjg));
			}
			if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
				return;
			}
			if (result.error == SearchResult.ERRORNO.NO_ERROR) {
				for (int i = 0; i < result.getRouteLines().size(); i++) {
					int juli = result.getRouteLines().get(0).getDistance();
					alljuli = juli;
				}
				alljulioo = ToolUtils.fenShuBaoErWeiXiaoShu(String
						.valueOf((alljuli / 1000.00)));
				tjygfy();
				ycbtnxyba.setClickable(true);
				ycbtnxyba.setBackgroundResource(R.drawable.next);
			}
		}

		@Override
		public void onGetTransitRouteResult(TransitRouteResult result) {
		}

		@Override
		public void onGetWalkingRouteResult(WalkingRouteResult result) {
		}

		@Override
		public void onGetBikingRouteResult(BikingRouteResult arg0) {
		}
	}

	private void zcpd() {
		if (ztdsl == 5) {
			if (ztdaddress.equals(getResources().getString(R.string.ztd))
					|| ztdbddress
							.equals(getResources().getString(R.string.ztd))
					|| ztdcddress
							.equals(getResources().getString(R.string.ztd))
					|| ztddddress
							.equals(getResources().getString(R.string.ztd))
					|| ztdeddress
							.equals(getResources().getString(R.string.ztd))) {
				showToast(getResources().getString(R.string.qtxztd));
			} else {
				tz();
			}
		}
		if (ztdsl == 4) {
			if (ztdaddress.equals(getResources().getString(R.string.ztd))
					|| ztdbddress
							.equals(getResources().getString(R.string.ztd))
					|| ztdcddress
							.equals(getResources().getString(R.string.ztd))
					|| ztddddress
							.equals(getResources().getString(R.string.ztd))) {
				showToast(getResources().getString(R.string.qtxztd));
			} else {
				tz();
			}
		}
		if (ztdsl == 3) {
			if (ztdaddress.equals(getResources().getString(R.string.ztd))
					|| ztdbddress
							.equals(getResources().getString(R.string.ztd))
					|| ztdcddress
							.equals(getResources().getString(R.string.ztd))) {
				showToast(getResources().getString(R.string.qtxztd));
			} else {
				tz();
			}
		}
		if (ztdsl == 2) {
			if (ztdaddress.equals(getResources().getString(R.string.ztd))
					|| ztdbddress
							.equals(getResources().getString(R.string.ztd))) {
				showToast(getResources().getString(R.string.qtxztd));
			} else {
				tz();
			}
		}
		if (ztdsl == 1) {
			if (ztdaddress.equals(getResources().getString(R.string.ztd))) {
				showToast(getResources().getString(R.string.qtxztd));
			} else {
				tz();
			}
		}
		if (ztdsl == 0) {
			tz();
		}

	}

	private void tz() {
		ada = "hello";
		Ycactivityb.adb = "adb";
		intenta = new Intent(Ycactivitya.this, Ycactivityb.class);
		bundleb = new Bundle();
		bundleb.putString("mytime", mytime);
		bundleb.putString("xzdsj", xzdsj);
		bundleb.putString("eyyq", eyyq);
		bundleb.putString("qcmc", qcmc);
		bundleb.putString("qcdw", qcdw);
		bundleb.putString("qcgg", qcgg);
		bundleb.putString("qcsl", qcsl);
		bundleb.putInt("carid", carid);
		bundleb.putString("alljulioo", String.valueOf(gls));
		bundleb.putString("qczhl", qczhl);
		bundleb.putString("ztdalat", ztdalat);
		bundleb.putString("ztdalon", ztdalon);
		bundleb.putString("ztdblat", ztdblat);
		bundleb.putString("ztdblon", ztdblon);
		bundleb.putString("ztdclat", ztdclat);
		bundleb.putString("ztdclon", ztdclon);
		bundleb.putString("ztddlat", ztddlat);
		bundleb.putString("ztddlon", ztddlon);
		bundleb.putString("ztdelat", ztdelat);
		bundleb.putString("ztdelon", ztdelon);
		bundleb.putString("zhlat", zhlat);
		bundleb.putString("zhlon", zhlon);
		bundleb.putString("xhlat", xhlat);
		bundleb.putString("xhlon", xhlon);
		bundleb.putString("zhdd", zhdd);
		bundleb.putString("zhname", zhname);
		bundleb.putString("zhtel", zhtel);
		bundleb.putString("xhdd", xhdd);
		bundleb.putString("xhname", xhname);
		bundleb.putString("xhtel", xhtel);
		bundleb.putString("ztadd", ztadd);
		bundleb.putString("ztbdd", ztbdd);
		bundleb.putString("ztcdd", ztcdd);
		bundleb.putString("ztddd", ztddd);
		bundleb.putString("ztedd", ztedd);
		bundleb.putString("ztaname", ztaname);
		bundleb.putString("ztbname", ztbname);
		bundleb.putString("ztcname", ztcname);
		bundleb.putString("ztdname", ztdname);
		bundleb.putString("ztename", ztename);
		bundleb.putString("ztatel", ztatel);
		bundleb.putString("ztbtel", ztbtel);
		bundleb.putString("ztctel", ztctel);
		bundleb.putString("ztdtel", ztdtel);
		bundleb.putString("ztetel", ztetel);
		intenta.putExtras(bundleb);
		startActivity(intenta);
	}

	private void jsjl(String slat, String slon, String elat, String elon, int a) {
		startlatlng = new LatLng(Double.parseDouble(slat),
				Double.parseDouble(slon));
		endlatlng = new LatLng(Double.parseDouble(elat),
				Double.parseDouble(elon));
		startnode = PlanNode.withLocation(startlatlng);
		endnode = PlanNode.withLocation(endlatlng);
		if (a == 0) {
			search.drivingSearch((new DrivingRoutePlanOption())
					.policy(DrivingPolicy.ECAR_TIME_FIRST).from(startnode)
					.to(endnode));
		} else {
			DrivingRoutePlanOption drivingRoutePlanOption = new DrivingRoutePlanOption();
			drivingRoutePlanOption.from(startnode).passBy(list).to(endnode);
			search.drivingSearch(drivingRoutePlanOption);
		}
	}

	BaseAdapter adaptera = new BaseAdapter() {
		@Override
		public View getView(int position, View convertview, ViewGroup parent) {
			ImageView imageView = new ImageView(Ycactivitya.this);
			imageView.setImageResource(im[position % im.length]);
			return imageView;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		if (arg2 == 0) {
			of[0] = 1;
		} else {
			of[0] = 0;
		}
		if (arg2 == 1) {
			of[1] = 1;
		} else {
			of[1] = 0;
		}
		if (arg2 == 2) {
			of[2] = 1;
		} else {
			of[2] = 0;
		}
		if (arg2 == 3) {
			of[3] = 1;
		} else {
			of[3] = 0;
		}
		gallerytva.setText(imtv[arg2 % imtv.length]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	@Override
	protected void onPause() {
		super.onPause();
		ada = "hello";
		MobclickAgent.onPause(this);
	}

	private void geojs() {
		geocodera = GeoCoder.newInstance();
		geocodera.setOnGetGeoCodeResultListener(ongeoa);
		geocoderb = GeoCoder.newInstance();
		geocoderb.setOnGetGeoCodeResultListener(ongeob);
		xhlatlng = new LatLng(Double.valueOf(xhlat), Double.valueOf(xhlon));
		zhlatlng = new LatLng(Double.valueOf(zhlat), Double.valueOf(zhlon));
		geocodera.reverseGeoCode(new ReverseGeoCodeOption().location(zhlatlng));
		geocoderb.reverseGeoCode(new ReverseGeoCodeOption().location(xhlatlng));
	}

	OnGetGeoCoderResultListener ongeoa = new OnGetGeoCoderResultListener() {

		@Override
		public void onGetGeoCodeResult(GeoCodeResult arg0) {
		}

		@Override
		public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
			if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
				startcity = arg0.getAddressDetail().city;
				return;
			}
		}

	};
	OnGetGeoCoderResultListener ongeob = new OnGetGeoCoderResultListener() {

		@Override
		public void onGetGeoCodeResult(GeoCodeResult arg0) {
		}

		@Override
		public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
			if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
				endcity = arg0.getAddressDetail().city;
				return;
			}
		}

	};

	private void mcdjsjla() {
		alljuli = 0;
		list = new ArrayList<PlanNode>();
		if (ztdalat.equals("null")) {
			jsjl(zhlat, zhlon, xhlat, xhlon, 0);
		} else {
			if (ztdblat.equals("null")) {
				list.add(PlanNode.withLocation(new LatLng(Double
						.parseDouble(ztdalat), Double.parseDouble(ztdalon))));
				jsjl(zhlat, zhlon, xhlat, xhlon, 1);
			} else {
				if (ztdclat.equals("null")) {
					list.add(PlanNode.withLocation(new LatLng(Double
							.parseDouble(ztdalat), Double.parseDouble(ztdalon))));
					list.add(PlanNode.withLocation(new LatLng(Double
							.parseDouble(ztdblat), Double.parseDouble(ztdblon))));
					jsjl(zhlat, zhlon, xhlat, xhlon, 1);
				} else {
					if (ztddlat.equals("null")) {
						list.add(PlanNode.withLocation(new LatLng(Double
								.parseDouble(ztdalat), Double
								.parseDouble(ztdalon))));
						list.add(PlanNode.withLocation(new LatLng(Double
								.parseDouble(ztdblat), Double
								.parseDouble(ztdblon))));
						list.add(PlanNode.withLocation(new LatLng(Double
								.parseDouble(ztdclat), Double
								.parseDouble(ztdclon))));
						jsjl(zhlat, zhlon, xhlat, xhlon, 1);
					} else {
						if (ztdelat.equals("null")) {
							list.add(PlanNode.withLocation(new LatLng(Double
									.parseDouble(ztdalat), Double
									.parseDouble(ztdalon))));
							list.add(PlanNode.withLocation(new LatLng(Double
									.parseDouble(ztdblat), Double
									.parseDouble(ztdblon))));
							list.add(PlanNode.withLocation(new LatLng(Double
									.parseDouble(ztdclat), Double
									.parseDouble(ztdclon))));
							list.add(PlanNode.withLocation(new LatLng(Double
									.parseDouble(ztddlat), Double
									.parseDouble(ztddlon))));
							jsjl(zhlat, zhlon, xhlat, xhlon, 1);
						} else {
							list.add(PlanNode.withLocation(new LatLng(Double
									.parseDouble(ztdalat), Double
									.parseDouble(ztdalon))));
							list.add(PlanNode.withLocation(new LatLng(Double
									.parseDouble(ztdblat), Double
									.parseDouble(ztdblon))));
							list.add(PlanNode.withLocation(new LatLng(Double
									.parseDouble(ztdclat), Double
									.parseDouble(ztdclon))));
							list.add(PlanNode.withLocation(new LatLng(Double
									.parseDouble(ztdelat), Double
									.parseDouble(ztdelon))));
							jsjl(zhlat, zhlon, xhlat, xhlon, 1);
						}
					}
				}
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		String cddzhdd = zhaddress.getText().toString().trim();
		String cddxhdd = xhaddress.getText().toString().trim();
		ycsj = ycatva.getText().toString().trim();
		if (ycsj.equals(getResources().getString(R.string.qxzycsj))) {
			showToast(getResources().getString(R.string.qxzycsja));
		} else {
			if (cddzhdd.equals(getResources().getString(R.string.cnlzh))
					|| cddxhdd.equals(getResources().getString(R.string.dnlxh))) {
				showToast(getResources().getString(R.string.qxzhhxhdd));
			} else {
				if (of[0] == 0) {
					of[0] = 1;
				} else if (of[0] == 1) {
					Mydialogd mydialogd = new Mydialogd(Ycactivitya.this,
							zhlat, zhlon, xhlat, xhlon, phonea, passworda,
							qcmz, d, l, msychsyuyc, eyyq, mytime, zhdd, xhdd,
							aya, ayb);
					mydialogd.setDialogCallbackd(mydialogddissmiss);
					mydialogd.show();
				}
			}
			if (arg2 == 1) {
				if (of[1] == 0) {
					of[1] = 1;
				} else if (of[1] == 1) {
					Mydialoge mydialoge = new Mydialoge(Ycactivitya.this,
							zhlat, zhlon, xhlat, xhlon, phonea, passworda,
							qcmze, d, l, msychsyuyc, eyyq, mytime, zhdd, xhdd,
							aya, ayb);
					mydialoge.setDialogcallbacke(mydialogdeissmiss);
					mydialoge.show();
				}
			}
			if (arg2 == 2) {
				if (of[2] == 0) {
					of[2] = 1;
				} else if (of[2] == 1) {
					Mydialogf mydialogf = new Mydialogf(Ycactivitya.this,
							zhlat, zhlon, xhlat, xhlon, phonea, passworda,
							qcmzf, d, l, msychsyuyc, eyyq, mytime, zhdd, xhdd,
							aya, ayb);
					mydialogf.setDialogcallbackf(mydialogdfissmiss);
					mydialogf.show();
				}
			}
			if (arg2 == 3) {
				if (of[3] == 0) {
					of[3] = 1;
				} else if (of[3] == 1) {
					Mydialogg mydialogg = new Mydialogg(Ycactivitya.this,
							zhlat, zhlon, xhlat, xhlon, phonea, passworda, d,
							l, qcmzjzx, c, msychsyuyc, eyyq, mytime, zhdd,
							xhdd, aya);
					mydialogg.setDialogCallbackg(mydialoggfissmiss);
					mydialogg.show();
				}
			}
			// pdsfjs();
		}
	}

	private void ygfy() {
		ygfyxs.setVisibility(View.VISIBLE);
		ygfyxs.setText(getResources().getString(R.string.qdd));
		geojs();
		mcdjsjla();
	}

	private void tjygfy() {
		gls = alljuli / 1000;
		if (startcity == null) {
			startcity = getResources().getString(R.string.zss);
		}
		if (endcity == null) {
			endcity = getResources().getString(R.string.zss);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("key", "about_money");
		map.put("cartype_id", carid);
		map.put("request", eyyq);
		map.put("distance", gls);
		map.put("start_city", startcity);
		map.put("end_city", endcity);
		String jiami = ToolUtils.JiaMi(map);
		String url = Net.Ygfy + jiami;
		application.doGet(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Ygfyhelper ygfyhelper = GsonUtils.json2Bean(arg0.result,
						Ygfyhelper.class);
				if (ygfyhelper.status.equals("0")) {
					String strmoney = ygfyhelper.money;
					String intaqcsl = qcsl.replace(
							getResources().getString(R.string.l), "").trim();
					// doubletoint
					int intqcsl = Integer.valueOf(intaqcsl);
					int money = Integer.valueOf(strmoney);
					int allmoney = intqcsl * money;
					ygfyxs.setText(getResources().getString(R.string.ygfy)
							+ "	¥" + allmoney
							+ getResources().getString(R.string.yuan));
				} else {
					showToast("sql is word");
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast(getResources().getString(R.string.wlyc));
			}
		});
	}

	Dialogcallbackg mydialoggfissmiss = new Dialogcallbackg() {
		@Override
		public void dialogdog(String a, String b, String c, String d, String e,
				int f, String h) {
			if (c.equals("null")) {
				c = "1" + getResources().getString(R.string.l);
			}
			qcmc = a;
			qcgg = b;
			qcsl = c;
			qcdw = "null";
			carid = f;
			qczhl = h;
			ygfy();
			ygfy.setTextColor(Color.BLACK);
			ygfy.setText(a + "	" + b + "	" + c);
			gallerytva.setError(null);
		}
	};
	Dialogcallbackf mydialogdfissmiss = new Dialogcallbackf() {
		@Override
		public void dialogdof(String a, String b, String c, String d, String e,
				String f, int g, String h) {
			if (d.equals("null")) {
				d = "1" + getResources().getString(R.string.l);
			}
			qcmc = a;
			qcdw = b;
			qcgg = c;
			qcsl = d;
			carid = g;
			qczhl = h;
			ygfy();
			ygfy.setTextColor(Color.BLACK);
			ygfy.setText(a + b + "	" + c + "	" + d);
			gallerytva.setError(null);
		}
	};
	Dialogcallbacke mydialogdeissmiss = new Dialogcallbacke() {
		@Override
		public void dialogdoe(String a, String b, String c, String d, String e,
				String f, int g, String h) {
			if (d.equals("null")) {
				d = "1" + getResources().getString(R.string.l);
			}
			qcmc = a;
			qcdw = b;
			qcgg = c;
			qcsl = d;
			carid = g;
			qczhl = h;
			ygfy();
			ygfy.setTextColor(Color.BLACK);
			ygfy.setText(a + b + "	" + c + "	" + d);
			gallerytva.setError(null);
		}
	};
	Dialogcallbackd mydialogddissmiss = new Dialogcallbackd() {
		@Override
		public void dialogdod(String a, String b, String c, String d, String e,
				String f, int g, String h) {
			if (d.equals("null")) {
				d = "1" + getResources().getString(R.string.l);
			}
			qcmc = a;
			qcdw = b;
			qcgg = c;
			qcsl = d;
			carid = g;
			qczhl = h;
			ygfy.setTextColor(Color.BLACK);
			ygfy.setText(a + b + "	" + c + "	" + d);
			ygfy();
			gallerytva.setError(null);
		}
	};
	Dialogcallback mydialogadissmiss = new Dialogcallback() {
		@Override
		public void dialogdo(int a, int b, int c, int d, int e, int f) {
			qcgs = a;
			hgjg = b;
			bswl = c;
			dwb = d;
			dcm = e;
			scm = f;
			bcztqcgs = a;
			bczthgjg = b;
			bcztbswl = c;
			bcztdwb = d;
			bcztdcm = e;
			bcztscm = f;
			eyyqb = "";
			if (a == 1) {
				eyyqa = getResources().getString(R.string.qcgs);
				eyyqb = eyyqb + "," + eyyqa;
			}
			if (b == 1) {
				eyyqa = getResources().getString(R.string.hgjg);
				eyyqb = eyyqb + "," + eyyqa;
			}
			if (c == 1) {
				eyyqa = getResources().getString(R.string.bswl);
				eyyqb = eyyqb + "," + eyyqa;
			}
			if (d == 1) {
				eyyqa = getResources().getString(R.string.dwb);
				eyyqb = eyyqb + "," + eyyqa;
			}
			if (e == 1) {
				eyyqa = getResources().getString(R.string.dcm);
				eyyqb = eyyqb + "," + eyyqa;
			}
			if (f == 1) {
				eyyqa = getResources().getString(R.string.scm);
				eyyqb = eyyqb + "," + eyyqa;
			}

			if (qcgs > 0) {
				qcgs = 3;
			}
			if (hgjg > 0) {
				hgjg = 1;
			}
			if (bswl > 0) {
				bswl = 5;
			}
			if (dwb > 0) {
				dwb = 6;
			}
			if (dcm > 0) {
				dcm = 7;
			}
			if (scm > 0) {
				scm = 8;
			}
			if (scm == 0) {
				eyyq = String.valueOf(qcgs) + "|" + String.valueOf(hgjg) + "|"
						+ String.valueOf(bswl) + "|" + String.valueOf(dwb)
						+ "|" + String.valueOf(dcm);
			} else {
				eyyq = String.valueOf(qcgs) + "|" + String.valueOf(hgjg) + "|"
						+ String.valueOf(bswl) + "|" + String.valueOf(dwb)
						+ "|" + String.valueOf(dcm) + "|" + String.valueOf(scm);
			}
			eyyq = eyyq.replace("0|", "");
			eyyq = eyyq.replace("|0", "");
			int eyyqxzgs = a + b + c + d + e + f;
			if (eyyqxzgs > 0) {
				eyyqb = eyyqb.substring(1);
				ycatvb.setText(eyyqb);
			} else {
				ycatvb.setText(getResources().getString(R.string.eyyq));
			}
			if (eyyqxzgs < 1) {
				eyyq = "";
			}
			if (carid != 0) {
				ygfy();
			}
			qcsl = "0" + getResources().getString(R.string.l);
			ygfy.setTextColor(Color.parseColor("#AEAEAE"));
			ygfy.setText(getResources().getString(R.string.ygyf));
			ygfyxs.setText("");
			ygfyxs.setVisibility(View.GONE);
		}
	};
	Dialogcallbackb mydialogbdissmiss = new Dialogcallbackb() {
		@Override
		public void dialogdob(String a, String b, String c) {
			datea = new Date(System.currentTimeMillis());
			sdfyear = new SimpleDateFormat("yyyy");
			yeara = sdfyear.format(datea);
			intyeara = Integer.valueOf(yeara);
			sdfmonth = new SimpleDateFormat("MM");
			montha = sdfmonth.format(datea);
			intmonth = Integer.valueOf(montha);
			sdfday = new SimpleDateFormat("dd");
			daya = sdfday.format(datea);
			intday = Integer.valueOf(daya);
			if (a.equals("null")) {
				a = getResources().getString(R.string.xz);
			} else {
				if (!a.equals(getResources().getString(R.string.xz))) {
					jqdtimea = a;
					jqdtimeb = jqdtimea.split(getResources().getString(
							R.string.y));
					jqdmonth = jqdtimeb[0];
					jqdmonthint = Integer.valueOf(jqdmonth);
					jqdaya = jqdtimeb[1];
					jqddaya = jqdaya
							.split(getResources().getString(R.string.r));
					jqdayc = jqddaya[0];
					jqddayint = Integer.valueOf(jqdayc);
					if (intmonth > jqdmonthint + 6) {
						year = intyeara + 1;
					} else {
						year = intyeara;
					}
					ayear = String.valueOf(year);
					month = String.valueOf(jqdmonthint);
					day = String.valueOf(jqddayint);
				} else {
					year = intyeara;
				}
			}
			if (b.equals("null")) {
				jqtimea = b;
				b = "";
			} else {
				jqtimea = b;
				b = b + ":";
			}
			if (c.equals("null")) {
				c = "";
			} else {
				jqtimeint = Integer.valueOf(jqtimea);
				jqsecondint = Integer.valueOf(c);
				hh = String.valueOf(jqtimeint);
				mm = c;
			}
			ycatva.setText(a + "		" + b + c);
			ycatva.setTextColor(Color.parseColor("#EC494A"));
			xzdsj = a + "		" + b + c;

			if (a.equals("null")
					|| a.equals(getResources().getString(R.string.xz))) {
				mytime = "00000000000000";
			} else {
				if (jqdmonthint < 10) {
					month = "0" + month;
				}
				if (jqddayint < 10) {
					day = "0" + day;
				}
				if (jqtimeint < 10) {
					hh = "0" + hh;
				}
				mytime = ayear + month + day + hh + mm + ss;
			}
			qcsl = "0" + getResources().getString(R.string.l);
			ygfy.setTextColor(Color.parseColor("#AEAEAE"));
			ygfy.setText(getResources().getString(R.string.ygyf));
			ygfyxs.setText("");
			ygfyxs.setVisibility(View.GONE);
		}
	};
}