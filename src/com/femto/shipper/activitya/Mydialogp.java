package com.femto.shipper.activitya;

import com.femto.shipper.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Mydialogp implements OnClickListener
{
	private Context context;
	private Dialog dialog;
	private Button btna, btnb;
	private Dialogcallbackp dialogcallbackp;

	public Mydialogp(Context con)
	{
		this.context = con;
		dialog = new Dialog(context, R.style.mydialogb);
		dialog.setContentView(R.layout.mydialogp);
		dialog.setCanceledOnTouchOutside(false);
		btna = (Button) dialog.findViewById(R.id.mydialogpbtna);
		btnb = (Button) dialog.findViewById(R.id.mydialogpbtnb);
		btna.setOnClickListener(this);
		btnb.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.mydialogpbtna:
			dialogcallbackp.dialogdop(1);
			dialog.dismiss();
			break;
		case R.id.mydialogpbtnb:
			dialogcallbackp.dialogdop(2);
			dialog.dismiss();
			break;
		}
	}

	public interface Dialogcallbackp
	{
		public void dialogdop(int a);
	}

	public void setDialogCallbackp(Dialogcallbackp dialogcallbackp)
	{
		this.dialogcallbackp = dialogcallbackp;

	}

	public void show()
	{
		dialog.show();
	}

	public void dismiss()
	{
		dialog.dismiss();
	}
}
