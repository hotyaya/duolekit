package net.blogjava.mobile.calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

//��ͼ
public class CalendarView extends View
{
	public Calendar ce;
	Main activity;

	@Override
	protected void onDraw(Canvas canvas)
	{

		ce.draw(canvas);

	}

	public CalendarView(Activity activity)
	{
		super(activity);
		this.activity=(Main)activity;
		ce = new Calendar(activity, this);
	}


	//��CalendarView �������һ�������¼�����
	
	@Override
	public boolean onTouchEvent(MotionEvent motion)
	{

		ce.grid.setCellX(motion.getX());
		ce.grid.setCellY(motion.getY());
		if (ce.grid.inBoundary())
		{
			this.invalidate();//����Invalidate�Ⱥ����󴰿ڲ��������ػ�
			System.out.println("ce="+ce);
			System.out.println("111111111");
			Intent intent=new Intent();
			intent.setClass(activity, com.test.mydialog.MyDialogActivity.class);
			activity.startActivity(intent);
			
			
			
		}
		return super.onTouchEvent(motion);
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{

			case KeyEvent.KEYCODE_DPAD_UP:
			{

				ce.grid.setCurrentRow(ce.grid.getCurrentRow() - 1);
				break;
			}
			case KeyEvent.KEYCODE_DPAD_DOWN:
			{
				ce.grid.setCurrentRow(ce.grid.getCurrentRow() + 1);
				break;
			}
			case KeyEvent.KEYCODE_DPAD_LEFT:
			{
				ce.grid.setCurrentCol(ce.grid.getCurrentCol() - 1);
				break;
			}
			case KeyEvent.KEYCODE_DPAD_RIGHT:
			{
				ce.grid.setCurrentCol(ce.grid.getCurrentCol() + 1);
				break;
			}
		
		}
		
		return true;
	}
}
