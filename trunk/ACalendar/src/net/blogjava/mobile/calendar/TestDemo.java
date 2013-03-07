package net.blogjava.mobile.calendar;


import java.util.ArrayList;
import java.util.HashMap;

//import com.earth.R;
import com.earth.db.DBHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
/*
 * @about
 */
import android.widget.AdapterView.OnItemClickListener;
  
 

public class TestDemo extends Activity
{
	 ListView list;
     LinearLayout linearLayout;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.testdemo);
        
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        list = (ListView)findViewById(R.id.list);
        DBHelper dbHelper = new DBHelper(TestDemo.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("record", null, null, null, null, null, null);
		ArrayList arrayList = new ArrayList();
		while(cursor.moveToNext()){
			HashMap map = new HashMap();
			map.put("etTitle", cursor.getString(cursor.getColumnIndex("etTitle")));
			map.put("etContent", cursor.getString(cursor.getColumnIndex("etContent")));
			
			arrayList.add(map);
		}	
	
		SimpleAdapter listAdapter = new SimpleAdapter(this,
				arrayList,
				R.layout.list_item,
				new String[]{"etTitle","etContent"},
				new int[]{R.id.etTitle,R.id.etContent});
		list.setAdapter(listAdapter);

		if(arrayList.size() == 0){
			Drawable nodata_bg = getResources().getDrawable(R.drawable.nodata_bg);
			linearLayout.setBackgroundDrawable(nodata_bg);
			setTitle("û���κ�����");
		}
        list.setOnItemClickListener(new OnItemClickListener(){

        	/*
        	 * parent:�������е�����,ʹ��HashMap��key��value����ȡ��������е�����
        	 * View:����һ��Ĳ���
        	 * position:���е�һ��
        	 * id:��ʶ 
        	 * */        	
        				
        				public void onItemClick(AdapterView<?> parent, View arg1, int position,
        						long id) {
//        					ȡ����������һ���map,�����arrayList���е�����
        					HashMap map = (HashMap)parent.getItemAtPosition(position);
        					Intent intent = new Intent(TestDemo.this,Main.class);
        					intent.putExtra("map", map);
        					startActivity(intent);
        					finish();
        					
        				}
        	        	
        	        });
	
		
		
}
}