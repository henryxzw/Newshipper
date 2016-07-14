package com.femto.shipper.activitya;

import com.femto.shipper.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;

public class Mydialogk extends Ycactivityb implements OnClickListener {
	private Context context;
	private Dialog dialog;
	private Dialogcallbackk dialogcallbackk;
	private LinearLayout mydlgkllc, mydlgkllb, mydlgklla;

	public Mydialogk(Context con) {
		this.context = con;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogk);
		dialog.setCanceledOnTouchOutside(false);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.BOTTOM);
		mydlgklla = (LinearLayout) dialog.findViewById(R.id.mydlgklla);
		mydlgkllb = (LinearLayout) dialog.findViewById(R.id.mydlgkllb);
		mydlgkllc = (LinearLayout) dialog.findViewById(R.id.mydlgkllc);
		mydlgkllc.setOnClickListener(this);
		mydlgklla.setOnClickListener(this);
		mydlgkllb.setOnClickListener(this);
	}

	public interface Dialogcallbackk {
		public void dialogdok(int a);
	}

	public void setDialogCallbackk(Dialogcallbackk dialogcallbackk) {
		this.dialogcallbackk = dialogcallbackk;

	}

	public void show() {
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.mydlgklla:
			dialogcallbackk.dialogdok(1);
			dialog.dismiss();
			break;
		case R.id.mydlgkllb:
			dialogcallbackk.dialogdok(2);
			dialog.dismiss();
			break;
		case R.id.mydlgkllc:
			dialog.dismiss();
			break;
		}
	}
}
