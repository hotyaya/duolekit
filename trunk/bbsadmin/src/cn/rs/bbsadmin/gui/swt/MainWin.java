package cn.rs.bbsadmin.gui.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;
import cn.rs.bbsadmin.notifyinterface.IReceiveNotice;
import cn.rs.bbsadmin.notifyinterface.message.NoticeMessage;
import cn.rs.bbsadmin.notifyinterface.message.ProgressMessage;

public class MainWin implements IReceiveNotice{

	protected Shell shell;
	private Text text;
	private Text text_1;
	private ProgressBar progressBar;

	@Override
	public boolean notifyMessage(NoticeMessage message) {
		text.setText(message +text.getText());
		return true;
	}

	@Override
	public boolean notifyProgress(ProgressMessage progress) {
		if (progress.getiTotle()==0){
			text.setText("");
			return true;
		}
		progressBar.setMinimum(0);
		progressBar.setMaximum(progress.getiTotle());
		progressBar.setState(progress.getiProgress());
		return true;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWin window = new MainWin();
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
		shell.setLocation(display.getClientArea().width / 2 - shell.getSize().x/2, display
                .getClientArea().height / 2 - shell.getSize().y/2);
		shell.open();
		shell.layout();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(new Point(800, 600));
		shell.setMinimumSize(new Point(800, 600));
		shell.setSize(801, 686);
		shell.setText("论坛管理");
		shell.setLayout(new BorderLayout(0, 0));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("系统");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("帮助");
		
		text = new Text(shell, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(BorderLayout.SOUTH);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		composite.setLayoutData(BorderLayout.NORTH);
		FillLayout fl_composite = new FillLayout(SWT.HORIZONTAL);
		fl_composite.marginWidth = 5;
		fl_composite.marginHeight = 5;
		fl_composite.spacing = 5;
		composite.setLayout(fl_composite);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("选项");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setText("帮助");
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setText("升级");
		
		Button btnCheckButton = new Button(composite, SWT.CHECK);
		btnCheckButton.setText("自动运行");
		
		progressBar = new ProgressBar(composite, SWT.NONE);
		
		Composite composite_3 = new Composite(shell, SWT.NONE);
		composite_3.setSize(new Point(120, 120));
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		composite_3.setSize(280, 280);
		composite_3.setLayoutData(BorderLayout.EAST);
		FormLayout fl_composite_3 = new FormLayout();
		fl_composite_3.marginWidth = 10;
		composite_3.setLayout(fl_composite_3);
		
		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 8);
		fd_lblNewLabel.left = new FormAttachment(0, 5);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("信息");
		
		text_1 = new Text(composite_3, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(0, 5);
		fd_text_1.left = new FormAttachment(0, 40);
		text_1.setLayoutData(fd_text_1);
		text_1.setEditable(false);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(BorderLayout.CENTER);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		StyledText styledText = new StyledText(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(styledText);
		scrolledComposite.setMinSize(styledText.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

}
