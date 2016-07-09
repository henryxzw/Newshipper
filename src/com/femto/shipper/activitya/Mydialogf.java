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
import com.femto.shipper.activitya.Mypainta.onSelectListener;
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

public class Mydialogf extends BaseActivity implements OnClickListener
{
	private Context context;
	private StatusBean statusbean;
	private Dialog dialog;
	private Button mdldbtn, mdldbtnqx;
	private ImageView mdldimva;
	private TextView mdldtvzdzzl, mdldtvzdzhl, mdldtvqbjg, mdldtvccjg, mdldxshctv, tvclsl;
	private Mypainta mypaintadwxz, mypaintaccxz, mypaintaclsla;
	private List<String> dwxzlist, ccxzlist, clslist;
	private String[] dw = new String[] { "8T", "10T", "15T", "0.6T", "3T", "5T" };
	private String[] cc = new String[] { "2.4*1.5*1.7M", "4.0*1.9*1.9M", "4.2*2.1*2.0M", "6.1*2.2*2.3M", "6.8*2.3*2.4M", "7.2*2.3*2.4M",
			"7.6*2.4*2.5M", "8.0*2.4*2.5M", "9.6*2.4*2.5M" };
	private String[] zdzhl = new String[9];
	private String[] zdzzl = new String[6];
	private String[] qbjg = new String[5];
	private String[] ccjg = new String[7];
	private int[] imgid = new int[] { R.drawable.glpbz, R.drawable.glpb3, R.drawable.glpb5, R.drawable.glpb8, R.drawable.glpb15 };
	private LinearLayout mdldlla, mdldllb;
	private String dwzw = "null", gg = "null", qbj = "null", ccj = "null", xhsl = "null", jqdwa, jqdwb, jqdwc, qcmz = "", zhlat, zhlon, xhlat, zhl,
			xhlon, phonea, passworda, jgcdm = "no", d, l;;
	private int djcsa = 0, clsl, carid = 36;
	private String[] jqdw;
	private Dialogcallbackf dialogcallbackf;
	private HttpUtils http;

