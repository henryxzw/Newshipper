package com.femto.shipper.activitya;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class FilpperListvew extends ListView
{
	private float myLastX = -1;
	private float myLastY = -1;
	private boolean delete = false;
	private FilpperDeleteListener filpperDeleterListener;

	public FilpperListvew(Context context)
	{
		super(context);
	}

	public FilpperListvew(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		switch (ev.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			myLastX = ev.getX(0);
			myLastY = ev.getY(0);
			break;
		case MotionEvent.ACTION_MOVE:
			float deltaXb = myLastX - ev.getX(ev.getPointerCount() - 1);
			float deltaX = ev.getX(ev.getPointerCount() - 1) - myLastX;
			float deltaY = Math.abs(ev.getY(ev.getPointerCount() - 1) - myLastY);
			if (deltaX > 100.0 && deltaY < 50)
			{
				delete = true;
			}
			if (deltaXb > 100.0 && deltaY < 50)
			{
				delete = true;
			}
			break;

		case MotionEvent.ACTION_UP:
			if (delete && filpperDeleterListener != null)
			{
				filpperDeleterListener.filpperDelete(myLastX, myLastY);
			}
			reset();
			break;
		}
		return super.onTouchEvent(ev);
	}

	public void reset()
	{
		delete = false;
		myLastX = -1;
		myLastY = -1;
	}

	public void setFilpperDeleteListener(FilpperDeleteListener f)
	{
		filpperDeleterListener = f;
	}

	public interface FilpperDeleteListener
	{
		public void filpperDelete(float xPosition, float yPosition);
	}
}