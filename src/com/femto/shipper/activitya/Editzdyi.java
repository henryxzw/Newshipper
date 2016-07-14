package com.femto.shipper.activitya;

import com.femto.shipper.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class Editzdyi extends EditText {
	private Drawable draw;
	private Context context;

	public Editzdyi(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public Editzdyi(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public Editzdyi(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	private void init() {
		draw = context.getResources().getDrawable(R.drawable.qk);
		setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					setDrawable();
				}
			}
		});
	}

	private void setDrawable() {
		setCompoundDrawablesWithIntrinsicBounds(null, null, draw, null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP && this.isFocused()) {
			int eventX = (int) event.getX();
			if (this.getRight() - eventX < draw.getIntrinsicWidth()
					+ this.getPaddingRight()) {
				setText("");
			}
		}
		return super.onTouchEvent(event);
	}
}