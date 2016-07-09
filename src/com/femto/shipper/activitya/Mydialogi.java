package com.femto.shipper.activitya;

import com.femto.shipper.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Mydialogi extends Activity implements OnClickListener {
	private Context context;
	private Dialog dialog;
	private Button mydlgibtna, mydlgibtnb;
	private Dialogcallbacki dialogcallbacki;
	private EditText mydlogiet;
	private String a, b;

	public Mydialogi(Context con, String a, String b) {
		this.context = con;
		this.a = a;
		this.b = b;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogi);
		dialog.setCanceledOnTouchOutside(false);
		mydlgibtna = (Button) dialog.findViewById(R.id.mydlgibtna);
		mydlgibtnb = (Button) dialog.findViewById(R.id.mydlgibtnb);
		mydlogiet = (EditText) dialog.findViewById(R.id.mydlogiet);
		mydlgibtna.setOnClickListener(this);
		mydlgibtnb.setOnClickListener(this);

	}

	public interface Dialogcallbacki {
		public void dialogdoi(String a);
	}

	public void setDialogCallbacki(Dialogcallbacki dialogcallbacki) {
		this.dialogcallbacki = dialogcallbacki;

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
		case R.id.mydlgibtna:
			dialog.dismiss();
			break;
		case R.id.mydlgibtnb:
			if (mydlogiet.getText().length() < 1) {
				mydlogiet.setError(a);
			} else {
				if (Integer.valueOf(mydlogiet.getText().toString().trim()) > 0) {
					dialogcallbacki.dialogdoi(mydlogiet.getText().toString()
							.trim());
					dialog.dismiss();
				} else {
					mydlogiet.setError(b);
				}
			}
			break;
		}
	}
}
