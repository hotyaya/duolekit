package net.blogjava.mobile.calendar;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
/*
 * @about
 */

public class About extends Activity
{
	private TextView tvAbout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		tvAbout = (TextView) findViewById(R.id.tvAbout);
		String about = "��������Android�棩1.0\n\n";
		about += "�Ŷӣ���˼�ֻ���Ϸ�Ŷ�\n\n";
		about += "����������ѩ\n\n";
		about += "���ͣ�http://nokiaguy.blogjava.net\n\n";
		about += "��ַ��http://www.enterise.cn/";
		tvAbout.setText(about);
	}
}
