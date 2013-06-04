package cn.railsoft.implugin.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.SWT;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class ViewPartClients extends ViewPart {
	private Text text;
	private Text text_1;
	
	public ViewPartClients() {

	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new BorderLayout(0, 0));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(10, 10, 162, 27);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(178, 10, 88, 29);
		btnNewButton.setText("广播");
		
		text_1 = new Text(composite, SWT.BORDER | SWT.MULTI);
		text_1.setBounds(10, 43, 375, 58);
		
		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
