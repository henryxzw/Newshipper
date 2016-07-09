package com.femto.shipper.activitya;

import com.femto.shipper.R;
import com.umeng.analytics.MobclickAgent;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Lxrlb extends Activity implements OnClickListener {
	private Button lxrlbqrbtn;
	private ImageButton lxrlbibtna;
	private ListView lxrlv;
	private TextView ycbtzfhrtv;
	private EditText ycbchet, ycbdhet;
	private ImageView ycbtzfhriv;
	private RelativeLayout ycbtzfhrrl, ycbtxlrl;
	private int dw[] = { R.drawable.gou, R.drawable.goua };
	private int a = 0;
	private String name, tel, sfdxtz = "1";
	private Shelperlxr sh;
	public static Cursor cursor, cursorb;
	private SimpleCursorAdapter cursorAdapter;
	private static final String keya = "name";
	private static final String keyb = "tel";
	private int ida, sfxt = 0;
	private Intent inta;
	private Bundle bundlea;
	private SQLiteDatabase db;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Lxrlb.this.setTheme(R.style.All);
		setContentView(R.layout.lxrlb);
		lxrlbibtna = (ImageButton) findViewById(R.id.lxrlbibtna);
		lxrlbqrbtn = (Button) findViewById(R.id.lxrlbqrbtn);
		ycbtzfhrrl = (RelativeLayout) findViewById(R.id.ycbtzfhrrl);
		ycbtxlrl = (RelativeLayout) findViewById(R.id.ycbtxlrl);
		ycbtzfhrtv = (TextView) findViewById(R.id.ycbtzfhrtv);
		ycbtzfhriv = (ImageView) findViewById(R.id.ycbtzfhriv);
		lxrlv = (ListView) findViewById(R.id.lxrlv);
		ycbchet = (EditText) findViewById(R.id.ycbchet);
		ycbdhet = (EditText) findViewById(R.id.ycbdhet);
		ycbtzfhriv.setBackgroundResource(dw[0]);
		sh = new Shelperlxr(this);
		db = sh.getWritableDatabase();
		lxrlbibtna.setOnClickListener(this);
		ycbtxlrl.setOnClickListener(this);
		lxrlbqrbtn.setOnClickListener(this);
		ycbtzfhrrl.setOnClickListener(this);
		cursor = sh.select();
		cursorAdapter = new SimpleCursorAdapter(this, R.layout.lxrlvitem,
				cursor, new String[] { keya, keyb, }, new int[] {
						R.id.lxritemtva, R.id.lxritemtvb });
		lxrlv.setAdapter(cursorAdapter);
		lxrlv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				disloga();
				return false;
			}
		});
		lxrlv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				name = cursor.getString(1);
				tel = cursor.getString(2);
				ycbchet.setText(name);
				ycbdhet.setText(tel);
			}
		});
	}

	@Override
	protected void onDestroy() {
		sh.close();
		db.close();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		cursor.close();
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			ContentResolver reContentResolverol = getContentResolver();
			Uri contactData = data.getData();
			@SuppressWarnings("deprecation")
			Cursor cursor = managedQuery(contactData, null, null, null, null);
			cursor.moveToFirst();
			String username = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			String contactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			Cursor phone = reContentResolverol.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
							+ contactId, null, null);
			while (phone.moveToNext()) {
				String usernumber = phone
						.getString(phone
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				ycbchet.setText(username);
				ycbdhet.setText(usernumber);
			}
		}
	}

	private void disloga() {
		AlertDialog.Builder builder = new Builder(Lxrlb.this);
		builder.setMessage(getResources().getString(R.string.qrsccjl));
		builder.setTitle(getResources().getString(R.string.ts));
		builder.setPositiveButton(getResources().getString(R.string.qr),
				new DialogInterface.OnClickListener() {
					@SuppressWarnings("deprecation")
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						ida = cursor.getInt(0);
						sh.delete(ida);
						cursor.requery();
						arg0.dismiss();
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

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.ycbtzfhrrl:
			if (a == 0) {
				ycbtzfhriv.setBackgroundResource(dw[1]);
				ycbtzfhrtv.setTextColor(Color.parseColor("#AEAEAE"));
				a = 1;
				sfdxtz = "0";
			} else if (a == 1) {
				ycbtzfhriv.setBackgroundResource(dw[0]);
				ycbtzfhrtv.setTextColor(Color.BLACK);
				a = 0;
				sfdxtz = "1";
			}
			break;
		case R.id.lxrlbqrbtn:
			// sh.deletehi();
			name = ycbchet.getText().toString().trim();
			tel = ycbdhet.getText().toString().trim();
			if (ycbchet.getText().length() < 1) {
				ycbchet.setError(getResources().getString(R.string.qsrch));
			} else if (ycbdhet.getText().length() < 8) {
				ycbdhet.setError(getResources().getString(R.string.qsrzqddhhm));
			} else if (ycbchet.getText().length() > 0
					&& ycbdhet.getText().length() > 7) {
				cursorb = db.query("lxra", null, null, null, null, null, null);
				for (cursorb.moveToFirst(); !cursorb.isAfterLast(); cursorb
						.moveToNext()) {
					if (tel.equals(cursorb.getString(2))) {
						sfxt = 1;
						break;
					}
				}
				if (sfxt == 0) {
					sh.insert(name, tel);
				}
				cursorb.close();
				inta = new Intent();
				bundlea = new Bundle();
				bundlea.putString("lxrname", name);
				bundlea.putString("lxrtel", tel);
				bundlea.putString("sfdxtz", sfdxtz);
				bundlea.putInt("fdxtz", a);
				inta.putExtras(bundlea);
				this.setResult(5, inta);
				finish();
			}
			break;
		case R.id.ycbtxlrl:
			startActivityForResult(new Intent(Intent.ACTION_PICK,
					ContactsContract.Contacts.CONTENT_URI), 0);
			break;
		case R.id.lxrlbibtna:
			finish();
			break;
		}
	}
}