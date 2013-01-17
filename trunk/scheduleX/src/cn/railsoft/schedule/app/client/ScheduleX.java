package cn.railsoft.schedule.app.client;

import java.util.ArrayList;

import cn.railsoft.schedule.app.shared.Jobitem;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScheduleX implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
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
		
		DockPanel dp = new DockPanel();
		dp.setSize("800px", "600px");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setSize("800px", "30px");
		horizontalPanel.add(new About(greetingService));
//		horizontalPanel.add(nameField);
//		horizontalPanel.add(sendButton);
//		horizontalPanel.add(errorLabel);
		dp.add(horizontalPanel, DockPanel.NORTH);
		
		TabPanel tabPanel = new TabPanel();
		tabPanel.setSize("800px", "600px");
		dp.add(tabPanel, DockPanel.CENTER);
		
		RootPanel.get("gwtwindow").add(dp);
	}
}
