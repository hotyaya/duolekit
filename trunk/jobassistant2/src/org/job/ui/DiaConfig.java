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
import org.job.HibernateUtil;
import org.job.dao.entity.Sysprop;
import org.job.dao.entity.SyspropDAO;

public class DiaConfig extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Button btnCheckButton;
	private Button btnCheckButton_1;
	private Button btnCheckButton_2;

	/**
	 * 
	 * 	1.USERID		UUID
		2.USERNAME		NAME
		3.USERUNIT		使用者所在单位；（单位）
		4.USERDEPT		使用者所在单位；（部门）
		5.OADO			登录帐号不同；boolean
		6.OAUSER		登录帐号不同；xxhclh
		7.OAPASS		登录帐号不同；a
		8.TGDO			登录帐号不同；boolean
		9.TGUSER		登录帐号不同；zhanglan
		10.TGPASS		登录帐号不同；a
		11.DBDO			登录帐号不同；boolean
		12.DBUSER		登录帐号不同；user
		13.DBPASS		登录帐号不同；pass
	 * 	text			ID
	 * 	text_1			USER
	 *	text_2			单位
		text_3			部门
		btnCheckButton 	启用电报追踪
		text_4			电报用户
		text_5			电报密码
		btnCheckButton_1 公文追踪
		text_6			公文用户
		text_7			公文密码
		btnCheckButton_2 待办
		text_8			待办用户
		text_9			待办密码
	 */
	void init(){
		try{
			Sysprop pro1 = null;
			Sysprop pro2 = null;
			Sysprop pro3 = null;
			Sysprop pro4 = null;
			Sysprop pro5 = null;
			Sysprop pro6 = null;
			Sysprop pro7 = null;
			Sysprop pro8 = null;
			Sysprop pro9 = null;
			Sysprop pro10 = null;
			Sysprop pro11 = null;
			Sysprop pro12 = null;
			Sysprop pro13 = null;

			Session session = HibernateUtil.currentSession();
			Transaction tran = null;
			tran = session.beginTransaction();
			SyspropDAO dao =new SyspropDAO();
			pro1 = dao.findById("USERID");
			pro2 = dao.findById("USERNAME");
			pro3 = dao.findById("USERUNIT");
			pro4 = dao.findById("USERDEPT");
			pro5 = dao.findById("OADO");
			pro6 = dao.findById("OAUSER");
			pro7 = dao.findById("OAPASS");
			pro8 = dao.findById("TGDO");
			pro9 = dao.findById("TGUSER");
			pro10 = dao.findById("TGPASS");
			pro11 = dao.findById("DBDO");
			pro12 = dao.findById("DBUSER");
			pro13 = dao.findById("DBPASS");
			//session.save(item);
			tran.commit();
			
		 	text.setText("");//			ID
		 	text_1.setText("");//			USER
		 	text_2.setText("");//			单位
			text_3.setText("");//			部门
			btnCheckButton.setSelection(true);// 	启用电报追踪
			text_4.setText("");//			电报用户
			text_5.setText("");//			电报密码
			btnCheckButton_1.setSelection(true);// 公文追踪
			text_6.setText("");//			公文用户
			text_7.setText("");//			公文密码
			btnCheckButton_2.setSelection(true);// 待办
			text_8.setText("");//			待办用户
			text_9.setText("");//			待办密码
			
			Thread.sleep(100);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	void exit() {
		this.getParent().dispose();
	}

	public static void main(String[] args) {
		new DiaConfig(new Shell(), SWT.ICON_INFORMATION).open();
		
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
	public DiaConfig(Shell parent, int style) {
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
		init();//20140211
		setScreenPoint(shell);//居中；
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
		shell.setSize(455, 372);
		shell.setText("配置与选项");
		shell.setLayout(new GridLayout(4, false));

		Label lblNewLabel_9 = new Label(shell, SWT.NONE);
		lblNewLabel_9.setAlignment(SWT.CENTER);
		GridData gd_lblNewLabel_9 = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 4, 1);
		gd_lblNewLabel_9.heightHint = 30;
		lblNewLabel_9.setLayoutData(gd_lblNewLabel_9);
		lblNewLabel_9.setText("提示");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("用户ID");

		text = new Text(shell, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setText("用户名");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3,
				1));

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_2.setText("单位");

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_3.setText("部门");

		text_3 = new Text(shell, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(shell, SWT.NONE);

		btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setText("启用电报追踪");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_4.setText("电报用户");

		text_4 = new Text(shell, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_5.setText("电报密码");

		text_5 = new Text(shell, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(shell, SWT.NONE);

		btnCheckButton_1 = new Button(shell, SWT.CHECK);
		btnCheckButton_1.setText("启用公文收文追踪");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Label label = new Label(shell, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label.setText("公文用户");

		text_6 = new Text(shell, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_6.setText("公文密码");

		text_7 = new Text(shell, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(shell, SWT.NONE);

		btnCheckButton_2 = new Button(shell, SWT.CHECK);
		btnCheckButton_2.setText("启用公文待办追踪");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_7.setText("待办用户");

		text_8 = new Text(shell, SWT.BORDER);
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblNewLabel_8 = new Label(shell, SWT.NONE);
		lblNewLabel_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_8.setText("待办密码");

		text_9 = new Text(shell, SWT.BORDER);
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(8, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 4, 1);
		gd_composite.heightHint = 66;
		composite.setLayoutData(gd_composite);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Button btnNewButton = new Button(composite, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton.widthHint = 90;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("保存");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exit();
			}
		});
		GridData gd_btnNewButton_1 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_btnNewButton_1.widthHint = 90;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("退出");
	}
}
