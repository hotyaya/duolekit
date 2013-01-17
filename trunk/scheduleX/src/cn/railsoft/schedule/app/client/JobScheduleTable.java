package cn.railsoft.schedule.app.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class JobScheduleTable extends Composite {

	private final NavBar navbar = new NavBar();

	public JobScheduleTable() {
		DockPanel dockPanel = new DockPanel();
		dockPanel.setStyleName("jobScheduleTable");
		initWidget(dockPanel);
		dockPanel.add(navbar, DockPanel.NORTH);
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
