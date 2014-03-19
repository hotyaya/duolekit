package org.job.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.Application;
import org.job.dao.entity.Sysprop;
import org.job.dao.entity.SyspropDAO;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.wb.swt.SWTResourceManager;

public class DiaAbout extends Dialog {
	protected Object result;
	protected Shell shell;

	public void hide(){
		shell.setVisible(false);
	}
	
	public static void main(String[] args) {
		new DiaAbout(new Shell(), SWT.ICON_INFORMATION).open();
	}

	protected void setScreenPoint(Shell shell) {
		int width = shell.getMonitor().getClientArea().width;
		int height = shell.getMonitor().getClientArea().height;
		int x = shell.getSize().x;
		int y = shell.getSize().y;
		if (x > width) {
			shell.getSize().x = width;
		}
		if (y > height) {
			shell.getSize().y = height;
		}
		shell.setLocation((width - x) / 2, (height - y) / 2);
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public DiaAbout(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		setScreenPoint(shell);
		
		StyledText styledText = new StyledText(shell, SWT.BORDER);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		styledText.setEditable(false);
		styledText.setText("\r\n    感谢您使用("+
				Application.getV("SYSVERSION")+"-"+Application.getV("SUBVERSION")+")"
				+"办公软件系统！\r\n\r\n    技术支持电话：021-24236\r\n"+"\r\n    ["+Application.getV("PUBDATE")+"]");
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Button btnNewButton = new Button(shell, SWT.CENTER);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hide();
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 82;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("退出");
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(500, 400);
		shell.setText("关于");
		shell.setLayout(new GridLayout(1, false));
	}
}
