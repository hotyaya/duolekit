package net.blogjava.mobile.calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
/*
 * ���ѽ���
 */

public class AlarmAlert extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//��ô�������Intent
		Intent intent = getIntent();
		//����getExtras�������ݴ���Bundle
		Bundle bundle = intent.getExtras();
//		��Bundle��ȡ��remindMsg
		String remindMsg = bundle.getString("remindMsg");
		//if ����
		if (bundle.getBoolean("ring"))
		{
			Main.mediaPlayer = MediaPlayer.create(this, R.raw.ring);
			try
			{
				Main.mediaPlayer.setLooping(true);
				Main.mediaPlayer.prepare();
			}
			catch (Exception e)
			{
				setTitle(e.getMessage());
			}
			Main.mediaPlayer.start();
		}
		//if ��
		if(bundle.getBoolean("shake"))
		{
			//���Vibrator����
			Main.vibrator = (Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
			//������Ƶ�ʣ��Լ��ظ�����
			Main.vibrator.vibrate(new long[]{1000, 100, 100,1000}, -1);
		}
		//Creates a AlertDialog with the arguments 
		//supplied to this builder and show()'s the dialog.
		new AlertDialog.Builder(AlarmAlert.this).setIcon(R.drawable.clock)
				.setTitle("����").setMessage(remindMsg).setPositiveButton("�ص���",
						//Interface used to allow the creator of a dialog 
						//to run some code when an item on the dialog is clicked.. 
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int whichButton)
							{
								AlarmAlert.this.finish();
								//�ر���������
								if (Main.mediaPlayer != null)
									Main.mediaPlayer.stop();
								if(Main.vibrator != null)
									Main.vibrator.cancel();
							}
						}).show();

	}
}
