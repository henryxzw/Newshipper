package com.femto.shipper.activitya;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.femto.shipper.R;
import com.femto.shipper.activitya.Mypaintb.onSelectListener;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Mydialogg extends BaseActivity implements OnClickListener
{
	private Mypaintb mypaintagclsla, mypaintb;
	private Context context;
	private Dialog dialog;
	private TextView mdlgxshctv, mdlgtvzdzzl, mdlgtvzdzhl, mdlgtvqbjg, mdlgtvccjg, tvqcsl;
	private ImageView mdlgimva;
	private LinearLayout mdlglla, mdlgllb;
	private Button mdlgbtn, mdlgbtnqx;
	private List<String> ggccxzlist, qclist;
	private String[] ggxz = new String[] { "40HQ   12.017*2.342*2.693M", "45HQ   13.546*2.347*2.693M", "", "40GP   12.017*2.342*2.388M" };
	private String[] zdzhl = new String[4];
	private String[] zdzzl = new String[3];
	private String[] qbjg = new String[1];
	private String[] ccjg = new String[1];
	private int[] imgid = new int[] { R.drawable.jzx };
	@SuppressWarnings("unused")
	private LinearLayout mdldlla, mdldllb;
	@SuppressWarnings("unused")
	private String dwzw = "null", gg = "null", qbj = "null", ccj = "null", xhsl = "null", jqdwa, jqdwb, jqdwc, qcmz = "", d, l, zhlat, zhlon, c,
			allescc, es = "20", escc = "	5.890*2.342*2.388M", jgcdm = "no", xhlat, xhlon, phonea, passworda, zhl;
	private int djcsa = 0, clsl, carid = 31;
	@SuppressWarnings("unused")
	private String[] jqdw;
	Dialogcallbackg dialogcallbackg;
	private StatusBean statusbean;
	private HttpUtils http;

	public Mydialogg(Context con, String zhlat, String zhlon, String xhlat, String xhlon, String phonea, String passworda, String zdzhljzxa,
			String zdzhljzxb, String zdzhljzxc, String zdzhljzxd, String zdzzla, String zdzzlb, String zdzzlc, String qbjgjzx, String ccjgjzx,
			String d, String l, String qcmz, String c)
	{
		this.context = con;
		this.zhlat = zhlat;
		this.zhlon = zhlon;
		this.xhlat = xhlat;
		this.xhlon = xhlon;
		this.phonea = phonea;
		this.passworda = passworda;
		this.c = c;
		qbjg[0] = qbjgjzx;
		ccjg[0] = ccjgjzx;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogg);
		zdzhl[0] = zdzhljzxa;
		zdzhl[1] = zdzhljzxb;
		zdzhl[2] = zdzhljzxc;
		zdzhl[3] = zdzhljzxd;
		zdzzl[0] = zdzzla;
		zdzzl[1] = zdzzlb;
		zdzzl[2] = zdzzlc;
		this.qcmz = qcmz;
		this.d = d;
		this.l = l;
		allescc = es + c + escc;
		ggxz[2] = allescc;
		cshid();
		tjcshsj();
		mypaintagclsla();
		clsl();
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.mdlgbtn:
			if (djcsa == 0)
			{
				clsl();
				if (jgcdm.equals("yes"))
				{
					changea();
					addcls();
				}
			} else
			{
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

	private void addcls()
	{
		qclist.clear();
		tvqcsl.setText(clsl + "");
		if (clsl < 2)
		{
			if (clsl == 0)
			{
				for (int i = 0; i < 6; i++)
				{
					qclist.add(0 + l);
					xhsl = 0 + l;
				}
			} else
			{
				for (int i = 0; i < 6; i++)
				{
					qclist.add(1 + l);
				}
			}
		} else
		{
			if (clsl % 2 == 1)
			{
				for (int i = clsl / 2 + 2; i < clsl + 1; i++)
				{
					qclist.add(i + l);
				}
				for (int i = 1; i < clsl / 2 + 2; i++)
				{
					qclist.add(i + l);
				}
			} else
			{
				for (int i = clsl / 2 + 1; i < clsl + 1; i++)
				{
					qclist.add(i + l);
				}
				for (int i = 1; i < clsl / 2 + 1; i++)
				{
					qclist.add(i + l);
				}
			}
		}
		mypaintb.setData(qclist);
		mypaintb.setOnSelectListener(new onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
				xhsl = text;
			}
		});
	}

	private void changea()
	{
		djcsa = 1;
		// mdlgllawh.width = 0;
		// mdlgllawh.height = 0;
		// mdlglla.setLayoutParams(mdlgllawh);
		// mdlgllbwh.width = 720;
		// mdlgllbwh.height = 400;
		// mdlgllb.setLayoutParams(mdlgllbwh);
		mdlglla.setVisibility(View.GONE);
		mdlgllb.setVisibility(View.VISIBLE);
		if (gg == "null")
		{
			gg = ggxz[0];
		}
		mdlgxshctv.setTextColor(Color.RED);
		mdlgxshctv.setText(qcmz + gg);
	}

	private void clsl()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", "get_car_count");
		map.put("pwd", passworda);
		map.put("username", phonea);
		map.put("start_location", zhlat + "|" + zhlon);
		map.put("end_location", xhlat + "|" + xhlon);
		map.put("cartype", carid);
		String jiami = ToolUtils.JiaMi(map);
		String url = Net.Car + jiami;
		http = new HttpUtils();
		http.send(HttpMethod.GET, url, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				statusbean = GsonUtils.json2Bean(arg0.result, StatusBean.class);
				if (statusbean.status.equals("0"))
				{
					clsl = Integer.valueOf(statusbean.count);
					tvqcsl.setText(clsl + "");
					jgcdm = "yes";
				} else
				{
					tvqcsl.setText(statusbean.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
			}
		});
	}

	public interface Dialogcallbackg
	{
		public void dialogdog(String a, String b, String c, String d, String e, int f, String h);
	}

	public void setDialogCallbackg(Dialogcallbackg dialogcallbackg)
	{
		this.dialogcallbackg = dialogcallbackg;

	}

	public void show()
	{
		dialog.show();
	}

	public void dismiss()
	{
		dialog.dismiss();
	}

	private void mypaintagclsla()
	{
		for (int i = 0; i < ggxz.length; i++)
		{
			ggccxzlist.add(ggxz[i]);
		}
		mypaintagclsla.setData(ggccxzlist);
		mypaintagclsla.setOnSelectListener(new onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
				if (text == ggxz[0])
				{
					carid = 42;
					clsl();
					mdlgtvzdzzl.setText(zdzzl[0]);
					mdlgtvzdzhl.setText(zdzhl[0]);
				}
				if (text == ggxz[1])
				{
					carid = 82;
					clsl();
					mdlgtvzdzzl.setText(zdzzl[1]);
					mdlgtvzdzhl.setText(zdzhl[1]);
				}
				if (text == ggxz[2])
				{
					carid = 31;
					clsl();
					mdlgtvzdzzl.setText(zdzzl[1]);
					mdlgtvzdzhl.setText(zdzhl[2]);
				}
				if (text == ggxz[3])
				{
					carid = 33;
					clsl();
					mdlgtvzdzzl.setText(zdzzl[2]);
					mdlgtvzdzhl.setText(zdzhl[3]);
				}
				gg = text;
			}
		});
	}

	private void cshid()
	{
		mypaintagclsla = (Mypaintb) dialog.findViewById(R.id.mypaintagclsla);
		mypaintb = (Mypaintb) dialog.findViewById(R.id.mypaintb);
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

	public void tjcshsj()
	{
		mdlgxshctv.setText(qcmz);
		mdlgimva.setBackgroundResource(imgid[0]);
		mdlgtvccjg.setText(ccjg[0]);
		mdlgtvqbjg.setText(qbjg[0]);
		mdlgtvzdzzl.setText(zdzzl[0]);
		mdlgtvzdzhl.setText(zdzhl[0]);
		ggccxzlist = new ArrayList<String>();
		qclist = new ArrayList<String>();
	}
}