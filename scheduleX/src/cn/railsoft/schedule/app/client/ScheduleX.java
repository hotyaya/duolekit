package cn.railsoft.schedule.app.client;

import java.util.ArrayList;

import cn.railsoft.schedule.app.shared.Jobitem;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class ScheduleX implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	public void onModuleLoad() {
		/**
		 * 刘辉的代码2013/01/16
		 */
		greetingService.getJobItemList(new AsyncCallback<ArrayList<Jobitem>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onSuccess(ArrayList<Jobitem> result) {
				// TODO Auto-generated method stub
				if (result!=null){
					//nameField.setText("+"+result.get(0).getId());
				}else{
					//nameField.setText("getJobItemList_null");
				}
			}
		});

		JobScheduleTable jobScheduleTable = new JobScheduleTable();
		jobScheduleTable.setSize("800px", "500px");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setSize("800px", "30px");
		absolutePanel.add(new About(greetingService),0,0);

		TabPanel tabPanel = new TabPanel();
		tabPanel.setSize("800px", "600px");
		tabPanel.add(jobScheduleTable, "我的工作日程表", false);

		DockPanel dp = new DockPanel();
		dp.setSize("800px", "600px");
		dp.add(absolutePanel, DockPanel.NORTH);
		dp.add(tabPanel, DockPanel.CENTER);
		
		RootPanel.get("gwtwindow").add(dp);
	}
}
