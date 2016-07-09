package com.femto.shipper.view;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.PopupWindow;

import com.femto.shipper.R;

public class SelectPicPopupWindow extends PopupWindow
{

	private Button btn_take_photo, btn_pick_photo, btn_cancel;
	private View mMenuView;
	/** 本地相册 **/
	public static final int TAKE_PHOTO_ID = R.id.btn_take_photo;
	/** 相机拍照 **/
	public static final int PICK_PHOTO_ID = R.id.btn_pick_photo;

	public SelectPicPopupWindow(final Activity context, OnClickListener itemsOnClick)
	{
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popupwindow_get_picture, null);
		mMenuView.getBackground().setAlpha(140);
		btn_take_photo = (Button) mMenuView.findViewById(TAKE_PHOTO_ID);
		btn_pick_photo = (Button) mMenuView.findViewById(PICK_PHOTO_ID);
		btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
		// 取消按钮
		btn_cancel.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dismiss();// 销毁弹出框
			}
		});
		// 设置按钮监听
		btn_pick_photo.setOnClickListener(itemsOnClick);
		btn_take_photo.setOnClickListener(itemsOnClick);
		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimBottomPopup);
		// 实例化一个ColorDrawable颜色为半透明
//		   ColorDrawable dw = new ColorDrawable(-00000);
//		ColorDrawable dw = new ColorDrawable(0xb0000000);//  暂时注释
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(null);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnTouchListener(new OnTouchListener()
		{
			public boolean onTouch(View v, MotionEvent event)
			{
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP)
				{
					if (y < height)
					{
						dismiss();
					}
				}
				return true;
			}
		});
	}
}