package net.blogjava.mobile.calendar;

import com.earth.Detail;
//import com.earth.Main;
//import com.earth.Main;
 import com.earth.db.*;
 
import com.earth.db.DBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
/*
 * @Reader DaXiong
 * ����¼�༭ҳ��
 */
public class Record extends Activity
{
	//�ճ�Title
	private EditText etTitle;
	//�ճ�����
	private EditText etContent;
	//�Ƿ��޸�
	private boolean edit = false;
	//
	private int id, index;
	//ʱ��
	private int hour, minute;
	//���ѷ�ʽ���񶯣�����
	private boolean shake, ring;
	//
	private String remindTime;
	String s_title;
	private static final int DELETE_ID = Menu.FIRST + 1;

	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record);
		
		etTitle = (EditText) findViewById(R.id.etTitle);
		etContent = (EditText) findViewById(R.id.etContent);
		//��ô��͹�����Intent
		Intent intent = getIntent();
		//���edit��ֵ�����û�У��ͷ���false
		edit = intent.getBooleanExtra("edit", false);
		//����޸� ����ȡ����Ϣ
		if (edit)
		{	//the value to be returned if no value of the 
			//desired type is stored with the given name
			id = intent.getIntExtra("id", 0);
			index = intent.getIntExtra("index", -1);

			Cursor cursor = Grid.dbService.query(id);			
			if (cursor.moveToLast())
			{
				etTitle.setText(cursor.getString(0));
				etContent.setText(cursor.getString(1));
				shake = Boolean.parseBoolean(cursor.getString(2));
				ring = Boolean.parseBoolean(cursor.getString(3));
			}

		}

	}

	class MenuItemClickParent
	{
		protected Activity activity;

		public MenuItemClickParent(Activity activity)
		{
			this.activity = activity;
		}
	}
	//����
	class OnSaveMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		
		public boolean onMenuItemClick(MenuItem item)
		{

			if (edit)
			{
				Grid.dbService.updateRecord(id, etTitle.getText().toString(),
						etContent.getText().toString(),remindTime, shake,ring);

				AllRecord.recordArray.set(index, etTitle.getText().toString());				
				AllRecord.myListActivity.setListAdapter(AllRecord.arrayAdapter);

			}
			else
			{
				Grid.dbService.insertRecord(etTitle.getText().toString(),
						etContent.getText().toString(), AllRecord.year + "-"
								+ AllRecord.month + "-" + AllRecord.day,
						remindTime, shake, ring);
				AllRecord.arrayAdapter.insert(etTitle.getText().toString(), 0);
				
				AllRecord.idList.add(0, Grid.dbService.getMaxId(AllRecord.year
						+ "-" + AllRecord.month + "-" + AllRecord.day));

			}

			activity.finish();
			return true;
		}

		public OnSaveMenuItemClick(Activity activity)
		{
			super(activity);
		}
		
	}
	//��������ʱ��
	class OnSettingMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener, OnClickListener
	{
		private TimePicker tpRemindTime;
		private CheckBox cbShake, cbRing;

		
		public boolean onMenuItemClick(MenuItem item)
		{
			AlertDialog.Builder builder;

			builder = new AlertDialog.Builder(activity);
			builder.setTitle("��������ʱ��");
			//��̬����һ��LinearLayout
			LinearLayout remindSettingLayout = (LinearLayout) getLayoutInflater()
					.inflate(R.layout.remindsetting, null);
			//TimePicker
			tpRemindTime = (TimePicker) remindSettingLayout
					.findViewById(R.id.tpRemindTime);
			cbShake = (CheckBox)remindSettingLayout.findViewById(R.id.cbShake);
			cbRing = (CheckBox)remindSettingLayout.findViewById(R.id.cbRing);
			//
			cbShake.setChecked(shake);
			cbRing.setChecked(ring);
			
			tpRemindTime.setIs24HourView(true);
			
			if (remindTime != null)
			{
				tpRemindTime.setCurrentHour(hour);
				tpRemindTime.setCurrentMinute(minute);
			}
			builder.setView(remindSettingLayout);

			builder.setPositiveButton("ȷ��", this);
			builder.setNegativeButton("ȡ��", null);
			AlertDialog adRemindSetting;
			adRemindSetting = builder.create();
			adRemindSetting.show();
			return true;
		}
		//���
		
		public void onClick(DialogInterface dialog, int which)
		{

			hour = tpRemindTime.getCurrentHour();
			minute = tpRemindTime.getCurrentMinute();
			remindTime = hour + ":" + minute + ":0";
			shake = cbShake.isChecked();
			ring = cbRing.isChecked();

		}

		public OnSettingMenuItemClick(Activity activity)
		{
			super(activity);
		}
//		public OndeleteMenuItemClick(Activity activity)
//		{
//			super(activity);
//		}
		
	}
	/*
	 * �˵�����
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuItem miSaveRecord = menu.add(0, 1, 1, "���");
		MenuItem miSetting = menu.add(0, 2, 2, "��������ʱ��");
		MenuItem delete = menu.add(0, DELETE_ID, 3, "ɾ��");
		miSaveRecord.setOnMenuItemClickListener(new OnSaveMenuItemClick(this));
		miSetting.setOnMenuItemClickListener(new OnSettingMenuItemClick(this));
//        delete.setOnMenuItemClickListener(new On(this));
		
		delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem item) {
//			Log.e("-----", "dss-___");
			if(item.getItemId()==3) {
//				Intent intent=new Intent();
//			    intent.setClass(activity, com.earth.Main2.class);
//			    activity.startActivity(intent);
//				finish();
					
				}
			return false;
			}
			});
		
	
		
		
		
	
		return true;
	}
//////////////////////////////////////////////
	
	
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		switch (DELETE_ID) {
		case DELETE_ID:{
			AlertDialog dialog;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("ȷ��Ҫɾ���˼�¼��");
			builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener(){

				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					DBHelper dbHelper = new DBHelper(Record.this);
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					db.delete("record", "etTitle=?", new String[]{s_title});
					Intent intent = new Intent(Record.this,Main.class);
					startActivity(intent);
					finish();
				}
				
			});
			builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener(){

				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
				
			});
			dialog = builder.create();
			dialog.show();
			break;
			
			
			
			
			
			
//			break;
		}
		default:
			break;
		}
		
		
		
		
		
		
		
		
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	
///////////////////////////////////////////////	
}
