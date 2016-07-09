package com.femto.shipper.activitya;

import com.femto.shipper.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Mydialogl extends Activity implements OnClickListener
{
	private Context context;
	private Dialog dialog;
	private Dialogcallbackl dialogcallbackl;
	private int djln;
	private String groupid, nwqxsyggn, nwktylzfgn;
	private LinearLayout mydialogllla, mydialoglllb, mydialoglllc;
	private ImageView yj;

	public Mydialogl(Context con, int a, String b, String c, String d)
	{
		this.context = con;
		this.djln = a;
		this.groupid = b;
		this.nwqxsyggn = c;
		this.nwktylzfgn = d;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mylialogl);
		dialog.setCanceledOnTouchOutside(false);
		mydialogllla = (LinearLayout) dialog.findViewById(R.id.mydialogllla);
		mydialoglllb = (LinearLayout) dialog.findViewById(R.id.mydialoglllb);
		mydialoglllc = (LinearLayout) dialog.findViewById(R.id.mydialoglllc);
		yj = (ImageView) dialog.findViewById(R.id.yj);
		// Window dialogWindow = dialog.getWindow();
		// dialogWindow.setGravity(Gravity.BOTTOM);
		if (groupid.equals("8") || groupid.equals("9"))
		{
			yj.setBackgroundResource(R.drawable.yj);
		} else
		{
			yj.setBackgroundResource(R.drawable.yuejiezhanghu);
		}
		if (djln == 1)
		{
			mydialogllla.setBackgroundResource(R.drawable.sgapelogl);
			mydialoglllb.setBackground(null);
			mydialoglllc.setBackground(null);

		} else if (djln == 2)
		{
			mydialoglllb.setBackgroundResource(R.drawable.sgapelogl);
			mydialogllla.setBackground(null);
			mydialoglllc.setBackground(null);
		} else if (djln == 3)
		{
			mydialoglllc.setBackgroundResource(R.drawable.sgapelogl);
			mydialogllla.setBackground(null);
			mydialoglllb.setBackground(null);
		}
		mydialogllla.setOnClickListener(this);
		mydialoglllb.setOnClickListener(this);
		mydialoglllc.setOnClickListener(this);
	}

	public interface Dialogcallbackl
	{
		public void dialogdol(int a);
	}

	public void setDialogcallbackl(Dialogcallbackl dialogcallbackl)
	{
		this.dialogcallbackl = dialogcallbackl;

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
		case R.id.mydialogllla:
			mydialogllla.setBackgroundResource(R.drawable.sgapelogl);
			mydialoglllb.setBackground(null);
			mydialoglllc.setBackground(null);
			dialogcallbackl.dialogdol(1);
			dialog.dismiss();
			break;
		case R.id.mydialoglllb:
			if (groupid.equals("8") || groupid.equals("9"))
			{
				mydialoglllb.setBackgroundResource(R.drawable.sgapelogl);
				mydialogllla.setBackground(null);
				mydialoglllc.setBackground(null);
				dialogcallbackl.dialogdol(2);
				dialog.dismiss();
			} else
			{
				Toast.makeText(context, nwqxsyggn, Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.mydialoglllc:
			Toast.makeText(context, nwktylzfgn, Toast.LENGTH_SHORT).show();
			// mydialoglllc.setBackgroundResource(R.drawable.sgapelogl);
			// mydialogllla.setBackground(null);
			// mydialoglllb.setBackground(null);
			// dialogcallbackl.dialogdol(3);
			// dialog.dismiss();
			break;
		}
	}
}