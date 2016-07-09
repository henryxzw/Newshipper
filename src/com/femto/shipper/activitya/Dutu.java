package com.femto.shipper.activitya;

import com.femto.shipper.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Dutu extends Activity implements OnClickListener
{
	private Button tv_back;
	private EditText et_serchText;
	private Bundle bundlea;
	private String ycatvastr,zhdd, zhname, zhtel, xhdd, xhname, xhtel, ztadd, ztaname, ztatel, ztbdd, ztbname, ztbtel, ztcdd, ztcname, ztctel, ztddd, ztdname,
			ztdtel, ztedd, ztename, ztetel, djzx;
	private int ztdsl;
	private Intent inta, intb;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dutu);
		tv_back = (Button) findViewById(R.id.tv_back);
		et_serchText = (EditText) findViewById(R.id.et_serchText);
		tv_back.setOnClickListener(this);
		ddz();
	}

	private void ddz()
	{
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

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.tv_back:
			// Toast.makeText(Dutu.this, djzx, Toast.LENGTH_SHORT).show();
			if (djzx.equals("zh") || djzx.equals("xh"))
			{
				tz();
			}
			if (djzx.equals("zta") || djzx.equals("ztb") || djzx.equals("ztc") || djzx.equals("ztc") || djzx.equals("ztd") || djzx.equals("zte"))
			{
				tza();
			}
			break;
		}

	}

	private void tz()
	{
		inta = new Intent(Dutu.this, Addressposition.class);
		String trim = et_serchText.getText().toString().trim();
//		new Addressposition().haa = "a";
		Bundle bda = new Bundle();
		bda.putString("address", trim);
		bda.putString("djzx", djzx);
		bda.putInt("ztdsl", ztdsl);
		bda.putString("zhdd", zhdd);
		bda.putString("zhname", zhname);
		bda.putString("zhtel", zhtel);
		bda.putString("xhdd", xhdd);
		bda.putString("xhname", xhname);
		bda.putString("xhtel", xhtel);
		bda.putString("ztadd", ztadd);
		bda.putString("ztbdd", ztbdd);
		bda.putString("ztcdd", ztcdd);
		bda.putString("ztddd", ztddd);
		bda.putString("ztedd", ztedd);
		bda.putString("ztaname", ztaname);
		bda.putString("ztbname", ztbname);
		bda.putString("ztcname", ztcname);
		bda.putString("ztdname", ztdname);
		bda.putString("ztename", ztename);
		bda.putString("ztatel", ztatel);
		bda.putString("ztbtel", ztbtel);
		bda.putString("ztctel", ztctel);
		bda.putString("ztdtel", ztdtel);
		bda.putString("ztetel", ztetel);
		bda.putString("ycatvastr", ycatvastr);
		inta.putExtras(bda);
		new Historyjl().finish();
		startActivity(inta);
		finish();
	}

	@SuppressWarnings("static-access")
	private void tza()
	{
		intb = new Intent(Dutu.this, Addresspo.class);
		String trim = et_serchText.getText().toString().trim();
		new Addresspo().haa = "a";
		Bundle bda = new Bundle();
		bda.putString("address", trim);
		bda.putString("djzx", djzx);
		bda.putInt("ztdsl", ztdsl);
		bda.putString("zhdd", zhdd);
		bda.putString("zhname", zhname);
		bda.putString("zhtel", zhtel);
		bda.putString("xhdd", xhdd);
		bda.putString("xhname", xhname);
		bda.putString("xhtel", xhtel);
		bda.putString("ztadd", ztadd);
		bda.putString("ztbdd", ztbdd);
		bda.putString("ztcdd", ztcdd);
		bda.putString("ztddd", ztddd);
		bda.putString("ztedd", ztedd);
		bda.putString("ztaname", ztaname);
		bda.putString("ztbname", ztbname);
		bda.putString("ztcname", ztcname);
		bda.putString("ztdname", ztdname);
		bda.putString("ztename", ztename);
		bda.putString("ztatel", ztatel);
		bda.putString("ztbtel", ztbtel);
		bda.putString("ztctel", ztctel);
		bda.putString("ztdtel", ztdtel);
		bda.putString("ztetel", ztetel);
		bda.putString("ycatvastr", ycatvastr);
		intb.putExtras(bda);
		new Historyjl().finish();
		startActivity(intb);
		finish();
	}
}
