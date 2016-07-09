package com.femto.shipper;

import com.femto.shipper.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class Dialogme {
	private Context context;
	private Dialog dialog;

	public Dialogme(Context con) {
		this.context = con;
		dialog = new Dialog(context, R.style.CustomProgressDialog);
		dialog.setContentView(R.layout.dialogme);
		ImageView imageView = (ImageView) dialog.findViewById(R.id.dialogmeiv);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView
				.getBackground();
		animationDrawable.start();
		dialog.setCanceledOnTouchOutside(false);
	}

	public void show() {
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}
}
