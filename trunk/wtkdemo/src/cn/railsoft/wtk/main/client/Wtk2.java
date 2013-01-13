package cn.railsoft.wtk.main.client;

import com.google.gwt.core.client.EntryPoint;
//import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
/*import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;*/

public class Wtk2 implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(new Button("模块2"));
//		Window window = new Window();
//		window.setSize("800", "600");
//		window.setHeading("测试");
//		window.show();
		
/*		Label lblNewLabel = new Label("New label");
		rootPanel.add(lblNewLabel, 20, 46);
		
		TextBox textBox = new TextBox();
		rootPanel.add(textBox, 83, 46);
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("信息化处");
		comboBox.addItem("多经处");
		rootPanel.add(comboBox, 83, 86);
		comboBox.setSize("169px", "22px");
*/	}
}
