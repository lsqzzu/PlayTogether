package com.chenantao.playtogether.utils;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chenantao.playtogether.R;

/**
 * Created by Chenantao_gg on 2016/1/22.
 *
 */
public class PopupWindowManager
{

	public static PopupWindow getDefaultPopupWindow(View contentView, final Activity activity)
	{
		final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams
				.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		mPopupWindow.setTouchable(true);
		mPopupWindow.setAnimationStyle(R.style.selectPopupWindowAnim);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.setTouchInterceptor(new View.OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
				{
					mPopupWindow.dismiss();
					return true;
				}
				return false;
			}
		});
		mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
		{
			@Override
			public void onDismiss()
			{
				toggleLight(false,activity);
			}
		});
		return mPopupWindow;
	}

	/**
	 * popupWindow弹出或隐藏时
	 * 使内容区域变亮或变暗
	 */
	public static void toggleLight(boolean isOpen,Activity activity)
	{
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = isOpen ? 0.3f : 1.0f;
		lp.dimAmount = isOpen ? 0.5f : 1.0f;
		activity.getWindow().setAttributes(lp);
	}

}