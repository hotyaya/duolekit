package railway.bj.admin.my.job.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;

public class RecvDocInput extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RecvDocInput(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("文件ID号");
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("主要内容");
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_1.heightHint = 71;
		text_1.setLayoutData(gd_text_1);
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("传 达 人");
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_2.heightHint = 74;
		gd_text_2.widthHint = 166;
		text_2.setLayoutData(gd_text_2);
		
		List list = new List(this, SWT.BORDER | SWT.V_SCROLL);
		list.setItems(new String[] {"范久顺", "李  伟", "吴文楠", "转  交"});
		GridData gd_list = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_list.widthHint = 87;
		list.setLayoutData(gd_list);
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("接收日期");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group group = new Group(this, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		btnNewButton.setText("向前");
		
		Button btnNewButton_1 = new Button(group, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		btnNewButton_1.setText("向后");
		
		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("签收标识");
		
		text_4 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group group_1 = new Group(this, SWT.NONE);
		group_1.setLayout(new GridLayout(2, false));
		
		Button btnRadioButton = new Button(group_1, SWT.RADIO);
		btnRadioButton.setText("标准");
		
		Button btnRadioButton_1 = new Button(group_1, SWT.RADIO);
		btnRadioButton_1.setSize(119, 20);
		btnRadioButton_1.setText("重要");
		
		Label lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_5.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("备  注");
		
		text_5 = new Text(this, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		GridData gd_text_5 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_5.heightHint = 78;
		text_5.setLayoutData(gd_text_5);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
