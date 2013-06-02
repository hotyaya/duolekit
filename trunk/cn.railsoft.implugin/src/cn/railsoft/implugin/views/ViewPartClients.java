package cn.railsoft.implugin.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.SWT;
import swing2swt.layout.BorderLayout;

public class ViewPartClients extends ViewPart {
	public ViewPartClients() {

	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		
		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
