package com.femto.shipper.activitya;

import com.femto.shipper.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Mydialogj extends Activity implements OnClickListener
{
	private Context context;
	private Dialog dialog;
	private Dialogcallbackj dialogcallbackj;
	private CheckBox mydlogjcb;
	private Button mydlgjbtna, mydlgjbtnb;
	private int dt = 0;
	private EditText mydlogjet;
	private String a, b;

	public Mydialogj(Context con, String a, String b)
	{
		this.context = con;
		this.a = a;
		this.b = b;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogj);
		dialog.setCanceledOnTouchOutside(false);
		mydlgjbtna = (Button) dialog.findViewById(R.id.mydlgjbtna);
		mydlgjbtnb = (Button) dialog.findViewById(R.id.mydlgjbtnb);
		mydlogjet = (EditText) dialog.findViewById(R.id.mydlogjet);
		mydlogjcb = (CheckBox) dialog.findViewById(R.id.mydlogjcb);
		mydlgjbtna.setOnClickListener(this);
		mydlgjbtnb.setOnClickListener(this);

	}

	public interface Dialogcallbackj
	{
		public void dialogdoj(int a, String b);
	}

	public void setDialogCallbackj(Dialogcallbackj dialogcallbackj)
	{
		this.dialogcallbackj = dialogcallbackj;

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
		case R.id.mydlgjbtna:
			dialog.dismiss();
			break;
		case R.id.mydlgjbtnb:
			if (mydlogjet.getText().length() < 1)
			{
				mydlogjet.setError(a);
			} else
			{
				if (Integer.valueOf(mydlogjet.getText().toString().trim()) > 1)
				{
					if (mydlogjcb.isChecked())
					{
						dt = 1;
					} else
					{
						dt = 0;
					}
					dialogcallbackj.dialogdoj(dt, mydlogjet.getText().toString().trim());
					dialog.dismiss();
				} else
				{
					mydlogjet.setError(b);
				}
			}
			break;
		}
	}
}
