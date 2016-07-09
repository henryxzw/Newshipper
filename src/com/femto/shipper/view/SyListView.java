package com.femto.shipper.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class SyListView extends ListView
{

	public SyListView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public SyListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public SyListView(Context context)
	{
		super(context);
	}

	@Override
	public void setSelector(Drawable sel)
	{
		super.setSelector(new ColorDrawable(Color.TRANSPARENT));
	}

	/**
	 * 重写该方法，达到使ListView适应ScrollView的效果
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_MOVE)
		{
			return true;
		}
		return super.onTouchEvent(event);
	}
}
