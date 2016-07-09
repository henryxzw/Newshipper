package com.femto.shipper.activitya;

import java.util.HashMap;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

public class Wdlxadd extends BaseActivity implements OnItemClickListener,
		OnItemLongClickListener {
	private AlertDialog.Builder builder;
	private int intid, zhid, ztdsl = 0, bcztqcgs, bczthgjg, bcztbswl, bcztdwb,
			bcztdcm, bcztscm;
	private Wdlxsqlhelper sh;
	private Cursor cursor = null, cursora = null;
	private String oldtimestamp, newtimestamp, start, ztda, ztdb, ztdc, ztdd,
			ztde, end, startb, ztdab, ztdbb, ztdcb, ztddb, ztdeb, endb, id,
			getid, startadd, startname, starttel, startlat, startlon, ztdaadd,
			ztdaname, ztdatel, ztdalan, ztdalon, ztdbadd, ztdbname, ztdbtel,
			ztdcadd, ztdcname, ztdctel, ztddadd, ztddname, ztddtel, ztdeadd,
			ztdename, ztdetel, endadd, endname, endtel, endlat, endlon,
			ztdblan, ztdblon, eyyq, strycatvb, ztdclan, ztdclon, ztddlan,
			ztddlon, ztdelan, phonea, passworda, mytime, xzdsj, ztdelon;
	private String strstart[] = new String[3], strztda[] = new String[3],
			bstrztda[] = new String[3], strztdb[] = new String[3],
			bstrztdb[] = new String[3], bstrztdc[] = new String[3],
			strztdc[] = new String[3], strztdd[] = new String[3],
			bstrztdd[] = new String[3], bstrztde[] = new String[3],
			strztde[] = new String[3], strend[] = new String[3],
			strstartb[] = new String[3], strztdab[] = new String[3],
			strztdbb[] = new String[3], strztdcb[] = new String[3],
			strztdeb[] = new String[3], strztddb[] = new String[3],
			strstartbb[] = new String[3], strendb[] = new String[3],
			strendbb[] = new String[3];
	private ListView wdlxlva;
	private SharedPreferences sharedPreferences;
	private Mysimplecursoradapter mysimplecursoradapter;
	private SharedPreferences sharedpreferences;
	private Editor editor = null;
	private Intent intenta;
	private Bundle bundlea, bundleb;
	private RelativeLayout wdlxrla;
	private TextView wdlxtstv;
	private SQLiteDatabase db;

	@Override
	protected void onDestroy() {
		db.close();
		sh.close();
		if (editor != null) {
			editor.clear();
		}
		if (cursor != null) {
			cursor.close();
		}
		if (cursora != null) {
			cursora.close();
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wdlxadd);
		wdlxtstv = (TextView) findViewById(R.id.wdlxtstv);
		wdlxlva = (ListView) findViewById(R.id.wdlxlva);
		wdlxrla = (RelativeLayout) findViewById(R.id.wdlxrla);
		sharedPreferences = getSharedPreferences("spinnera",
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString("name", "");
		passworda = sharedPreferences.getString("pwd", "");
		sh = new Wdlxsqlhelper(this);
		cursor = sh.select();
		db = sh.getWritableDatabase();
		mysimplecursoradapter = new Mysimplecursoradapter(this,
				R.layout.wdlxaddlistview, cursor, new String[] { "startadd",
						"startname", "starttel", "ztdaadd", "ztdaname",
						"ztdatel", "ztdbadd", "ztdbname", "ztdbtel", "ztdcadd",
						"ztdcname", "ztdctel", "ztddadd", "ztddname",
						"ztddtel", "ztdeadd", "ztdename", "ztdetel", "endadd",
						"endname", "endtel" }, new int[] { R.id.wdlxstartaddtv,
						R.id.wdlxstartnametv, R.id.wdlxstartteltv,
						R.id.wdlxztaaddtv, R.id.wdlxztanametv,
						R.id.wdlxztateltv, R.id.wdlxztbaddtv,
						R.id.wdlxztbnametv, R.id.wdlxztbteltv,
						R.id.wdlxztcaddtv, R.id.wdlxztcnametv,
						R.id.wdlxztcteltv, R.id.wdlxztdaddtv,
						R.id.wdlxztdnametv, R.id.wdlxztdteltv,
						R.id.wdlxzteaddtv, R.id.wdlxztenametv,
						R.id.wdlxzteteltv, R.id.wdlxendaddtv,
						R.id.wdlxendnametv, R.id.wdlxendteltv });
		wdlxlva.setAdapter(mysimplecursoradapter);
		try {
			get();
		} catch (Exception e) {
			showToast("sorry");
		}
		getbundle();
		wdlxlva.setOnItemClickListener(this);
		wdlxlva.setOnItemLongClickListener(this);
		wdlxrla.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		cursora = db.query("wdlxadda", null, null, null, null, null, null);
		if (cursora.moveToFirst()) {
			wdlxtstv.setVisibility(View.GONE);
		}
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
				convertview = layoutinflater.inflate(R.layout.wdlxaddlistview,
						null);
			} else {
				convertview = view;
			}
			// ImageView wdlxiva = (ImageView)
			// convertview.findViewById(R.id.wdlxiva);
			LinearLayout ztdlla = (LinearLayout) convertview
					.findViewById(R.id.ztdlla);
			LinearLayout ztdllb = (LinearLayout) convertview
					.findViewById(R.id.ztdllb);
			LinearLayout ztdllc = (LinearLayout) convertview
					.findViewById(R.id.ztdllc);
			LinearLayout ztdlld = (LinearLayout) convertview
					.findViewById(R.id.ztdlld);
			LinearLayout ztdlle = (LinearLayout) convertview
					.findViewById(R.id.ztdlle);
			if (cursor.getString(cursor.getColumnIndex("ztdaadd")).equals("")
					|| cursor.getString(cursor.getColumnIndex("ztdaadd")) == null) {
				// wdlxiva.setBackgroundResource(R.drawable.dian2);
				ztdlla.setVisibility(View.GONE);
				ztdllb.setVisibility(View.GONE);
				ztdllc.setVisibility(View.GONE);
				ztdlld.setVisibility(View.GONE);
				ztdlle.setVisibility(View.GONE);
			} else {
				if (cursor.getString(cursor.getColumnIndex("ztdbadd")).equals(
						"")
						|| cursor.getString(cursor.getColumnIndex("ztdbadd")) == null) {
					// wdlxiva.setBackgroundResource(R.drawable.dian3);
					ztdlla.setVisibility(View.VISIBLE);
					ztdllb.setVisibility(View.GONE);
					ztdllc.setVisibility(View.GONE);
					ztdlld.setVisibility(View.GONE);
					ztdlle.setVisibility(View.GONE);
				} else {
					if (cursor.getString(cursor.getColumnIndex("ztdcadd"))
							.equals("")
							|| cursor.getString(cursor
									.getColumnIndex("ztdcadd")) == null) {
						// wdlxiva.setBackgroundResource(R.drawable.dian4);
						ztdlla.setVisibility(View.VISIBLE);
						ztdllb.setVisibility(View.VISIBLE);
						ztdllc.setVisibility(View.GONE);
						ztdlld.setVisibility(View.GONE);
						ztdlle.setVisibility(View.GONE);
					} else {
						if (cursor.getString(cursor.getColumnIndex("ztddadd"))
								.equals("")
								|| cursor.getString(cursor
										.getColumnIndex("ztddadd")) == null) {
							// wdlxiva.setBackgroundResource(R.drawable.dian5);
							ztdlla.setVisibility(View.VISIBLE);
							ztdllb.setVisibility(View.VISIBLE);
							ztdllc.setVisibility(View.VISIBLE);
							ztdlld.setVisibility(View.GONE);
							ztdlle.setVisibility(View.GONE);
						} else {
							if (cursor.getString(
									cursor.getColumnIndex("ztdeadd"))
									.equals("")
									|| cursor.getString(cursor
											.getColumnIndex("ztdeadd")) == null) {

								// wdlxiva.setBackgroundResource(R.drawable.dian6);
								ztdlla.setVisibility(View.VISIBLE);
								ztdllb.setVisibility(View.VISIBLE);
								ztdllc.setVisibility(View.VISIBLE);
								ztdlld.setVisibility(View.VISIBLE);
								ztdlle.setVisibility(View.GONE);
							} else {
								// wdlxiva.setBackgroundResource(R.drawable.dian7);
								ztdlla.setVisibility(View.VISIBLE);
								ztdllb.setVisibility(View.VISIBLE);
								ztdllc.setVisibility(View.VISIBLE);
								ztdlld.setVisibility(View.VISIBLE);
								ztdlle.setVisibility(View.VISIBLE);
							}
						}
					}
				}
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	private void getbundle() {
		bundleb = new Bundle();
		bundleb = getIntent().getExtras();
		mytime = bundleb.getString("mytime");
		xzdsj = bundleb.getString("xzdsj");
		eyyq = bundleb.getString("eyyq");
		strycatvb = bundleb.getString("strycatvb");
		bcztqcgs = bundleb.getInt("bcztqcgs");
		bczthgjg = bundleb.getInt("bczthgjg");
		bcztbswl = bundleb.getInt("bcztbswl");
		bcztdwb = bundleb.getInt("bcztdwb");
		bcztdcm = bundleb.getInt("bcztdcm");
		bcztscm = bundleb.getInt("bcztscm");
	}

	private void get() {
		showProgressDialog(getResources().getString(R.string.jzz));
		sharedpreferences = getSharedPreferences("spinnerawllxtimestamp",
				Activity.MODE_PRIVATE);
		oldtimestamp = sharedpreferences.getString("timestamp", "").trim();
		if (oldtimestamp == null || oldtimestamp.equals("")) {
			oldtimestamp = "0";
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "my_line");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("timestamp", oldtimestamp);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.cyadd + jiaMi;
		application.doget(url, new RequestCallBack<String>() {
			@SuppressWarnings("deprecation")
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				Cyhelper cy = GsonUtils.json2Bean(arg0.result, Cyhelper.class);
				if (cy.status.equals("0")) {
					sh.deletehi();
					for (int i = 0; i < cy.list.size(); i++) {
						id = cy.list.get(i).id;
						start = cy.list.get(i).start_addr.trim();
						startb = cy.list.get(i).start_addr_contact.trim();
						end = cy.list.get(i).end_addr.trim();
						endb = cy.list.get(i).end_addr_contact.trim();
						ztda = cy.list.get(i).middle_addr1.trim();
						ztdab = cy.list.get(i).middle_addr1_contact.trim();
						ztdb = cy.list.get(i).middle_addr2.trim();
						ztdbb = cy.list.get(i).middle_addr2_contact.trim();
						ztdc = cy.list.get(i).middle_addr3.trim();
						ztdcb = cy.list.get(i).middle_addr3_contact.trim();
						ztdd = cy.list.get(i).middle_addr4.trim();
						ztddb = cy.list.get(i).middle_addr4_contact.trim();
						ztde = cy.list.get(i).middle_addr5.trim();
						ztdeb = cy.list.get(i).middle_addr5_contact.trim();
						strstart = start.split("\\|");
						strend = end.split("\\|");
						if (startb.equals("")) {
							strstartb[0] = "";
							strstartb[1] = "";
						} else {
							strstartbb = startb.split("\\|");
							if (strstartbb.length < 2) {
								String a = startb.substring(0, 1);
								if (a.equals("|")) {
									strstartb[0] = "";
									strstartb[1] = startb.substring(1);
								} else {
									strstartb[0] = strstartbb[0];
									strstartb[1] = "";
								}
							} else {
								strstartb[0] = strstartbb[0];
								strstartb[1] = strstartbb[1];
							}
						}
						if (endb.equals("")) {
							strendb[0] = "";
							strendb[1] = "";
						} else {
							strendbb = endb.split("\\|");
							if (strendbb.length < 2) {
								String a = endb.substring(0, 1);
								if (a.equals("|")) {
									strendb[0] = "";
									strendb[1] = endb.substring(1);
								} else {
									strendb[0] = strendbb[0];
									strendb[1] = "";
								}
							} else {
								strendb[0] = strendbb[0];
								strendb[1] = strendbb[1];
							}
						}
						if (ztda.equals("")) {
							strztda[0] = "";
							strztda[1] = "";
							strztda[2] = "";
						} else {
							strztda = ztda.split("\\|");
						}

						if (ztdb.equals("")) {
							strztdb[0] = "";
							strztdb[1] = "";
							strztdb[2] = "";
						} else {
							strztdb = ztdb.split("\\|");
						}
						if (ztdc.equals("")) {
							strztdc[0] = "";
							strztdc[1] = "";
							strztdc[2] = "";
						} else {
							strztdc = ztdc.split("\\|");
						}
						if (ztdd.equals("")) {
							strztdd[0] = "";
							strztdd[1] = "";
							strztdd[2] = "";
						} else {
							strztdd = ztdd.split("\\|");
						}
						if (ztde.equals("")) {
							strztde[0] = "";
							strztde[1] = "";
							strztde[2] = "";
						} else {
							strztde = ztde.split("\\|");
						}
						if (ztdab.equals("")) {
							strztdab[0] = "";
							strztdab[1] = "";
						} else {
							bstrztda = ztdab.split("\\|");
							if (bstrztda.length < 2) {
								String a = ztdab.substring(0, 1);
								if (a.equals("|")) {
									strztdab[0] = "";
									strztdab[1] = ztdab.substring(1);
								} else {
									strztdab[0] = bstrztda[0];
									strztdab[1] = "";
								}
							} else {
								strztdab[0] = bstrztda[0];
								strztdab[1] = bstrztda[1];
							}
						}
						if (ztdbb.equals("")) {
							strztdbb[0] = "";
							strztdbb[1] = "";
						} else {
							bstrztdb = ztdbb.split("\\|");
							if (bstrztdb.length < 2) {
								String a = ztdbb.substring(0, 1);
								if (a.equals("|")) {
									strztdbb[0] = "";
									strztdbb[1] = ztdbb.substring(1);
								} else {
									strztdbb[0] = bstrztdb[0];
									strztdbb[1] = "";
								}
							} else {
								strztdbb[0] = bstrztdb[0];
								strztdbb[1] = bstrztdb[1];
							}
						}
						if (ztdcb.equals("")) {
							strztdcb[0] = "";
							strztdcb[1] = "";
						} else {
							bstrztdc = ztdcb.split("\\|");
							if (bstrztdc.length < 2) {
								String a = ztdcb.substring(0, 1);
								if (a.equals("|")) {
									strztdcb[0] = "";
									strztdcb[1] = ztdcb.substring(1);
								} else {
									strztdcb[0] = bstrztdc[0];
									strztdcb[1] = "";
								}
							} else {
								strztdcb[0] = bstrztdc[0];
								strztdcb[1] = bstrztdc[1];
							}
						}
						if (ztddb.equals("")) {
							strztddb[0] = "";
							strztddb[1] = "";
						} else {
							bstrztdd = ztddb.split("\\|");
							if (bstrztdd.length < 2) {
								String a = ztddb.substring(0, 1);
								if (a.equals("|")) {
									strztddb[0] = "";
									strztddb[1] = ztddb.substring(1);
								} else {
									strztddb[0] = bstrztdd[0];
									strztddb[1] = "";
								}
							} else {
								strztddb[0] = bstrztdd[0];
								strztddb[1] = bstrztdd[1];
							}
						}
						if (ztdeb.equals("")) {
							strztdeb[0] = "";
							strztdeb[1] = "";
						} else {
							bstrztde = ztdeb.split("\\|");
							if (bstrztde.length < 2) {
								String a = ztdeb.substring(0, 1);
								if (a.equals("|")) {
									strztdeb[0] = "";
									strztdeb[1] = ztdeb.substring(1);
								} else {
									strztdeb[0] = bstrztde[0];
									strztdeb[1] = "";
								}
							} else {
								strztdeb[0] = bstrztde[0];
								strztdeb[1] = bstrztde[1];
							}
						}
						sh.insert(id, strstart[0], strstartb[0], strstartb[1],
								strstart[2], strstart[1], strztda[0],
								strztdab[0], strztdab[1], strztda[2],
								strztda[1], strztdb[0], strztdbb[0],
								strztdbb[1], strztdb[2], strztdb[1],
								strztdc[0], strztdcb[0], strztdcb[1],
								strztdc[2], strztdc[1], strztdd[0],
								strztddb[0], strztddb[1], strztdd[2],
								strztdd[1], strztde[0], strztdeb[0],
								strztdeb[1], strztde[2], strztde[1], strend[0],
								strendb[0], strendb[1], strend[2], strend[1]);
						cursor.requery();
						cursora = db.query("wdlxadda", null, null, null, null,
								null, null);
						if (cursora.moveToFirst()) {
							wdlxtstv.setVisibility(View.GONE);
						}
					}
					newtimestamp = cy.timestamp;
					sharedpreferences = getSharedPreferences(
							"spinnerawllxtimestamp", Activity.MODE_PRIVATE);
					editor = sharedpreferences.edit();
					editor.putString("timestamp", newtimestamp);
					editor.commit();
				} else if (cy.status.equals("200")) {
					cursor.requery();
				} else {
					showToast(cy.status + "word");
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				dismissProgressDialog();
				showToast(getResources().getString(R.string.wlyc));
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				if (isUploading) {
					// showToast("upload: " + current + "/" + total);
				} else {
					// showToast("reply: " + current + "/" + total);
				}
				super.onLoading(total, current, isUploading);
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		startadd = cursor.getString(2).trim();
		startname = cursor.getString(3).trim();
		starttel = cursor.getString(4).trim();
		startlat = cursor.getString(5).trim();
		startlon = cursor.getString(6).trim();
		ztdaadd = cursor.getString(7).trim();
		ztdaname = cursor.getString(8).trim();
		ztdatel = cursor.getString(9).trim();
		ztdalan = cursor.getString(10).trim();
		ztdalon = cursor.getString(11).trim();
		ztdbadd = cursor.getString(12).trim();
		ztdbname = cursor.getString(13).trim();
		ztdbtel = cursor.getString(14).trim();
		ztdblan = cursor.getString(15).trim();
		ztdblon = cursor.getString(16).trim();
		ztdcadd = cursor.getString(17).trim();
		ztdcname = cursor.getString(18).trim();
		ztdctel = cursor.getString(19).trim();
		ztdclan = cursor.getString(20).trim();
		ztdclon = cursor.getString(21).trim();
		ztddadd = cursor.getString(22).trim();
		ztddname = cursor.getString(23).trim();
		ztddtel = cursor.getString(24).trim();
		ztddlan = cursor.getString(25).trim();
		ztddlon = cursor.getString(26).trim();
		ztdeadd = cursor.getString(27).trim();
		ztdename = cursor.getString(28).trim();
		ztdetel = cursor.getString(29).trim();
		ztdelan = cursor.getString(30).trim();
		ztdelon = cursor.getString(31).trim();
		endadd = cursor.getString(32).trim();
		endname = cursor.getString(33).trim();
		endtel = cursor.getString(34).trim();
		endlat = cursor.getString(35).trim();
		endlon = cursor.getString(36).trim();
		if (ztdaadd.equals("")) {
			ztdsl = 0;
			ztdaadd = "null";
			ztdalan = "null";
			ztdalon = "null";
			ztdblan = "null";
			ztdblon = "null";
			ztdclan = "null";
			ztdclon = "null";
			ztddlan = "null";
			ztddlon = "null";
			ztdelan = "null";
			ztdelon = "null";
			ztdbadd = "null";
			ztdcadd = "null";
			ztddadd = "null";
			ztdeadd = "null";
		} else {
			if (ztdbadd.equals("")) {
				ztdsl = 1;
				ztdbadd = "null";
				ztdblan = "null";
				ztdblon = "null";
				ztdclan = "null";
				ztdclon = "null";
				ztddlan = "null";
				ztddlon = "null";
				ztdelan = "null";
				ztdelon = "null";
				ztdcadd = "null";
				ztddadd = "null";
				ztdeadd = "null";
			} else {
				if (ztdcadd.equals("")) {
					ztdsl = 2;
					ztdcadd = "null";
					ztdclan = "null";
					ztdclon = "null";
					ztddlan = "null";
					ztddlon = "null";
					ztdelan = "null";
					ztdelon = "null";
					ztddadd = "null";
					ztdeadd = "null";
				} else {
					if (ztddadd.equals("")) {
						ztdsl = 3;
						ztddadd = "null";
						ztddlan = "null";
						ztddlon = "null";
						ztdelan = "null";
						ztdelon = "null";
						ztdeadd = "null";
					} else {
						if (ztdeadd.equals("")) {
							ztdeadd = "null";
							ztdelan = "null";
							ztdelon = "null";
							ztdsl = 4;
						} else {
							ztdsl = 5;
						}
					}
				}
			}
		}
		if (startname.equals("")) {
			startname = "null";
		}
		if (starttel.equals("")) {
			starttel = "null";
		}
		if (endname.equals("")) {
			endname = "null";
		}
		if (endtel.equals("")) {
			endtel = "null";
		}
		if (ztdaname.equals("")) {
			ztdaname = "null";
		}
		if (ztdatel.equals("")) {
			ztdatel = "null";
		}
		if (ztdbname.equals("")) {
			ztdbname = "null";
		}
		if (ztdbtel.equals("")) {
			ztdbtel = "null";
		}
		if (ztdcname.equals("")) {
			ztdcname = "null";
		}
		if (ztdctel.equals("")) {
			ztdctel = "null";
		}
		if (ztddname.equals("")) {
			ztddname = "null";
		}
		if (ztddtel.equals("")) {
			ztddtel = "null";
		}
		if (ztdename.equals("")) {
			ztdename = "null";
		}
		if (ztdetel.equals("")) {
			ztdetel = "null";
		}
		Ycactivitya.ada = "wdlx";
		intenta = new Intent(Wdlxadd.this, Ycactivitya.class);
		bundlea = new Bundle();
		bundlea.putString("zhdd", startadd);
		bundlea.putString("zhname", startname);
		bundlea.putString("zhtel", starttel);
		bundlea.putString("zhlat", startlat);
		bundlea.putString("zhlon", startlon);
		bundlea.putString("ztadd", ztdaadd);
		bundlea.putString("ztbdd", ztdbadd);
		bundlea.putString("ztcdd", ztdcadd);
		bundlea.putString("ztddd", ztddadd);
		bundlea.putString("ztedd", ztdeadd);
		bundlea.putString("ztaname", ztdaname);
		bundlea.putString("ztbname", ztdbname);
		bundlea.putString("ztcname", ztdcname);
		bundlea.putString("ztdname", ztddname);
		bundlea.putString("ztename", ztdename);
		bundlea.putString("ztatel", ztdatel);
		bundlea.putString("ztbtel", ztdbtel);
		bundlea.putString("ztctel", ztdctel);
		bundlea.putString("ztdtel", ztddtel);
		bundlea.putString("ztetel", ztdetel);
		bundlea.putString("ztdalat", ztdalan);
		bundlea.putString("ztdblat", ztdblan);
		bundlea.putString("ztdclat", ztdclan);
		bundlea.putString("ztddlat", ztddlan);
		bundlea.putString("ztdelat", ztdelan);
		bundlea.putString("ztdalon", ztdalon);
		bundlea.putString("ztdblon", ztdblon);
		bundlea.putString("ztdclon", ztdclon);
		bundlea.putString("ztddlon", ztddlon);
		bundlea.putString("ztdelon", ztdelon);
		bundlea.putString("xhdd", endadd);
		bundlea.putString("xhname", endname);
		bundlea.putString("xhtel", endtel);
		bundlea.putString("xhlat", endlat);
		bundlea.putString("xhlon", endlon);
		bundlea.putString("mytime", mytime);
		bundlea.putString("xzdsj", xzdsj);
		bundlea.putInt("bcztqcgs", bcztqcgs);
		bundlea.putInt("bczthgjg", bczthgjg);
		bundlea.putInt("bcztbswl", bcztbswl);
		bundlea.putInt("bcztdwb", bcztdwb);
		bundlea.putInt("bcztdcm", bcztdcm);
		bundlea.putInt("bcztscm", bcztscm);
		bundlea.putInt("ztdsl", ztdsl);
		bundlea.putString("eyyq", eyyq);
		bundlea.putString("strycatvb", strycatvb);
		intenta.putExtras(bundlea);
		Ycactivitya.ycactivitya.finish();
		Wdlxadd.this.finish();
		startActivity(intenta);
		// showToast(startlat + "," + startlon + "," + endlat + "," + endlon);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		dialog();
		return true;
	}

	private void dialog() {
		builder = new Builder(Wdlxadd.this);
		builder.setMessage(getResources().getString(R.string.qdsccjl));
		builder.setTitle(getResources().getString(R.string.wxts));
		builder.setPositiveButton(getResources().getString(R.string.qd),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						intid = cursor.getInt(0);
						getid = cursor.getString(1);
						zhid = Integer.valueOf(getid);
						deleteid();
					}
				});
		builder.setNegativeButton(getResources().getString(R.string.qx),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				});
		builder.create().show();
	}

	private void deleteid() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("key", "delete_my_line");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("line_id", zhid);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.cyadd + jiaMi;
		application.doGet(url, new RequestCallBack<String>() {
			@SuppressWarnings("deprecation")
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Cyhelper cy = GsonUtils.json2Bean(arg0.result, Cyhelper.class);
				if (cy.status.equals("0")) {
					sh.delete(intid);
					showToast(getResources().getString(R.string.sccg));
					cursora = db.query("wdlxadda", null, null, null, null,
							null, null);
					if (cursora.moveToFirst()) {
						wdlxtstv.setVisibility(View.GONE);
					} else {
						wdlxtstv.setVisibility(View.VISIBLE);
					}
					cursor.requery();
					builder.create().dismiss();
				} else {
					showToast(getResources().getString(R.string.scsb));
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast(getResources().getString(R.string.wlyc));
			}
		});

	}
}
