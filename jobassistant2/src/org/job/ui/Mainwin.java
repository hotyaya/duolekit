package org.job.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FillLayout;

public class Mainwin {

	protected Shell shell;
	private Text text;
	private Table table;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Mainwin window = new Mainwin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(400, 700);
		shell.setText("电报公文提醒系统");
		shell.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("系统");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("设置");
		
		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("帮助");
		
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		composite.setLayout(new GridLayout(5, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite.heightHint = 137;
		composite.setLayoutData(gd_composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("请选择查看日期");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("向前");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setText("向后");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		lblNewLabel_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("提示信息");
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1);
		gd_composite_1.heightHint = 40;
		composite_1.setLayoutData(gd_composite_1);
		
		Button btnNewButton_2 = new Button(composite_1, SWT.FLAT);
		
		Button btnNewButton_3 = new Button(composite_1, SWT.NONE);
		
		Button btnNewButton_4 = new Button(composite_1, SWT.NONE);
		
		Button btnNewButton_5 = new Button(composite_1, SWT.NONE);
		
		Button btnNewButton_6 = new Button(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1);
		gd_composite_2.heightHint = 51;
		composite_2.setLayoutData(gd_composite_2);
		
		Button btnCheckButton = new Button(composite_2, SWT.CHECK);
		btnCheckButton.setBounds(10, 10, 53, 17);
		btnCheckButton.setText("电报");
		
		Button btnCheckButton_1 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_1.setBounds(69, 10, 53, 17);
		btnCheckButton_1.setText("公文");
		
		Button btnCheckButton_2 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_2.setBounds(156, 10, 53, 17);
		btnCheckButton_2.setText("过滤");
		
		Button btnNewButton_7 = new Button(composite_2, SWT.NONE);
		btnNewButton_7.setBounds(215, 5, 80, 27);
		btnNewButton_7.setText("设置过滤");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		text = new Text(shell, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}
}
