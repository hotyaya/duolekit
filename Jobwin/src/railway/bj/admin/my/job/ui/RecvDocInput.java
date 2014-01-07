package railway.bj.admin.my.job.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;

public class RecvDocInput extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Label lblNewLabel_5;
	private Text text_5;
	private List list;
	private Button btnNewButton;
	private Button btnNewButton_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RecvDocInput(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
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
		shell.setSize(570, 520);
		shell.setText("文件签收");
		shell.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("文件ID号");
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setEditable(false);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_5.setText("主要信息");
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setEditable(false);
		GridData gd_text_5 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_5.heightHint = 53;
		text_5.setLayoutData(gd_text_5);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("传达人");
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text.heightHint = 72;
		gd_text.widthHint = 251;
		text.setLayoutData(gd_text);
		
		list = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		list.setItems(new String[] {"范久顺", "李  伟", "吴文楠", "转  交"});
		list.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		GridData gd_list = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_list.heightHint = 72;
		gd_list.widthHint = 103;
		list.setLayoutData(gd_list);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("接收日期");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
		gd_text_1.widthHint = 252;
		text_1.setLayoutData(gd_text_1);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("签收标记");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1);
		gd_text_2.widthHint = 251;
		text_2.setLayoutData(gd_text_2);
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("备注");
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		GridData gd_text_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_3.heightHint = 127;
		gd_text_3.widthHint = 299;
		text_3.setLayoutData(gd_text_3);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton.setText("签收");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		btnNewButton_1.setText("退出");

	}

}
