package com.femto.shipper.activitya;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Addresspo extends BaseActivity implements OnClickListener
{
	private RelativeLayout addpoarla;
	private EditText addpaetb, addpaetc;
	private TextView addpatva;
	private Button addpabtna;
	private String addptvastr, addpetbstr, addpetcstr, addressa, ycatvastr;
	private Shelperhistory sh;
	private Bundle bundlea, bundleb, bundlec, bundled;
	public static String haa = "hello";
	private Intent intenta, intentb;
	private String zhdd, zhname, zhtel, xhdd, xhname, xhtel, ztadd, ztaname, ztatel, ztbdd, ztbname, ztbtel, ztcdd, ztcname, ztctel, ztddd, ztdname,
			ztdtel, ztedd, ztename, ztetel, djzx;
	private int ztdsl;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addresspo);
		addpoarla = (RelativeLayout) findViewById(R.id.addpoarla);
		addpatva = (TextView) findViewById(R.id.addpatva);
		addpaetb = (EditText) findViewById(R.id.addpaetb);
		addpaetc = (EditText) findViewById(R.id.addpaetc);
		addpabtna = (Button) findViewById(R.id.addpabtna);
		addpabtna.setOnClickListener(this);
		addpoarla.setOnClickListener(this);
		addpatva.setOnClickListener(this);
		sh = new Shelperhistory(this);
		da();
	}

	private void da()
	{
		if (haa.equals("b"))
		{
			bundlec = new Bundle();
			bundlec = getIntent().getExtras();
			ycatvastr = bundlec.getString("ycatvastr");
			djzx = bundlec.getString("djzx");
			ztdsl = bundlec.getInt("ztdsl");
			zhdd = bundlec.getString("zhdd");
			zhname = bundlec.getString("zhname");
			zhtel = bundlec.getString("zhtel");
			xhdd = bundlec.getString("xhdd");
			xhname = bundlec.getString("xhname");
			xhtel = bundlec.getString("xhtel");
			ztadd = bundlec.getString("ztadd");
			ztbdd = bundlec.getString("ztbdd");
			ztcdd = bundlec.getString("ztcdd");
			ztddd = bundlec.getString("ztddd");
			ztedd = bundlec.getString("ztedd");
			ztaname = bundlec.getString("ztaname");
			ztbname = bundlec.getString("ztbname");
			ztcname = bundlec.getString("ztcname");
			ztdname = bundlec.getString("ztdname");
			ztename = bundlec.getString("ztename");
			ztatel = bundlec.getString("ztatel");
			ztbtel = bundlec.getString("ztbtel");
			ztctel = bundlec.getString("ztctel");
			ztdtel = bundlec.getString("ztdtel");
			ztetel = bundlec.getString("ztetel");
		} else if (haa.equals("a"))
		{
			bundlea = getIntent().getExtras();
			addressa = bundlea.getString("address");
			ycatvastr = bundlea.getString("ycatvastr");
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
			addpatva.setText(addressa);
			addpatva.setTextColor(Color.BLACK);
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.addpatva:
			haa = "hello";
			intentb = new Intent(Addresspo.this, Historyjl.class);
			bundled = new Bundle();
			bundled.putString("djzx", djzx);
			bundled.putInt("ztdsl", ztdsl);
			bundled.putString("zhdd", zhdd);
			bundled.putString("zhname", zhname);
			bundled.putString("zhtel", zhtel);
			bundled.putString("xhdd", xhdd);
			bundled.putString("xhname", xhname);
			bundled.putString("xhtel", xhtel);
			bundled.putString("ztadd", ztadd);
			bundled.putString("ztbdd", ztbdd);
			bundled.putString("ztcdd", ztcdd);
			bundled.putString("ztddd", ztddd);
			bundled.putString("ztedd", ztedd);
			bundled.putString("ztaname", ztaname);
			bundled.putString("ztbname", ztbname);
			bundled.putString("ztcname", ztcname);
			bundled.putString("ztdname", ztdname);
			bundled.putString("ztename", ztename);
			bundled.putString("ztatel", ztatel);
			bundled.putString("ztbtel", ztbtel);
			bundled.putString("ztctel", ztctel);
			bundled.putString("ztdtel", ztdtel);
			bundled.putString("ztetel", ztetel);
			bundled.putString("ycatvastr", ycatvastr);
			intentb.putExtras(bundled);
			startActivity(intentb);
			break;
		case R.id.addpabtna:
			addptvastr = addpatva.getText().toString().trim();
			addpetbstr = addpaetb.getText().toString().trim();
			addpetcstr = addpaetc.getText().toString().trim();
			if (addptvastr.equals("选择地址"))
			{
				addpatva.setError("请选择地址");
				showToast("请选择地址");
			} else
			{
				haa = "hello";
				sh.insert(addptvastr, addpetbstr, addpetcstr);
				Historyjl.cursor = sh.select();
				new Ycactivitya().ada = "a";
				intenta = new Intent(Addresspo.this, Ycactivitya.class);
				bundleb = new Bundle();
				bundleb.putString("djzx", djzx);
				bundleb.putInt("ztdsl", ztdsl);
				bundleb.putString("zhdd", zhdd);
				bundleb.putString("zhname", zhname);
				bundleb.putString("zhtel", zhtel);
				bundleb.putString("xhdd", xhdd);
				bundleb.putString("xhname", xhname);
				bundleb.putString("xhtel", xhtel);
				bundleb.putString("address", addptvastr);
				bundleb.putString("name", addpetbstr);
				bundleb.putString("tel", addpetcstr);
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
				intenta.putExtras(bundleb);
				startActivity(intenta);
				finish();
			}
			break;
		case R.id.addpoarla:
			finish();
			break;
		}
	}
}
