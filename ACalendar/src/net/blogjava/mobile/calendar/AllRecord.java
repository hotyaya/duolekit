package net.blogjava.mobile.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.blogjava.mobile.calendar.Record.MenuItemClickParent;
import net.blogjava.mobile.calendar.db.DBService;

import android.R.integer;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/*
 * @Reader DaXiong
 * Display all record ��ʾ���м�¼
 */

public class AllRecord extends ListActivity
{
//  variable Year ,Month , Day
	public static int year, month, day;
//	string list �ı����б�
	public static List<String> recordArray;
//	String's arrayadapter ���������� 
	public static ArrayAdapter<String> arrayAdapter;
//	��
	public static List<Integer> idList = new ArrayList<Integer>();
//	one listactivity object
	public static ListActivity myListActivity;
//	menuitem object
	private MenuItem miNewRecord;
	private MenuItem miModifyRecord;
	private MenuItem miDeleteRecord;
//	������
	private OnEditRecordMenuItemClick editRecordMenuItemClick = new OnEditRecordMenuItemClick(
			this);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//get this from intent
		year = getIntent().getExtras().getInt("year");
		month = getIntent().getExtras().getInt("month");
		day = getIntent().getExtras().getInt("day");
        //���ݼ��� ���磺ADO.NET
		Cursor cursor = Grid.dbService.query(year + "-" + month + "-" + day);
		//��ʼ��recordArray
		if (recordArray == null)
			recordArray = new ArrayList<String>();
		//��ʼ��arrayAdapter
		if (arrayAdapter == null)
			arrayAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, recordArray);
		else
			arrayAdapter.clear();
		//����б�
		idList.clear();
		//�����ݷ���arrayAdapter
		while (cursor.moveToNext())
		{
			arrayAdapter.add(cursor.getString(1));
			idList.add(cursor.getInt(0));
		}
		//�趨Activity String�ĸ�ʽ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��M��d��");
		//Calendar rightNow = Calendar.getInstance()
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		//Sets the year, month and day of the month fields
		calendar.set(year, month, day);
		//����ListActivity��Title 
		setTitle(sdf.format(calendar.getTime()));
		
		
		//����ListActivity��ListAdapter
		setListAdapter(arrayAdapter);
		//�����myListActivity object 
		myListActivity = null;
		//then ��myListActivity object ����AllRecord activity ֵ
		//ʹ����ΪAllRecord Activity ����
		myListActivity = this;

	}
	/*
	 * ��һ��Menuѡ�ѡ�л���ʱ
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		//���ñ༭Activity
		startEditRecordActivity(position);
	}
	
	/*
	 * @�˵�
	 */
	class MenuItemClickParent
	{
		protected Activity activity;

		public MenuItemClickParent(Activity activity)
		{
			this.activity = activity;
		}
	}
	//Add Record by AddMenu clicking
	class OnAddRecordMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		
		public boolean onMenuItemClick(MenuItem item)
		{
			Intent intent = new Intent(activity, Record.class);
			activity.startActivity(intent);
			return true;
		}

		public OnAddRecordMenuItemClick(Activity activity)
		{
			super(activity);
		}

	}
	/*
	 * ����¼�༭Activity
	 */
	public void startEditRecordActivity(int index)
	{
		Intent intent = new Intent(this, Record.class);
		//�������
		intent.putExtra("edit", true);
		intent.putExtra("id", idList.get(index));
		intent.putExtra("index", index);
		startActivity(intent);
	}
	/*
	 * �޸ļ�¼
	 */
	class OnEditRecordMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		
		public boolean onMenuItemClick(MenuItem item)
		{
			AllRecord allRecord = (AllRecord) activity;
			//����allrecord�õ�ѡ��ɾ�����ݵ�����
			int index = allRecord.getSelectedItemPosition();

			if (index < 0)
				return false;
			//���ñ༭ҳ��
			allRecord.startEditRecordActivity(index);
			return true;
		}

		public OnEditRecordMenuItemClick(Activity activity)
		{
			super(activity);
		}

	}
	
	/*
	 * ɾ����¼
	 */
	class OnDeleteRecordMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		public OnDeleteRecordMenuItemClick(Activity activity)
		{
			super(activity);
			// TODO Auto-generated constructor stub
		}

		
		public boolean onMenuItemClick(MenuItem item)
		{
			
			AllRecord allRecord = (AllRecord) activity;
			//����allrecord�õ�ѡ��ɾ�����ݵ�����
			int index = allRecord.getSelectedItemPosition();

			if (index < 0)
				return false;
			//ɾ��
			recordArray.remove(index);
			int id = idList.get(index);
			idList.remove(index);
			//ҳ�����
			allRecord.setListAdapter(arrayAdapter);
			//��������ɾ��
			Grid.dbService.deleteRecord(id);
			
			return true;
		}

	}
	/*
	 * ���menu�˵�
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		miNewRecord = menu.add(0, 1, 1, "��Ӽ�¼");
		miModifyRecord = menu.add(0, 2, 2, "�޸�/�鿴");
		miDeleteRecord = menu.add(0, 3, 3, "ɾ����¼");
		
		
		
	//�󶨼�����
		miNewRecord.setOnMenuItemClickListener(new OnAddRecordMenuItemClick(
				this));
		miModifyRecord.setOnMenuItemClickListener(editRecordMenuItemClick);
		miDeleteRecord.setOnMenuItemClickListener(new OnDeleteRecordMenuItemClick(this));
		return true;
	}

}
