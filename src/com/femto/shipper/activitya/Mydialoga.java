package com.femto.shipper.activitya;

import com.femto.shipper.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Mydialoga implements OnClickListener
{
	private Context context;
	private Dialogcallback dialogcallback;
	private Dialog dialog;
	private RelativeLayout rlqcgs, rlhgjg, rlbswl, rldwb, rldcm, rlscm;
	private Button sure, qxbtn;
	private ImageView mdaimvqcgs, mdaimvqcgsga, mdaimvhgjg, mdaimvhgjgga, mdaimvbswl, mdaimvbswlga, mdaimvdwb, mdaimvdwbga, mdaimvdcm, mdaimvdcmga,
			mdaimvscm, mdaimvscmga;
	private int rlqcgsint = 0, rlhgjgint = 0, rlbswlint = 0, rldwbint = 0, rldcmint = 0, rlscmint = 0;
	private TextView mdatvqcgs, mdatvhgjg, mdatvbswl, mdatvdwb, mdatvdcm, mdatvscm;
	private int qcgs = 0, hgjg = 0, bswl = 0, dwb = 0, dcm = 0, scm = 0;

	public Mydialoga(Context con, int bcztqcgs, int bczthgjg, int bcztbswl, int bcztdwb, int bcztdcm, int bcztscm)
	{
		this.context = con;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialoga);
		dialog.setCanceledOnTouchOutside(false);
		// textView = (TextView) dialog.findViewById(R.id.atava);
		// editText = (EditText) dialog.findViewById(R.id.editText1);
		mdatvqcgs = (TextView) dialog.findViewById(R.id.mdatvqcgs);
		mdatvhgjg = (TextView) dialog.findViewById(R.id.mdatvhgjg);
		mdatvbswl = (TextView) dialog.findViewById(R.id.mdatvbswl);
		mdatvdwb = (TextView) dialog.findViewById(R.id.mdatvdwb);
		mdatvdcm = (TextView) dialog.findViewById(R.id.mdatvdcm);
		mdatvscm = (TextView) dialog.findViewById(R.id.mdatvscm);
		mdaimvqcgs = (ImageView) dialog.findViewById(R.id.mdaimvqcgs);
		mdaimvqcgsga = (ImageView) dialog.findViewById(R.id.mdaimvqcgsga);
		mdaimvhgjg = (ImageView) dialog.findViewById(R.id.mdaimvhgjg);
		mdaimvhgjgga = (ImageView) dialog.findViewById(R.id.mdaimvhgjgga);
		mdaimvbswl = (ImageView) dialog.findViewById(R.id.mdaimvbswl);
		mdaimvbswlga = (ImageView) dialog.findViewById(R.id.mdaimvbswlga);
		mdaimvdwb = (ImageView) dialog.findViewById(R.id.mdaimvdwb);
		mdaimvdwbga = (ImageView) dialog.findViewById(R.id.mdaimvdwbga);
		mdaimvdcm = (ImageView) dialog.findViewById(R.id.mdaimvdcm);
		mdaimvdcmga = (ImageView) dialog.findViewById(R.id.mdaimvdcmga);
		mdaimvscm = (ImageView) dialog.findViewById(R.id.mdaimvscm);
		mdaimvscmga = (ImageView) dialog.findViewById(R.id.mdaimvscmga);
		rlqcgs = (RelativeLayout) dialog.findViewById(R.id.rlqcgs);
		rlhgjg = (RelativeLayout) dialog.findViewById(R.id.rlhgjg);
		rlbswl = (RelativeLayout) dialog.findViewById(R.id.rlbswl);
		rldwb = (RelativeLayout) dialog.findViewById(R.id.rldwb);
		rldcm = (RelativeLayout) dialog.findViewById(R.id.rldcm);
		rlscm = (RelativeLayout) dialog.findViewById(R.id.rlscm);
		sure = (Button) dialog.findViewById(R.id.mdabtna);
		qxbtn = (Button) dialog.findViewById(R.id.mdabtnab);
		rlqcgs.setOnClickListener(this);
		rlhgjg.setOnClickListener(this);
		rlbswl.setOnClickListener(this);
		rldwb.setOnClickListener(this);
		rldcm.setOnClickListener(this);
		rlscm.setOnClickListener(this);
		sure.setOnClickListener(this);
		qxbtn.setOnClickListener(this);
		if (bcztqcgs == 0)
		{
			clearimg(0);
		} else
		{
			setchoise(0);
		}
		if (bczthgjg == 0)
		{
			clearimg(1);
		} else
		{
			setchoise(1);
		}
		if (bcztbswl == 0)
		{
			clearimg(2);
		} else
		{
			setchoise(2);
		}
		if (bcztdwb == 0)
		{
			clearimg(3);
		} else
		{
			setchoise(3);
		}
		if (bcztdcm == 0)
		{
			clearimg(4);
		} else
		{
			setchoise(4);
		}
		if (bcztscm == 0)
		{
			clearimg(5);
		} else
		{
			setchoise(5);
		}
		qcgs = bcztqcgs;
		hgjg = bczthgjg;
		bswl = bcztbswl;
		dwb = bcztdwb;
		dcm = bcztdcm;
		scm = bcztscm;
		rlqcgsint = bcztqcgs;
		rlhgjgint = bczthgjg;
		rlbswlint = bcztbswl;
		rldwbint = bcztdwb;
		rldcmint = bcztdcm;
		rlscmint = bcztscm;
	}

	public interface Dialogcallback
	{
		public void dialogdo(int a, int b, int c, int d, int e, int f);
	}

	public void setDialogCallback(Dialogcallback dialogcallback)
	{
		this.dialogcallback = dialogcallback;
	}

	public void show()
	{
		dialog.show();
	}

	public void hide()
	{
		dialog.hide();
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
		case R.id.mdabtna:
			dialogcallback.dialogdo(qcgs, hgjg, bswl, dwb, dcm, scm);
			dismiss();
			break;
		case R.id.mdabtnab:
			dismiss();
			break;
		case R.id.rlqcgs:
			if (rlqcgsint == 0)
			{
				qcgs = 1;
				rlqcgsint = 1;
				setchoise(0);
			} else if (rlqcgsint == 1)
			{
				qcgs = 0;
				rlqcgsint = 0;
				clearimg(0);
			}
			break;
		case R.id.rlhgjg:
			if (rlhgjgint == 0)
			{
				hgjg = 1;
				rlhgjgint = 1;
				setchoise(1);
			} else if (rlhgjgint == 1)
			{
				hgjg = 0;
				rlhgjgint = 0;
				clearimg(1);
			}
			break;
		case R.id.rlbswl:
			if (rlbswlint == 0)
			{
				bswl = 1;
				rlbswlint = 1;
				setchoise(2);
			} else if (rlbswlint == 1)
			{
				bswl = 0;
				rlbswlint = 0;
				clearimg(2);
			}
			break;
		case R.id.rldwb:
			if (rldwbint == 0)
			{
				dwb = 1;
				rldwbint = 1;
				setchoise(3);
			} else if (rldwbint == 1)
			{
				dwb = 0;
				rldwbint = 0;
				clearimg(3);
			}
			break;
		case R.id.rldcm:
			if (rldcmint == 0)
			{
				dcm = 1;
				rldcmint = 1;
				setchoise(4);
				rlscmint = 0;
				scm = 0;
				clearimg(5);
			} else if (rldcmint == 1)
			{
				dcm = 0;
				rldcmint = 0;
				clearimg(4);
			}
			break;
		case R.id.rlscm:
			if (rlscmint == 0)
			{
				scm = 1;
				rlscmint = 1;
				setchoise(5);
				dcm = 0;
				rldcmint = 0;
				clearimg(4);
			} else if (rlscmint == 1)
			{
				scm = 0;
				rlscmint = 0;
				clearimg(5);
			}
			break;
		}
	}

	private void setchoise(int arg0)
	{
		switch (arg0)
		{
		case 0:
			mdaimvqcgs.setImageResource(R.drawable.gaosu2);
			mdaimvqcgsga.setImageResource(R.drawable.gou);
			mdatvqcgs.setTextColor(Color.parseColor("#EC494A"));
			// mdatvqcgs.setVisibility(View.VISIBLE);
			break;
		case 1:
			mdatvhgjg.setTextColor(Color.parseColor("#EC494A"));
			mdaimvhgjg.setImageResource(R.drawable.haiguan2);
			mdaimvhgjgga.setImageResource(R.drawable.gou);
			// mdaimvhgjgga.setVisibility(View.VISIBLE);
			break;
		case 2:
			mdatvbswl.setTextColor(Color.parseColor("#EC494A"));
			mdaimvbswl.setImageResource(R.drawable.baosui2);
			mdaimvbswlga.setImageResource(R.drawable.gou);
			// mdaimvbswlga.setVisibility(View.VISIBLE);
			break;
		case 3:
			mdatvdwb.setTextColor(Color.parseColor("#EC494A"));
			mdaimvdwb.setImageResource(R.drawable.weiban2);
			mdaimvdwbga.setImageResource(R.drawable.gou);
			// mdaimvdwbga.setVisibility(View.VISIBLE);
			break;
		case 4:
			mdatvdcm.setTextColor(Color.parseColor("#EC494A"));
			mdaimvdcm.setImageResource(R.drawable.cenmen2);
			mdaimvdcmga.setImageResource(R.drawable.gou);
			// mdaimvdcmga.setVisibility(View.VISIBLE);
			break;
		case 5:
			mdatvscm.setTextColor(Color.parseColor("#EC494A"));
			mdaimvscm.setImageResource(R.drawable.cemen2);
			mdaimvscmga.setImageResource(R.drawable.gou);
			// mdaimvscmga.setVisibility(View.VISIBLE);
			break;
		}
	}

	private void clearimg(int arg0)
	{
		switch (arg0)
		{
		case 0:
			mdatvqcgs.setTextColor(Color.parseColor("#000000"));
			mdaimvqcgs.setImageResource(R.drawable.gaosu1);
			mdaimvqcgsga.setImageResource(R.drawable.goua);
			// mdaimvqcgsga.setVisibility(View.GONE);
			break;
		case 1:
			mdatvhgjg.setTextColor(Color.parseColor("#000000"));
			mdaimvhgjg.setImageResource(R.drawable.haiguan1);
			mdaimvhgjgga.setImageResource(R.drawable.goua);
			// mdaimvhgjgga.setVisibility(View.GONE);
			break;
		case 2:
			mdatvbswl.setTextColor(Color.parseColor("#000000"));
			mdaimvbswl.setImageResource(R.drawable.baosui);
			mdaimvbswlga.setImageResource(R.drawable.goua);
			// mdaimvbswlga.setVisibility(View.GONE);
			break;
		case 3:
			mdatvdwb.setTextColor(Color.parseColor("#000000"));
			mdaimvdwb.setImageResource(R.drawable.weiban1);
			mdaimvdwbga.setImageResource(R.drawable.goua);
			// mdaimvdwbga.setVisibility(View.GONE);
			break;
		case 4:
			mdatvdcm.setTextColor(Color.parseColor("#000000"));
			mdaimvdcm.setImageResource(R.drawable.cenmen1);
			mdaimvdcmga.setImageResource(R.drawable.goua);
			// mdaimvdcmga.setVisibility(View.GONE);
			break;
		case 5:
			mdatvscm.setTextColor(Color.parseColor("#000000"));
			mdaimvscm.setImageResource(R.drawable.cemen1);
			mdaimvscmga.setImageResource(R.drawable.goua);
			// mdaimvscmga.setVisibility(View.GONE);
			break;
		}
	}
}