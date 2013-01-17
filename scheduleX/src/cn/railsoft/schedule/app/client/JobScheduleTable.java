package cn.railsoft.schedule.app.client;

import java.util.ArrayList;

import cn.railsoft.schedule.app.shared.Jobitem;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Grid;

public class JobScheduleTable extends Composite {

	GreetingServiceAsync greetingService = null;
	private final NavBar navbar = new NavBar();
	private int irows = 1;
	private int icolumns = 20;
	private ArrayList<Jobitem> jobitemlist = null;
	private Grid grid = null;

	public JobScheduleTable(GreetingServiceAsync greetingService) {
		this.greetingService = greetingService;
		DockPanel dockPanel = new DockPanel();
		dockPanel.setStyleName("jobScheduleTable");
		initWidget(dockPanel);
		dockPanel.add(navbar, DockPanel.NORTH);
		grid = new Grid(irows, icolumns);
		dockPanel.add(grid, DockPanel.CENTER);
		// grid.resize(10, 10);
		// grid.setText(5, 5, "我的测试数据");
		//loadJobScheduleData();
	}

	public void loadJobSchedule() {
		greetingService.getJobItemList(new AsyncCallback<ArrayList<Jobitem>>() {
			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(ArrayList<Jobitem> result) {
				if (result != null) {
					jobitemlist = result;
					// nameField.setText("+"+result.get(0).getId());
				} else {
					// nameField.setText("getJobItemList_null");
				}
				loadJobScheduleData();
			}
		});

	}

	private void loadJobScheduleData(){
		if (jobitemlist!=null){
			irows = jobitemlist.size();
			grid.resize(irows + 1, icolumns);
			for (int i = 0; i < irows; i++) {
				grid.getCellFormatter().setStyleName(i+1, 1, "tablecell");
				grid.setText(i + 1, 0, jobitemlist.get(i).getId());
				grid.setText(i + 1, 1, jobitemlist.get(i).getContent1());
				grid.setText(i + 1, 2, jobitemlist.get(i).getContent2());
			}
		}else{
			grid.resize(1, icolumns);
		}
		grid.setText(0, 0, "序号");
		grid.setText(0, 1, "2");
	}
	
	private class NavBar extends Composite implements ClickHandler {
		public final DockPanel bar = new DockPanel();
		public final Button gotoFirst = new Button("&lt;&lt;", this);
		public final Button gotoNext = new Button("&gt;", this);
		public final Button gotoPrev = new Button("&lt;", this);
		public final HTML status = new HTML();

		public NavBar() {
			initWidget(bar);
			bar.setStyleName("navbar");
			status.setStyleName("status");

			HorizontalPanel buttons = new HorizontalPanel();
			buttons.add(gotoFirst);
			buttons.add(gotoPrev);
			buttons.add(gotoNext);
			bar.add(buttons, DockPanel.EAST);
			bar.setCellHorizontalAlignment(buttons, DockPanel.ALIGN_RIGHT);
			bar.add(status, DockPanel.CENTER);
			bar.setVerticalAlignment(DockPanel.ALIGN_MIDDLE);
			bar.setCellHorizontalAlignment(status, HasAlignment.ALIGN_RIGHT);
			bar.setCellVerticalAlignment(status, HasAlignment.ALIGN_MIDDLE);
			bar.setCellWidth(status, "100%");

			// Initialize prev & first button to disabled.
			//
			gotoPrev.setEnabled(false);
			gotoFirst.setEnabled(false);
		}

		public void onClick(ClickEvent event) {
			Object source = event.getSource();
			if (source == gotoNext) {
				// startRow += getDataRowCount();
				refresh();
			} else if (source == gotoPrev) {
				// startRow -= getDataRowCount();
				// if (startRow < 0) {
				// startRow = 0;
				// }
				refresh();
			} else if (source == gotoFirst) {
				// startRow = 0;
				refresh();
			}
		}
	}

	public void refresh() {
		navbar.gotoFirst.setEnabled(false);
		navbar.gotoPrev.setEnabled(false);
		navbar.gotoNext.setEnabled(false);
		// setStatusText("Please wait...");
		// provider.updateRowData(startRow, grid.getRowCount() - 1, acceptor);
	}

}
