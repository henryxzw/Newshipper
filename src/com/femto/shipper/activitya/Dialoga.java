package com.femto.shipper.activitya;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.femto.shipper.R;

public class Dialoga {
	private Context context;
	private Dialog dialog;

	public Dialoga(Context con) {
		this.context = con;
		dialog = new Dialog(context, R.style.CustomProgressDialog);
		dialog.setContentView(R.layout.dialoga);
		ImageView imageView = (ImageView) dialog.findViewById(R.id.dialogaiv);
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