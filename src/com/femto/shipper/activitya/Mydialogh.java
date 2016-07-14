package com.femto.shipper.activitya;

import java.util.ArrayList;
import java.util.List;
import com.femto.shipper.R;
import com.femto.shipper.activitya.Mypainta.onSelectListener;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Mydialogh extends Activity implements OnClickListener
{
	private Context context;
	private Dialog dialog;
	private Button mdlhbtn, mdlhbtnqx;
	private Mypainta mypaintabzzlxz, mypaintahwsxxz;
	private List<String> bzxzlist, sxxzlist;
	private String[] bz = new String[7];
	private String[] sx = new String[11];
	private Dialogcallbackh dialogcallbackh;
	private String bzzl = "null", hwsx = "null";

	public Mydialogh(Context con, String kbbz, String zxbz, String mx, String jx, String tz, String sz, String qt, String nbcp, String ryp,
			String jc, String juju, String jydq, String jxsb, String dzcp, String lbj, String qph, String yshw, String hgcp)
	{
		this.context = con;
		bz[0] = kbbz;
		bz[1] = zxbz;
		bz[2] = mx;
		bz[3] = jx;
		bz[4] = tz;
		bz[5] = sz;
		bz[6] = qt;
		sx[0] = nbcp;
		sx[1] = ryp;
		sx[2] = jc;
		sx[3] = juju;
		sx[4] = jydq;
		sx[5] = jxsb;
		sx[6] = dzcp;
		sx[7] = lbj;
		sx[8] = qph;
		sx[9] = yshw;
		sx[10] = hgcp;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogh);
		dialog.setCanceledOnTouchOutside(false);
		cshid();
	}

	private void cshid()
	{
		mdlhbtn = (Button) dialog.findViewById(R.id.mdlhbtn);
		mdlhbtnqx = (Button) dialog.findViewById(R.id.mdlhbtnqx);
		mypaintabzzlxz = (Mypainta) dialog.findViewById(R.id.mypaintabzzlxz);
		mypaintahwsxxz = (Mypainta) dialog.findViewById(R.id.mypaintahwsxxz);
		bzxzlist = new ArrayList<String>();
		sxxzlist = new ArrayList<String>();
		mdlhbtn.setOnClickListener(this);
		mdlhbtnqx.setOnClickListener(this);
		for (int i = 0; i < bz.length; i++)
		{
			bzxzlist.add(bz[i]);
		}
		mypaintabzzlxz.setData(bzxzlist);
		for (int i = 0; i < sx.length; i++)
		{
			sxxzlist.add(sx[i]);
		}
		mypaintahwsxxz.setData(sxxzlist);
		mypaintabzzlxz.setOnSelectListener(new onSelectListener()
		{
			@Override
			public void onSelect(String text)
			{
				bzzl = text;
			}
		});
		mypaintahwsxxz.setOnSelectListener(new onSelectListener()
		{
			@Override
			public void onSelect(String text)
			{
				hwsx = text;
			}
		});
	}

	public interface Dialogcallbackh
	{
		public void dialogdoh(String a, String b);
	}

	public void setDialogCallbackh(Dialogcallbackh dialogcallbackh)
	{
		this.dialogcallbackh = dialogcallbackh;

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
		case R.id.mdlhbtn:
			if (bzzl.equals("null"))
			{
				bzzl = bz[3];
			}
			if (hwsx.equals("null"))
			{
				hwsx = sx[5];
			}
			dialogcallbackh.dialogdoh(bzzl, hwsx);
			dialog.dismiss();
			break;
		case R.id.mdlhbtnqx:
			dialog.dismiss();
			break;
		}
	}

}
