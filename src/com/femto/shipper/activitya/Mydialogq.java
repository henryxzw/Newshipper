package com.femto.shipper.activitya;

import com.femto.shipper.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Mydialogq implements OnClickListener {
	private Context context;
	private Dialog dialog;
	private Button btna, btnb, btnc;
	private Dialogcallbackq dialogcallbackq;
	private TextView mydialogqtvb, mydialogqtva;

	public Mydialogq(Context con, int a, int b) {
		this.context = con;
		dialog = new Dialog(context, R.style.mydialogb);
		dialog.setContentView(R.layout.mydialogq);
		dialog.setCanceledOnTouchOutside(false);
		btna = (Button) dialog.findViewById(R.id.mydialogqbtna);
		btnb = (Button) dialog.findViewById(R.id.mydialogqbtnb);
		btnc = (Button) dialog.findViewById(R.id.mydialogqbtnc);
		mydialogqtva = (TextView) dialog.findViewById(R.id.mydialogqtva);
		mydialogqtvb = (TextView) dialog.findViewById(R.id.mydialogqtvb);
		mydialogqtva.setText(a + "");
		mydialogqtvb.setText(b + "");
		btna.setOnClickListener(this);
		btnb.setOnClickListener(this);
		btnc.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.mydialogqbtna:
			dialogcallbackq.dialogdoq(1);
			dialog.dismiss();
			break;
		case R.id.mydialogqbtnb:
			Ycactivitya.ycactivitya.finish();
			dialogcallbackq.dialogdoq(2);
			dialog.dismiss();
			break;
		case R.id.mydialogqbtnc:
			dialogcallbackq.dialogdoq(3);
			dialog.dismiss();
			break;
		}
	}

	public interface Dialogcallbackq {
		public void dialogdoq(int a);
	}

	public void setDialogCallbackq(Dialogcallbackq dialogcallbackq) {
		this.dialogcallbackq = dialogcallbackq;

	}

	public void show() {
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}
}
