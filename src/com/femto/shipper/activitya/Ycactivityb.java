package com.femto.shipper.activitya;

import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.femto.shipper.R;
import com.femto.shipper.activityab.Mydialogh;
import com.femto.shipper.activityab.Mydialogh.Dialogcallbackh;
import com.femto.shipper.activitya.Mydialogi.Dialogcallbacki;
import com.femto.shipper.activitya.Mydialogj.Dialogcallbackj;
import com.femto.shipper.activitya.Mydialogk.Dialogcallbackk;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.umeng.analytics.MobclickAgent;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Ycactivityb extends BaseActivity implements OnClickListener,
		OnItemClickListener {
	private RelativeLayout ycbrla, ycbrlb, ycbrlc, ycbslrl, ycbshrl, ycbgcrl,
			ycbxhrl;
	private TextView ycbtvd, ycbtvc, ycbhuotva, yvbhuotvb, yvbhuotvc, ycbgctv,
			ycbsltv, ycbshtv, ycbxhtv;
	@SuppressWarnings("unused")
	private String name, tel, fdxtz, timea, zhdd, zhlxr, zhtel, ztdda, ztddb,
			filename, phonea, passworda, ztddc, ztdde, ztlxra, ztlxrb, ztlxrc,
			ztlxrd, qsrlc, nickname, srdlcsbzq, ztlxre, ztdha, ztdhb, ztdhc,
			tokena, ztdhd, ztdhe, xhlxr, xhdd, xhdh, cxid, zhname, xhname,
			qczhl, xhtel, ztadd, ztaname, ztatel, qsrgcrs, srdgcrsbzq, ztbdd,
			ztbname, orderamount = "", orderno = "", add_start, add_end,
			ztbtel, ztcdd, xzdsj, mytime, eyyq, qcmc, qcdw, qcgg, qcsl,
			ztdalon, ztdalat, ztdblon, ztdblat, ztdclon, ztdclat, ztddlon,
			ztddlat, midway5_contact, midway4_contact, midway3_contact,
			midway2_contact, hwzpida = "", hwzpidb = "", hwzpidc = "", request,
			hwzpidd = "", strprovince, midway1, midway2, midway3, midway4,
			midway5, strcity, strcityb, strprovinceb, yballslcs, ztdelon,
			ztdelat, zhlat, zhlon, xhlat, xhlon, ztcname, username, pwd,
			use_time, add_start_contact, sfdxtz = "1", add_end_contact,
			midway1_contact, hwzpidba, hwzpidca, kbbz, zxbz, mx, jx, tz, sz,
			qt, nbcp, ryp, jc, juju, jydq, jxsb, yballbz, yballfzrdh,
			yballfzrxm, yballhwsx, yballbzzl, yballshi, yballxhi, yballsli,
			yballdt, yballgci, yballgcrs, dzcp, lbj, qph, yshw, hgcp, hwzpidda,
			img_url, ztctel, ztddd, alljulioo, startprovince = "",
			startcity = "", province = "", city = "", pack, remark,
			contacts_name, contacts_mobile, pathimage, ztdname, ztdtel, ztedd,
			distance, systemtime = "", ztename, ztetel;
	private Bundle bundleb = null, bundlea = null;
	private int sh[] = { R.drawable.shanghuoa, R.drawable.shanghuob };
	private int xh[] = { R.drawable.xiahuoa, R.drawable.xiahuob };
	private int sl[] = { R.drawable.shangloua, R.drawable.shangloub };
	private int gc[] = { R.drawable.genchea, R.drawable.gencheb };
	private ImageView ycbshiv, ycbxhiv, ycbgciv, ycbsliv;
	private Gridadapter gridadapter;
	private GridView ycbgv;
	@SuppressWarnings("unused")
	private int shi = 0, carid, sli = 0, xhi = 0, gci = 0, b = 0, bzzlid,
			tp = 0, qcgs, hgjg, bswl, dwb, dcm, scm, year, jqdmonthint,
			jqddayint, order_type, is_notify = 1, jqtimeint, hwsxid,
			jqsecondint, lxra = 0, dt = 0, slcs = 0, gcrs = 0, is_up, is_down,
			is_supercargo, is_up_floor, is_lift, cartype, car_quantity,
			ctcs = 0, goods_type, floor_number, supercargo_number, height,
			width;
	private Intent intenta, intentb, intntc;
	public static String adb = "hello";
	private String fzrxm, fzrdh, hwsx = "null", bzzl = "null", bz;
	private Button ycbbtnxyba;
	private EditText ycbbzet;
	private SharedPreferences sharedPreferences, sharedpreferencesa,
			sharedpreferencesb, sharedpreferencesc, sharedpreferencese;
	private GeoCoder geocodera = null, geocoderb = null;
	private LatLng xhlatlng, zhlatlng;
	private String qcslstr[];
	public static Activity ycactivityb;
	private Editor editor = null, editora = null;
	private Bitmap bitmap = null, bitmapa = null;
	// private Uri uria = null, urib = null, uric = null, urid = null;
	private byte[] uria = null, urib = null, uric = null, urid = null;
	private Dialoga dialoga;
	private UploadManager uploadManager;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			if (b == 1) {
				if (Bimp.tempSelectBitmap.size() < 4) {
					bitmap = BitmapFactory.decodeFile(Environment
							.getExternalStorageDirectory()
							+ "/mycardworkupload.jpg");
					bitmapa = ToolUtils.Piczoom(bitmap, bitmap.getWidth() / 4,
							bitmap.getHeight() / 4);
					bitmap.recycle();
					ImageItem takePhoto = new ImageItem();
					takePhoto.setBitmap(bitmapa);
					Bimp.tempSelectBitmap.add(takePhoto);
					sctp();
				}
				b = 0;
			}
		}
		if (resultCode == 5) {
			name = data.getStringExtra("lxrname");
			tel = data.getStringExtra("lxrtel");
			fdxtz = data.getStringExtra("fdxtz");
			sfdxtz = data.getStringExtra("sfdxtz");
			ycbtvc.setText(name);
			ycbtvd.setText(tel);
		}
	}

	Dialogcallbackk mydialogkdissmiss = new Dialogcallbackk() {
		@SuppressLint("InlinedApi")
		@Override
		public void dialogdok(int a) {
			if (a == 1) {
				// intenta = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// startActivityForResult(intenta, 6);
				String SDState = Environment.getExternalStorageState();
				if (SDState.equals(Environment.MEDIA_MOUNTED)) {
					intenta = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					Uri photoUri = Uri.fromFile(new File(Environment
							.getExternalStorageDirectory(),
							"mycardworkupload.jpg"));
					intenta.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
							photoUri);
					startActivityForResult(intenta, 6);
					b = 1;
				} else {
					showToast("内存卡不存在");
				}
			} else if (a == 2) {
				intentb = new Intent(Ycactivityb.this, AlbumActivity.class);
				startActivity(intentb);
				b = 2;
			}

		}
	};

	@Override
	protected void onDestroy() {
		if (bitmapa != null) {
			bitmapa.recycle();
		}
		if (editor != null) {
			editor.clear();
		}
		if (editora != null) {
			editora.clear();
		}
		if (geocodera != null) {
			geocodera.destroy();
		}
		if (geocoderb != null) {
			geocoderb.destroy();
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
		setContentView(R.layout.ycactivityb);
		ycactivityb = this;
		dialoga = new Dialoga(this);
		cshid();
	}

	@Override
	protected void onResume() {
		super.onResume();
		sctp();
		ycbgv.setAdapter(gridadapter);
		MobclickAgent.onResume(this);
	}

	class Myasynctaska extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			UpDataToNet(uria);
			return null;
		}
	}

	class Myasynctaskb extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			UpDataToNet(urib);
			return null;
		}
	}

	class Myasynctaskc extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			UpDataToNet(uric);
			return null;
		}
	}

	class Myasynctaskd extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			UpDataToNet(urid);
			return null;
		}
	}

	// private void ybsctp() {
	// if (uria != null) {
	// if (urib == null) {
	// Myasynctaska myasynctaska = new Myasynctaska();
	// myasynctaska.execute("100");
	// } else {
	// if (uric == null) {
	// Myasynctaska myasynctaska = new Myasynctaska();
	// myasynctaska.execute("100");
	// Myasynctaskb myasynctaskb = new Myasynctaskb();
	// myasynctaskb.execute("100");
	// } else {
	// if (urid == null) {
	// Myasynctaska myasynctaska = new Myasynctaska();
	// myasynctaska.execute("100");
	// Myasynctaskb myasynctaskb = new Myasynctaskb();
	// myasynctaskb.execute("100");
	// Myasynctaskc myasynctaskc = new Myasynctaskc();
	// myasynctaskc.execute("100");
	// } else {
	// Myasynctaska myasynctaska = new Myasynctaska();
	// myasynctaska.execute("100");
	// Myasynctaskb myasynctaskb = new Myasynctaskb();
	// myasynctaskb.execute("100");
	// Myasynctaskc myasynctaskc = new Myasynctaskc();
	// myasynctaskc.execute("100");
	// Myasynctaskd myasynctaskd = new Myasynctaskd();
	// myasynctaskd.execute("100");
	// }
	// }
	// }
	// }
	// }
	private void UpDataToNet(final byte[] bytea) {
		uploadManager = new UploadManager();
		final String key = phonea + "_" + ToolUtils.Timea();
		uploadManager.put(bytea, key, tokena, new UpCompletionHandler() {
			@Override
			public void complete(String arg0,
					com.qiniu.android.http.ResponseInfo arg1, JSONObject arg2) {
				if (arg1.statusCode == 200) {
					tp++;
					if (tp == 1) {
						hwzpida = key;
					} else if (tp == 2) {
						hwzpidb = key;
					} else if (tp == 3) {
						hwzpidc = key;
					} else if (tp == 4) {
						hwzpidd = key;
					}

				} else if (arg1.statusCode == 401) {
					tokena = ToolUtils.Token();
					editora = sharedpreferencese.edit();
					editora.putString("tokena", tokena);
					editora.commit();
					UpDataToNet(bytea);
				} else {
					showToast(getResources().getString(R.string.scsba));
				}
				if (urib != null) {
					if (uric != null) {
						if (urid != null) {
							ctcs++;
							if (ctcs == 1) {
								Myasynctaskb myasynctaskb = new Myasynctaskb();
								myasynctaskb.execute("100");
							} else if (ctcs == 2) {
								Myasynctaskc myasynctaskc = new Myasynctaskc();
								myasynctaskc.execute("100");
							} else if (ctcs == 3) {
								Myasynctaskd myasynctaskd = new Myasynctaskd();
								myasynctaskd.execute("100");
							} else if (ctcs == 4) {
								tjdd();
							}
						} else {
							ctcs++;
							if (ctcs == 1) {
								Myasynctaskb myasynctaskb = new Myasynctaskb();
								myasynctaskb.execute("100");
							} else if (ctcs == 2) {
								Myasynctaskc myasynctaskc = new Myasynctaskc();
								myasynctaskc.execute("100");
							} else if (ctcs == 3) {
								tjdd();
							}
						}
					} else {
						ctcs++;
						if (ctcs == 1) {
							Myasynctaskb myasynctaskb = new Myasynctaskb();
							myasynctaskb.execute("100");
						} else if (ctcs == 2) {
							tjdd();
						}
					}
				} else {
					tjdd();
				}
			}
		}, null);
	}

	// private void UpDataToNet(Uri uri) {
	// File urlToFilePath = URIUtils.urlToFilePath(mContext, uri);
	// RequestParams params = new RequestParams();
	// params.addBodyParameter("action", "upload_goodsImg");
	// params.addBodyParameter("face", urlToFilePath);
	// application.doPost(Net.UPLOAD_GOODSIMG, params,
	// new RequestCallBack<String>() {
	// @Override
	// public void onSuccess(ResponseInfo<String> arg0) {
	// UpImageBean statusBean = GsonUtils.json2Bean(
	// arg0.result, UpImageBean.class);
	// if (statusBean.status.equals("0")) {
	// tp++;
	// if (tp == 1) {
	// hwzpida = statusBean.path;
	// } else if (tp == 2) {
	// hwzpidb = statusBean.path;
	// } else if (tp == 3) {
	// hwzpidc = statusBean.path;
	// } else if (tp == 4) {
	// hwzpidd = statusBean.path;
	// }
	// // showToast(getResources().getString(R.string.sccga));
	// } else {
	// // c = 1;
	// showToast(getResources().getString(R.string.scsba)
	// + statusBean.msg);
	// }
	// if (urib != null) {
	// if (uric != null) {
	// if (urid != null) {
	// ctcs++;
	// if (ctcs == 1) {
	// Myasynctaskb myasynctaskb = new Myasynctaskb();
	// myasynctaskb.execute("100");
	// } else if (ctcs == 2) {
	// Myasynctaskc myasynctaskc = new Myasynctaskc();
	// myasynctaskc.execute("100");
	// } else if (ctcs == 3) {
	// Myasynctaskd myasynctaskd = new Myasynctaskd();
	// myasynctaskd.execute("100");
	// } else if (ctcs == 4) {
	// tjdd();
	// }
	// } else {
	// ctcs++;
	// if (ctcs == 1) {
	// Myasynctaskb myasynctaskb = new Myasynctaskb();
	// myasynctaskb.execute("100");
	// } else if (ctcs == 2) {
	// Myasynctaskc myasynctaskc = new Myasynctaskc();
	// myasynctaskc.execute("100");
	// } else if (ctcs == 3) {
	// tjdd();
	// }
	// }
	// } else {
	// ctcs++;
	// if (ctcs == 1) {
	// Myasynctaskb myasynctaskb = new Myasynctaskb();
	// myasynctaskb.execute("100");
	// } else if (ctcs == 2) {
	// tjdd();
	// }
	// }
	// } else {
	// tjdd();
	// }
	// }
	//
	// @Override
	// public void onFailure(HttpException arg0, String arg1) {
	// dismissProgressDialog();
	// // c = 1;
	// // int tpaa = tp + 1;
	// // showToast(getResources().getString(R.string.ditp)
	// // + tpaa
	// // + getResources().getString(R.string.tpscsb));
	// showToast(getResources().getString(R.string.wlyc));
	// }
	// });
	// }

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	private void sctp() {
		try {
			uria = ToolUtils.Bitmap2Bytes(ToolUtils
					.compressImage(Bimp.tempSelectBitmap.get(0).getBitmap()));
		} catch (Exception e) {
			uria = null;
		}
		try {
			urib = ToolUtils.Bitmap2Bytes(ToolUtils
					.compressImage(Bimp.tempSelectBitmap.get(1).getBitmap()));
		} catch (Exception e) {
			urib = null;
		}
		try {
			uric = ToolUtils.Bitmap2Bytes(ToolUtils
					.compressImage(Bimp.tempSelectBitmap.get(2).getBitmap()));
		} catch (Exception e) {
			uric = null;
		}
		try {
			urid = ToolUtils.Bitmap2Bytes(ToolUtils
					.compressImage(Bimp.tempSelectBitmap.get(3).getBitmap()));
		} catch (Exception e) {
			urid = null;
		}
	}

	// private void sctp() {
	// try {
	// uria = Uri.parse(MediaStore.Images.Media.insertImage(
	// getContentResolver(), ToolUtils
	// .compressImage(Bimp.tempSelectBitmap.get(0)
	// .getBitmap()), null, null));
	// } catch (Exception e) {
	// uria = null;
	// }
	// try {
	// urib = Uri.parse(MediaStore.Images.Media.insertImage(
	// getContentResolver(), ToolUtils
	// .compressImage(Bimp.tempSelectBitmap.get(1)
	// .getBitmap()), null, null));
	// } catch (Exception e) {
	// urib = null;
	// }
	// try {
	// uric = Uri.parse(MediaStore.Images.Media.insertImage(
	// getContentResolver(), ToolUtils
	// .compressImage(Bimp.tempSelectBitmap.get(2)
	// .getBitmap()), null, null));
	// } catch (Exception e) {
	// uric = null;
	// }
	// try {
	// urid = Uri.parse(MediaStore.Images.Media.insertImage(
	// getContentResolver(), ToolUtils
	// .compressImage(Bimp.tempSelectBitmap.get(3)
	// .getBitmap()), null, null));
	// } catch (Exception e) {
	// urid = null;
	// }
	// }

	private void cshid() {
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		sharedpreferencesa = getSharedPreferences("user_xinxi",
				Activity.MODE_PRIVATE);
		nickname = sharedpreferencesa.getString("nick_name", "");
		sharedpreferencese = getSharedPreferences("tokena",
				Activity.MODE_PRIVATE);
		tokena = sharedpreferencese.getString("tokena", "");
		if (tokena == null || tokena.equals("") || tokena.equals("null")) {
			tokena = ToolUtils.Token();
			editora = sharedpreferencese.edit();
			editora.putString("tokena", tokena);
			editora.commit();
		}
		sharedpreferencesb = getSharedPreferences("yball",
				Activity.MODE_PRIVATE);
		yballbz = sharedpreferencesb.getString("bz", "");
		yballfzrdh = sharedpreferencesb.getString("fzrdh", "");
		yballfzrxm = sharedpreferencesb.getString("fzrxm", "");
		yballhwsx = sharedpreferencesb.getString("hwsx", "");
		yballbzzl = sharedpreferencesb.getString("bzzl", "");
		yballshi = sharedpreferencesb.getString("shi", "");
		yballxhi = sharedpreferencesb.getString("xhi", "");
		yballsli = sharedpreferencesb.getString("sli", "");
		yballdt = sharedpreferencesb.getString("dt", "");
		yballslcs = sharedpreferencesb.getString("slcs", "");
		yballgci = sharedpreferencesb.getString("gci", "");
		yballgcrs = sharedpreferencesb.getString("gcrs", "");
		kbbz = getResources().getString(R.string.kbbz);
		zxbz = getResources().getString(R.string.zxbz);
		mx = getResources().getString(R.string.mx);
		jx = getResources().getString(R.string.jx);
		tz = getResources().getString(R.string.tz);
		sz = getResources().getString(R.string.sz);
		qt = getResources().getString(R.string.qt);
		nbcp = getResources().getString(R.string.nbcp);
		ryp = getResources().getString(R.string.ryp);
		jc = getResources().getString(R.string.jc);
		juju = getResources().getString(R.string.juju);
		jydq = getResources().getString(R.string.jydq);
		jxsb = getResources().getString(R.string.jxsb);
		dzcp = getResources().getString(R.string.dzcp);
		lbj = getResources().getString(R.string.lbj);
		qph = getResources().getString(R.string.qph);
		yshw = getResources().getString(R.string.yshw);
		hgcp = getResources().getString(R.string.hgcp);
		qsrlc = getResources().getString(R.string.qsrlcs);
		qsrgcrs = getResources().getString(R.string.qsrgcrs);
		srdgcrsbzq = getResources().getString(R.string.srdgcrsbzq);
		srdlcsbzq = getResources().getString(R.string.srdlcsbzq);
		ycbbzet = (EditText) findViewById(R.id.ycbbzet);
		ycbbtnxyba = (Button) findViewById(R.id.ycbbtnxyba);
		ycbtvc = (TextView) findViewById(R.id.ycbtvc);
		ycbtvd = (TextView) findViewById(R.id.ycbtvd);
		ycbhuotva = (TextView) findViewById(R.id.ycbhuotva);
		yvbhuotvb = (TextView) findViewById(R.id.yvbhuotvb);
		yvbhuotvc = (TextView) findViewById(R.id.yvbhuotvc);
		ycbgctv = (TextView) findViewById(R.id.ycbgctv);
		ycbsltv = (TextView) findViewById(R.id.ycbsltv);
		ycbshtv = (TextView) findViewById(R.id.ycbshtv);
		ycbxhtv = (TextView) findViewById(R.id.ycbxhtv);
		ycbshiv = (ImageView) findViewById(R.id.ycbshiv);
		ycbxhiv = (ImageView) findViewById(R.id.ycbxhiv);
		ycbgciv = (ImageView) findViewById(R.id.ycbgciv);
		ycbsliv = (ImageView) findViewById(R.id.ycbsliv);
		ycbrla = (RelativeLayout) findViewById(R.id.ycbrla);
		ycbrlb = (RelativeLayout) findViewById(R.id.ycbrlb);
		ycbrlc = (RelativeLayout) findViewById(R.id.ycbrlc);
		ycbslrl = (RelativeLayout) findViewById(R.id.ycbslrl);
		ycbshrl = (RelativeLayout) findViewById(R.id.ycbshrl);
		ycbgcrl = (RelativeLayout) findViewById(R.id.ycbgcrl);
		ycbxhrl = (RelativeLayout) findViewById(R.id.ycbxhrl);
		ycbgv = (GridView) findViewById(R.id.ycbgv);
		ycbgv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridadapter = new Gridadapter(this);
		// gridadapter.update();
		ycbgv.setAdapter(gridadapter);
		ycbgv.setOnItemClickListener(this);
		ycbshiv.setBackgroundResource(sh[1]);
		ycbxhiv.setBackgroundResource(xh[1]);
		ycbgciv.setBackgroundResource(gc[1]);
		ycbsliv.setBackgroundResource(sl[1]);
		if (yballfzrdh == null || yballfzrdh.equals("")
				|| yballfzrdh.equals("null")) {
			ycbtvd.setText(phonea);
		} else {
			ycbtvd.setText(yballfzrdh);
		}
		if (yballfzrxm == null || yballfzrxm.equals("")
				|| yballfzrxm.equals("null")) {
		} else {
			ycbtvc.setText(yballfzrxm);
		}
		if (yballbz == null || yballbz.equals("") || yballbz.equals("null")) {
		} else {
			ycbbzet.setText(yballfzrxm);
		}
		if (yballhwsx == null || yballhwsx.equals("")
				|| yballhwsx.equals("null")) {
		} else {
			hwsx = yballhwsx;
			bzzl = yballbzzl;
			ycbhuotva.setVisibility(View.GONE);
			yvbhuotvb.setText(yballhwsx);
			yvbhuotvc.setText(getResources().getString(R.string.bzzl) + "		"
					+ yballbzzl);
		}
		if (yballshi == null || yballshi.equals("") || yballshi.equals("null")) {
		} else {
			if (yballshi.equals("1")) {
				ycbshiv.setBackgroundResource(sh[0]);
				ycbshtv.setTextColor(Color.BLACK);
				shi = 1;
			}
		}
		if (yballxhi == null || yballxhi.equals("") || yballxhi.equals("null")) {
		} else {
			if (yballxhi.equals("1")) {
				ycbxhiv.setBackgroundResource(xh[0]);
				ycbxhtv.setTextColor(Color.BLACK);
				xhi = 1;
			}
		}
		if (yballsli == null || yballsli.equals("") || yballsli.equals("null")) {
		} else {
			if (yballsli.equals("1")) {
				dt = Integer.valueOf(yballdt);
				slcs = Integer.valueOf(yballslcs);
				ycbsliv.setBackgroundResource(sl[0]);
				ycbsltv.setTextColor(Color.BLACK);
				sli = 1;
			}
		}
		if (yballgci == null || yballgci.equals("") || yballgci.equals("null")) {
		} else {
			if (yballgci.equals("1")) {
				gcrs = Integer.valueOf(yballgcrs);
				ycbgciv.setBackgroundResource(gc[0]);
				ycbgctv.setTextColor(Color.BLACK);
				gci = 1;
			}
		}
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		height = dm.heightPixels;
		width = dm.widthPixels;
		geocodera = GeoCoder.newInstance();
		geocodera.setOnGetGeoCodeResultListener(ongeoa);
		geocoderb = GeoCoder.newInstance();
		geocoderb.setOnGetGeoCodeResultListener(ongeob);
		ycbrla.setOnClickListener(this);
		ycbrlb.setOnClickListener(this);
		ycbrlc.setOnClickListener(this);
		ycbslrl.setOnClickListener(this);
		ycbshrl.setOnClickListener(this);
		ycbgcrl.setOnClickListener(this);
		ycbxhrl.setOnClickListener(this);
		ycbbtnxyba.setOnClickListener(this);
		pd();
	}

	private void pd() {
		if (adb.equals("adb")) {
			bundleb = new Bundle();
			bundleb = getIntent().getExtras();
			mytime = bundleb.getString("mytime");
			xzdsj = bundleb.getString("xzdsj");
			eyyq = bundleb.getString("eyyq");
			qcmc = bundleb.getString("qcmc");
			qcdw = bundleb.getString("qcdw");
			qcgg = bundleb.getString("qcgg");
			qcsl = bundleb.getString("qcsl");
			carid = bundleb.getInt("carid");
			alljulioo = bundleb.getString("alljulioo");
			qczhl = bundleb.getString("qczhl");
			ztdalat = bundleb.getString("ztdalat");
			ztdalon = bundleb.getString("ztdalon");
			ztdblat = bundleb.getString("ztdblat");
			ztdblon = bundleb.getString("ztdblon");
			ztdclat = bundleb.getString("ztdclat");
			ztdclon = bundleb.getString("ztdclon");
			ztddlat = bundleb.getString("ztddlat");
			ztddlon = bundleb.getString("ztddlon");
			ztdelat = bundleb.getString("ztdelat");
			ztdelon = bundleb.getString("ztdelon");
			zhlat = bundleb.getString("zhlat");
			zhlon = bundleb.getString("zhlon");
			xhlat = bundleb.getString("xhlat");
			xhlon = bundleb.getString("xhlon");
			zhdd = bundleb.getString("zhdd");
			zhname = bundleb.getString("zhname");
			zhtel = bundleb.getString("zhtel");
			xhdd = bundleb.getString("xhdd");
			xhname = bundleb.getString("xhname");
			xhtel = bundleb.getString("xhtel");
			ztadd = bundleb.getString("ztadd");
			ztbdd = bundleb.getString("ztbdd");
			ztcdd = bundleb.getString("ztcdd");
			ztddd = bundleb.getString("ztddd");
			ztedd = bundleb.getString("ztedd");
			ztaname = bundleb.getString("ztaname");
			ztbname = bundleb.getString("ztbname");
			ztcname = bundleb.getString("ztcname");
			ztdname = bundleb.getString("ztdname");
			ztename = bundleb.getString("ztename");
			ztatel = bundleb.getString("ztatel");
			ztbtel = bundleb.getString("ztbtel");
			ztctel = bundleb.getString("ztctel");
			ztdtel = bundleb.getString("ztdtel");
			ztetel = bundleb.getString("ztetel");
			xhlatlng = new LatLng(Double.valueOf(xhlat), Double.valueOf(xhlon));
			zhlatlng = new LatLng(Double.valueOf(zhlat), Double.valueOf(zhlon));
			geocoderb.reverseGeoCode(new ReverseGeoCodeOption()
					.location(zhlatlng));
			geocodera.reverseGeoCode(new ReverseGeoCodeOption()
					.location(xhlatlng));
		}
	}

	private void getall() {
		if ((zhname.equals("null") && zhtel.equals("null"))
				|| (zhname.equals("") && zhtel.equals(""))) {
			add_start_contact = "";
		} else {
			if (zhname.equals("null") || zhname.equals("")) {
				add_start_contact = "|" + zhtel;
			} else {
				if (zhtel.equals("null") || zhtel.equals("")) {
					add_start_contact = zhname + "|";
				} else {
					add_start_contact = zhname + "|" + zhtel;
				}
			}
		}
		if ((xhname.equals("null") && xhtel.equals("null"))
				|| (xhname.equals("") && xhtel.equals(""))) {
			add_end_contact = "";

		} else {
			if (xhname.equals("null") || xhname.equals("")) {
				add_end_contact = "|" + xhtel;
			} else {
				if (xhtel.equals("null") || xhtel.equals("")) {
					add_end_contact = xhname + "|";
				} else {
					add_end_contact = xhname + "|" + xhtel;
				}
			}
		}
		if ((ztaname.equals("null") && ztatel.equals("null"))
				|| ztaname.equals("") && ztatel.equals("")) {
			midway1_contact = "";
		} else {
			if (ztaname.equals("null") || ztaname.equals("")) {
				midway1_contact = "|" + ztatel;
			} else {
				if (ztatel.equals("null") || ztatel.equals("")) {
					midway1_contact = ztaname + "|";
				} else {
					midway1_contact = ztaname + "|" + ztatel;
				}
			}
		}
		if ((ztbname.equals("null") && ztbtel.equals("null"))
				|| (ztbname.equals("") && ztbtel.equals(""))) {
			midway2_contact = "";
		} else {
			if (ztbname.equals("null") || ztbname.equals("")) {
				midway2_contact = "|" + ztbtel;
			} else {
				if (ztbtel.equals("null") || ztbtel.equals("")) {
					midway2_contact = ztbname + "|";
				} else {
					midway2_contact = ztbname + "|" + ztbtel;
				}
			}
		}
		if ((ztcname.equals("null") && ztctel.equals("null"))
				|| (ztcname.equals("") && ztctel.equals(""))) {
			midway3_contact = "";
		} else {
			if (ztcname.equals("null") || ztcname.equals("")) {
				midway3_contact = "|" + ztctel;
			} else {
				if (ztctel.equals("null") || ztctel.equals("")) {
					midway3_contact = ztcname + "|";
				} else {
					midway3_contact = ztcname + "|" + ztctel;
				}
			}
		}
		if ((ztdname.equals("null") && ztdtel.equals("null"))
				|| (ztdname.equals("") && ztdtel.equals(""))) {
			midway4_contact = "";
		} else {
			if (ztdname.equals("null") || ztdname.equals("")) {
				midway4_contact = "|" + ztdtel;
			} else {
				if (ztdtel.equals("null") || ztdtel.equals("")) {
					midway4_contact = ztdname + "|";
				} else {
					midway4_contact = ztdname + "|" + ztdtel;
				}
			}
		}
		if ((ztename.equals("null") && ztetel.equals("null"))
				|| (ztename.equals("") && ztetel.equals(""))) {
			midway5_contact = "";
		} else {
			if (ztename.equals("null") || ztename.equals("")) {
				midway5_contact = "|" + ztetel;
			} else {
				if (ztetel.equals("null") || ztetel.equals("")) {
					midway5_contact = ztename + "|";
				} else {
					midway5_contact = ztename + "|" + ztetel;
				}
			}
		}
		if (ztadd.equals("null") || ztadd.equals("")) {
			midway1 = "";
		} else {
			if (ztadd.indexOf("&") != -1) {
				midway1 = ztadd + "|" + ztdalon + "|" + ztdalat;
			} else {
				midway1 = ztadd + "&" + "|" + ztdalon + "|" + ztdalat;
			}
		}
		if (ztbdd.equals("null") || ztbdd.equals("")) {
			midway2 = "";
		} else {
			if (ztbdd.indexOf("&") != -1) {
				midway2 = ztbdd + "|" + ztdblon + "|" + ztdblat;
			} else {
				midway2 = ztbdd + "&" + "|" + ztdblon + "|" + ztdblat;
			}
		}
		if (ztcdd.equals("null") || ztcdd.equals("")) {
			midway3 = "";
		} else {
			if (ztcdd.indexOf("&") != -1) {
				midway3 = ztcdd + "|" + ztdclon + "|" + ztdclat;
			} else {
				midway3 = ztcdd + "&" + "|" + ztdclon + "|" + ztdclat;
			}
		}
		if (ztddd.equals("null") || ztddd.equals("")) {
			midway4 = "";
		} else {
			if (ztddd.indexOf("&") != -1) {
				midway4 = ztddd + "|" + ztddlon + "|" + ztddlat;
			} else {
				midway4 = ztddd + "&" + "|" + ztddlon + "|" + ztddlat;
			}

		}
		if (ztedd.equals("null") || ztedd.equals("")) {
			midway5 = "";
		} else {
			if (ztedd.indexOf("&") != -1) {
				midway5 = ztedd + "|" + ztdelon + "|" + ztdelat;
			} else {
				midway5 = ztedd + "&" + "|" + ztdelon + "|" + ztdelat;
			}
		}
		img_url = "";
		if (tp == 0) {
			img_url = "";
		} else {
			if (tp == 1) {
				img_url = hwzpida;
			} else {
				if (tp == 2) {
					img_url = hwzpida + "|" + hwzpidb;
				} else {
					if (tp == 3) {
						img_url = hwzpida + "|" + hwzpidb + "|" + hwzpidc;
					} else {
						img_url = hwzpida + "|" + hwzpidb + "|" + hwzpidc + "|"
								+ hwzpidd;
					}
				}
			}
		}
		if (hwsx.equals(getResources().getString(R.string.nbcp))) {
			hwsxid = 1;
		} else if (hwsx.equals(getResources().getString(R.string.ryp))) {
			hwsxid = 2;
		} else if (hwsx.equals(getResources().getString(R.string.jc))) {
			hwsxid = 3;
		} else if (hwsx.equals(getResources().getString(R.string.juju))) {
			hwsxid = 4;
		} else if (hwsx.equals(getResources().getString(R.string.jydq))) {
			hwsxid = 5;
		} else if (hwsx.equals(getResources().getString(R.string.jxsb))) {
			hwsxid = 6;
		} else if (hwsx.equals(getResources().getString(R.string.dzcp))) {
			hwsxid = 7;
		} else if (hwsx.equals(getResources().getString(R.string.lbj))) {
			hwsxid = 8;
		} else if (hwsx.equals(getResources().getString(R.string.qph))) {
			hwsxid = 9;
		} else if (hwsx.equals(getResources().getString(R.string.yshw))) {
			hwsxid = 10;
		} else if (hwsx.equals(getResources().getString(R.string.hgcp))) {
			hwsxid = 11;
		} else if (hwsx.equals(getResources().getString(R.string.qt))) {
			hwsxid = 12;
		}
		if (bzzl.equals(getResources().getString(R.string.kbbz))) {
			bzzlid = 1;
		} else if (bzzl.equals(getResources().getString(R.string.zxbz))) {
			bzzlid = 2;
		} else if (bzzl.equals(getResources().getString(R.string.mx))) {
			bzzlid = 3;
		} else if (bzzl.equals(getResources().getString(R.string.jx))) {
			bzzlid = 4;
		} else if (bzzl.equals(getResources().getString(R.string.tz))) {
			bzzlid = 5;
		} else if (bzzl.equals(getResources().getString(R.string.sz))) {
			bzzlid = 6;
		} else if (bzzl.equals(getResources().getString(R.string.qt))) {
			bzzlid = 7;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void tjdd() {
		order_type = Ycactivitya.msychsyuyc;
		if (order_type == 0) {
			try {
				HashMap mapa = new HashMap<String, String>();
				mapa.put("key", "get_server_time");
				String jiamia = ToolUtils.JiaMi(mapa);
				String urla = Net.SEVERTIME + jiamia;
				application.doget(urla, new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						showToast(getResources().getString(R.string.wlyc));
						dialoga.dismiss();
					}

					@Override
					public void onSuccess(final ResponseInfo<String> arg0) {
						String systemyear = arg0.result.split("\\ ")[0]
								.split("\\-")[0];
						String systemmonth = arg0.result.split("\\ ")[0]
								.split("\\-")[1];
						String systemday = arg0.result.split("\\ ")[0]
								.split("\\-")[2];
						String systemhour = arg0.result.split("\\ ")[1]
								.split("\\:")[0];
						String systemsecond = arg0.result.split("\\ ")[1]
								.split("\\:")[1];
						String systemmiao = arg0.result.split("\\ ")[1]
								.split("\\:")[2];
						systemtime = systemyear + systemmonth + systemday
								+ systemhour + systemsecond + systemmiao;
						tzpda();
						// Log.e("time>>>", arg0.result);
					}
				});
			} catch (Exception e) {
			}
		} else {
			tzpda();
		}
	}

	private void tzpda() {
		bz = ycbbzet.getText().toString().trim();
		fzrxm = ycbtvc.getText().toString().trim();
		fzrdh = ycbtvd.getText().toString().trim();
		sharedpreferencesc = getSharedPreferences("yball",
				Activity.MODE_PRIVATE);
		editor = sharedpreferencesc.edit();
		editor.putString("bz", bz);
		editor.putString("fzrxm", fzrxm);
		editor.putString("fzrdh", fzrdh);
		editor.putString("hwsx", hwsx);
		editor.putString("bzzl", bzzl);
		editor.putString("shi", String.valueOf(shi));
		editor.putString("xhi", String.valueOf(xhi));
		editor.putString("sli", String.valueOf(sli));
		editor.putString("dt", String.valueOf(dt));
		editor.putString("slcs", String.valueOf(slcs));
		editor.putString("gci", String.valueOf(gci));
		editor.putString("gcrs", String.valueOf(gcrs));
		editor.commit();
		getall();
		if (mytime.equals("00000000000000")) {
			use_time = systemtime.substring(2);
		} else {
			use_time = mytime.substring(2);
		}
		if (fzrxm.equals(getResources().getString(R.string.br))) {
			contacts_name = nickname;
		} else {
			contacts_name = fzrxm;
		}
		if (sfdxtz.equals("1")) {
			is_notify = 1;
		} else {
			is_notify = 0;
		}
		qcslstr = qcsl.split(getResources().getString(R.string.l));
		if (strprovince == null) {
			strprovince = getResources().getString(R.string.gds);
		}
		if (strcity == null) {
			strcity = getResources().getString(R.string.zss);
		}
		if (strprovinceb == null) {
			strprovinceb = getResources().getString(R.string.gds);
		}
		if (strcityb == null) {
			strcityb = getResources().getString(R.string.zss);
		}
		username = phonea;
		pwd = passworda;
		if (zhdd.indexOf("&") != -1) {
			add_start = zhdd + "|" + zhlon + "|" + zhlat;
		} else {
			add_start = zhdd + "&" + "|" + zhlon + "|" + zhlat;
		}
		if (xhdd.indexOf("&") != -1) {
			add_end = xhdd + "|" + xhlon + "|" + xhlat;
		} else {
			add_end = xhdd + "&" + "|" + xhlon + "|" + xhlat;
		}
		request = eyyq;
		cartype = carid;
		car_quantity = Integer.valueOf(qcslstr[0]);
		contacts_mobile = fzrdh;
		goods_type = hwsxid;
		is_up = shi;
		is_down = xhi;
		is_supercargo = gci;
		is_up_floor = sli;
		floor_number = slcs;
		is_lift = dt;
		supercargo_number = gcrs;
		distance = alljulioo;
		startprovince = strprovinceb;
		startcity = strcityb;
		province = strprovince;
		city = strcity;
		pack = String.valueOf(bzzlid);
		remark = bz;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("key", "add_order");
		map.put("username", username);
		map.put("pwd", pwd);
		map.put("use_time", use_time);
		map.put("add_start", add_start);
		map.put("add_end", add_end);
		map.put("midway1", midway1);
		map.put("midway2", midway2);
		map.put("midway3", midway3);
		map.put("midway4", midway4);
		map.put("midway5", midway5);
		map.put("add_start_contact", add_start_contact);
		map.put("add_end_contact", add_end_contact);
		map.put("midway1_contact", midway1_contact);
		map.put("midway2_contact", midway2_contact);
		map.put("midway3_contact", midway3_contact);
		map.put("midway4_contact", midway4_contact);
		map.put("midway5_contact", midway5_contact);
		map.put("request", request);
		map.put("cartype", cartype);
		map.put("car_quantity", car_quantity);
		map.put("contacts_name", contacts_name);
		map.put("contacts_mobile", contacts_mobile);
		map.put("goods_type", goods_type);
		map.put("is_up", is_up);
		map.put("is_down", is_down);
		map.put("is_supercargo", is_supercargo);
		map.put("is_up_floor", is_up_floor);
		map.put("floor_number", floor_number);
		map.put("is_lift", is_lift);
		map.put("supercargo_number", supercargo_number);
		map.put("img_url", img_url);
		map.put("remark", remark);
		map.put("distance", distance);
		map.put("province", province);
		map.put("city", city);
		map.put("start_province", startprovince);
		map.put("start_city", startcity);
		map.put("pack", pack);
		map.put("is_notify", is_notify);
		map.put("order_type", order_type);
		// String json = new Gson().toJson(map);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.myaddordera;
		RequestParams params = new RequestParams();
		params.addBodyParameter(Net.myaddorderb, jiaMi);
		try {

			application.doPost(url, params, new RequestCallBack<String>() {
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					dialoga.dismiss();
					Orderhelpera orderhelpera = GsonUtils.json2Bean(
							arg0.result, Orderhelpera.class);
					if (orderhelpera.status.equals("0")) {
						// showToast("Order Form Success");
						orderamount = orderhelpera.order_amount;
						orderno = orderhelpera.order_no;
						// Log.e("orderno>>>>>", orderno);
						tzpd();
					} else {
						showToast(orderhelpera.status + ":" + orderhelpera.msg);
					}
				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					dialoga.dismiss();
					showToast(getResources().getString(R.string.wlyc));
				}
			});
		} catch (Exception e) {
		}
	}

	private void tzpd() {
		Qrddactivity.qrdd = "ybto";
		intntc = new Intent(Ycactivityb.this, Qrddactivity.class);
		bundlea = new Bundle();
		bundlea.putString("mytime", mytime);
		bundlea.putString("xzdsj", xzdsj);
		bundlea.putString("eyyq", eyyq);
		bundlea.putString("qcmc", qcmc);
		bundlea.putString("qcdw", qcdw);
		bundlea.putString("qcgg", qcgg);
		bundlea.putString("qcsl", qcsl);
		bundlea.putInt("carid", carid);
		bundlea.putString("alljulioo", alljulioo);
		bundlea.putString("qczhl", qczhl);
		bundlea.putString("ztdalat", ztdalat);
		bundlea.putString("ztdalon", ztdalon);
		bundlea.putString("ztdblat", ztdblat);
		bundlea.putString("ztdblon", ztdblon);
		bundlea.putString("ztdclat", ztdclat);
		bundlea.putString("ztdclon", ztdclon);
		bundlea.putString("ztddlat", ztddlat);
		bundlea.putString("ztddlon", ztddlon);
		bundlea.putString("ztdelat", ztdelat);
		bundlea.putString("ztdelon", ztdelon);
		bundlea.putString("zhlat", zhlat);
		bundlea.putString("zhlon", zhlon);
		bundlea.putString("xhlat", xhlat);
		bundlea.putString("xhlon", xhlon);
		bundlea.putString("zhdd", zhdd);
		bundlea.putString("zhname", zhname);
		bundlea.putString("zhtel", zhtel);
		bundlea.putString("xhdd", xhdd);
		bundlea.putString("xhname", xhname);
		bundlea.putString("xhtel", xhtel);
		bundlea.putString("ztadd", ztadd);
		bundlea.putString("ztbdd", ztbdd);
		bundlea.putString("ztcdd", ztcdd);
		bundlea.putString("ztddd", ztddd);
		bundlea.putString("ztedd", ztedd);
		bundlea.putString("ztaname", ztaname);
		bundlea.putString("ztbname", ztbname);
		bundlea.putString("ztcname", ztcname);
		bundlea.putString("ztdname", ztdname);
		bundlea.putString("ztename", ztename);
		bundlea.putString("ztatel", ztatel);
		bundlea.putString("ztbtel", ztbtel);
		bundlea.putString("ztctel", ztctel);
		bundlea.putString("ztdtel", ztdtel);
		bundlea.putString("ztetel", ztetel);
		bundlea.putString("fzrxm", fzrxm);
		bundlea.putString("fzrdh", fzrdh);
		bundlea.putString("hwsx", hwsx);
		bundlea.putString("bzzl", bzzl);
		bundlea.putInt("shi", shi);
		bundlea.putInt("xhi", xhi);
		bundlea.putInt("gci", gci);
		bundlea.putInt("gcrs", gcrs);
		bundlea.putInt("slcs", slcs);
		bundlea.putInt("sli", sli);
		bundlea.putInt("dt", dt);
		bundlea.putString("orderamount", orderamount);
		bundlea.putString("orderno", orderno);
		bundlea.putString("use_time", use_time);
		bundlea.putString("add_start", add_start);
		bundlea.putString("add_end", add_end);
		bundlea.putString("midway1", midway1);
		bundlea.putString("midway2", midway2);
		bundlea.putString("midway3", midway3);
		bundlea.putString("midway4", midway4);
		bundlea.putString("midway5", midway5);
		bundlea.putString("add_start_contact", add_start_contact);
		bundlea.putString("add_end_contact", add_end_contact);
		bundlea.putString("midway1_contact", midway1_contact);
		bundlea.putString("midway2_contact", midway2_contact);
		bundlea.putString("midway3_contact", midway3_contact);
		bundlea.putString("midway4_contact", midway4_contact);
		bundlea.putString("midway5_contact", midway5_contact);
		bundlea.putString("request", request);
		bundlea.putInt("cartype", cartype);
		bundlea.putInt("car_quantity", car_quantity);
		bundlea.putString("contacts_name", contacts_name);
		bundlea.putString("contacts_mobile", contacts_mobile);
		bundlea.putInt("goods_type", goods_type);
		bundlea.putInt("is_up", is_up);
		bundlea.putInt("is_down", is_down);
		bundlea.putInt("is_supercargo", is_supercargo);
		bundlea.putInt("is_up_floor", is_up_floor);
		bundlea.putInt("floor_number", floor_number);
		bundlea.putInt("is_lift", is_lift);
		bundlea.putInt("supercargo_number", supercargo_number);
		bundlea.putString("img_url", img_url);
		bundlea.putString("remark", remark);
		bundlea.putString("distance", distance);
		bundlea.putString("province", province);
		bundlea.putString("city", city);
		bundlea.putString("pack", pack);
		bundlea.putInt("is_notify", is_notify);
		bundlea.putInt("order_type", order_type);
		intntc.putExtras(bundlea);
		startActivity(intntc);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.ycbbtnxyba:
			if (hwsx.equals("null")) {
				ycbhuotva.setError(getResources().getString(
						R.string.qxzhwsxjbzzl));
				showToast(getResources().getString(R.string.qxzhwsxjbzzl));
			} else {
				// ybsctp();
				if (uria != null) {
					dialoga.show();
					Myasynctaska myasynctaska = new Myasynctaska();
					myasynctaska.execute("100");
				} else {
					dialoga.show();
					tjdd();
				}
				// dialoga.show();
				// new Handler().postDelayed(new Runnable() {
				// @Override
				// public void run() {
				// // if (c == 1) {
				// // dismissProgressDialog();
				// // } else {
				// tjdd();
				// // }
				// }
				// }, 5000);
			}
			break;
		case R.id.ycbrla:
			adb = "hello";
			finish();
			break;
		case R.id.ycbrlb:
			adb = "hello";
			lxra = 1;
			startActivityForResult(new Intent(Ycactivityb.this, Lxrlb.class), 0);
			break;
		case R.id.ycbrlc:
			Mydialogh mydialogh = new Mydialogh(Ycactivityb.this, kbbz, zxbz,
					mx, jx, tz, sz, qt, nbcp, ryp, jc, juju, jydq, jxsb, dzcp,
					lbj, qph, yshw, hgcp);
			mydialogh.setDialogCallbackh(mydialoghdissmiss);
			mydialogh.show();
			break;
		case R.id.ycbslrl:
			if (sli == 0) {
				Mydialogj mydialogj = new Mydialogj(Ycactivityb.this, qsrlc,
						srdlcsbzq);
				mydialogj.setDialogCallbackj(mydialogjdissmiss);
				mydialogj.show();
			} else if (sli == 1) {
				ycbsliv.setBackgroundResource(sl[1]);
				ycbsltv.setTextColor(Color.parseColor("#AEAEAE"));
				sli = 0;
			}
			break;
		case R.id.ycbshrl:
			if (shi == 0) {
				ycbshiv.setBackgroundResource(sh[0]);
				ycbshtv.setTextColor(Color.BLACK);
				shi = 1;
			} else if (shi == 1) {
				ycbshiv.setBackgroundResource(sh[1]);
				ycbshtv.setTextColor(Color.parseColor("#AEAEAE"));
				shi = 0;
			}
			break;
		case R.id.ycbxhrl:
			if (xhi == 0) {
				ycbxhiv.setBackgroundResource(xh[0]);
				ycbxhtv.setTextColor(Color.BLACK);
				xhi = 1;
			} else if (xhi == 1) {
				ycbxhiv.setBackgroundResource(xh[1]);
				ycbxhtv.setTextColor(Color.parseColor("#AEAEAE"));
				xhi = 0;
			}
			break;
		case R.id.ycbgcrl:
			if (gci == 0) {
				Mydialogi mydialogi = new Mydialogi(Ycactivityb.this, qsrgcrs,
						srdgcrsbzq);
				mydialogi.setDialogCallbacki(mydialogidissmiss);
				mydialogi.show();
			} else if (gci == 1) {
				ycbgciv.setBackgroundResource(gc[1]);
				ycbgctv.setTextColor(Color.parseColor("#AEAEAE"));
				gci = 0;
			}
			break;
		}

	}

	Dialogcallbackj mydialogjdissmiss = new Dialogcallbackj() {
		@Override
		public void dialogdoj(int a, String b) {
			dt = a;
			slcs = Integer.valueOf(b);
			ycbsliv.setBackgroundResource(sl[0]);
			ycbsltv.setTextColor(Color.BLACK);
			sli = 1;

		}
	};
	Dialogcallbacki mydialogidissmiss = new Dialogcallbacki() {
		@Override
		public void dialogdoi(String a) {
			gcrs = Integer.valueOf(a);
			ycbgciv.setBackgroundResource(gc[0]);
			ycbgctv.setTextColor(Color.BLACK);
			gci = 1;
		}
	};
	Dialogcallbackh mydialoghdissmiss = new Dialogcallbackh() {
		@Override
		public void dialogdoh(String a, String b) {
			hwsx = b;
			bzzl = a;
			ycbhuotva.setVisibility(View.GONE);
			yvbhuotvb.setText(b);
			yvbhuotvc.setText(getResources().getString(R.string.bzzl) + "		"
					+ a);
		}
	};
	OnGetGeoCoderResultListener ongeob = new OnGetGeoCoderResultListener() {

		@Override
		public void onGetGeoCodeResult(GeoCodeResult arg0) {
		}

		@Override
		public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
			if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
				strcityb = arg0.getAddressDetail().city;
				strprovinceb = arg0.getAddressDetail().province;
				return;
			}
		}

	};
	OnGetGeoCoderResultListener ongeoa = new OnGetGeoCoderResultListener() {

		@Override
		public void onGetGeoCodeResult(GeoCodeResult arg0) {
		}

		@Override
		public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
			if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
				strcity = arg0.getAddressDetail().city;
				strprovince = arg0.getAddressDetail().province;
				return;
			}
		}

	};

	public class Gridadapter extends BaseAdapter {
		private LayoutInflater inflater;
		private int selectedPosition = -1;
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public Gridadapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public int getCount() {
			if (Bimp.tempSelectBitmap.size() == 4) {
				return 4;
			}
			return (Bimp.tempSelectBitmap.size() + 1);
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.ybgvitem, parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.itemiv);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (position == Bimp.tempSelectBitmap.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.zxa));
				if (position == 4) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position)
						.getBitmap());
			}

			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
		}

		@SuppressLint("HandlerLeak")
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					gridadapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if (arg2 == Bimp.tempSelectBitmap.size()) {
			Mydialogk mydialogk = new Mydialogk(Ycactivityb.this);
			mydialogk.setDialogCallbackk(mydialogkdissmiss);
			mydialogk.show();
		} else {
			Bimp.tempSelectBitmap.remove(arg2);
			ycbgv.setAdapter(gridadapter);
		}
	}
}