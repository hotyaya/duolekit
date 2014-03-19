package my.outline.gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.hibernate.Session;

public class DInputTag extends Dialog {
	Session session = null;
	protected Object result;
	protected Shell shlInputtag;
	private Text text;
	private Text text_1;
	private Text text_2;

	void loadTags(){
		
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
	 * @param parent
	 * @param style
	 */
	public DInputTag(Shell parent, int style,Session session) {
		super(parent, style);
		setText("SWT Dialog");
		this.session = session; 
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		setScreenPoint(shlInputtag);
		shlInputtag.open();
		shlInputtag.layout();
		Display display = getParent().getDisplay();
		while (!shlInputtag.isDisposed()) {
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
		shlInputtag = new Shell(getParent(), getStyle());
		shlInputtag.setSize(450, 175);
		shlInputtag.setText("InputTag");
		shlInputtag.setLayout(new GridLayout(2, false));
		
		ToolBar toolBar = new ToolBar(shlInputtag, SWT.FLAT | SWT.RIGHT);
		GridData gd_toolBar = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_toolBar.widthHint = 381;
		toolBar.setLayoutData(gd_toolBar);
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setText("保存");
		
		ToolItem tltmNewItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);
		tltmNewItem_2.setText("New Item");
		
		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.setText("退出");
		
		Label lblNewLabel = new Label(shlInputtag, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("标识编号");
		
		text = new Text(shlInputtag, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(shlInputtag, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("父标识");
		
		Combo combo = new Combo(shlInputtag, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_2 = new Label(shlInputtag, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("标识名");
		
		text_1 = new Text(shlInputtag, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text_2 = new Text(shlInputtag, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

	}
}
