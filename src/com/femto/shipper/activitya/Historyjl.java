package com.femto.shipper.activitya;

import com.femto.shipper.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Historyjl extends Activity implements OnClickListener {
	private ImageButton histimbtna, histrotyimbtnb;
	private SimpleCursorAdapter cursorAdapter;
	private Shelperhistory sh;
	public static Cursor cursor;
	private ListView lv;
	private static int ida;
	private static String address, name, tel;
	private static final String keyb = "address";
	private static final String keyc = "name";
	private static final String keyd = "tel";
	private Button killhistorybtn;
	private Bundle bundlea, bundleb;
	private Intent inta;
	private String zhdd, zhname, zhtel, xhdd, xhname, xhtel, ztadd, ztaname,
			ztatel, ztbdd, ztbname, ztbtel, ztcdd, ztcname, ztctel, ztddd,
			ztdname, ztdtel, ztedd, ztename, ztetel, djzx,ycatvastr;
	private int ztdsl;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.historyjl);
		histrotyimbtnb = (ImageButton) findViewById(R.id.histrotyimbtnb);
		histimbtna = (ImageButton) findViewById(R.id.histimbtna);
		killhistorybtn = (Button) findViewById(R.id.killhistorybtn);
		sh = new Shelperhistory(this);
		cursor = sh.select();
		lv = (ListView) findViewById(R.id.historylv);
		registerForContextMenu(lv);
		histrotyimbtnb.setOnClickListener(this);
		histimbtna.setOnClickListener(this);
		killhistorybtn.setOnClickListener(this);
		cursorAdapter = new SimpleCursorAdapter(this, R.layout.historylista,
				cursor, new String[] { keyb, keyc, keyd, }, new int[] {
						R.id.listtxta, R.id.listtxtb, R.id.listtxtc });
		lv.setAdapter(cursorAdapter);
		ddsj();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("static-access")
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				Intent inta = new Intent(Historyjl.this, Ycactivitya.class);
				// new Addressposition().hisa = "abc";
				new Ycactivitya().ada = "b";
				address = cursor.getString(1);
				name = cursor.getString(2);
				tel = cursor.getString(3);
				Bundle bd = new Bundle();
				bd.putString("address", address);
				bd.putString("name", name);
				bd.putString("tel", tel);
				bd.putString("djzx", djzx);
				bd.putInt("ztdsl", ztdsl);
				bd.putString("zhdd", zhdd);
				bd.putString("zhname", zhname);
				bd.putString("zhtel", zhtel);
				bd.putString("xhdd", xhdd);
				bd.putString("xhname", xhname);
				bd.putString("xhtel", xhtel);
				bd.putString("ztadd", ztadd);
				bd.putString("ztbdd", ztbdd);
				bd.putString("ztcdd", ztcdd);
				bd.putString("ztddd", ztddd);
				bd.putString("ztedd", ztedd);
				bd.putString("ztaname", ztaname);
				bd.putString("ztbname", ztbname);
				bd.putString("ztcname", ztcname);
				bd.putString("ztdname", ztdname);
				bd.putString("ztename", ztename);
				bd.putString("ztatel", ztatel);
				bd.putString("ztbtel", ztbtel);
				bd.putString("ztctel", ztctel);
				bd.putString("ztdtel", ztdtel);
				bd.putString("ztetel", ztetel);
				bd.putString("ycatvastr", ycatvastr);
				inta.putExtras(bd);
				startActivity(inta);

			}
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				disloga();
				return true;
			}
		});

	}

	private void ddsj() {
		bundlea = new Bundle();
		bundlea = getIntent().getExtras();
		djzx = bundlea.getString("djzx");
		ztdsl = bundlea.getInt("ztdsl");
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
		ycatvastr= bundlea.getString("ycatvastr");
	}

	private void disloga() {
		AlertDialog.Builder builder = new Builder(Historyjl.this);
		builder.setMessage("ȷ��ɾ���˼�¼?");
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				ida = cursor.getInt(0);
				sh.delete(ida);
				cursor.requery();
				arg0.dismiss();
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		builder.create().show();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.histimbtna:
			finish();
			break;
		case R.id.killhistorybtn:
			sh.deletehi();
			cursor.requery();
			break;
		case R.id.histrotyimbtnb:
//			inta = new Intent(Historyjl.this, BaiduMapSelectActivity.class);
			bundleb = new Bundle();
			bundleb.putString("djzx", djzx);
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
			bundleb.putString("ycatvastr", ycatvastr);
			inta.putExtras(bundleb);
			startActivity(inta);
			break;
		}

	}
}
