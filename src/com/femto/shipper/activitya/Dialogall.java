package com.femto.shipper.activitya;

import com.femto.shipper.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

public class Dialogall
{
	private Context context;
	private Dialog dialog;

	public Dialogall(Context con)
	{
		this.context = con;
		dialog = new Dialog(context, R.style.CustomProgressDialog);
		dialog.setContentView(R.layout.dialogall);
		ImageView imageView = (ImageView) dialog.findViewById(R.id.dialogalliv);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
		animationDrawable.start();
		dialog.setCanceledOnTouchOutside(false);
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
