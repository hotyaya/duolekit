package com.itheima.redbaby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.itheima.redbaby.adapter.ProductAdapter;
import com.itheima.redbaby.parser.ProductListParser;
import com.itheima.redbaby.vo.ProductListVo;
import com.itheima.redbaby.vo.RequestVo;

public class HotproductActivity extends BaseWapperActivity {
	private List<ProductListVo> List;
	private ListView listView;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findViewById() {
		listView = (ListView) findViewById(R.id.promBulldtinLv);
		//listView = (ListView) findViewById(R.id.productList);
		System.out.println(listView==null);
	}
	
	//加载布局文件
	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.prom_bulletin_activity);
		//setContentView(R.layout.product_list_activity);
		List = new ArrayList<ProductListVo>();
		setTitle("热卖单品");
		
	}
	//执行逻辑
	@Override
	protected void processLogic() {
		RequestVo reqVo = new RequestVo();
		reqVo.requestUrl = R.string.url_hotproduct;
		reqVo.context = context;
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("page", "");
		requestDataMap.put("pageNum", "");
		//requestDataMap.put("orderby", "sale_down");
		reqVo.requestDataMap = requestDataMap;
		
		reqVo.jsonParser = new ProductListParser();
		
		super.getDataFromServer(reqVo, new DataCallback<List<ProductListVo>>() {

			@Override
			public void processData(List<ProductListVo> paramObject,
					boolean paramBoolean) {
				List = paramObject;
				ProductAdapter newproductAdapter = new ProductAdapter(context, listView ,List);
				listView.setAdapter(newproductAdapter);
			}

			
		});
	}
	//设置监听事件
	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ProductListVo vo = (ProductListVo) listView.getItemAtPosition(position);
				//String prodId = vo.getId()+"";
				Intent producutlistIntent = new Intent(HotproductActivity.this,ProductDetailActivity.class);
				//将ID传递到商品分类显示中，显示相关内容
				producutlistIntent.putExtra("id",  vo.getId());
				//跳转到新的activity
				startActivity(producutlistIntent);
			}
		});
		
	}
}
