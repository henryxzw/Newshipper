package com.femto.shipper.activitya;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.femto.shipper.R;

public class Mydialogone extends Activity implements OnClickListener {
	private Context context;
	private Dialog dialog;
	private Button btn;
	private Dialogcallbackone dialogcallbackone;

	public Mydialogone(Context con) {
		this.context = con;
		dialog = new Dialog(context, R.style.mydialogb);
		dialog.setContentView(R.layout.mylialogone);
		dialog.setCanceledOnTouchOutside(false);
		btn = (Button) dialog.findViewById(R.id.onebtn);
		btn.setOnClickListener(this);
	}

	public interface Dialogcallbackone {
		public void dialogdop(int a);
	}

	public void setDialogcallbackone(Dialogcallbackone dialogcallbackone) {
		this.dialogcallbackone = dialogcallbackone;

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
		case R.id.onebtn:
			dialogcallbackone.dialogdop(1);
			dialog.dismiss();
			break;
		}
	}
}