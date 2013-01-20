package cn.railsoft.schedule.app.client;

import java.util.ArrayList;
import java.util.Hashtable;

import cn.railsoft.schedule.app.shared.Jobitem;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class JobScheduleTable extends Composite implements
		INotifyJobScheduleChange {

	GreetingServiceAsync greetingService = null;
	private final NavBar navbar = new NavBar();
	private int irows = 1;
	private int icolumns = 12;
	private ArrayList<Jobitem> jobitemlist = null;
	private Hashtable<String, Jobitem> jobitemlisttable = new Hashtable<String, Jobitem>();
	private Grid grid = null;
	private JobScheduleInput input = null;

	public JobScheduleTable(GreetingServiceAsync greetingService) {
		this.greetingService = greetingService;
		input = new JobScheduleInput(greetingService, this);
		DockPanel dockPanel = new DockPanel();
		dockPanel.setStyleName("jobScheduleTable");
		initWidget(dockPanel);
		dockPanel.add(navbar, DockPanel.NORTH);
		grid = new Grid(irows, icolumns);
		dockPanel.add(grid, DockPanel.CENTER);
		// grid.resize(10, 10);
		// grid.setText(5, 5, "我的测试数据");
		// loadJobScheduleData();
	}

	public void delJobitem() {
		int m = 0;
		for (int i = 0; i < jobitemlist.size(); i++) {
			if (((CheckBox) grid.getWidget(i + 1, 0)).getValue()) {
				m++;
				Window.alert(""
						+ ((CheckBox) grid.getWidget(i + 1, 0)).getFormValue());

				greetingService.delJobItem(jobitemlisttable.get(((CheckBox) grid.getWidget(i + 1, 0)).getFormValue()), 
						new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						
					}

					public void onSuccess(String result) {
						if (result.equals("SUCCESS")) {
							notifyJobScheduleChange();
							Window.alert("删除成功！");
						} else {
							Window.alert("删除失败！");
						}
					}
				});

				
			}
		}
		if (m == 0)
			Window.alert("您没有选择要删除的项目！");
		notifyJobScheduleChange();
	}

	@Override
	public void notifyJobScheduleChange() {
		// TODO Auto-generated method stub
		refresh();
		loadJobSchedule();
	}

	public void loadJobSchedule() {
		greetingService.getJobItemList("ALL",
				new AsyncCallback<ArrayList<Jobitem>>() {
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

	/**
	 * 根据取得对象列表，格式化并显示数组； 2013/01/19
	 */
	private void loadJobScheduleData() {
		jobitemlisttable.clear();
		if (jobitemlist != null) {
			irows = jobitemlist.size();
			grid.resize(irows + 1, icolumns);
			for (int i = 0; i < irows; i++) {
				jobitemlisttable.put(jobitemlist.get(i).getId(),
						jobitemlist.get(i));
				// grid.getCellFormatter().setStyleName(i + 1, 1, "tablecell");
				CheckBox cb = new CheckBox(jobitemlist.get(i).getSeq() + "");
				cb.setFormValue(jobitemlist.get(i).getId());// test
				grid.setWidget(i + 1, 0, cb);
				// grid.setText(i + 1, 1, jobitemlist.get(i).getId());
				grid.setText(i + 1, 2, jobitemlist.get(i).getActionDate() + "");
				grid.setText(i + 1, 3, jobitemlist.get(i).getType());
				grid.setText(i + 1, 4, jobitemlist.get(i).getNumNo());
				grid.setText(i + 1, 5, jobitemlist.get(i).getContent1());
				grid.setText(i + 1, 6, jobitemlist.get(i).getContent2());
				grid.setText(i + 1, 7, jobitemlist.get(i).getSource());
				grid.setText(i + 1, 8, jobitemlist.get(i).getContent3());
				grid.setText(i + 1, 9, jobitemlist.get(i).getContent4());
				grid.setText(i + 1, 10, jobitemlist.get(i).getStatus());
				grid.setText(i + 1, 11, jobitemlist.get(i).getContent5());
			}
		} else {
			grid.resize(1, icolumns);
		}
		grid.getColumnFormatter().setStyleName(0, "tablecolumn30");
		grid.setText(0, 0, "序号");
		grid.getColumnFormatter().setStyleName(1, "tablecolumn0");
		grid.setText(0, 1, "UUID");
		grid.getColumnFormatter().setStyleName(2, "tablecell2");
		grid.setText(0, 2, "收办日期");
		grid.getColumnFormatter().setStyleName(3, "tablecolumn50");
		grid.setText(0, 3, "事物类别");
		grid.getColumnFormatter().setStyleName(4, "tablecell2");
		grid.setText(0, 4, "文号");
		grid.getColumnFormatter().setStyleName(5, "tablecolumn100");
		grid.setText(0, 5, "事项");
		grid.getColumnFormatter().setStyleName(6, "tablecolumn30");
		grid.setText(0, 6, "主管领导批示");
		grid.getColumnFormatter().setStyleName(7, "tablecell2");
		grid.setText(0, 7, "来源");
		grid.getColumnFormatter().setStyleName(8, "tablecolumn50");
		grid.setText(0, 8, "科长意见");
		grid.getColumnFormatter().setStyleName(9, "tablecell2");
		grid.setText(0, 9, "摘要");
		grid.getColumnFormatter().setStyleName(10, "tablecolumn50");
		grid.setText(0, 10, "状态");
		grid.getColumnFormatter().setStyleName(11, "tablecolumn50");
		grid.setText(0, 11, "结果");
	}

	private class NavBar extends Composite implements ClickHandler {
		public final DockPanel bar = new DockPanel();
		public final Button gotoFirst = new Button("&lt;&lt;", this);
		public final Button gotoNext = new Button("&gt;", this);
		public final Button gotoPrev = new Button("&lt;", this);
		public final Button gotoNew = new Button("+", this);
		public final Button gotoDel = new Button("-", this);
		public final HTML status = new HTML();

		public NavBar() {
			initWidget(bar);
			bar.setStyleName("navbar");
			status.setStyleName("status");

			HorizontalPanel buttons = new HorizontalPanel();
			buttons.add(gotoNew);
			buttons.add(gotoFirst);
			buttons.add(gotoPrev);
			buttons.add(gotoNext);
			buttons.add(gotoDel);

			bar.add(buttons, DockPanel.EAST);
			bar.setCellHorizontalAlignment(buttons, DockPanel.ALIGN_RIGHT);
			bar.add(status, DockPanel.CENTER);
			bar.setVerticalAlignment(DockPanel.ALIGN_MIDDLE);
			bar.setCellHorizontalAlignment(status, HasAlignment.ALIGN_RIGHT);
			bar.setCellVerticalAlignment(status, HasAlignment.ALIGN_MIDDLE);
			bar.setCellWidth(status, "100%");

			// Initialize prev & first button to disabled.
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
			} else if (source == gotoNew) {
				input.jobitem = null; // 赋值
				input.showInput();
			} else if (source == gotoDel) {
				delJobitem();
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