	public Mydialogf(Context con, String zhlat, String zhlon, String xhlat, String xhlon, String phonea, String passworda, String dqbjgz,
			String dqbjgo, String dqbjgt, String dqbjgth, String dqbjgf, String dccjgz, String dccjgo, String dccjgt, String dccjgth, String dccjgf,
			String dccjgfi, String dccjgs, String zdzhlz, String zdzhlo, String zdzhlt, String zdzhlth, String zdzhlf, String zdzhlfi,
			String zdzhlsix, String zdzhlsenver, String zdzhleight, String zdzzlz, String zdzzlo, String zdzzlt, String zdzzlth, String zdzzlf,
			String zdzzlfi, String qcmz, String d, String l)
	{
		this.context = con;
		this.zhlat = zhlat;
		this.zhlon = zhlon;
		this.xhlat = xhlat;
		this.xhlon = xhlon;
		this.phonea = phonea;
		this.passworda = passworda;
		qbjg[0] = dqbjgz;
		qbjg[1] = dqbjgo;
		qbjg[2] = dqbjgt;
		qbjg[3] = dqbjgth;
		qbjg[4] = dqbjgf;
		ccjg[0] = dccjgz;
		ccjg[1] = dccjgo;
		ccjg[2] = dccjgt;
		ccjg[3] = dccjgth;
		ccjg[4] = dccjgf;
		ccjg[5] = dccjgfi;
		ccjg[6] = dccjgs;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogd);
		zdzhl[0] = zdzhlz;
		zdzhl[1] = zdzhlo;
		zdzhl[2] = zdzhlt;
		zdzhl[3] = zdzhlth;
		zdzhl[4] = zdzhlf;
		zdzhl[5] = zdzhlfi;
		zdzhl[6] = zdzhlsix;
		zdzhl[7] = zdzhlsenver;
		zdzhl[8] = zdzhleight;
		zdzzl[0] = zdzzlz;
		zdzzl[1] = zdzzlo;
		zdzzl[2] = zdzzlt;
		zdzzl[3] = zdzzlth;
		zdzzl[4] = zdzzlf;
		zdzzl[5] = zdzzlfi;
		this.qcmz = qcmz;
		this.d = d;
		this.l = l;
		cshid();
		tjcshsj();
		mypaintaccxz();
		mypaintadwxz();
		clsl();
	}

	public interface Dialogcallbackf
	{
		public void dialogdof(String a, String b, String c, String d, String e, String f, int g, String h);
	}

	public void setDialogCallbackf(Dialogcallbackf dialogcallbackf)
	{
		this.dialogcallbackf = dialogcallbackf;

	}

	public void show()
	{
		dialog.show();
	}

	public void dismiss()
	{
		dialog.dismiss();
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.mdldbtn:
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
				qbj = mdldtvqbjg.getText().toString();
				ccj = mdldtvccjg.getText().toString();
				zhl = mdldtvzdzhl.getText().toString();
				dialogcallbackf.dialogdof(qcmz, dwzw, gg, xhsl, qbj, ccj, carid, zhl);
				dismiss();
			}
			break;
		case R.id.mdldbtnqx:
			dismiss();
			break;
		}
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
					tvclsl.setText(clsl + "");
					jgcdm = "yes";
				} else
				{
					mdldxshctv.setText(statusbean.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
			}
		});
	}

	private void changea()
	{
		djcsa = 1;
		mdldlla.setVisibility(View.GONE);
		if (dwzw == "null")
		{
			jqdwa = dw[3];
			jqdw = jqdwa.split("T");
			jqdwb = jqdw[0];
			jqdwc = jqdwb + d;
			dwzw = jqdwc;
		}
		if (gg == "null")
		{
			gg = cc[0];
		}
		mdldllb.setVisibility(View.VISIBLE);
		mdldxshctv.setTextColor(Color.RED);
		mdldxshctv.setText(qcmz + dwzw + "		" + gg);
	}

	private void tjcshsj()
	{
		dwxzlist = new ArrayList<String>();
		ccxzlist = new ArrayList<String>();
		clslist = new ArrayList<String>();
		mdldbtnqx.setOnClickListener(this);
		mdldbtn.setOnClickListener(this);
		mdldxshctv.setText(qcmz);
		mdldtvzdzzl.setText(zdzzl[0]);
		mdldtvzdzhl.setText(zdzhl[0]);
		mdldtvqbjg.setText(qbjg[0]);
		mdldtvccjg.setText(ccjg[0]);
		mdldimva.setBackgroundResource(imgid[0]);
	}

	private void cshid()
	{
		mdldbtnqx = (Button) dialog.findViewById(R.id.mdldbtnqx);
		mdldbtn = (Button) dialog.findViewById(R.id.mdldbtn);
		tvclsl = (TextView) dialog.findViewById(R.id.tvclsl);
		mdldxshctv = (TextView) dialog.findViewById(R.id.mdldxshctv);
		mypaintadwxz = (Mypainta) dialog.findViewById(R.id.mypaintadwxz);
		mypaintaccxz = (Mypainta) dialog.findViewById(R.id.mypaintaccxz);
		mypaintaclsla = (Mypainta) dialog.findViewById(R.id.mypaintaclsla);
		mdldimva = (ImageView) dialog.findViewById(R.id.mdldimva);
		mdldtvzdzzl = (TextView) dialog.findViewById(R.id.mdldtvzdzzl);
		mdldtvzdzhl = (TextView) dialog.findViewById(R.id.mdldtvzdzhl);
		mdldtvqbjg = (TextView) dialog.findViewById(R.id.mdldtvqbjg);
		mdldtvccjg = (TextView) dialog.findViewById(R.id.mdldtvccjg);
		mdldlla = (LinearLayout) dialog.findViewById(R.id.mdldlla);
		mdldllb = (LinearLayout) dialog.findViewById(R.id.mdldllb);
		mdldllb.setVisibility(View.GONE);
	}

	private void mypaintadwxz()
	{
		for (int i = 0; i < dw.length; i++)
		{
			dwxzlist.add(dw[i]);
		}
		mypaintadwxz.setData(dwxzlist);
		mypaintadwxz.setOnSelectListener(new onSelectListener()
		{
			@Override
			public void onSelect(String text)
			{
				if (text == dw[3])
				{
					ccxzlist.clear();
					for (int i = 0; i < cc.length; i++)
					{
						ccxzlist.add(cc[0]);
					}
					mdldtvzdzzl.setText(zdzzl[0]);
					mdldtvzdzhl.setText(zdzhl[0]);
					mdldtvqbjg.setText(qbjg[0]);
					mdldtvccjg.setText(ccjg[0]);
					mdldimva.setBackgroundResource(imgid[0]);
					mypaintaccxz.setData(ccxzlist);
					jqdwa = dw[3];
					jqdw = jqdwa.split("T");
					jqdwb = jqdw[0];
					jqdwc = jqdwb + d;
					dwzw = jqdwc;
					gg = cc[0];
				}
				if (text == dw[4])
				{
					ccxzlist.clear();
					for (int i = 0; i < cc.length; i++)
					{
						ccxzlist.add(cc[1]);
						ccxzlist.add(cc[2]);
					}
					mdldtvzdzhl.setText(zdzhl[2]);
					mdldtvzdzzl.setText(zdzzl[1]);
					mdldtvqbjg.setText(qbjg[1]);
					mdldtvccjg.setText(ccjg[1]);
					mdldimva.setBackgroundResource(imgid[1]);
					mypaintaccxz.setData(ccxzlist);
					jqdwa = dw[4];
					jqdw = jqdwa.split("T");
					jqdwb = jqdw[0];
					jqdwc = jqdwb + d;
					dwzw = jqdwc;
					gg = cc[2];
				}
				if (text == dw[5])
				{
					ccxzlist.clear();
					for (int i = 0; i < cc.length; i++)
					{
						ccxzlist.add(cc[3]);
						ccxzlist.add(cc[4]);
					}
					mdldtvzdzhl.setText(zdzhl[4]);
					mdldtvzdzzl.setText(zdzzl[2]);
					mdldtvqbjg.setText(qbjg[2]);
					mdldtvccjg.setText(ccjg[2]);
					mdldimva.setBackgroundResource(imgid[2]);
					mypaintaccxz.setData(ccxzlist);
					jqdwa = dw[5];
					jqdw = jqdwa.split("T");
					jqdwb = jqdw[0];
					jqdwc = jqdwb + d;
					dwzw = jqdwc;
					gg = cc[4];
				}
				if (text == dw[0])
				{
					ccxzlist.clear();
					for (int i = 0; i < cc.length; i++)
					{
						ccxzlist.add(cc[5]);
					}
					mdldtvzdzhl.setText(zdzhl[5]);
					mdldtvzdzzl.setText(zdzzl[3]);
					mdldtvqbjg.setText(qbjg[3]);
					mdldtvccjg.setText(ccjg[3]);
					mdldimva.setBackgroundResource(imgid[3]);
					mypaintaccxz.setData(ccxzlist);
					jqdwa = dw[0];
					jqdw = jqdwa.split("T");
					jqdwb = jqdw[0];
					jqdwc = jqdwb + d;
					dwzw = jqdwc;
					gg = cc[5];
				}
				if (text == dw[1])
				{
					ccxzlist.clear();
					for (int i = 0; i < cc.length; i++)
					{
						ccxzlist.add(cc[6]);
						ccxzlist.add(cc[7]);
					}
					mdldtvzdzhl.setText(zdzhl[7]);
					mdldtvzdzzl.setText(zdzzl[4]);
					mdldtvqbjg.setText(qbjg[3]);
					mdldtvccjg.setText(ccjg[5]);
					mdldimva.setBackgroundResource(imgid[3]);
					mypaintaccxz.setData(ccxzlist);
					jqdwa = dw[1];
					jqdw = jqdwa.split("T");
					jqdwb = jqdw[0];
					jqdwc = jqdwb + d;
					dwzw = jqdwc;
					gg = cc[7];
				}
				if (text == dw[2])
				{
					ccxzlist.clear();
					for (int i = 0; i < cc.length; i++)
					{
						ccxzlist.add(cc[8]);
					}
					mdldtvzdzhl.setText(zdzhl[8]);
					mdldtvzdzzl.setText(zdzzl[5]);
					mdldtvqbjg.setText(qbjg[4]);
					mdldtvccjg.setText(ccjg[6]);
					mdldimva.setBackgroundResource(imgid[4]);
					mypaintaccxz.setData(ccxzlist);
					jqdwa = dw[2];
					jqdw = jqdwa.split("T");
					jqdwb = jqdw[0];
					jqdwc = jqdwb + d;
					dwzw = jqdwc;
					gg = cc[8];
				}
			}
		});
	}

	private void mypaintaccxz()
	{
		for (int i = 0; i < cc.length; i++)
		{
			ccxzlist.add(cc[0]);
		}

		mypaintaccxz.setData(ccxzlist);
		mypaintaccxz.setOnSelectListener(new onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
				if (text == cc[0])
				{
					carid = 36;
					clsl();
					mdldtvzdzzl.setText(zdzzl[0]);
					mdldtvzdzhl.setText(zdzhl[0]);
					mdldtvqbjg.setText(qbjg[0]);
					mdldtvccjg.setText(ccjg[0]);
				}
				if (text == cc[1])
				{
					carid = 61;
					clsl();
					mdldtvzdzhl.setText(zdzhl[1]);
					mdldtvzdzzl.setText(zdzzl[1]);
					mdldtvqbjg.setText(qbjg[1]);
					mdldtvccjg.setText(ccjg[1]);
				}
				if (text == cc[2])
				{
					carid = 61;
					clsl();
					mdldtvzdzhl.setText(zdzhl[2]);
					mdldtvzdzzl.setText(zdzzl[1]);
					mdldtvqbjg.setText(qbjg[1]);
					mdldtvccjg.setText(ccjg[1]);
				}
				if (text == cc[3])
				{
					carid = 63;
					clsl();
					mdldtvzdzhl.setText(zdzhl[3]);
					mdldtvzdzzl.setText(zdzzl[2]);
					mdldtvqbjg.setText(qbjg[2]);
					mdldtvccjg.setText(ccjg[2]);
				}
				if (text == cc[4])
				{
					carid = 63;
					clsl();
					mdldtvzdzhl.setText(zdzhl[4]);
					mdldtvzdzzl.setText(zdzzl[2]);
					mdldtvqbjg.setText(qbjg[2]);
					mdldtvccjg.setText(ccjg[2]);
				}
				if (text == cc[5])
				{
					if (jqdwb.trim().equals("8"))
					{
						carid = 65;
						clsl();
					} else
					{
						carid = 80;
						clsl();
					}
					mdldtvzdzhl.setText(zdzhl[5]);
					mdldtvzdzzl.setText(zdzzl[3]);
					mdldtvqbjg.setText(qbjg[3]);
					mdldtvccjg.setText(ccjg[3]);
				}
				if (text == cc[6])
				{
					if (jqdwb.trim().equals("8"))
					{
						carid = 65;
						clsl();
					} else
					{
						carid = 80;
						clsl();
					}
					mdldtvzdzhl.setText(zdzhl[6]);
					mdldtvzdzzl.setText(zdzzl[4]);
					mdldtvqbjg.setText(qbjg[3]);
					mdldtvccjg.setText(ccjg[4]);
				}
				if (text == cc[7])
				{
					if (jqdwb.trim().equals("8"))
					{
						carid = 65;
						clsl();
					} else
					{
						carid = 80;
						clsl();
					}
					mdldtvzdzhl.setText(zdzhl[7]);
					mdldtvzdzzl.setText(zdzzl[4]);
					mdldtvqbjg.setText(qbjg[3]);
					mdldtvccjg.setText(ccjg[5]);
				}
				if (text == cc[8])
				{
					carid = 68;
					clsl();
					mdldtvzdzhl.setText(zdzhl[8]);
					mdldtvzdzzl.setText(zdzzl[5]);
					mdldtvqbjg.setText(qbjg[4]);
					mdldtvccjg.setText(ccjg[6]);
				}
				gg = text;
			}

		});
	}

	private void addcls()
	{
		clslist.clear();
		tvclsl.setText(clsl + "");
		if (clsl < 2)
		{
			if (clsl == 0)
			{
				for (int i = 0; i < 6; i++)
				{
					clslist.add(0 + l);
					xhsl = 0 + l;
				}
			} else
			{
				for (int i = 0; i < 6; i++)
				{
					clslist.add(1 + l);
				}
			}
		} else
		{
			if (clsl % 2 == 1)
			{
				for (int i = clsl / 2 + 2; i < clsl + 1; i++)
				{
					clslist.add(i + l);
				}
				for (int i = 1; i < clsl / 2 + 2; i++)
				{
					clslist.add(i + l);
				}
			} else
			{
				for (int i = clsl / 2 + 1; i < clsl + 1; i++)
				{
					clslist.add(i + l);
				}
				for (int i = 1; i < clsl / 2 + 1; i++)
				{
					clslist.add(i + l);
				}
			}
		}
		mypaintaclsla.setData(clslist);
		mypaintaclsla.setOnSelectListener(new onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
				xhsl = text;
			}
		});
	}
}
