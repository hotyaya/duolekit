package cn.railsoft.schedule.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class ScheduleX implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	public void onModuleLoad() {

		JobScheduleTable jobScheduleTable = new JobScheduleTable(greetingService);
		jobScheduleTable.setSize("800px", "500px");
		jobScheduleTable.loadJobSchedule();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absolutePanel.setSize("800px", "30px");
		absolutePanel.add(new About(greetingService),0,0);

		TabPanel tabPanel = new TabPanel();
		tabPanel.setSize("800px", "600px");
		tabPanel.add(jobScheduleTable, "我的工作日程表", false);

		DockPanel dp = new DockPanel();
		dp.setSize("800px", "600px");
		dp.add(absolutePanel, DockPanel.SOUTH);
		dp.add(tabPanel, DockPanel.CENTER);
		
		RootPanel.get("gwtwindow").add(dp);
	}
}
