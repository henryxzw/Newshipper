package com.femto.shipper.activitya;

import com.femto.shipper.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Mydialogo implements OnClickListener
{
	private Context context;
	private Dialog dialog;
	private Button btna;
	private Dialogcallbacko dialogcallbacko;

	public Mydialogo(Context con)
	{
		this.context = con;
		dialog = new Dialog(context, R.style.mydialogb);
		dialog.setContentView(R.layout.mydialogo);
		dialog.setCanceledOnTouchOutside(false);
		btna = (Button) dialog.findViewById(R.id.mydialogobtn);
		btna.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.mydialogobtn:
			dialogcallbacko.dialogdoo(1);
			dialog.dismiss();
			break;
		}
	}

	public interface Dialogcallbacko
	{
		public void dialogdoo(int a);
	}

	public void setDialogCallbacko(Dialogcallbacko dialogcallbacko)
	{
		this.dialogcallbacko = dialogcallbacko;

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
